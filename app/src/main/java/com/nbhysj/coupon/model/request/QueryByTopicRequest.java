package com.nbhysj.coupon.model.request;

/**
 * @auther：hysj created on 2019/4/3
 * description：首页按照子标签查询
 */
public class QueryByTopicRequest {

    //子标签id
    private int id;

    private int page;

    private int pageSize;

    private String longitude;

    private String latitude;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
