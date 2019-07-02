package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.response.ArticleWithCateResponse;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.ShopMallHomePageResponse;

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
    }

    interface View extends BaseView {

        void getShopMallHomePageDataResult(BackResult<ShopMallHomePageResponse> res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getShopMallHomePageData();

    }
}