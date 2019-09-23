package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.request.MchCollectionRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.MchBangDanRankingResponse;
import com.nbhysj.coupon.model.response.MchHomestayDetailsResponse;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.model.response.ScenicSpotResponse;
import com.nbhysj.coupon.model.response.StrategyResponse;

import java.util.HashMap;

import io.reactivex.Observable;

/**
 * @auther：hysj created on 2019/09/21
 * description：攻略模块
 */
public interface StrategyContract {

    /**
     * 数据和业务模型
     **/
    interface Model extends BaseModel {

        //攻略列表
        Observable<BackResult<StrategyResponse>> findAllStrategy(int pageNo, int pageSize);
    }

    interface View extends BaseView {

        void findAllStrategyResult(BackResult<StrategyResponse> res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void findAllStrategy(int pageNo, int pageSize);
    }
}