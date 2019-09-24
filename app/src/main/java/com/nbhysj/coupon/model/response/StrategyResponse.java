package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/9/21
 * description：攻略
 */
public class StrategyResponse {

    //攻略列表
    private List<StrategyBean> result;

    private BasePaginationResult page;

    public List<StrategyBean> getResult() {
        return result;
    }

    public void setResult(List<StrategyBean> result) {
        this.result = result;
    }

    public BasePaginationResult getPage() {
        return page;
    }

    public void setPage(BasePaginationResult page) {
        this.page = page;
    }

}
