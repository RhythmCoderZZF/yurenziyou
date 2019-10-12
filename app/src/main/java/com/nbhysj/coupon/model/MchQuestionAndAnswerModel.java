package com.nbhysj.coupon.model;


import com.nbhysj.coupon.contract.MchQuestionAndAnswerContract;
import com.nbhysj.coupon.contract.UserInfoContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.request.QuestionAnsweringPublishRequest;
import com.nbhysj.coupon.model.request.UpdateUserInfoRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.ThirdPartyLoginStatusResponse;
import com.nbhysj.coupon.model.response.UserInfoResponse;
import com.nbhysj.coupon.model.response.WaitMyAnswerResponse;

import io.reactivex.Observable;

/**
 * created by hysj at 2019/10/04.
 * description :商户问题Model层
 */

public class MchQuestionAndAnswerModel implements MchQuestionAndAnswerContract.Model {


    @Override
    public Observable<BackResult> questionAnsweringPublish(QuestionAnsweringPublishRequest questionAnsweringPublishRequest) {
        return Api.getInstance().apiService.questionAnswering(questionAnsweringPublishRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<WaitMyAnswerResponse>> getMchQuestionAndAnswerList(int mchId, int page, int pageSize) {
        return Api.getInstance().apiService.getMchQuestionAndAnswerList(mchId,page,pageSize).compose(RxSchedulers.io_main());
    }
}
