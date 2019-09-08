package com.nbhysj.coupon.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class MyOrderFragmentManager extends FragmentPagerAdapter {

    String content[] = {};
    private List<Fragment> fragmentList;

    public MyOrderFragmentManager(FragmentManager fm, String content[], List<Fragment> fragmentList) {
        super(fm);
        this.content = content;
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return content[position % content.length];
    }

    @Override
    public int getCount() {
        return content.length;
    }
}