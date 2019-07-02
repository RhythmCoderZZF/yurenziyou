package com.nbhysj.coupon.fragment;

import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.FindPwdContract;
import com.nbhysj.coupon.model.FindPwdModel;
import com.nbhysj.coupon.model.request.FindPwdByPhoneRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.presenter.FindPwdPresenter;
import com.nbhysj.coupon.ui.UserRegistrationActivity;
import com.nbhysj.coupon.util.EncryptedSignatureUtil;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

public class FindPwdByPhoneFragment extends BaseFragment<FindPwdPresenter, FindPwdModel> implements FindPwdContract.View {

    //手机号
    @BindView(R.id.edt_phone)
    EditText mEdtPhone;
    //密码
    @BindView(R.id.edt_password)
    EditText mEdtPassword;
    //获取短信验证码
    @BindView(R.id.tv_get_verification_code)
    TextView mTvGetVerificationCode;
    //短信验证码
    @BindView(R.id.edt_verification_code)
    EditText mEdtVerifyCode;
    @BindView(R.id.tv_find_pwd)
    TextView mTvFindPwd;
    //加密后的密码
    private String encryptedPwd;
    private String phoneNum;
    private String verifyCode;
    private Timer mTimer;
    Handler handler;
    private int delaytime = 60;
    private int VERIFY_CODE_MSG = 0;

    public FindPwdByPhoneFragment() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_findpwd_by_phone;
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView(View v) {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == VERIFY_CODE_MSG) {
                    mTvGetVerificationCode.setText("重新发送(" + delaytime + ")");
                    if (delaytime == 59) {
                        showToast(getActivity(), getResources().getString(R.string.str_short_msg_send_success));
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
    public void initData() {

        mEdtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String phone = charSequence.toString().trim();
                String password = mEdtPassword.getText().toString().trim();
                verifyCode = mEdtVerifyCode.getText().toString().trim();
                if (TextUtils.isEmpty(phone) && TextUtils.isEmpty(password) && TextUtils.isEmpty(verifyCode)) {

                    mTvFindPwd.setBackgroundResource(R.drawable.bg_rect_gray_shape);
                } else {
                    mTvFindPwd.setBackgroundResource(R.drawable.btn_oprate_bg);
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

                String phone = charSequence.toString().trim();
                String password = mEdtPassword.getText().toString().trim();
                verifyCode = mEdtVerifyCode.getText().toString().trim();
                if (TextUtils.isEmpty(phone) && TextUtils.isEmpty(password) && TextUtils.isEmpty(verifyCode)) {

                    mTvFindPwd.setBackgroundResource(R.drawable.bg_rect_gray_shape);
                } else {
                    mTvFindPwd.setBackgroundResource(R.drawable.btn_oprate_bg);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void getFindPwdVerifyCodeResult(BackResult res) {
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:

                delaytime = 60;
                mTvGetVerificationCode.setEnabled(false);
                showTimer();
                break;
            default:
                showToast(getActivity(), Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getSaltResult(BackResult<String> res) {
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:

                String saltKey = res.getData();
                findPwdByPhone(saltKey);
                break;
            default:
                showToast(getActivity(), Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void sendEmailResult(BackResult res) {

    }

    @Override
    public void updatePwdByEmailGetSaltResult(BackResult<String> res) {

    }

    @Override
    public void modifyPasswordByMobileResult(BackResult res) {
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    showToast(getActivity(), res.getMsg());

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(getActivity(), Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void modifyPasswordByEmailResult(BackResult res) {

    }

    @Override
    public void showMsg(String msg) {
        dismissProgressDialog();
        showToast(getActivity(), Constants.getResultMsg(msg));
    }

    //获取盐
    public void getSalt() {

        if (validateInternet()) {
            phoneNum = mEdtPhone.getText().toString().trim();
            mPresenter.getSalt(phoneNum);
        }
    }

    //通过手机号修改密码
    public void findPwdByPhone(String saltKey) {

        if (validateInternet()) {
            try {
                String phoneNum = mEdtPhone.getText().toString().trim();
                String password = mEdtPassword.getText().toString().trim();
                verifyCode = mEdtVerifyCode.getText().toString().trim();

                if (TextUtils.isEmpty(phoneNum)) {
                    showToast(getActivity(), getResources().getString(R.string.str_input_phone_number));
                    return;
                }

                if (TextUtils.isEmpty(verifyCode)) {
                    showToast(getActivity(), getResources().getString(R.string.str_verification_code_is_error));
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    showToast(getActivity(), getResources().getString(R.string.str_input_password));
                    return;
                }

                encryptedPwd = EncryptedSignatureUtil.getHmacMd5Bytes(saltKey.getBytes(), password.getBytes());
                //encryptedPwd = hmacMd5Pwd.toUpperCase();
                FindPwdByPhoneRequest findPwdRequest = new FindPwdByPhoneRequest();
                findPwdRequest.setMobile(phoneNum);
                findPwdRequest.setPassword(encryptedPwd);
                findPwdRequest.setAuth(verifyCode);
                findPwdRequest.setSalt(saltKey);
                mPresenter.modifyPasswordByMobile(findPwdRequest);
            } catch (Exception e) {  //NoSuchAlgorithmException
                e.printStackTrace();
            }
        }
    }

    @OnClick({R.id.tv_get_verification_code, R.id.tv_find_pwd})
    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.tv_get_verification_code:
                String phoneNum = mEdtPhone.getText().toString().trim();

                if (TextUtils.isEmpty(phoneNum)) {
                    showToast(getActivity(), getResources().getString(R.string.str_input_phone_number));
                    return;
                }

                mPresenter.getFindPwdVerifyCode(phoneNum);
                break;
            case R.id.tv_find_pwd:

                getSalt();
                break;
            default:
                break;
        }
    }

    @Override
    public void lazyInitView(View view) {

    }
}
