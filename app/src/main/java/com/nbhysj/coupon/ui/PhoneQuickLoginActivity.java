package com.nbhysj.coupon.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.common.Enum.ThirdPartyLoginTypeEnum;
import com.nbhysj.coupon.contract.LoginContract;
import com.nbhysj.coupon.dialog.OprateDialog;
import com.nbhysj.coupon.model.LoginModel;
import com.nbhysj.coupon.model.request.LoginRequest;
import com.nbhysj.coupon.model.request.ThirdPartyLoginRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.LoginResponse;
import com.nbhysj.coupon.model.response.SerializableThirdPartyMap;
import com.nbhysj.coupon.model.response.ThirdPartyLoginStatusResponse;
import com.nbhysj.coupon.model.response.UserInfoResponse;
import com.nbhysj.coupon.presenter.LoginPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @auther：hysj created on 2019/03/02
 * description：手机快捷登录
 */
public class PhoneQuickLoginActivity extends BaseActivity<LoginPresenter, LoginModel> implements LoginContract.View {

    //用户头像
    @BindView(R.id.image_user_avatar)
    CircleImageView mImageUserAvatar;
    //注册
    @BindView(R.id.tv_toolbar_right)
    TextView mTvUserRegister;
    //短信验证码
    @BindView(R.id.edt_verification_code)
    EditText mEdtVerifyCode;
    //获取短信验证码
    @BindView(R.id.tv_get_verification_code)
    TextView mTvGetVerificationCode;
    //用户名
    @BindView(R.id.edt_phone)
    EditText mEdtPhone;
    //国际电话区号
    @BindView(R.id.tv_country_code)
    TextView mTvCountryCode;
    @BindView(R.id.tv_login)
    TextView mTvLogin;
    //手机号码
    private String phoneNum;
    //手机验证码
    private String verifyCode;
    private Timer mTimer;
    Handler handler;
    private int delaytime = 60;
    private int VERIFY_CODE_MSG = 0;
    //等三方登录类型入口(1.QQ 2.wechat 3.微博)
    private String mThirdPartyLoginType;
    //第三方信息Map
    Map<String, String> thirdPartyData;

    //第三方绑定请求code
    private int THIRD_PARTY_LOGIN_REQUEST_CODE = 0;

    //登录成功code
    private int LOGIN_SUCCESS_REQUEST_CODE = 1;

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_phone_quick_login;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setLoginBar(PhoneQuickLoginActivity.this, getResources().getString(R.string.str_login), R.mipmap.nav_ico_back_black, "注册");

        mTvUserRegister.setOnClickListener(v -> {

            toActivity(UserRegistrationActivity.class);
        });

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == VERIFY_CODE_MSG) {
                    mTvGetVerificationCode.setText("重新发送(" + delaytime + ")");
                    if (delaytime == 59) {
                        showToast(PhoneQuickLoginActivity.this, getResources().getString(R.string.str_short_msg_send_success));
                    }
                    if (delaytime == 0 || delaytime < 0) {
                        mTimer.cancel();
                        mTvGetVerificationCode.setEnabled(true);
                        mTvGetVerificationCode.setText(getResources().getString(R.string.str_get_verification_code));
                    }

//                }else if(msg.what == 101){
//                    changeVerifyImage();
                }
            }
        };
    }

    @Override
    public void initData() {

        String avatar = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.USER_AVATAR, "");
        if (!TextUtils.isEmpty(avatar))
        {
            GlideUtil.loadImage(PhoneQuickLoginActivity.this,avatar,mImageUserAvatar);
        }

        mEdtPhone.addTextChangedListener(new TextWatcher() {
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

        mEdtVerifyCode.addTextChangedListener(new TextWatcher() {
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
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                delaytime = 60;
                mTvGetVerificationCode.setEnabled(false);
                showTimer();
                break;
            default:
                showToast(PhoneQuickLoginActivity.this, Constants.getResultMsg(res.getMsg()));
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
                    String mobile = thirdPartyLoginResponse.getMobile();      //手机号
                    String nickname = thirdPartyLoginResponse.getNickname();  //昵称
                    String username = thirdPartyLoginResponse.getUsername();  //用户名
                    String token = res.getToken();
                    SharedPreferencesUtils.saveLoginData(userId, mobile, nickname, username, token);

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
                intent.setClass(PhoneQuickLoginActivity.this, BindPhoneActivity.class);
                bundle.putSerializable("thirdPartyMap", thirdPartyMap);
                bundle.putString("thirdPartyLoginType", mThirdPartyLoginType);
                intent.putExtras(bundle);
                startActivityForResult(intent, THIRD_PARTY_LOGIN_REQUEST_CODE);
                break;
            default:
                dismissProgressDialog();
                showToast(PhoneQuickLoginActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getLoginSaltResult(BackResult res) {

        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:

                String saltKey = (String) res.getData();
                login(saltKey);

                break;
            default:
                dismissProgressDialog();
                showToast(PhoneQuickLoginActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    //登录返回
    @Override
    public void loginResult(BackResult<LoginResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                LoginResponse loginResponse = res.getData();
                userId = loginResponse.getId();                 //用户id
                String mobile = loginResponse.getMobile();      //手机号
                String nickname = loginResponse.getNickname();  //昵称
                String username = loginResponse.getUsername();  //用户名
                String token = res.getToken();
                SharedPreferencesUtils.saveLoginData(userId, mobile, nickname, username, token);
                getUserInfo();

                break;
            default:
                showToast(PhoneQuickLoginActivity.this, Constants.getResultMsg(res.getMsg()));
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
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(PhoneQuickLoginActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {
        dismissProgressDialog();
        showToast(PhoneQuickLoginActivity.this, Constants.getResultMsg(msg));
    }

    private void showTimer() {
        mTimer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                delaytime--;
                Message msg = new Message();
                msg.what = VERIFY_CODE_MSG;
                handler.sendMessage(msg);
            }
        };
        mTimer.schedule(timerTask, 1, 1000);
    }

    //获取盐
    public void getSalt() {

        if (validateInternet()) {
            phoneNum = mEdtPhone.getText().toString().trim();
            verifyCode = mEdtVerifyCode.getText().toString().trim();
            if (TextUtils.isEmpty(phoneNum)) {
                showToast(PhoneQuickLoginActivity.this, getResources().getString(R.string.str_input_phone_number));
                return;
            }

            if (TextUtils.isEmpty(verifyCode)) {
                showToast(PhoneQuickLoginActivity.this, getResources().getString(R.string.str_verification_code_is_error));
                return;
            }
            showProgressDialog(PhoneQuickLoginActivity.this);
            mDialog.setTitle("正在登录...");
            mPresenter.getLoginSalt(phoneNum);
        }
    }

    //登录
    public void login(String saltKey) {

        if (validateInternet()) {

            try {
                if (TextUtils.isEmpty(saltKey)) {
                    showToast(PhoneQuickLoginActivity.this, getResources().getString(R.string.str_salt_error));
                    return;
                }

                verifyCode = mEdtVerifyCode.getText().toString().trim();
                LoginRequest loginRequest = new LoginRequest();
                loginRequest.setUsername(phoneNum);
                loginRequest.setAuth(verifyCode);
                loginRequest.setSalt(saltKey);

                mPresenter.login(loginRequest);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer.cancel();
        }
    }

    //校验输入信息是否填写完成
    public void checkIsInputFill() {

        String phone = mEdtPhone.toString().trim();
        verifyCode = mEdtVerifyCode.getText().toString().trim();
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(verifyCode)) {

            mTvLogin.setBackgroundResource(R.drawable.bg_rect_gray_shape);
            mTvLogin.setEnabled(false);
            mTvLogin.setClickable(false);
        } else if(!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(verifyCode)){
            mTvLogin.setBackgroundResource(R.drawable.btn_oprate_bg);
            mTvLogin.setEnabled(true);
            mTvLogin.setClickable(true);
        }
    }


    //获取用户信息
    public void getUserInfo() {

        if (validateInternet()) {

            mPresenter.getUserInfo(userId);
        }
    }

    @OnClick({R.id.tv_pwd_login, R.id.tv_login, R.id.tv_get_verification_code, R.id.tv_country_code, R.id.rlyt_weixin, R.id.rlyt_qq, R.id.rlyt_sina_weibo})
    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.tv_pwd_login:          //密码登录
                Intent intent = new Intent();
                intent.setClass(PhoneQuickLoginActivity.this,LoginActivity.class);
                startActivityForResult(intent,LOGIN_SUCCESS_REQUEST_CODE);
                break;
            case R.id.tv_get_verification_code:

                if (validateInternet()) {

                    String phoneNum = mEdtPhone.getText().toString().trim();
                    if (TextUtils.isEmpty(phoneNum)) {
                        showToast(PhoneQuickLoginActivity.this, getResources().getString(R.string.str_input_phone_number));
                        return;
                    }
                    showProgressDialog(PhoneQuickLoginActivity.this);
                    mDialog.setTitle(getResources().getString(R.string.str_getting_verification_code));
                    mPresenter.getLoginVerifyCode(phoneNum);
                }
                break;
            case R.id.tv_country_code:

                //   toActivity(CountryCodeSelectActivity.class);

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
            default:
                break;
        }
    }

    public void oprateTips(String platform, SHARE_MEDIA share_media) {

        OprateDialog oprateDialog = new OprateDialog(PhoneQuickLoginActivity.this).builder().setTitle("鱼人自游想要打开" + platform);
        oprateDialog.setNegativeButton(getResources().getString(R.string.str_cancel), getResources().getColor(R.color.color_text_black7), new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        oprateDialog.setPositiveButton("打开", getResources().getColor(R.color.color_blue2), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UMShareAPI.get(PhoneQuickLoginActivity.this).doOauthVerify(PhoneQuickLoginActivity.this, share_media, authListener);
            }
        });

        oprateDialog.show();
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
            showProgressDialog(PhoneQuickLoginActivity.this);
            mDialog.setTitle("");
            mPresenter.thirdPartyLogin(thirdPartyLoginRequest);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        String avatar = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.USER_AVATAR, "");
        if (!TextUtils.isEmpty(avatar))
        {
            GlideUtil.loadImage(PhoneQuickLoginActivity.this,avatar,mImageUserAvatar);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == LOGIN_SUCCESS_REQUEST_CODE && resultCode == RESULT_OK){

            PhoneQuickLoginActivity.this.finish();
        }
    }
}
