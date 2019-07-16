package com.nbhysj.coupon.model.response;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * @auther：hysj created on 2019/6/13
 * description：区县
 */
@Entity
public class CountryBean {
    @Id(autoincrement = true)
    Long id;
    @Property(nameInDb = "tripId")
    private int tripId;
    private String countyId;//字段
    private String county;

    private String banner;

    //是否定位城市
    private boolean isLocationCity;

    @Generated(hash = 1010939683)
    public CountryBean(Long id, int tripId, String countyId, String county, String banner, boolean isLocationCity) {
        this.id = id;
        this.tripId = tripId;
        this.countyId = countyId;
        this.county = county;
        this.banner = banner;
        this.isLocationCity = isLocationCity;
    }

    @Generated(hash = 2046296519)
    public CountryBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public String getCountyId() {
        return countyId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public boolean isLocationCity() {
        return isLocationCity;
    }

    public void setLocationCity(boolean locationCity) {
        isLocationCity = locationCity;
    }

    public boolean getIsLocationCity() {
        return this.isLocationCity;
    }

    public void setIsLocationCity(boolean isLocationCity) {
        this.isLocationCity = isLocationCity;
    }
}
