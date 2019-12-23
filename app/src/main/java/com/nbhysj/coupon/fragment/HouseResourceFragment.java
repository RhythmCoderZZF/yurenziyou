package com.nbhysj.coupon.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.FineFoodListAdapter;
import com.nbhysj.coupon.adapter.HomestayResourcesAdapter;
import com.nbhysj.coupon.adapter.HouseResourceAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.DestinationContract;
import com.nbhysj.coupon.contract.HomestayContract;
import com.nbhysj.coupon.model.DestinationModel;
import com.nbhysj.coupon.model.HomestayModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.CouponsGetBean;
import com.nbhysj.coupon.model.response.DestinationCityResponse;
import com.nbhysj.coupon.model.response.DestinationResponse;
import com.nbhysj.coupon.model.response.HomestayBean;
import com.nbhysj.coupon.model.response.HotScenicSpotResponse;
import com.nbhysj.coupon.model.response.HouseResouceResponse;
import com.nbhysj.coupon.model.response.LandlordBean;
import com.nbhysj.coupon.model.response.LandlordDetailResonse;
import com.nbhysj.coupon.model.response.MchBangDanRankingResponse;
import com.nbhysj.coupon.model.response.MchCollectionResponse;
import com.nbhysj.coupon.model.response.MchCouponResponse;
import com.nbhysj.coupon.model.response.MchHomestayDetailsResponse;
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.model.response.ScenicSpotResponse;
import com.nbhysj.coupon.model.response.StrategyBean;
import com.nbhysj.coupon.presenter.DestinationPresenter;
import com.nbhysj.coupon.presenter.HomestayPresenter;
import com.nbhysj.coupon.ui.HomestayDetailActivity;
import com.nbhysj.coupon.ui.StrategyActivity;
import com.nbhysj.coupon.util.Tools;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/12/23
 * description：房源
 */
public class HouseResourceFragment extends BaseFragment<HomestayPresenter, HomestayModel> implements HomestayContract.View {
    private static final String ARG_LANDLORDID = "landlordId";
    private int mPage = 1;
    private int mPageSize = 10;
    private HouseResourceAdapter houseResourceAdapter;
    //房源列表
    @BindView(R.id.rv_house_resources)
    RecyclerView mRvHouseResources;
    @BindView(R.id.rlyt_no_data)
    RelativeLayout mRlytNoData;
    int mTotalPageCount;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    private boolean isOnLoadMore = false;

    private List<HomestayBean> houseResourceList;

    //房东id
    private int landlordId;

    public HouseResourceFragment() {
        // Required empty public constructor
    }

    public static HouseResourceFragment newInstance(int landlordId) {
        HouseResourceFragment fragment = new HouseResourceFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_LANDLORDID, landlordId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            landlordId = getArguments().getInt("landlordId", 0);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_house_resources;
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView(View v) {

        if (houseResourceList == null)
        {
            houseResourceList = new ArrayList<HomestayBean>();
        } else {
            houseResourceList.clear();
        }

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        layoutManager.setOrientation(layoutManager.VERTICAL);
        mRvHouseResources.setLayoutManager(layoutManager);
        mRvHouseResources.addItemDecoration(new RecyclerViewItemDecoration(Tools.dip2px(getActivity(), 10)));
        houseResourceAdapter = new HouseResourceAdapter(getActivity());
        houseResourceAdapter.setHomeResourceList(houseResourceList);
        mRvHouseResources.setAdapter(houseResourceAdapter);
    }

    @Override
    public void initData() {
        mSmartRefreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）

        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            if (mTotalPageCount == houseResourceList.size()) {
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            } else {
                                mPage++;
                                getLandlordHouseResourceList();
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
    public void getLandlordHouseResourceListResult(BackResult<HouseResouceResponse> res) {
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

                        houseResourceList.clear();
                        houseResourceAdapter.notifyDataSetChanged();
                        mSmartRefreshLayout.finishRefresh();
                        mSmartRefreshLayout.setNoMoreData(false);
                    }

                    BasePaginationResult paginationResult = res.getData().getPage();
                    mTotalPageCount = paginationResult.getPageCount();
                    List<HomestayBean> strategyList = res.getData().getResult();

                    if (mTotalPageCount == 0) {
                        mRlytNoData.setVisibility(View.VISIBLE);

                    } else {
                        mRlytNoData.setVisibility(View.GONE);
                    }

                    if (strategyList != null) {
                        houseResourceList.addAll(strategyList);
                    }

                    houseResourceAdapter.setHomeResourceList(houseResourceList);
                    houseResourceAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(getActivity(), Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    /* @Override
     public void findMchBycityNameResult(BackResult<HotScenicSpotResponse> res) {
         mSmartRefreshLayout.finishLoadMore();
         mLlytProgressBarLoading.setVisibility(View.GONE);
         mRlytNoData.setVisibility(View.GONE);
         HotScenicSpotResponse hotScenicSpotResponse = res.getData();
         BasePaginationResult pageBean = res.getData().getPage();
         hasNext = pageBean.getHasNext();
         mTotalPageCount = pageBean.getPageCount();
         List<MchTypeBean> hotScenicSpotList = hotScenicSpotResponse.getResult();
         if (mTotalPageCount == 0) {
             mHotScenicSpotList.clear();
             mRlytNoData.setVisibility(View.VISIBLE);
         }
         if (hotScenicSpotList != null) {
             mHotScenicSpotList.addAll(hotScenicSpotList);
         }
         fineFoodListAdapter.setFineFoodList(mHotScenicSpotList);
         fineFoodListAdapter.notifyDataSetChanged();
     }
 */
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

    }

    @Override
    public void lazyInitView(View view) {
        mPage = 1;
        houseResourceList.clear();
        houseResourceAdapter.notifyDataSetChanged();
        mSmartRefreshLayout.finishRefresh();
        mSmartRefreshLayout.setNoMoreData(false);
        showProgressDialog(getActivity());
        getLandlordHouseResourceList();
    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(getActivity(), Constants.getResultMsg(msg));

    }

    public void getLandlordHouseResourceList() {
        if (validateInternet()) {

            mPresenter.getLandlordHouseResourceList(landlordId);
        }
    }

    /**
     * 为RecyclerView增加间距
     * 预设2列，如果是3列，则左右值不同
     */
    public class RecyclerViewItemDecoration extends RecyclerView.ItemDecoration {
        private int space = 0;
        private int pos;

        public RecyclerViewItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

            outRect.top = space;

            //该View在整个RecyclerView中位置。
            pos = parent.getChildAdapterPosition(view);

            //取模

            //两列的左边一列
            if (pos % 2 == 0) {
                outRect.left = space;
                outRect.right = space / 2;
            }

            //两列的右边一列
            if (pos % 2 == 1) {
                outRect.left = space / 2;
                outRect.right = space;
            }
        }
    }
}
