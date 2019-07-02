package com.nbhysj.coupon.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.RegisterContract;
import com.nbhysj.coupon.model.RegisterModel;
import com.nbhysj.coupon.model.request.LoginRequest;
import com.nbhysj.coupon.model.request.RegisterUserRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.LoginResponse;
import com.nbhysj.coupon.presenter.RegisterPresenter;
import com.nbhysj.coupon.util.EncryptedSignatureUtil;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.security.NoSuchAlgorithmException;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;

/**
 * @auther：hysj created on 2019/03/02
 * description：用户注册
 */
public class UserRegistrationActivity extends BaseActivity<RegisterPresenter, RegisterModel> implements RegisterContract.View {

    //用户协议
    @BindView(R.id.tv_user_agreement)
    TextView mTvUserAgreement;

    @BindView(R.id.iv_toolbar_right)
    ImageView mImgToolbarRight;
    //短信验证码
    @BindView(R.id.edt_verification_code)
    EditText mEdtVerifyCode;
    //用户名
    @BindView(R.id.edt_phone)
    EditText mEdtPhone;
    //密码
    @BindView(R.id.edt_password)
    EditText mEdtPassword;
    //登录
    @BindView(R.id.tv_login)
    TextView mTvLogin;
    //手机号码下划线
    @BindView(R.id.line_phone)
    View mLinePhone;
    //验证码下划线
    @BindView(R.id.line_verification_code)
    View mLineVerificationCode;
    //获取短信验证码
    @BindView(R.id.tv_get_verification_code)
    TextView mTvGetVerificationCode;
    //查看和隐藏密码
    @BindView(R.id.btn_toggle_pwd)
    ToggleButton mBtnTogglePwd;

    private Timer mTimer;
    Handler handler;
    private int delaytime = 60;
    private int VERIFY_CODE_MSG = 0;

    //加密后的密码
    private String encryptedPwd;
    //手机号
    private String phoneNum;

    //手机验证码
    private String verifyCode;

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_registration;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ToolbarHelper.setBar(UserRegistrationActivity.this, "", R.mipmap.nav_ico_back_black, R.mipmap.icon_cancel);
        mImgToolbarRight.setOnClickListener(v -> {

            UserRegistrationActivity.this.finish();
        });

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == VERIFY_CODE_MSG) {
                    mTvGetVerificationCode.setText("重新发送(" + delaytime + ")");
                    if (delaytime == 59) {
                        showToast(UserRegistrationActivity.this, getResources().getString(R.string.str_short_msg_send_success));
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
        mTvUserAgreement.setText(Html.fromHtml("点击注册即表示接受鱼人自游的" + "<font color='#00C7DA'>" + "《用户协议》" + "</font>"));
        mEdtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String username = charSequence.toString();

                if (TextUtils.isEmpty(username)) {
                    mTvLogin.setBackgroundResource(R.drawable.bg_rect_gray_shape);
                    mLinePhone.setBackgroundResource(R.color.line_grey);
                } else {

                    mTvLogin.setBackgroundResource(R.drawable.btn_oprate_bg);
                    mLinePhone.setBackgroundResource(R.drawable.btn_oprate_bg);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        /**
         * 查看密码
         */
        mBtnTogglePwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //如果选中，显示密码
                    mBtnTogglePwd.setBackgroundResource(R.mipmap.icon_see_password);
                    mEdtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //否则隐藏密码
                    mBtnTogglePwd.setBackgroundResource(R.mipmap.icon_invisible_password);
                    mEdtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
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
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }


    @OnClick({R.id.tv_get_verification_code, R.id.tv_login})
    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.tv_get_verification_code:
                if (validateInternet()) {
                    String phoneNum = mEdtPhone.getText().toString().trim();
                    if (TextUtils.isEmpty(phoneNum)) {
                        showToast(UserRegistrationActivity.this, getResources().getString(R.string.str_input_phone));
                        return;
                    }
                    mPresenter.getRegisterVerifyCode(phoneNum);
                }
                break;
            case R.id.tv_login:

                getSalt();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer.cancel();
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
                showToast(UserRegistrationActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void registerUserResult(BackResult res) {
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    login();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(UserRegistrationActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getSaltResult(BackResult<String> res) {
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    String saltKey = res.getData();
                    String password = mEdtPassword.getText().toString().trim();
                    verifyCode = mEdtVerifyCode.getText().toString().trim();
                    encryptedPwd = EncryptedSignatureUtil.getHmacMd5Bytes(saltKey.getBytes(), password.getBytes());
                    //encryptedPwd = hmacMd5Pwd.toUpperCase();
                    RegisterUserRequest registerUserRequest = new RegisterUserRequest();
                    registerUserRequest.setMobile(phoneNum);
                    registerUserRequest.setPassword(encryptedPwd);
                    registerUserRequest.setAuth(verifyCode);
                    registerUserRequest.setSalt(saltKey);
                    mPresenter.registerUser(registerUserRequest);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }

                break;
            default:
                showToast(UserRegistrationActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void loginResult(BackResult<LoginResponse> res) {

        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    LoginResponse loginResponse = res.getData();
                    showToast(UserRegistrationActivity.this, "手机号:" + loginResponse.getMobile());

                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            default:
                showToast(UserRegistrationActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {

        showToast(UserRegistrationActivity.this, Constants.getResultMsg(msg));
    }

    //获取盐
    public void getSalt() {

        if (validateInternet()) {
            phoneNum = mEdtPhone.getText().toString().trim();
            mPresenter.getSalt(phoneNum);
        }
    }

    //登录
    public void login() {

        if (validateInternet()) {
            phoneNum = mEdtPhone.getText().toString().trim();
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setUsername(phoneNum);
            loginRequest.setPassword(encryptedPwd);
            mPresenter.login(loginRequest);
        }
    }
}
