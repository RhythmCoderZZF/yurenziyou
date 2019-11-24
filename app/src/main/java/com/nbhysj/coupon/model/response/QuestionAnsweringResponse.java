package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/11/21
 * description：我的提问
 */
public class QuestionAnsweringResponse {

    private List<MyQuestionAnsweringBean> result;

    private BasePaginationResult page;

    public List<MyQuestionAnsweringBean> getResult() {
        return result;
    }

    public void setResult(List<MyQuestionAnsweringBean> result) {
        this.result = result;
    }

    public BasePaginationResult getPage() {
        return page;
    }

    public void setPage(BasePaginationResult page) {
        this.page = page;
    }
}
