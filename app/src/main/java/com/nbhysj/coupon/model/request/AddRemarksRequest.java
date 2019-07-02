package com.nbhysj.coupon.model.request;

/**
 * @auther：hysj created on 2019/6/14
 * description：添加商户
 */
public class AddRemarksRequest {

    //备注
    private String note;

    //行程id
    private int tripId;

    //天数索引
    private int dayIndex;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
