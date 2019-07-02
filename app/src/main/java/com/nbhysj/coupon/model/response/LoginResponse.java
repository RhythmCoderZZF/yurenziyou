package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2019/3/6
 * description：登录返回
 */
public class LoginResponse {

    //用户id
    private int id;

    //手机号码
    private String mobile;

    //用户名
    private String username;

    //昵称
    private String nickname;

    //头像
    private String avater;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }
}
