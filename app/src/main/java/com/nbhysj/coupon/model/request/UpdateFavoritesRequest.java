package com.nbhysj.coupon.model.request;

/**
 * @auther：hysj created on 2019/9/20
 * description：更改专辑
 */
public class UpdateFavoritesRequest {

    //专辑Id
    private int id;

    //专辑标题
    private String title;

    //专辑简介
    private String intro;

    //他人是否可见 1可见 0不可见
    private int visibleStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getVisibleStatus() {
        return visibleStatus;
    }

    public void setVisibleStatus(int visibleStatus) {
        this.visibleStatus = visibleStatus;
    }
}
