package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/9/29
 * description：限时特卖列表
 */
public class LimitedTimeSalePageBean {

    private TimeLimitEntity timeLimit;

    private RecommendEntity recommend;

    public TimeLimitEntity getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(TimeLimitEntity timeLimit) {
        this.timeLimit = timeLimit;
    }

    public RecommendEntity getRecommend() {
        return recommend;
    }

    public void setRecommend(RecommendEntity recommend) {
        this.recommend = recommend;
    }

    public class TimeLimitEntity{

        private int id;

        private String title;

        private long startTime;

        private long endTime;

        private int goodsCount;

        private List<GoodsBean> goods;

        private long ctime;

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

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public int getGoodsCount() {
            return goodsCount;
        }

        public void setGoodsCount(int goodsCount) {
            this.goodsCount = goodsCount;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public long getCtime() {
            return ctime;
        }

        public void setCtime(long ctime) {
            this.ctime = ctime;
        }
    }

    public class RecommendEntity{

        private List<ConscienceRecommendationBean> recreation;

        private List<ConscienceRecommendationBean> hotel;

        private List<ConscienceRecommendationBean> scenic;

        private List<ConscienceRecommendationBean> group;

        public List<ConscienceRecommendationBean> getRecreation() {
            return recreation;
        }

        public void setRecreation(List<ConscienceRecommendationBean> recreation) {
            this.recreation = recreation;
        }

        public List<ConscienceRecommendationBean> getHotel() {
            return hotel;
        }

        public void setHotel(List<ConscienceRecommendationBean> hotel) {
            this.hotel = hotel;
        }

        public List<ConscienceRecommendationBean> getScenic() {
            return scenic;
        }

        public void setScenic(List<ConscienceRecommendationBean> scenic) {
            this.scenic = scenic;
        }

        public List<ConscienceRecommendationBean> getGroup() {
            return group;
        }

        public void setGroup(List<ConscienceRecommendationBean> group) {
            this.group = group;
        }
    }
}
