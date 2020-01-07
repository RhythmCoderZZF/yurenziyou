package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.PositionDistanceSearchBean;
import java.util.List;

import io.reactivex.Observable;

/**
 * @auther：hysj created on 2019/09/24
 * description：酒店民宿筛选模块
 */
public interface HotelAndHomestaySearchContract {

    /**
     * 数据和业务模型
     **/
    interface Model extends BaseModel {

        //酒店民宿筛选条件
        Observable<BackResult<List<PositionDistanceSearchBean>>> getHomestayScreeningCondition();

    }

    interface View extends BaseView {

        void getHomestayScreeningConditionResult(BackResult<List<PositionDistanceSearchBean>> res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View>
    {
        //酒店民宿筛选条件
        public abstract void getHomestayScreeningCondition();

    }
}