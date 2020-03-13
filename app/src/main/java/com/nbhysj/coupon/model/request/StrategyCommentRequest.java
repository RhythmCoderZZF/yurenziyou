package com.nbhysj.coupon.model.request;

/**
 * @auther：hysj created on 2020/1/11
 * description：攻略评论
 */
public class StrategyCommentRequest {

    //攻略id
    private int articleId;

    //攻略内容
    private String content;

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
