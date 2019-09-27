package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/9/26
 * description：订单全部退款
 */
public class OrderAllRefundInitResponse {

    private String orderNo;

    //退款商品总价
    private int price;

    //总扣除价格
    private int totalDeductPrice;

    //退款价格
    private int refundPrice;

    //折扣价格
    private int discountPrice;

    private List<OrderRefundInitResponse> goods;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotalDeductPrice() {
        return totalDeductPrice;
    }

    public void setTotalDeductPrice(int totalDeductPrice) {
        this.totalDeductPrice = totalDeductPrice;
    }

    public int getRefundPrice() {
        return refundPrice;
    }

    public void setRefundPrice(int refundPrice) {
        this.refundPrice = refundPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    public List<OrderRefundInitResponse> getGoods() {
        return goods;
    }

    public void setGoods(List<OrderRefundInitResponse> goods) {
        this.goods = goods;
    }
}
