package com.nbhysj.coupon.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.fragment.BroadcastFragment;
import com.nbhysj.coupon.fragment.MessageFragment;
import com.nbhysj.coupon.fragment.MineFansFollowListFragment;
import com.nbhysj.coupon.fragment.MineFollowListFragment;
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
    @BindView(R.id.iv_back)
    ImageButton mImgBack;
    //标题
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    private ArrayList<Fragment> fragments;
    private MineFollowListFragment mineFollowListFragment;
    private MineFansFollowListFragment mineFansListFragment;
    private String[] titles = new String[]{"关注", "粉丝"};
    private int currentItem;

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_attention_and_fans;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        currentItem = getIntent().getIntExtra("currentItem",0);

        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FollowAndFansActivity.this.finish();
            }
        });

        mTvTitle.setText(getResources().getString(R.string.str_attention_and_fens));

        fragments = new ArrayList<>();
        List<Fragment> fragments1 = getSupportFragmentManager().getFragments();
        if (fragments1 != null && fragments1.size() > 1) {
            mineFollowListFragment = (MineFollowListFragment) fragments1.get(0);
            mineFansListFragment = (MineFansFollowListFragment) fragments1.get(1);
        } else {
            mineFollowListFragment = new MineFollowListFragment();
            mineFansListFragment = new MineFansFollowListFragment();
        }

        fragments.add(mineFollowListFragment);
        fragments.add(mineFansListFragment);

        tabLayout.setViewPager(viewpager, titles, this, fragments);

        viewpager.setCurrentItem(currentItem);
    }

    @Override
    public void initData() {


    }

    @Override
    public void initPresenter() {

    }
}
