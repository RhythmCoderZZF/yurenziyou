package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.request.QuestionAnsweringPublishRequest;
import com.nbhysj.coupon.model.request.UpdateUserInfoRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.ThirdPartyLoginStatusResponse;
import com.nbhysj.coupon.model.response.UserInfoResponse;
import com.nbhysj.coupon.model.response.WaitMyAnswerResponse;

import io.reactivex.Observable;
import retrofit2.http.Query;

/**
 * @auther：hysj created on 2019/10/04
 * description：商户问题模块
 */
public interface MchQuestionAndAnswerContract {

    /**
     * 数据和业务模型
     **/
    interface Model extends BaseModel {

        //我要提问-发布问题
        Observable<BackResult> questionAnsweringPublish(QuestionAnsweringPublishRequest questionAnsweringPublishRequest);

        //更多问题列表
        Observable<BackResult<WaitMyAnswerResponse>> getMchQuestionAndAnswerList(int mchId, int page, int pageSize);
    }

    interface View extends BaseView {

        void questionAnsweringPublishResult(BackResult res);

        void getMchQuestionAndAnswerListResult(BackResult<WaitMyAnswerResponse> res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void questionAnsweringPublish(QuestionAnsweringPublishRequest questionAnsweringPublishRequest);

        //更多问题列表
        public abstract void getMchQuestionAndAnswerList(int mchId, int page, int pageSize);
    }
}