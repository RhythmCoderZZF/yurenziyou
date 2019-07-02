package com.nbhysj.coupon.model;


import com.nbhysj.coupon.contract.HotelContract;
import com.nbhysj.coupon.contract.ScenicSpotContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.ScenicBangDanRankingResponse;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.model.response.ScenicSpotResponse;

import java.util.HashMap;

import io.reactivex.Observable;

/**
 * created by hysj at 2019/06/01.
 * description :酒店 Model层
 */

public class HotelModel implements HotelContract.Model {

    @Override
    public Observable<BackResult<ScenicSpotHomePageResponse>> getHotelHomestayHomePage(String longitude, String latitude) {
        return Api.getInstance().apiService.getHotelHomestayHomePage(longitude, latitude).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<ScenicSpotResponse>> findHotelHomestayByCate(HashMap<String, String> map) {
        return Api.getInstance().apiService.findHotelHomestayByCate(map).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<ScenicBangDanRankingResponse>> getHotelHomestayBangDanRanking(int cityId) {
        return Api.getInstance().apiService.getScenicBangDanRanking(cityId).compose(RxSchedulers.io_main());
    }
}
