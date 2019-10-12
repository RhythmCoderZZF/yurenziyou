package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/10/4
 * description：商户界面商户问答列表
 */
public class WaitMyAnswerResponse {

    private List<WaitMyAnswerEntity> result;

    private String mchName;

    private String recomment;

    private BasePaginationResult page;

    public List<WaitMyAnswerEntity> getResult() {
        return result;
    }

    public void setResult(List<WaitMyAnswerEntity> result) {
        this.result = result;
    }

    public String getMchName() {
        return mchName;
    }

    public void setMchName(String mchName) {
        this.mchName = mchName;
    }

    public String getRecomment() {
        return recomment;
    }

    public void setRecomment(String recomment) {
        this.recomment = recomment;
    }

    public BasePaginationResult getPage() {
        return page;
    }

    public void setPage(BasePaginationResult page) {
        this.page = page;
    }

    public class WaitMyAnswerEntity{

        private int questionId;

        private String questionContent;

        private int answerId;

        private String answerContent;

        private int userId;

        private String answerNickName;

        private String answerAvater;

        private String otherAnswer;

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

        public String getOtherAnswer() {
            return otherAnswer;
        }

        public void setOtherAnswer(String otherAnswer) {
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
}
