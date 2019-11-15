package com.nbhysj.coupon.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.CommentAndAnswerAdapter;
import com.nbhysj.coupon.adapter.ZanAndCollectionAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.MessageContract;
import com.nbhysj.coupon.model.MessageModel;
import com.nbhysj.coupon.model.response.AttentionResponse;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.CommentAndAnswerBean;
import com.nbhysj.coupon.model.response.CommentAndAnswerResponse;
import com.nbhysj.coupon.model.response.CommentReceiveResponse;
import com.nbhysj.coupon.model.response.FollowUserStatusResponse;
import com.nbhysj.coupon.model.response.MessageResponse;
import com.nbhysj.coupon.model.response.UserFansFollowResponse;
import com.nbhysj.coupon.model.response.UserFollowResponse;
import com.nbhysj.coupon.model.response.ZanAndCollectionBean;
import com.nbhysj.coupon.model.response.ZanAndCollectionResponse;
import com.nbhysj.coupon.presenter.MessagePresenter;
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
 * @auther：hysj created on 2019/03/13
 * description：收到的评论
 */
public class CommentsAndAnswersActivity extends BaseActivity<MessagePresenter, MessageModel> implements MessageContract.View {

    //收到的评论
    @BindView(R.id.rv_zan_and_collection)
    RecyclerView mRvZanAndCollection;

    private List<CommentAndAnswerBean> mCommentAndAnswerList;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    //赞无数据
    @BindView(R.id.rlyt_no_data)
    RelativeLayout mRlytNoData;
    private int mPageNo = 1;
    private int mPageSize = 10;

    private CommentAndAnswerAdapter commentAndAnswerAdapter;

    private boolean isOnLoadMore = false;

    //总条数
    private int mTotalPageCount;
    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_zan_and_collection;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setBar(CommentsAndAnswersActivity.this, getResources().getString(R.string.str_praise_and_collection), R.mipmap.nav_ico_back_black);
        if (mCommentAndAnswerList == null) {
            mCommentAndAnswerList = new ArrayList<>();

        } else {
            mCommentAndAnswerList.clear();
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CommentsAndAnswersActivity.this);
        mRvZanAndCollection.setLayoutManager(linearLayoutManager);
        commentAndAnswerAdapter = new CommentAndAnswerAdapter(CommentsAndAnswersActivity.this);
        commentAndAnswerAdapter.setCommentAndAnswerList(mCommentAndAnswerList);
        mRvZanAndCollection.setAdapter(commentAndAnswerAdapter);
    }

    @Override
    public void initData() {

        showProgressDialog(CommentsAndAnswersActivity.this);
        getPostsCommentAndAnswer();

        mSmartRefreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isOnLoadMore = false;
                        mPageNo = 1;
                        mCommentAndAnswerList.clear();
                        commentAndAnswerAdapter.notifyDataSetChanged();
                        showProgressDialog(CommentsAndAnswersActivity.this);
                        getPostsCommentAndAnswer();

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
                            if (mTotalPageCount == mCommentAndAnswerList.size()) {
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            } else {
                                mPageNo++;
                                getPostsCommentAndAnswer();
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
    public void getZanAndCollectionMsgResult(BackResult<ZanAndCollectionResponse> res) {

    }

    @Override
    public void showMsg(String msg) {

        if(mSmartRefreshLayout != null){

            mSmartRefreshLayout.finishRefresh();
        }
        dismissProgressDialog();
        showToast(CommentsAndAnswersActivity.this, Constants.getResultMsg(msg));
    }

    @Override
    public void getPostsCommentAndAnswerResult(BackResult<CommentAndAnswerResponse> res) {
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

                        mCommentAndAnswerList.clear();
                        commentAndAnswerAdapter.notifyDataSetChanged();
                        mSmartRefreshLayout.finishRefresh();
                        mSmartRefreshLayout.setNoMoreData(false);
                    }
                    BasePaginationResult paginationResult = res.getData().getPage();
                    mTotalPageCount = paginationResult.getPageCount();
                    List<CommentAndAnswerBean> commentAndAnswerList = res.getData().getResult();

                    if (mTotalPageCount == 0)
                    {
                        mRlytNoData.setVisibility(View.VISIBLE);

                    } else {
                        mRlytNoData.setVisibility(View.GONE);
                    }

                    if (commentAndAnswerList != null)
                    {
                        mCommentAndAnswerList.addAll(commentAndAnswerList);
                    }

                    commentAndAnswerAdapter.setCommentAndAnswerList(mCommentAndAnswerList);
                    commentAndAnswerAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(CommentsAndAnswersActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    //获取评论和回答
    public void getPostsCommentAndAnswer() {
        if (validateInternet())
        {
            mPresenter.getPostsCommentAndAnswer(mPageNo, mPageSize);
        }
    }
}
