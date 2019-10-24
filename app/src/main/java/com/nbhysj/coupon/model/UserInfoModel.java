package com.nbhysj.coupon.model;


import com.nbhysj.coupon.contract.UserInfoContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.request.UpdateUserInfoRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.MyCardResponse;
import com.nbhysj.coupon.model.response.ThirdPartyLoginStatusResponse;
import com.nbhysj.coupon.model.response.UserInfoResponse;

import io.reactivex.Observable;

/**
 * created by hysj at 2019/03/12.
 * description :用户Model层
 */

public class UserInfoModel implements UserInfoContract.Model {

    @Override
    public Observable<BackResult<UserInfoResponse>> getUserInfo(int id) {
        return Api.getInstance().apiService.getUserInfo(id).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult> updateInformation(UpdateUserInfoRequest updateUserInfoRequest) {
        return Api.getInstance().apiService.updateInformation(updateUserInfoRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult> userLogout() {
        return Api.getInstance().apiService.userLogout().compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<ThirdPartyLoginStatusResponse>> getThirdPartyLoginStatus(int userId) {
        return Api.getInstance().apiService.getThirdPartyLoginStatus(userId).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<MyCardResponse>> getMyCard(int userId) {
        return Api.getInstance().apiService.getMyCard(userId).compose(RxSchedulers.io_main());
    }
}
