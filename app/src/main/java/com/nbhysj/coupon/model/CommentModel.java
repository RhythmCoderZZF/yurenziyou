package com.nbhysj.coupon.model;

import com.nbhysj.coupon.contract.CommentContract;
import com.nbhysj.coupon.contract.OrderListContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.request.OrderCancelRequest;
import com.nbhysj.coupon.model.request.OrderDeleteRequest;
import com.nbhysj.coupon.model.request.PostsCommentRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.MchCommentResponse;
import com.nbhysj.coupon.model.response.UserOrderListResponse;

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
    public Observable<BackResult<MchCommentResponse>> getMchCommentList(int mchId) {
        return Api.getInstance().apiService.getMchCommentList(mchId).compose(RxSchedulers.io_main());
    }
}
