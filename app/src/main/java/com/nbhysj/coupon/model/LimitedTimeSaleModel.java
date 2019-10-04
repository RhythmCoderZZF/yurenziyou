package com.nbhysj.coupon.model;


import com.nbhysj.coupon.contract.LimitedTimeSaleContract;
import com.nbhysj.coupon.contract.RecreationContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.LimitedTimeSalePageBean;
import com.nbhysj.coupon.model.response.MchBangDanRankingResponse;
import com.nbhysj.coupon.model.response.MchDetailsResponse;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.model.response.ScenicSpotResponse;

import java.util.HashMap;

import io.reactivex.Observable;

/**
 * created by hysj at 2019/06/07.
 * description:互动Model层
 */

public class LimitedTimeSaleModel implements LimitedTimeSaleContract.Model {

    @Override
    public Observable<BackResult<LimitedTimeSalePageBean>> getLimitedTimeSalePage() {
        return Api.getInstance().apiService.getLimitedTimeSalePage().compose(RxSchedulers.io_main());
    }
}
