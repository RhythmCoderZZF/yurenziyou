package com.nbhysj.coupon.model;


import com.nbhysj.coupon.contract.MessageContract;
import com.nbhysj.coupon.contract.MineContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.response.AttentionResponse;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BroadcastResponse;
import com.nbhysj.coupon.model.response.CommentAndAnswerResponse;
import com.nbhysj.coupon.model.response.MessageResponse;
import com.nbhysj.coupon.model.response.UserFansFollowResponse;
import com.nbhysj.coupon.model.response.FollowUserStatusResponse;
import com.nbhysj.coupon.model.response.UserFollowResponse;
import com.nbhysj.coupon.model.response.ZanAndCollectionResponse;

import io.reactivex.Observable;

/**
 * created by hysj at 2019/10/18.
 * description :消息模块Model层
 */

public class MessageModel implements MessageContract.Model {

    @Override
    public Observable<BackResult<UserFansFollowResponse>> getUserFansList(int mPageNo,int mPageSize) {
        return Api.getInstance().apiService.getUserFansList(mPageNo,mPageSize).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<FollowUserStatusResponse>> userFollow(int userId) {
        return Api.getInstance().apiService.userFollow(userId).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<AttentionResponse>> getAttentionInit() {
        return Api.getInstance().apiService.getAttentionInit().compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<UserFollowResponse>> getUserFollow(int pageNo, int pageSize) {
        return Api.getInstance().apiService.getUserFollow(pageNo,pageSize).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<MessageResponse>> getMessageList() {
        return Api.getInstance().apiService.getMessageList().compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<ZanAndCollectionResponse>> getZanAndCollectionMsg(int pageNo, int pageSize) {
        return Api.getInstance().apiService.getZanAndCollectionMsg(pageNo,pageSize).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<CommentAndAnswerResponse>> getPostsCommentAndAnswer(int pageNo, int pageSize) {
        return Api.getInstance().apiService.getPostsCommentAndAnswer(pageNo,pageSize).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<BroadcastResponse>> getBroadcatMessageList(int pageNo, int pageSize) {
        return Api.getInstance().apiService.getBroadcatMessageList(pageNo,pageSize).compose(RxSchedulers.io_main());
    }
}
