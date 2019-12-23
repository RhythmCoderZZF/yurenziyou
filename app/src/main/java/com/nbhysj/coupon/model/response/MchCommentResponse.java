package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/10/11
 * description：
 */
public class MchCommentResponse {

    private ScoreEntity score;

    private CommentEntity comment;

    private List<LabelEntity> label;

    public ScoreEntity getScore() {
        return score;
    }

    public void setScore(ScoreEntity score) {
        this.score = score;
    }

    public CommentEntity getComment() {
        return comment;
    }

    public void setComment(CommentEntity comment) {
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

        private float commentScore;

        private float commentScore1;

        private float commentScore2;

        private float commentScore3;

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public float getCommentScore() {
            return commentScore;
        }

        public void setCommentScore(float commentScore) {
            this.commentScore = commentScore;
        }

        public float getCommentScore1() {
            return commentScore1;
        }

        public void setCommentScore1(float commentScore1) {
            this.commentScore1 = commentScore1;
        }

        public float getCommentScore2() {
            return commentScore2;
        }

        public void setCommentScore2(float commentScore2) {
            this.commentScore2 = commentScore2;
        }

        public float getCommentScore3() {
            return commentScore3;
        }

        public void setCommentScore3(float commentScore3) {
            this.commentScore3 = commentScore3;
        }
    }

    public class CommentEntity{

        private List<MchCommentEntity> result;

        private BasePaginationResult page;

        public List<MchCommentEntity> getResult() {
            return result;
        }

        public void setResult(List<MchCommentEntity> result) {
            this.result = result;
        }

        public BasePaginationResult getPage() {
            return page;
        }

        public void setPage(BasePaginationResult page) {
            this.page = page;
        }
    }


}
