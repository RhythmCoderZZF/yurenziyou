package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/6/15
 * description：行程助手地图路线规划
 */
public class TripRouteMapResponse {

    //某一天
    private List<List<TripMapResponse>> data;

    //全部
    private List<TripMapResponse> tripList;

    public List<TripMapResponse> getTripList() {
        return tripList;
    }

    public void setTripList(List<TripMapResponse> tripList) {
        this.tripList = tripList;
    }

    public List<List<TripMapResponse>> getData() {
        return data;
    }

    public void setData(List<List<TripMapResponse>> data) {
        this.data = data;
    }

    public class TripMapEntity{

        private List<TripMapResponse> data;

        public List<TripMapResponse> getData() {
            return data;
        }

        public void setData(List<TripMapResponse> data) {
            this.data = data;
        }
    }
}
