package com.nbhysj.coupon.model.response;

import java.io.Serializable;
import java.util.List;

/**
 * @auther：hysj created on 2019/6/13
 * description：行程助手首页
 */
public class TripHomePageResponse implements Serializable {

    //行程列表
    private List<TripEntity> trip;

    //攻略推荐
    private List<StrategyBean> strategy;

    public List<TripEntity> getTrip() {
        return trip;
    }

    public void setTrip(List<TripEntity> trip) {
        this.trip = trip;
    }

    public List<StrategyBean> getStrategy() {
        return strategy;
    }

    public void setStrategy(List<StrategyBean> strategy) {
        this.strategy = strategy;
    }

    public class TripEntity implements Serializable{

        private int id;

        private String title;

        private String startDate;

        private String endDate;

        private String photo;

        private int dayNum;

        private String startPlace;

        private String endPlace;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public int getDayNum() {
            return dayNum;
        }

        public void setDayNum(int dayNum) {
            this.dayNum = dayNum;
        }

        public String getStartPlace() {
            return startPlace;
        }

        public void setStartPlace(String startPlace) {
            this.startPlace = startPlace;
        }

        public String getEndPlace() {
            return endPlace;
        }

        public void setEndPlace(String endPlace) {
            this.endPlace = endPlace;
        }
    }
}
