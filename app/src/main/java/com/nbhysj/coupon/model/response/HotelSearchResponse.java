package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2020/1/14
 * description：酒店筛选
 */
public class HotelSearchResponse {

    private List<MchTypeBean> result;

    private BasePaginationResult page;

    public List<MchTypeBean> getResult() {
        return result;
    }

    public void setResult(List<MchTypeBean> result) {
        this.result = result;
    }

    public BasePaginationResult getPage() {
        return page;
    }

    public void setPage(BasePaginationResult page) {
        this.page = page;
    }
}
