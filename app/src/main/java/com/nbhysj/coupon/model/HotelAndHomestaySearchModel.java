package com.nbhysj.coupon.model;


import com.nbhysj.coupon.contract.HotelAndHomestaySearchContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.PositionDistanceSearchBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * created by hysj at 2019/03/22
 * description :酒店民宿搜索模块Model层
 */

public class HotelAndHomestaySearchModel implements HotelAndHomestaySearchContract.Model {

    @Override
    public Observable<BackResult<List<PositionDistanceSearchBean>>> getHomestayScreeningCondition() {
        return Api.getInstance().apiService.getHomestayScreeningCondition().compose(RxSchedulers.io_main());
    }
}
