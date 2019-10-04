package com.nbhysj.coupon.model;


import com.nbhysj.coupon.contract.FindPwdContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.request.FindPwdByEmailRequest;
import com.nbhysj.coupon.model.request.FindPwdByPhoneRequest;
import com.nbhysj.coupon.model.response.BackResult;

import io.reactivex.Observable;

/**
 * created by hysj at 2019/03/05.
 * description : 找回密码Model层
 */

public class FindPwdModel implements FindPwdContract.Model {

    @Override
    public Observable<BackResult<Object>> getSalt(String mobile) {
        return Api.getInstance().apiService.getSalt(mobile).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult> getFindPwdVerifyCode(String mobile) {
        return Api.getInstance().apiService.getFindPwdVerifyCode(mobile).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult> modifyPasswordByMobile(FindPwdByPhoneRequest findPwdRequest) {
        return Api.getInstance().apiService.modifyPasswordByMobile(findPwdRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<Object>> updatePwdByEmailGetSalt(String email) {
        return Api.getInstance().apiService.updatePwdByEmailGetSalt(email).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult> sendEmail(String mobile) {
        return Api.getInstance().apiService.sendEmail(mobile).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult> modifyPasswordByEmail(FindPwdByEmailRequest findPwdByEmailRequest) {
        return Api.getInstance().apiService.modifyPasswordByEmail(findPwdByEmailRequest).compose(RxSchedulers.io_main());
    }


}
