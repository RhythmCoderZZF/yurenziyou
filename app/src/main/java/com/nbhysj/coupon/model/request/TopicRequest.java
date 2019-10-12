package com.nbhysj.coupon.model.request;

/**
 * @auther：hysj created on 2019/10/6
 * description：主题标签
 */
public class TopicRequest {

    //话题标题
    private String title;

    //话题简介注：如果不写简介就是标题
    private String intro;

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
}
