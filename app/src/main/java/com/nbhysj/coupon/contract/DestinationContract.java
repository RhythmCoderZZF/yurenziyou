package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.DestinationResponse;
import com.nbhysj.coupon.model.response.HotScenicSpotResponse;

import io.reactivex.Observable;

/**
 * @auther：hysj created on 2019/06/05
 * description：目的地模块
 */
public interface DestinationContract {

    /**
     * 数据和业务模型
     **/
    interface Model extends BaseModel {

        Observable<BackResult<HotScenicSpotResponse>> findMchBycityName(int cityId, int mchType, int page, int pageSize);

        Observable<BackResult<DestinationResponse>> getDestinationHomePage(int cityId);
    }

    interface View extends BaseView {

        void findMchBycityNameResult(BackResult<HotScenicSpotResponse> res);

        void getDestinationHomePageResult(BackResult<DestinationResponse> res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void findMchBycityName(int cityId, int mchType, int page, int pageSize);

        public abstract void getDestinationHomePage(int cityId);

    }
}