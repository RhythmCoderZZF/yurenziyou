package com.nbhysj.coupon.model;


import com.nbhysj.coupon.contract.ScenicSpotContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.request.MchCollectionRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.MchAlbumResponse;
import com.nbhysj.coupon.model.response.MchBangDanRankingResponse;
import com.nbhysj.coupon.model.response.MchCollectionResponse;
import com.nbhysj.coupon.model.response.MchDetailsResponse;
import com.nbhysj.coupon.model.response.NetFriendAlbumResponse;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.model.response.ScenicSpotResponse;
import com.nbhysj.coupon.model.response.TourGuideBean;
import java.util.HashMap;
import java.util.List;
import io.reactivex.Observable;

/**
 * created by hysj at 2019/06/01.
 * description :景点 Model层
 */

public class ScenicSpotModel implements ScenicSpotContract.Model {

    @Override
    public Observable<BackResult<ScenicSpotHomePageResponse>> getScenicSpotHomePage(String longitude, String latitude) {
        return Api.getInstance().apiService.getScenicSpotHomePage(longitude, latitude).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<ScenicSpotResponse>> findScenicByCate(HashMap<String, String> map) {
        return Api.getInstance().apiService.findScenicByCate(map).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<MchBangDanRankingResponse>> getScenicBangDanRanking(int cityId) {
        return Api.getInstance().apiService.getScenicBangDanRanking(cityId).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<MchDetailsResponse>> getMchDetails(int mchId) {
        return Api.getInstance().apiService.getMchDetails(mchId).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<List<TourGuideBean>>> getTourGuideList(HashMap<String, String> map) {
        return Api.getInstance().apiService.getTourGuideList(map).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<MchAlbumResponse>> getMchAlbumList(int mchId) {
        return Api.getInstance().apiService.getMchAlbumList(mchId).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<NetFriendAlbumResponse>> getNetFriendAlbumList(int mchId, int page, int pageSize) {
        return Api.getInstance().apiService.getNetFriendsAlbumList(mchId, page, pageSize).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<MchCollectionResponse>> mchCollection(MchCollectionRequest mchCollectionRequest) {
        return Api.getInstance().apiService.mchCollection(mchCollectionRequest).compose(RxSchedulers.io_main());
    }
}
