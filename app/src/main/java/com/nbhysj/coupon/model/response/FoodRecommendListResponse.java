package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/10/13
 * description：美食推荐列表
 */
public class FoodRecommendListResponse {

    private List<MchFoodBean> result;

    private BasePaginationResult page;

    public List<MchFoodBean> getResult() {
        return result;
    }

    public void setResult(List<MchFoodBean> result) {
        this.result = result;
    }

    public BasePaginationResult getPage() {
        return page;
    }

    public void setPage(BasePaginationResult page) {
        this.page = page;
    }
}
