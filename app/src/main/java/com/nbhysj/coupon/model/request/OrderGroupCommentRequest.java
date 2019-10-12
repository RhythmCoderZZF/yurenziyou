package com.nbhysj.coupon.model.request;

import java.util.List;

/**
 * @auther：hysj created on 2019/9/26
 * description：组合商品评价
 */
public class OrderGroupCommentRequest {

    private List<OrderPartialCommentRequest> createMchCommentVO;

    public List<OrderPartialCommentRequest> getCreateMchCommentVO() {
        return createMchCommentVO;
    }

    public void setCreateMchCommentVO(List<OrderPartialCommentRequest> createMchCommentVO) {
        this.createMchCommentVO = createMchCommentVO;
    }
}
