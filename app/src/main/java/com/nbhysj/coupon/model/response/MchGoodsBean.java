package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2019/6/13
 * description：商品订单对象
 */
public class MchGoodsBean {

    private int goodsId;

    private String title;

    private int marketPrice;

    private int defaultPrice;

    private String bookingInfo;

    private int sellNum;

    private String refundSettings;

    private String ticketIntoType;

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(int marketPrice) {
        this.marketPrice = marketPrice;
    }

    public int getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(int defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public String getBookingInfo() {
        return bookingInfo;
    }

    public void setBookingInfo(String bookingInfo) {
        this.bookingInfo = bookingInfo;
    }

    public int getSellNum() {
        return sellNum;
    }

    public void setSellNum(int sellNum) {
        this.sellNum = sellNum;
    }

    public String getRefundSettings() {
        return refundSettings;
    }

    public void setRefundSettings(String refundSettings) {
        this.refundSettings = refundSettings;
    }

    public String getTicketIntoType() {
        return ticketIntoType;
    }

    public void setTicketIntoType(String ticketIntoType) {
        this.ticketIntoType = ticketIntoType;
    }
}
