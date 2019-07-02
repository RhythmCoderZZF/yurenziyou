package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2019/3/14
 * description：行程动态对象
 */
public class StrokeDynamicsResponse {

    //标题
    private String title;

    //内容
    private String content;

    //时间
    private String time;

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
}
