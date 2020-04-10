package com.nbhysj.coupon.model.request;

/**
 * @auther：hysj created on 2020/4/1
 * description：
 */
public class GetRongYunTokenRequest {

    private String userId;

    private String userName;

    private String avater;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }
}
