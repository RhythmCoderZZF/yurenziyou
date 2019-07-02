package com.nbhysj.coupon.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.HotelHomestayAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.DestinationContract;
import com.nbhysj.coupon.model.DestinationModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.DestinationResponse;
import com.nbhysj.coupon.model.response.HotScenicSpotResponse;
import com.nbhysj.coupon.model.response.ScenicSpotBean;
import com.nbhysj.coupon.presenter.DestinationPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/06/04
 * description：酒店精选
 */
public class HotelSelectionFragment extends BaseFragment<DestinationPresenter, DestinationModel> implements DestinationContract.View {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    List<ScenicSpotBean> mHotScenicSpotList;
    private int mPage = 1;
    private int mPageSize = 10;
    private HotelHomestayAdapter hotelHomestayAdapter;
    @BindView(R.id.rv_fine_food)
    RecyclerView mRvFineFood;
    @BindView(R.id.rlyt_no_data)
    RelativeLayout mRlytNoData;
    private int hasNext;
    private int cityId = 330212;
    //酒店类型:3
    private int mchType = 3;
    int mTotalPageCount;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    //加载
    @BindView(R.id.llyt_progress_bar_loading)
    LinearLayout mLlytProgressBarLoading;

    public HotelSelectionFragment() {
        // Required empty public constructor
    }

    public static HotelSelectionFragment newInstance(String param1, String param2) {
        HotelSelectionFragment fragment = new HotelSelectionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fine_food;
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }


    @Override
    protected void initView(View v) {

        if (mHotScenicSpotList == null) {
            mHotScenicSpotList = new ArrayList<>();
        } else {
            mHotScenicSpotList.clear();
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        mRvFineFood.setLayoutManager(linearLayoutManager);
        hotelHomestayAdapter = new HotelHomestayAdapter(getActivity());
        hotelHomestayAdapter.setHotelReputationList(mHotScenicSpotList);
        mRvFineFood.setAdapter(hotelHomestayAdapter);

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
                            if (mTotalPageCount == mHotScenicSpotList.size()) {
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            } else {
                                mPage++;
                                findMchBycityName();
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
    public void findMchBycityNameResult(BackResult<HotScenicSpotResponse> res) {
        try {
            mSmartRefreshLayout.finishLoadMore();
            mLlytProgressBarLoading.setVisibility(View.GONE);
            mRlytNoData.setVisibility(View.GONE);
            HotScenicSpotResponse hotScenicSpotResponse = res.getData();
            BasePaginationResult pageBean = res.getData().getPage();
            hasNext = pageBean.getHasNext();
            mTotalPageCount = pageBean.getPageCount();
            List<ScenicSpotBean> hotScenicSpotList = hotScenicSpotResponse.getResult();
            if (mTotalPageCount == 0) {
                mHotScenicSpotList.clear();
                mRlytNoData.setVisibility(View.VISIBLE);
            }
            if (hotScenicSpotList != null) {
                mHotScenicSpotList.addAll(hotScenicSpotList);
            }
            hotelHomestayAdapter.setHotelReputationList(mHotScenicSpotList);
            hotelHomestayAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getDestinationHomePageResult(BackResult<DestinationResponse> res) {

    }

    @Override
    public void lazyInitView(View view) {
        findMchBycityName();
    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(getActivity(), Constants.getResultMsg(msg));

    }

    public void findMchBycityName() {

        if (validateInternet()) {

            mPresenter.findMchBycityName(cityId, mchType, mPage, mPageSize);
        }
    }
}
