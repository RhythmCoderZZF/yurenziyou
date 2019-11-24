package com.nbhysj.coupon.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.MchQuestionAndAnswerContract;
import com.nbhysj.coupon.model.MchQuestionAndAnswerModel;
import com.nbhysj.coupon.model.request.AnswerPublishRequest;
import com.nbhysj.coupon.model.response.AskTogetherResponse;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.QuestionAnsweringResponse;
import com.nbhysj.coupon.model.response.QuestionDetailsBean;
import com.nbhysj.coupon.model.response.WaitMyAnswerBean;
import com.nbhysj.coupon.model.response.WaitMyAnswerResponse;
import com.nbhysj.coupon.presenter.MchQuestionAndAnswerPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.DateUtil;
import com.nbhysj.coupon.util.ToolbarHelper;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/11/18
 * description: 我来回答
 */
public class LetMeAnswerActivity extends BaseActivity<MchQuestionAndAnswerPresenter, MchQuestionAndAnswerModel> implements MchQuestionAndAnswerContract.View {

    //商户名
    @BindView(R.id.tv_mch_name)
    TextView mTvMchName;

    //问题
    @BindView(R.id.tv_question_content)
    TextView mTvQuestionContent;

    //回答
    @BindView(R.id.edt_answer)
    EditText mEdtAnswer;

    //发布
    @BindView(R.id.tv_publish)
    TextView mTvPublish;

    //我来回答
    private int questionId;

    //商户名
    private String mchName;

    //问题内容
    private String questionContent;

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_let_me_answer;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setBar(LetMeAnswerActivity.this, getResources().getString(R.string.str_let_me_answer), R.mipmap.icon_left_arrow_black);
    }

    @Override
    public void initData() {

        mchName = getIntent().getStringExtra("mchName");
        questionContent = getIntent().getStringExtra("questionContent");
        questionId = getIntent().getIntExtra("questionId", 0);

        mTvMchName.setText(mchName);
        mTvQuestionContent.setText(questionContent);

        mTvPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                answerPublish();
            }
        });
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
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
    public void askTogetherResult(BackResult res) {

    }

    @Override
    public void answerPublishResult(BackResult res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    setResult(RESULT_OK);
                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Constants.USER_NOT_LOGIN_CODE:
                toActivity(PhoneQuickLoginActivity.class);
                break;
            default:
                showToast(LetMeAnswerActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void answerZanResult(BackResult res) {

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
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(LetMeAnswerActivity.this, Constants.getResultMsg(msg));

    }

    @Override
    public void getWaitMyAnswerListResult(BackResult res) {

    }

    public void answerPublish() {

        if (validateInternet()) {
            String answerContent = mEdtAnswer.getText().toString();

            if (TextUtils.isEmpty(answerContent)) {
                showToast(LetMeAnswerActivity.this, "请填写回答内容");
                return;
            }
            AnswerPublishRequest answerPublishRequest = new AnswerPublishRequest();
            answerPublishRequest.setQuestionId(questionId);
            answerPublishRequest.setContent(answerContent);
            mPresenter.answerPublish(answerPublishRequest);
        }
    }
}
