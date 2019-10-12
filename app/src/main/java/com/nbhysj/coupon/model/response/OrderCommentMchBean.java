package com.nbhysj.coupon.model.response;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther：hysj created on 2019/10/7
 * description：订单评价商户
 */
public class OrderCommentMchBean {

    private int mchId;

    private String mchName;

    private String mchType;

    private List<OrderCommentMchTagBean> mchTag;

    /*******************   组合封装   ******************/

    private List<String> photo = new ArrayList<>();

    //内容
    private String content;

    //评分,一共5分
    private float score;

    //评分,一共5分
    private float score1;

    //评分,一共5分
    private float score2;

    //评分,一共5分
    private float score3;

    //人均消费
    private double consumePrice;

    //0:不匿名  1:匿名
    private int anonymousStatus;

    private List<Integer> tagJson;

    public int getMchId() {
        return mchId;
    }

    public void setMchId(int mchId) {
        this.mchId = mchId;
    }

    public String getMchName() {
        return mchName;
    }

    public void setMchName(String mchName) {
        this.mchName = mchName;
    }

    public String getMchType() {
        return mchType;
    }

    public void setMchType(String mchType) {
        this.mchType = mchType;
    }

    public List<OrderCommentMchTagBean> getMchTag() {
        return mchTag;
    }

    public void setMchTag(List<OrderCommentMchTagBean> mchTag) {
        this.mchTag = mchTag;
    }

    public List<String> getPhoto() {
        return photo;
    }

    public void setPhoto(List<String> photo) {
        this.photo = photo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public float getScore1() {
        return score1;
    }

    public void setScore1(float score1) {
        this.score1 = score1;
    }

    public float getScore2() {
        return score2;
    }

    public void setScore2(float score2) {
        this.score2 = score2;
    }

    public float getScore3() {
        return score3;
    }

    public void setScore3(float score3) {
        this.score3 = score3;
    }

    public double getConsumePrice() {
        return consumePrice;
    }

    public void setConsumePrice(double consumePrice) {
        this.consumePrice = consumePrice;
    }

    public int getAnonymousStatus() {
        return anonymousStatus;
    }

    public void setAnonymousStatus(int anonymousStatus) {
        this.anonymousStatus = anonymousStatus;
    }

    public List<Integer> getTagJson() {
        return tagJson;
    }

    public void setTagJson(List<Integer> tagJson) {
        this.tagJson = tagJson;
    }
}
