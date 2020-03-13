package com.nbhysj.coupon.model;


import com.nbhysj.coupon.contract.CouponListContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.request.UseCouponTicketRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CouponListBean;
import com.nbhysj.coupon.model.response.CouponsBean;
import com.nbhysj.coupon.model.response.CouponsGetBean;
import com.nbhysj.coupon.model.response.UseCouponTicketResponse;

import java.util.List;

import io.reactivex.Observable;
/**
 * created by hysj at 2019/10/29.
 * description: 优惠券Model层
 */

public class CouponListModel implements CouponListContract.Model {

    @Override
    public Observable<BackResult<CouponListBean>> getCouponList(String type, int page, int pageSize) {
        return Api.getInstance().apiService.getCouponList(type,page,pageSize).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<List<CouponsBean>>> getCouponCenter() {
        return Api.getInstance().apiService.getCouponCenter().compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<CouponsGetBean>> getCoupon(int couponId) {
        return Api.getInstance().apiService.getCoupon(couponId).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<UseCouponTicketResponse>> useCouponTicketRequest(UseCouponTicketRequest useCouponTicketRequest) {
        return Api.getInstance().apiService.useCouponTicketRequest(useCouponTicketRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult> couponExchange(String code) {
        return Api.getInstance().apiService.couponExchange(code).compose(RxSchedulers.io_main());
    }
}
