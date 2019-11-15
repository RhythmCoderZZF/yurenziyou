package com.nbhysj.coupon.model.request;

import java.util.List;

/**
 * @auther：hysj created on 2019/11/08
 * description：智能规划行程
 */
public class IntelligentTripRequest {

    //行程Id
    private int tripId;

    //区县数组
    private List<Integer> countyId;

    public List<Integer> getCountyId() {
        return countyId;
    }

    public void setCountyId(List<Integer> countyId) {
        this.countyId = countyId;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }
}
