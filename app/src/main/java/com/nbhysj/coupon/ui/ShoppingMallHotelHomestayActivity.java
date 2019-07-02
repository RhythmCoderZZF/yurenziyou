package com.nbhysj.coupon.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.HotelHomestayAdapter;
import com.nbhysj.coupon.adapter.HotelHomestaySectionAdapter;
import com.nbhysj.coupon.adapter.ScenicSpotClassificationAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.HotelContract;
import com.nbhysj.coupon.model.HotelModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.ScenicBangDanRankingResponse;
import com.nbhysj.coupon.model.response.ScenicSpotBean;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.model.response.ScenicSpotResponse;
import com.nbhysj.coupon.presenter.HotelPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.view.StickyScrollView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/0/02
 * description：酒店名宿
 */
public class ShoppingMallHotelHomestayActivity extends BaseActivity<HotelPresenter, HotelModel> implements HotelContract.View {
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    //热门景点
    @BindView(R.id.rv_popular_scenic_spots)
    RecyclerView mRvPopularScenicSpots;
    //景点分类
    @BindView(R.id.rv_scenic_spot_classification)
    RecyclerView mRvScenicSpotClassification;
    //附近热门
    @BindView(R.id.tv_popular_nearby)
    TextView mTvPopularNearby;
    //综合排序
    @BindView(R.id.tv_comprehensive_sorting)
    TextView mTvComprehensiveSorting;
    //超值预定
    @BindView(R.id.tv_super_value_reservation)
    TextView mTvSuperValueReservation;
    //距离优先
    @BindView(R.id.tv_distance_first)
    TextView mTvDistanceFirst;
    @BindView(R.id.rlyt_back)
    RelativeLayout mRlytBack;
    @BindView(R.id.sticky_scrollview)
    StickyScrollView mStickyScrollView;
    @BindView(R.id.rv_hot_scenic_spot)
    RecyclerView mRvHotelHomestay;
    LinearLayoutManager scenicSpotsLinearLayoutManager;
    //景点栏目
    private HotelHomestaySectionAdapter hotelHomestaySectionAdapter;
    //景点分类
    private ScenicSpotClassificationAdapter scenicSpotClassificationAdapter;
    //酒店民宿适配器
    private HotelHomestayAdapter hotelHomestayAdapter;
    //景点中间分类
    List<ScenicSpotHomePageResponse.CateEntity> mCateEntityList;
    //景点栏目
    List<ScenicSpotBean> mScenicSpotHotList;
    //酒店民宿列表
    List<ScenicSpotBean> mScenicSpotList;
    //加载
    @BindView(R.id.llyt_progress_bar_loading)
    LinearLayout mLlytProgressBarLoading;
    @BindView(R.id.progressbar_load_more)
    ProgressBar mProgressBarLoadMore;
    @BindView(R.id.tv_load_more)
    TextView mTvLoadMore;

    private int mPage = 1;
    private int mPageSize = 10;
    int mTotalPageCount;
    //排序规则类型 FJ=附近热门 ZH=综合排序 默认FJ
    private String mSortStr = "FJ";

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_shopping_mall_scenic_spot;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        if (mCateEntityList == null) {

            mCateEntityList = new ArrayList<>();
        } else {
            mCateEntityList.clear();
        }
        if (mScenicSpotHotList == null) {

            mScenicSpotHotList = new ArrayList<>();
        } else {
            mScenicSpotHotList.clear();
        }
        if (mScenicSpotList == null) {

            mScenicSpotList = new ArrayList<>();
        } else {
            mScenicSpotList.clear();
        }

        showProgressDialog(ShoppingMallHotelHomestayActivity.this);
        getHotelHomestayHomePage();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShoppingMallHotelHomestayActivity.this);
        linearLayoutManager.setOrientation(linearLayoutManager.HORIZONTAL);
        mRvPopularScenicSpots.setLayoutManager(linearLayoutManager);
        hotelHomestaySectionAdapter = new HotelHomestaySectionAdapter(ShoppingMallHotelHomestayActivity.this);
        hotelHomestaySectionAdapter.setHotelHomestaySectionList(mScenicSpotHotList);
        mRvPopularScenicSpots.setAdapter(hotelHomestaySectionAdapter);

        //酒店民宿分类
        LinearLayoutManager scenicSpotClassificationLinearLayout = new LinearLayoutManager(ShoppingMallHotelHomestayActivity.this);
        scenicSpotClassificationLinearLayout.setOrientation(linearLayoutManager.HORIZONTAL);
        mRvScenicSpotClassification.setLayoutManager(scenicSpotClassificationLinearLayout);
        scenicSpotClassificationAdapter = new ScenicSpotClassificationAdapter(ShoppingMallHotelHomestayActivity.this);
        scenicSpotClassificationAdapter.setScenicSpotClassificationList(mCateEntityList);
        mRvScenicSpotClassification.setAdapter(scenicSpotClassificationAdapter);

        scenicSpotsLinearLayoutManager = new LinearLayoutManager(ShoppingMallHotelHomestayActivity.this);
        scenicSpotsLinearLayoutManager.setOrientation(scenicSpotsLinearLayoutManager.VERTICAL);
        mRvHotelHomestay.setLayoutManager(scenicSpotsLinearLayoutManager);
        hotelHomestayAdapter = new HotelHomestayAdapter(ShoppingMallHotelHomestayActivity.this);
        hotelHomestayAdapter.setHotelReputationList(mScenicSpotList);
        mRvHotelHomestay.setAdapter(hotelHomestayAdapter);

    }

    @Override
    public void initData() {
        mRlytBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ShoppingMallHotelHomestayActivity.this.finish();
            }
        });

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        mPage = 1;
                        mScenicSpotList.clear();
                        //scenicSpotsListAdapter.notifyDataSetChanged();
                        findHotelHomestayByCate();

                    }
                }, 100);
            }
        });

        mStickyScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {


                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    loadData();
                }
            }
        });

        mStickyScrollView.setScrolListener(new StickyScrollView.OnScrollListener() {
            @Override
            public void onScroll(int scrollY) {

                if (scrollY == (mStickyScrollView.getChildAt(0).getMeasuredHeight() - mStickyScrollView.getMeasuredHeight())) {
                    loadData();
                }
            }
        });
    }

    public void loadData() {
        mLlytProgressBarLoading.setVisibility(View.VISIBLE);
        if (mScenicSpotList.size() != mTotalPageCount) {
            mProgressBarLoadMore.setVisibility(View.VISIBLE);
            mTvLoadMore.setText(getResources().getString(R.string.loading));
            showProgressDialog(ShoppingMallHotelHomestayActivity.this);
            mPage++;
            findHotelHomestayByCate();
        } else {
            mProgressBarLoadMore.setVisibility(View.GONE);
            mTvLoadMore.setText(getResources().getString(R.string.str_loading_no_more));
        }
    }


    @Override
    public void getHotelHomestayHomePageResult(BackResult<ScenicSpotHomePageResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                /*    if (isOnLoadMore) {

                        // mSmartRefreshLayout.finishLoadMore();
                    } else {

                        recommendFriendsList.clear();
                        recommendFriendsAdapter.notifyDataSetChanged();
                    }*/

                    //景点栏
                    mScenicSpotHotList = res.getData().getHot();
                    hotelHomestaySectionAdapter.setHotelHomestaySectionList(mScenicSpotHotList);
                    hotelHomestaySectionAdapter.notifyDataSetChanged();

                    //景点分类
                    mCateEntityList = res.getData().getCate();
                    scenicSpotClassificationAdapter.setScenicSpotClassificationList(mCateEntityList);
                    scenicSpotClassificationAdapter.notifyDataSetChanged();

                    List<ScenicSpotBean> scenicSpotList = res.getData().getMch().getOverflow().getResult();
                    mScenicSpotList.addAll(scenicSpotList);
                    hotelHomestayAdapter.setHotelReputationList(mScenicSpotList);
                    hotelHomestayAdapter.notifyDataSetChanged();
                    BasePaginationResult pageBean = res.getData().getMch().getOverflow().getPage();
                    mTotalPageCount = pageBean.getPageCount();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(ShoppingMallHotelHomestayActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void findHotelHomestayByCateResult(BackResult<ScenicSpotResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    List<ScenicSpotBean> scenicSpotList = res.getData().getResult();
                    BasePaginationResult pageBean = res.getData().getPage();
                    mTotalPageCount = pageBean.getPageCount();
                    mScenicSpotList.addAll(scenicSpotList);

                    if (mPage == 1) {

                        if (mSmartRefreshLayout != null) {
                            mSmartRefreshLayout.finishRefresh();
                            mProgressBarLoadMore.setVisibility(View.GONE);
                            mTvLoadMore.setText(getResources().getString(R.string.str_pull_up_loading));
                        }
                        // mRvHotelHomestay.smoothScrollToPosition(2);
                        //  MoveToPosition(scenicSpotsLinearLayoutManager,mRvHotelHomestay,2);
                    }

                    hotelHomestayAdapter.setHotelReputationList(mScenicSpotList);
                    hotelHomestayAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(ShoppingMallHotelHomestayActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getHotelHomestayBangDanRankingResult(BackResult<ScenicBangDanRankingResponse> res) {

    }

    @Override
    public void showMsg(String msg) {
        dismissProgressDialog();
        showToast(ShoppingMallHotelHomestayActivity.this, Constants.getResultMsg(msg));

    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }


    @OnClick({R.id.tv_popular_nearby, R.id.tv_comprehensive_sorting, R.id.tv_super_value_reservation, R.id.tv_distance_first})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.tv_popular_nearby:
                mTvPopularNearby.setTextColor(getResources().getColor(R.color.txt_font_black2));
                mTvPopularNearby.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
                mTvComprehensiveSorting.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvSuperValueReservation.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvDistanceFirst.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvComprehensiveSorting.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mTvSuperValueReservation.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mTvDistanceFirst.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mPage = 1;
                mScenicSpotList.clear();
                //  scenicSpotsListAdapter.notifyDataSetChanged();
                mSortStr = "FJ";
                showProgressDialog(ShoppingMallHotelHomestayActivity.this);
                findHotelHomestayByCate();
                break;
            case R.id.tv_comprehensive_sorting:
                mTvComprehensiveSorting.setTextColor(getResources().getColor(R.color.txt_font_black2));
                mTvComprehensiveSorting.setTypeface(Typeface.DEFAULT, Typeface.BOLD);

                mTvPopularNearby.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvSuperValueReservation.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvDistanceFirst.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvPopularNearby.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mTvSuperValueReservation.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mTvDistanceFirst.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mPage = 1;
                mScenicSpotList.clear();
                //   scenicSpotsListAdapter.notifyDataSetChanged();
                mSortStr = "ZH";
                showProgressDialog(ShoppingMallHotelHomestayActivity.this);
                findHotelHomestayByCate();

                break;
            case R.id.tv_super_value_reservation:
                mTvSuperValueReservation.setTextColor(getResources().getColor(R.color.txt_font_black2));
                mTvComprehensiveSorting.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvPopularNearby.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvDistanceFirst.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvSuperValueReservation.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
                mTvComprehensiveSorting.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mTvPopularNearby.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mTvDistanceFirst.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mPage = 1;
                mScenicSpotList.clear();
                // scenicSpotsListAdapter.notifyDataSetChanged();
                mSortStr = "CZ";
                showProgressDialog(ShoppingMallHotelHomestayActivity.this);
                findHotelHomestayByCate();
                break;
            case R.id.tv_distance_first:
                mTvDistanceFirst.setTextColor(getResources().getColor(R.color.txt_font_black2));
                mTvSuperValueReservation.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvComprehensiveSorting.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvPopularNearby.setTextColor(getResources().getColor(R.color.color_text_gray20));

                mTvDistanceFirst.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
                mTvSuperValueReservation.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mTvComprehensiveSorting.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mTvPopularNearby.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mPage = 1;
                mScenicSpotList.clear();
                // scenicSpotsListAdapter.notifyDataSetChanged();
                mSortStr = "cz";
                showProgressDialog(ShoppingMallHotelHomestayActivity.this);
                findHotelHomestayByCate();
                break;
            default:
                break;
        }
    }

    /**
     * RecyclerView 移动到当前位置，
     *
     * @param manager       设置RecyclerView对应的manager
     * @param mRecyclerView 当前的RecyclerView
     * @param n             要跳转的位置
     */
    public void MoveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView, int n) {
        int firstItem = manager.findFirstVisibleItemPosition();
        int lastItem = manager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            mRecyclerView.scrollToPosition(n);
        } else if (n <= lastItem) {
            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
            mRecyclerView.scrollBy(0, top);
        } else {
            mRecyclerView.scrollToPosition(n);
        }
    }

    /* RecyclerView 移动到当前位置，
     *
     * @param manager  设置RecyclerView对应的manager
     * @param n  要跳转的位置
     */
   /* public static void MoveToPosition(LinearLayoutManager manager, int n) {
        manager.scrollToPositionWithOffset(n, 0);
        manager.setStackFromEnd(true);
    }*/

    public void getHotelHomestayHomePage() {

        if (validateInternet()) {

            mPresenter.getHotelHomestayHomePage("121.583009", "29.853123");
        }
    }

    public void findHotelHomestayByCate() {
        if (validateInternet()) {

            HashMap<String, String> scenicSpotByCateRequest = new HashMap<>();

            scenicSpotByCateRequest.put("Sorting", mSortStr);
            scenicSpotByCateRequest.put("page", String.valueOf(mPage));
            scenicSpotByCateRequest.put("pageSize", String.valueOf(mPageSize));
            scenicSpotByCateRequest.put("longitude", "121.583009");
            scenicSpotByCateRequest.put("latitude", "29.853123");
            mPresenter.findHotelHomestayByCate(scenicSpotByCateRequest);
        }
    }

    /* RecyclerView 移动到当前位置，
     *
     * @param manager  设置RecyclerView对应的manager
     * @param n  要跳转的位置
     */
    public static void MoveToPosition(LinearLayoutManager manager, int n) {
        manager.scrollToPositionWithOffset(n, 0);
        manager.setStackFromEnd(true);
    }
}
