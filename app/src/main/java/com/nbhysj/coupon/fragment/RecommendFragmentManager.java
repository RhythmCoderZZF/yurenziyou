package com.nbhysj.coupon.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nbhysj.coupon.model.response.HomePageResponse;
import com.nbhysj.coupon.model.response.HomePageSubTopicTagBean;

import java.util.List;

public class RecommendFragmentManager extends FragmentPagerAdapter {

    private List<HomePageResponse.SmallTagEntity> smallTagList;

    private List<HomePageSubTopicTagBean> recommendList;

    public void setData(List<HomePageResponse.SmallTagEntity> smallTagList, List<HomePageSubTopicTagBean> recommendList) {
        this.smallTagList = smallTagList;
        this.recommendList = recommendList;
    }

    public RecommendFragmentManager(FragmentManager fm) {
        super(fm);
    }

   /* @Override
    public Fragment getItem(int position) {
        //return MyRecommendFragment.newInstance(position,recommendList);
    }
*/

    @Override
    public Fragment getItem(int position) {
        return null;
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