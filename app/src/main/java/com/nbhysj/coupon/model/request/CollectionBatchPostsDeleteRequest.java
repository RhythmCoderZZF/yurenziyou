package com.nbhysj.coupon.model.request;

import java.util.List;

/**
 * @auther：hysj created on 2019/10/15
 * description：收藏帖子批量删除
 */
public class CollectionBatchPostsDeleteRequest {

    //帖子id
    private List<Integer> ids;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
