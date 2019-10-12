package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2019/10/7
 * description：单商品初始化接口
 */
public class OrderGoodsInitResponse {

    //订单号
    private String orderNo;

    //商品类型
    private String goodsType;

    private OrderCommentMchBean mch;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public OrderCommentMchBean getMch() {
        return mch;
    }

    public void setMch(OrderCommentMchBean mch) {
        this.mch = mch;
    }
}
