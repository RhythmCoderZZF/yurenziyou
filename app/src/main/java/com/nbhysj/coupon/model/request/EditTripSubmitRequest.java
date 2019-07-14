package com.nbhysj.coupon.model.request;

/**
 * @auther：hysj created on 2019/6/14
 * description：编辑行程提交
 */
public class EditTripSubmitRequest{

    //行程id
    private int tripId;

    //行程标题
    private String title;

    //行程开始时间
    private String startDate;

    //行程结束时间
    private String endDate;

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
