package com.nbhysj.coupon.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.common.Enum.ThirdPartyLoginTypeEnum;
import com.nbhysj.coupon.contract.LoginContract;
import com.nbhysj.coupon.dialog.OprateDialog;
import com.nbhysj.coupon.model.LoginModel;
import com.nbhysj.coupon.model.request.LoginRequest;
import com.nbhysj.coupon.model.request.RegisterUserRequest;
import com.nbhysj.coupon.model.request.ThirdPartyLoginRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.LoginResponse;
import com.nbhysj.coupon.model.response.SerializableThirdPartyMap;
import com.nbhysj.coupon.model.response.ThirdPartyLoginResponse;
import com.nbhysj.coupon.model.response.ThirdPartyLoginStatusResponse;
import com.nbhysj.coupon.model.response.UserInfoResponse;
import com.nbhysj.coupon.presenter.LoginPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.EncryptedSignatureUtil;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.nbhysj.coupon.view.GlideImageView;
import com.nbhysj.coupon.view.RoundedImageView;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.http.FieldMap;

/**
 * @auther：hysj created on 2019/01/23
 * description：登录页面
 */
public class LoginActivity extends BaseActivity<LoginPresenter, LoginModel> implements LoginContract.View {

    //用户头像
    @BindView(R.id.image_user_avatar)
    CircleImageView mImageUserAvatar;
    //用户名
    @BindView(R.id.et_username)
    EditText mEdtUsername;
    //登录密码
    @BindView(R.id.et_password)
    EditText mEdtPassword;
    //查看和隐藏密码
    @BindView(R.id.img_password_is_invisible)
    ImageView mImgPwdIsInvisible;
    @BindView(R.id.tv_login)
    TextView mTvLogin;
    //用户名
    private String username;
    //未加密的密码
    private String password;
    //加密后的密码
    private String encryptedPwd;
    //盐
    private String saltKey;
    //用户d
    private int userId;
    //等三方登录类型入口(1.QQ 2.wechat 3.微博)
    private String mThirdPartyLoginType;

    //第三方信息Map
    Map<String, String> thirdPartyData;

    //第三方绑定请求code
    private int REQUEST_CODE_THIRD_PARTY_LOGIN = 0;

    //用户注册成功
    private int REQUEST_CODE_USER_REGISTER_SUCCESS = 1;

    private boolean isSeePasswordOprate = true;
    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_login;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setLoginBar(LoginActivity.this, getResources().getString(R.string.str_login), R.mipmap.icon_left_arrow_black, "注册");
    }

    @Override
    public void initData() {
        String avatar = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.USER_AVATAR, "");
        String username = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.USERNAME, "");
        if (!TextUtils.isEmpty(avatar))
        {
            GlideUtil.loadImage(LoginActivity.this,avatar,mImageUserAvatar);
        }

        if (!TextUtils.isEmpty(username))
        {
           mEdtUsername.setText(username);
        }

        mImgPwdIsInvisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isSeePasswordOprate)
                {
                    mImgPwdIsInvisible.setImageResource(R.mipmap.icon_see_password);
                    isSeePasswordOprate = false;

                    mEdtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                } else {

                    mImgPwdIsInvisible.setImageResource(R.mipmap.icon_invisible_password);
                    isSeePasswordOprate = true;
                    mEdtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });


        mEdtUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkIsInputFill();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mEdtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkIsInputFill();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }


    @Override
    public void getLoginVerifyCodeResult(BackResult res) {

    }

    @Override
    public void getLoginSaltResult(BackResult res) {
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                saltKey = (String) res.getData();
                login();
                break;
            default:
                dismissProgressDialog();
                showToast(LoginActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }
    //校验输入信息是否填写完成
    public void checkIsInputFill() {

        username = mEdtUsername.toString().trim();
        password = mEdtPassword.getText().toString().trim();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password))
        {
            mTvLogin.setBackgroundResource(R.drawable.bg_rect_gray_shape);
            mTvLogin.setEnabled(false);
            mTvLogin.setClickable(false);
        } else if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password))
        {
            mTvLogin.setBackgroundResource(R.drawable.btn_oprate_bg);
            mTvLogin.setEnabled(true);
            mTvLogin.setClickable(true);
        }
    }
    @Override
    public void loginResult(BackResult<LoginResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                LoginResponse loginResponse = res.getData();
                userId = loginResponse.getId();                 //用户id
                String mobile = loginResponse.getMobile();      //手机号(用户名)
                String nickname = loginResponse.getNickname();  //昵称
                String token = res.getToken();
                SharedPreferencesUtils.saveLoginData(userId, mobile, nickname, token);
                getUserInfo();

                break;
            default:
                showToast(LoginActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getUserInfoResult(BackResult<UserInfoResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    UserInfoResponse userInfoResponse = res.getData();
                    String avatar = userInfoResponse.getAvater();      //用户头像
                    String profile = userInfoResponse.getProfile();   //简介
                    int sex = userInfoResponse.getSex();              //性别
                    String birthday = userInfoResponse.getBirthday(); //生日
                    String fansNum = String.valueOf(userInfoResponse.getFansNum()); //粉丝数
                    String followNum = String.valueOf(userInfoResponse.getFollowNum()); //关注数
                    String collectionNum = String.valueOf(userInfoResponse.getCollectionNum()); //收藏数
                    String zanNum = String.valueOf(userInfoResponse.getZanNum()); //点赞数
                    SharedPreferencesUtils.saveUserInfoData(avatar, sex, birthday, profile, fansNum, followNum, collectionNum, zanNum);

                    String currentVersionName = getCurrentVersionName();
                    SharedPreferencesUtils.putData("version",currentVersionName);
                    setResult(RESULT_OK);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(LoginActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getThirdPartyLoginStatusResult(BackResult<ThirdPartyLoginStatusResponse> res) {

    }

    @Override
    public void thirdPartyLoginResult(BackResult<LoginResponse> res) {

        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    LoginResponse thirdPartyLoginResponse = res.getData();

                    userId = thirdPartyLoginResponse.getId();                 //用户id
                    String mobile = thirdPartyLoginResponse.getMobile();      //手机号（用户账号）
                    String nickname = thirdPartyLoginResponse.getNickname();  //昵称
                    String token = res.getToken();
                    SharedPreferencesUtils.saveLoginData(userId, mobile, nickname, token);

                    getUserInfo();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Constants.THIRD_PARTY_LOGIN_FOR_UNBOUND_CODE://未绑定手机号
                dismissProgressDialog();
                SerializableThirdPartyMap thirdPartyMap = new SerializableThirdPartyMap();
                thirdPartyMap.setMap(thirdPartyData);
                Bundle bundle = new Bundle();
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, BindPhoneActivity.class);
                bundle.putSerializable("thirdPartyMap", thirdPartyMap);
                bundle.putString("thirdPartyLoginType", mThirdPartyLoginType);
                intent.putExtras(bundle);
                startActivityForResult(intent, REQUEST_CODE_THIRD_PARTY_LOGIN);
                break;
            default:
                dismissProgressDialog();
                showToast(LoginActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {
        dismissProgressDialog();
        showToast(LoginActivity.this, Constants.getResultMsg(msg));
    }

    @OnClick({R.id.tv_find_pwd, R.id.tv_login, R.id.rlyt_weixin, R.id.rlyt_qq, R.id.rlyt_sina_weibo,R.id.tv_toolbar_right})
    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.tv_find_pwd:  //忘记密码
                toActivity(FindPasswordActivity.class);
                break;
            case R.id.tv_login:
                getSalt();
                break;
            case R.id.rlyt_qq:
                //QQ登录
                mThirdPartyLoginType = ThirdPartyLoginTypeEnum.QQ.getValue();
                oprateTips(getResources().getString(R.string.str_qq), SHARE_MEDIA.QQ);

                break;
            case R.id.rlyt_weixin:
                //微信
                mThirdPartyLoginType = ThirdPartyLoginTypeEnum.WECHAT.getValue();
                oprateTips(getResources().getString(R.string.str_wechat), SHARE_MEDIA.WEIXIN);

                break;
            case R.id.rlyt_sina_weibo:
                //新浪微博
                mThirdPartyLoginType = ThirdPartyLoginTypeEnum.WEIBO.getValue();
                oprateTips(getResources().getString(R.string.str_weibo), SHARE_MEDIA.SINA);

                break;
              case R.id.tv_toolbar_right:

                  Intent intent = new Intent();
                  intent.setClass(LoginActivity.this, UserRegistrationActivity.class);
                  startActivityForResult(intent, REQUEST_CODE_USER_REGISTER_SUCCESS);

            break;
            default:
                break;
        }
    }

    //获取盐
    public void getSalt() {

        if (validateInternet()) {
            username = mEdtUsername.getText().toString().trim();
            password = mEdtPassword.getText().toString().trim();

            if (TextUtils.isEmpty(username)) {
                showToast(LoginActivity.this, getResources().getString(R.string.str_input_username));
                return;
            }

            if (TextUtils.isEmpty(password)) {
                showToast(LoginActivity.this, getResources().getString(R.string.str_password));
                return;
            }

            showProgressDialog(LoginActivity.this);
            mDialog.setTitle(getResources().getString(R.string.str_logining));
            mPresenter.getLoginSalt(username);
        }
    }

    public void login() {

        if (validateInternet()) {

            try {

                if (TextUtils.isEmpty(saltKey)) {

                    showToast(LoginActivity.this, getResources().getString(R.string.str_salt_error));
                    return;
                }

                encryptedPwd = EncryptedSignatureUtil.getHmacMd5Bytes(saltKey.getBytes(), password.getBytes());
                //encryptedPwd = hmacMd5Pwd.toUpperCase();
                LoginRequest loginRequest = new LoginRequest();
                loginRequest.setUsername(username);
                loginRequest.setPassword(encryptedPwd);
                loginRequest.setSalt(saltKey);

                mPresenter.login(loginRequest);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_THIRD_PARTY_LOGIN && resultCode == RESULT_OK) {

            finish();
        } else if(requestCode == REQUEST_CODE_USER_REGISTER_SUCCESS && resultCode == RESULT_OK){

            setResult(RESULT_OK);
            finish();
        }
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    public void oprateTips(String platform, SHARE_MEDIA share_media) {

        OprateDialog oprateDialog = new OprateDialog(LoginActivity.this).builder().setTitle("鱼人自游想要打开" + platform);
        oprateDialog.setNegativeButton(getResources().getString(R.string.str_cancel), getResources().getColor(R.color.color_text_black7), new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        oprateDialog.setPositiveButton("打开", getResources().getColor(R.color.color_blue2), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UMShareAPI.get(LoginActivity.this).doOauthVerify(LoginActivity.this, share_media, authListener);
            }
        });

        oprateDialog.show();
    }

    //获取用户信息
    public void getUserInfo() {

        if (validateInternet()) {

            mPresenter.getUserInfo(userId);
        }
    }

    /**
     * 第三方登录
     *
     * @param openid
     * @param unionid
     */
    public void thirdPartyLogin(String openid, String unionid) {

        if (validateInternet()) {
            ThirdPartyLoginRequest thirdPartyLoginRequest = new ThirdPartyLoginRequest();
            thirdPartyLoginRequest.setLoginType(mThirdPartyLoginType);
            if (!TextUtils.isEmpty(openid)) {
                thirdPartyLoginRequest.setOpenid(openid);
            }
            if (!TextUtils.isEmpty(unionid)) {
                thirdPartyLoginRequest.setUnionid(unionid);
            }
            showProgressDialog(LoginActivity.this);
            mDialog.setTitle("");
            mPresenter.thirdPartyLogin(thirdPartyLoginRequest);
        }
    }

    UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //SocializeUtils.safeShowDialog(dialog);
            //  Toast.makeText(OneClickLoginActivity.this, "onStart", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            thirdPartyData = data;
            String openid = data.get("openid");
            String unionid = data.get("unionid");
            thirdPartyLogin(openid, unionid);

        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            // SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(mContext, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            // SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(mContext, "取消了", Toast.LENGTH_LONG).show();
        }
    };
}
