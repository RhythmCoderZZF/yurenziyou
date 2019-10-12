package com.nbhysj.coupon.model.request;

/**
 * @auther：hysj created on 2019/08/05
 * description：帖子操作请求
 */
public class PostOprateRequest {

    //用户id
    private int userId;

    //作者Id
    private int authorId;

    //帖子ID/评论ID
    private int postsId;

    //1帖子点赞，2帖子收藏，3.帖子评论 postsType
    private int postsType;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getPostsId() {
        return postsId;
    }

    public void setPostsId(int postsId) {
        this.postsId = postsId;
    }

    public int getPostsType() {
        return postsType;
    }

    public void setPostsType(int postsType) {
        this.postsType = postsType;
    }
}
