package com.nbhysj.coupon.model.response;


import java.io.Serializable;
import java.util.List;

/**
 * @author by hysj on 2017/6/27.
 * description:发现好友图片展示
 */
public class FindFriendsPictureBean extends UserInfoBean implements Serializable {
    public List<ImageData> images;

    public List<ImageData> getImages() {
        return images;
    }

    public void setImages(List<ImageData> images) {
        this.images = images;
    }
}
