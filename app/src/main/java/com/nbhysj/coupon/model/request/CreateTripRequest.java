package com.nbhysj.coupon.model.request;

import java.util.List;

/**
 * @auther：hysj created on 2019/6/14
 * description：创建行程
 */
public class CreateTripRequest {

    private List<Integer> countyId;

    private String startDate;

    private String endDate;

    public List<Integer> getCountyId() {
        return countyId;
    }

    public void setCountyId(List<Integer> countyId) {
        this.countyId = countyId;
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
