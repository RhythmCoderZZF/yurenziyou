package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.request.OrderCancelRequest;
import com.nbhysj.coupon.model.request.OrderDeleteRequest;
import com.nbhysj.coupon.model.request.PostsCommentRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.MchCommentResponse;
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

    }

    interface View extends BaseView {

        void postsCommentResult(BackResult res);

        void getMchCommentListResult(BackResult<MchCommentResponse> res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void postsCommentRequest(PostsCommentRequest postsCommentRequest);

        //获取商户评论列表
        public abstract void getMchCommentList(int mchId);

    }
}