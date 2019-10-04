package com.nbhysj.coupon.model;


import com.nbhysj.coupon.contract.CommonProblemContract;
import com.nbhysj.coupon.contract.ShopMallHomePageContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.response.ArticleWithCateResponse;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CarH5UrlResponse;
import com.nbhysj.coupon.model.response.ShopMallHomePageResponse;
import com.nbhysj.coupon.model.response.WeatherResponse;

import io.reactivex.Observable;

/**
 * created by hysj at 2019/06/01.
 * description :商城首页 Model层
 */

public class ShopMallHomePageModel implements ShopMallHomePageContract.Model {


    @Override
    public Observable<BackResult<ShopMallHomePageResponse>> getShopMallHomePageData() {
        return Api.getInstance().apiService.getShopMallHomePageData().compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<CarH5UrlResponse>> getCarH5Url(String startLg, String startLt) {
        return Api.getInstance().apiService.getCarH5Url(startLg,startLg).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<WeatherResponse>> getWeather(int cityCode) {
        return Api.getInstance().apiService.getWeather(cityCode).compose(RxSchedulers.io_main());
    }
}
