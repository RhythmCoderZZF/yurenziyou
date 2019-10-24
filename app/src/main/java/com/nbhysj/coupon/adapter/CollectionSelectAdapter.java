package com.nbhysj.coupon.adapter;

import java.util.List;

/**
 * @author hysj created at 2019/4/14.
 * description : 收藏选项适配器
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nbhysj.coupon.model.response.CollectionTagResponse;

import java.util.List;

/**
 * @auther：hysj created at 2019/04/16
 * description：收藏选择项适配器
 */
public class CollectionSelectAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;
    private List<CollectionTagResponse> titleTagList;


    public CollectionSelectAdapter(FragmentManager fm, List<Fragment> fragmentList, List<CollectionTagResponse> titleTagList) {
        super(fm);
        this.fragmentList = fragmentList;
        this.titleTagList = titleTagList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleTagList.get(position).getTagName();
    }
}
