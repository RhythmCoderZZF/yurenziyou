package com.nbhysj.coupon.model;
import com.nbhysj.coupon.contract.OrderRefundContract;
import com.nbhysj.coupon.contract.OrderSubmitContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.request.DeleteTravellerInfoRequest;
import com.nbhysj.coupon.model.request.GroupMchOrderSubmitRequest;
import com.nbhysj.coupon.model.request.OrderAllRefundRequest;
import com.nbhysj.coupon.model.request.OrderPartialRefundRequest;
import com.nbhysj.coupon.model.request.TicketOrderSubmitRequest;
import com.nbhysj.coupon.model.request.TravellerInfoRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.EstimatedPriceResponse;
import com.nbhysj.coupon.model.response.OrderAllRefundInitResponse;
import com.nbhysj.coupon.model.response.OrderRefundDetailResponse;
import com.nbhysj.coupon.model.response.OrderRefundInitResponse;
import com.nbhysj.coupon.model.response.OrderSubmitInitResponse;
import com.nbhysj.coupon.model.response.OrderSubmitResponse;
import com.nbhysj.coupon.model.response.TravellerInfoResponse;

import java.util.Map;

import io.reactivex.Observable;

/**
 * created by hysj at 2019/08/20.
 * description：订单退款model层
 */

public class OrderRefundModel implements OrderRefundContract.Model {

    @Override
    public Observable<BackResult<OrderRefundInitResponse>> getOrderPartialRefundDataInit(int orderGoodsId, String goodsType) {
        return Api.getInstance().apiService.getOrderRefundPartialInit(orderGoodsId,goodsType).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult> orderPartialRefundSubmit(OrderPartialRefundRequest orderPartialRefundRequest) {
        return Api.getInstance().apiService.orderPartialRefundSubmit(orderPartialRefundRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<OrderAllRefundInitResponse>> getOrderAllRefundDataInit(String orderNo) {
        return Api.getInstance().apiService.getOrderRefundAllInit(orderNo).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult> orderAllRefundSubmit(OrderAllRefundRequest orderAllRefundRequest) {
        return Api.getInstance().apiService.orderAllRefundSubmit(orderAllRefundRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<OrderRefundDetailResponse>> getOrderRefundDetail(String orderRefundNo) {
        return Api.getInstance().apiService.getOrderRefundDetail(orderRefundNo).compose(RxSchedulers.io_main());
    }
}
