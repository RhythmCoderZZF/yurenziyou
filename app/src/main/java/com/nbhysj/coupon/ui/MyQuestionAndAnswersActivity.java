package com.nbhysj.coupon.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.FragmentManageAdapter;
import com.nbhysj.coupon.fragment.HotelSelectionFragment;
import com.nbhysj.coupon.fragment.InteractiveSelectionFragment;
import com.nbhysj.coupon.fragment.LocalFoodFragment;
import com.nbhysj.coupon.fragment.ScenicSpotFragment;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
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
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/03/02
 * description：更多问题
 */
public class MyQuestionAndAnswersActivity extends BaseActivity {
    private String[] mTitles = new String[]{"待我来答", "提问", "同问", "回答"};
    private List<String> mDataList = Arrays.asList(mTitles);
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    private FragmentManageAdapter fragmentManageAdapter;
    SimplePagerTitleView simplePagerTitleView;
    CommonNavigatorAdapter commonNavigatorAdapter;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_my_question_and_answers;
    }

    @Override
    public void initView(Bundle savedInstanceState)
    {
        ToolbarHelper.setBar(MyQuestionAndAnswersActivity.this,getResources().getString(R.string.str_question_and_answers),R.mipmap.icon_left_arrow_black);
    }

    @Override
    public void initData() {

        fragmentManageAdapter = new FragmentManageAdapter(getSupportFragmentManager(), getFragments());
        // orderFragmentManageAdapter.getCurrentFragment(0);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(fragmentManageAdapter);
        // viewPager.setOffscreenPageLimit(0);
        initMagicIndicator();

        mTvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 String[] mTitles = new String[]{"待我来答12", "提问", "同问", "回答1"};
                 mDataList = Arrays.asList(mTitles);

                commonNavigatorAdapter.notifyDataSetChanged();

            }
        });
    }
    private List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new ScenicSpotFragment());
        fragments.add(new LocalFoodFragment());
        fragments.add(new HotelSelectionFragment());
        fragments.add(new InteractiveSelectionFragment());
        return fragments;
    }
    @Override
    public void initPresenter() {

    }

    private void initMagicIndicator() {
        CommonNavigator commonNavigator = new CommonNavigator(MyQuestionAndAnswersActivity.this);
        commonNavigator.setScrollPivotX(0.65f);
        commonNavigator.setAdjustMode(true);
        commonNavigatorAdapter = new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                simplePagerTitleView = new ColorFlipPagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setNormalColor(ContextCompat.getColor(MyQuestionAndAnswersActivity.this, R.color.color_text_gray28));
                simplePagerTitleView.setSelectedColor(ContextCompat.getColor(MyQuestionAndAnswersActivity.this, R.color.color_text_black7));
                simplePagerTitleView.setTextSize(14);
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
                indicator.setColors(ContextCompat.getColor(MyQuestionAndAnswersActivity.this, R.color.color_green3));
                return indicator;
            }
        };
        commonNavigator.setAdapter(commonNavigatorAdapter);
        magicIndicator.setNavigator(commonNavigator);


        ViewPagerHelper.bind(magicIndicator, viewPager);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
