package com.nbhysj.coupon.model;
import com.nbhysj.coupon.contract.OrderDetailContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.OrderDetailResponse;
import io.reactivex.Observable;

/**
 * created by hysj at 2019/09/07.
 * description：订单详情model层
 */

public class OrderDetailModel implements OrderDetailContract.Model
{
    @Override
    public Observable<BackResult<OrderDetailResponse>> getOrderDetail(String orderNo) {
        return Api.getInstance().apiService.getOrderDetail(orderNo).compose(RxSchedulers.io_main());
    }
}
