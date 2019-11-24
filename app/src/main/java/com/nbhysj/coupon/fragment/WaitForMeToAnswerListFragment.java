package com.nbhysj.coupon.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.WaitForMeToAnswerAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.MchQuestionAndAnswerContract;
import com.nbhysj.coupon.model.MchQuestionAndAnswerModel;
import com.nbhysj.coupon.model.request.IgnoreQuestionsAndAnswersRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.QuestionAnsweringResponse;
import com.nbhysj.coupon.model.response.QuestionDetailsBean;
import com.nbhysj.coupon.model.response.WaitForMeToAnswerBean;
import com.nbhysj.coupon.model.response.WaitForMeToAnswerResponse;
import com.nbhysj.coupon.model.response.WaitMyAnswerResponse;
import com.nbhysj.coupon.presenter.MchQuestionAndAnswerPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/11/18
 * description：我的回答(待我来答)列表
 */
public class WaitForMeToAnswerListFragment extends BaseFragment<MchQuestionAndAnswerPresenter, MchQuestionAndAnswerModel> implements MchQuestionAndAnswerContract.View {
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.rlyt_no_data)
    RelativeLayout mRlytNoData;
    //待我来回答
    @BindView(R.id.rv_wait_for_me_to_answer)
    RecyclerView mRvWaitForMeToAnswer;

    private int mPageNo = 1;

    private int mPageSize = 10;

    private boolean isOnLoadMore = false;

    int mTotalPageCount;

    public String TAG = "WaitForMeToAnswerListFragment.this";

    private WaitForMeToAnswerAdapter waitForMeToAnswerAdapter;

    private List<WaitForMeToAnswerBean> waitForMeToAnswerList;

    public WaitForMeToAnswerListFragment() {
        // Required empty public constructor

    }

    public static WaitForMeToAnswerListFragment newInstance(String orderStatus) {

        WaitForMeToAnswerListFragment fragment = new WaitForMeToAnswerListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {


            long time = new Date().getTime();

        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_wait_for_me_to_answer;
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }


    @Override
    protected void initView(View v) {

        if (waitForMeToAnswerList == null) {

            waitForMeToAnswerList = new ArrayList<>();
        } else {
            waitForMeToAnswerList.clear();
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        };
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        mRvWaitForMeToAnswer.setLayoutManager(linearLayoutManager);
        waitForMeToAnswerAdapter = new WaitForMeToAnswerAdapter(getActivity(), new WaitForMeToAnswerAdapter.WaitForMeToAnswerListener() {
            @Override
            public void setQuestionIgnoreListener(int position) {

                questionIgnore();
            }

            @Override
            public void setQuestionAnswerListener(int position) {

            }
        });
        waitForMeToAnswerAdapter.setWaitForMeToAnswerList(waitForMeToAnswerList);
        mRvWaitForMeToAnswer.setAdapter(waitForMeToAnswerAdapter);

    }

    @Override
    public void initData() {
        // getOrderList();
        mSmartRefreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isOnLoadMore = false;
                        mPageNo = 1;
                        waitForMeToAnswerList.clear();
                        waitForMeToAnswerAdapter.notifyDataSetChanged();
                        showProgressDialog(getActivity());
                        getWaitMyAnswerList();

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
                            if (mTotalPageCount == waitForMeToAnswerList.size()) {
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            } else {
                                mPageNo++;
                                getWaitMyAnswerList();
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
    public void lazyInitView(View view) {
        showProgressDialog(getActivity());
        waitForMeToAnswerList.clear();
        isOnLoadMore = false;
        mPageNo = 1;
        getWaitMyAnswerList();
    }

    @Override
    public void getWaitMyAnswerListResult(BackResult<WaitForMeToAnswerResponse> res) {
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

                        waitForMeToAnswerList.clear();
                        waitForMeToAnswerAdapter.notifyDataSetChanged();
                        mSmartRefreshLayout.finishRefresh();
                        mSmartRefreshLayout.setNoMoreData(false);
                    }

                    BasePaginationResult paginationResult = res.getData().getPage();
                    mTotalPageCount = paginationResult.getPageCount();
                    List<WaitForMeToAnswerBean> answerList = res.getData().getResult();

                    if (mTotalPageCount == 0) {
                        mRlytNoData.setVisibility(View.VISIBLE);

                    } else {
                        mRlytNoData.setVisibility(View.GONE);
                    }

                    if (answerList != null) {
                        waitForMeToAnswerList.addAll(answerList);
                    }

                    waitForMeToAnswerAdapter.setWaitForMeToAnswerList(waitForMeToAnswerList);
                    waitForMeToAnswerAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Constants.USER_NOT_LOGIN_CODE:
                onReLogin("");
                break;
            default:
                showToast(getActivity(), Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            String action = intent.getAction();
            if (Constants.BROCAST_ACTION_TRIP_ASSISANT.equals(action)) {

                // showToast(getActivity(),"111");
            }
        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
        }
    }


    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);


    }

    @Override
    public void questionAnsweringPublishResult(BackResult res) {

    }

    @Override
    public void getMchQuestionAndAnswerListResult(BackResult<WaitMyAnswerResponse> res) {

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
    public void answerZanResult(BackResult res) {

    }

    @Override
    public void getUserAskTogetherListResult(BackResult res) {

    }

    @Override
    public void getMyAnswerListResult(BackResult res) {

    }

    @Override
    public void getMyQuestionListResult(BackResult<QuestionAnsweringResponse> res) {

    }

    @Override
    public void ignoreQuestionsAndAnswersResult(BackResult<QuestionAnsweringResponse> res) {

    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();

        showToast(getActivity(), Constants.getResultMsg(msg));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    //待回答
    public void getWaitMyAnswerList() {

        if (validateInternet()) {

            mPresenter.getWaitMyAnswerList(mPageNo, mPageSize);
        }
    }

    //问题忽略
    public void questionIgnore(){

        if(validateInternet()) {
            IgnoreQuestionsAndAnswersRequest ignoreQuestionsAndAnswersRequest = new IgnoreQuestionsAndAnswersRequest();
            ignoreQuestionsAndAnswersRequest.setMchId(0);
            mPresenter.ignoreQuestionsAndAnswers(ignoreQuestionsAndAnswersRequest);
        }
    }
}
