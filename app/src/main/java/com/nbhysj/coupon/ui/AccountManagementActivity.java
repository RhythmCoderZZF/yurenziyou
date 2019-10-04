package com.nbhysj.coupon.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.AccountManagementContract;
import com.nbhysj.coupon.model.AccountManagementModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.ThirdPartyLoginStatusResponse;
import com.nbhysj.coupon.model.response.UserInfoResponse;
import com.nbhysj.coupon.presenter.AccountmanagementPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.RadiusGradientSpanUtil;
import com.nbhysj.coupon.util.ToolbarHelper;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/03/08
 * description：账号管理
 */
public class AccountManagementActivity extends BaseActivity<AccountmanagementPresenter, AccountManagementModel> implements AccountManagementContract.View {

    //微博账号绑定
    @BindView(R.id.tv_weibo_account)
    TextView mTvWeiboAccountBind;
    //手机号绑定
    @BindView(R.id.tv_bind_phone)
    TextView mTvPhoneAccountBind;
    //邮箱绑定
    @BindView(R.id.tv_bind_email)
    TextView mTvEmailAccountBind;
    //邮箱绑定
    @BindView(R.id.tv_wechat_account_bind)
    TextView mTvWechatAccountBind;
    //邮箱绑定
    @BindView(R.id.tv_qq_account)
    TextView mTvQQAccountBind;

    private ThirdPartyLoginStatusResponse thirdPartyLoginStatusResponse;

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_account_management;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ToolbarHelper.setBar(AccountManagementActivity.this, getResources().getString(R.string.str_account_management), R.mipmap.nav_ico_back_black);
        // mTvAccount.setText(RadiusGradientSpanUtil.getRadiusGradientSpan(getResources().getString(R.string.str_bind_immediately),0xFF1DEB96, 0xFF0DDDF6));
    }

    @Override
    public void initData() {
        thirdPartyLoginStatusResponse = (ThirdPartyLoginStatusResponse) getIntent().getSerializableExtra("thirdPartyLoginStatusResponse");
        // System.out.print(thirdPartyLoginStatusResponse);
        boolean isMobileBind = thirdPartyLoginStatusResponse.isMOBILE();
        boolean isQQBind = thirdPartyLoginStatusResponse.isQQ();
        boolean isEmailBind = thirdPartyLoginStatusResponse.isEMAIL();
        boolean isWechatBind = thirdPartyLoginStatusResponse.isWECHAT();
        boolean isWeiboBind = thirdPartyLoginStatusResponse.isWEIBO();

        //是否手机绑定
        if (!isMobileBind) {

            mTvPhoneAccountBind.setText(RadiusGradientSpanUtil.getRadiusGradientSpan(getResources().getString(R.string.str_bind_immediately), 0xFF1DEB96, 0xFF0DDDF6));
        } else {
            mTvPhoneAccountBind.setText(getResources().getString(R.string.str_bound));
            mTvPhoneAccountBind.setTextColor(getResources().getColor(R.color.button_grey));
        }
        //是否qq绑定
        if (!isQQBind) {

            mTvQQAccountBind.setText(RadiusGradientSpanUtil.getRadiusGradientSpan(getResources().getString(R.string.str_bind_immediately), 0xFF1DEB96, 0xFF0DDDF6));
        } else {
            mTvQQAccountBind.setText(getResources().getString(R.string.str_bound));
            mTvQQAccountBind.setTextColor(getResources().getColor(R.color.button_grey));
        }
        //是否邮箱绑定
        if (!isEmailBind) {

            mTvEmailAccountBind.setText(RadiusGradientSpanUtil.getRadiusGradientSpan(getResources().getString(R.string.str_bind_immediately), 0xFF1DEB96, 0xFF0DDDF6));
        } else {
            mTvEmailAccountBind.setText(getResources().getString(R.string.str_bound));
            mTvEmailAccountBind.setTextColor(getResources().getColor(R.color.button_grey));
        }
        //是否微信绑定
        if (!isWechatBind) {

            mTvWechatAccountBind.setText(RadiusGradientSpanUtil.getRadiusGradientSpan(getResources().getString(R.string.str_bind_immediately), 0xFF1DEB96, 0xFF0DDDF6));
        } else {
            mTvWechatAccountBind.setText(getResources().getString(R.string.str_bound));
            mTvWechatAccountBind.setTextColor(getResources().getColor(R.color.button_grey));
        }
        //是否微博绑定
        if (!isWeiboBind) {

            mTvWeiboAccountBind.setText(RadiusGradientSpanUtil.getRadiusGradientSpan(getResources().getString(R.string.str_bind_immediately), 0xFF1DEB96, 0xFF0DDDF6));
        } else {
            mTvWeiboAccountBind.setText(getResources().getString(R.string.str_bound));
            mTvWeiboAccountBind.setTextColor(getResources().getColor(R.color.button_grey));
        }
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @OnClick({R.id.rlyt_logout,R.id.rlyt_update_password})
    public void onclick(View view) {
        {
            switch (view.getId()) {
                case R.id.rlyt_logout:

                    toActivityWithFinish(LoginActivity.class, null);

                    break;
                case R.id.rlyt_update_password:

                    toActivity(FindPasswordActivity.class);

                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void getThirdPartyLoginStatusResult(BackResult<ThirdPartyLoginStatusResponse> res) {

    }

    @Override
    public void getUserInfoResult(BackResult<UserInfoResponse> res) {

    }

    @Override
    public void getThirdPartyLoginCreateUserResult(BackResult res) {

    }

    @Override
    public void getRegisterVerifyCodeResult(BackResult res) {

    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(AccountManagementActivity.this, Constants.getResultMsg(msg));
    }

    public void getThirdPartyLoginStatus() {

        if (validateInternet()) {
            userId = getSharedPreferencesUserId();
            mPresenter.getThirdPartyLoginStatus(userId);
        }
    }
}
