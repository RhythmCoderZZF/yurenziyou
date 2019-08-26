package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2019/8/22
 * description：商品价格日历
 */
public class GoodsPriceDatesResponse {

    private int id;

    //日期
    private String date;

    //价格
    private int price;

    //可售状态
    private int sellStatus;

    //库存数量
    private int stockNum;

    //最大购买数量
    private int maxBuyNum;

    //是否可预订
    private int isCanBooking;

    //是否选中
    private boolean isSelectDatePrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSellStatus() {
        return sellStatus;
    }

    public void setSellStatus(int sellStatus) {
        this.sellStatus = sellStatus;
    }

    public int getStockNum() {
        return stockNum;
    }

    public void setStockNum(int stockNum) {
        this.stockNum = stockNum;
    }

    public int getMaxBuyNum() {
        return maxBuyNum;
    }

    public void setMaxBuyNum(int maxBuyNum) {
        this.maxBuyNum = maxBuyNum;
    }

    public int getIsCanBookings() {
        return isCanBooking;
    }

    public void setIsCanBooking(int isCanBooking) {
        this.isCanBooking = isCanBooking;
    }

    public int getIsCanBooking() {
        return isCanBooking;
    }

    public boolean isSelectDatePrice() {
        return isSelectDatePrice;
    }

    public void setSelectDatePrice(boolean selectDatePrice) {
        isSelectDatePrice = selectDatePrice;
    }
}
