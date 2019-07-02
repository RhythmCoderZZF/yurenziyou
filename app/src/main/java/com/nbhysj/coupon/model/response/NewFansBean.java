package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2019/03/21
 * description：新增粉丝对象
 */
public class NewFansBean {

    private String url;

    private String title;

    private String time;

    //回粉状态
    private boolean returnPowderStatus;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isReturnPowderStatus() {
        return returnPowderStatus;
    }

    public void setReturnPowderStatus(boolean returnPowderStatus) {
        this.returnPowderStatus = returnPowderStatus;
    }
}
