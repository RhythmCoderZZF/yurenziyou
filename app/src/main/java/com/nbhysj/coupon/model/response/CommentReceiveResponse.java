package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/03/14
 * description：收到的评论
 */
public class CommentReceiveResponse {

    private String avatar;

    private String commentUser;

    private String commentOprate;

    private String commentTime;

    private String commentPictrue;

    private List<CommentEntity> commentList;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(String commentUser) {
        this.commentUser = commentUser;
    }

    public String getCommentOprate() {
        return commentOprate;
    }

    public void setCommentOprate(String commentOprate) {
        this.commentOprate = commentOprate;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getCommentPictrue() {
        return commentPictrue;
    }

    public void setCommentPictrue(String commentPictrue) {
        this.commentPictrue = commentPictrue;
    }

    public class CommentEntity {

        private String comment;

        private boolean isMyComment;

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public boolean isMyComment() {
            return isMyComment;
        }

        public void setMyComment(boolean myComment) {
            isMyComment = myComment;
        }
    }

    public List<CommentEntity> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentEntity> commentList) {
        this.commentList = commentList;
    }
}
