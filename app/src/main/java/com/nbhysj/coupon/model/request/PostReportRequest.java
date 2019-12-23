package com.nbhysj.coupon.model.request;

import java.util.List;

/**
 * @auther：hysj created on 2019/11/24
 * description：帖子举报
 */
public class PostReportRequest {

    //帖子Id
    private int postsId;

    //理由
    private String reason;

    private List<String> images;

    public int getPostsId() {
        return postsId;
    }

    public void setPostsId(int postsId) {
        this.postsId = postsId;
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
