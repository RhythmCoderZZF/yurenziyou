package com.nbhysj.coupon.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.flyco.tablayout.SlidingTabLayout;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.dialog.CouponDescriptionDialog;
import com.nbhysj.coupon.dialog.OprateDialog;
import com.nbhysj.coupon.fragment.CouponListFragment;
import com.nbhysj.coupon.fragment.MyOrderFragmentManager;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.umeng.socialize.UMShareAPI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CouponListActivity extends BaseActivity {
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    //返回
    @BindView(R.id.iv_back)
    ImageView mImgBack;

    private String[] mTitles = new String[]{"未使用", "已过期", "已使用"};
    HashMap<String, String> couponStatusMap;

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_coupon_list;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        couponStatusMap = getCouponStatus();
        List<Fragment> fragmentList = getFragments();
        FragmentPagerAdapter adapter = new MyOrderFragmentManager(getSupportFragmentManager(), mTitles,fragmentList);
        viewPager.setAdapter(adapter);
        tabLayout.setViewPager(viewPager);
    }

    @Override
    public void initData() {

        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CouponListActivity.this.finish();
            }
        });
    }

    @Override
    public void initPresenter() {

      //  mPresenter.setVM(this,mModel);
    }

    private List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            String couponStatus = couponStatusMap.get(mTitles[i]);
            fragments.add(new CouponListFragment().newInstance(couponStatus));
        }
        return fragments;
    }

    private HashMap<String, String> getCouponStatus() {

        HashMap<String, String> map = new HashMap<>();
        map.put("未使用", "ALREADYRECEIVED");
        map.put("已过期", "EXPIRED");
        map.put("已使用", "USED");  //已付款
        return map;
    }

    @OnClick({R.id.tv_ticket_center})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.tv_ticket_center:

                toActivity(CouponCenterActivity.class);
                break;
                default:break;

        }

    }

}
