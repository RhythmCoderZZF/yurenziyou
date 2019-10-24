package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/9/20
 * description：专辑对象
 */
public class FavoritesBean {

    private int id;

    private int num;

    private int visibleStatus;

    private String photo;

    private String title;

    private String intro;

    private String ctime;

    //附加 专辑详情
    private int collectionId;

    private int hits;

    private int postsType;

    private int userId;

    private String nickname;

    private String avater;

    private String content;

    private int photoWidth;

    private int photoHeight;

    private int mchId;

    private String mchType;

    //专辑选择
    private int isAlbumSelect;

    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getVisibleStatus() {
        return visibleStatus;
    }

    public void setVisibleStatus(int visibleStatus) {
        this.visibleStatus = visibleStatus;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getPostsType() {
        return postsType;
    }

    public void setPostsType(int postsType) {
        this.postsType = postsType;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPhotoWidth() {
        return photoWidth;
    }

    public void setPhotoWidth(int photoWidth) {
        this.photoWidth = photoWidth;
    }

    public int getPhotoHeight() {
        return photoHeight;
    }

    public void setPhotoHeight(int photoHeight) {
        this.photoHeight = photoHeight;
    }

    public int getMchId() {
        return mchId;
    }

    public void setMchId(int mchId) {
        this.mchId = mchId;
    }

    public String getMchType() {
        return mchType;
    }

    public void setMchType(String mchType) {
        this.mchType = mchType;
    }

    public int getIsAlbumSelect() {
        return isAlbumSelect;
    }

    public void setIsAlbumSelect(int isAlbumSelect) {
        this.isAlbumSelect = isAlbumSelect;
    }
}
