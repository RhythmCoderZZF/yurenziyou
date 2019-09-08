package com.nbhysj.coupon.presenter;

import com.nbhysj.coupon.contract.OrderListContract;
import com.nbhysj.coupon.model.request.OrderCancelRequest;
import com.nbhysj.coupon.model.request.OrderDeleteRequest;

/**
 * @auther：hysj created on 2019/08/20.
 * description：订单提交 Presenter
 */
public class OrderListPresenter extends OrderListContract.Presenter {

    @Override
    public void getUserOrderList(int page, int pageSize) {
        mRxManager.add(mModel.getUserOrderList(page,pageSize).subscribe(res -> mView.getUserOrderListResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getPendingOrdersList(int page, int pageSize) {
        mRxManager.add(mModel.getPendingOrdersList(page,pageSize).subscribe(res -> mView.getPendingOrdersListResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void deleteOrder(OrderDeleteRequest orderDeleteRequest) {
        mRxManager.add(mModel.deleteOrder(orderDeleteRequest).subscribe(res -> mView.deleteOrderResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void cancelOrder(OrderCancelRequest orderCancelRequest) {
        mRxManager.add(mModel.cancelOrder(orderCancelRequest).subscribe(res -> mView.cancelOrderResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void onStart() {

    }
}
