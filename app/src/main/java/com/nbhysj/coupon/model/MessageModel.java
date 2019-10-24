package com.nbhysj.coupon.model;


import com.nbhysj.coupon.contract.MessageContract;
import com.nbhysj.coupon.contract.MineContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.UserFansFollowResponse;
import com.nbhysj.coupon.model.response.FollowUserStatusResponse;

import io.reactivex.Observable;

/**
 * created by hysj at 2019/10/18.
 * description :消息模块Model层
 */

public class MessageModel implements MessageContract.Model {

    @Override
    public Observable<BackResult<UserFansFollowResponse>> getUserFansList() {
        return Api.getInstance().apiService.getUserFansList().compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<FollowUserStatusResponse>> userFollow(int userId) {
        return Api.getInstance().apiService.userFollow(userId).compose(RxSchedulers.io_main());
    }
}
