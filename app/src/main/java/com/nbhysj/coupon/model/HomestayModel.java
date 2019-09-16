package com.nbhysj.coupon.model;


import com.nbhysj.coupon.contract.HomestayContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.request.MchCollectionRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.MchBangDanRankingResponse;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.model.response.ScenicSpotResponse;
import java.util.HashMap;
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
    public Observable<BackResult> mchCollection(MchCollectionRequest mchCollectionRequest) {
        return Api.getInstance().apiService.mchCollection(mchCollectionRequest).compose(RxSchedulers.io_main());
    }
}
