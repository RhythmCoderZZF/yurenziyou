package com.nbhysj.coupon.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.ConscienceRecommendationListAdapter;
import com.nbhysj.coupon.adapter.FlashSaleGrabAtOnceAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.LimitedTimeSaleContract;
import com.nbhysj.coupon.fragment.ConscienceRecommendationListFragment;
import com.nbhysj.coupon.model.LimitedTimeSaleModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.ConscienceRecommendationBean;
import com.nbhysj.coupon.model.response.LimitedTimeSalePageBean;
import com.nbhysj.coupon.presenter.LimitedTimeSalePresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author hysj created at 2019/09/30.
 * description:商城限时特卖详情
 */
public class ShoppingMallLimitedSaleDetailActivity extends BaseActivity<LimitedTimeSalePresenter, LimitedTimeSaleModel> implements LimitedTimeSaleContract.View {

    //热门景点
    @BindView(R.id.tv_hot_scenic_spot)
    TextView mTvHotScenicSpot;
    //组合推荐
    @BindView(R.id.tv_group_recommend)
    TextView mTvGroupRecommend;
    //酒店精选
    @BindView(R.id.tv_hotel_selection)
    TextView mTvHotelSelection;
    //互动精选
    @BindView(R.id.tv_recreation_selection)
    TextView mTvRecreationSelection;
    //限时特卖
    @BindView(R.id.rv_flash_sale_immediate_robbery)
    RecyclerView mRvFlashSaleImmediateRobbery;
    //附近推荐
    @BindView(R.id.rv_nearby_recommend)
    RecyclerView mRvNearByRecommend;
    private String[] mTitles = new String[]{"热门景点", "组合推荐", "酒店精选", "互动精选"};
    private List<String> mDataList = Arrays.asList(mTitles);
    private FlashSaleGrabAtOnceAdapter flashSaleGrabAtOnceAdapter;
    //良心推荐
    LimitedTimeSalePageBean.RecommendEntity recommendEntity;
    List<Fragment> fragments = null;
    List<ConscienceRecommendationBean> conscienceRecommendationList;
    ConscienceRecommendationListAdapter conscienceRecommendationListAdapter;
    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_shopping_mall_special_sale;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        fragments = new ArrayList<>();
        if(conscienceRecommendationList == null)
        {
            conscienceRecommendationList = new ArrayList<>();
        } else {
            conscienceRecommendationList.clear();
        }

        showProgressDialog(ShoppingMallLimitedSaleDetailActivity.this);
        getLimitedTimeSalePage();
       /* fragments = new ArrayList<>();
        List<Fragment> fragments1 = getSupportFragmentManager().getFragments();
        if (fragments1 != null && fragments1.size() > 1) {

            localFoodFragment = (LocalFoodFragment) fragments1.get(0);
            interactiveSelectionFragment = (InteractiveSelectionFragment) fragments1.get(1);
            hotelSelectionFragment = (HotelSelectionFragment) fragments1.get(2);

        } else {

            localFoodFragment = new LocalFoodFragment();
            interactiveSelectionFragment = new InteractiveSelectionFragment();
            hotelSelectionFragment = new HotelSelectionFragment();

        }

        fragments.add(localFoodFragment);
        fragments.add(interactiveSelectionFragment);
        fragments.add(hotelSelectionFragment);

        tabLayout.setViewPager(viewpager, titles, ShoppingMallSpecialSaleActivity.this, fragments);

        LinearLayoutManager hotelReputationLinearLayout = new LinearLayoutManager(ShoppingMallSpecialSaleActivity.this);
        hotelReputationLinearLayout.setOrientation(hotelReputationLinearLayout.HORIZONTAL);
        mRvFlashSaleImmediateRobbery.setLayoutManager(hotelReputationLinearLayout);
        flashSaleGrabAtOnceAdapter = new FlashSaleGrabAtOnceAdapter(ShoppingMallSpecialSaleActivity.this);
        mRvFlashSaleImmediateRobbery.setAdapter(flashSaleGrabAtOnceAdapter);*/

        LinearLayoutManager hotelReputationLinearLayout = new LinearLayoutManager(ShoppingMallLimitedSaleDetailActivity.this);
        hotelReputationLinearLayout.setOrientation(hotelReputationLinearLayout.HORIZONTAL);
        mRvFlashSaleImmediateRobbery.setLayoutManager(hotelReputationLinearLayout);
        flashSaleGrabAtOnceAdapter = new FlashSaleGrabAtOnceAdapter(ShoppingMallLimitedSaleDetailActivity.this);
        mRvFlashSaleImmediateRobbery.setAdapter(flashSaleGrabAtOnceAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShoppingMallLimitedSaleDetailActivity.this);
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        mRvNearByRecommend.setLayoutManager(linearLayoutManager);
        conscienceRecommendationListAdapter = new ConscienceRecommendationListAdapter(ShoppingMallLimitedSaleDetailActivity.this);
        conscienceRecommendationListAdapter.setConscienceRecommendationList(conscienceRecommendationList);
        mRvNearByRecommend.setAdapter(conscienceRecommendationListAdapter);

    }

    @Override
    public void initData() {
       /* mLlytHeaderToolbar.post(new Runnable() {
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
        mLlytHeaderToolbar.setBackgroundColor(0);*/


    }
/*
    private void dealWithViewPager() {
        toolBarPositionY = mLlytHeaderToolbar.getHeight();
        ViewGroup.LayoutParams params = viewPager.getLayoutParams();
        params.height = ScreenUtil.getScreenHeightPx(getApplicationContext())  - magicIndicator.getHeight() + 1;
        viewPager.setLayoutParams(params);
    }*/
    private List<Fragment> getFragments() {

        fragments.clear();
        for(int i = 0; i < 4;i++)
        {
            ConscienceRecommendationListFragment recommendationListFragment = new ConscienceRecommendationListFragment();
            if(i == 0){

                List<ConscienceRecommendationBean> conscienceRecommendationList = recommendEntity.getScenic();
                recommendationListFragment.setConscienceRecommendationList(conscienceRecommendationList);
            } else if(i == 1){

                List<ConscienceRecommendationBean> conscienceRecommendationList = recommendEntity.getGroup();
                recommendationListFragment.setConscienceRecommendationList(conscienceRecommendationList);

            } else if(i == 2){

                List<ConscienceRecommendationBean> conscienceRecommendationList = recommendEntity.getHotel();
                recommendationListFragment.setConscienceRecommendationList(conscienceRecommendationList);

            }else if(i == 3){

                List<ConscienceRecommendationBean> conscienceRecommendationList = recommendEntity.getRecreation();
                recommendationListFragment.setConscienceRecommendationList(conscienceRecommendationList);
            }

            fragments.add(recommendationListFragment);
        }
        return fragments;
    }

  /*  private void initMagicIndicator() {
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
                simplePagerTitleView.setNormalColor(ContextCompat.getColor(ShoppingMallSpecialSaleActivity.this, R.color.color_text_gray24));
                simplePagerTitleView.setSelectedColor(ContextCompat.getColor(ShoppingMallSpecialSaleActivity.this, R.color.black));
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
                indicator.setColors(ContextCompat.getColor(ShoppingMallSpecialSaleActivity.this, R.color.color_high_light_green));
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
                simplePagerTitleView.setNormalColor(ContextCompat.getColor(ShoppingMallSpecialSaleActivity.this, R.color.color_text_gray24));
                simplePagerTitleView.setSelectedColor(ContextCompat.getColor(ShoppingMallSpecialSaleActivity.this, R.color.black));
                simplePagerTitleView.setTextSize(16);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //int[] location = new int[2];
                        //magicIndicator.getLocationOnScreen(location);
                        scrollView.scrollTo(0, 2835);
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
                indicator.setColors(ContextCompat.getColor(ShoppingMallSpecialSaleActivity.this, R.color.color_high_light_green));
                return indicator;
            }
        });
        magicIndicatorTitle.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicatorTitle, viewPager);
    }*/
    @Override
    public void initPresenter() {

       mPresenter.setVM(this,mModel);
    }

    @Override
    public void getLimitedTimeSalePageResult(BackResult<LimitedTimeSalePageBean> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    LimitedTimeSalePageBean limitedTimeSalePageBean = res.getData();
                    recommendEntity = limitedTimeSalePageBean.getRecommend();
                    List<ConscienceRecommendationBean> limitedTimeSalePageList = recommendEntity.getScenic();
                    conscienceRecommendationListAdapter.setConscienceRecommendationList(limitedTimeSalePageList);
                    conscienceRecommendationListAdapter.notifyDataSetChanged();
                   /* viewPager.setAdapter(new ComFragmentAdapter(getSupportFragmentManager(), getFragments()));
                    viewPager.setOffscreenPageLimit(10);
                    initMagicIndicator();
                    initMagicIndicatorTitle();*/

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(ShoppingMallLimitedSaleDetailActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(ShoppingMallLimitedSaleDetailActivity.this, Constants.getResultMsg(msg));
    }

    @OnClick({R.id.img_back})
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.img_back:

                ShoppingMallLimitedSaleDetailActivity.this.finish();

                break;
            default:
                break;

        }
    }

    @OnClick({R.id.tv_hot_scenic_spot, R.id.tv_group_recommend, R.id.tv_hotel_selection, R.id.tv_recreation_selection})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.tv_hot_scenic_spot:
                mTvHotScenicSpot.setTextColor(getResources().getColor(R.color.txt_font_black2));
                mTvHotScenicSpot.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
                mTvGroupRecommend.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvHotelSelection.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvRecreationSelection.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvGroupRecommend.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mTvHotelSelection.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mTvRecreationSelection.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);

                conscienceRecommendationList.clear();
                List<ConscienceRecommendationBean> hotScenicList = recommendEntity.getScenic();
                conscienceRecommendationList.addAll(hotScenicList);
                conscienceRecommendationListAdapter.setConscienceRecommendationList(conscienceRecommendationList);
                mRvNearByRecommend.setAdapter(conscienceRecommendationListAdapter);
                //  scenicSpotsListAdapter.notifyDataSetChanged();

                break;
            case R.id.tv_group_recommend:
                mTvGroupRecommend.setTextColor(getResources().getColor(R.color.txt_font_black2));
                mTvGroupRecommend.setTypeface(Typeface.DEFAULT, Typeface.BOLD);

                mTvHotScenicSpot.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvHotelSelection.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvRecreationSelection.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvHotScenicSpot.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mTvHotelSelection.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mTvRecreationSelection.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);

                conscienceRecommendationList.clear();
                List<ConscienceRecommendationBean> groupRecommendationList = recommendEntity.getGroup();
                conscienceRecommendationList.addAll(groupRecommendationList);
                conscienceRecommendationListAdapter.setConscienceRecommendationList(conscienceRecommendationList);
                mRvNearByRecommend.setAdapter(conscienceRecommendationListAdapter);

                break;
            case R.id.tv_hotel_selection:
                mTvHotelSelection.setTextColor(getResources().getColor(R.color.txt_font_black2));
                mTvHotScenicSpot.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvGroupRecommend.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvRecreationSelection.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvHotelSelection.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
                mTvHotScenicSpot.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mTvGroupRecommend.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mTvRecreationSelection.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);

                conscienceRecommendationList.clear();
                List<ConscienceRecommendationBean> hotelRecommendationList = recommendEntity.getHotel();
                conscienceRecommendationList.addAll(hotelRecommendationList);
                conscienceRecommendationListAdapter.setConscienceRecommendationList(conscienceRecommendationList);
                mRvNearByRecommend.setAdapter(conscienceRecommendationListAdapter);
                break;
            case R.id.tv_recreation_selection:
                mTvRecreationSelection.setTextColor(getResources().getColor(R.color.txt_font_black2));
                mTvHotelSelection.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvHotScenicSpot.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvGroupRecommend.setTextColor(getResources().getColor(R.color.color_text_gray20));

                mTvRecreationSelection.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
                mTvHotelSelection.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mTvHotScenicSpot.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mTvGroupRecommend.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);

                conscienceRecommendationList.clear();
                List<ConscienceRecommendationBean> recreationRecommendationList = recommendEntity.getRecreation();
                conscienceRecommendationList.addAll(recreationRecommendationList);
                conscienceRecommendationListAdapter.setConscienceRecommendationList(conscienceRecommendationList);
                mRvNearByRecommend.setAdapter(conscienceRecommendationListAdapter);
                break;
            default:
                break;
        }
    }

    public void getLimitedTimeSalePage(){

        if(validateInternet()){

            mPresenter.getLimitedTimeSalePage();
        }
    }
}
