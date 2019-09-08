package com.nbhysj.coupon.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.flyco.tablayout.SlidingTabLayout;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.fragment.MyOrderFragmentManager;
import com.nbhysj.coupon.fragment.MyOrderListFragment;
import com.nbhysj.coupon.fragment.PendingPaymentListFragment;
import com.nbhysj.coupon.statusbar.StatusBarCompat;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MyOrderActivity extends BaseActivity {
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.iv_back)
    ImageView mImgBack;
    private String[] titles = new String[]{"全部", "待付款", "待出行", "待评价", "退换/售后"};

    private List<Fragment> fragmentList;
    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_my_order;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        if(fragmentList == null){

            fragmentList = new ArrayList<>();
        } else {

            fragmentList.clear();
        }

        fragmentList.add(new MyOrderListFragment());
        fragmentList.add(new PendingPaymentListFragment());
        fragmentList.add(new MyOrderListFragment());
        fragmentList.add(new MyOrderListFragment());
        fragmentList.add(new MyOrderListFragment());

        FragmentPagerAdapter adapter = new MyOrderFragmentManager(getSupportFragmentManager(), titles,fragmentList);
        viewpager.setAdapter(adapter);
        tabLayout.setViewPager(viewpager, titles);
    }

    @Override
    public void initData() {
        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyOrderActivity.this.finish();
            }
        });
    }

    @Override
    public void initPresenter() {

    }
}
