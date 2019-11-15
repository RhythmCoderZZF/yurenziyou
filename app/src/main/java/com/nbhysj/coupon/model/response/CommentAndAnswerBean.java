package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2019/11/12
 * description：评论和回答
 */
public class CommentAndAnswerBean {

    private int id;

    private int userId;

    private String content;

    private String articleType;

    private int articleId;

    private int authorId;

    private String photo;

    private int pid;

    private int commentType;

    private int zanCount;

    private String articleIntro;

    private String nickname;

    private String avater;

    private String commentUserAvater;

    private String commentUserNickname;
    private long ctime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getZanCount() {
        return zanCount;
    }

    public void setZanCount(int zanCount) {
        this.zanCount = zanCount;
    }

    public String getArticleIntro() {
        return articleIntro;
    }

    public void setArticleIntro(String articleIntro) {
        this.articleIntro = articleIntro;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }

    public String getCommentUserAvater() {
        return commentUserAvater;
    }

    public void setCommentUserAvater(String commentUserAvater) {
        this.commentUserAvater = commentUserAvater;
    }

    public String getCommentUserNickname() {
        return commentUserNickname;
    }

    public void setCommentUserNickname(String commentUserNickname) {
        this.commentUserNickname = commentUserNickname;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getCommentType() {
        return commentType;
    }

    public void setCommentType(int commentType) {
        this.commentType = commentType;
    }
}
