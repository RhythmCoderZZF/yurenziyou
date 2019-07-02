package com.nbhysj.coupon.model.response;

import java.io.Serializable;

/**
 * @auther：hysj created on 2019/3/15
 * description：选择景点
 */
public class SelectDestionationsBean implements Serializable {

    private int id;

    private String destionation;
    //人气榜
    private int popularity;
    //推荐景点
    private String scenicSpotsName;

    private boolean isSelect;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestionation() {
        return destionation;
    }

    public void setDestionation(String destionation) {
        this.destionation = destionation;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public String getScenicSpotsName() {
        return scenicSpotsName;
    }

    public void setScenicSpotsName(String scenicSpotsName) {
        this.scenicSpotsName = scenicSpotsName;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
