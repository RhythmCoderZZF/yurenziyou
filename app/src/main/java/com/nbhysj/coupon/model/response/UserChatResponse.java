package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2020/1/3
 * description：用户聊天
 */
public class UserChatResponse {

    private List<UserChatBean> result;

    private BasePaginationResult page;

    public List<UserChatBean> getResult() {
        return result;
    }

    public void setResult(List<UserChatBean> result) {
        this.result = result;
    }

    public BasePaginationResult getPage() {
        return page;
    }

    public void setPage(BasePaginationResult page) {
        this.page = page;
    }
}
