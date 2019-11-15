package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2019/6/14
 * description：
 */
public class TravelAssistantDetailCountryBean {

    private int ranking;

    private String recommend;

    private int status;

    private String countyName;

    private String banner;

    private int countyId;

    private boolean isSelect;

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public int getCountyId() {
        return countyId;
    }

    public void setCountyId(int countyId) {
        this.countyId = countyId;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }
}
