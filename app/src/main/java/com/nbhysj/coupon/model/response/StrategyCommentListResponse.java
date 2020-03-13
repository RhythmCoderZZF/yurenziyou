package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2020/1/11
 * description：攻略评论列表
 */
public class StrategyCommentListResponse {

    private List<StrategyCommentBean> result;

    private BasePaginationResult page;

    public List<StrategyCommentBean> getResult() {
        return result;
    }

    public void setResult(List<StrategyCommentBean> result) {
        this.result = result;
    }

    public BasePaginationResult getPage() {
        return page;
    }

    public void setPage(BasePaginationResult page) {
        this.page = page;
    }
}
