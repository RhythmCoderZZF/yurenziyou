package com.nbhysj.coupon.presenter;

import com.nbhysj.coupon.contract.HomestayContract;
import com.nbhysj.coupon.contract.HotelContract;
import com.nbhysj.coupon.model.request.MchCollectionRequest;

import java.util.HashMap;

/**
 * @auther：hysj created on 2019/06/05
 * description：酒店Presenter
 */
public class HomestayPresenter extends HomestayContract.Presenter {


    @Override
    public void getHomestayHomePage(String longitude, String latitude) {
        mRxManager.add(mModel.getHomestayHomePage(longitude, latitude).subscribe(res -> mView.getHomestayHomePageResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void findHomestayByCate(HashMap<String, String> map) {
        mRxManager.add(mModel.findHomestayByCate(map).subscribe(res -> mView.findHomestayByCateResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getHomestayBangDanRanking(int cityId) {
        mRxManager.add(mModel.getHomestayBangDanRanking(cityId).subscribe(res -> mView.getHomestayBangDanRankingResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void mchCollection(MchCollectionRequest mchCollectionRequest) {
        mRxManager.add(mModel.mchCollection(mchCollectionRequest).subscribe(res -> mView.mchCollectionResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void onStart() {

    }
}
