package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/11/21
 * description：同问返回
 */
public class AskTogetherResponse {

    private List<AskTogetherBean> result;

    private BasePaginationResult page;

    public List<AskTogetherBean> getResult() {
        return result;
    }

    public void setResult(List<AskTogetherBean> result) {
        this.result = result;
    }

    public BasePaginationResult getPage() {
        return page;
    }

    public void setPage(BasePaginationResult page) {
        this.page = page;
    }
}
