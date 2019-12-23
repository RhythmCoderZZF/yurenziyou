package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/12/23
 * description：房源信息列表
 */
public class HouseResouceResponse {

  private List<HomestayBean> result;

  private BasePaginationResult page;

    public List<HomestayBean> getResult() {
        return result;
    }

    public void setResult(List<HomestayBean> result) {
        this.result = result;
    }

    public BasePaginationResult getPage() {
        return page;
    }

    public void setPage(BasePaginationResult page) {
        this.page = page;
    }
}
