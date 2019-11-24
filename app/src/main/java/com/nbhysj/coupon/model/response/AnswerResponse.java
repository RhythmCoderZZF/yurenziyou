package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/11/10
 * description：回答
 */
public class AnswerResponse {

    private List<AnswerBean> result;

    private BasePaginationResult page;

    public List<AnswerBean> getResult() {
        return result;
    }

    public void setResult(List<AnswerBean> result) {
        this.result = result;
    }

    public BasePaginationResult getPage() {
        return page;
    }

    public void setPage(BasePaginationResult page) {
        this.page = page;
    }
}
