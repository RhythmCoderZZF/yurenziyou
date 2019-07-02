package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/3/15
 * description：旅客信息返回
 */
public class TravellerInfoResponse {

    //旅客信息返回
    private List<TravellerBean> result;

    private BasePaginationResult page;

    public List<TravellerBean> getResult() {
        return result;
    }

    public void setResult(List<TravellerBean> result) {
        this.result = result;
    }

    public BasePaginationResult getPage() {
        return page;
    }

    public void setPage(BasePaginationResult page) {
        this.page = page;
    }
}
