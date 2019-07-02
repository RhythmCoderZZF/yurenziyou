package com.nbhysj.coupon.model.request;

/**
 * @auther：hysj created on 2019/6/14
 * description：添加商户
 */
public class AddTrafficRequest {

    //交通类型
    private String trafficType;

    //行程id
    private int tripId;

    //天数索引
    private int dayIndex;

    private long trainTime;

    private String startCity;

    private String endCity;

    public String getTrafficType() {
        return trafficType;
    }

    public void setTrafficType(String trafficType) {
        this.trafficType = trafficType;
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

    public long getTrainTime() {
        return trainTime;
    }

    public void setTrainTime(long trainTime) {
        this.trainTime = trainTime;
    }

    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    public String getEndCity() {
        return endCity;
    }

    public void setEndCity(String endCity) {
        this.endCity = endCity;
    }
}
