package com.nbhysj.coupon.model;


import com.nbhysj.coupon.contract.FineFoodContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.MchBangDanRankingResponse;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.model.response.ScenicSpotResponse;

import java.util.HashMap;

import io.reactivex.Observable;

/**
 * created by hysj at 2019/06/01.
 * description :美食 Model层
 */

public class FineFoodModel implements FineFoodContract.Model {

    @Override
    public Observable<BackResult<ScenicSpotHomePageResponse>> getFineFoodHomePage(String longitude, String latitude) {
        return Api.getInstance().apiService.getFineFoodHomePage(longitude, latitude).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<ScenicSpotResponse>> findFoodByCate(HashMap<String, String> map) {
        return Api.getInstance().apiService.findFoodByCate(map).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<MchBangDanRankingResponse>> getScenicBangDanRanking(int cityId) {
        return Api.getInstance().apiService.getScenicBangDanRanking(cityId).compose(RxSchedulers.io_main());
    }
}
