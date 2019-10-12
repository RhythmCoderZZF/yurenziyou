package com.nbhysj.coupon.model;
import com.nbhysj.coupon.contract.OrderCommentContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.request.OrderGroupCommentRequest;
import com.nbhysj.coupon.model.request.OrderPartialCommentRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.OrderGoodsInitResponse;
import com.nbhysj.coupon.model.response.OrderGroupGoodsInitResponse;

import java.util.List;

import io.reactivex.Observable;

/**
 * created by hysj at 2019/10/07.
 * description :订单评价问题 Model层
 */

public class OrderCommentModel implements OrderCommentContract.Model {

    @Override
    public Observable<BackResult<OrderGoodsInitResponse>> getOrderGoodsCommentInit(int orderGoodsId) {
        return Api.getInstance().apiService.getOrderGoodsCommentInit(orderGoodsId).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<OrderGroupGoodsInitResponse>> getOrderGroupGoodsCommentInit(String orderNo) {
        return Api.getInstance().apiService.getOrderGroupGoodsCommentInit(orderNo).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult> orderGoodsComment(OrderPartialCommentRequest orderPartialCommentRequest) {
        return Api.getInstance().apiService.orderGoodsComment(orderPartialCommentRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult> orderGoodsGroupComment( OrderGroupCommentRequest orderGroupCommentRequest) {
        return Api.getInstance().apiService.orderGroupGoodsComment(orderGroupCommentRequest).compose(RxSchedulers.io_main());
    }
}
