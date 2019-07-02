package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2019/4/29
 * description：景点附近
 */
public class NearbyScenicSpotsResponse {

    private String scenicSpotsPhoto;

    private String scenicSpotsName;

    private String scenicSpotsScore;

    private String scenicSpotsDistance;

    private String scenicSpotsTicketPrice;

    //星级
    private int starLevel;

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

    public String getScenicSpotsDistance() {
        return scenicSpotsDistance;
    }

    public void setScenicSpotsDistance(String scenicSpotsDistance) {
        this.scenicSpotsDistance = scenicSpotsDistance;
    }

    public String getScenicSpotsTicketPrice() {
        return scenicSpotsTicketPrice;
    }

    public void setScenicSpotsTicketPrice(String scenicSpotsTicketPrice) {
        this.scenicSpotsTicketPrice = scenicSpotsTicketPrice;
    }

    public int getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(int starLevel) {
        this.starLevel = starLevel;
    }
}
