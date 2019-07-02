package com.nbhysj.coupon.model.response;


/**
 * @auther：hysj created on 2019/4/1
 * description：热门标签
 */
public class HotTagsTopicBean {

    private int count;

    private String title;

    //主题id
    private int topicId;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

}
