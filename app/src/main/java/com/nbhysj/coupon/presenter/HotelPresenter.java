package com.nbhysj.coupon.presenter;

import com.nbhysj.coupon.contract.HotelContract;

import java.util.HashMap;

/**
 * @auther：hysj created on 2019/06/05
 * description：酒店Presenter
 */
public class HotelPresenter extends HotelContract.Presenter {


    @Override
    public void getHotelHomePage(String longitude, String latitude) {
        mRxManager.add(mModel.getHotelHomePage(longitude, latitude).subscribe(res -> mView.getHotelHomePageResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void findHotelByCate(HashMap<String, String> map) {
        mRxManager.add(mModel.findHotelByCate(map).subscribe(res -> mView.findHotelByCateResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getHotelBangDanRanking(int cityId) {
        mRxManager.add(mModel.getHotelBangDanRanking(cityId).subscribe(res -> mView.getHotelBangDanRankingResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void onStart() {

    }
}
