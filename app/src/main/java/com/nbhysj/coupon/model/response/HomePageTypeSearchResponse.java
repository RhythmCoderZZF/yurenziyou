package com.nbhysj.coupon.model.response;

import java.io.Serializable;
import java.util.List;

/**
 * @auther：hysj created on 2019/4/3
 * description：首页类型搜索返回
 */
public class HomePageTypeSearchResponse implements Serializable {

    private List<HomeSearchMchTypeBean> result;

    private BasePaginationResult page;

    public List<HomeSearchMchTypeBean> getResult() {
        return result;
    }

    public void setResult(List<HomeSearchMchTypeBean> result) {
        this.result = result;
    }

    public BasePaginationResult getPage() {
        return page;
    }

    public void setPage(BasePaginationResult page) {
        this.page = page;
    }
}







