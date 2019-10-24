package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.request.OrderCancelRequest;
import com.nbhysj.coupon.model.request.OrderDeleteRequest;
import com.nbhysj.coupon.model.request.PostOprateRequest;
import com.nbhysj.coupon.model.request.PostsCommentRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.MchCommentResponse;
import com.nbhysj.coupon.model.response.PostsCommentResponse;
import com.nbhysj.coupon.model.response.PraiseOrCollectResponse;
import com.nbhysj.coupon.model.response.UserOrderListResponse;

import io.reactivex.Observable;

/**
 * @auther：hysj created on 2019/09/24
 * description：评论模块
 */
public interface CommentContract {

    /**
     * 数据和业务模型
     **/
    interface Model extends BaseModel {

        //帖子评论
        Observable<BackResult> postsCommentRequest(PostsCommentRequest postsCommentRequest);

        //获取商户评论列表
        Observable<BackResult<MchCommentResponse>> getMchCommentList(int mchId);

        //获取帖子评论列表
        Observable<BackResult<PostsCommentResponse>> getAllPostsCommentListByArticleId(int articleId, int pageNo, int pageSize);

        //帖子操作: 1点赞帖子 2点赞帖子的评论 postsType
        Observable<BackResult<PraiseOrCollectResponse>> postOprate(PostOprateRequest postOprateRequest);

    }

    interface View extends BaseView {

        void postsCommentResult(BackResult res);

        void getMchCommentListResult(BackResult<MchCommentResponse> res);

        void getAllPostsCommentListByArticleIdResult(BackResult<PostsCommentResponse> res);

        void postOprateResult(BackResult<PraiseOrCollectResponse> res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void postsCommentRequest(PostsCommentRequest postsCommentRequest);

        //获取商户评论列表
        public abstract void getMchCommentList(int mchId);

        //获取帖子评论列表
        public abstract void getAllPostsCommentListByArticleId(int articleId, int pageNo, int pageSize);


        public abstract void postOprate(PostOprateRequest postOprateRequest);

    }
}