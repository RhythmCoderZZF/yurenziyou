package com.nbhysj.coupon.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.FragmentManageAdapter;
import com.nbhysj.coupon.fragment.HomeRecommendFragment;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.nbhysj.coupon.view.ColorFlipPagerTitleView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/07/31
 * description: 关注与粉丝
 */
public class AttentionAndFansActivity extends BaseActivity {
    private String[] mTitles = new String[]{"全部", "进行中", "已完成", "未接单"};
    List<Fragment> fragments;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;

    @Override
    public int getLayoutId() {
        return R.layout.activity_attention_and_fans;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setBar(AttentionAndFansActivity.this, getResources().getString(R.string.str_attention_and_fens), R.mipmap.icon_left_arrow_black);


        List<Fragment> fragmentList = getFragments();
        viewPager.setAdapter(new FragmentManageAdapter(getSupportFragmentManager(), fragmentList));
        viewPager.setOffscreenPageLimit(4);
        initMagicIndicator();
    }

    @Override
    public void initData() {

    }

    private List<Fragment> getFragments() {

        if (fragments == null) {
            fragments = new ArrayList<>();
        } else {
            fragments.clear();
        }
        fragments.add(new HomeRecommendFragment());
        fragments.add(new HomeRecommendFragment());
        return fragments;
    }

    @Override
    public void initPresenter() {

    }

    private void initMagicIndicator() {
        CommonNavigator commonNavigator = new CommonNavigator(AttentionAndFansActivity.this);
        commonNavigator.setScrollPivotX(0.65f);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mTitles == null ? 0 : mTitles.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorFlipPagerTitleView(context);
                simplePagerTitleView.setText(mTitles[index]);
                simplePagerTitleView.setNormalColor(ContextCompat.getColor(AttentionAndFansActivity.this, R.color.color_text_gray33));
                simplePagerTitleView.setSelectedColor(ContextCompat.getColor(AttentionAndFansActivity.this, R.color.color_text_blue10));
                simplePagerTitleView.setTextSize(16);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // scrollView.scrollTo(0,2835);
                        viewPager.setCurrentItem(index, false);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 2));
                indicator.setLineWidth(UIUtil.dip2px(context, 81));
                indicator.setRoundRadius(UIUtil.dip2px(context, 0));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(ContextCompat.getColor(AttentionAndFansActivity.this, R.color.color_text_blue10));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);
    }
}
