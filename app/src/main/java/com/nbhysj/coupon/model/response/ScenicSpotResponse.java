package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/6/1
 * description：景点
 */
public class ScenicSpotResponse {

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
