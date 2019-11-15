package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
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
 * @auther：hysj created on 2019/03/05
 * description：订单提交模块
 */
public interface OrderSubmitContract {

    /**
     * 数据和业务模型
     **/
    interface Model extends BaseModel {

        //互动订单提交界面(互动日历价格)
        Observable<BackResult<OrderSubmitInitResponse>> getRecreationDatePriceInit(int goodsId);

        //互动订单提交
        Observable<BackResult<OrderSubmitResponse>> recreationOrderSubmit(TicketOrderSubmitRequest ticketOrderSubmitRequest);


        //门票订单提交界面(景区日历价格)
        Observable<BackResult<OrderSubmitInitResponse>> getOrderSubmitInit(int goodsId);

        //门票订单提交
        Observable<BackResult<OrderSubmitResponse>> ticketOrderSubmit(TicketOrderSubmitRequest ticketOrderSubmitRequest);

        //用车价格计算
        Observable<BackResult<EstimatedPriceResponse>> getEstimatedPrice(Map<String, Object> map);

        //获取旅客列表
        Observable<BackResult<TravellerInfoResponse>> getUserTravellerList(int userId, int page, int pageSize);

        //添加旅客
        Observable<BackResult> addUserTraveller(TravellerInfoRequest addTravellerRequest);

        //修改旅客
        Observable<BackResult> updateUserTraveller(TravellerInfoRequest updateTravellerRequest);

        //删除旅客
        Observable<BackResult> deleteUserTraveller(DeleteTravellerInfoRequest deleteTravellerInfoRequest);

        //组合订单提交界面(组合价格日历)
        Observable<BackResult<OrderSubmitInitResponse>> getGroupMchOrderSubmitInit(int groupId);

        //组合订单下单
        Observable<BackResult<OrderSubmitResponse>> groupMchOrderSubmit(GroupMchOrderSubmitRequest groupMchOrderSubmitRequest);

        //查询日历价格下可选的所有优惠券
        Observable<BackResult<QueryByTicketResponse>> queryByTicket(QueryByTicketRequest queryByTicketRequest);

        //选择使用优惠券
        Observable<BackResult<UseCouponTicketResponse>> useCouponTicketRequest(UseCouponTicketRequest useCouponTicketRequest);

    }

    interface View extends BaseView {

        void getRecreationDatePriceInitResult(BackResult<OrderSubmitInitResponse> res);

        //订单提交返回
        void recreationOrderSubmitResult(BackResult<OrderSubmitResponse> res);

        void getOrderSubmitInitResult(BackResult<OrderSubmitInitResponse> res);

        //订单提交返回
        void ticketOrderSubmitResult(BackResult<OrderSubmitResponse> res);

        void getEstimatedPriceResult(BackResult<EstimatedPriceResponse> res);

        //获取旅客列表
        void getUserTravellerListResult(BackResult<TravellerInfoResponse> res);

        void addUserTravellerResult(BackResult res);

        void updateUserTravellerResult(BackResult res);

        void deleteUserTravellerResult(BackResult res);

        void getGroupMchOrderSubmitInitResult(BackResult<OrderSubmitInitResponse> res);

        //订单提交返回
        void groupMchOrderSubmitResult(BackResult<OrderSubmitResponse> res);

        //订单提交返回
        void queryByTicketResult(BackResult<QueryByTicketResponse> res);

        void useCouponTicketResult(BackResult<UseCouponTicketResponse> res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getRecreationDatePriceInit(int goodsId);

        public abstract void recreationOrderSubmit(TicketOrderSubmitRequest ticketOrderSubmitRequest);

        public abstract void getOrderSubmitInit(int goodsId);

        public abstract void ticketOrderSubmit(TicketOrderSubmitRequest ticketOrderSubmitRequest);

        public abstract void getEstimatedPrice(Map<String, Object> map);

        public abstract void getUserTravellerList(int userId, int page, int pageSize);

        public abstract void addUserTraveller(TravellerInfoRequest addTravellerRequest);

        public abstract void updateUserTraveller(TravellerInfoRequest updateTravellerRequest);

        public abstract void deleteUserTraveller(DeleteTravellerInfoRequest deleteTravellerInfoRequest);

        public abstract void getGroupMchOrderSubmitInit(int groupId);

        public abstract void groupMchOrderSubmit(GroupMchOrderSubmitRequest groupMchOrderSubmitRequest);

        public abstract void queryByTicket(QueryByTicketRequest queryByTicketRequest);

        public abstract void useCouponTicketRequest(UseCouponTicketRequest useCouponTicketRequest);
    }
}