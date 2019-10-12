package com.nbhysj.coupon.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.ComFragmentAdapter;
import com.nbhysj.coupon.adapter.DeliciousFoodSectionAdapter;
import com.nbhysj.coupon.adapter.DestinationScenicSpotsBannerAdapter;
import com.nbhysj.coupon.adapter.FineFoodListAdapter;
import com.nbhysj.coupon.adapter.InteractiveSelectionAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.DestinationContract;
import com.nbhysj.coupon.fragment.HotelSelectionFragment;
import com.nbhysj.coupon.fragment.InteractiveSelectionFragment;
import com.nbhysj.coupon.fragment.LocalFoodFragment;
import com.nbhysj.coupon.fragment.NearbyFragment;
import com.nbhysj.coupon.fragment.RecommendFragment;
import com.nbhysj.coupon.fragment.ScenicSpotFragment;
import com.nbhysj.coupon.model.DestinationModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BannerBean;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.BaseTagsBean;
import com.nbhysj.coupon.model.response.DestinationResponse;
import com.nbhysj.coupon.model.response.HotScenicSpotResponse;
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.presenter.DestinationPresenter;
import com.nbhysj.coupon.systembar.StatusBarCompat;
import com.nbhysj.coupon.systembar.StatusBarUtil;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.ScreenUtil;
import com.nbhysj.coupon.view.BannerView;
import com.nbhysj.coupon.view.ColorFlipPagerTitleView;
import com.nbhysj.coupon.view.DestinationBannerView;
import com.nbhysj.coupon.view.JudgeNestedScrollView;
import com.nbhysj.coupon.view.ScenicSpotDetailBannerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
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

public class ScenicSpotDestinationActivity extends BaseActivity<DestinationPresenter, DestinationModel> implements DestinationContract.View {
    public static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.img_destination)
    ImageView mImgDestination;
    @BindView(R.id.rv_scenic_spot_banner)
    RecyclerView mRvScenicSpotBanner;
    @BindView(R.id.banner_homestay)
    DestinationBannerView mBannerViewHomestay;
    @BindView(R.id.collapse)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.toolbar_space)
    View mToolbarSpace;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.llyt_header)
    LinearLayout mLlytHeaderToolbar;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.scrollView)
    JudgeNestedScrollView scrollView;
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.magic_indicator_title)
    MagicIndicator magicIndicatorTitle;
    //名宿名字
    @BindView(R.id.tv_hotel_homestay_name)
    TextView mTvHotelHomestayName;
    //名宿标签
    @BindView(R.id.tv_hotel_homestay_tag)
    TextView mTvHotelHomestayTag;
    //互动精选
    @BindView(R.id.rv_interactive_selection)
    RecyclerView mRvInteractiveSelection;
    //美食推荐
    @BindView(R.id.rv_delicious_food_recommendation)
    RecyclerView mRvDeliciousFoodRecommendation;
    //攻略1标题
    @BindView(R.id.tv_strategys_one_title)
    TextView mTvStrategysOneTitle;
    //攻略1描述
    @BindView(R.id.tv_strategys_one_des)
    TextView mTvStrategysOneDes;
    //攻略1图片
    @BindView(R.id.img_strategys_one)
    ImageView mImageStrategysOne;
    //攻略2标题
    @BindView(R.id.tv_strategys_two_title)
    TextView mTvStrategysTwoTitle;
    //攻略2描述
    @BindView(R.id.tv_strategys_two_des)
    TextView mTvStrategysTwoDes;
    //攻略2图片
    @BindView(R.id.img_strategys_two)
    ImageView mImageStrategysTwo;
    //欢迎来到
    @BindView(R.id.tv_welcome_to_location)
    TextView mTvWelcomeToLocation;
    //位置
    @BindView(R.id.tv_location)
    TextView mTvLocation;

    int toolBarPositionY = 0;
    private int mOffset = 0;
    private int mScrollY = 0;
    private String[] mTitles = new String[]{"热门景点", "当地美食", "酒店精选", "互动精选"};
    private List<String> mDataList = Arrays.asList(mTitles);
    List<MchTypeBean> scenicSpotBeanList;
    private List<ImageView> viewList;
    //酒店民宿
    private List<DestinationResponse.HomestaysEntity> homestaysEntityList;
    //精选互动
    private List<MchTypeBean> interactiveSelectionList;
    //美食精选
    private List<MchTypeBean> fineFoodList;
    DestinationScenicSpotsBannerAdapter destinationScenicSpotsBannerAdapter;
    //酒店精选
    InteractiveSelectionAdapter interactiveSelectionAdapter;
    //美食精选
    DeliciousFoodSectionAdapter deliciousFoodSectionAdapter;
    StringBuffer stringBuffer = new StringBuffer();

    //城市ID
    private String cityId = String.valueOf(Constants.CITY_CODE);
    @Override
    public int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusBarCompat.translucentStatusBar(this, false);
        //修改状态栏字体颜色
        StatusBarUtil.setImmersiveStatusBar(this, true);
        return R.layout.activity_scenic_spot_destination;
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }


    @Override
    public void initData() {
        cityId = getIntent().getStringExtra("cityId");
        getDestinationHomePage();

        if (scenicSpotBeanList == null) {

            scenicSpotBeanList = new ArrayList<>();
        } else {
            scenicSpotBeanList.clear();
        }

        if (homestaysEntityList == null) {

            homestaysEntityList = new ArrayList<>();
        } else {

            homestaysEntityList.clear();
        }

        if (viewList == null) {

            viewList = new ArrayList<>();
        } else {
            viewList.clear();
        }

        if (interactiveSelectionList == null) {

            interactiveSelectionList = new ArrayList<>();
        } else {
            interactiveSelectionList.clear();
        }

        if (fineFoodList == null) {

            fineFoodList = new ArrayList<>();
        } else {
            fineFoodList.clear();
        }
        //目的地景点切换
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ScenicSpotDestinationActivity.this);
        linearLayoutManager.setOrientation(linearLayoutManager.HORIZONTAL);
        mRvScenicSpotBanner.setLayoutManager(linearLayoutManager);
        destinationScenicSpotsBannerAdapter = new DestinationScenicSpotsBannerAdapter(ScenicSpotDestinationActivity.this);
        destinationScenicSpotsBannerAdapter.setPopularScenicSpotsList(scenicSpotBeanList);
        mRvScenicSpotBanner.setAdapter(destinationScenicSpotsBannerAdapter);


        LinearLayoutManager interactiveSelectionLinearLayoutManager = new LinearLayoutManager(ScenicSpotDestinationActivity.this);
        interactiveSelectionLinearLayoutManager.setOrientation(interactiveSelectionLinearLayoutManager.HORIZONTAL);
        mRvInteractiveSelection.setLayoutManager(interactiveSelectionLinearLayoutManager);
        interactiveSelectionAdapter = new InteractiveSelectionAdapter(ScenicSpotDestinationActivity.this);
        interactiveSelectionAdapter.setInteractiveSelectionList(interactiveSelectionList);
        mRvInteractiveSelection.setAdapter(interactiveSelectionAdapter);

        LinearLayoutManager fineFoodLayoutManager = new LinearLayoutManager(ScenicSpotDestinationActivity.this);
        fineFoodLayoutManager.setOrientation(fineFoodLayoutManager.HORIZONTAL);
        mRvDeliciousFoodRecommendation.setLayoutManager(fineFoodLayoutManager);
        deliciousFoodSectionAdapter = new DeliciousFoodSectionAdapter(ScenicSpotDestinationActivity.this);
        deliciousFoodSectionAdapter.setDeliciousFoodRecommendList(fineFoodList);
        mRvDeliciousFoodRecommendation.setAdapter(deliciousFoodSectionAdapter);
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

        //StatusBarUtil.immersive(this);
        //StatusBarUtil.setPaddingSmart(this, toolbar);
      /*  refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onHeaderPulling(RefreshHeader header, float percent, int offset, int bottomHeight, int extendHeight) {

            }

            @Override
            public void onHeaderReleasing(RefreshHeader header, float percent, int offset, int bottomHeight, int extendHeight) {

            }
        });*/

        mSmartRefreshLayout.setOnMultiPurposeListener(new OnMultiPurposeListener() {
            @Override
            public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {
                mOffset = offset / 2;
                //   mLlytHeaderBg.setTranslationY(mOffset - mScrollY);
                mLlytHeaderToolbar.setAlpha(1 - Math.min(percent, 1));

            }

            @Override
            public void onHeaderReleased(RefreshHeader header, int headerHeight, int maxDragHeight) {

            }

            @Override
            public void onHeaderStartAnimator(RefreshHeader header, int headerHeight, int maxDragHeight) {

            }

            @Override
            public void onHeaderFinish(RefreshHeader header, boolean success) {

            }

            @Override
            public void onFooterMoving(RefreshFooter footer, boolean isDragging, float percent, int offset, int footerHeight, int maxDragHeight) {

            }

            @Override
            public void onFooterReleased(RefreshFooter footer, int footerHeight, int maxDragHeight) {

            }

            @Override
            public void onFooterStartAnimator(RefreshFooter footer, int footerHeight, int maxDragHeight) {

            }

            @Override
            public void onFooterFinish(RefreshFooter footer, boolean success) {

            }

            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

            }

            @Override
            public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {

            }
        });

        //判断是否是华为手机并且是否有虚拟导航键
//        if (DeviceUtil.isHUAWEI() && DeviceUtil.checkDeviceHasNavigationBar(this.getApplicationContext())) {
//            getContentResolver().registerContentObserver(Settings.System.getUriFor
//                    ("navigationbar_is_min"), true, mNavigationStatusObserver);
//        }
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
        initMagicIndicator();
        initMagicIndicatorTitle();
    }

    private void dealWithViewPager() {
        toolBarPositionY = mLlytHeaderToolbar.getHeight();
        ViewGroup.LayoutParams params = viewPager.getLayoutParams();
        params.height = ScreenUtil.getScreenHeightPx(getApplicationContext()) - toolBarPositionY - magicIndicator.getHeight() + 1;
        viewPager.setLayoutParams(params);
    }

    private List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new ScenicSpotFragment());
        fragments.add(new LocalFoodFragment());
        fragments.add(new HotelSelectionFragment());
        fragments.add(new InteractiveSelectionFragment());
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
                simplePagerTitleView.setNormalColor(ContextCompat.getColor(ScenicSpotDestinationActivity.this, R.color.color_text_gray24));
                simplePagerTitleView.setSelectedColor(ContextCompat.getColor(ScenicSpotDestinationActivity.this, R.color.black));
                simplePagerTitleView.setTextSize(16);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        scrollView.smoothScrollTo(0, 2835);
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
                indicator.setColors(ContextCompat.getColor(ScenicSpotDestinationActivity.this, R.color.color_high_light_green));
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
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorFlipPagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setNormalColor(ContextCompat.getColor(ScenicSpotDestinationActivity.this, R.color.color_text_gray24));
                simplePagerTitleView.setSelectedColor(ContextCompat.getColor(ScenicSpotDestinationActivity.this, R.color.black));
                simplePagerTitleView.setTextSize(16);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //int[] location = new int[2];
                        //magicIndicator.getLocationOnScreen(location);
                        scrollView.smoothScrollTo(0, 2835);
                        int hight = mCollapsingToolbarLayout.getHeight();
                        System.out.print(hight + "");
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
                indicator.setColors(ContextCompat.getColor(ScenicSpotDestinationActivity.this, R.color.color_high_light_green));
                return indicator;
            }
        });
        magicIndicatorTitle.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicatorTitle, viewPager);

    }

    @Override
    public void findMchBycityNameResult(BackResult<HotScenicSpotResponse> res) {

    }

    @Override
    public void getDestinationHomePageResult(BackResult<DestinationResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    DestinationResponse destinationResponse = res.getData();
                    List<BannerBean> bannerBeanList = destinationResponse.getBanner();
                    String province = destinationResponse.getProvince();
                    String city = destinationResponse.getCity();
                    String county = destinationResponse.getCounty();

                    mTvWelcomeToLocation.setText("欢迎来到" + city);
                    mTvLocation.setText(province + county + city);
                    scenicSpotBeanList = destinationResponse.getMches();//景点
                    List<MchTypeBean> strategysList = destinationResponse.getStrategys();
                    if (strategysList != null) {
                        MchTypeBean strategysOne = strategysList.get(0);
                        MchTypeBean strategysTwo = strategysList.get(1);
                        String strategysOneTitle = strategysOne.getDataName();
                        String strategysOneIntro = strategysOne.getIntro();
                        String strategysOnePhotoUrl = strategysOne.getPhoto();
                        mTvStrategysOneTitle.setText(strategysOneTitle);
                        mTvStrategysOneDes.setText(strategysOneIntro);
                        GlideUtil.loadImage(ScenicSpotDestinationActivity.this, strategysOnePhotoUrl, mImageStrategysOne);

                        if (strategysList.size() > 1) {
                            String strategysTwoTitle = strategysTwo.getDataName();
                            String strategysTwoIntro = strategysTwo.getIntro();
                            String strategysTwoPhotoUrl = strategysTwo.getPhoto();
                            mTvStrategysTwoTitle.setText(strategysTwoTitle);
                            mTvStrategysTwoDes.setText(strategysTwoIntro);
                            GlideUtil.loadImage(ScenicSpotDestinationActivity.this, strategysTwoPhotoUrl, mImageStrategysTwo);
                        }
                    }

                    interactiveSelectionList = destinationResponse.getInteraction();
                    fineFoodList = destinationResponse.getFood();
                    List<DestinationResponse.HomestaysEntity> homestaysEntityList = destinationResponse.getHomestays();
                    BannerBean bannerBean = bannerBeanList.get(0);
                    String photoUrl = bannerBean.getPhoto();
                    GlideUtil.loadImage(ScenicSpotDestinationActivity.this, photoUrl, mImgDestination);

                    destinationScenicSpotsBannerAdapter.setPopularScenicSpotsList(scenicSpotBeanList);
                    destinationScenicSpotsBannerAdapter.notifyDataSetChanged();

                    if (homestaysEntityList != null) {


                        if (homestaysEntityList.size() > 0) {

                            for (int i = 0; i < homestaysEntityList.size(); i++) {
                                ImageView image = new ImageView(ScenicSpotDestinationActivity.this);
                                image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                                //设置显示格式
                                image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                                viewList.add(image);
                            }

                            String mchName = homestaysEntityList.get(0).getMchName();
                            List<BaseTagsBean> tagsList = homestaysEntityList.get(0).getTags();
                            if (!TextUtils.isEmpty(mchName)) {
                                mTvHotelHomestayName.setText(mchName);
                            } else {
                                mTvHotelHomestayName.setText("");
                            }
                            for (BaseTagsBean tagsBean : tagsList) {
                                String title = tagsBean.getTitle();
                                stringBuffer.append(title);
                                stringBuffer.append("/");
                            }

                            String tagStr = stringBuffer.toString();
                            if (tagStr.length() > 0) {
                                String tag = tagStr.substring(0, tagStr.length() - 1);
                                mTvHotelHomestayTag.setText(tag);
                            }

                            mBannerViewHomestay.setViewList(ScenicSpotDestinationActivity.this, viewList, homestaysEntityList, new DestinationBannerView.ScenicSpotDetailBannerViewListener() {
                                @Override
                                public void setScenicSpotDetailBannerViewListener(int curPos) {

                                    stringBuffer.setLength(0);
                                    DestinationResponse.HomestaysEntity homestaysEntity = homestaysEntityList.get(curPos % homestaysEntityList.size());
                                    String mchName = homestaysEntity.getMchName();
                                    List<BaseTagsBean> tagsList = homestaysEntity.getTags();

                                    homestaySwitching(mchName, tagsList);
                                }
                            });
                        }
                    }

                    if (interactiveSelectionList != null) {
                        interactiveSelectionAdapter.setInteractiveSelectionList(interactiveSelectionList);
                        interactiveSelectionAdapter.notifyDataSetChanged();
                    }

                    if (fineFoodList != null) {
                        deliciousFoodSectionAdapter.setDeliciousFoodRecommendList(fineFoodList);
                        deliciousFoodSectionAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:break;
        }

    }

    public void homestaySwitching(String mchName, List<BaseTagsBean> tagsList) {

        if (!TextUtils.isEmpty(mchName)) {
            mTvHotelHomestayName.setText(mchName);
        } else {
            mTvHotelHomestayName.setText("");
        }

        for (BaseTagsBean tagsBean : tagsList) {
            String title = tagsBean.getTitle();
            stringBuffer.append(title);
            stringBuffer.append("/");
        }

        String tagStr = stringBuffer.toString();
        if (tagStr.length() > 0) {
            String tag = tagStr.substring(0, tagStr.length() - 1);
            mTvHotelHomestayTag.setText(tag);
        }
    }

    @Override
    public void showMsg(String msg) {
        dismissProgressDialog();
        showToast(ScenicSpotDestinationActivity.this, Constants.getResultMsg(msg));
    }

    /**
     * 处理华为虚拟键显示隐藏问题导致屏幕高度变化，ViewPager的高度也需要重新测量
     */
//    private void dealWithHuaWei() {
//        flActivity.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                dealWithViewPager();
//                flActivity.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//            }
//        });
//    }
    @OnClick({R.id.rlyt_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rlyt_back:
                ScenicSpotDestinationActivity.this.finish();
                break;
            default:
                break;
        }
    }

    public void getDestinationHomePage() {

        if (validateInternet()) {

            if (!TextUtils.isEmpty(cityId))
            {
                showProgressDialog(ScenicSpotDestinationActivity.this);
                mPresenter.getDestinationHomePage(Integer.parseInt(cityId));
            }
        }
    }
}
