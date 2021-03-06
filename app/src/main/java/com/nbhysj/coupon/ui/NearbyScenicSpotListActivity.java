package com.nbhysj.coupon.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.NearbyScenicSpotMoreAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.HotelContract;
import com.nbhysj.coupon.contract.ScenicSpotContract;
import com.nbhysj.coupon.model.HotelModel;
import com.nbhysj.coupon.model.ScenicSpotModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.CouponsGetBean;
import com.nbhysj.coupon.model.response.HotelOrderInitResponse;
import com.nbhysj.coupon.model.response.MchAlbumResponse;
import com.nbhysj.coupon.model.response.MchBangDanRankingResponse;
import com.nbhysj.coupon.model.response.MchCateListResponse;
import com.nbhysj.coupon.model.response.MchCollectionResponse;
import com.nbhysj.coupon.model.response.MchCouponResponse;
import com.nbhysj.coupon.model.response.MchDetailsResponse;
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.model.response.NetFriendAlbumResponse;
import com.nbhysj.coupon.model.response.OrderSubmitResponse;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.model.response.ScenicSpotResponse;
import com.nbhysj.coupon.model.response.TourGuideBean;
import com.nbhysj.coupon.presenter.HotelPresenter;
import com.nbhysj.coupon.presenter.ScenicSpotPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/10/10
 * description：附近景点列表
 */
public class NearbyScenicSpotListActivity extends BaseActivity<ScenicSpotPresenter, ScenicSpotModel> implements ScenicSpotContract.View {
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.rv_hotels_near_scenic_spots)
    RecyclerView mRvHotelNearScenicSpots;
    //暂无订单数据
    @BindView(R.id.rlyt_no_data)
    RelativeLayout mRlytNoOrderData;
    private int mPage = 1;
    private int mPageSize = 10;
    int mTotalPageCount;
    //排序规则类型 FJ=附近热门 ZH=综合排序 默认FJ
    private String mSortStr = "FJ";
    String latitude;
    String longitude;
    //景点附近酒店列表
    List<MchTypeBean> mScenicSpotNearbyHotelList;

    NearbyScenicSpotMoreAdapter scenicSpotNearbyHotelAdapter;

    private boolean isOnLoadMore = false;

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_hotel_near_scenic_spots_list;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setHeadBar(NearbyScenicSpotListActivity.this, "附近景点", R.mipmap.icon_left_arrow_black, "");

        latitude = getIntent().getStringExtra("latitude");
        longitude = getIntent().getStringExtra("longitude");

        if (mScenicSpotNearbyHotelList == null) {

            mScenicSpotNearbyHotelList = new ArrayList<>();
        } else {
            mScenicSpotNearbyHotelList.clear();
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(NearbyScenicSpotListActivity.this);
        layoutManager.setOrientation(layoutManager.VERTICAL);
        mRvHotelNearScenicSpots.setLayoutManager(layoutManager);
        scenicSpotNearbyHotelAdapter = new NearbyScenicSpotMoreAdapter(NearbyScenicSpotListActivity.this);
        scenicSpotNearbyHotelAdapter.setNearbyScenicSpotsList(mScenicSpotNearbyHotelList);
        mRvHotelNearScenicSpots.setAdapter(scenicSpotNearbyHotelAdapter);
    }

    @Override
    public void initData() {
        findScenicSpotNearbyByCate();
        mSmartRefreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPage = 1;
                        findScenicSpotNearbyByCate();

                        isOnLoadMore = false;
                    }
                }, 100);
            }
        });

        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        isOnLoadMore = true;
                        try {
                            if (mTotalPageCount == mScenicSpotNearbyHotelList.size()) {
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            } else {
                                mPage++;
                                findScenicSpotNearbyByCate();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 200);
            }
        });

    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    public void getScenicSpotHomePageResult(BackResult<ScenicSpotHomePageResponse> res) {

    }

    @Override
    public void findScenicListByCateIdResult(BackResult<MchCateListResponse> res) {

    }

    @Override
    public void getCouponResult(BackResult<CouponsGetBean> res) {

    }

    @Override
    public void queryMchCouponListResult(BackResult<List<MchCouponResponse>> res) {

    }

    @Override
    public void findScenicByCateResult(BackResult<ScenicSpotResponse> res) {
        dismissProgressDialog();
        if (mSmartRefreshLayout != null) {
            mSmartRefreshLayout.finishRefresh();
        }
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    if (isOnLoadMore) {

                        mSmartRefreshLayout.finishLoadMore();
                    } else {

                        mScenicSpotNearbyHotelList.clear();
                        scenicSpotNearbyHotelAdapter.notifyDataSetChanged();
                        mSmartRefreshLayout.finishRefresh();
                        mSmartRefreshLayout.setNoMoreData(false);
                    }

                    List<MchTypeBean> scenicSpotNearbyHotelList = res.getData().getResult();
                    BasePaginationResult pageBean = res.getData().getPage();
                    mTotalPageCount = pageBean.getPageCount();

                    if (scenicSpotNearbyHotelList != null) {
                        mScenicSpotNearbyHotelList.addAll(scenicSpotNearbyHotelList);
                    }

                    if (mTotalPageCount == 0) {
                        mRlytNoOrderData.setVisibility(View.VISIBLE);
                    } else {
                        mRlytNoOrderData.setVisibility(View.GONE);
                    }

                    scenicSpotNearbyHotelAdapter.setNearbyScenicSpotsList(mScenicSpotNearbyHotelList);
                    scenicSpotNearbyHotelAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }

    @Override
    public void getScenicBangDanRankingResult(BackResult<MchBangDanRankingResponse> res) {

    }

    @Override
    public void getTourGuideListResult(BackResult<List<TourGuideBean>> res) {

    }

    @Override
    public void getMchAlbumListResult(BackResult<MchAlbumResponse> res) {

    }

    @Override
    public void getNetFriendAlbumListResult(BackResult<NetFriendAlbumResponse> res) {

    }

    @Override
    public void getMchDetailsResult(BackResult<MchDetailsResponse> res) {

    }

    @Override
    public void mchCollectionResult(BackResult<MchCollectionResponse> res) {

    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(NearbyScenicSpotListActivity.this, Constants.getResultMsg(msg));
    }

    //景点附近酒店
    public void findScenicSpotNearbyByCate() {
        if (validateInternet()) {

            HashMap<String, String> scenicSpotByCateRequest = new HashMap<>();

            scenicSpotByCateRequest.put("Sorting", mSortStr);
            scenicSpotByCateRequest.put("page", String.valueOf(mPage));
            scenicSpotByCateRequest.put("pageSize", String.valueOf(mPageSize));
            scenicSpotByCateRequest.put("latitude", latitude);
            scenicSpotByCateRequest.put("longitude", longitude);
            mPresenter.findScenicByCate(scenicSpotByCateRequest);
        }
    }
}
