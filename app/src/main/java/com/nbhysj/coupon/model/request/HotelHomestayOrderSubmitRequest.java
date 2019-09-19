package com.nbhysj.coupon.model.request;

/**
 * @auther：hysj created on 2019/9/19
 * description：酒店民宿下单
 */
public class HotelHomestayOrderSubmitRequest {

    //商品id
    private int goodsId;

    //入住和离店时间
    private String checkInAndOutDate;

    //旅客手机号
    private String mobile;

    //旅客姓名
    private String name;

    //房间数量
    private int num;

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getCheckInAndOutDate() {
        return checkInAndOutDate;
    }

    public void setCheckInAndOutDate(String checkInAndOutDate) {
        this.checkInAndOutDate = checkInAndOutDate;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
