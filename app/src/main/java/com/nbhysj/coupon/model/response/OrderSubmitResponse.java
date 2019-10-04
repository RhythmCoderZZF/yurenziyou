package com.nbhysj.coupon.model.response;

import java.io.Serializable;

/**
 * @auther：hysj created on 2019/9/2
 * description：订单提交返回
 */
public class OrderSubmitResponse implements Serializable {

    //订单号
    private String orderNo;

    //票价格
    private double price;

    //票标题
    private String title;

    //支付过期时间
    private long payExprireTime;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getPayExprireTime() {
        return payExprireTime;
    }

    public void setPayExprireTime(long payExprireTime) {
        this.payExprireTime = payExprireTime;
    }
}
