package com.nbhysj.coupon.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.flyco.tablayout.SlidingTabLayout;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.fragment.BroadcastFragment;
import com.nbhysj.coupon.fragment.MessageFragment;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.ToolbarHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FollowAndFansActivity extends BaseActivity {
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private ArrayList<Fragment> fragments;
    private MessageFragment messageFragment;
    private BroadcastFragment notifyFragment;
    private String[] titles = new String[]{"关注", "粉丝"};


    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_attention_and_fans;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setBar(FollowAndFansActivity.this,getResources().getString(R.string.str_attention_and_fens),R.mipmap.icon_left_arrow_black);

        fragments = new ArrayList<>();
        List<Fragment> fragments1 = getSupportFragmentManager().getFragments();
        if (fragments1 != null && fragments1.size() > 1) {
            messageFragment = (MessageFragment) fragments1.get(0);
            notifyFragment = (BroadcastFragment) fragments1.get(1);
        } else {
            messageFragment = new MessageFragment();
            notifyFragment = new BroadcastFragment();
        }

        fragments.add(messageFragment);
        fragments.add(notifyFragment);

        tabLayout.setViewPager(viewpager, titles, this, fragments);
    }

    @Override
    public void initData() {


    }

    @Override
    public void initPresenter() {

    }
}
