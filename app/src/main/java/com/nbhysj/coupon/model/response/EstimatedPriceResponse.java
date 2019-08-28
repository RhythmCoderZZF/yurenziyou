package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2019/8/27
 * description：预估价格
 */
public class EstimatedPriceResponse {

    private String name;

    private String price;

    private String originPrice;

    private String distance;

    private String duration;

    private String priceKey;

    private int derateType;

    private String dynamicRuleId;

    private String carType;

    private int lineType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(String originPrice) {
        this.originPrice = originPrice;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
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
}
