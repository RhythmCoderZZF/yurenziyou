package com.nbhysj.coupon.model.response;

import java.io.Serializable;
import java.util.List;

/**
 * @auther：hysj created on 2019/3/20
 * description：城市类
 */
public class RecipientsCityBean implements Serializable {

    private String id;

    private String name;

    private String cityCode;

    private int level;

    private int pid;

    private String letter;

    private String cityVOs;

    //子级地区或者县城
    private List<DistrictBean> cities;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCityVOs() {
        return cityVOs;
    }

    public void setCityVOs(String cityVOs) {
        this.cityVOs = cityVOs;
    }

    public List<DistrictBean> getCities() {
        return cities;
    }

    public void setCities(List<DistrictBean> cities) {
        this.cities = cities;
    }
}
