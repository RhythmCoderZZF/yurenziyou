package com.nbhysj.coupon.model.request;

import java.util.List;

/**
 * @auther：hysj created on 2019/11/25
 * description：用户举报
 */
public class UserReportRequest {

    private int userId;

    private String reason;

    private List<String> images;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
