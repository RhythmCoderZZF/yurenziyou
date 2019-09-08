package com.nbhysj.coupon.presenter;

import com.nbhysj.coupon.contract.OrderDetailContract;
import com.nbhysj.coupon.contract.OrderListContract;
import com.nbhysj.coupon.model.request.DeleteOrderRequest;

/**
 * @auther：hysj created on 2019/08/20.
 * description：订单提交 Presenter
 */
public class OrderDetailPresenter extends OrderDetailContract.Presenter {

    @Override
    public void getOrderDetail(String orderNo) {
        mRxManager.add(mModel.getOrderDetail(orderNo).subscribe(res -> mView.getOrderDetailResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void onStart() {

    }
}
