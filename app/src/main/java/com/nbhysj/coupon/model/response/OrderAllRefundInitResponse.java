package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/9/26
 * description：订单全部退款
 */
public class OrderAllRefundInitResponse {

    private String orderNo;

    private double price;

    //总扣除价格
    private double totalDeductPrice;

    //退款价格
    private double refundPrice;

    private double discountPrice;

    public List<OrderRefundInitResponse> goods;

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

    public double getTotalDeductPrice() {
        return totalDeductPrice;
    }

    public void setTotalDeductPrice(double totalDeductPrice) {
        this.totalDeductPrice = totalDeductPrice;
    }

    public double getRefundPrice() {
        return refundPrice;
    }

    public void setRefundPrice(double refundPrice) {
        this.refundPrice = refundPrice;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public List<OrderRefundInitResponse> getGoods() {
        return goods;
    }

    public void setGoods(List<OrderRefundInitResponse> goods) {
        this.goods = goods;
    }
}
