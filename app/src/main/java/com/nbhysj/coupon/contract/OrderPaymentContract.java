package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.request.DeleteTravellerInfoRequest;
import com.nbhysj.coupon.model.request.GroupMchOrderSubmitRequest;
import com.nbhysj.coupon.model.request.TicketOrderSubmitRequest;
import com.nbhysj.coupon.model.request.TravellerInfoRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.EstimatedPriceResponse;
import com.nbhysj.coupon.model.response.OrderPaymentResponse;
import com.nbhysj.coupon.model.response.OrderSubmitInitResponse;
import com.nbhysj.coupon.model.response.OrderSubmitResponse;
import com.nbhysj.coupon.model.response.TravellerInfoResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Query;

/**
 * @auther：hysj created on 2019/03/05
 * description：订单提交模块
 */
public interface OrderPaymentContract {

    /**
     * 数据和业务模型
     **/
    interface Model extends BaseModel {

        //订单支付
        Observable<BackResult<OrderPaymentResponse>> orderPayment(String orderNo,String paymentFlag);
    }

    interface View extends BaseView {

        void orderPaymentResult(BackResult<OrderPaymentResponse> res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void orderPayment(String orderNo,String paymentFlag);
    }
}