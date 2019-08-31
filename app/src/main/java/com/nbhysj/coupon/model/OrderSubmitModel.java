package com.nbhysj.coupon.model;
import com.nbhysj.coupon.contract.OrderSubmitContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.request.DeleteTravellerInfoRequest;
import com.nbhysj.coupon.model.request.EstimatedPriceRequest;
import com.nbhysj.coupon.model.request.TravellerInfoRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.EstimatedPriceResponse;
import com.nbhysj.coupon.model.response.OrderSubmitInitResponse;
import com.nbhysj.coupon.model.response.TravellerInfoResponse;

import java.util.Map;

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
    public Observable<BackResult<EstimatedPriceResponse>> getEstimatedPrice(Map<String, Object> map) {
        return Api.getInstance().apiService.getEstimatedPrice(map).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<TravellerInfoResponse>> getUserTravellerList(int userId, int page, int pageSize) {
        return Api.getInstance().apiService.getUserTravellerList(userId,page,pageSize).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult> addUserTraveller(TravellerInfoRequest addTravellerRequest) {
        return Api.getInstance().apiService.addTravellerInfo(addTravellerRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult> updateUserTraveller(TravellerInfoRequest updateTravellerRequest) {
        return Api.getInstance().apiService.updateTravellerInfo(updateTravellerRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult> deleteUserTraveller(DeleteTravellerInfoRequest deleteTravellerInfoRequest) {
        return Api.getInstance().apiService.deleteTravellerInfo(deleteTravellerInfoRequest).compose(RxSchedulers.io_main());
    }
}