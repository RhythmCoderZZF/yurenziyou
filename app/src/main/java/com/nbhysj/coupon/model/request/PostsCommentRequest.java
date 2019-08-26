package com.nbhysj.coupon.model.request;

/**
 * @auther：hysj created on 2019/8/12
 * description：帖子评论
 */
public class PostsCommentRequest {

    //帖子id
    private String articleId;

    //评论内容
    private String content;

    //评论id,0为评论帖子
    private String pid;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
