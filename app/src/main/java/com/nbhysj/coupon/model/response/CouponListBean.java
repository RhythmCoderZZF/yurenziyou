package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/10/31
 * description：优惠券列表
 */
public class CouponListBean {

    private List<CouponsBean> result;

    private BasePaginationResult page;

    public List<CouponsBean> getResult() {
        return result;
    }

    public void setResult(List<CouponsBean> result) {
        this.result = result;
    }

    public BasePaginationResult getPage() {
        return page;
    }

    public void setPage(BasePaginationResult page) {
        this.page = page;
    }
}
