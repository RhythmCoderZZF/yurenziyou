package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.response.ArticleWithCateResponse;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CarH5UrlResponse;
import com.nbhysj.coupon.model.response.ShopMallHomePageResponse;
import com.nbhysj.coupon.model.response.WeatherResponse;

import io.reactivex.Observable;

/**
 * @auther：hysj created on 2019/03/25
 * description：商城首页模块
 */
public interface ShopMallHomePageContract {

    /**
     * 数据和业务模型
     **/
    interface Model extends BaseModel {

        //获取商城首页模块数据
        Observable<BackResult<ShopMallHomePageResponse>> getShopMallHomePageData();

        //用车h5
        Observable<BackResult<CarH5UrlResponse>> getCarH5Url(String startLg, String startLt);

        //获取天气接口
        Observable<BackResult<WeatherResponse>> getWeather(int cityCode);
    }

    interface View extends BaseView {

        void getShopMallHomePageDataResult(BackResult<ShopMallHomePageResponse> res);

        void getCarH5UrlResult(BackResult<CarH5UrlResponse> res);

        //获取天气接口
        void getWeatherResult(BackResult<WeatherResponse> res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getShopMallHomePageData();

        public abstract void getCarH5Url(String startLg, String startLt);

        //获取天气接口
        public abstract void getWeather(int cityCode);

    }
}