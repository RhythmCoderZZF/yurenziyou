package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2020/1/11
 * description：
 */
public class StrategyCommentBean {

    private int id;

    private CommentUserEntity user;

    private String content;

    private int zanCount;

    private int zanStatus;

    private long ctime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CommentUserEntity getUser() {
        return user;
    }

    public void setUser(CommentUserEntity user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getZanCount() {
        return zanCount;
    }

    public void setZanCount(int zanCount) {
        this.zanCount = zanCount;
    }

    public int getZanStatus() {
        return zanStatus;
    }

    public void setZanStatus(int zanStatus) {
        this.zanStatus = zanStatus;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }
}
