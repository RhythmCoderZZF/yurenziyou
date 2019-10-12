package com.nbhysj.coupon.model.response;


import java.io.Serializable;

/**
 * @auther：hysj created on 2019/4/1
 * description：热门标签
 */
public class HotTagsTopicBean implements Serializable {

    //标签id
    private int id;

    //标签主题
    private String title;

    private int postsCount;

    private int ctime;

    //标签是否选择
    private boolean isHotTagsTopicSelect;

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

    public int getPostsCount() {
        return postsCount;
    }

    public void setPostsCount(int postsCount) {
        this.postsCount = postsCount;
    }

    public int getCtime() {
        return ctime;
    }

    public void setCtime(int ctime) {
        this.ctime = ctime;
    }

    public boolean isHotTagsTopicSelect() {
        return isHotTagsTopicSelect;
    }

    public void setHotTagsTopicSelect(boolean hotTagsTopicSelect) {
        isHotTagsTopicSelect = hotTagsTopicSelect;
    }
}
