package com.nbhysj.coupon.model.request;

/**
 * @auther：hysj created on 2019/10/4
 * description：我要提问-发布问题
 */
public class QuestionPublishRequest {

    //商户id
    private int mchId;

    //问题
    private String content;

    public int getMchId() {
        return mchId;
    }

    public void setMchId(int mchId) {
        this.mchId = mchId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
