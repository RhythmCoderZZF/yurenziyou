package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/11/12
 * description：评论和回答
 */
public class CommentAndAnswerResponse {

    private List<CommentAndAnswerBean> result;

    private BasePaginationResult page;

    public List<CommentAndAnswerBean> getResult() {
        return result;
    }

    public void setResult(List<CommentAndAnswerBean> result) {
        this.result = result;
    }

    public BasePaginationResult getPage() {
        return page;
    }

    public void setPage(BasePaginationResult page) {
        this.page = page;
    }
}
