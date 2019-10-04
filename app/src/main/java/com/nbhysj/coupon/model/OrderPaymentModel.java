package com.nbhysj.coupon.model;
import com.nbhysj.coupon.contract.OrderPaymentContract;
import com.nbhysj.coupon.contract.OrderSubmitContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.request.DeleteTravellerInfoRequest;
import com.nbhysj.coupon.model.request.GroupMchOrderSubmitRequest;
import com.nbhysj.coupon.model.request.TicketOrderSubmitRequest;
import com.nbhysj.coupon.model.request.TravellerInfoRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.EstimatedPriceResponse;
import com.nbhysj.coupon.model.response.OrderPaymentResponse;
import com.nbhysj.coupon.model.response.OrderSubmitInitResponse;
import com.nbhysj.coupon.model.response.OrderSubmitResponse;
import com.nbhysj.coupon.model.response.TravellerInfoResponse;

import java.util.Map;

import io.reactivex.Observable;

/**
 * created by hysj at 2019/08/20.
 * description：订单支付model层
 */

public class OrderPaymentModel implements OrderPaymentContract.Model {

    @Override
    public Observable<BackResult<OrderPaymentResponse>> orderPayment(String orderNo, String paymentFlag) {
        return Api.getInstance().apiService.orderPayment(orderNo,paymentFlag).compose(RxSchedulers.io_main());
    }
}
