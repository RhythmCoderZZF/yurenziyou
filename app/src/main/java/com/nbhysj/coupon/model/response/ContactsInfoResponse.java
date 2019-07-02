package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/3/15
 * description：联系人信息返回
 */
public class ContactsInfoResponse {

    //旅客信息返回
    private List<ContactsBean> result;

    private BasePaginationResult page;

    public List<ContactsBean> getResult() {
        return result;
    }

    public void setResult(List<ContactsBean> result) {
        this.result = result;
    }

    public BasePaginationResult getPage() {
        return page;
    }

    public void setPage(BasePaginationResult page) {
        this.page = page;
    }
}
