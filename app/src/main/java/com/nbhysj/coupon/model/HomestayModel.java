package com.nbhysj.coupon.model;


import com.nbhysj.coupon.contract.HomestayContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.request.MchCollectionRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CouponsGetBean;
import com.nbhysj.coupon.model.response.HouseResouceResponse;
import com.nbhysj.coupon.model.response.LandlordDetailResonse;
import com.nbhysj.coupon.model.response.MchBangDanRankingResponse;
import com.nbhysj.coupon.model.response.MchCateListResponse;
import com.nbhysj.coupon.model.response.MchCollectionResponse;
import com.nbhysj.coupon.model.response.MchCouponResponse;
import com.nbhysj.coupon.model.response.MchHomestayDetailsResponse;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.model.response.ScenicSpotResponse;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;

/**
 * created by hysj at 2019/9/15.
 * description:民宿 Model层
 */

public class HomestayModel implements HomestayContract.Model {

    @Override
    public Observable<BackResult<ScenicSpotHomePageResponse>> getHomestayHomePage(String longitude, String latitude) {
        return Api.getInstance().apiService.getHomestayHomePage(longitude, latitude).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<ScenicSpotResponse>> findHomestayByCate(HashMap<String, String> map) {
        return Api.getInstance().apiService.findHomestayByCate(map).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<MchBangDanRankingResponse>> getHomestayBangDanRanking(int cityId) {
        return Api.getInstance().apiService.getHomestayBangDanRank(cityId).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<MchCollectionResponse>> mchCollection(MchCollectionRequest mchCollectionRequest) {
        return Api.getInstance().apiService.mchCollection(mchCollectionRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<MchHomestayDetailsResponse>> getMchHomestayDetail(int mchId) {
        return Api.getInstance().apiService.getMchHomestayDetail(mchId).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<List<MchCouponResponse>>> queryMchCouponList(int mchId) {
        return Api.getInstance().apiService.queryMchCouponList(mchId).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<CouponsGetBean>> getCoupon(int couponId) {
        return Api.getInstance().apiService.getCoupon(couponId).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<LandlordDetailResonse>> getLandlordHomePage(int landlordId) {
        return Api.getInstance().apiService.getLandlordHomePage(landlordId).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<HouseResouceResponse>> getLandlordHouseResourceList(int landlordId) {
        return Api.getInstance().apiService.getLandlordHouseResourceList(landlordId).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<MchCateListResponse>> getHomestayListByCateId(HashMap<String, String> map) {
        return Api.getInstance().apiService.findHomestayByCateId(map).compose(RxSchedulers.io_main());
    }
}
