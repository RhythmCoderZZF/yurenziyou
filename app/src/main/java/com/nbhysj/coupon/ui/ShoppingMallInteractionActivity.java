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
import com.nbhysj.coupon.adapter.InteractionSectionAdapter;
import com.nbhysj.coupon.adapter.MchRankingClassificationAdapter;
import com.nbhysj.coupon.adapter.RecreationListAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.common.Enum.MchTypeEnum;
import com.nbhysj.coupon.contract.RecreationContract;
import com.nbhysj.coupon.model.RecreationModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.MchBangDanRankingResponse;
import com.nbhysj.coupon.model.response.MchDetailsResponse;
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.model.response.ScenicSpotResponse;
import com.nbhysj.coupon.presenter.RecreationPresenter;
import com.nbhysj.coupon.presenter.ScenicSpotPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
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
 * description：互动首页
 */
public class ShoppingMallInteractionActivity extends BaseActivity<RecreationPresenter, RecreationModel> implements RecreationContract.View {
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
    RecyclerView mRvHotScenicSpot;
    //互动栏目
    private InteractionSectionAdapter interactionSectionAdapter;
    //互动分类
    private MchRankingClassificationAdapter mchRankingClassificationAdapter;
    //互动适配器
    private RecreationListAdapter recreationListAdapter;
    //景点中间分类
    List<ScenicSpotHomePageResponse.CateEntity> mCateEntityList;
    //景点栏目
    List<MchTypeBean> mScenicSpotHotList;
    //景点列表
    List<MchTypeBean> mScenicSpotList;
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
    //经度
    String longitude;
    //纬度
    String latitude;

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

        longitude = (String)SharedPreferencesUtils.getData(SharedPreferencesUtils.LONGITUDE,"");
        latitude = (String)SharedPreferencesUtils.getData(SharedPreferencesUtils.LATITUDE,"");
        showProgressDialog(ShoppingMallInteractionActivity.this);
        getScenicSpotHomePage();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShoppingMallInteractionActivity.this);
        linearLayoutManager.setOrientation(linearLayoutManager.HORIZONTAL);
        mRvPopularScenicSpots.setLayoutManager(linearLayoutManager);
        interactionSectionAdapter = new InteractionSectionAdapter(ShoppingMallInteractionActivity.this);
        interactionSectionAdapter.setInteractionSectionList(mScenicSpotHotList);
        mRvPopularScenicSpots.setAdapter(interactionSectionAdapter);

        //互动分类
        LinearLayoutManager scenicSpotClassificationLinearLayout = new LinearLayoutManager(ShoppingMallInteractionActivity.this);
        scenicSpotClassificationLinearLayout.setOrientation(linearLayoutManager.HORIZONTAL);
        mRvScenicSpotClassification.setLayoutManager(scenicSpotClassificationLinearLayout);
        mchRankingClassificationAdapter = new MchRankingClassificationAdapter(ShoppingMallInteractionActivity.this,MchTypeEnum.MCH_RECREATION.getValue());
        mchRankingClassificationAdapter.setMchRankingClassificationList(mCateEntityList);
        mRvScenicSpotClassification.setAdapter(mchRankingClassificationAdapter);

        LinearLayoutManager scenicSpotsLinearLayoutManager = new LinearLayoutManager(ShoppingMallInteractionActivity.this);
        scenicSpotsLinearLayoutManager.setOrientation(scenicSpotsLinearLayoutManager.VERTICAL);
        mRvHotScenicSpot.setLayoutManager(scenicSpotsLinearLayoutManager);
        recreationListAdapter = new RecreationListAdapter(ShoppingMallInteractionActivity.this);
        recreationListAdapter.setRecreationListList(mScenicSpotList);
        mRvHotScenicSpot.setAdapter(recreationListAdapter);

    }

    @Override
    public void initData() {
        mRlytBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ShoppingMallInteractionActivity.this.finish();
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
                        findScenicByCate();

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
            showProgressDialog(ShoppingMallInteractionActivity.this);
            mPage++;
            findScenicByCate();
        } else {
            mProgressBarLoadMore.setVisibility(View.GONE);
            mTvLoadMore.setText(getResources().getString(R.string.str_loading_no_more));
        }
    }

    @Override
    public void getRecreationHomePageResult(BackResult<ScenicSpotHomePageResponse> res) {
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
                    interactionSectionAdapter.setInteractionSectionList(mScenicSpotHotList);
                    interactionSectionAdapter.notifyDataSetChanged();

                    //景点分类
                    mCateEntityList = res.getData().getCate();
                    mchRankingClassificationAdapter.setMchRankingClassificationList(mCateEntityList);
                    mchRankingClassificationAdapter.notifyDataSetChanged();

                    List<MchTypeBean> scenicSpotList = res.getData().getMch().getNearby().getResult();
                    mScenicSpotList.addAll(scenicSpotList);
                    recreationListAdapter.setRecreationListList(mScenicSpotList);
                    recreationListAdapter.notifyDataSetChanged();
                    BasePaginationResult pageBean = res.getData().getMch().getNearby().getPage();
                    mTotalPageCount = pageBean.getPageCount();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(ShoppingMallInteractionActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void findRecreationByCateResult(BackResult<ScenicSpotResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    if (mPage == 1) {

                        if (mSmartRefreshLayout != null) {
                            mSmartRefreshLayout.finishRefresh();
                            mProgressBarLoadMore.setVisibility(View.GONE);
                            mTvLoadMore.setText(getResources().getString(R.string.str_pull_up_loading));
                        }
                    }
                    List<MchTypeBean> scenicSpotList = res.getData().getResult();
                    BasePaginationResult pageBean = res.getData().getPage();
                    mTotalPageCount = pageBean.getPageCount();
                    mScenicSpotList.addAll(scenicSpotList);
                    recreationListAdapter.setRecreationListList(mScenicSpotList);
                    recreationListAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(ShoppingMallInteractionActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getRecreationDanRankingResult(BackResult<MchBangDanRankingResponse> res) {

    }

    @Override
    public void getRecreationDetailResult(BackResult<MchDetailsResponse> res) {

    }

    @Override
    public void showMsg(String msg) {
        dismissProgressDialog();
        showToast(ShoppingMallInteractionActivity.this, Constants.getResultMsg(msg));

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
                showProgressDialog(ShoppingMallInteractionActivity.this);
                findScenicByCate();
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
                showProgressDialog(ShoppingMallInteractionActivity.this);
                findScenicByCate();

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
                showProgressDialog(ShoppingMallInteractionActivity.this);
                findScenicByCate();
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
                showProgressDialog(ShoppingMallInteractionActivity.this);
                findScenicByCate();
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
    public static void MoveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView, int n) {
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
    public static void MoveToPosition(LinearLayoutManager manager, int n) {
        manager.scrollToPositionWithOffset(n, 0);
        manager.setStackFromEnd(true);
    }

    public void getScenicSpotHomePage() {

        if (validateInternet()) {

            mPresenter.getRecreationHomePage(longitude, latitude);
        }
    }

    public void findScenicByCate() {
        if (validateInternet()) {

            HashMap<String, String> scenicSpotByCateRequest = new HashMap<>();

            scenicSpotByCateRequest.put("Sorting", mSortStr);
            scenicSpotByCateRequest.put("page", String.valueOf(mPage));
            scenicSpotByCateRequest.put("pageSize", String.valueOf(mPageSize));
            scenicSpotByCateRequest.put("longitude",longitude);
            scenicSpotByCateRequest.put("latitude", latitude);
            mPresenter.findRecreationByCate(scenicSpotByCateRequest);
        }
    }
}
