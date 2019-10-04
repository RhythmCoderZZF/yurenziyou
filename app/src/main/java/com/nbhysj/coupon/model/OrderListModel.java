package com.nbhysj.coupon.model;
import com.nbhysj.coupon.contract.OrderListContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.request.OrderCancelRequest;
import com.nbhysj.coupon.model.request.OrderDeleteRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.UserOrderListResponse;
import io.reactivex.Observable;
/**
 * created by hysj at 2019/09/05.
 * description：订单列表model层
 */

public class OrderListModel implements OrderListContract.Model
{
    @Override
    public Observable<BackResult<UserOrderListResponse>> getUserOrderList(int page, int pageSize) {
        return Api.getInstance().apiService.getUserOrderList(page,pageSize).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<UserOrderListResponse>> getPendingOrdersList(int page, int pageSize) {
        return Api.getInstance().apiService.getPendingOrdersList(page,pageSize).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult> deleteOrder(OrderDeleteRequest orderDeleteRequest) {
        return Api.getInstance().apiService.deleteOrder(orderDeleteRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult> cancelOrder(OrderCancelRequest cancelOrderRequest) {
        return Api.getInstance().apiService.cancelOrder(cancelOrderRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<UserOrderListResponse>> getAwaitGoingOrderList(int page, int pageSize) {
        return Api.getInstance().apiService.getAwaitGoingList(page,pageSize).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<UserOrderListResponse>> getAwaitCommentOrderList(int page, int pageSize) {
        return Api.getInstance().apiService.getAwaitCommentList(page,pageSize).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<UserOrderListResponse>> getAwaitRefundOrderList(int page, int pageSize) {
        return Api.getInstance().apiService.getAwaitRefundList(page,pageSize).compose(RxSchedulers.io_main());
    }
}
