package com.nbhysj.coupon.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.ComFragmentAdapter;
import com.nbhysj.coupon.adapter.ConscienceRecommendationListAdapter;
import com.nbhysj.coupon.adapter.FlashSaleGrabAtOnceAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.LimitedTimeSaleContract;
import com.nbhysj.coupon.fragment.ConscienceRecommendationListFragment;
import com.nbhysj.coupon.fragment.FollowFragment;
import com.nbhysj.coupon.fragment.HotelSelectionFragment;
import com.nbhysj.coupon.fragment.InteractiveSelectionFragment;
import com.nbhysj.coupon.fragment.LocalFoodFragment;
import com.nbhysj.coupon.fragment.ScenicSpotFragment;
import com.nbhysj.coupon.model.LimitedTimeSaleModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.ConscienceRecommendationBean;
import com.nbhysj.coupon.model.response.GoodsBean;
import com.nbhysj.coupon.model.response.GroupMchResponse;
import com.nbhysj.coupon.model.response.LimitedTimeSalePageBean;
import com.nbhysj.coupon.presenter.LimitedTimeSalePresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author hysj created at 2019/4/19.
 * description:商城特卖
 */
public class ShoppingMallSpecialSaleActivity extends BaseActivity<LimitedTimeSalePresenter, LimitedTimeSaleModel> implements LimitedTimeSaleContract.View {

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
    int toolBarPositionY = 0;
    private int mOffset = 0;
    private int mScrollY = 0;
    private String[] mTitles = new String[]{"热门景点", "组合推荐", "酒店精选", "互动精选"};
    private List<String> mDataList = Arrays.asList(mTitles);
    private FlashSaleGrabAtOnceAdapter flashSaleGrabAtOnceAdapter;
    //良心推荐
    LimitedTimeSalePageBean.RecommendEntity recommendEntity;
    List<Fragment> fragments = null;
    //良心推荐
    List<ConscienceRecommendationBean> conscienceRecommendationList;
    //限时特卖
    private List<GoodsBean> mLimitedSaleGoodsList;
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

        if(mLimitedSaleGoodsList == null){

            mLimitedSaleGoodsList = new ArrayList<>();
        } else {
            mLimitedSaleGoodsList.clear();
        }

        showProgressDialog(ShoppingMallSpecialSaleActivity.this);
        getLimitedTimeSalePage();

        LinearLayoutManager hotelReputationLinearLayout = new LinearLayoutManager(ShoppingMallSpecialSaleActivity.this);
        hotelReputationLinearLayout.setOrientation(hotelReputationLinearLayout.HORIZONTAL);
        mRvFlashSaleImmediateRobbery.setLayoutManager(hotelReputationLinearLayout);
        flashSaleGrabAtOnceAdapter = new FlashSaleGrabAtOnceAdapter(ShoppingMallSpecialSaleActivity.this);
        flashSaleGrabAtOnceAdapter.setLimitedTimeSaleGoodsList(mLimitedSaleGoodsList);
        mRvFlashSaleImmediateRobbery.setAdapter(flashSaleGrabAtOnceAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShoppingMallSpecialSaleActivity.this);
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        mRvNearByRecommend.setLayoutManager(linearLayoutManager);
        conscienceRecommendationListAdapter = new ConscienceRecommendationListAdapter(ShoppingMallSpecialSaleActivity.this);
        conscienceRecommendationListAdapter.setConscienceRecommendationList(conscienceRecommendationList);
        mRvNearByRecommend.setAdapter(conscienceRecommendationListAdapter);

    }

    @Override
    public void initData() {


    }

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
                    LimitedTimeSalePageBean.TimeLimitEntity timeLimitEntity = limitedTimeSalePageBean.getTimeLimit();
                    String title = timeLimitEntity.getTitle();
                    long startTime = timeLimitEntity.getStartTime();
                    long endTime = timeLimitEntity.getEndTime();
                    List<GoodsBean> limitedTimeSalegoodsList = timeLimitEntity.getGoods();
                    if(limitedTimeSalegoodsList != null)
                    {
                        flashSaleGrabAtOnceAdapter.setLimitedTimeSaleGoodsList(limitedTimeSalegoodsList);
                        flashSaleGrabAtOnceAdapter.notifyDataSetChanged();
                    }

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
                showToast(ShoppingMallSpecialSaleActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(ShoppingMallSpecialSaleActivity.this, Constants.getResultMsg(msg));
    }

    @OnClick({R.id.img_back})
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.img_back:

                ShoppingMallSpecialSaleActivity.this.finish();

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
