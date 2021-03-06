package com.nbhysj.coupon.model.request;

import java.util.List;

/**
 * @auther：hysj created on 2019/9/26
 * description：全部退款
 */
public class OrderAllRefundRequest {

    //退款理由
    private String note;

    //退款商品列表
    private List<RefundGoodsEntity> refundGoods;

    //折扣金额
    private String discountPrice;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<RefundGoodsEntity> getRefundGoods() {
        return refundGoods;
    }

    public void setRefundGoods(List<RefundGoodsEntity> refundGoods) {
        this.refundGoods = refundGoods;
    }

    public String getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
    }

    public class RefundGoodsEntity{

        //订单商品Id
        private int orderGoodsId;

        //订单商品类型
        private String goodsType;

        //该商品的退款价格
        private String goodsRefundPrice;

        //退款数量
        private int num;

        public int getOrderGoodsId() {
            return orderGoodsId;
        }

        public void setOrderGoodsId(int orderGoodsId) {
            this.orderGoodsId = orderGoodsId;
        }

        public String getGoodsType() {
            return goodsType;
        }

        public void setGoodsType(String goodsType) {
            this.goodsType = goodsType;
        }

        public String getGoodsRefundPrice() {
            return goodsRefundPrice;
        }

        public void setGoodsRefundPrice(String goodsRefundPrice) {
            this.goodsRefundPrice = goodsRefundPrice;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }
}
