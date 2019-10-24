package com.nbhysj.coupon.model.response;

import java.io.Serializable;
import java.util.List;

/**
 * @auther：hysj created on 2019/10/16
 * description:分享返回
 */
public class MyPostShareResponse implements Serializable {

    private String year;

    private List<MyPostShareBean> myPosts;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<MyPostShareBean> getMyPosts() {
        return myPosts;
    }

    public void setMyPosts(List<MyPostShareBean> myPosts) {
        this.myPosts = myPosts;
    }
}
