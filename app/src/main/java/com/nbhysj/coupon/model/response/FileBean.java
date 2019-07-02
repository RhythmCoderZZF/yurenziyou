package com.nbhysj.coupon.model.response;

import java.io.Serializable;

/**
 * created by hysj on 2019/03/18.
 * description: 图片对象
 */
public class FileBean implements Serializable {

    /**
     * 文件类型id
     */
    private int filetypeId;

    /**
     * 图片路径
     */
    private String image;

    /**
     * 缩略图
     */
    private String thumbnail;

    /**
     * 图片名
     */
    private String filename;

    public int getFiletypeId() {
        return filetypeId;
    }

    public void setFiletypeId(int filetypeId) {
        this.filetypeId = filetypeId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

}
