package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2019/3/21
 * description：帖子详情评论对象
 */
public class NoteCommentBean {

    //评论用户头像
    private String commentUserAvatar;

    //评论用户名字
    private String commentUsername;

    //评论内容
    private String commentContent;

    //评论时间
    private String commentTime;

    public String getCommentUserAvatar() {
        return commentUserAvatar;
    }

    public void setCommentUserAvatar(String commentUserAvatar) {
        this.commentUserAvatar = commentUserAvatar;
    }

    public String getCommentUsername() {
        return commentUsername;
    }

    public void setCommentUsername(String commentUsername) {
        this.commentUsername = commentUsername;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }
}

