package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/4/4
 * description：
 */
public class HomePageSubTopicTagReponse {

    private List<HomePageSubTopicTagBean> result;

    private BasePaginationResult page;

    public List<HomePageSubTopicTagBean> getResult() {
        return result;
    }

    public void setResult(List<HomePageSubTopicTagBean> result) {
        this.result = result;
    }

    public BasePaginationResult getPage() {
        return page;
    }

    public void setPage(BasePaginationResult page) {
        this.page = page;
    }
}
