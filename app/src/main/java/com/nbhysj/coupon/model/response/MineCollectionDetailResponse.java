package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/9/20
 * description：我的收藏查看全部详情对象
 */
public class MineCollectionDetailResponse {

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
