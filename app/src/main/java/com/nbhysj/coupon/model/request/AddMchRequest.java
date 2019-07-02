package com.nbhysj.coupon.model.request;

/**
 * @auther：hysj created on 2019/6/14
 * description：添加商户
 */
public class AddMchRequest {

    //商户id
    private int mchId;

    //行程id
    private int tripId;

    //天数索引
    private int dayIndex;

    public int getMchId() {
        return mchId;
    }

    public void setMchId(int mchId) {
        this.mchId = mchId;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public int getDayIndex() {
        return dayIndex;
    }

    public void setDayIndex(int dayIndex) {
        this.dayIndex = dayIndex;
    }
}
