package com.nbhysj.coupon;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.flyco.tablayout.SlidingTabLayout;
import com.nbhysj.coupon.fragment.HomeFragment;
import com.nbhysj.coupon.ui.BaseActivity;
import com.nbhysj.coupon.view.TestView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TestActivity extends BaseActivity {

    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private String[] titles = new String[]{"游记", "收藏", "赞过"};
    private ArrayList<Fragment> fragments;
    private HomeFragment homeFragment1;
    private HomeFragment homeFragment2;
    private HomeFragment homeFragment3;

    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        fragments = new ArrayList<>();
        List<Fragment> fragments1 = getSupportFragmentManager().getFragments();
        if (fragments1 != null && fragments1.size() > 1) {
            homeFragment1 = (HomeFragment) fragments1.get(0);
            homeFragment2 = (HomeFragment) fragments1.get(1);
            homeFragment3 = (HomeFragment) fragments1.get(2);
        } else {
            homeFragment1 = new HomeFragment();
            homeFragment2 = new HomeFragment();
            homeFragment3 = new HomeFragment();
        }

        fragments.add(homeFragment1);
        fragments.add(homeFragment2);
        fragments.add(homeFragment3);

        tabLayout.setViewPager(viewpager, titles, this, fragments);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }

}
