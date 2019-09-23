package com.nbhysj.coupon.model.request;

/**
 * @auther：hysj created on 2019/9/20
 * description：
 */
public class MoveFavoritesContentRequest {

    //收藏ID
    private int id;

    //专辑Id
    private int favoritesId;

    //每页数量
    private int pageSize;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFavoritesId() {
        return favoritesId;
    }

    public void setFavoritesId(int favoritesId) {
        this.favoritesId = favoritesId;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
