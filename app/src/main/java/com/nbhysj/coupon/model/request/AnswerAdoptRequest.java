package com.nbhysj.coupon.model.request;

/**
 * @auther：hysj created on 2019/12/29
 * description：问题采纳
 */
public class AnswerAdoptRequest {

    private int questionId;

    private int answerId;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }
}
