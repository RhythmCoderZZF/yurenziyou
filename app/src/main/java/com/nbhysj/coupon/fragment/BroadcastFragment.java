package com.nbhysj.coupon.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.BroadcastItemAdapter;
import com.nbhysj.coupon.adapter.StrategyListAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.MessageContract;
import com.nbhysj.coupon.model.MessageModel;
import com.nbhysj.coupon.model.response.AttentionResponse;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.BroadcastBean;
import com.nbhysj.coupon.model.response.BroadcastResponse;
import com.nbhysj.coupon.model.response.CommentAndAnswerResponse;
import com.nbhysj.coupon.model.response.FollowUserStatusResponse;
import com.nbhysj.coupon.model.response.ImageData;
import com.nbhysj.coupon.model.response.MessageResponse;
import com.nbhysj.coupon.model.response.StrategyBean;
import com.nbhysj.coupon.model.response.UnReadMessageBean;
import com.nbhysj.coupon.model.response.UserFansFollowResponse;
import com.nbhysj.coupon.model.response.UserFollowResponse;
import com.nbhysj.coupon.model.response.ZanAndCollectionResponse;
import com.nbhysj.coupon.presenter.MessagePresenter;
import com.nbhysj.coupon.ui.StrategyActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BroadcastFragment extends BaseFragment<MessagePresenter, MessageModel> implements MessageContract.View {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    //赞无数据
    @BindView(R.id.rlyt_no_data)
    RelativeLayout mRlytNoData;
    //广播
    @BindView(R.id.rv_broadcast)
    RecyclerView mRvBroadcastList;

    private int mPageNo = 1;
    private int mPageSize = 10;

    private boolean isOnLoadMore = false;

    private List<BroadcastBean> broadcastMessageList;

    BroadcastItemAdapter mBroadcastItemAdapter;

    private int mTotalPageCount;
    public BroadcastFragment() {
        // Required empty public constructor
    }

    public static BroadcastFragment newInstance(String param1, String param2) {
        BroadcastFragment fragment = new BroadcastFragment();
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
        return R.layout.fragment_broadcast;
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this,mModel);

    }

    @Override
    public void initView(View v) {

        getBroadcatMessageList();

        if (broadcastMessageList == null) {

            broadcastMessageList = new ArrayList<>();
        } else {
            broadcastMessageList.clear();
        }



        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        // 设置布局管理器
        mRvBroadcastList.setLayoutManager(layoutManager);

        mBroadcastItemAdapter = new BroadcastItemAdapter(getActivity());
        mBroadcastItemAdapter.setBroadcastList(broadcastMessageList);
        mRvBroadcastList.setAdapter(mBroadcastItemAdapter);

    }

    @Override
    public void initData() {

    }

    @Override
    public void lazyInitView(View view) {

    }

    @Override
    public void getUserFansListResult(BackResult<UserFansFollowResponse> res) {

    }

    @Override
    public void userFollowResult(BackResult<FollowUserStatusResponse> res) {

    }

    @Override
    public void getAttentionInitResult(BackResult<AttentionResponse> res) {

    }

    @Override
    public void getUserFollowResult(BackResult<UserFollowResponse> res) {

    }

    @Override
    public void getMessageListResult(BackResult<MessageResponse> res) {

    }

    @Override
    public void getUnReadMessage(BackResult<UnReadMessageBean> res) {

    }

    @Override
    public void getZanAndCollectionMsgResult(BackResult<ZanAndCollectionResponse> res) {

    }

    @Override
    public void getPostsCommentAndAnswerResult(BackResult<CommentAndAnswerResponse> res) {

    }

    @Override
    public void getBroadcatMessageListResult(BackResult<BroadcastResponse> res) {
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

                        broadcastMessageList.clear();
                        mBroadcastItemAdapter.notifyDataSetChanged();
                        mSmartRefreshLayout.finishRefresh();
                        mSmartRefreshLayout.setNoMoreData(false);
                    }

                    BasePaginationResult paginationResult = res.getData().getPage();
                    mTotalPageCount = paginationResult.getPageCount();
                    List<BroadcastBean> broadcastList = res.getData().getResult();

                    if (mTotalPageCount == 0)
                    {
                        mRlytNoData.setVisibility(View.VISIBLE);

                    } else {
                        mRlytNoData.setVisibility(View.GONE);
                    }

                    if (broadcastList != null)
                    {
                        broadcastMessageList.addAll(broadcastList);
                    }

                    mBroadcastItemAdapter.setBroadcastList(broadcastMessageList);
                    mBroadcastItemAdapter.notifyDataSetChanged();

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
    public void showMsg(String msg) {
        dismissProgressDialog();
        showToast(getActivity(), Constants.getResultMsg(msg));

    }

    public void getBroadcatMessageList(){

        if(validateInternet()){

            mPresenter.getBroadcatMessageList(mPageNo,mPageSize);
        }
    }
}
