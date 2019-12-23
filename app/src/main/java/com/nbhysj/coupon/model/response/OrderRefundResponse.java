package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2019/11/27
 * description：订单退款
 */
public class OrderRefundResponse {

    //退款订单号
    private String orderRefundNo;

    public String getOrderRefundNo() {
        return orderRefundNo;
    }

    public void setOrderRefundNo(String orderRefundNo) {
        this.orderRefundNo = orderRefundNo;
    }
}
