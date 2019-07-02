package com.nbhysj.coupon.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nbhysj.coupon.model.response.HomePageResponse;
import com.nbhysj.coupon.model.response.HomePageSubTopicTagBean;

import java.util.List;

public class NearbyFragmentManager extends FragmentPagerAdapter {

    private List<HomePageSubTopicTagBean> recommendList;
    private List<HomePageResponse.SmallTagEntity> smallTagList;

    public void setData(List<HomePageResponse.SmallTagEntity> smallTagList) {
        this.smallTagList = smallTagList;
    }

    public NearbyFragmentManager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return NearbySubTagFragment.newInstance(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return smallTagList.get(position).getTitle();
    }

    @Override
    public int getCount() {
        return smallTagList.size();
    }
}