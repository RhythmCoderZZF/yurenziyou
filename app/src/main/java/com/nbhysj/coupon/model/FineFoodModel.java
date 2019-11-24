package com.nbhysj.coupon.model;


import com.nbhysj.coupon.contract.FineFoodContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.request.MchCollectionRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.FoodRecommendListResponse;
import com.nbhysj.coupon.model.response.MchBangDanRankingResponse;
import com.nbhysj.coupon.model.response.MchCateListResponse;
import com.nbhysj.coupon.model.response.MchCollectionResponse;
import com.nbhysj.coupon.model.response.MchDetailsResponse;
import com.nbhysj.coupon.model.response.MchFoodDetailResponse;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.model.response.ScenicSpotResponse;

import java.util.HashMap;

import io.reactivex.Observable;

/**
 * created by hysj at 2019/09/16.
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
    public Observable<BackResult<MchBangDanRankingResponse>> getFoodBangDanRanking(int cityId) {
        return Api.getInstance().apiService.getFoodBangDanRank(cityId).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<MchFoodDetailResponse>> getFoodDetail(int mchId) {
        return Api.getInstance().apiService.getMchFoodDetail(mchId).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<MchCollectionResponse>> mchCollection(MchCollectionRequest mchCollectionRequest) {
        return Api.getInstance().apiService.mchCollection(mchCollectionRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<FoodRecommendListResponse>> getGoodsFoodRecommendList(int mchId) {
        return Api.getInstance().apiService.goodsFoodRecommendList(mchId).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<MchCateListResponse>> findFoodListByCateId(HashMap<String, String> map) {
        return Api.getInstance().apiService.findFoodListByCateId(map).compose(RxSchedulers.io_main());
    }
}
