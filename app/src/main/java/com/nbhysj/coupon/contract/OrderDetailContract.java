package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.request.DeleteOrderRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.OrderDetailResponse;
import com.nbhysj.coupon.model.response.UserOrderListResponse;

import io.reactivex.Observable;

/**
 * @auther：hysj created on 2019/09/05
 * description：订单详情模块
 */
public interface OrderDetailContract {

    /**
     * 数据和业务模型
     **/
    interface Model extends BaseModel {

        //获取订单详情
        Observable<BackResult<OrderDetailResponse>> getOrderDetail(String orderNo);



    }

    interface View extends BaseView {

        void getOrderDetailResult(BackResult<OrderDetailResponse> res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getOrderDetail(String orderNo);
    }
}