package com.nbhysj.coupon.model.response;


import java.io.Serializable;

/**
 * @author by hysj on 2017/02/22.
 * description:推荐好友图片展示
 */
public class RecommendFriendsBean extends UserInfoBean implements Serializable {
    public String image;

    public boolean isLove;

    private String des;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isLove() {
        return isLove;
    }

    public void setLove(boolean love) {
        isLove = love;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
