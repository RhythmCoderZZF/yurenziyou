package com.nbhysj.coupon.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.common.Enum.ThirdPartyLoginTypeEnum;
import com.nbhysj.coupon.contract.AccountManagementContract;
import com.nbhysj.coupon.model.AccountManagementModel;
import com.nbhysj.coupon.model.request.ThirdPartyLoginCreateUserBind;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.LoginResponse;
import com.nbhysj.coupon.model.response.SerializableThirdPartyMap;
import com.nbhysj.coupon.model.response.ThirdPartyLoginResponse;
import com.nbhysj.coupon.model.response.ThirdPartyLoginStatusResponse;
import com.nbhysj.coupon.model.response.UserInfoResponse;
import com.nbhysj.coupon.presenter.AccountmanagementPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/03/22
 * description：绑定手机号
 */
public class BindPhoneActivity extends BaseActivity<AccountmanagementPresenter, AccountManagementModel> implements AccountManagementContract.View {

    private Timer mTimer;
    Handler handler;
    private int delaytime = 60;
    private int VERIFY_CODE_MSG = 0;
    //手机验证码
    private String verifyCode;
    //短信验证码
    @BindView(R.id.edt_verification_code)
    EditText mEdtVerifyCode;
    //获取短信验证码
    @BindView(R.id.tv_get_verification_code)
    TextView mTvGetVerificationCode;
    //手机号
    @BindView(R.id.edt_phone)
    EditText mEdtPhone;
    //第三方登录
    @BindView(R.id.tv_third_party_login)
    TextView mTvThirdPartyLogin;

    @BindView(R.id.llyt_bind_phone)
    LinearLayout mLlytBindPhone;

    SerializableThirdPartyMap serializableThirdPartyMap;
    private String thirdPartyLoginType;
    //1等于绑定 0等于注册
    private int bind;

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_bind_phone;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ToolbarHelper.setLoginBar(BindPhoneActivity.this, "", R.mipmap.nav_ico_back_black, "");
        Bundle bundle = getIntent().getExtras();
        serializableThirdPartyMap = (SerializableThirdPartyMap) bundle.get("thirdPartyMap");
        thirdPartyLoginType = bundle.getString("thirdPartyLoginType");
        bind = bundle.getInt("bind",0);
    }

    @Override
    public void initData() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == VERIFY_CODE_MSG) {
                    mTvGetVerificationCode.setText("重新发送(" + delaytime + ")");
                    if (delaytime == 59) {
                        showToast(BindPhoneActivity.this, getResources().getString(R.string.str_short_msg_send_success));
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
    public void getThirdPartyLoginStatusResult(BackResult<ThirdPartyLoginStatusResponse> res) {

    }

    @Override
    public void getThirdPartyLoginCreateUserResult(BackResult<LoginResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:

                try {
                    if(bind == 0){
                        LoginResponse thirdPartyLoginResponse = res.getData();

                        userId = thirdPartyLoginResponse.getId();                 //用户id
                        String mobile = thirdPartyLoginResponse.getMobile();      //手机号(用户账号)
                        String nickname = thirdPartyLoginResponse.getNickname();  //昵称
                        String token = res.getToken();
                        SharedPreferencesUtils.saveLoginData(userId, mobile, nickname, token);

                        getUserInfo();
                    } else if(bind == 1){

                        setResult(RESULT_OK);
                        finish();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            default:
                showToast(BindPhoneActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getRegisterVerifyCodeResult(BackResult res) {
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                delaytime = 60;
                mTvGetVerificationCode.setEnabled(false);
                showTimer();
                break;
            default:
                showToast(BindPhoneActivity.this, Constants.getResultMsg(res.getMsg()));
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
                    setResult(RESULT_OK);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(BindPhoneActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void thirdPartyLoginResult(BackResult<LoginResponse> res) {

    }

    @Override
    public void showMsg(String msg) {
        dismissProgressDialog();
        showToast(BindPhoneActivity.this, Constants.getResultMsg(msg));
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
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

            mTvThirdPartyLogin.setBackgroundResource(R.drawable.bg_rect_gray_shape);
        } else {
            mTvThirdPartyLogin.setBackgroundResource(R.drawable.btn_oprate_bg);
        }
    }

    //获取用户信息
    public void getUserInfo() {

        if (validateInternet()) {

            mPresenter.getUserInfo(userId);
        }
    }

    //获取验证码
    public void getVerificationCode() {

        if (validateInternet()) {
            String phoneNum = mEdtPhone.getText().toString().trim();
            if (TextUtils.isEmpty(phoneNum)) {
                showToast(BindPhoneActivity.this, getResources().getString(R.string.str_input_phone));
                return;
            }
            mPresenter.getRegisterVerifyCode(phoneNum);
        }
    }

    /**
     * 第三方绑定用户
     */
    public void thirdPartyLoginCreateUser() {

        if (validateInternet()) {
            String verifyCode = mEdtVerifyCode.getText().toString().trim();
            String phone = mEdtPhone.getText().toString().trim();
            if (TextUtils.isEmpty(verifyCode)) {
                showToast(BindPhoneActivity.this, getResources().getString(R.string.str_verification_code_is_error));
                return;
            }

            if (TextUtils.isEmpty(phone)) {
                showToast(BindPhoneActivity.this, getResources().getString(R.string.str_input_phone_number));
                return;
            }

            showProgressDialog(BindPhoneActivity.this);
            Map<String, String> thirdPartyMap = serializableThirdPartyMap.getMap();
            String unionid = thirdPartyMap.get("unionid");
            String openid = thirdPartyMap.get("openid");
            String access_token = thirdPartyMap.get("access_token");
            String uid = thirdPartyMap.get("uid");

            ThirdPartyLoginCreateUserBind thirdPartyLoginCreateUserBind = new ThirdPartyLoginCreateUserBind();
            //1.验证码
            thirdPartyLoginCreateUserBind.setAuth(verifyCode);

            //2.第三方登录token
            if (!TextUtils.isEmpty(access_token)) {
                thirdPartyLoginCreateUserBind.setAccessToken(access_token);
            }
            //3.第三方登录类型
            if (!TextUtils.isEmpty(thirdPartyLoginType)) {
                thirdPartyLoginCreateUserBind.setLoginType(thirdPartyLoginType);
            }
            //4.手机号码
            thirdPartyLoginCreateUserBind.setMobile(phone);

            //5.用户标识（当使用微信或qq注册openid为必选项）
            if (!TextUtils.isEmpty(openid)) {
                thirdPartyLoginCreateUserBind.setOpenid(openid);
            }

            //6.用户信息uid(当使用微博注册uid为必选项)
            if (!TextUtils.isEmpty(uid)) {
                thirdPartyLoginCreateUserBind.setUid(uid);
            }
            //7.用户统一标识（当使用微博注册时uniodID为必选项）,当注册微信时微信用户信息存在unionid时(建议选择)
            if (!TextUtils.isEmpty(unionid)) {
                thirdPartyLoginCreateUserBind.setUnionid(unionid);
            }

            //1:等于绑定 0:等于注册
            thirdPartyLoginCreateUserBind.setBind(bind);

            mPresenter.getThirdPartyLoginCreateUser(thirdPartyLoginCreateUserBind);

        }
    }


    @OnClick({R.id.tv_get_verification_code, R.id.tv_third_party_login, R.id.iv_toolbar_right})
    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.tv_get_verification_code:
                getVerificationCode();
                break;
            case R.id.tv_third_party_login:

                thirdPartyLoginCreateUser();

                break;
            case R.id.iv_toolbar_right:
                finish();
                break;
            case R.id.llyt_bind_phone:
                hideKeyboard(mLlytBindPhone);
                break;

            default:
                break;
        }
    }
}
