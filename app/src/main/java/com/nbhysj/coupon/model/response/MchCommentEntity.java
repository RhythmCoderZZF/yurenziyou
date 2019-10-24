package com.nbhysj.coupon.model.response;

import java.io.Serializable;
import java.util.List;

/**
 * @auther：hysj created on 2019/9/21
 * description：商户评论
 */
public class MchCommentEntity implements Serializable {

    private int id;

    private float score;

    private String content;

    private List<String> photo;

    private int zanNum;

    private int top;

    private String reply;

    private CommentUserEntity user;

    private int hitsNum;

    private String mchDetails;

    private int goodsId;

    private String goodsTitle;

    private long payTime;

    private long ctime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getPhoto() {
        return photo;
    }

    public void setPhoto(List<String> photo) {
        this.photo = photo;
    }

    public int getZanNum() {
        return zanNum;
    }

    public void setZanNum(int zanNum) {
        this.zanNum = zanNum;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public CommentUserEntity getUser() {
        return user;
    }

    public void setUser(CommentUserEntity user) {
        this.user = user;
    }

    public int getHitsNum() {
        return hitsNum;
    }

    public void setHitsNum(int hitsNum) {
        this.hitsNum = hitsNum;
    }

    public String getMchDetails() {
        return mchDetails;
    }

    public void setMchDetails(String mchDetails) {
        this.mchDetails = mchDetails;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsTitle() {
        return goodsTitle;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }

    public long getPayTime() {
        return payTime;
    }

    public void setPayTime(long payTime) {
        this.payTime = payTime;
    }

}
