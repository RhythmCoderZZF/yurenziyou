package com.nbhysj.coupon.model;
import com.nbhysj.coupon.contract.OrderSubmitContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.request.DeleteTravellerInfoRequest;
import com.nbhysj.coupon.model.request.GroupMchOrderSubmitRequest;
import com.nbhysj.coupon.model.request.QueryByTicketRequest;
import com.nbhysj.coupon.model.request.TicketOrderSubmitRequest;
import com.nbhysj.coupon.model.request.TravellerInfoRequest;
import com.nbhysj.coupon.model.request.UseCouponTicketRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.EstimatedPriceResponse;
import com.nbhysj.coupon.model.response.OrderSubmitInitResponse;
import com.nbhysj.coupon.model.response.OrderSubmitResponse;
import com.nbhysj.coupon.model.response.QueryByTicketResponse;
import com.nbhysj.coupon.model.response.TravellerInfoResponse;
import com.nbhysj.coupon.model.response.UseCouponTicketResponse;

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

    @Override
    public Observable<BackResult<OrderSubmitResponse>> ticketOrderSubmit(TicketOrderSubmitRequest ticketOrderSubmitRequest) {
        return Api.getInstance().apiService.ticketOrderSubmit(ticketOrderSubmitRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<OrderSubmitInitResponse>> getGroupMchOrderSubmitInit(int groupId) {
        return Api.getInstance().apiService.getGroupMchOrderSubmitInit(groupId).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<OrderSubmitResponse>> groupMchOrderSubmit(GroupMchOrderSubmitRequest groupMchOrderSubmitRequest) {
        return Api.getInstance().apiService.groupMchOrderSubmit(groupMchOrderSubmitRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<OrderSubmitInitResponse>> getRecreationDatePriceInit(int goodsId) {
        return Api.getInstance().apiService.getRecreationDatePriceInit(goodsId).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<OrderSubmitResponse>> recreationOrderSubmit(TicketOrderSubmitRequest ticketOrderSubmitRequest) {
        return Api.getInstance().apiService.recreationOrderSubmit(ticketOrderSubmitRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<QueryByTicketResponse>> queryByTicket(QueryByTicketRequest queryByTicketRequest) {
        return Api.getInstance().apiService.getTicketQueryByDate(queryByTicketRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<UseCouponTicketResponse>> useCouponTicketRequest(UseCouponTicketRequest useCouponTicketRequest) {
        return Api.getInstance().apiService.useCouponTicketRequest(useCouponTicketRequest).compose(RxSchedulers.io_main());
    }
}
