package com.nbhysj.coupon.presenter;

import com.nbhysj.coupon.contract.HotelContract;

import java.util.HashMap;

/**
 * @auther：hysj created on 2019/06/05
 * description：酒店Presenter
 */
public class HotelPresenter extends HotelContract.Presenter {


    @Override
    public void getHotelHomestayHomePage(String longitude, String latitude) {
        mRxManager.add(mModel.getHotelHomestayHomePage(longitude, latitude).subscribe(res -> mView.getHotelHomestayHomePageResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void findHotelHomestayByCate(HashMap<String, String> map) {
        mRxManager.add(mModel.findHotelHomestayByCate(map).subscribe(res -> mView.findHotelHomestayByCateResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getHotelHomestayBangDanRanking(int cityId) {
        mRxManager.add(mModel.getHotelHomestayBangDanRanking(cityId).subscribe(res -> mView.getHotelHomestayBangDanRankingResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void onStart() {

    }
}
