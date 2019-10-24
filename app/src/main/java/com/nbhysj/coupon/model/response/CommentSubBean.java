package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2019/10/14
 * description：
 */
public class CommentSubBean {

    private int id;

    private int pid;

    private CommentUserEntity author;

    private CommentUserEntity user;

    private String content;

    private int zanCount;

    private long ctime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public CommentUserEntity getAuthor() {
        return author;
    }

    public void setAuthor(CommentUserEntity author) {
        this.author = author;
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

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }
}
