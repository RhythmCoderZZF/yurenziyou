package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.LimitedTimeSalePageBean;
import com.nbhysj.coupon.model.response.StrategyResponse;

import io.reactivex.Observable;

/**
 * @auther：hysj created on 2019/09/29
 * description：限时特卖模块
 */
public interface LimitedTimeSaleContract {

    /**
     * 数据和业务模型
     **/
    interface Model extends BaseModel {

        //攻略列表
        Observable<BackResult<LimitedTimeSalePageBean>> getLimitedTimeSalePage();
    }

    interface View extends BaseView {

        void getLimitedTimeSalePageResult(BackResult<LimitedTimeSalePageBean> res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getLimitedTimeSalePage();
    }
}