package com.nbhysj.coupon.model.request;

/**
 * @auther：hysj created on 2019/10/15
 * description：帖子收藏
 */
public class PostsCollectionRequest {

    //收藏夹Id
    private int favoritesId;

    //用户id
    private int userId;

    //数据Id
    private int dataId;

    public int getFavoritesId() {
        return favoritesId;
    }

    public void setFavoritesId(int favoritesId) {
        this.favoritesId = favoritesId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDataId() {
        return dataId;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }
}
