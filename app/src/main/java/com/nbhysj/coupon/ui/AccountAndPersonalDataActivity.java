package com.nbhysj.coupon.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSAuthCredentialsProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.common.Enum.GenderEnum;
import com.nbhysj.coupon.common.Enum.UploadFileTypeEnum;
import com.nbhysj.coupon.common.Enum.UploadTypeEnum;
import com.nbhysj.coupon.contract.UserInfoContract;
import com.nbhysj.coupon.model.UserInfoModel;
import com.nbhysj.coupon.model.request.UpdateUserInfoRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.ThirdPartyLoginStatusResponse;
import com.nbhysj.coupon.oss.Config;
import com.nbhysj.coupon.oss.service.ImageService;
import com.nbhysj.coupon.oss.service.OssService;
import com.nbhysj.coupon.oss.view.UIDisplayer;
import com.nbhysj.coupon.presenter.UserInfoPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.DateUtil;
import com.nbhysj.coupon.util.EncryptedSignatureUtil;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.nbhysj.coupon.view.GlideImageView;
import com.nbhysj.coupon.dialog.UserInfoEditDialog;
import com.nbhysj.coupon.widget.glide.GifSizeFilter;
import com.nbhysj.coupon.widget.glide.Glide4Engine;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhihu.matisse.listener.OnCheckedListener;
import com.zhihu.matisse.listener.OnSelectedListener;

import java.io.File;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.nbhysj.coupon.oss.Config.STS_SERVER_URL;

/**
 * @auther：hysj created on 2019/03/08
 * description：账号与个人资料
 */
public class AccountAndPersonalDataActivity extends BaseActivity<UserInfoPresenter, UserInfoModel> implements UserInfoContract.View {

    //头像
    @BindView(R.id.image_head_portrait)
    GlideImageView mImageUserAvatar;
    //昵称
    @BindView(R.id.tv_nickname)
    TextView mTvNickname;
    //性别
    @BindView(R.id.tv_gender_options)
    TextView mTvGenderOptions;
    //出生日期
    @BindView(R.id.tv_date_of_birth)
    TextView mTvDateOfBirth;
    //昵称
    private String nickName;

    //性别 0.未知 1.男 2.女 3.保密
    private String gender;
    //性别
    private int sex;

    private int oprateFlag = 0; //1.用户昵称 2.性别 3.出生日期 4.常居城市 5.个人简介 6.头像

    private int REQUEST_CODE = 0;
    //生日日期
    private String birthday;

    //负责所有的界面更新
    private UIDisplayer mUIDisplayer;

    //OSS的上传下载
    private OssService mService;

    StringBuffer stringBuffer = new StringBuffer();

    private String objectName;

    //照片选取返回code
    private int REQUEST_CODE_POST_PHOTO = 23;
    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_account_and_personal_data;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setBar(AccountAndPersonalDataActivity.this, getResources().getString(R.string.str_account_and_personal_data), R.mipmap.nav_ico_back_black);
        userId = getSharedPreferencesUserId();
    }

    @Override
    public void initData() {
        String avatar = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.USER_AVATAR, "");
        if (!TextUtils.isEmpty(avatar))
        {
            mImageUserAvatar.loadCircle(avatar);
        }
        String nickname = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.NICKNAME, "");
        int sex = (int) SharedPreferencesUtils.getData(SharedPreferencesUtils.USER_SEX, 0);
        birthday = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.USER_BIRTHDAY, "");
        userId = getSharedPreferencesUserId();
        String genderValue = GenderEnum.getEnumByKey(sex).getValue();
        //性别
        if (!TextUtils.isEmpty(genderValue)) {
            mTvGenderOptions.setText(genderValue);
        }

        //昵称
        if (!TextUtils.isEmpty(nickname)) {
            mTvNickname.setText(nickname);
        }

        //生日
        if (!TextUtils.isEmpty(birthday)) {
            mTvDateOfBirth.setText(birthday);
        }

        mUIDisplayer = new UIDisplayer(this, new UIDisplayer.UIDisPlayerListener() {
            @Override
            public void setUIDisPlayerListener() {
                // dismissProgressDialog();

                oprateFlag = 6;
                updateAvatar(objectName);
            }
        });
        //uploadType(头像1，帖子2)

        mService = initOSS(Config.OSS_ENDPOINT, Config.BUCKET_NAME, mUIDisplayer);
        //设置上传的callback地址，目前暂时只支持putObject的回调
        mService.setCallbackAddress(Config.OSS_CALLBACK_URL);

        //图片服务和OSS使用不同的endpoint，但是可以共用SDK，因此只需要初始化不同endpoint的OssService即可
        // mIMGService = new ImageService(initOSS(mImgEndpoint, mBucket, mUIDisplayer));
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    //编辑用户信息提示框
    private void showUserInfoDialog() {
        oprateFlag = 1;
        LayoutInflater inflater = LayoutInflater.from(this);
        View layout = inflater.inflate(R.layout.dialog_userinfo_edit, null);
        TextView title_tv = (TextView) layout.findViewById(R.id.tv_title);
        final EditText mEdtNickName = (EditText) layout.findViewById(R.id.edt_nickname);
        TextView sure_tv = (TextView) layout.findViewById(R.id.sure_tv);
        TextView dissmiss_tv = (TextView) layout.findViewById(R.id.tv_dissmiss);
        ImageView mImgCancelDialog = layout.findViewById(R.id.img_cancel_dialog);
        TextView mTvInputLimitation = layout.findViewById(R.id.tv_input_limitation);
        final UserInfoEditDialog dialog = new UserInfoEditDialog(this, 0, 0, layout, R.style.dialog);
        dialog.show();
        sure_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nickName = mEdtNickName.getText().toString();
                if (TextUtils.isEmpty(nickName)) {
                    showToast(AccountAndPersonalDataActivity.this, getResources().getString(R.string.str_input_nickname));
                    return;
                }
                updateNickName(nickName);

                dialog.dismiss();
            }
        });
        dissmiss_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        mImgCancelDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        mEdtNickName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int nicknameLength = charSequence.length();
                mTvInputLimitation.setText(nicknameLength + " / 15");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    //用户性别选择提示框
    private void showGenderOptionsDialog() {
        oprateFlag = 2;
        LayoutInflater inflater = LayoutInflater.from(this);
        View layout = inflater.inflate(R.layout.dialog_gender_options, null);
        RelativeLayout mRlytMale = layout.findViewById(R.id.rlyt_male);
        RelativeLayout mRlytFemale = layout.findViewById(R.id.rlyt_female);
      /*  title_tv.setText(title);
        editText.setText(content);*/
        final UserInfoEditDialog dialog = new UserInfoEditDialog(this, 0, 0, layout, R.style.dialog);
        dialog.show();
        mRlytMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = GenderEnum.MALE.getValue();
                sex = GenderEnum.MALE.getKey();
                updateSex(sex);
                dialog.dismiss();
            }
        });
        mRlytFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = GenderEnum.FEMALE.getValue();
                sex = GenderEnum.FEMALE.getKey();
                updateSex(sex);
                dialog.dismiss();
            }
        });
    }

    @OnClick({R.id.rlyt_update_username, R.id.tv_gender_options, R.id.tv_date_of_birth, R.id.rlyt_personal_profile, R.id.llyt_avatar})
    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.rlyt_update_username:

                showUserInfoDialog();
                break;
            case R.id.tv_gender_options:
                showGenderOptionsDialog();
                break;
            case R.id.tv_date_of_birth:
                Intent mIntent = new Intent();
                mIntent.setClass(AccountAndPersonalDataActivity.this, ChooseTheDateOfBirthActivity.class);
                mIntent.putExtra("birthday", birthday);
                startActivityForResult(mIntent, REQUEST_CODE);
                break;
            case R.id.rlyt_personal_profile:
                toActivity(EditorPersonalProfileActivity.class);
                break;
            case R.id.llyt_avatar:
                AndPermission.with(AccountAndPersonalDataActivity.this)
                        .permission(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
                        .callback(new PermissionListener() {
                            @Override
                            public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {

                                Matisse.from(AccountAndPersonalDataActivity.this)
                                        .choose(MimeType.ofAll(), false)
                                        .countable(true)
                                        .capture(false)
                                        .captureStrategy(
                                                new CaptureStrategy(true, "com.zhihu.matisse.sample.fileprovider"))
                                        .maxSelectable(1)

                                        .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                                        .gridExpectedSize(
                                                getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                                        .thumbnailScale(0.85f)
                                        // for glide-V3
//                                            .imageEngine(new GlideEngine())
                                        // for glide-V4
                                        .imageEngine(new Glide4Engine())
                                        .setOnSelectedListener(new OnSelectedListener() {
                                            @Override
                                            public void onSelected(
                                                    @NonNull List<Uri> uriList, @NonNull List<String> pathList) {
                                                // DO SOMETHING IMMEDIATELY HERE
                                                Log.e("onSelected", "onSelected: pathList=" + pathList);

                                            }
                                        })
                                        .originalEnable(true)
                                        .maxOriginalSize(10)
                                        .setOnCheckedListener(new OnCheckedListener() {
                                            @Override
                                            public void onCheck(boolean isChecked) {
                                                // DO SOMETHING IMMEDIATELY HERE
                                                Log.e("isChecked", "onCheck: isChecked=" + isChecked);
                                            }
                                        })
                                        .forResult(REQUEST_CODE_POST_PHOTO);
                            }

                            @Override
                            public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                                Uri packageURI = Uri.parse("package:" + getPackageName());
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                // Toast.makeText(MainActivity.this, "没有权限无法扫描呦", Toast.LENGTH_LONG).show();
                            }
                        }).start();
                break;
            default:
                break;
        }
    }

    @Override
    public void getUserInfoResult(BackResult res) {

    }

    @Override
    public void getThirdPartyLoginStatusResult(BackResult<ThirdPartyLoginStatusResponse> res) {

    }

    @Override
    public void updateInformationResult(BackResult res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    if (oprateFlag == 1) {
                        mTvNickname.setText(nickName);
                        SharedPreferencesUtils.putData(SharedPreferencesUtils.NICKNAME, nickName);
                    } else if (oprateFlag == 2) {
                        SharedPreferencesUtils.putData(SharedPreferencesUtils.USER_SEX, sex);
                        mTvGenderOptions.setText(gender);
                    } else if (oprateFlag == 6) {
                        showToast(AccountAndPersonalDataActivity.this, "上传成功");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(AccountAndPersonalDataActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void userLogoutResult(BackResult res) {

    }

    @Override
    public void showMsg(String msg) {
        dismissProgressDialog();
        showToast(AccountAndPersonalDataActivity.this, Constants.getResultMsg(msg));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
                birthday = data.getStringExtra("birthday");
                mTvDateOfBirth.setText(birthday);
            } else if (resultCode == RESULT_OK &&
                    requestCode == REQUEST_CODE_POST_PHOTO) {
                List<String> photos = null;
                if (data != null) {
                    photos = Matisse.obtainPathResult(data);
                    String selectPhoto = photos.get(0);
                    mImageUserAvatar.loadCircle(selectPhoto, R.mipmap.icon_placeholder_image);
                    showProgressDialog(AccountAndPersonalDataActivity.this);
                    mDialog.setTitle("");
                    objectName = getFileName(selectPhoto);
                    mService.asyncPutFile(objectName, UploadFileTypeEnum.IMAGE.getValue(), selectPhoto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getFileName(String selectPhoto) {

        stringBuffer.setLength(0);
        stringBuffer.append("images/");
        String date = DateUtil.getTime(new Date(), DateUtil.sDateYMDFormat);
        stringBuffer.append(date + "/");
        String uuid = EncryptedSignatureUtil.getUUID();
        stringBuffer.append(uuid);
        File file = new File(selectPhoto);
        String fileName = file.getName();
        stringBuffer.append(fileName);

        return stringBuffer.toString();
    }

    //修改昵称
    public void updateNickName(String nickname) {

        if (validateInternet()) {

            UpdateUserInfoRequest updateUserInfoRequest = new UpdateUserInfoRequest();
            updateUserInfoRequest.setId(userId);
            updateUserInfoRequest.setNickname(nickname);
            showProgressDialog(AccountAndPersonalDataActivity.this);
            mDialog.setTitle("");
            mPresenter.updateInformation(updateUserInfoRequest);
        }
    }

    //修改性别
    public void updateSex(int sex) {

        if (validateInternet()) {

            UpdateUserInfoRequest updateUserInfoRequest = new UpdateUserInfoRequest();
            updateUserInfoRequest.setId(userId);
            updateUserInfoRequest.setSex(sex);
            showProgressDialog(AccountAndPersonalDataActivity.this);
            mDialog.setTitle("");
            mPresenter.updateInformation(updateUserInfoRequest);
        }
    }

    //修改头像
    public void updateAvatar(String avatar) {

        if (validateInternet()) {

            UpdateUserInfoRequest updateUserInfoRequest = new UpdateUserInfoRequest();
            updateUserInfoRequest.setId(userId);
            updateUserInfoRequest.setAvater(avatar);
          /*  showProgressDialog(AccountAndPersonalDataActivity.this);
            mDialog.setTitle("");*/
            mPresenter.updateInformation(updateUserInfoRequest);
        }
    }

    public OssService initOSS(String endpoint, String bucket, UIDisplayer displayer) {

//        移动端是不安全环境，不建议直接使用阿里云主账号ak，sk的方式。建议使用STS方式。具体参
//        https://help.aliyun.com/document_detail/31920.html
//        注意：SDK 提供的 PlainTextAKSKCredentialProvider 只建议在测试环境或者用户可以保证阿里云主账号AK，SK安全的前提下使用。具体使用如下
//        主账户使用方式
//        String AK = "******";
//        String SK = "******";
//        credentialProvider = new PlainTextAKSKCredentialProvider(AK,SK)
//        以下是使用STS Sever方式。
//        如果用STS鉴权模式，推荐使用OSSAuthCredentialProvider方式直接访问鉴权应用服务器，token过期后可以自动更新。
//        详见：https://help.aliyun.com/document_detail/31920.html
//        OSSClient的生命周期和应用程序的生命周期保持一致即可。在应用程序启动时创建一个ossClient，在应用程序结束时销毁即可。


        OSSCredentialProvider credentialProvider;
        //使用自己的获取STSToken的类

        credentialProvider = new OSSAuthCredentialsProvider(STS_SERVER_URL);

        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        OSS oss = new OSSClient(getApplicationContext(), endpoint, credentialProvider, conf);
        OSSLog.enableLog();
        return new OssService(oss, bucket, displayer, AccountAndPersonalDataActivity.this);

    }
}
