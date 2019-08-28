package com.nbhysj.coupon.presenter;

import com.nbhysj.coupon.contract.OrderSubmitContract;
import com.nbhysj.coupon.contract.UserInfoContract;
import com.nbhysj.coupon.model.request.EstimatedPriceRequest;
import com.nbhysj.coupon.model.request.UpdateUserInfoRequest;

/**
 * @auther：hysj created on 2019/08/20.
 * description：订单提交 Presenter
 */
public class OrderSubmitPresenter extends OrderSubmitContract.Presenter {

    @Override
    public void getOrderSubmitInit(int goodsId) {
        mRxManager.add(mModel.getOrderSubmitInit(goodsId).subscribe(res -> mView.getOrderSubmitInitResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getEstimatedPrice(EstimatedPriceRequest estimatedPriceRequest) {
        mRxManager.add(mModel.getEstimatedPrice(estimatedPriceRequest).subscribe(res -> mView.getEstimatedPriceResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void onStart() {

    }
}
