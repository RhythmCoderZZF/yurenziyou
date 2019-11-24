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
import com.nbhysj.coupon.model.request.QuestionPublishRequest;
import com.nbhysj.coupon.model.response.AskTogetherResponse;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.QuestionAnsweringResponse;
import com.nbhysj.coupon.model.response.QuestionDetailsBean;
import com.nbhysj.coupon.model.response.WaitMyAnswerResponse;
import com.nbhysj.coupon.presenter.MchQuestionAndAnswerPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.nbhysj.coupon.view.RoundedImageView;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/04/10
 * description: 我要提问
 */
public class AskQuestionsActivity extends BaseActivity<MchQuestionAndAnswerPresenter, MchQuestionAndAnswerModel> implements MchQuestionAndAnswerContract.View {
    //商户名
    @BindView(R.id.tv_mch_name)
    TextView mTvMchName;

    //问题发布
    @BindView(R.id.tv_question_publish)
    TextView mTvQuestionPublish;

    //回答内容
    @BindView(R.id.edt_question_content)
    EditText mEdtQuestionContent;

    //商户照片
    @BindView(R.id.img_mch_photo)
    RoundedImageView mImgMchPhoto;

    //商户位置
    @BindView(R.id.tv_location)
    TextView mTvAddress;
    //我来回答
    private int mchId;

    //商户名
    private String mchName;

    //商户照片
    private String mchPhotoUrl;

    @Override
    public int getLayoutId() {
        return R.layout.activity_ask_questions;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        StatusBarCompat.setStatusBarColor(this, -131077);
        ToolbarHelper.setBar(AskQuestionsActivity.this, getResources().getString(R.string.str_my_ask_question), R.mipmap.nav_ico_back_black);
        mchName = getIntent().getStringExtra("mchName");
        mchId = getIntent().getIntExtra("mchId", 0);
        mchPhotoUrl = getIntent().getStringExtra("mchPhotoUrl");
        String address = getIntent().getStringExtra("address");
        mTvMchName.setText(mchName);
        mTvAddress.setText(address);

        GlideUtil.loadImage(AskQuestionsActivity.this,mchPhotoUrl,mImgMchPhoto);

    }

    @Override
    public void initData() {

        mTvQuestionPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                questionPublish();
            }
        });
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this,mModel);
    }

    @Override
    public void questionAnsweringPublishResult(BackResult res) {
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
                showToast(AskQuestionsActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }


    @Override
    public void getWaitMyAnswerListResult(BackResult res) {

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
    public void showMsg(String msg) {

    }

    @Override
    public void getMyQuestionListResult(BackResult<QuestionAnsweringResponse> res) {

    }

    @Override
    public void answerZanResult(BackResult res) {

    }

    @Override
    public void getUserAskTogetherListResult(BackResult<AskTogetherResponse> res) {

    }

    @Override
    public void ignoreQuestionsAndAnswersResult(BackResult<QuestionAnsweringResponse> res) {

    }

    @Override
    public void getMyAnswerListResult(BackResult res) {

    }

    public void questionPublish() {

        if (validateInternet()) {
            String questionContent = mEdtQuestionContent.getText().toString();

            if (TextUtils.isEmpty(questionContent)) {
                showToast(AskQuestionsActivity.this, "请填写问题内容");
                return;
            }
            QuestionPublishRequest questionPublishRequest = new QuestionPublishRequest();
            questionPublishRequest.setMchId(mchId);
            questionPublishRequest.setContent(questionContent);
            mPresenter.questionAnsweringPublish(questionPublishRequest);
        }
    }
}
