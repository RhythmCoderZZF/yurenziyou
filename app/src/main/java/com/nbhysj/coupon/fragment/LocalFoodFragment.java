package com.nbhysj.coupon.fragment;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.FineFoodListAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.DestinationContract;
import com.nbhysj.coupon.model.DestinationModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.DestinationResponse;
import com.nbhysj.coupon.model.response.HotScenicSpotResponse;
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.presenter.DestinationPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * @auther：hysj created on 2019/06/04
 * description：当地美食
 */
public class LocalFoodFragment extends BaseFragment<DestinationPresenter, DestinationModel> implements DestinationContract.View {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    List<MchTypeBean> mHotScenicSpotList;
    private int mPage = 1;
    private int mPageSize = 10;
    private FineFoodListAdapter fineFoodListAdapter;
    @BindView(R.id.rv_fine_food)
    RecyclerView mRvFineFood;
    @BindView(R.id.rlyt_no_data)
    RelativeLayout mRlytNoData;
    private int hasNext;
    private int cityId = 330212;
    private int mchType = 2;
    int mTotalPageCount;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    //加载
    @BindView(R.id.llyt_progress_bar_loading)
    LinearLayout mLlytProgressBarLoading;

    public LocalFoodFragment() {
        // Required empty public constructor
    }

    public static LocalFoodFragment newInstance(String param1, String param2) {
        LocalFoodFragment fragment = new LocalFoodFragment();
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
            mHotScenicSpotList = new ArrayList<MchTypeBean>();
        } else {
            mHotScenicSpotList.clear();
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        mRvFineFood.setLayoutManager(linearLayoutManager);
        fineFoodListAdapter = new FineFoodListAdapter(getActivity());
        fineFoodListAdapter.setFineFoodList(mHotScenicSpotList);
        mRvFineFood.setAdapter(fineFoodListAdapter);

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
