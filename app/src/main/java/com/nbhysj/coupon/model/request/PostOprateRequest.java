package com.nbhysj.coupon.model.request;

/**
 * @auther：hysj created on 2019/08/05
 * description：帖子操作请求
 */
public class PostOprateRequest {

    //帖子ID/评论ID
    private int postsId;

    //1帖子点赞，2.对帖子已评论的点赞 postsType
    private int postsType;

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
