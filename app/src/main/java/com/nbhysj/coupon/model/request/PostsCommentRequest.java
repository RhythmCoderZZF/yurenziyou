package com.nbhysj.coupon.model.request;

/**
 * @auther：hysj created on 2019/10/14
 * description：帖子评论
 */
public class PostsCommentRequest {

    private String content;

    //帖子id
    private int articleId;

    private int authorId;

    //评论id,0为评论帖子
    private int pid;

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPid() {
        return pid;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

}
