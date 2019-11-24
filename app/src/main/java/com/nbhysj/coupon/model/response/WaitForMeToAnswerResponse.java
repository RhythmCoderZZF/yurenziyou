package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/11/22
 * description：待我回答
 */
public class WaitForMeToAnswerResponse {

    private List<WaitForMeToAnswerBean> result;

    private BasePaginationResult page;

    public List<WaitForMeToAnswerBean> getResult() {
        return result;
    }

    public void setResult(List<WaitForMeToAnswerBean> result) {
        this.result = result;
    }

    public BasePaginationResult getPage() {
        return page;
    }

    public void setPage(BasePaginationResult page) {
        this.page = page;
    }
}
