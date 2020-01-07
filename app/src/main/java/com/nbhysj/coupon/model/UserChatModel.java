package com.nbhysj.coupon.model;


import com.nbhysj.coupon.contract.UserChatContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.request.ChatMessageReplyRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.UserChatResponse;
import io.reactivex.Observable;

/**
 * created by hysj at 2019/09/15.
 * description :用户聊天 Model层
 */

public class UserChatModel implements UserChatContract.Model {

    @Override
    public Observable<BackResult<UserChatResponse>> getUserChatList(int uid, int page, int pageSize) {
        return Api.getInstance().apiService.getUserChatList(uid, page,pageSize).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult> userReplyChat(ChatMessageReplyRequest chatMessageReplyRequest) {
        return Api.getInstance().apiService.userReplyChat(chatMessageReplyRequest).compose(RxSchedulers.io_main());
    }
}
