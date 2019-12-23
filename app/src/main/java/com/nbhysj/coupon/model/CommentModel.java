package com.nbhysj.coupon.model;

import com.nbhysj.coupon.contract.CommentContract;
import com.nbhysj.coupon.contract.OrderListContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.request.OrderCancelRequest;
import com.nbhysj.coupon.model.request.OrderDeleteRequest;
import com.nbhysj.coupon.model.request.PostOprateRequest;
import com.nbhysj.coupon.model.request.PostsCommentRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.MchCommentResponse;
import com.nbhysj.coupon.model.response.PostsCommentResponse;
import com.nbhysj.coupon.model.response.PraiseOrCollectResponse;
import com.nbhysj.coupon.model.response.UserOrderListResponse;

import java.util.Map;

import io.reactivex.Observable;

/**
 * created by hysj at 2019/09/24.
 * description：评论model层
 */

public class CommentModel implements CommentContract.Model
{
    @Override
    public Observable<BackResult> postsCommentRequest(PostsCommentRequest postsCommentRequest) {
        return Api.getInstance().apiService.postsCommentRequest(postsCommentRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<MchCommentResponse>> getMchCommentList(Map<String, Object> map) {
        return Api.getInstance().apiService.getMchCommentList(map).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<PostsCommentResponse>> getAllPostsCommentListByArticleId(int articleId, int pageNo, int pageSize) {
        return Api.getInstance().apiService.getAllPostsCommentListByArticleId(articleId,pageNo,pageSize).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<PraiseOrCollectResponse>> postOprate(PostOprateRequest postOprateRequest) {
        return Api.getInstance().apiService.postOprate(postOprateRequest).compose(RxSchedulers.io_main());
    }
}
