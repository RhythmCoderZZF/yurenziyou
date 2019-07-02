package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2019/03/09
 * description：已点赞用户对象
 */
public class SupportedUserBean {

    private String url;

    private String title;

    private String content;

    private boolean followStatus;

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

    public boolean isFollowStatus() {
        return followStatus;
    }

    public void setFollowStatus(boolean followStatus) {
        this.followStatus = followStatus;
    }
}
