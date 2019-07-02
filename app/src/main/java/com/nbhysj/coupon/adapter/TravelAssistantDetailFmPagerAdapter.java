package com.nbhysj.coupon.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.nbhysj.coupon.model.response.HomePageResponse;
import com.nbhysj.coupon.model.response.TripDetailsResponse;

import java.util.List;

public class TravelAssistantDetailFmPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragmentList;
    private FragmentManager fm;
    List<TripDetailsResponse.DetailsEntity> tagList;

    public TravelAssistantDetailFmPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fm = fm;
    }

    public void setTravelAssistantDetailList(List<Fragment> fragmentList, List<TripDetailsResponse.DetailsEntity> tagList) {

        this.fragmentList = fragmentList;
        this.tagList = tagList;
    }

    @Override
    public int getCount() {
        return fragmentList != null && !fragmentList.isEmpty() ? fragmentList.size() : 0;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tagList.get(position).getTripDate();
    }


}
