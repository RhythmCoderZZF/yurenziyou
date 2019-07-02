package com.nbhysj.coupon.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.nbhysj.coupon.model.response.CountryBean;
import com.nbhysj.coupon.model.response.HomePageResponse;

import java.util.List;

public class TravelAssistantCountryPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragmentList;
    List<CountryBean> countryList;

    public TravelAssistantCountryPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setTravelAssistantCountryList(List<Fragment> fragmentList, List<CountryBean> countryList) {

        this.fragmentList = fragmentList;
        this.countryList = countryList;
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
        return countryList.get(position).getCounty();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }
}
