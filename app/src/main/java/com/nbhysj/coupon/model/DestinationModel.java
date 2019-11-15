package com.nbhysj.coupon.model;


import com.nbhysj.coupon.contract.DestinationContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.DestinationCityResponse;
import com.nbhysj.coupon.model.response.DestinationResponse;
import com.nbhysj.coupon.model.response.HotScenicSpotResponse;
import com.nbhysj.coupon.model.response.HotTagsTopicBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * created by hysj at 2019/06/01.
 * description :目的地 Model层
 */

public class DestinationModel implements DestinationContract.Model {

    @Override
    public Observable<BackResult<HotScenicSpotResponse>> findMchBycityName(int cityId, int mchType, int page, int pageSize) {
        return Api.getInstance().apiService.findMchBycityName(cityId, mchType, page, pageSize).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<DestinationResponse>> getDestinationHomePage(int cityId) {
        return Api.getInstance().apiService.getDestinationHomePage(cityId).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<List<DestinationCityResponse>>> getDestinationCityTagsList(String type) {
        return Api.getInstance().apiService.getDestinationCityTagsList(type).compose(RxSchedulers.io_main());
    }
}
