package com.nbhysj.coupon.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.fragment.FindPwdByEmailFragment;
import com.nbhysj.coupon.fragment.FindPwdByPhoneFragment;
import com.nbhysj.coupon.fragment.FollowFragment;
import com.nbhysj.coupon.fragment.NearbyFragment;
import com.nbhysj.coupon.fragment.RecommendFragment;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.ToolbarHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/03/06
 * description：找回密码
 */
public class FindPasswordActivity extends BaseActivity {

    private FindPwdByPhoneFragment mFindPwdByPhone;
    private FindPwdByEmailFragment mFindPwdByEmail;
    private String[] titles = new String[]{"手机找回", "邮箱找回"};
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private ArrayList<Fragment> fragments;

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_find_password;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setHeaderBar(FindPasswordActivity.this, "", R.mipmap.icon_left_arrow_black, 0);
        fragments = new ArrayList<>();
        List<Fragment> fragments1 = getSupportFragmentManager().getFragments();
        if (fragments1 != null && fragments1.size() > 1) {
            mFindPwdByPhone = (FindPwdByPhoneFragment) fragments1.get(1);
           // mFindPwdByEmail = (FindPwdByEmailFragment) fragments1.get(2);
        } else {
            mFindPwdByPhone = new FindPwdByPhoneFragment();
           // mFindPwdByEmail = new FindPwdByEmailFragment();
        }

        fragments.add(mFindPwdByPhone);
       // fragments.add(mFindPwdByEmail);

        tabLayout.setViewPager(viewpager, titles, FindPasswordActivity.this, fragments);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }
}
