package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2019/7/3
 * description：行程助手地图路线规划
 */
public class TripMapResponse {

    private int tripPlaceId;

    private String title;

    private String longitude;

    private String latitude;

    private int dayIndex;

    private int sort;

    public int getTripPlaceId() {
        return tripPlaceId;
    }

    public void setTripPlaceId(int tripPlaceId) {
        this.tripPlaceId = tripPlaceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public int getDayIndex() {
        return dayIndex;
    }

    public void setDayIndex(int dayIndex) {
        this.dayIndex = dayIndex;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
