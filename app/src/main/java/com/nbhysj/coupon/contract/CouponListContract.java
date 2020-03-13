package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.request.UseCouponTicketRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CouponListBean;
import com.nbhysj.coupon.model.response.CouponsBean;
import com.nbhysj.coupon.model.response.CouponsGetBean;
import com.nbhysj.coupon.model.response.UseCouponTicketResponse;

import java.util.List;

import io.reactivex.Observable;

/**
 * @auther：hysj created on 2019/10/29
 * description：优惠券模块
 */
public interface CouponListContract {

    /**
     * 数据和业务模型
     **/
    interface Model extends BaseModel {

        //获取优惠券列表
        Observable<BackResult<CouponListBean>> getCouponList(String type, int page, int pageSize);

        //获取惠券中心
        Observable<BackResult<List<CouponsBean>>> getCouponCenter();

        //领取优惠券
        Observable<BackResult<CouponsGetBean>> getCoupon(int couponId);

        //选择使用优惠券
        Observable<BackResult<UseCouponTicketResponse>> useCouponTicketRequest(UseCouponTicketRequest useCouponTicketRequest);

        //优惠券兑换
        Observable<BackResult> couponExchange(String code);
    }

    interface View extends BaseView {

        void getCouponListResult(BackResult<CouponListBean> res);

        void getCouponCenterResult(BackResult<List<CouponsBean>> res);

        void getCouponResult(BackResult<CouponsGetBean> res);

        void useCouponTicketResult(BackResult<UseCouponTicketResponse> res);

        void couponExchangeResult(BackResult res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getCouponList(String type,int page,int pageSize);

        public abstract void getCouponCenter();

        public abstract void getCoupon(int couponId);

        public abstract void useCouponTicketRequest(UseCouponTicketRequest useCouponTicketRequest);

        public abstract void couponExchange(String code);
    }
}