package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/11/10
 * description：
 */
public class UserFollowResponse {

    private List<UserFansFollowBean> result;

    private BasePaginationResult page;

    public List<UserFansFollowBean> getResult() {
        return result;
    }

    public void setResult(List<UserFansFollowBean> result) {
        this.result = result;
    }

    public BasePaginationResult getPage() {
        return page;
    }

    public void setPage(BasePaginationResult page) {
        this.page = page;
    }
}
