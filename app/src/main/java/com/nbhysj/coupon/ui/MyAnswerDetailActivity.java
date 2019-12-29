package com.nbhysj.coupon.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.AnswerDetailSubAdapter;
import com.nbhysj.coupon.adapter.MyQuestionCorrelationListAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.MchQuestionAndAnswerContract;
import com.nbhysj.coupon.model.MchQuestionAndAnswerModel;
import com.nbhysj.coupon.model.request.AnswerPublishRequest;
import com.nbhysj.coupon.model.request.AnswerZanRequest;
import com.nbhysj.coupon.model.request.AskTogetherRequest;
import com.nbhysj.coupon.model.response.AnswerAdoptStatusResponse;
import com.nbhysj.coupon.model.response.AnswerBean;
import com.nbhysj.coupon.model.response.AskTogetherResponse;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.QuestionAnsweringResponse;
import com.nbhysj.coupon.model.response.QuestionDetailsBean;
import com.nbhysj.coupon.model.response.WaitMyAnswerResponse;
import com.nbhysj.coupon.presenter.MchQuestionAndAnswerPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.DateUtil;
import com.nbhysj.coupon.util.ToolbarHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/11/18
 * description: (我的提问)问答详情
 */
public class MyAnswerDetailActivity extends BaseActivity<MchQuestionAndAnswerPresenter, MchQuestionAndAnswerModel> implements MchQuestionAndAnswerContract.View {

    //商户名
    @BindView(R.id.tv_mch_name)
    TextView mTvMchName;

    //问题内容
    @BindView(R.id.tv_question_content)
    TextView mTvQuestionContent;

    //问答时间
    @BindView(R.id.tv_question_time)
    TextView mTvQuestionTime;

    //问答数量
    @BindView(R.id.tv_answer_num)
    TextView mTvAnswerNum;

    //回答
    @BindView(R.id.rv_answer)
    RecyclerView mRvAnswer;

    //相关问题
    @BindView(R.id.rv_correlation_question)
    RecyclerView mRvCorrelationQuestion;

    //评论
    @BindView(R.id.edt_comment)
    EditText mEdtComment;

    //发送确认
    @BindView(R.id.tv_answer_sure_send)
    TextView mTvAnswerSureSend;

    @BindView(R.id.rlyt_answer_detail_more_question)
    RelativeLayout mRlytAnswerDetailMoreQuestion;

    //相关问题
    @BindView(R.id.llyt_correlation_question)
    LinearLayout mLlytCorrelationQuestion;

    //回答
    @BindView(R.id.llyt_answer)
    LinearLayout mLlytAnswer;

    //问题id
    private int questionId;

    private List<AnswerBean> answerList;

    private List<AnswerBean> correlationList;

    private AnswerDetailSubAdapter answerDetailSubAdapter;

    private MyQuestionCorrelationListAdapter myQuestionCorrelationListAdapter;

    //商户名
    private String mchName;

    //问题内容
    private String content;

    private int ANSWER_REQUEST_CODE = 0;

    private int mPosition;

    //同问状态
    private int questionStatus;
    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_my_question_detail;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setBar(MyAnswerDetailActivity.this,"问答详情",R.mipmap.icon_left_arrow_black);
        questionId = getIntent().getIntExtra("questionId",0);

        if(answerList == null){

            answerList = new ArrayList<>();
        } else {
            answerList.clear();
        }

        if(correlationList == null){

            correlationList = new ArrayList<>();

        } else {

            correlationList.clear();
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyAnswerDetailActivity.this);
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        mRvAnswer.setLayoutManager(linearLayoutManager);
        answerDetailSubAdapter = new AnswerDetailSubAdapter(MyAnswerDetailActivity.this, new AnswerDetailSubAdapter.AnswerUsefulListener() {
            @Override
            public void setAnswerUsefulListener(int position,AnswerBean answerBean) {

                mPosition = position;
                int answerId = answerBean.getId();
                answerZan(answerId);

            }

            @Override
            public void setAnswerAdoptListener(int position, AnswerBean answerBean) {


            }
        });
        answerDetailSubAdapter.setAnswerList(answerList);
        mRvAnswer.setAdapter(answerDetailSubAdapter);

        LinearLayoutManager correlationLinearLayoutManager = new LinearLayoutManager(MyAnswerDetailActivity.this);
        correlationLinearLayoutManager.setOrientation(correlationLinearLayoutManager.VERTICAL);
        mRvCorrelationQuestion.setLayoutManager(correlationLinearLayoutManager);
        myQuestionCorrelationListAdapter = new MyQuestionCorrelationListAdapter(MyAnswerDetailActivity.this, new MyQuestionCorrelationListAdapter.QuestionUsefulListener() {
            @Override
            public void setQuestionUsefulListener(int position) {

            }
        });
        myQuestionCorrelationListAdapter.setMyQuestionList(correlationList);
        mRvCorrelationQuestion.setAdapter(myQuestionCorrelationListAdapter);
    }

    @Override
    public void initData() {

        showProgressDialog(MyAnswerDetailActivity.this);
        getQuestionDetails();

        mTvAnswerSureSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                answerPublish();
            }
        });

    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this,mModel);
    }

    @Override
    public void questionAnsweringPublishResult(BackResult res) {

    }

    @Override
    public void getMchQuestionAndAnswerListResult(BackResult<WaitMyAnswerResponse> res) {

    }

    @Override
    public void answersAdoptResult(BackResult<AnswerAdoptStatusResponse> res) {

    }

    @Override
    public void getQuestionDetailsResult(BackResult<QuestionDetailsBean> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    QuestionDetailsBean questionDetailsBean = res.getData();
                    mchName = questionDetailsBean.getMchName();
                    content = questionDetailsBean.getContent();
                    long ctime = questionDetailsBean.getCtime();
                    questionId = questionDetailsBean.getQuestionId();
                    questionStatus = questionDetailsBean.getQuestionStatus();
                    mTvQuestionContent.setText(content);

                    mTvMchName.setText(mchName);

                    String answerTime = DateUtil.transferLongToDate(DateUtil.sDateYMDFormat,ctime);
                    mTvQuestionTime.setText(answerTime);
                    answerList = questionDetailsBean.getAnswer();

                    if(answerList != null && answerList.size() > 0)
                    {
                        mLlytAnswer.setVisibility(View.VISIBLE);
                        answerDetailSubAdapter.setAnswerList(answerList);
                        answerDetailSubAdapter.notifyDataSetChanged();
                        int answerNum = answerList.size();
                        mTvAnswerNum.setText("共" + answerNum + "个回答");

                    } else {

                        mLlytAnswer.setVisibility(View.GONE);
                    }

                    correlationList = questionDetailsBean.getCorrelation();
                    if(correlationList != null && correlationList.size() > 0)
                    {
                        mLlytCorrelationQuestion.setVisibility(View.VISIBLE);
                        myQuestionCorrelationListAdapter.setMyQuestionList(correlationList);
                        myQuestionCorrelationListAdapter.notifyDataSetChanged();
                    } else {
                        mLlytCorrelationQuestion.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Constants.USER_NOT_LOGIN_CODE:
                toActivity(PhoneQuickLoginActivity.class);
                break;
            default:
                showToast(MyAnswerDetailActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void answerPublishResult(BackResult res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    hideKeyboard(mRlytAnswerDetailMoreQuestion);
                    mEdtComment.setText("");
                    answerList.clear();
                    answerDetailSubAdapter.notifyDataSetChanged();
                    getQuestionDetails();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Constants.USER_NOT_LOGIN_CODE:
                toActivity(PhoneQuickLoginActivity.class);
                break;
            default:
                showToast(MyAnswerDetailActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void askTogetherResult(BackResult res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {


                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Constants.USER_NOT_LOGIN_CODE:
                toActivity(PhoneQuickLoginActivity.class);
                break;
            default:
                showToast(MyAnswerDetailActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getUserAskTogetherListResult(BackResult<AskTogetherResponse> res) {

    }

    @Override
    public void getMyAnswerListResult(BackResult res) {

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

                            answerList.get(mPosition).setZanStatus(1);
                            answerDetailSubAdapter.setAnswerList(answerList);
                            answerDetailSubAdapter.notifyDataSetChanged();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                }
                break;
            default:
                showToast(MyAnswerDetailActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getMyQuestionListResult(BackResult<QuestionAnsweringResponse> res) {

    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(MyAnswerDetailActivity.this, Constants.getResultMsg(msg));
    }

    //获取问答详情
    public void getQuestionDetails(){

        if(validateInternet()){

            mPresenter.getQuestionDetails(questionId);
        }
    }

    //问题回答
    public void answerPublish(){

        if(validateInternet()){
            String answerContent = mEdtComment.getText().toString().trim();

            if(TextUtils.isEmpty(answerContent))
            {
                showToast(MyAnswerDetailActivity.this,"请填写你的评论");
                return;
            }
            AnswerPublishRequest answerPublishRequest = new AnswerPublishRequest();
            answerPublishRequest.setQuestionId(questionId);
            answerPublishRequest.setContent(answerContent);
            mPresenter.answerPublish(answerPublishRequest);
        }
    }

    //同问接口
    public void askTogether(){

        if(validateInternet())
        {
            AskTogetherRequest askTogetherRequest = new AskTogetherRequest();
            askTogetherRequest.setQuestionId(questionId);
            mPresenter.askTogether(askTogetherRequest);
        }
    }


    public void answerZan(int answerId){

        if(validateInternet())
        {
            AnswerZanRequest answerZanRequest = new AnswerZanRequest();
            answerZanRequest.setAnswerId(answerId);
            mPresenter.answerZanRequest(answerZanRequest);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ANSWER_REQUEST_CODE && resultCode == RESULT_OK){

            answerList.clear();
            answerDetailSubAdapter.notifyDataSetChanged();
            getQuestionDetails();
        }
    }

    @Override
    public void getWaitMyAnswerListResult(BackResult res) {

    }

    @Override
    public void ignoreQuestionsAndAnswersResult(BackResult<QuestionAnsweringResponse> res) {

    }
}
