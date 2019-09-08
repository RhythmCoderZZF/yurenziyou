package com.nbhysj.coupon.model.request;

/**
 * @auther：hysj created on 2019/9/6
 * description:删除订单
 */
public class OrderDeleteRequest {

    //订单号
    private String orderNo;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
