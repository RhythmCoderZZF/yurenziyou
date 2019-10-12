package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/10/7
 * description：组合商品初始化接口
 */
public class OrderGroupGoodsInitResponse {

    //订单号
    private String orderNo;

    //商品类型
    private String goodsType;

    private List<OrderCommentMchBean> mch;


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

    public List<OrderCommentMchBean> getMch() {
        return mch;
    }

    public void setMch(List<OrderCommentMchBean> mch) {
        this.mch = mch;
    }
}
