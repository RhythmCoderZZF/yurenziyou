package com.nbhysj.coupon.model;


import com.nbhysj.coupon.contract.LoginContract;
import com.nbhysj.coupon.contract.RegisterContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.request.LoginRequest;
import com.nbhysj.coupon.model.request.RegisterUserRequest;
import com.nbhysj.coupon.model.request.ThirdPartyLoginCreateUserBind;
import com.nbhysj.coupon.model.request.ThirdPartyLoginRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.LoginResponse;
import com.nbhysj.coupon.model.response.ThirdPartyLoginResponse;
import com.nbhysj.coupon.model.response.ThirdPartyLoginStatusResponse;
import com.nbhysj.coupon.model.response.UserInfoResponse;

import io.reactivex.Observable;

/**
 * created by hysj at 2019/03/05.
 * description : 登录Model层
 */

public class LoginModel implements LoginContract.Model {

    @Override
    public Observable<BackResult> getLoginVerifyCode(String mobile) {
        return Api.getInstance().apiService.getLoginVerifyCode(mobile).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<String>> getLoginSalt(String mobile) {
        return Api.getInstance().apiService.getLoginSalt(mobile).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<LoginResponse>> login(LoginRequest loginRequest) {
        return Api.getInstance().apiService.login(loginRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<UserInfoResponse>> getUserInfo(int id) {
        return Api.getInstance().apiService.getUserInfo(id).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<ThirdPartyLoginStatusResponse>> getThirdPartyLoginStatus(int userId) {
        return Api.getInstance().apiService.getThirdPartyLoginStatus(userId).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<LoginResponse>> thirdPartyLogin(ThirdPartyLoginRequest thirdPartyLoginRequest) {
        return Api.getInstance().apiService.thirdPartyLogin(thirdPartyLoginRequest).compose(RxSchedulers.io_main());
    }
}
