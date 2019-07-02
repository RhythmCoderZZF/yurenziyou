package com.nbhysj.coupon.model.response;

import java.io.Serializable;

/**
 * @auther：hysj created on 2019/2/16
 * description：图片对象
 */
public class ImageData implements Serializable {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
