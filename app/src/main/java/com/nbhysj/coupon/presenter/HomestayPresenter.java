package com.nbhysj.coupon.presenter;

import com.nbhysj.coupon.contract.HomestayContract;
import com.nbhysj.coupon.contract.HotelContract;
import com.nbhysj.coupon.model.request.MchCollectionRequest;

import java.util.HashMap;

/**
 * @auther：hysj created on 2019/06/05
 * description：民宿Presenter
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
    public void getMchHomestayDetail(int mchId) {
        mRxManager.add(mModel.getMchHomestayDetail(mchId).subscribe(res -> mView.getMchHomestayDetailResult(res), e -> mView.showMsg(e.getMessage())));
    }
    @Override
    public void queryMchCouponList(int mchId) {
        mRxManager.add(mModel.queryMchCouponList(mchId).subscribe(res -> mView.queryMchCouponListResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getCoupon(int couponId) {
        mRxManager.add(mModel.getCoupon(couponId).subscribe(res -> mView.getCouponResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getLandlordHomePage(int landlordId) {
        mRxManager.add(mModel.getLandlordHomePage(landlordId).subscribe(res -> mView.getLandlordHomePageResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getLandlordHouseResourceList(int landlordId) {
        mRxManager.add(mModel.getLandlordHouseResourceList(landlordId).subscribe(res -> mView.getLandlordHouseResourceListResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getHomestayListByCateId(HashMap<String, String> map)
    {
      mRxManager.add(mModel.getHomestayListByCateId(map).subscribe(res -> mView.getHomestayListByCateIdResult(res), e -> mView.showMsg(e.getMessage())));
}

    @Override
    public void onStart() {

    }
}
