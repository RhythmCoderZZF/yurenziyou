package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.request.DeleteTravellerInfoRequest;
import com.nbhysj.coupon.model.request.TicketOrderSubmitRequest;
import com.nbhysj.coupon.model.request.TravellerInfoRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.EstimatedPriceResponse;
import com.nbhysj.coupon.model.response.OrderSubmitInitResponse;
import com.nbhysj.coupon.model.response.OrderSubmitResponse;
import com.nbhysj.coupon.model.response.TravellerInfoResponse;

import java.util.Map;

import io.reactivex.Observable;

/**
 * @auther：hysj created on 2019/03/05
 * description：订单提交模块
 */
public interface OrderSubmitContract {

    /**
     * 数据和业务模型
     **/
    interface Model extends BaseModel {

        //门票订单提交界面(景区日历价格)
        Observable<BackResult<OrderSubmitInitResponse>> getOrderSubmitInit(int goodsId);

        //门票订单提交界面(景区日历价格)
        Observable<BackResult<EstimatedPriceResponse>> getEstimatedPrice(Map<String, Object> map);

        //门票订单提交
        Observable<BackResult<OrderSubmitResponse>> ticketOrderSubmit(TicketOrderSubmitRequest ticketOrderSubmitRequest);

        //获取旅客列表
        Observable<BackResult<TravellerInfoResponse>> getUserTravellerList(int userId, int page, int pageSize);

        //添加旅客
        Observable<BackResult> addUserTraveller(TravellerInfoRequest addTravellerRequest);

        //修改旅客
        Observable<BackResult> updateUserTraveller(TravellerInfoRequest updateTravellerRequest);

        //删除旅客
        Observable<BackResult> deleteUserTraveller(DeleteTravellerInfoRequest deleteTravellerInfoRequest);

    }

    interface View extends BaseView {

        void getOrderSubmitInitResult(BackResult<OrderSubmitInitResponse> res);

        void getEstimatedPriceResult(BackResult<EstimatedPriceResponse> res);

        //订单提交返回
        void ticketOrderSubmitResult(BackResult<OrderSubmitResponse> res);

        //获取旅客列表
        void getUserTravellerListResult(BackResult<TravellerInfoResponse> res);

        void addUserTravellerResult(BackResult res);

        void updateUserTravellerResult(BackResult res);

        void deleteUserTravellerResult(BackResult res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getOrderSubmitInit(int goodsId);

        public abstract void getEstimatedPrice(Map<String, Object> map);

        public abstract void ticketOrderSubmit(TicketOrderSubmitRequest ticketOrderSubmitRequest);

        public abstract void getUserTravellerList(int userId, int page, int pageSize);

        public abstract void addUserTraveller(TravellerInfoRequest addTravellerRequest);

        public abstract void updateUserTraveller(TravellerInfoRequest updateTravellerRequest);

        public abstract void deleteUserTraveller(DeleteTravellerInfoRequest deleteTravellerInfoRequest);
    }
}