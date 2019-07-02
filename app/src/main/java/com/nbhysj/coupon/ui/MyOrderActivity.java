package com.nbhysj.coupon.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.fragment.MyOrderFragmentManager;
import com.nbhysj.coupon.statusbar.StatusBarCompat;

import butterknife.BindView;

public class MyOrderActivity extends BaseActivity {
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    private String[] titles = new String[]{"全部", "待付款", "待出行", "待评价", "退换/售后"};

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_my_order;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        FragmentPagerAdapter adapter = new MyOrderFragmentManager(getSupportFragmentManager(), titles);
        viewpager.setAdapter(adapter);
        tabLayout.setViewPager(viewpager, titles);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }
}
