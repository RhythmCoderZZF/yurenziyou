package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/11/15
 * description：广播
 */
public class BroadcastResponse {

    private List<BroadcastBean> result;

    private BasePaginationResult page;

    public List<BroadcastBean> getResult() {
        return result;
    }

    public void setResult(List<BroadcastBean> result) {
        this.result = result;
    }

    public BasePaginationResult getPage() {
        return page;
    }

    public void setPage(BasePaginationResult page) {
        this.page = page;
    }
}
