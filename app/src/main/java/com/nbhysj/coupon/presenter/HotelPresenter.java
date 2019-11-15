package com.nbhysj.coupon.presenter;

import com.nbhysj.coupon.contract.HotelContract;
import com.nbhysj.coupon.model.request.HotelHomestayOrderSubmitRequest;
import com.nbhysj.coupon.model.request.MchCollectionRequest;
import com.nbhysj.coupon.model.request.QueryByTicketRequest;
import com.nbhysj.coupon.model.request.UseCouponTicketRequest;

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
    public void getMchDetails(int mchId) {
        mRxManager.add(mModel.getMchDetails(mchId).subscribe(res -> mView.getMchDetailsResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getHotelHomestayOrderInit(int goodsId, String checkInAndOutTime) {
        mRxManager.add(mModel.getHotelHomestayOrderInit(goodsId,checkInAndOutTime).subscribe(res -> mView.getHotelHomestayOrderInitResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void hotelHomestayOrderSubmit(HotelHomestayOrderSubmitRequest hotelHomestayOrderSubmitRequest) {
        mRxManager.add(mModel.hotelHomestayOrderSubmit(hotelHomestayOrderSubmitRequest).subscribe(res -> mView.hotelHomestayOrderSubmitResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void mchCollection(MchCollectionRequest mchCollectionRequest) {

        mRxManager.add(mModel.mchCollection(mchCollectionRequest).subscribe(res -> mView.mchCollectionResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void queryByTicket(QueryByTicketRequest queryByTicketRequest) {
        mRxManager.add(mModel.queryByTicket(queryByTicketRequest).subscribe(res -> mView.queryByTicketResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void useCouponTicketRequest(UseCouponTicketRequest useCouponTicketRequest) {
        mRxManager.add(mModel.useCouponTicketRequest(useCouponTicketRequest).subscribe(res -> mView.useCouponTicketResult(res), e -> mView.showMsg(e.getMessage())));
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
    public void onStart() {

    }
}
