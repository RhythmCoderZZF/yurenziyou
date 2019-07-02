package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/3/15
 * description：收件人信息返回
 */
public class RecipientsInfoResponse {

    //旅客信息返回
    private List<RecipientsBean> result;

    private BasePaginationResult page;

    public List<RecipientsBean> getResult() {
        return result;
    }

    public void setResult(List<RecipientsBean> result) {
        this.result = result;
    }

    public BasePaginationResult getPage() {
        return page;
    }

    public void setPage(BasePaginationResult page) {
        this.page = page;
    }
}
