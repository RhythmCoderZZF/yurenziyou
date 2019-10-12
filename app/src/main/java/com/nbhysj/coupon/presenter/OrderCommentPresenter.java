package com.nbhysj.coupon.presenter;
import com.nbhysj.coupon.contract.OrderCommentContract;
import com.nbhysj.coupon.model.request.OrderGroupCommentRequest;
import com.nbhysj.coupon.model.request.OrderPartialCommentRequest;

/**
 * @auther：hysj created on 2019/10/04
 * description：订单评论模块Presenter
 */
public class OrderCommentPresenter extends OrderCommentContract.Presenter {

    @Override
    public void getOrderGoodsCommentInit(int orderGoodsId) {
        mRxManager.add(mModel.getOrderGoodsCommentInit(orderGoodsId).subscribe(res -> mView.getOrderGoodsCommentInitResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getOrderGroupGoodsCommentInit(String orderNo) {
        mRxManager.add(mModel.getOrderGroupGoodsCommentInit(orderNo).subscribe(res -> mView.getOrderGroupGoodsCommentInitResult(res), e -> mView.showMsg(e.getMessage())));
    }


    @Override
    public void orderPartialGoodsComment(OrderPartialCommentRequest orderPartialCommentRequest) {
        mRxManager.add(mModel.orderGoodsComment(orderPartialCommentRequest).subscribe(res -> mView.orderGoodsCommentResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void orderGroupGoodsComment(OrderGroupCommentRequest orderGroupCommentRequest) {

        mRxManager.add(mModel.orderGoodsGroupComment(orderGroupCommentRequest).subscribe(res -> mView.orderGroupGoodsCommentResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void onStart() {

    }
}
