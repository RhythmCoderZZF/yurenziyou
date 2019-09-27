package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2019/9/24
 * description：感兴趣的用户
 */
public class RecommendInterestUsersBean {

    //用户id
    private int id;

    //用户昵称
    private String nickname;

    //用户头像
    private String avater;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
