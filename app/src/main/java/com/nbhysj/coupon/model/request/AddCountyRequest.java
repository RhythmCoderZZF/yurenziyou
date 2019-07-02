package com.nbhysj.coupon.model.request;

import java.util.List;

/**
 * @auther：hysj created on 2019/6/14
 * description：创建行程
 */
public class AddCountyRequest {

    private List<Integer> countyId;

    private int tripId;

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
