package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.request.OrderAllRefundRequest;
import com.nbhysj.coupon.model.request.OrderPartialRefundRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.OrderAllRefundInitResponse;
import com.nbhysj.coupon.model.response.OrderRefundDetailResponse;
import com.nbhysj.coupon.model.response.OrderRefundInitResponse;
import com.nbhysj.coupon.model.response.OrderRefundResponse;
import com.nbhysj.coupon.model.response.OrderSubmitInitResponse;
import io.reactivex.Observable;

/**
 * @auther：hysj created on 2019/09/26
 * description：订单退款模块
 */
public interface OrderRefundContract {

    /**
     * 数据和业务模型
     **/
    interface Model extends BaseModel {

        //部分退款订单界面
        Observable<BackResult<OrderRefundInitResponse>> getOrderPartialRefundDataInit(int orderGoodsId, String goodsType);

        //部分退款
        Observable<BackResult<OrderRefundResponse>> orderPartialRefundSubmit(OrderPartialRefundRequest orderPartialRefundRequest);

        //全部退款订单页面
        Observable<BackResult<OrderAllRefundInitResponse>> getOrderAllRefundDataInit(String orderNo);

        //全部退款
        Observable<BackResult<OrderRefundResponse>> orderAllRefundSubmit(OrderAllRefundRequest orderAllRefundRequest);

        //全部退款
        Observable<BackResult<OrderRefundDetailResponse>> getOrderRefundDetail(String orderRefundNo);


    }

    interface View extends BaseView {

        void getOrderPartialRefundDataInitResult(BackResult<OrderRefundInitResponse> res);

        void orderPartialRefundSubmitResult(BackResult<OrderRefundResponse> res);

        void getOrderAllRefundDataInitResult(BackResult<OrderAllRefundInitResponse> res);

        void orderAllRefundSubmitResult(BackResult<OrderRefundResponse> res);

        void getOrderRefundDetailResult(BackResult<OrderRefundDetailResponse> res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getOrderPartialRefundDataInit(int orderGoodsId, String goodsType);

        public abstract void orderPartialRefundSubmit(OrderPartialRefundRequest orderPartialRefundRequest);

        public abstract void getOrderAllRefundDataInit(String orderNo);

        public abstract void orderAllRefundSubmit(OrderAllRefundRequest orderAllRefundRequest);

        public abstract void getOrderRefundDetail(String orderRefundNo);

    }
}