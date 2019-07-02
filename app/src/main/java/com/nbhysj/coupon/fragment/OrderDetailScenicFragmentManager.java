package com.nbhysj.coupon.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nbhysj.coupon.model.response.OrderDetailScenicSpotReponse;

import java.util.List;

public class OrderDetailScenicFragmentManager extends FragmentPagerAdapter {

    private List<OrderDetailScenicSpotReponse> mOrderDetailScenicSpotList;

    public OrderDetailScenicFragmentManager(FragmentManager fm, List<OrderDetailScenicSpotReponse> mOrderDetailScenicSpotList) {
        super(fm);
        this.mOrderDetailScenicSpotList = mOrderDetailScenicSpotList;
    }

    @Override
    public Fragment getItem(int position) {
        return OrderDetailScenicSpotFragment.newInstance(mOrderDetailScenicSpotList.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "12";
    }

    @Override
    public int getCount() {
        return 3;
    }
}