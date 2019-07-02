package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2019/5/24
 * description：专辑收藏列表
 */
public class CollectionAlbumListResponse {

    private String albumImage;

    private String albumName;

    public String getAlbumImage() {
        return albumImage;
    }

    public void setAlbumImage(String albumImage) {
        this.albumImage = albumImage;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }
}
