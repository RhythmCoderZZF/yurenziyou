package com.nbhysj.coupon.model.response;

import java.io.Serializable;
import java.util.List;

/**
 * @auther：hysj created on 2019/11/20
 * description：待我回答
 */
public class WaitMyAnswerBean implements Serializable {

    private int questionId;

    private String questionContent;

    private int answerId;

    private String answerContent;

    private int userId;

    private String answerNickName;

    private String answerAvater;

    private List<OtherAnswerBean> otherAnswer;

    private String otherAnswerNum;

    private String questionDate;

    private int haveAnswerStatus;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAnswerNickName() {
        return answerNickName;
    }

    public void setAnswerNickName(String answerNickName) {
        this.answerNickName = answerNickName;
    }

    public String getAnswerAvater() {
        return answerAvater;
    }

    public void setAnswerAvater(String answerAvater) {
        this.answerAvater = answerAvater;
    }

    public List<OtherAnswerBean> getOtherAnswer() {
        return otherAnswer;
    }

    public void setOtherAnswer(List<OtherAnswerBean> otherAnswer) {
        this.otherAnswer = otherAnswer;
    }

    public String getOtherAnswerNum() {
        return otherAnswerNum;
    }

    public void setOtherAnswerNum(String otherAnswerNum) {
        this.otherAnswerNum = otherAnswerNum;
    }

    public String getQuestionDate() {
        return questionDate;
    }

    public void setQuestionDate(String questionDate) {
        this.questionDate = questionDate;
    }

    public int getHaveAnswerStatus() {
        return haveAnswerStatus;
    }

    public void setHaveAnswerStatus(int haveAnswerStatus) {
        this.haveAnswerStatus = haveAnswerStatus;
    }
}


