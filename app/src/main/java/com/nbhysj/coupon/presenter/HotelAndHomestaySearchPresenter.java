package com.nbhysj.coupon.presenter;

import com.nbhysj.coupon.contract.HotelAndHomestaySearchContract;

import java.util.HashMap;

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
    public void getMchHotelList(HashMap<String, String> map) {
        mRxManager.add(mModel.getMchHotelList(map).subscribe(res -> mView.getMchHotelListResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void onStart() {

    }
}
