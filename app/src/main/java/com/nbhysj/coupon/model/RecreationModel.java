package com.nbhysj.coupon.model;


import com.nbhysj.coupon.contract.RecreationContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.MchBangDanRankingResponse;
import com.nbhysj.coupon.model.response.MchCateListResponse;
import com.nbhysj.coupon.model.response.MchDetailsResponse;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.model.response.ScenicSpotResponse;

import java.util.HashMap;

import io.reactivex.Observable;

/**
 * created by hysj at 2019/06/07.
 * description:互动Model层
 */

public class RecreationModel implements RecreationContract.Model {

    @Override
    public Observable<BackResult<ScenicSpotHomePageResponse>> getRecreationHomePage(String longitude, String latitude) {
        return Api.getInstance().apiService.getRecreationHomePage(longitude, latitude).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<ScenicSpotResponse>> findRecreationByCate(HashMap<String, String> map) {
        return Api.getInstance().apiService.findRecreationByCate(map).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<MchBangDanRankingResponse>> getRecreationDanRanking(int cityId) {
        return Api.getInstance().apiService.getRecreationRanking(cityId).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<MchDetailsResponse>> getRecreationDetail(int mchId) {
        return Api.getInstance().apiService.getMchDetails(mchId).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<MchCateListResponse>> getRecreationListByCateId(HashMap<String, String> map) {
        return Api.getInstance().apiService.getRecreationListByCateId(map).compose(RxSchedulers.io_main());
    }
}
