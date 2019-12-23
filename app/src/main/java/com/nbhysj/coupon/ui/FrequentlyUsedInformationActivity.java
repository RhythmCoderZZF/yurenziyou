package com.nbhysj.coupon.ui;

import android.os.Bundle;
import android.view.View;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.ToolbarHelper;

import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/03/08
 * description：常用信息
 */
public class FrequentlyUsedInformationActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_frequently_used_information;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ToolbarHelper.setBar(FrequentlyUsedInformationActivity.this, getResources().getString(R.string.str_my_frequent_passengers), R.mipmap.nav_ico_back_black);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }


    @OnClick({R.id.rlyt_frequently_traveller, R.id.rlyt_frequently_contacts, R.id.rlyt_recipient_information})
    public void onclick(View v) {
        switch (v.getId()) {

            case R.id.rlyt_frequently_traveller:
                toActivity(MyFrequentPassengersActivity.class);
                break;
            case R.id.rlyt_frequently_contacts:
                toActivity(FrequentContactsActivity.class);
                break;
            case R.id.rlyt_recipient_information:
                toActivity(RecipientListActivity.class);
                break;
            default:
                break;
        }
    }
}
