package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/10/20
 * description：专辑详情
 */
public class AlbumFavoritesDetail {

    private int favoritesId;

    private String title;

    private String intro;

    private int userId;

    private String nickname;

    private String avater;

    private int postsNum;

    private List<FavoritesBean> postsCollections;

    public int getFavoritesId() {
        return favoritesId;
    }

    public void setFavoritesId(int favoritesId) {
        this.favoritesId = favoritesId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

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

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }

    public int getPostsNum() {
        return postsNum;
    }

    public void setPostsNum(int postsNum) {
        this.postsNum = postsNum;
    }

    public List<FavoritesBean> getPostsCollections() {
        return postsCollections;
    }

    public void setPostsCollections(List<FavoritesBean> postsCollections) {
        this.postsCollections = postsCollections;
    }
}
