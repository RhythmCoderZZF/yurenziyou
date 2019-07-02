package com.nbhysj.coupon.model.response;


import java.util.List;

/**
 * @auther：hysj created on 2019/06/05
 * description：热门景点
 */
public class HotScenicSpotResponse {

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
