package com.nbhysj.coupon.presenter;

import com.nbhysj.coupon.contract.OrderPaymentContract;
import com.nbhysj.coupon.contract.OrderSubmitContract;
import com.nbhysj.coupon.model.request.DeleteTravellerInfoRequest;
import com.nbhysj.coupon.model.request.GroupMchOrderSubmitRequest;
import com.nbhysj.coupon.model.request.TicketOrderSubmitRequest;
import com.nbhysj.coupon.model.request.TravellerInfoRequest;

import java.util.Map;

/**
 * @auther：hysj created on 2019/10/01.
 * description：订单支付 Presenter
 */
public class OrderPaymentPresenter extends OrderPaymentContract.Presenter {

    @Override
    public void orderPayment(String orderNo, String paymentFlag) {
        mRxManager.add(mModel.orderPayment(orderNo,paymentFlag).subscribe(res -> mView.orderPaymentResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void onStart() {

    }
}
