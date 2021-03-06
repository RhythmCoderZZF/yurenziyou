package com.nbhysj.coupon.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.MineFansFollowAdapter;
import com.nbhysj.coupon.adapter.MyOrderListAdapter;
import com.nbhysj.coupon.adapter.NewFansListAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.MessageContract;
import com.nbhysj.coupon.contract.OrderListContract;
import com.nbhysj.coupon.dialog.OprateDialog;
import com.nbhysj.coupon.model.MessageModel;
import com.nbhysj.coupon.model.OrderListModel;
import com.nbhysj.coupon.model.request.OrderDeleteRequest;
import com.nbhysj.coupon.model.response.AttentionResponse;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.BroadcastResponse;
import com.nbhysj.coupon.model.response.CommentAndAnswerResponse;
import com.nbhysj.coupon.model.response.FollowUserStatusResponse;
import com.nbhysj.coupon.model.response.MessageResponse;
import com.nbhysj.coupon.model.response.UnReadMessageBean;
import com.nbhysj.coupon.model.response.UserFansFollowBean;
import com.nbhysj.coupon.model.response.UserFansFollowResponse;
import com.nbhysj.coupon.model.response.UserFollowResponse;
import com.nbhysj.coupon.model.response.UserOrderListResponse;
import com.nbhysj.coupon.model.response.ZanAndCollectionResponse;
import com.nbhysj.coupon.presenter.MessagePresenter;
import com.nbhysj.coupon.presenter.OrderListPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.ui.NewFansActivity;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * created by hysj on 2019/11/27.
 * description: (粉丝关注)我的模块
 */
public class MineFansFollowListFragment extends BaseFragment<MessagePresenter, MessageModel> implements MessageContract.View {
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    //赞无数据
    @BindView(R.id.rlyt_no_data)
    RelativeLayout mRlytNoData;
    @BindView(R.id.rv_new_fans)
    RecyclerView mRvNewFansList;
    //新增粉丝
    private List<UserFansFollowBean> userFansFollowList;

    private MineFansFollowAdapter mineFansFollowAdapter;

    private boolean isOnLoadMore = false;
    private int mPageNo = 1;
    private int mPageSize = 10;
    //总条数
    private int mTotalPageCount;

    UserFansFollowBean mNewFansBean;
    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(getActivity(), -131077);
        return R.layout.fragment_mine_fans_follow;
    }

    @Override
    public void lazyInitView(View view) {
        mPageNo = 1;
        userFansFollowList.clear();
        mineFansFollowAdapter.notifyDataSetChanged();
        getUserFansList();
    }


    @Override
    public void initView(View v) {

        if (userFansFollowList == null) {

            userFansFollowList = new ArrayList<>();

        } else {
            userFansFollowList.clear();
        }

        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        // 设置布局管理器
        mRvNewFansList.setLayoutManager(layoutManager);

        mineFansFollowAdapter = new MineFansFollowAdapter(getActivity(), new MineFansFollowAdapter.MineFansFollowListener() {
            @Override
            public void setMineFansFollowListener(UserFansFollowBean newFansBean) {
                mNewFansBean = newFansBean;
                int userId = mNewFansBean.getFansId();
                userFollow(userId);
            }
        });
        mineFansFollowAdapter.setMineFansFollowList(userFansFollowList);
        mRvNewFansList.setAdapter(mineFansFollowAdapter);
    }

    @Override
    public void initData() {

        showProgressDialog(getActivity());
        getUserFansList();

        mSmartRefreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isOnLoadMore = false;
                        mPageNo = 1;
                        userFansFollowList.clear();
                        mineFansFollowAdapter.notifyDataSetChanged();
                        showProgressDialog(getActivity());
                        getUserFansList();

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
                            if (mTotalPageCount == userFansFollowList.size()) {
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            } else {
                                mPageNo++;
                                getUserFansList();
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
        mPresenter.setVM(this,mModel);
    }

    @Override
    public void getUnReadMessage(BackResult<UnReadMessageBean> res) {

    }

    @Override
    public void userFollowResult(BackResult<FollowUserStatusResponse> res) {
        dismissProgressDialog();

        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    int attentionStatus = mNewFansBean.getAttentionStatus();

                    if(attentionStatus == 0){

                        mNewFansBean.setAttentionStatus(1);
                    } else if(attentionStatus == 1){
                        mNewFansBean.setAttentionStatus(0);
                    }

                    mineFansFollowAdapter.setMineFansFollowList(userFansFollowList);
                    mineFansFollowAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(getActivity(), Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getAttentionInitResult(BackResult<AttentionResponse> res) {

    }

    @Override
    public void getUserFollowResult(BackResult<UserFollowResponse> res) {

    }

    @Override
    public void getPostsCommentAndAnswerResult(BackResult<CommentAndAnswerResponse> res) {

    }

    @Override
    public void getBroadcatMessageListResult(BackResult<BroadcastResponse> res) {

    }

    @Override
    public void getUserFansListResult(BackResult<UserFansFollowResponse> res) {
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

                        userFansFollowList.clear();
                        mineFansFollowAdapter.notifyDataSetChanged();
                        mSmartRefreshLayout.finishRefresh();
                        mSmartRefreshLayout.setNoMoreData(false);
                    }

                    BasePaginationResult paginationResult = res.getData().getPage();
                    mTotalPageCount = paginationResult.getPageCount();
                    List<UserFansFollowBean> strategyList = res.getData().getResult();

                    if (mTotalPageCount == 0) {
                        mRlytNoData.setVisibility(View.VISIBLE);

                    } else {
                        mRlytNoData.setVisibility(View.GONE);
                    }

                    if (strategyList != null) {
                        userFansFollowList.addAll(strategyList);
                    }

                    mineFansFollowAdapter.setMineFansFollowList(userFansFollowList);
                    mineFansFollowAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(getActivity(), Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getMessageListResult(BackResult<MessageResponse> res) {

    }

    @Override
    public void getZanAndCollectionMsgResult(BackResult<ZanAndCollectionResponse> res) {

    }

    @Override
    public void showMsg(String msg) {
        if (mSmartRefreshLayout != null)
        {
            mSmartRefreshLayout.finishRefresh();
        }

        dismissProgressDialog();
        showToast(getActivity(), Constants.getResultMsg(msg));
    }

    public void getUserFansList(){

        if(validateInternet()){

            showProgressDialog(getActivity());
            mPresenter.getUserFansList(mPageNo,mPageSize);
        }
    }

    public void userFollow(int userId){

        if(validateInternet()){

            showProgressDialog(getActivity());
            mPresenter.userFollow(userId);
        }
    }
}
