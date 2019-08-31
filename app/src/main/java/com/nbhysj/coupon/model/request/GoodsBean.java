package com.nbhysj.coupon.model.request;

/**
 * @auther：hysj created on 2019/8/30
 * description：商品对象
 */
public class GoodsBean {

    //商品id
    private int goodsId;

    //商品数量
    private int num;

    //购买时间
    private String priceDate;

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getPriceDate() {
        return priceDate;
    }

    public void setPriceDate(String priceDate) {
        this.priceDate = priceDate;
    }
}
