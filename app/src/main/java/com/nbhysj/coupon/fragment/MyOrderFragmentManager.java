package com.nbhysj.coupon.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyOrderFragmentManager extends FragmentPagerAdapter {

    String content[] = {};

    public MyOrderFragmentManager(FragmentManager fm, String content[]) {
        super(fm);
        this.content = content;
    }

    @Override
    public Fragment getItem(int position) {
        return MyOrderListFragment.newInstance("fragment");
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