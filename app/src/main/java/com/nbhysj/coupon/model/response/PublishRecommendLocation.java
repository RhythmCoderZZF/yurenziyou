package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2019/3/18
 * description：推荐位置
 */
public class PublishRecommendLocation {

    //位置
    private String location;

    //具体位置
    private String specificLocation;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSpecificLocation() {
        return specificLocation;
    }

    public void setSpecificLocation(String specificLocation) {
        this.specificLocation = specificLocation;
    }
}
