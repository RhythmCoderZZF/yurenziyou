package com.nbhysj.coupon.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.RegisterContract;
import com.nbhysj.coupon.framework.Net;
import com.nbhysj.coupon.model.RegisterModel;
import com.nbhysj.coupon.model.request.LoginRequest;
import com.nbhysj.coupon.model.request.RegisterUserRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.LoginResponse;
import com.nbhysj.coupon.model.response.UserInfoResponse;
import com.nbhysj.coupon.presenter.RegisterPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.EncryptedSignatureUtil;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
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
    @BindView(R.id.img_password_is_invisible)
    ImageView mImgPwdIsInvisible;

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

    private boolean isSeePasswordOprate = true;

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_user_registration;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ToolbarHelper.setHeaderBar(UserRegistrationActivity.this, "", R.mipmap.nav_ico_back_black, R.mipmap.icon_cancel);
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
    private class PrivacyPolicyClick extends ClickableSpan{
        @Override
        public void onClick(View widget) {
            //            跳转隐私政策网址
            Intent intent = new Intent();
            intent.putExtra("url", Net.PRAVACY_POLICY_URL);
            intent.putExtra("title", Constants.PRAVACY_POLICY_H5_TITEL);
            intent.setClass(UserRegistrationActivity.this, WebActivity.class);
            startActivity(intent);

        }

        @Override
        public void updateDrawState(TextPaint ds) {
//            ds.setColor(ds.linkColor);
//            ds.setUnderlineText(true);
        }
    }

    private class userAgreementClick extends ClickableSpan{
        @Override
        public void onClick(View widget) {
            // 跳转用户协议网址
            Intent intent = new Intent();
            intent.putExtra("url", Net.USER_AGREEMENT_URL);
            intent.putExtra("title", Constants.USER_AGREEMENT_H5_TITEL);
            intent.setClass(UserRegistrationActivity.this, WebActivity.class);
            startActivity(intent);

        }

        @Override
        public void updateDrawState(TextPaint ds) {
//            ds.setColor(ds.linkColor);
//            ds.setUnderlineText(true);
        }
    }
    @Override
    public void initData() {
       // mTvUserAgreement.setText(Html.fromHtml("点击注册即表示接受鱼人自游的" + "<font color='#00C7DA'>" + "《用户协议》" + "</font>"));

        //        我同意本DEMO的隐私政策和服务协议
      /*  SpannableStringBuilder builder=new SpannableStringBuilder(getString(R.string.string_privacy));
        ClickableSpan clickSpanPrivacy=new ClickableSpan() {
            @Override
            public void onClick( View widget) {
//                跳转隐私政策网址
                Toast.makeText(UserRegistrationActivity.this, "隐私政策", Toast.LENGTH_SHORT).show();
            }
        };
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#0000FF"));
        builder.setSpan(foregroundColorSpan,15,18, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(clickSpanPrivacy,15,18, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
       // builder.setSpan(new ForegroundColorSpan(Color.parseColor("#009ad6")),13,18, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ClickableSpan clickableSpanProtocol=new ClickableSpan() {
            @Override
            public void onClick( View widget) {
//                跳转服务协议
                Toast.makeText(UserRegistrationActivity.this, "用户协议", Toast.LENGTH_SHORT).show();
            }
        };
        builder.setSpan(clickableSpanProtocol,21,24,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvUserAgreement.setText(builder);
        mTvUserAgreement.setMovementMethod(LinkMovementMethod.getInstance());//调用此方法时文字点击事件才有效*/

        SpannableStringBuilder spannable = new SpannableStringBuilder(getString(R.string.string_privacy));
        //设置文字的前景色
        spannable.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_blu4)),14,20,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //这个一定要记得设置，不然点击不生效
        mTvUserAgreement.setMovementMethod(LinkMovementMethod.getInstance());
        spannable.setSpan(new PrivacyPolicyClick(),14,20 , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置文字的前景色
        spannable.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_blu4)),21,27,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //这个一定要记得设置，不然点击不生效
        mTvUserAgreement.setMovementMethod(LinkMovementMethod.getInstance());
        spannable.setSpan(new userAgreementClick(),23,26 , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvUserAgreement.setText(spannable);


        mEdtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                String verifyCode = charSequence.toString();
                String phone = mEdtPhone.getText().toString();
                String password = mEdtPassword.getText().toString();
                if (TextUtils.isEmpty(verifyCode) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
                    mTvLogin.setBackgroundResource(R.drawable.bg_rect_gray_shape);
                    mLinePhone.setBackgroundResource(R.color.line_grey);
                    mTvLogin.setEnabled(false);
                    mTvLogin.setClickable(false);
                } else if (!TextUtils.isEmpty(verifyCode) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(password)) {

                    mTvLogin.setBackgroundResource(R.drawable.btn_oprate_bg);
                    mLinePhone.setBackgroundResource(R.drawable.btn_oprate_bg);
                    mTvLogin.setEnabled(true);
                    mTvLogin.setClickable(true);
                }
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

                String verifyCode = charSequence.toString();
                String phone = mEdtPhone.getText().toString();
                String password = mEdtPassword.getText().toString();
                if (TextUtils.isEmpty(verifyCode) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
                    mTvLogin.setBackgroundResource(R.drawable.bg_rect_gray_shape);
                    mLinePhone.setBackgroundResource(R.color.line_grey);
                    mTvLogin.setEnabled(false);
                    mTvLogin.setClickable(false);
                } else if (!TextUtils.isEmpty(verifyCode) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(password)) {

                    mTvLogin.setBackgroundResource(R.drawable.btn_oprate_bg);
                    mLinePhone.setBackgroundResource(R.drawable.btn_oprate_bg);
                    mTvLogin.setEnabled(true);
                    mTvLogin.setClickable(true);
                }
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

                String verifyCode = charSequence.toString();
                String phone = mEdtPhone.getText().toString();
                String password = mEdtPassword.getText().toString();
                if (TextUtils.isEmpty(verifyCode) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
                    mTvLogin.setBackgroundResource(R.drawable.bg_rect_gray_shape);
                    mLinePhone.setBackgroundResource(R.color.line_grey);
                    mTvLogin.setEnabled(false);
                } else if (!TextUtils.isEmpty(verifyCode) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(password)) {

                    mTvLogin.setBackgroundResource(R.drawable.btn_oprate_bg);
                    mLinePhone.setBackgroundResource(R.drawable.btn_oprate_bg);
                    mTvLogin.setEnabled(true);
                    mTvLogin.setClickable(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        /**
         * 查看密码
         */
        mImgPwdIsInvisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSeePasswordOprate) {
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


    @OnClick({R.id.tv_get_verification_code, R.id.tv_login, R.id.iv_toolbar_right, R.id.tv_user_agreement})
    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.tv_get_verification_code:
                if (validateInternet()) {
                    String phoneNum = mEdtPhone.getText().toString().trim();
                    if (TextUtils.isEmpty(phoneNum)) {
                        showToast(UserRegistrationActivity.this, getResources().getString(R.string.str_input_phone));
                        return;
                    }
                    showProgressDialog(UserRegistrationActivity.this);
                    mDialog.setTitle("正在发送验证码...");
                    mPresenter.getRegisterVerifyCode(phoneNum);
                }
                break;
            case R.id.tv_login:

                getSalt();
                break;
            case R.id.iv_toolbar_right:

                UserRegistrationActivity.this.finish();
                break;
          /*  case R.id.tv_user_agreement:

                Intent intent = new Intent();
                intent.putExtra("url", Net.USER_AGREEMENT_URL);
                intent.putExtra("title", Constants.USER_AGREEMENT_H5_TITEL);
                intent.setClass(UserRegistrationActivity.this, WebActivity.class);
                startActivity(intent);


                break;*/
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
        dismissProgressDialog();
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
                dismissProgressDialog();
                showToast(UserRegistrationActivity.this, Constants.getResultMsg(res.getMsg()));
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
                    SharedPreferencesUtils.putData("version", currentVersionName);
                    finish();
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
    public void getSaltResult(BackResult<Object> res) {
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    String saltKey = (String) res.getData();
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
                    dismissProgressDialog();
                    e.printStackTrace();
                }

                break;
            default:
                dismissProgressDialog();
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
                    userId = loginResponse.getId();                 //用户id
                    String mobile = loginResponse.getMobile();      //手机号
                    String nickname = loginResponse.getNickname();  //昵称
                    String username = loginResponse.getUsername();  //用户名
                    String token = res.getToken();
                    SharedPreferencesUtils.saveLoginData(userId, mobile, nickname, username, token);
                    getUserInfo();

                } catch (Exception e) {
                    dismissProgressDialog();
                    e.printStackTrace();
                }

                break;
            default:
                dismissProgressDialog();
                showToast(UserRegistrationActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {
        dismissProgressDialog();
        showToast(UserRegistrationActivity.this, Constants.getResultMsg(msg));
    }

    //获取盐
    public void getSalt() {

        if (validateInternet()) {
            phoneNum = mEdtPhone.getText().toString().trim();
            if (TextUtils.isEmpty(phoneNum)) {

                showToast(UserRegistrationActivity.this, getResources().getString(R.string.str_input_phone_number));
                return;
            }
            showProgressDialog(UserRegistrationActivity.this);
            mDialog.setTitle("");
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

    //获取用户信息
    public void getUserInfo() {

        if (validateInternet()) {

            mPresenter.getUserInfo(userId);
        }
    }
}
