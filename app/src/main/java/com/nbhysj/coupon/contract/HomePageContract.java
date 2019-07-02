package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.request.QueryByTopicRequest;
import com.nbhysj.coupon.model.request.UpdateUserInfoRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.HomePageResponse;
import com.nbhysj.coupon.model.response.HomePageSubTopicTagReponse;
import com.nbhysj.coupon.model.response.ThirdPartyLoginStatusResponse;
import com.nbhysj.coupon.model.response.UserInfoResponse;

import io.reactivex.Observable;

/**
 * @auther：hysj created on 2019/04/03
 * description：首页模块
 */
public interface HomePageContract {

    /**
     * 数据和业务模型
     **/
    interface Model extends BaseModel {

        //获取首页信息
        Observable<BackResult<HomePageResponse>> getHomePageIndex();

        //获取首页信息根据子标签
        Observable<BackResult<HomePageResponse>> queryByTopic(QueryByTopicRequest queryByTopicRequest);

        //首页关注
        Observable<BackResult<HomePageResponse>> getHomeAttention(int page, int pageSize);

    }

    interface View extends BaseView {

        void getHomePageIndexResult(BackResult<HomePageResponse> res);

        void queryByTopicResult(BackResult<HomePageResponse> res);

        void getHomeAttentionResult(BackResult<HomePageResponse> res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getHomePageIndex();

        public abstract void queryByTopic(QueryByTopicRequest queryByTopicRequest);

        //首页关注
        public abstract void getHomeAttention(int page, int pageSize);

    }
}