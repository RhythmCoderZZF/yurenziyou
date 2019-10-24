package com.nbhysj.coupon.model.request;

import java.util.List;

/**
 * @auther：hysj created on 2019/10/15
 * description：收藏商户批量删除
 */
public class CollectionBatchMchDeleteRequest {

    //商户Id
    private List<Integer> mchIds;

    public List<Integer> getMchIds() {
        return mchIds;
    }

    public void setMchIds(List<Integer> mchIds) {
        this.mchIds = mchIds;
    }
}
