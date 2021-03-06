package com.nbhysj.coupon.presenter;

import com.nbhysj.coupon.contract.ShopMallHomePageContract;

/**
 * @auther：hysj created on 2019/06/01
 * description：商城首页Presenter
 */
public class ShopMallHomePagePresenter extends ShopMallHomePageContract.Presenter {

    @Override
    public void getShopMallHomePageData() {
        mRxManager.add(mModel.getShopMallHomePageData().subscribe(res -> mView.getShopMallHomePageDataResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void onStart() {

    }

    @Override
    public void getCarH5Url(String startLg, String startLt) {
        mRxManager.add(mModel.getCarH5Url(startLg,startLt).subscribe(res -> mView.getCarH5UrlResult(res), e -> mView.showMsg(e.getMessage())));
    }


    @Override
    public void getWeather(int cityCode) {
        mRxManager.add(mModel.getWeather(cityCode).subscribe(res -> mView.getWeatherResult(res), e -> mView.showMsg(e.getMessage())));
    }

}
