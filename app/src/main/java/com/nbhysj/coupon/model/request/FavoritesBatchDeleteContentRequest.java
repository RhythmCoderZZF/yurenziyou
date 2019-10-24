package com.nbhysj.coupon.model.request;

import java.util.List;

/**
 * @auther：hysj created on 2019/10/22
 * description：专辑内容批量删除
 */
public class FavoritesBatchDeleteContentRequest {

    //专辑内容(帖子id)
    private List<Integer> dataIds;

    //专辑id
    private int favoritesId;

    public List<Integer> getDataIds() {
        return dataIds;
    }

    public void setDataIds(List<Integer> dataIds) {
        this.dataIds = dataIds;
    }

    public int getFavoritesId() {
        return favoritesId;
    }

    public void setFavoritesId(int favoritesId) {
        this.favoritesId = favoritesId;
    }
}
