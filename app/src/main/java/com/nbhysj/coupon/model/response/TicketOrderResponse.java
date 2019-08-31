package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2019/8/30
 * description：
 */
public class TicketOrderResponse {

    private String orderNo;

    private String price;

    private String title;

    private long payExprireTime;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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
