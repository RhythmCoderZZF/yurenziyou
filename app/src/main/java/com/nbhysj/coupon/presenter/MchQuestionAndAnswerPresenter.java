package com.nbhysj.coupon.presenter;

import com.nbhysj.coupon.contract.MchQuestionAndAnswerContract;
import com.nbhysj.coupon.contract.UserInfoContract;
import com.nbhysj.coupon.model.request.QuestionAnsweringPublishRequest;
import com.nbhysj.coupon.model.request.UpdateUserInfoRequest;

/**
 * @auther：hysj created on 2019/10/04
 * description：商户问答模块Presenter
 */
public class MchQuestionAndAnswerPresenter extends MchQuestionAndAnswerContract.Presenter {

    @Override
    public void questionAnsweringPublish(QuestionAnsweringPublishRequest questionAnsweringPublishRequest) {
        mRxManager.add(mModel.questionAnsweringPublish(questionAnsweringPublishRequest).subscribe(res -> mView.questionAnsweringPublishResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getMchQuestionAndAnswerList(int mchId, int page, int pageSize) {
        mRxManager.add(mModel.getMchQuestionAndAnswerList(mchId,page,pageSize).subscribe(res -> mView.getMchQuestionAndAnswerListResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void onStart() {

    }
}
