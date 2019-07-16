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
import com.nbhysj.coupon.adapter.ComFragmentAdapter;
import com.nbhysj.coupon.fragment.ScenicSpotFragment;
import com.nbhysj.coupon.fragment.TravelAssistantTrafficAddDriveFragment;
import com.nbhysj.coupon.fragment.TravelAssistantTrafficAddTrainFragment;
import com.nbhysj.coupon.fragment.TripTrafficAddPassengerTransportFragment;
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
 * @auther：hysj created on 2019/03/08
 * description：添加交通
 */
public class TravelAssistantAddTrafficActivity extends BaseActivity {

    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.view_pager_traffic_classify)
    ViewPager mViewPagerTrafficClassify;

    private String[] mTitles = new String[]{"火车","客运"};
    private List<String> mDataList = Arrays.asList(mTitles);
    //行程id
    private int tripId;
    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_add_traffic;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
       /* //沉浸式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        ViewGroup.LayoutParams layoutParams = mToolbarSpace.getLayoutParams();//取控件当前的布局参数
        layoutParams.height = getStatusBarHeight();// 控件的高强制设成状态栏高度
        mToolbarSpace.setLayoutParams(layoutParams); //使设置好的布局参数应用到控件</pre>
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbarSpace.setVisibility(View.VISIBLE);
        } else {
            mToolbarSpace.setVisibility(View.GONE);
        }*/

        ToolbarHelper.setHeadBar(TravelAssistantAddTrafficActivity.this,getResources().getString(R.string.str_add_traffic),R.mipmap.icon_left_arrow_black,"");
    }

    @Override
    public void initData() {

        tripId = getIntent().getIntExtra("tripId",0);

        mViewPagerTrafficClassify.setAdapter(new ComFragmentAdapter(getSupportFragmentManager(), getFragments()));
        mViewPagerTrafficClassify.setOffscreenPageLimit(10);
        initMagicIndicator();
    }


    @Override
    public void initPresenter() {

    }

    private List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new TravelAssistantTrafficAddTrainFragment().newInstance(tripId));
        fragments.add(new TripTrafficAddPassengerTransportFragment().newInstance(tripId));
        return fragments;
    }

    private void initMagicIndicator() {
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setScrollPivotX(0.65f);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorFlipPagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setNormalColor(ContextCompat.getColor(TravelAssistantAddTrafficActivity.this, R.color.color_text_gray24));
                simplePagerTitleView.setSelectedColor(ContextCompat.getColor(TravelAssistantAddTrafficActivity.this, R.color.black));
                simplePagerTitleView.setTextSize(16);
                //simplePagerTitleView.setTypeface(Typeface.DEFAULT_BOLD,Typeface.NORMAL);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPagerTrafficClassify.setCurrentItem(index, false);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 4));
                indicator.setLineWidth(UIUtil.dip2px(context, 18));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(ContextCompat.getColor(TravelAssistantAddTrafficActivity.this, R.color.color_high_light_green));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, mViewPagerTrafficClassify);
    }
}
