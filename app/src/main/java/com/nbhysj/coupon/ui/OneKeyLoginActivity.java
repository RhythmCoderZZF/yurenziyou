package com.nbhysj.coupon.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.ImageListAdapter;

import cn.jiguang.verifysdk.api.JVerificationInterface;
import cn.jiguang.verifysdk.api.VerifyListener;

public class OneKeyLoginActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvLog;
    private Button btnGetToken;
    private EditText etPhone;
    private Button btnValidate;
    private Button btn_login;

    @Override
    public int getLayoutId() {
        return R.layout.activity_one_key_login;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        view();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_validate:
                verifyNumber();
                break;
            case R.id.btn_get_token:
                getToken();
                break;
            case R.id.btn_login:
                loginAuth();
                break;
        }
    }

    public void view() {
        etPhone = (EditText) findViewById(R.id.et_phone);
        btnValidate = (Button) findViewById(R.id.btn_validate);

        btnValidate.setOnClickListener(this);
        tvLog = (TextView) findViewById(R.id.tv_log);
        tvLog.setOnClickListener(this);
        btnGetToken = (Button) findViewById(R.id.btn_get_token);
        btnGetToken.setOnClickListener(this);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
    }

    private String token;

    private void getToken() {
        boolean verifyEnable = JVerificationInterface.checkVerifyEnable(this);
        if (!verifyEnable) {
            tvLog.setText("[2016],msg = 当前网络环境不支持认证");
            return;
        }
        tvLog.setText(null);
        showLoadingDialog();
        JVerificationInterface.getToken(this, new VerifyListener() {
            @Override
            public void onResult(final int code, final String content, final String operator) {
                tvLog.post(new Runnable() {
                    @Override
                    public void run() {
                        if (code == 2000) {
                            token = content;
                            tvLog.setText("[" + code + "]token=" + content + ", operator=" + operator);
                        } else {
                            tvLog.setText("[" + code + "]message=" + content);
                        }
                        dismissLoadingDialog();

                    }
                });
            }
        });
    }

    private void verifyNumber() {
        boolean verifyEnable = JVerificationInterface.checkVerifyEnable(this);
        if (!verifyEnable) {
            tvLog.setText("[2016],msg = 当前网络环境不支持认证");
            return;
        }
        String phone = etPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        tvLog.setText(null);
        showLoadingDialog();
        JVerificationInterface.verifyNumber(this, token, phone, new VerifyListener() {
            @Override
            public void onResult(final int code, final String content, final String operator) {
                tvLog.post(new Runnable() {
                    @Override
                    public void run() {
                        tvLog.setText("[" + code + "]message=" + content + ", operator=" + operator);
                        dismissLoadingDialog();
                    }
                });
            }
        });
    }


    private void loginAuth() {
        boolean verifyEnable = JVerificationInterface.checkVerifyEnable(this);
        if (!verifyEnable) {
            tvLog.setText("[2016],msg = 当前网络环境不支持认证");
            return;
        }
        showLoadingDialog();
        JVerificationInterface.setLoginAuthLogo("logo_cm", "logo_cu", "logo_ct");
        JVerificationInterface.loginAuth(this, new VerifyListener() {
            @Override
            public void onResult(final int code, final String content, final String operator) {
                tvLog.post(new Runnable() {
                    @Override
                    public void run() {
                        tvLog.setText("[" + code + "]message=" + content + ", operator=" + operator);
                        dismissLoadingDialog();
                    }
                });
            }
        });
    }

    private void submit() {
        // validate
        String phone = etPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }

    private AlertDialog alertDialog;

    public void showLoadingDialog() {
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable());
        alertDialog.setCancelable(false);
        alertDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_SEARCH || keyCode == KeyEvent.KEYCODE_BACK)
                    return true;
                return false;
            }
        });
        alertDialog.show();
        alertDialog.setContentView(R.layout.loading_alert);
        alertDialog.setCanceledOnTouchOutside(false);
    }

    public void dismissLoadingDialog() {
        if (null != alertDialog && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }


}
