package com.nbhysj.coupon.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.AnswerDetailSubAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.MchQuestionAndAnswerContract;
import com.nbhysj.coupon.model.MchQuestionAndAnswerModel;
import com.nbhysj.coupon.model.request.AnswerPublishRequest;
import com.nbhysj.coupon.model.request.AnswerZanRequest;
import com.nbhysj.coupon.model.request.AskTogetherRequest;
import com.nbhysj.coupon.model.response.AnswerAdoptStatusResponse;
import com.nbhysj.coupon.model.response.AnswerBean;
import com.nbhysj.coupon.model.response.AnswerZanResponse;
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
 * description: 问答详情
 */
public class AskAndAnswerDetailActivity extends BaseActivity<MchQuestionAndAnswerPresenter, MchQuestionAndAnswerModel> implements MchQuestionAndAnswerContract.View {

    //商户名
    @BindView(R.id.tv_mch_name)
    TextView mTvMchName;

    //问题内容
    @BindView(R.id.tv_question_content)
    TextView mTvQuestionContent;

    //问答时间
    @BindView(R.id.tv_question_time)
    TextView mTvQuestionTime;

    //同问接口
    @BindView(R.id.tv_same_question)
    TextView mTvSameQuestion;

    //问答数量
    @BindView(R.id.tv_answer_num)
    TextView mTvAnswerNum;

    //回答
    @BindView(R.id.rv_answer)
    RecyclerView mRvAnswer;

    //评论
    @BindView(R.id.edt_comment)
    EditText mEdtComment;

    //发送确认
    @BindView(R.id.tv_answer_sure_send)
    TextView mTvAnswerSureSend;

    @BindView(R.id.rlyt_answer_detail_more_question)
    RelativeLayout mRlytAnswerDetailMoreQuestion;

    @BindView(R.id.tv_answer)
    TextView mTvAnswer;

    //问题id
    private int questionId;

    private List<AnswerBean> answerList;

    private AnswerDetailSubAdapter answerDetailSubAdapter;

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
        return R.layout.activity_ask_and_answer_detail;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setBar(AskAndAnswerDetailActivity.this,"问答详情",R.mipmap.icon_left_arrow_black);
        questionId = getIntent().getIntExtra("questionId",0);

        if(answerList == null){

            answerList = new ArrayList<>();
        } else {
            answerList.clear();
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AskAndAnswerDetailActivity.this);
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        mRvAnswer.setLayoutManager(linearLayoutManager);
        answerDetailSubAdapter = new AnswerDetailSubAdapter(AskAndAnswerDetailActivity.this, new AnswerDetailSubAdapter.AnswerUsefulListener() {
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
    }

    @Override
    public void initData() {

        showProgressDialog(AskAndAnswerDetailActivity.this);
        getQuestionDetails();

        mTvAnswerSureSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                answerPublish();
            }
        });


        mTvSameQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(questionStatus == 0) {
                    askTogether();
                }
            }
        });

        mTvAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.putExtra("questionId",questionId);
                intent.putExtra("questionContent",content);
                intent.putExtra("mchName",mchName);
                intent.setClass(AskAndAnswerDetailActivity.this, LetMeAnswerActivity.class);
                startActivityForResult(intent,ANSWER_REQUEST_CODE);
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

                    if(questionStatus == 0){
                        mTvSameQuestion.setBackgroundResource(R.drawable.bg_stroke_light_blue_radius_thirteen_shape);
                        mTvSameQuestion.setTextColor(getResources().getColor(R.color.color_blue2));
                        mTvSameQuestion.setText(getResources().getString(R.string.str_same_question));
                    } else  if(questionStatus == 1){

                        mTvSameQuestion.setBackgroundResource(R.drawable.bg_stroke_radius_thirteen_light_gray_shape);
                        mTvSameQuestion.setTextColor(getResources().getColor(R.color.color_text_gray28));
                        mTvSameQuestion.setText("已同问");
                    }

                    String answerTime = DateUtil.transferLongToDate(DateUtil.sDateYMDFormat,ctime);
                    mTvQuestionTime.setText(answerTime);
                    answerList = questionDetailsBean.getAnswer();

                    if(answerList != null && answerList.size() > 0)
                    {
                        answerDetailSubAdapter.setAnswerList(answerList);
                        answerDetailSubAdapter.notifyDataSetChanged();
                        int answerNum = answerList.size();
                        mTvAnswerNum.setText("共" + answerNum + "个回答");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Constants.USER_NOT_LOGIN_CODE:
                toActivity(PhoneQuickLoginActivity.class);
                break;
            default:
                showToast(AskAndAnswerDetailActivity.this, Constants.getResultMsg(res.getMsg()));
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
                showToast(AskAndAnswerDetailActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void askTogetherResult(BackResult res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    if(questionStatus == 0){
                        mTvSameQuestion.setBackgroundResource(R.drawable.bg_stroke_light_blue_radius_thirteen_shape);
                        mTvSameQuestion.setTextColor(getResources().getColor(R.color.color_blue2));
                        mTvSameQuestion.setText(getResources().getString(R.string.str_same_question));
                    } else  if(questionStatus == 1){

                        mTvSameQuestion.setBackgroundResource(R.drawable.bg_stroke_radius_thirteen_light_gray_shape);
                        mTvSameQuestion.setTextColor(getResources().getColor(R.color.color_text_gray28));
                        mTvSameQuestion.setText("已同问");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Constants.USER_NOT_LOGIN_CODE:
                toActivity(PhoneQuickLoginActivity.class);
                break;
            default:
                showToast(AskAndAnswerDetailActivity.this, Constants.getResultMsg(res.getMsg()));
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
    public void answerZanResult(BackResult<AnswerZanResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:

                dismissProgressDialog();
                switch (res.getCode()) {
                    case Constants.SUCCESS_CODE:
                        try {

                            AnswerZanResponse answerZanResponse = res.getData();
                            int zanNum = answerZanResponse.getZanNum();
                            int zanStatus = answerZanResponse.getZanStatus();
                            answerList.get(mPosition).setZanStatus(zanStatus);
                            answerList.get(mPosition).setZanNum(zanNum);
                            answerDetailSubAdapter.setAnswerList(answerList);
                            answerDetailSubAdapter.notifyDataSetChanged();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                }
                break;
            default:
                showToast(AskAndAnswerDetailActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void ignoreQuestionsAndAnswersResult(BackResult<QuestionAnsweringResponse> res) {

    }

    @Override
    public void getMyQuestionListResult(BackResult<QuestionAnsweringResponse> res) {

    }

    @Override
    public void answersAdoptResult(BackResult<AnswerAdoptStatusResponse> res) {

    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(AskAndAnswerDetailActivity.this, Constants.getResultMsg(msg));
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
                showToast(AskAndAnswerDetailActivity.this,"请填写你的评论");
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
}
