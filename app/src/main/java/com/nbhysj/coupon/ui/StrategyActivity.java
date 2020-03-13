package com.nbhysj.coupon.ui;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.StrategyListAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.StrategyContract;
import com.nbhysj.coupon.model.StrategyModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.StrategyBean;
import com.nbhysj.coupon.model.response.StrategyCommentListResponse;
import com.nbhysj.coupon.model.response.StrategyResponse;
import com.nbhysj.coupon.presenter.StrategyPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/09/21
 * description:攻略列表
 *
 */
public class StrategyActivity extends BaseActivity<StrategyPresenter, StrategyModel> implements StrategyContract.View {
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    //赞无数据
    @BindView(R.id.rlyt_no_data)
    RelativeLayout mRlytNoData;
    //攻略列表
    @BindView(R.id.rv_strategy_list)
    RecyclerView mRvStrategyList;

    private int mPageNo = 1;
    private int mPageSize = 10;

    private StrategyListAdapter strategyListAdapter;

    private List<StrategyBean> mStrategyList;
    private boolean isOnLoadMore = false;

    //总条数
    private int mTotalPageCount;
    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_strategy;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setBar(StrategyActivity.this, getResources().getString(R.string.str_strategy), R.mipmap.icon_left_arrow_black);

        if(mStrategyList == null){

            mStrategyList = new ArrayList<>();

        } else {

            mStrategyList.clear();
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(StrategyActivity.this) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        };

        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        mRvStrategyList.setLayoutManager(linearLayoutManager);
        strategyListAdapter = new StrategyListAdapter(StrategyActivity.this);
        strategyListAdapter.setStrategyList(mStrategyList);
        mRvStrategyList.setAdapter(strategyListAdapter);
    }

    @Override
    public void initData()
    {
        showProgressDialog(StrategyActivity.this);
        getStrategyList();

        mSmartRefreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isOnLoadMore = false;
                        mPageNo = 1;
                        mStrategyList.clear();
                        strategyListAdapter.notifyDataSetChanged();
                        showProgressDialog(StrategyActivity.this);
                        getStrategyList();

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
                            if (mTotalPageCount == mStrategyList.size()) {
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            } else {
                                mPageNo++;
                                getStrategyList();
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
    public void findAllStrategyResult(BackResult<StrategyResponse> res) {
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

                        mStrategyList.clear();
                        strategyListAdapter.notifyDataSetChanged();
                        mSmartRefreshLayout.finishRefresh();
                        mSmartRefreshLayout.setNoMoreData(false);
                    }

                    BasePaginationResult paginationResult = res.getData().getPage();
                    mTotalPageCount = paginationResult.getPageCount();
                    List<StrategyBean> strategyList = res.getData().getResult();

                    if (mTotalPageCount == 0)
                    {
                        mRlytNoData.setVisibility(View.VISIBLE);

                    } else {
                        mRlytNoData.setVisibility(View.GONE);
                    }

                    if (strategyList != null)
                    {
                        mStrategyList.addAll(strategyList);
                    }

                    strategyListAdapter.setStrategyList(mStrategyList);
                    strategyListAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(StrategyActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void addStrategyCommentResult(BackResult res) {

    }

    @Override
    public void getStrategyCommentListResult(BackResult<StrategyCommentListResponse> res) {

    }

    @Override
    public void showMsg(String msg) {
        if (mSmartRefreshLayout != null)
        {
            mSmartRefreshLayout.finishRefresh();
        }

        dismissProgressDialog();
        showToast(StrategyActivity.this, Constants.getResultMsg(msg));
    }

    public void getStrategyList() {

        if (validateInternet()) {
            mPresenter.findAllStrategy(mPageNo, mPageSize);
        }
    }
}
