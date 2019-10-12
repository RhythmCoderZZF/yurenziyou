package com.nbhysj.coupon.model;


import com.nbhysj.coupon.contract.HotelContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.request.HotelHomestayOrderSubmitRequest;
import com.nbhysj.coupon.model.request.MchCollectionRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.HotelOrderInitResponse;
import com.nbhysj.coupon.model.response.MchBangDanRankingResponse;
import com.nbhysj.coupon.model.response.MchCollectionResponse;
import com.nbhysj.coupon.model.response.MchDetailsResponse;
import com.nbhysj.coupon.model.response.OrderSubmitResponse;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.model.response.ScenicSpotResponse;
import java.util.HashMap;
import io.reactivex.Observable;

/**
 * created by hysj at 2019/09/15.
 * description :酒店 Model层
 */

public class HotelModel implements HotelContract.Model {

    @Override
    public Observable<BackResult<ScenicSpotHomePageResponse>> getHotelHomePage(String longitude, String latitude) {
        return Api.getInstance().apiService.getHotelHomePage(longitude, latitude).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<ScenicSpotResponse>> findHotelByCate(HashMap<String, String> map) {
        return Api.getInstance().apiService.findHotelByCate(map).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<MchBangDanRankingResponse>> getHotelBangDanRanking(int cityId) {
        return Api.getInstance().apiService.getHotelBangDanRank(cityId).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<MchDetailsResponse>> getMchDetails(int mchId) {
        return Api.getInstance().apiService.getMchDetails(mchId).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<HotelOrderInitResponse>> getHotelHomestayOrderInit(int goodsId, String checkInAndOutTime) {
        return Api.getInstance().apiService.getHotelHomestayOrderInit(goodsId,checkInAndOutTime).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<OrderSubmitResponse>> hotelHomestayOrderSubmit(HotelHomestayOrderSubmitRequest hotelHomestayOrderSubmitRequest) {
        return Api.getInstance().apiService.hotelHomestayOrderSubmit(hotelHomestayOrderSubmitRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<MchCollectionResponse>> mchCollection(MchCollectionRequest mchCollectionRequest) {
        return Api.getInstance().apiService.mchCollection(mchCollectionRequest).compose(RxSchedulers.io_main());
    }
}
