package com.nbhysj.coupon.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.annotations.NonNull;

/**
 * @author hysj created at 2019/03/02.
 * description : fragment 管理器
 */

public class FragmentManageAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;
    private Map<Integer, Fragment> map = new HashMap<Integer, Fragment>();

    public FragmentManageAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {

        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments != null ? fragments.size() : 0;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
   /*     if (fragments.contains(object)) {
            // 如果当前 item 未被 remove，则返回 item 的真实 position
            return fragments.indexOf(object);
        } else {*/
        // 否则返回状态值 POSITION_NONE

        // }

        return POSITION_NONE;
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == ((Fragment) obj).getView();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Fragment fragment = ((Fragment) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        //记录每个position位置最后显示的Fragment
        map.put(position, fragment);
        return fragment;
    }

    //获取指定位置最后显示的Fragment
    public Fragment getCurrentFragment(int index) {
        return map.get(index);
    }

}
