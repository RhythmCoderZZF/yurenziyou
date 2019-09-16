package com.nbhysj.coupon.presenter;

import com.nbhysj.coupon.contract.OrderDetailContract;

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
    public void willingToRecommendScore(String orderNo, int score) {
        mRxManager.add(mModel.willingToRecommendScore(orderNo,score).subscribe(res -> mView.willingToRecommendScoreResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void onStart() {

    }
}
