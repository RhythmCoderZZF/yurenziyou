package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2019/5/24
 * description：我的收藏专辑
 */
public class MineCollectionAlbumResponse {

    private String albumImage;

    private String albumName;

    private String albumDes;

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

    public String getAlbumDes() {
        return albumDes;
    }

    public void setAlbumDes(String albumDes) {
        this.albumDes = albumDes;
    }
}
