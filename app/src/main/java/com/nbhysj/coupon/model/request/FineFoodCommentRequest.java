package com.nbhysj.coupon.model.request;

import java.util.List;

/**
 * @auther：hysj created on 2019/9/26
 * description：美食评价
 */
public class FineFoodCommentRequest {

    List<FineFoodCommentBean> createMchCommentVO;

    public List<FineFoodCommentBean> getCreateMchCommentVO() {
        return createMchCommentVO;
    }

    public void setCreateMchCommentVO(List<FineFoodCommentBean> createMchCommentVO) {
        this.createMchCommentVO = createMchCommentVO;
    }
}
