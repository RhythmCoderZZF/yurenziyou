package com.nbhysj.coupon.model;


import com.nbhysj.coupon.contract.AccountManagementContract;
import com.nbhysj.coupon.contract.UserInfoContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.request.ThirdPartyLoginCreateUserBind;
import com.nbhysj.coupon.model.request.ThirdPartyLoginRequest;
import com.nbhysj.coupon.model.request.UpdateUserInfoRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.LoginResponse;
import com.nbhysj.coupon.model.response.ThirdPartyLoginResponse;
import com.nbhysj.coupon.model.response.ThirdPartyLoginStatusResponse;
import com.nbhysj.coupon.model.response.UserInfoResponse;

import io.reactivex.Observable;

/**
 * created by hysj at 2019/03/22
 * description :账户管理Model层
 */

public class AccountManagementModel implements AccountManagementContract.Model {

    @Override
    public Observable<BackResult<ThirdPartyLoginStatusResponse>> getThirdPartyLoginStatus(int userId) {
        return Api.getInstance().apiService.getThirdPartyLoginStatus(userId).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<UserInfoResponse>> getUserInfo(int id) {
        return Api.getInstance().apiService.getUserInfo(id).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<LoginResponse>> thirdPartyLoginCreateUserBind(ThirdPartyLoginCreateUserBind thirdPartyLoginCreateUserBind) {
        return Api.getInstance().apiService.thirdPartyLoginCreateUser(thirdPartyLoginCreateUserBind).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult> getRegisterverifyCode(String mobile) {
        return Api.getInstance().apiService.getRegisterVerifyCode(mobile).compose(RxSchedulers.io_main());
    }
}
