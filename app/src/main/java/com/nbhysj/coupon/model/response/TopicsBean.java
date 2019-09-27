package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2019/9/24
 * description：主题标签
 */
public class TopicsBean {

    private int id;

    private int userId;

    private int homeIsNearby;

    private int del;

    private int status;

    private String intro;

    private String title;

    private Long ctime;

    private Long utime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getHomeIsNearby() {
        return homeIsNearby;
    }

    public void setHomeIsNearby(int homeIsNearby) {
        this.homeIsNearby = homeIsNearby;
    }

    public int getDel() {
        return del;
    }

    public void setDel(int del) {
        this.del = del;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    public Long getUtime() {
        return utime;
    }

    public void setUtime(Long utime) {
        this.utime = utime;
    }
}
