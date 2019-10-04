package com.nbhysj.coupon.model;


import com.nbhysj.coupon.contract.RegisterContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.request.LoginRequest;
import com.nbhysj.coupon.model.request.RegisterUserRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.LoginResponse;
import com.nbhysj.coupon.model.response.UserInfoResponse;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * created by hysj at 2019/03/05.
 * description :注册Model层
 */

public class RegisterModel implements RegisterContract.Model {

    @Override
    public Observable<BackResult> getRegisterverifyCode(String mobile) {
        return Api.getInstance().apiService.getRegisterVerifyCode(mobile).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult> registerUser(RegisterUserRequest registerUserRequest) {
        return Api.getInstance().apiService.registerUser(registerUserRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<Object>> getSalt(String mobile) {
        return Api.getInstance().apiService.getSalt(mobile).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<LoginResponse>> login(LoginRequest loginRequest) {
        return Api.getInstance().apiService.login(loginRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<UserInfoResponse>> getUserInfo(int userId) {
        return Api.getInstance().apiService.getUserInfo(userId).compose(RxSchedulers.io_main());
    }
}
