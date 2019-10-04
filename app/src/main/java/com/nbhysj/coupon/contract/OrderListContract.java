package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.request.OrderCancelRequest;
import com.nbhysj.coupon.model.request.OrderDeleteRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.UserOrderListResponse;

import io.reactivex.Observable;

/**
 * @auther：hysj created on 2019/09/05
 * description：订单列表模块
 */
public interface OrderListContract {

    /**
     * 数据和业务模型
     **/
    interface Model extends BaseModel {

        //获取订单列表
        Observable<BackResult<UserOrderListResponse>> getUserOrderList(int page,int pageSize);

        //获取待付款订单列表
        Observable<BackResult<UserOrderListResponse>> getPendingOrdersList(int page,int pageSize);

        //获取待出行列表
        Observable<BackResult<UserOrderListResponse>> getAwaitGoingOrderList(int page,int pageSize);

        //获取待评价列表
        Observable<BackResult<UserOrderListResponse>> getAwaitCommentOrderList(int page,int pageSize);

        //获取售后列表
        Observable<BackResult<UserOrderListResponse>> getAwaitRefundOrderList(int page,int pageSize);

        //删除订单
        Observable<BackResult> deleteOrder(OrderDeleteRequest deleteOrderRequest);

        //取消订单
        Observable<BackResult> cancelOrder(OrderCancelRequest cancelOrderRequest);

    }

    interface View extends BaseView {

        void getUserOrderListResult(BackResult<UserOrderListResponse> res);

        void getPendingOrdersListResult(BackResult<UserOrderListResponse> res);

        void getAwaitGoingOrderListResult(BackResult<UserOrderListResponse> res);

        void getAwaitCommentOrderListResult(BackResult<UserOrderListResponse> res);

        void getAwaitRefundOrderListResult(BackResult<UserOrderListResponse> res);

        void deleteOrderResult(BackResult res);

        void cancelOrderResult(BackResult res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getUserOrderList(int page,int pageSize);

        public abstract void getPendingOrdersList(int page,int pageSize);

        public abstract void getAwaitGoingOrderList(int page,int pageSize);

        public abstract void getAwaitCommentOrderList(int page,int pageSize);

        public abstract void getAwaitRefundOrderList(int page,int pageSize);

        public abstract void deleteOrder(OrderDeleteRequest orderDeleteRequest);

        public abstract void cancelOrder(OrderCancelRequest orderCancelRequest);

    }
}