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
import com.nbhysj.coupon.model.request.FindPwdByEmailRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.presenter.FindPwdPresenter;
import com.nbhysj.coupon.util.EncryptedSignatureUtil;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

public class FindPwdByEmailFragment extends BaseFragment<FindPwdPresenter, FindPwdModel> implements FindPwdContract.View {

    //手机号
    @BindView(R.id.edt_email_number)
    EditText mEdtEmailNumber;
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
    private String password;
    private String emailAccount;
    private String verifyCode;
    private Timer mTimer;
    Handler handler;
    private int delaytime = 60;
    private int VERIFY_CODE_MSG = 0;

    public FindPwdByEmailFragment() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_findpwd_by_email;
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

        mEdtEmailNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                checkIsInputFill(charSequence);
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

                checkIsInputFill(charSequence);
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

                checkIsInputFill(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void getFindPwdVerifyCodeResult(BackResult res) {

    }

    @Override
    public void updatePwdByEmailGetSaltResult(BackResult<String> res) {
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:

                String saltKey = res.getData();
                modifyPasswordByEmail(saltKey);
                break;
            default:
                dismissProgressDialog();
                showToast(getActivity(), Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getSaltResult(BackResult<String> res) {

    }

    @Override
    public void sendEmailResult(BackResult res) {
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
    public void modifyPasswordByMobileResult(BackResult res) {

    }

    @Override
    public void modifyPasswordByEmailResult(BackResult res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    showToast(getActivity(), "密码修改成功");
                    getActivity().finish();

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
    public void showMsg(String msg) {
        dismissProgressDialog();
        showToast(getActivity(), Constants.getResultMsg(msg));
    }

    //获取盐
    public void getSalt() {

        if (validateInternet()) {
            emailAccount = mEdtEmailNumber.getText().toString().trim();
            password = mEdtPassword.getText().toString().trim();
            verifyCode = mEdtVerifyCode.getText().toString().trim();
            if (TextUtils.isEmpty(emailAccount)) {
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

            showProgressDialog(getActivity());
            mDialog.setTitle(getResources().getString(R.string.str_updating_password));
            mPresenter.updatePwdByEmailGetSalt(emailAccount);
        }
    }

    //通过邮箱修改密码
    public void modifyPasswordByEmail(String saltKey) {

        if (validateInternet()) {
            try {

                encryptedPwd = EncryptedSignatureUtil.getHmacMd5Bytes(saltKey.getBytes(), password.getBytes());
                //encryptedPwd = hmacMd5Pwd.toUpperCase();
                FindPwdByEmailRequest findPwdRequest = new FindPwdByEmailRequest();
                findPwdRequest.setEmail(emailAccount);
                findPwdRequest.setEmailCode(verifyCode);
                findPwdRequest.setPassword(encryptedPwd);
                findPwdRequest.setSalt(saltKey);
                mPresenter.modifyPasswordByEmail(findPwdRequest);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @OnClick({R.id.tv_get_verification_code, R.id.tv_find_pwd})
    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.tv_get_verification_code:
                String phoneNum = mEdtEmailNumber.getText().toString().trim();

                if (TextUtils.isEmpty(phoneNum)) {
                    showToast(getActivity(), getResources().getString(R.string.str_input_phone_number));
                    return;
                }
                if (validateInternet()) {
                    showProgressDialog(getActivity());
                    mDialog.setTitle(getResources().getString(R.string.str_updating_password));
                    mPresenter.sendEmail(phoneNum);
                }
                break;
            case R.id.tv_find_pwd:

                getSalt();
                break;
            default:
                break;
        }
    }

    //校验输入信息是否填写完成
    public void checkIsInputFill(CharSequence charSequence) {

        String phone = charSequence.toString().trim();
        String password = mEdtPassword.getText().toString().trim();
        verifyCode = mEdtVerifyCode.getText().toString().trim();
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password) || TextUtils.isEmpty(verifyCode)) {

            mTvFindPwd.setBackgroundResource(R.drawable.bg_rect_gray_shape);
        } else {
            mTvFindPwd.setBackgroundResource(R.drawable.btn_oprate_bg);
        }
    }

    @Override
    public void lazyInitView(View view) {

    }
}
