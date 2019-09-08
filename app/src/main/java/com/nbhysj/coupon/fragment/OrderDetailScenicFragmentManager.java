package com.nbhysj.coupon.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nbhysj.coupon.model.response.OrderDetailResponse;
import com.nbhysj.coupon.model.response.OrderDetailScenicSpotReponse;

import java.util.List;

public class OrderDetailScenicFragmentManager extends FragmentPagerAdapter {

    private List<OrderDetailResponse.OrderGoodsEntity> orderGoodsList;

    public OrderDetailScenicFragmentManager(FragmentManager fm, List<OrderDetailResponse.OrderGoodsEntity> orderGoodsList) {
        super(fm);
        this.orderGoodsList = orderGoodsList;
    }

    @Override
    public Fragment getItem(int position) {
        return OrderDetailScenicSpotFragment.newInstance(orderGoodsList.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return orderGoodsList.get(position).getMchName();
    }

    @Override
    public int getCount() {
        return orderGoodsList.size();
    }
}