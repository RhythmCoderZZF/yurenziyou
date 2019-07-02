package com.nbhysj.coupon.model.request;

/**
 * @auther：hysj created on 2019/3/12
 * description：个人信息修改
 */
public class UpdateUserInfoRequest {

    /*
     用户id
     */
    private int id;

    /*
    性别（关联字典）
    */
    private Integer sex;

    /*
    是否公开社交信息：1=公开，0=不公开
    */
    private Integer socialStatus;

    /*
    头像地址
    */
    private String avater;

    /*
     生日
     */
    private String birthday;
    /*
    身份证号码
    */
    private String identityCard;

    /*
    常居城市
    */
    private String liveCity;

    /*
    昵称
    */
    private String nickname;

    /*
    个人简介（50字以内）
    */
    private String profile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getSocialStatus() {
        return socialStatus;
    }

    public void setSocialStatus(Integer socialStatus) {
        this.socialStatus = socialStatus;
    }

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getLiveCity() {
        return liveCity;
    }

    public void setLiveCity(String liveCity) {
        this.liveCity = liveCity;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
