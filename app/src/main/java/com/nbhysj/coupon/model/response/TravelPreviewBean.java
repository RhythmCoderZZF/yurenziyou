package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/5/16
 * description：行程助手对象
 */
public class TravelPreviewBean {

    private String date;

    private List<TravelPreviewEntity> travelPreviewEntityList;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<TravelPreviewEntity> getTravelPreviewEntityList() {
        return travelPreviewEntityList;
    }

    public void setTravelPreviewEntityList(List<TravelPreviewEntity> travelPreviewEntityList) {
        this.travelPreviewEntityList = travelPreviewEntityList;
    }

    public class TravelPreviewEntity {

        private String destination;

        private String time;

        public String getDestination() {
            return destination;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
