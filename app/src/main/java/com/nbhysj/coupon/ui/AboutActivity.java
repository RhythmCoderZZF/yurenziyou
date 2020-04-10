package com.nbhysj.coupon.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.util.AppMarketUtil;
import com.nbhysj.coupon.util.ToolbarHelper;

import butterknife.BindView;
import butterknife.OnClick;

public class AboutActivity<UserInfoPresenter, UserInfoModel> extends BaseActivity {

    //版本号
    @BindView(R.id.tv_version)
    TextView mTvVersion;

    @BindView(R.id.iv_back)
    ImageButton mIBtnBack;

    @Override
    public int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        String versionName = getCurrentVersionName();
        mTvVersion.setText("版本" + versionName);
    }

    @Override
    public void initData() {

        ToolbarHelper.setLoginBar(AboutActivity.this,"关于",R.mipmap.icon_left_arrow_black,"");

        mIBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
    }

    @Override
    public void initPresenter() {

    }

    @OnClick({R.id.rlyt_evaluate_murloc_travel})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlyt_evaluate_murloc_travel:
                startMarket(AboutActivity.this);
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
            showToast(AboutActivity.this, "无法打开应用市场");
        }
    }
}
