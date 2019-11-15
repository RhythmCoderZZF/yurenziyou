package com.nbhysj.coupon.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.flyco.tablayout.SlidingTabLayout;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.fragment.BroadcastFragment;
import com.nbhysj.coupon.fragment.MessageFragment;
import com.nbhysj.coupon.statusbar.StatusBarCompat;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MessageActivity extends BaseActivity {
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.img_back)
    ImageView mImgBack;
    private ArrayList<Fragment> fragments;
    private MessageFragment messageFragment;
    private BroadcastFragment notifyFragment;
    private String[] titles = new String[]{"消息", "广播"};


    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_message;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
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

        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MessageActivity.this.finish();
            }
        });
    }

    @Override
    public void initPresenter() {

    }
}
