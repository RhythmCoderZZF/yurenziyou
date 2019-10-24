package com.nbhysj.coupon.model.request;

import java.util.List;

/**
 * @auther：hysj created on 2019/10/22
 * description：专辑内容批量移动
 */
public class FavoritesBatchMoveContentRequest {

    //专辑内容(帖子id)
    private List<Integer> collectionId;

    //专辑id
    private int favoritesId;

    public List<Integer> getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(List<Integer> collectionId) {
        this.collectionId = collectionId;
    }

    public int getFavoritesId() {
        return favoritesId;
    }

    public void setFavoritesId(int favoritesId) {
        this.favoritesId = favoritesId;
    }
}
