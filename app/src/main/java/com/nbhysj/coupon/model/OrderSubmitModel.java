package com.nbhysj.coupon.model;
import com.nbhysj.coupon.contract.OrderSubmitContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.request.EstimatedPriceRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.EstimatedPriceResponse;
import com.nbhysj.coupon.model.response.OrderSubmitInitResponse;
import io.reactivex.Observable;

/**
 * created by hysj at 2019/08/20.
 * description：订单提交model层
 */

public class OrderSubmitModel implements OrderSubmitContract.Model {

    @Override
    public Observable<BackResult<OrderSubmitInitResponse>> getOrderSubmitInit(int goodsId) {
        return Api.getInstance().apiService.getOrderSubmitInit(goodsId).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<EstimatedPriceResponse>> getEstimatedPrice(EstimatedPriceRequest estimatedPriceRequest) {
        return Api.getInstance().apiService.getEstimatedPrice(estimatedPriceRequest).compose(RxSchedulers.io_main());
    }
}
