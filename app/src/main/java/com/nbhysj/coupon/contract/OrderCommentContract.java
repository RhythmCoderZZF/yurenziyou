package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.request.OrderGroupCommentRequest;
import com.nbhysj.coupon.model.request.OrderPartialCommentRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.OrderGoodsInitResponse;
import com.nbhysj.coupon.model.response.OrderGroupGoodsInitResponse;
import io.reactivex.Observable;

/**
 * @auther：hysj created on 2019/10/07
 * description：订单评价模块
 */
public interface OrderCommentContract {

    /**
     * 数据和业务模型
     **/
    interface Model extends BaseModel {

        //单个商品评价
        Observable<BackResult<OrderGoodsInitResponse>> getOrderGoodsCommentInit(int orderGoodsId);

        //组合商品评价
        Observable<BackResult<OrderGroupGoodsInitResponse>> getOrderGroupGoodsCommentInit(String orderNo);

        //单个商品评论
        Observable<BackResult> orderGoodsComment(OrderPartialCommentRequest orderPartialCommentRequest);

        //组合商品评论
        Observable<BackResult> orderGoodsGroupComment(OrderGroupCommentRequest orderGroupCommentRequest);

    }

    interface View extends BaseView {

        void getOrderGoodsCommentInitResult(BackResult<OrderGoodsInitResponse> res);

        void getOrderGroupGoodsCommentInitResult(BackResult<OrderGroupGoodsInitResponse> res);

        void orderGoodsCommentResult(BackResult res);

        void orderGroupGoodsCommentResult(BackResult res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getOrderGoodsCommentInit(int orderGoodsId);

        //组合商品评价
        public abstract void getOrderGroupGoodsCommentInit(String orderNo);

        //单个评价
        public abstract void orderPartialGoodsComment(OrderPartialCommentRequest orderPartialCommentRequest);

        //组合评价
        public abstract void orderGroupGoodsComment(OrderGroupCommentRequest orderGroupCommentRequest);
    }
}