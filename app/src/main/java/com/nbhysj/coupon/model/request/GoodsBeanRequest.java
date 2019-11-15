package com.nbhysj.coupon.model.request;

/**
 * @auther：hysj created on 2019/8/30
 * description：商品对象
 */
public class GoodsBeanRequest {

    //商品id
    private int goodsId;

    private String goodsType;

    //商品数量
    private int num;

    //购买时间
    private String priceDate;

    //组合价格日期
    private String date;

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

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
