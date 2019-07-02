package com.nbhysj.coupon.model.response;

import java.io.Serializable;

import me.yokeyword.indexablerv.IndexableEntity;

/**
 * created by hysj on 2018/11/06.
 */

public class CitySearchBean implements Serializable {

    private int id;

    //cityName 城市名称
    private String name;

    private int open;

    private String cityCode;

    private int level;

    private int pid;
    //纬度
    private String letter;

    private int sort;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOpen() {
        return open;
    }

    public void setOpen(int open) {
        this.open = open;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
