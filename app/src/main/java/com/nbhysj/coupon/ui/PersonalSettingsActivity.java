package com.nbhysj.coupon.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.UserInfoContract;
import com.nbhysj.coupon.model.UserInfoModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.MyCardResponse;
import com.nbhysj.coupon.model.response.ThirdPartyLoginStatusResponse;
import com.nbhysj.coupon.presenter.UserInfoPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.AppMarketUtil;
import com.nbhysj.coupon.util.MemerryClear;
import com.nbhysj.coupon.util.MemerryClearUtil;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.nbhysj.coupon.util.ToolbarHelper;

import butterknife.BindView;
import butterknife.OnClick;

import static com.nbhysj.coupon.util.AppMarketUtil.isIntentSafe;

/**
 * @auther：hysj created on 2019/03/02
 * description：个人设置
 */
public class PersonalSettingsActivity extends BaseActivity<UserInfoPresenter, UserInfoModel> implements UserInfoContract.View {

    //编辑个人资料
    @BindView(R.id.rlyt_edit_personal_data)
    RelativeLayout mRlytEditPersonalData;

    //缓存
    @BindView(R.id.tv_cache_size)
    TextView mTvCacheSize;
    //缓存大小
    String mTotalCacheSize;

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_personal_settings;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ToolbarHelper.setBar(PersonalSettingsActivity.this, getResources().getString(R.string.str_personal_setting), R.mipmap.nav_ico_back_black);
    }

    @Override
    public void initData() {

        try {
            mTotalCacheSize = MemerryClear.getTotalCacheSize(this);
            mTvCacheSize.setText(mTotalCacheSize);

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
                onReLogin("");
                break;
            default:
                showToast(PersonalSettingsActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getMyCardResult(BackResult<MyCardResponse> res) {

    }

    @Override
    public void showMsg(String msg) {
        dismissProgressDialog();
        showToast(PersonalSettingsActivity.this, Constants.getResultMsg(msg));
    }

    @OnClick({R.id.rlyt_edit_personal_data, R.id.rlyt_bind_account_and_setting, R.id.rlyt_frequently_used_information, R.id.tv_logout, R.id.rlyt_clear_cache, R.id.rlyt_evaluate_murloc_travel,R.id.rlyt_video_auto_play_settings})
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
              //  logout();
                finish();
                onReLogin("");
                break;
            case R.id.rlyt_clear_cache:
                try {
                    cacheClearDialog(PersonalSettingsActivity.this, mTotalCacheSize);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.rlyt_video_auto_play_settings:

                toActivity(VideoAutoPlaySettingsActivity.class);
                break;
            default:
                break;
        }
    }

    //3.跳转应用市场的本应用详情页：
    public void startMarket(Activity activity) {
        Uri uri = Uri.parse(String.format("market://details?id=%s", AppMarketUtil.getAppProcessName(activity)));
        if (AppMarketUtil.isIntentSafe(activity, uri))    //  设备已安装应用市场
        {
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(intent);
        } else // 没有安装市场
        {
            showToast(PersonalSettingsActivity.this, "无法打开应用市场");
        }
    }

    public void cacheClearDialog(final Activity activity, final String msg) {

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("清除缓存")
                .setMessage(msg + "缓存")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MemerryClear.clearAllCache(activity);
                        try {
                            mTvCacheSize.setText(MemerryClear.getTotalCacheSize(activity));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
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
