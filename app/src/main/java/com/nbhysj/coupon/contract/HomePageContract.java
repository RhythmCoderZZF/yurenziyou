package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.request.PostOprateRequest;
import com.nbhysj.coupon.model.request.QueryByTopicRequest;
import com.nbhysj.coupon.model.request.UpdateUserInfoRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.HomePageResponse;
import com.nbhysj.coupon.model.response.HomePageSubTopicTagReponse;
import com.nbhysj.coupon.model.response.PostInfoDetailResponse;
import com.nbhysj.coupon.model.response.PraiseOrCollectResponse;
import com.nbhysj.coupon.model.response.ThirdPartyLoginStatusResponse;
import com.nbhysj.coupon.model.response.UserInfoResponse;

import io.reactivex.Observable;
import retrofit2.http.Query;

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

        //帖子详情
        Observable<BackResult<PostInfoDetailResponse>> getPostInfo(int id,String postKey,String latitude,String longitude);

        //帖子操作: 1帖子点赞，2帖子收藏，3.帖子评论 postsType
        Observable<BackResult<PraiseOrCollectResponse>> postOprate(PostOprateRequest postOprateRequest);

    }

    interface View extends BaseView {

        void getHomePageIndexResult(BackResult<HomePageResponse> res);

        void queryByTopicResult(BackResult<HomePageResponse> res);

        void getHomeAttentionResult(BackResult<HomePageResponse> res);

        void getPostInfoResult(BackResult<PostInfoDetailResponse> res);

        void postOprateResult(BackResult<PraiseOrCollectResponse> res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getHomePageIndex();

        public abstract void queryByTopic(QueryByTopicRequest queryByTopicRequest);

        //首页关注
        public abstract void getHomeAttention(int page, int pageSize);

        //帖子详情
        public abstract void getPostInfo(int id,String postKey,String latitude,String longitude);

        public abstract void postOprate(PostOprateRequest postOprateRequest);

    }
}