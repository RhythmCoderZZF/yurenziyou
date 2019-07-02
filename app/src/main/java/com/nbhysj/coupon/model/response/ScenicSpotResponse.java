package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/6/1
 * description：景点
 */
public class ScenicSpotResponse {

    private List<ScenicSpotBean> result;

    private BasePaginationResult page;

    public List<ScenicSpotBean> getResult() {
        return result;
    }

    public void setResult(List<ScenicSpotBean> result) {
        this.result = result;
    }

    public BasePaginationResult getPage() {
        return page;
    }

    public void setPage(BasePaginationResult page) {
        this.page = page;
    }
}
