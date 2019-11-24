package com.nbhysj.coupon.presenter;

import com.nbhysj.coupon.contract.MchQuestionAndAnswerContract;
import com.nbhysj.coupon.model.request.AnswerPublishRequest;
import com.nbhysj.coupon.model.request.AnswerZanRequest;
import com.nbhysj.coupon.model.request.AskTogetherRequest;
import com.nbhysj.coupon.model.request.IgnoreQuestionsAndAnswersRequest;
import com.nbhysj.coupon.model.request.QuestionPublishRequest;

/**
 * @auther：hysj created on 2019/10/04
 * description：商户问答模块Presenter
 */
public class MchQuestionAndAnswerPresenter extends MchQuestionAndAnswerContract.Presenter {

    @Override
    public void questionAnsweringPublish(QuestionPublishRequest questionPublishRequest) {
        mRxManager.add(mModel.questionAnsweringPublish(questionPublishRequest).subscribe(res -> mView.questionAnsweringPublishResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getMchQuestionAndAnswerList(int mchId, int page, int pageSize) {
        mRxManager.add(mModel.getMchQuestionAndAnswerList(mchId,page,pageSize).subscribe(res -> mView.getMchQuestionAndAnswerListResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getQuestionDetails(int questionId) {
        mRxManager.add(mModel.getQuestionDetails(questionId).subscribe(res -> mView.getQuestionDetailsResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void answerPublish(AnswerPublishRequest answerPublishRequest) {
        mRxManager.add(mModel.answerPublish(answerPublishRequest).subscribe(res -> mView.answerPublishResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void askTogether(AskTogetherRequest askTogetherRequest) {
        mRxManager.add(mModel.askTogether(askTogetherRequest).subscribe(res -> mView.askTogetherResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void answerZanRequest(AnswerZanRequest answerZanRequest) {
        mRxManager.add(mModel.answerZanRequest(answerZanRequest).subscribe(res -> mView.answerZanResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getWaitMyAnswerList(int page, int pageSize) {
        mRxManager.add(mModel.getWaitMyAnswerList(page,pageSize).subscribe(res -> mView.getWaitMyAnswerListResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getMyQuestionList(int page, int pageSize) {
        mRxManager.add(mModel.getMyQuestionList(page,pageSize).subscribe(res -> mView.getMyQuestionListResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getUserAskTogetherList(int page, int pageSize) {
        mRxManager.add(mModel.getUserAskTogetherList(page,pageSize).subscribe(res -> mView.getUserAskTogetherListResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getMyAnswerList(int page, int pageSize) {
        mRxManager.add(mModel.getMyAnswerList(page,pageSize).subscribe(res -> mView.getMyAnswerListResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void ignoreQuestionsAndAnswers(IgnoreQuestionsAndAnswersRequest ignoreQuestionsAndAnswersRequest) {
        mRxManager.add(mModel.ignoreQuestionsAndAnswers(ignoreQuestionsAndAnswersRequest).subscribe(res -> mView.getMyAnswerListResult(res), e -> mView.showMsg(e.getMessage())));
    }


    @Override
    public void onStart() {

    }
}
