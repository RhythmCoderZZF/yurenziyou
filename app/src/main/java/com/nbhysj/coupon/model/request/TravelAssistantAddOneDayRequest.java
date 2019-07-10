package com.nbhysj.coupon.model.request;

/**
 * @auther：hysj created on 2019/6/14
 * description：行程助手(增加一天)
 */
public class TravelAssistantAddOneDayRequest {

    //行程id
    private int tripId;

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }
}
