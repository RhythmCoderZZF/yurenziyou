package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/9/20
 * description：专辑列表对象
 */
public class FavoritesListResponse {

    private List<FavoritesBean> result;

    private BasePaginationResult page;

    public List<FavoritesBean> getResult() {
        return result;
    }

    public void setResult(List<FavoritesBean> result) {
        this.result = result;
    }

    public BasePaginationResult getPage() {
        return page;
    }

    public void setPage(BasePaginationResult page) {
        this.page = page;
    }
}
