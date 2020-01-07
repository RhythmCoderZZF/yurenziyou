package com.nbhysj.coupon.presenter;

import com.nbhysj.coupon.contract.HotelAndHomestaySearchContract;

/**
 * @auther：hysj created on 2019/03/05
 * description：酒店民宿模块Presenter
 */
public class HotelAndHomestaySearchPresenter extends HotelAndHomestaySearchContract.Presenter {

    @Override
    public void getHomestayScreeningCondition() {
        mRxManager.add(mModel.getHomestayScreeningCondition().subscribe(res -> mView.getHomestayScreeningConditionResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void onStart() {

    }
}
