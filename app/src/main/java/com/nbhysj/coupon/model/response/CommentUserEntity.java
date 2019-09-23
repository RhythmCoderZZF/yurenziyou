package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2019/9/21
 * description：评论用户对象
 */
public class CommentUserEntity {

    private int mchFlag;

    private int id;

    private String nickname;

    private String avater;

    private String photos;

    public int getMchFlag() {
        return mchFlag;
    }

    public void setMchFlag(int mchFlag) {
        this.mchFlag = mchFlag;
    }

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

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }
}
