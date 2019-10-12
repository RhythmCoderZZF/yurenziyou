package com.nbhysj.coupon.model.request;

/**
 * @auther：hysj created on 2019/9/26
 * description：部分退款
 */
public class OrderPartialRefundRequest {

    //子订单id
    private int orderGoodsId;

    //折扣金额
    private String discountPrice;

    //总金额  由用户勾选前端计算 pricenumrefundPercentage/100 保留两位小数)
    private String goodsRefundPrice;

    //商品类型
    private String goodsType;

    //退款理由
    private String note;

    //总订单号
    private String orderNo;

    //退款数量
    private int num;

    public int getOrderGoodsId() {
        return orderGoodsId;
    }

    public void setOrderGoodsId(int orderGoodsId) {
        this.orderGoodsId = orderGoodsId;
    }

    public String getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
    }

    public void setGoodsRefundPrice(String goodsRefundPrice) {
        this.goodsRefundPrice = goodsRefundPrice;
    }

    public String getGoodsRefundPrice() {
        return goodsRefundPrice;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
