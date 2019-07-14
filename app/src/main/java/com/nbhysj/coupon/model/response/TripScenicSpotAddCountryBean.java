package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/6/14
 * description：行程助手根据城市添加景点或者住宿
 */
public class TripScenicSpotAddCountryBean {

    private List<TravelAssistantAddScenicSpotEntity> result;

    private BasePaginationResult page;

    public List<TravelAssistantAddScenicSpotEntity> getResult() {
        return result;
    }

    public void setResult(List<TravelAssistantAddScenicSpotEntity> result) {
        this.result = result;
    }

    public BasePaginationResult getPage() {
        return page;
    }

    public void setPage(BasePaginationResult page) {
        this.page = page;
    }

    public class TravelAssistantAddScenicSpotEntity {

        private int mchId;

        private String mchName;

        private String mchType;

        private int level;

        private String intro;

        private double score;

        private int commentNum;

        private String photo;

        private String seoDesc;

        private int selectedStatus;

        private double price;

        private String tags;

        public int getMchId() {
            return mchId;
        }

        public void setMchId(int mchId) {
            this.mchId = mchId;
        }

        public String getMchName() {
            return mchName;
        }

        public void setMchName(String mchName) {
            this.mchName = mchName;
        }

        public String getMchType() {
            return mchType;
        }

        public void setMchType(String mchType) {
            this.mchType = mchType;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public double getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getSeoDesc() {
            return seoDesc;
        }

        public void setSeoDesc(String seoDesc) {
            this.seoDesc = seoDesc;
        }

        public int getSelectedStatus() {
            return selectedStatus;
        }

        public void setSelectedStatus(int selectedStatus) {
            this.selectedStatus = selectedStatus;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }


        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
}
