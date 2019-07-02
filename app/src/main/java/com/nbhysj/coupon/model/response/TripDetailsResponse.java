package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/6/15
 * description：行程助手详情
 */
public class TripDetailsResponse {

    private String endDate;

    private String countyId;

    private List<DetailsEntity> details;

    private String countyPhoto;

    private String startDate;

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCountyId() {
        return countyId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId;
    }

    public List<DetailsEntity> getDetails() {
        return details;
    }

    public void setDetails(List<DetailsEntity> details) {
        this.details = details;
    }

    public String getCountyPhoto() {
        return countyPhoto;
    }

    public void setCountyPhoto(String countyPhoto) {
        this.countyPhoto = countyPhoto;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public class DetailsEntity {

        private String tripDate;

        private String week;

        private int dayIndex;

        private String circuit;

        private List<TripDetailsEntity> tripDetails;

        public String getTripDate() {
            return tripDate;
        }

        public void setTripDate(String tripDate) {
            this.tripDate = tripDate;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public int getDayIndex() {
            return dayIndex;
        }

        public void setDayIndex(int dayIndex) {
            this.dayIndex = dayIndex;
        }

        public String getCircuit() {
            return circuit;
        }

        public void setCircuit(String circuit) {
            this.circuit = circuit;
        }

        public List<TripDetailsEntity> getTripDetails() {
            return tripDetails;
        }

        public void setTripDetails(List<TripDetailsEntity> tripDetails) {
            this.tripDetails = tripDetails;
        }
    }

    public class TripDetailsEntity {

        private int tripPlaceId;

        private String distance;

        private String time;

        private String tripType;

        private String tripPlaceType;

        private int countyId;

        private String county;

        private String title;

        private String intro;

        private String photo;

        private String playDuration;

        private int sort;

        private String longitude;

        private String latitude;

        private int dayIndex;

        private String address;

        public int getTripPlaceId() {
            return tripPlaceId;
        }

        public void setTripPlaceId(int tripPlaceId) {
            this.tripPlaceId = tripPlaceId;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTripType() {
            return tripType;
        }

        public void setTripType(String tripType) {
            this.tripType = tripType;
        }

        public String getTripPlaceType() {
            return tripPlaceType;
        }

        public void setTripPlaceType(String tripPlaceType) {
            this.tripPlaceType = tripPlaceType;
        }

        public int getCountyId() {
            return countyId;
        }

        public void setCountyId(int countyId) {
            this.countyId = countyId;
        }

        public String getCounty() {
            return county;
        }

        public void setCounty(String county) {
            this.county = county;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getPlayDuration() {
            return playDuration;
        }

        public void setPlayDuration(String playDuration) {
            this.playDuration = playDuration;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
