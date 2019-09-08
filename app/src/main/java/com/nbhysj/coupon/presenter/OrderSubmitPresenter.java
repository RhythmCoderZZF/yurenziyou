package com.nbhysj.coupon.presenter;

import com.nbhysj.coupon.contract.OrderSubmitContract;
import com.nbhysj.coupon.contract.UserInfoContract;
import com.nbhysj.coupon.model.request.DeleteTravellerInfoRequest;
import com.nbhysj.coupon.model.request.EstimatedPriceRequest;
import com.nbhysj.coupon.model.request.TicketOrderSubmitRequest;
import com.nbhysj.coupon.model.request.TravellerInfoRequest;
import com.nbhysj.coupon.model.request.UpdateUserInfoRequest;

import java.util.Map;

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
    public void getEstimatedPrice(Map<String, Object> map) {
        mRxManager.add(mModel.getEstimatedPrice(map).subscribe(res -> mView.getEstimatedPriceResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getUserTravellerList(int userId, int page, int pageSize) {
        mRxManager.add(mModel.getUserTravellerList(userId,page,pageSize).subscribe(res -> mView.getUserTravellerListResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void addUserTraveller(TravellerInfoRequest addTravellerRequest) {
        mRxManager.add(mModel.addUserTraveller(addTravellerRequest).subscribe(res -> mView.addUserTravellerResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void updateUserTraveller(TravellerInfoRequest updateTravellerRequest) {
        mRxManager.add(mModel.updateUserTraveller(updateTravellerRequest).subscribe(res -> mView.updateUserTravellerResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void deleteUserTraveller(DeleteTravellerInfoRequest deleteTravellerInfoRequest) {
        mRxManager.add(mModel.deleteUserTraveller(deleteTravellerInfoRequest).subscribe(res -> mView.deleteUserTravellerResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void ticketOrderSubmit(TicketOrderSubmitRequest ticketOrderSubmitRequest) {
        mRxManager.add(mModel.ticketOrderSubmit(ticketOrderSubmitRequest).subscribe(res -> mView.ticketOrderSubmitResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void onStart() {

    }
}
