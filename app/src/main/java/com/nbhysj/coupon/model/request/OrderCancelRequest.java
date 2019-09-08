package com.nbhysj.coupon.model.request;

/**
 * @auther：hysj created on 2019/9/08
 * description:取消订单
 */
public class OrderCancelRequest {

    //订单号
    private String orderNo;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
