package com.nbhysj.coupon.ui;


import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.ComFragmentAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.HomestayContract;
import com.nbhysj.coupon.fragment.HomestayCommentFragment;
import com.nbhysj.coupon.fragment.HouseResourceFragment;
import com.nbhysj.coupon.fragment.LocalFoodFragment;
import com.nbhysj.coupon.fragment.ScenicSpotFragment;
import com.nbhysj.coupon.model.HomestayModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CouponsGetBean;
import com.nbhysj.coupon.model.response.HouseResouceResponse;
import com.nbhysj.coupon.model.response.LandlordBean;
import com.nbhysj.coupon.model.response.LandlordDetailResonse;
import com.nbhysj.coupon.model.response.MchBangDanRankingResponse;
import com.nbhysj.coupon.model.response.MchCollectionResponse;
import com.nbhysj.coupon.model.response.MchCouponResponse;
import com.nbhysj.coupon.model.response.MchHomestayDetailsResponse;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.model.response.ScenicSpotResponse;
import com.nbhysj.coupon.presenter.HomestayPresenter;
import com.nbhysj.coupon.systembar.StatusBarCompat;
import com.nbhysj.coupon.systembar.StatusBarUtil;
import com.nbhysj.coupon.util.ScreenUtil;
import com.nbhysj.coupon.view.ColorFlipPagerTitleView;
import com.nbhysj.coupon.view.JudgeNestedScrollView;
import com.scwang.smartrefresh.layout.util.DensityUtil;

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
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/05/09
 * description：房东介绍
 */
public class IntroductionOfLandlordActivity extends BaseActivity<HomestayPresenter, HomestayModel> implements HomestayContract.View {
    @BindView(R.id.toolbar_space)
    View mToolbarSpace;
    @BindView(R.id.llyt_header)
    LinearLayout mLlytHeaderToolbar;
    @BindView(R.id.scrollView)
    JudgeNestedScrollView scrollView;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.magic_indicator_title)
    MagicIndicator magicIndicatorTitle;
    @BindView(R.id.collapse)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    //名字
    @BindView(R.id.tv_nickname)
    TextView mTvNickName;
    //预定成功率
    @BindView(R.id.tv_scheduled_success_rate)
    TextView mTvScheduledSuccessRate;
    //平均确认时长
    @BindView(R.id.tv_average_confirmation_time)
    TextView mTvAverageConfirmationTime;
    //房源数
    @BindView(R.id.tv_house_resources_num)
    TextView mTvHouseResoucesNum;
    //整体评分
    @BindView(R.id.tv_overall_score)
    TextView mTvOverallScore;
    //房东语录
    @BindView(R.id.llyt_landlord_motto)
    LinearLayout mLlytMotto;
    @BindView(R.id.tv_motto)
    TextView mTvMotto;

    //房源数量
    private int homestayRoomNum;
    int toolBarPositionY = 0;
    private int mOffset = 0;
    private int mScrollY = 0;
    private String[] mTitles = new String[2];

    private int landlordId;
    private List<String> mTitleDataList;

    public int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusBarCompat.translucentStatusBar(this, false);
        //修改状态栏字体颜色
        StatusBarUtil.setImmersiveStatusBar(this, true);
        return R.layout.activity_introduction_of_landlord;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        //沉浸式
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
        }

        landlordId = getIntent().getIntExtra("landlordId", landlordId);
        if(mTitleDataList == null){
            mTitleDataList = new ArrayList<>();
        } else {
            mTitleDataList.clear();
        }
        getHomeResourceList();
    }

    @Override
    public void initData() {
        mLlytHeaderToolbar.post(new Runnable() {
            @Override
            public void run() {
                dealWithViewPager();
            }
        });
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            int lastScrollY = 0;
            int h = DensityUtil.dp2px(170);
            int color = ContextCompat.getColor(getApplicationContext(), R.color.white) & 0x00ffffff;

            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.e(">>>>>>>>>>>>>>", scrollY + "");
                int[] location = new int[2];
                magicIndicator.getLocationOnScreen(location);
                int yPosition = location[1];
                if (yPosition < toolBarPositionY) {
                    magicIndicatorTitle.setVisibility(View.VISIBLE);
                    scrollView.setNeedScroll(false);
                } else {
                    magicIndicatorTitle.setVisibility(View.GONE);
                    scrollView.setNeedScroll(true);

                }

                if (lastScrollY < h) {
                    scrollY = Math.min(h, scrollY);
                    mScrollY = scrollY > h ? h : scrollY;
                    //    buttonBarLayout.setAlpha(1f * mScrollY / h);
                    mLlytHeaderToolbar.setBackgroundColor(((255 * mScrollY / h) << 24) | color);
                    // mLlytHeaderBg.setTranslationY(mOffset - mScrollY);
                }
                if (scrollY == 0) {
                    //ivBack.setImageResource(R.drawable.back_white);
                    // ivMenu.setImageResource(R.drawable.icon_menu_white);
                } else {
                    // ivBack.setImageResource(R.drawable.back_black);
                    // ivMenu.setImageResource(R.drawable.icon_menu_black);
                }

                lastScrollY = scrollY;
            }
        });
        //buttonBarLayout.setAlpha(0);
        mLlytHeaderToolbar.setBackgroundColor(0);

        viewPager.setAdapter(new ComFragmentAdapter(getSupportFragmentManager(), getFragments()));
        viewPager.setOffscreenPageLimit(10);

    }

    private void dealWithViewPager() {
        toolBarPositionY = mLlytHeaderToolbar.getHeight();
        ViewGroup.LayoutParams params = viewPager.getLayoutParams();
        params.height = ScreenUtil.getScreenHeightPx(getApplicationContext()) - toolBarPositionY - magicIndicator.getHeight() + 1;
        viewPager.setLayoutParams(params);
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    private List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new HouseResourceFragment().newInstance(landlordId));
        fragments.add(new HomestayCommentFragment());
        return fragments;
    }

    private void initMagicIndicator() {
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setScrollPivotX(0.65f);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mTitleDataList == null ? 0 : mTitleDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorFlipPagerTitleView(context);
                simplePagerTitleView.setText(mTitleDataList.get(index));
                simplePagerTitleView.setNormalColor(ContextCompat.getColor(IntroductionOfLandlordActivity.this, R.color.color_text_gray24));
                simplePagerTitleView.setSelectedColor(ContextCompat.getColor(IntroductionOfLandlordActivity.this, R.color.black));
                simplePagerTitleView.setTextSize(16);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        scrollView.scrollTo(0, 2835);
                        viewPager.setCurrentItem(index, false);
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
                indicator.setColors(ContextCompat.getColor(IntroductionOfLandlordActivity.this, R.color.color_high_light_green));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);
    }

    private void initMagicIndicatorTitle() {
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setScrollPivotX(0.65f);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mTitleDataList == null ? 0 : mTitleDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorFlipPagerTitleView(context);
                simplePagerTitleView.setText(mTitleDataList.get(index));
                simplePagerTitleView.setNormalColor(ContextCompat.getColor(IntroductionOfLandlordActivity.this, R.color.color_text_gray24));
                simplePagerTitleView.setSelectedColor(ContextCompat.getColor(IntroductionOfLandlordActivity.this, R.color.black));
                simplePagerTitleView.setTextSize(16);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //int[] location = new int[2];
                        //magicIndicator.getLocationOnScreen(location);
                        scrollView.scrollTo(0, 2835);
                        int hight = mCollapsingToolbarLayout.getHeight();
                       // System.out.print(hight + "");
                        viewPager.setCurrentItem(index, false);
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
                indicator.setColors(ContextCompat.getColor(IntroductionOfLandlordActivity.this, R.color.color_high_light_green));
                return indicator;
            }
        });
        magicIndicatorTitle.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicatorTitle, viewPager);
    }

    @Override
    public void getHomestayHomePageResult(BackResult<ScenicSpotHomePageResponse> res) {

    }

    @Override
    public void findHomestayByCateResult(BackResult<ScenicSpotResponse> res) {

    }

    @Override
    public void getHomestayBangDanRankingResult(BackResult<MchBangDanRankingResponse> res) {

    }

    @Override
    public void mchCollectionResult(BackResult<MchCollectionResponse> res) {

    }

    @Override
    public void getMchHomestayDetailResult(BackResult<MchHomestayDetailsResponse> res) {

    }

    @Override
    public void queryMchCouponListResult(BackResult<List<MchCouponResponse>> res) {

    }

    @Override
    public void getCouponResult(BackResult<CouponsGetBean> res) {

    }

    @Override
    public void getLandlordHomePageResult(BackResult<LandlordDetailResonse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    LandlordDetailResonse landlordDetailResonse = res.getData();
                    LandlordBean landlord = landlordDetailResonse.getLandlord();
                    int bookingSuccessRate = landlord.getBookingSuccess();
                    homestayRoomNum = landlord.getHomestayRoomNum();
                    mTitles[0] = "房源("+homestayRoomNum+")";
                    mTitles[1] = "评价";
                    mTitleDataList = Arrays.asList(mTitles);
                    initMagicIndicator();
                    initMagicIndicatorTitle();
                    int confirmTime = landlord.getConfirmTime();
                    int score = landlord.getScore();
                    String motto = landlord.getMotto();
                    mTvScheduledSuccessRate.setText(bookingSuccessRate + "%");
                    mTvAverageConfirmationTime.setText(confirmTime + "分钟");
                    mTvHouseResoucesNum.setText(String.valueOf(homestayRoomNum));
                    mTvOverallScore.setText(score + "分");
                    if (!TextUtils.isEmpty(motto)) {
                        mTvMotto.setText(motto);
                        mLlytMotto.setVisibility(View.VISIBLE);
                    } else {
                        mLlytMotto.setVisibility(View.GONE);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(IntroductionOfLandlordActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getLandlordHouseResourceListResult(BackResult<HouseResouceResponse> res) {

    }

    @OnClick({R.id.img_back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:

                IntroductionOfLandlordActivity.this.finish();

                break;
            default:
                break;
        }
    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(IntroductionOfLandlordActivity.this, Constants.getResultMsg(msg));
    }

    //房源信息
    public void getHomeResourceList() {
        if (validateInternet()) {

            mPresenter.getLandlordHomePage(landlordId);
        }
    }
}
