package com.nbhysj.coupon.model.request;

import java.util.List;

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

    //优惠券
    private List<Integer> couponIds;

    //是否用车（0:不用  1:用车）
    private int useCarStatus;

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

    public List<Integer> getCouponIds() {
        return couponIds;
    }

    public void setCouponIds(List<Integer> couponIds) {
        this.couponIds = couponIds;
    }

    public int getUseCarStatus() {
        return useCarStatus;
    }

    public void setUseCarStatus(int useCarStatus) {
        this.useCarStatus = useCarStatus;
    }
}
