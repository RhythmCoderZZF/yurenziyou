package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2019/8/9
 * description：点赞或者收藏或者评论
 */
public class PraiseOrCollectResponse {

    //点赞数量
    private int zanNum;

    //点赞状态
    private int zanStatus;

    public int getZanNum() {
        return zanNum;
    }

    public void setZanNum(int zanNum) {
        this.zanNum = zanNum;
    }

    public int getZanStatus() {
        return zanStatus;
    }

    public void setZanStatus(int zanStatus) {
        this.zanStatus = zanStatus;
    }
}
