package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.request.EstimatedPriceRequest;
import com.nbhysj.coupon.model.request.LoginRequest;
import com.nbhysj.coupon.model.request.ThirdPartyLoginRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.EstimatedPriceResponse;
import com.nbhysj.coupon.model.response.LoginResponse;
import com.nbhysj.coupon.model.response.OrderSubmitInitResponse;
import com.nbhysj.coupon.model.response.ThirdPartyLoginStatusResponse;
import com.nbhysj.coupon.model.response.UserInfoResponse;

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
        Observable<BackResult<EstimatedPriceResponse>> getEstimatedPrice(EstimatedPriceRequest estimatedPriceRequest);
    }

    interface View extends BaseView {

        void getOrderSubmitInitResult(BackResult<OrderSubmitInitResponse> res);

        void getEstimatedPriceResult(BackResult<EstimatedPriceResponse> res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getOrderSubmitInit(int goodsId);

        public abstract void getEstimatedPrice(EstimatedPriceRequest estimatedPriceRequest);
    }
}