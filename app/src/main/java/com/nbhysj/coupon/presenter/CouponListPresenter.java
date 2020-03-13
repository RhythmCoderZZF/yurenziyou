package com.nbhysj.coupon.presenter;

import com.nbhysj.coupon.contract.CouponListContract;
import com.nbhysj.coupon.contract.UserInfoContract;
import com.nbhysj.coupon.model.request.UpdateUserInfoRequest;
import com.nbhysj.coupon.model.request.UseCouponTicketRequest;

/**
 * @auther：hysj created on 2019/11/01
 * description：优惠券Presenter
 */
public class CouponListPresenter extends CouponListContract.Presenter {

    @Override
    public void getCouponList(String type,int page,int pageSize) {
        mRxManager.add(mModel.getCouponList(type,page,pageSize).subscribe(res -> mView.getCouponListResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getCouponCenter() {
        mRxManager.add(mModel.getCouponCenter().subscribe(res -> mView.getCouponCenterResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getCoupon(int couponId) {
        mRxManager.add(mModel.getCoupon(couponId).subscribe(res -> mView.getCouponResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void useCouponTicketRequest(UseCouponTicketRequest useCouponTicketRequest) {
        mRxManager.add(mModel.useCouponTicketRequest(useCouponTicketRequest).subscribe(res -> mView.useCouponTicketResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void couponExchange(String code) {
        mRxManager.add(mModel.couponExchange(code).subscribe(res -> mView.couponExchangeResult(res), e -> mView.showMsg(e.getMessage())));
    }


    @Override
    public void onStart() {

    }
}
