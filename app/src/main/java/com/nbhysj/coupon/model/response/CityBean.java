package com.nbhysj.coupon.model.response;

import java.io.Serializable;

import me.yokeyword.indexablerv.IndexableEntity;

/**
 * created by hysj on 2018/11/06.
 */

public class CityBean implements IndexableEntity, Serializable {
    //cityName 城市名称
    private String name;

    private int id;

    private int open;

    private String pingyin;

    private int sort_id;

    //纬度
    private String latitude;

    //经度
    private String longitude;

    private int up_city;

    // private long insertTime;

    public int getOpen() {
        return open;
    }

    public void setOpen(int open) {
        this.open = open;
    }

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

    public String getPingyin() {
        return pingyin;
    }

    public void setPingyin(String pingyin) {
        this.pingyin = pingyin;
    }

    public int getSort_id() {
        return sort_id;
    }

    public void setSort_id(int sort_id) {
        this.sort_id = sort_id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public int getUp_city() {
        return up_city;
    }

    public void setUp_city(int up_city) {
        this.up_city = up_city;
    }


    /*
    public long getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(long insertTime) {
        this.insertTime = insertTime;
    }*/

    @Override
    public String getFieldIndexBy() {
        return name;
    }

    @Override
    public void setFieldIndexBy(String indexField) {
        this.name = indexField;
    }

    @Override
    public void setFieldPinyinIndexBy(String pinyin) {

    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CityBean) {
            CityBean cityBean = (CityBean) obj;
            return this.name.equals(cityBean.getName()) && this.id == cityBean.getId() && this.open == cityBean.getOpen();
        }
        return super.equals(obj);
    }
}
