package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/4/29
 * description：景点详情用户评论
 */
public class ScenicSpotsUserCommentResponse {

    private int id;

    //用户名
    private String username;

    //用户头像
    private String userAvatarPhoto;

    private String commentPublishTime;

    private String content;

    //星级
    private int starLevel;

    private List<String> userCommentPhotoList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserAvatarPhoto() {
        return userAvatarPhoto;
    }

    public void setUserAvatarPhoto(String userAvatarPhoto) {
        this.userAvatarPhoto = userAvatarPhoto;
    }

    public String getCommentPublishTime() {
        return commentPublishTime;
    }

    public void setCommentPublishTime(String commentPublishTime) {
        this.commentPublishTime = commentPublishTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(int starLevel) {
        this.starLevel = starLevel;
    }

    public List<String> getUserCommentPhotoList() {
        return userCommentPhotoList;
    }

    public void setUserCommentPhotoList(List<String> userCommentPhotoList) {
        this.userCommentPhotoList = userCommentPhotoList;
    }
}
