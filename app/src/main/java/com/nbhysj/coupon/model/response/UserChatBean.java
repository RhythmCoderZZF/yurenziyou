package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2020/1/3
 * description：用户聊天
 */
public class UserChatBean {

    private int userId;

    private String nickname;

    private String message;

    private int type;

    private String avater;

    private int ownStatus;

    private long time;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }

    public int getOwnStatus() {
        return ownStatus;
    }

    public void setOwnStatus(int ownStatus) {
        this.ownStatus = ownStatus;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
