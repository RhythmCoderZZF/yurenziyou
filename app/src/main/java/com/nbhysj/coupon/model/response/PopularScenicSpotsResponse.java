package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2019/4/19
 * description：
 */
public class PopularScenicSpotsResponse {

    private String scenicSpotsPhoto;

    private String scenicSpotsName;

    private String scenicSpotsScore;

    private String scenicSpotsLocation;

    private String scenicSpotsTicketPrice;

    private int commentNum;

    private String scenicSpotsDes;

    //星级
    private int starLevel;

    private int scenicSpotsType;

    public String getScenicSpotsPhoto() {
        return scenicSpotsPhoto;
    }

    public void setScenicSpotsPhoto(String scenicSpotsPhoto) {
        this.scenicSpotsPhoto = scenicSpotsPhoto;
    }

    public String getScenicSpotsName() {
        return scenicSpotsName;
    }

    public void setScenicSpotsName(String scenicSpotsName) {
        this.scenicSpotsName = scenicSpotsName;
    }

    public String getScenicSpotsScore() {
        return scenicSpotsScore;
    }

    public void setScenicSpotsScore(String scenicSpotsScore) {
        this.scenicSpotsScore = scenicSpotsScore;
    }

    public String getScenicSpotsLocation() {
        return scenicSpotsLocation;
    }

    public void setScenicSpotsLocation(String scenicSpotsLocation) {
        this.scenicSpotsLocation = scenicSpotsLocation;
    }

    public String getScenicSpotsTicketPrice() {
        return scenicSpotsTicketPrice;
    }

    public void setScenicSpotsTicketPrice(String scenicSpotsTicketPrice) {
        this.scenicSpotsTicketPrice = scenicSpotsTicketPrice;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public String getScenicSpotsDes() {
        return scenicSpotsDes;
    }

    public void setScenicSpotsDes(String scenicSpotsDes) {
        this.scenicSpotsDes = scenicSpotsDes;
    }

    public int getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(int starLevel) {
        this.starLevel = starLevel;
    }

    public int getScenicSpotsType() {
        return scenicSpotsType;
    }

    public void setScenicSpotsType(int scenicSpotsType) {
        this.scenicSpotsType = scenicSpotsType;
    }
}
