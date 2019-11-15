package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/11/12
 * description：赞与收藏
 */
public class ZanAndCollectionResponse {

    private List<ZanAndCollectionBean> result;

    private BasePaginationResult page;

    public List<ZanAndCollectionBean> getResult() {
        return result;
    }

    public void setResult(List<ZanAndCollectionBean> result) {
        this.result = result;
    }

    public BasePaginationResult getPage() {
        return page;
    }

    public void setPage(BasePaginationResult page) {
        this.page = page;
    }
}
