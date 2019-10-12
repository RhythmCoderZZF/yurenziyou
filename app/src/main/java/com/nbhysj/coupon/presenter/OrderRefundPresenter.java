package com.nbhysj.coupon.presenter;

import com.nbhysj.coupon.contract.OrderRefundContract;
import com.nbhysj.coupon.contract.OrderSubmitContract;
import com.nbhysj.coupon.model.request.DeleteTravellerInfoRequest;
import com.nbhysj.coupon.model.request.GroupMchOrderSubmitRequest;
import com.nbhysj.coupon.model.request.OrderAllRefundRequest;
import com.nbhysj.coupon.model.request.OrderPartialRefundRequest;
import com.nbhysj.coupon.model.request.TicketOrderSubmitRequest;
import com.nbhysj.coupon.model.request.TravellerInfoRequest;

import java.util.Map;

/**
 * @auther：hysj created on 2019/08/20.
 * description：订单提交 Presenter
 */
public class OrderRefundPresenter extends OrderRefundContract.Presenter {

    @Override
    public void getOrderPartialRefundDataInit(int orderGoodsId, String goodsType) {
        mRxManager.add(mModel.getOrderPartialRefundDataInit(orderGoodsId,goodsType).subscribe(res -> mView.getOrderPartialRefundDataInitResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void orderPartialRefundSubmit(OrderPartialRefundRequest orderPartialRefundRequest) {
        mRxManager.add(mModel.orderPartialRefundSubmit(orderPartialRefundRequest).subscribe(res -> mView.orderPartialRefundSubmitResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getOrderAllRefundDataInit(String orderNo) {
        mRxManager.add(mModel.getOrderAllRefundDataInit(orderNo).subscribe(res -> mView.getOrderAllRefundDataInitResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void orderAllRefundSubmit(OrderAllRefundRequest orderAllRefundRequest) {
        mRxManager.add(mModel.orderAllRefundSubmit(orderAllRefundRequest).subscribe(res -> mView.orderAllRefundSubmitResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getOrderRefundDetail(String orderRefundNo) {
        mRxManager.add(mModel.getOrderRefundDetail(orderRefundNo).subscribe(res -> mView.getOrderRefundDetailResult(res), e -> mView.showMsg(e.getMessage())));
    }
    @Override
    public void onStart() {

    }
}
