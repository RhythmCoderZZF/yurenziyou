package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.request.AnswerAdoptRequest;
import com.nbhysj.coupon.model.request.AnswerPublishRequest;
import com.nbhysj.coupon.model.request.AnswerZanRequest;
import com.nbhysj.coupon.model.request.AskTogetherRequest;
import com.nbhysj.coupon.model.request.IgnoreQuestionsAndAnswersRequest;
import com.nbhysj.coupon.model.request.QuestionPublishRequest;
import com.nbhysj.coupon.model.response.AnswerAdoptStatusResponse;
import com.nbhysj.coupon.model.response.AnswerZanResponse;
import com.nbhysj.coupon.model.response.AskTogetherResponse;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.QuestionAnsweringResponse;
import com.nbhysj.coupon.model.response.QuestionDetailsBean;
import com.nbhysj.coupon.model.response.WaitForMeToAnswerResponse;
import com.nbhysj.coupon.model.response.WaitMyAnswerResponse;
import io.reactivex.Observable;

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
        Observable<BackResult> questionAnsweringPublish(QuestionPublishRequest questionPublishRequest);

        //更多问题列表
        Observable<BackResult<WaitMyAnswerResponse>> getMchQuestionAndAnswerList(int mchId, int page, int pageSize);

        //问答详情
        Observable<BackResult<QuestionDetailsBean>> getQuestionDetails(int questionId);

        //问题回答
        Observable<BackResult> answerPublish(AnswerPublishRequest answerPublishRequest);

        //同问接口
        Observable<BackResult> askTogether(AskTogetherRequest askTogetherRequest);

        //点赞接口
        Observable<BackResult<AnswerZanResponse>> answerZanRequest(AnswerZanRequest answerZanRequest);

        //待我回答
        Observable<BackResult<WaitForMeToAnswerResponse>> getWaitMyAnswerList(int page, int pageSize);

        //我的提问列表
        Observable<BackResult<QuestionAnsweringResponse>> getMyQuestionList(int page, int pageSize);

        //我的同问列表
        Observable<BackResult<AskTogetherResponse>> getUserAskTogetherList(int page, int pageSize);

        //我的回答列表
        Observable<BackResult<QuestionAnsweringResponse>> getMyAnswerList(int page, int pageSize);

        //忽略问题
        Observable<BackResult> ignoreQuestionsAndAnswers(IgnoreQuestionsAndAnswersRequest ignoreQuestionsAndAnswersRequest);

        //采纳问题
        Observable<BackResult> adoptAnswersRequest(AnswerAdoptRequest answerAdoptRequest);
    }

    interface View extends BaseView {

        void questionAnsweringPublishResult(BackResult res);

        void getMchQuestionAndAnswerListResult(BackResult<WaitMyAnswerResponse> res);

        //问答详情
        void getQuestionDetailsResult(BackResult<QuestionDetailsBean> res);

        //问题回答
        void answerPublishResult(BackResult res);

        //同问接口
        void askTogetherResult(BackResult res);

        //点赞接口
        void answerZanResult(BackResult<AnswerZanResponse> res);

        //待我回答
        void getWaitMyAnswerListResult(BackResult<WaitForMeToAnswerResponse> res);

        //我的提问列表
        void getMyQuestionListResult(BackResult<QuestionAnsweringResponse> res);

        //我的同问列表
        void getUserAskTogetherListResult(BackResult<AskTogetherResponse> res);

        //我的回答列表
        void getMyAnswerListResult(BackResult<QuestionAnsweringResponse> res);

        //忽略问题
        void ignoreQuestionsAndAnswersResult(BackResult<QuestionAnsweringResponse> res);

        //采纳问题
        void answersAdoptResult(BackResult<AnswerAdoptStatusResponse> res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void questionAnsweringPublish(QuestionPublishRequest questionPublishRequest);

        //更多问题列表
        public abstract void getMchQuestionAndAnswerList(int mchId, int page, int pageSize);

        //问答详情
        public abstract void getQuestionDetails(int questionId);

        //问答详情
        public abstract void answerPublish(AnswerPublishRequest answerPublishRequest);

        //同问接口
        public abstract void askTogether(AskTogetherRequest askTogetherRequest);

        //点赞接口
        public abstract void answerZanRequest(AnswerZanRequest answerZanRequest);

        //待我回答
        public abstract void getWaitMyAnswerList(int page,int pageSize);

        //我的提问列表
        public abstract void getMyQuestionList(int page,int pageSize);

        //我的同问列表
        public abstract void getUserAskTogetherList(int page,int pageSize);

        //我的回答列表
        public abstract void getMyAnswerList(int page,int pageSize);

        //忽略问题
        public abstract void ignoreQuestionsAndAnswers(IgnoreQuestionsAndAnswersRequest ignoreQuestionsAndAnswersRequest);

        //问题采纳
        public abstract void answersAdopt(AnswerAdoptRequest answerAdoptRequest);
    }
}