package com.nbhysj.coupon.model;


import com.nbhysj.coupon.contract.MchQuestionAndAnswerContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.request.AnswerPublishRequest;
import com.nbhysj.coupon.model.request.AnswerZanRequest;
import com.nbhysj.coupon.model.request.AskTogetherRequest;
import com.nbhysj.coupon.model.request.IgnoreQuestionsAndAnswersRequest;
import com.nbhysj.coupon.model.request.QuestionPublishRequest;
import com.nbhysj.coupon.model.response.AnswerResponse;
import com.nbhysj.coupon.model.response.AskTogetherResponse;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.QuestionAnsweringResponse;
import com.nbhysj.coupon.model.response.QuestionDetailsBean;
import com.nbhysj.coupon.model.response.WaitForMeToAnswerResponse;
import com.nbhysj.coupon.model.response.WaitMyAnswerResponse;

import io.reactivex.Observable;

/**
 * created by hysj at 2019/10/04.
 * description :商户问题Model层
 */

public class MchQuestionAndAnswerModel implements MchQuestionAndAnswerContract.Model {

    @Override
    public Observable<BackResult> questionAnsweringPublish(QuestionPublishRequest questionPublishRequest) {
        return Api.getInstance().apiService.questionAnswering(questionPublishRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<WaitMyAnswerResponse>> getMchQuestionAndAnswerList(int mchId, int page, int pageSize) {
        return Api.getInstance().apiService.getMchQuestionAndAnswerList(mchId,page,pageSize).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<QuestionDetailsBean>> getQuestionDetails(int questionId) {
        return Api.getInstance().apiService.getQuestionDetails(questionId).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult> answerPublish(AnswerPublishRequest answerPublishRequest) {
        return Api.getInstance().apiService.answerPublish(answerPublishRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult> askTogether(AskTogetherRequest askTogetherRequest) {
        return Api.getInstance().apiService.askTogether(askTogetherRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult> answerZanRequest(AnswerZanRequest answerZanRequest) {
        return Api.getInstance().apiService.answerZanRequest(answerZanRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<WaitForMeToAnswerResponse>> getWaitMyAnswerList(int page, int pageSize) {
        return Api.getInstance().apiService.getWaitMyAnswerList(page,pageSize).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<QuestionAnsweringResponse>> getMyQuestionList(int page, int pageSize) {
        return Api.getInstance().apiService.getMyQuestionList(page,pageSize).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<AskTogetherResponse>> getUserAskTogetherList(int page, int pageSize) {
        return Api.getInstance().apiService.getUserAskTogetherList(page,pageSize).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<QuestionAnsweringResponse>> getMyAnswerList(int page, int pageSize) {
        return Api.getInstance().apiService.getMyAnswerList(page,pageSize).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult> ignoreQuestionsAndAnswers(IgnoreQuestionsAndAnswersRequest ignoreQuestionsAndAnswersRequest) {
        return Api.getInstance().apiService.ignoreQuestionsAndAnswersRequest(ignoreQuestionsAndAnswersRequest).compose(RxSchedulers.io_main());
    }
}
