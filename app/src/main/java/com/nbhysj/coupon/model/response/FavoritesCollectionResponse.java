package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2019/10/15
 * description：专辑收藏
 */
public class FavoritesCollectionResponse {

    private int collectionStatus;

    private int collectionCount;

    public int getCollectionStatus() {
        return collectionStatus;
    }

    public void setCollectionStatus(int collectionStatus) {
        this.collectionStatus = collectionStatus;
    }

    public int getCollectionCount() {
        return collectionCount;
    }

    public void setCollectionCount(int collectionCount) {
        this.collectionCount = collectionCount;
    }
}
