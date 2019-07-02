package com.nbhysj.coupon.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.UserInfoContract;
import com.nbhysj.coupon.model.UserInfoModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.ThirdPartyLoginStatusResponse;
import com.nbhysj.coupon.presenter.UserInfoPresenter;
import com.nbhysj.coupon.util.MemerryClearUtil;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.nbhysj.coupon.util.ToolbarHelper;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/03/02
 * description：个人设置
 */
public class PersonalSettingsActivity extends BaseActivity<UserInfoPresenter, UserInfoModel> implements UserInfoContract.View {

    //编辑个人资料
    @BindView(R.id.rlyt_edit_personal_data)
    RelativeLayout mRlytEditPersonalData;

    //缓存
    @BindView(R.id.tv_cache)
    TextView mTvCache;

    @Override
    public int getLayoutId() {
        return R.layout.activity_personal_settings;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ToolbarHelper.setBar(PersonalSettingsActivity.this, getResources().getString(R.string.str_personal_setting), R.mipmap.nav_ico_back_black);
    }

    @Override
    public void initData() {

        try {
            String cacheSize = MemerryClearUtil.getTotalCacheSize(PersonalSettingsActivity.this);
            mTvCache.setText(cacheSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    public void getUserInfoResult(BackResult res) {

    }

    @Override
    public void updateInformationResult(BackResult res) {

    }

    @Override
    public void getThirdPartyLoginStatusResult(BackResult<ThirdPartyLoginStatusResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    ThirdPartyLoginStatusResponse thirdPartyLoginStatusResponse = res.getData();
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("thirdPartyLoginStatusResponse", thirdPartyLoginStatusResponse);
                    intent.setClass(PersonalSettingsActivity.this, AccountManagementActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(PersonalSettingsActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void userLogoutResult(BackResult res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                finish();
                onReLogin();
                break;
            case Constants.USER_NOT_LOGIN_CODE:
                finish();
                onReLogin();
                break;
            default:
                showToast(PersonalSettingsActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {
        dismissProgressDialog();
        showToast(PersonalSettingsActivity.this, Constants.getResultMsg(msg));
    }

    @OnClick({R.id.rlyt_edit_personal_data, R.id.rlyt_bind_account_and_setting, R.id.rlyt_frequently_used_information, R.id.tv_logout})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlyt_bind_account_and_setting:

                getThirdPartyLoginStatus();
                break;
            case R.id.rlyt_edit_personal_data:

                toActivity(AccountAndPersonalDataActivity.class);

                break;
            case R.id.rlyt_frequently_used_information:
                toActivity(FrequentlyUsedInformationActivity.class);
                break;
            case R.id.tv_logout:
                logout();
                break;
            default:
                break;
        }
    }

    //登出
    public void logout() {

        if (validateInternet()) {
            showProgressDialog(PersonalSettingsActivity.this);
            mDialog.setTitle("");
            mPresenter.userLogout();
        }
    }

    //获取第三方登录状态
    public void getThirdPartyLoginStatus() {

        if (validateInternet()) {
            showProgressDialog(PersonalSettingsActivity.this);
            mDialog.setTitle("");
            userId = getSharedPreferencesUserId();
            mPresenter.getThirdPartyLoginStatus(userId);
        }
    }
}
