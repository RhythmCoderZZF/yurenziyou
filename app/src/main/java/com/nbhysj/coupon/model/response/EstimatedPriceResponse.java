package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2019/8/27
 * description：预估价格
 */
public class EstimatedPriceResponse {

    private String name;

    private double price;

    private double originPrice;

    private int distance;

    private int duration;

    private String priceKey;

    private int derateType;

    private String dynamicRuleId;

    private String carType;

    private int lineType;

    private int cityCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriceKey() {
        return priceKey;
    }

    public void setPriceKey(String priceKey) {
        this.priceKey = priceKey;
    }

    public int getDerateType() {
        return derateType;
    }

    public void setDerateType(int derateType) {
        this.derateType = derateType;
    }

    public String getDynamicRuleId() {
        return dynamicRuleId;
    }

    public void setDynamicRuleId(String dynamicRuleId) {
        this.dynamicRuleId = dynamicRuleId;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public int getLineType() {
        return lineType;
    }

    public void setLineType(int lineType) {
        this.lineType = lineType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(double originPrice) {
        this.originPrice = originPrice;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }
}
