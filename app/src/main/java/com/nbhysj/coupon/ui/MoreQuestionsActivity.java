package com.nbhysj.coupon.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.MoreQuestionListAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.MchQuestionAndAnswerContract;
import com.nbhysj.coupon.model.MchQuestionAndAnswerModel;
import com.nbhysj.coupon.model.response.AskTogetherResponse;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.QuestionAnsweringResponse;
import com.nbhysj.coupon.model.response.QuestionDetailsBean;
import com.nbhysj.coupon.model.response.StrategyBean;
import com.nbhysj.coupon.model.response.WaitMyAnswerBean;
import com.nbhysj.coupon.model.response.WaitMyAnswerResponse;
import com.nbhysj.coupon.presenter.MchQuestionAndAnswerPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/03/02
 * description：更多问题
 */
public class MoreQuestionsActivity extends BaseActivity<MchQuestionAndAnswerPresenter, MchQuestionAndAnswerModel> implements MchQuestionAndAnswerContract.View {

    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;

    //很多问题
    @BindView(R.id.rv_more_question)
    RecyclerView mRvMoreQuestion;

    @BindView(R.id.tv_back)
    TextView mTvBack;

    @BindView(R.id.tv_question_num)
    TextView mTvQuestionNum;
    //赞无数据
    @BindView(R.id.rlyt_no_data)
    RelativeLayout mRlytNoData;
    private int mPageNo = 1;
    private int mPageSize = 10;

    //商户名
    private String mchName;

    MoreQuestionListAdapter moreQuestionListAdapter;

    private int mchId;

    private String mchPhotoUrl;

    List<WaitMyAnswerBean> mQuestionContentList;

    //地址
    private String address;

    private int QUESTION_REQUEST_CODE = 0;

    private boolean isOnLoadMore = false;

    //总条数
    private int mTotalPageCount;
    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_more_questions;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        mchId = getIntent().getIntExtra("mchId", mchId);
        mchPhotoUrl = getIntent().getStringExtra("mchPhotoUrl");
        address = getIntent().getStringExtra("address");

        if (mQuestionContentList == null) {

            mQuestionContentList = new ArrayList<>();
        } else {
            mQuestionContentList.clear();
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(MoreQuestionsActivity.this);
        mRvMoreQuestion.setLayoutManager(layoutManager);
        moreQuestionListAdapter = new MoreQuestionListAdapter(MoreQuestionsActivity.this);
        moreQuestionListAdapter.setMoreQuestionList(mQuestionContentList);
        mRvMoreQuestion.setAdapter(moreQuestionListAdapter);
    }

    @Override
    public void initData() {

        showProgressDialog(MoreQuestionsActivity.this);
        getMchQuestionAndAnswerList();

        mSmartRefreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isOnLoadMore = false;
                        mPageNo = 1;
                        mQuestionContentList.clear();
                        moreQuestionListAdapter.notifyDataSetChanged();
                        showProgressDialog(MoreQuestionsActivity.this);
                        getMchQuestionAndAnswerList();

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
                            if (mTotalPageCount == mQuestionContentList.size()) {
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            } else {
                                mPageNo++;
                                getMchQuestionAndAnswerList();
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

    @OnClick({R.id.rlyt_my_ask_question, R.id.rlyt_my_answer, R.id.tv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rlyt_my_ask_question:

                Intent intent = new Intent();
                intent.putExtra("mchId", mchId);
                intent.putExtra("mchName", mchName);
                intent.putExtra("mchPhotoUrl", mchPhotoUrl);
                intent.putExtra("address", address);
                intent.setClass(MoreQuestionsActivity.this, AskQuestionsActivity.class);
                startActivityForResult(intent, QUESTION_REQUEST_CODE);

                break;
            case R.id.rlyt_my_answer:
                toActivity(MyQuestionAndAnswersActivity.class);  //我的问题
                break;
            case R.id.tv_back:

                MoreQuestionsActivity.this.finish();

                break;
            default:
                break;
        }
    }

    @Override
    public void questionAnsweringPublishResult(BackResult res) {

    }

    @Override
    public void getQuestionDetailsResult(BackResult<QuestionDetailsBean> res) {

    }

    @Override
    public void answerPublishResult(BackResult res) {

    }

    @Override
    public void askTogetherResult(BackResult res) {

    }


    @Override
    public void getMchQuestionAndAnswerListResult(BackResult<WaitMyAnswerResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:

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

                                mQuestionContentList.clear();
                                moreQuestionListAdapter.notifyDataSetChanged();
                                mSmartRefreshLayout.finishRefresh();
                                mSmartRefreshLayout.setNoMoreData(false);
                            }
                            WaitMyAnswerResponse waitMyAnswerResponse = res.getData();
                            mchName = waitMyAnswerResponse.getMchName();
                            BasePaginationResult paginationResult = res.getData().getPage();
                            mTotalPageCount = paginationResult.getPageCount();
                            List<WaitMyAnswerBean> waitMyAnswerList = waitMyAnswerResponse.getResult();

                            if (mTotalPageCount == 0)
                            {
                                mRlytNoData.setVisibility(View.VISIBLE);

                            } else {
                                mRlytNoData.setVisibility(View.GONE);
                            }

                            if (waitMyAnswerList != null)
                            {
                                mQuestionContentList.addAll(waitMyAnswerList);
                            }

                            moreQuestionListAdapter.setMoreQuestionList(mQuestionContentList);
                            moreQuestionListAdapter.notifyDataSetChanged();
                            int pageCount = paginationResult.getPageCount();

                            mTvQuestionNum.setText("关于" + mchName + "的" + String.valueOf(pageCount) + "个问题");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                }

                break;
            default:
                showToast(MoreQuestionsActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getWaitMyAnswerListResult(BackResult res) {

    }

    @Override
    public void getMyQuestionListResult(BackResult<QuestionAnsweringResponse> res) {

    }

    @Override
    public void getUserAskTogetherListResult(BackResult<AskTogetherResponse> res) {

    }

    @Override
    public void getMyAnswerListResult(BackResult res) {

    }

    @Override
    public void ignoreQuestionsAndAnswersResult(BackResult<QuestionAnsweringResponse> res) {

    }

    @Override
    public void answerZanResult(BackResult res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:

                dismissProgressDialog();
                switch (res.getCode()) {
                    case Constants.SUCCESS_CODE:
                        try {


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                }
                break;
            default:
                showToast(MoreQuestionsActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(MoreQuestionsActivity.this, Constants.getResultMsg(msg));
    }

    //商户问答
    public void getMchQuestionAndAnswerList() {

        if (validateInternet()) {

            mPresenter.getMchQuestionAndAnswerList(mchId, mPageNo, mPageSize);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == QUESTION_REQUEST_CODE && resultCode == RESULT_OK) {

            mQuestionContentList.clear();
            moreQuestionListAdapter.notifyDataSetChanged();
            getMchQuestionAndAnswerList();
        }
    }
}
