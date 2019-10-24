package com.nbhysj.coupon.model.request;

/**
 * @auther：hysj created on 2019/9/20
 * description：新增专辑
 */
public class CreateFavoritesRequest {

    //专辑标题
    private String title;

    //专辑简介
    private String intro;

    //他人是否可见 1可见 0不可见
    private int visibleStatus;

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
