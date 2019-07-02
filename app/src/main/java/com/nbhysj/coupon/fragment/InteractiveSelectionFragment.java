package com.nbhysj.coupon.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.MyTravelListAdapter;
import com.nbhysj.coupon.adapter.RecreationListAdapter;
import com.nbhysj.coupon.adapter.ScenicSpotsListAdapter;
import com.nbhysj.coupon.adapter.TravelAssisantRecommendAdapter;
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
 * description：互动精选
 */
public class InteractiveSelectionFragment extends BaseFragment<DestinationPresenter, DestinationModel> implements DestinationContract.View {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private int mPage = 1;
    private int mPageSize = 10;
    private RecreationListAdapter recreationListAdapter;
    List<ScenicSpotBean> mHotScenicSpotList;
    @BindView(R.id.rv_interactive_selection)
    RecyclerView mRvInteractiveSelection;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.rlyt_no_data)
    RelativeLayout mRlytNoData;
    //加载
    @BindView(R.id.llyt_progress_bar_loading)
    LinearLayout mLlytProgressBarLoading;
    private int hasNext;
    private int cityId = 330212;
    //互动精选类型:4
    private int mchType = 4;
    int mTotalPageCount;

    public InteractiveSelectionFragment() {
        // Required empty public constructor
    }

    public static InteractiveSelectionFragment newInstance(String param1, String param2) {
        InteractiveSelectionFragment fragment = new InteractiveSelectionFragment();
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
        return R.layout.fragment_interactive_selection;
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);

    }

    @Override
    public void initView(View v) {

        if (mHotScenicSpotList == null) {
            mHotScenicSpotList = new ArrayList<>();
        } else {
            mHotScenicSpotList.clear();
        }
        LinearLayoutManager scenicSpotsLinearLayoutManager = new LinearLayoutManager(getActivity());
        scenicSpotsLinearLayoutManager.setOrientation(scenicSpotsLinearLayoutManager.VERTICAL);
        mRvInteractiveSelection.setLayoutManager(scenicSpotsLinearLayoutManager);
        recreationListAdapter = new RecreationListAdapter(getActivity());
        recreationListAdapter.setRecreationListList(mHotScenicSpotList);
        mRvInteractiveSelection.setAdapter(recreationListAdapter);

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
    public void getDestinationHomePageResult(BackResult<DestinationResponse> res) {

    }

    @Override
    public void findMchBycityNameResult(BackResult<HotScenicSpotResponse> res) {
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
        recreationListAdapter.setRecreationListList(mHotScenicSpotList);
        recreationListAdapter.notifyDataSetChanged();
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
