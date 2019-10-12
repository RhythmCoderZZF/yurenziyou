package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/10/11
 * description：
 */
public class MchCommentResponse {

    private ScoreEntity score;

    private List<MchCommentEntity> comment;

    private List<LabelEntity> label;

    public ScoreEntity getScore() {
        return score;
    }

    public void setScore(ScoreEntity score) {
        this.score = score;
    }

    public List<MchCommentEntity> getComment() {
        return comment;
    }

    public void setComment(List<MchCommentEntity> comment) {
        this.comment = comment;
    }

    public List<LabelEntity> getLabel() {
        return label;
    }

    public void setLabel(List<LabelEntity> label) {
        this.label = label;
    }

    public class ScoreEntity{

        private int commentNum;

        private int commentScore;

        private int commentScore2;

        private int commentScore3;

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public int getCommentScore() {
            return commentScore;
        }

        public void setCommentScore(int commentScore) {
            this.commentScore = commentScore;
        }

        public int getCommentScore2() {
            return commentScore2;
        }

        public void setCommentScore2(int commentScore2) {
            this.commentScore2 = commentScore2;
        }

        public int getCommentScore3() {
            return commentScore3;
        }

        public void setCommentScore3(int commentScore3) {
            this.commentScore3 = commentScore3;
        }
    }
}
