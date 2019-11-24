package com.nbhysj.coupon.model.request;

/**
 * @auther：hysj created on 2019/10/4
 * description：我要提问-发布问题
 */
public class AnswerPublishRequest {

    //问题id
    private int questionId;

    //问题
    private String content;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
