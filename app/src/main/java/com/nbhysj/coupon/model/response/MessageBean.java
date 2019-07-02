package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2019/02/20
 * description：消息对象
 */
public class MessageBean {

    private String url;

    private String title;

    private String content;

    private String time;

    private boolean isRead;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
