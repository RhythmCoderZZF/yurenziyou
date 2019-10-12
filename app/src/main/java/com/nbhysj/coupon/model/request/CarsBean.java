package com.nbhysj.coupon.model.request;

/**
 * @auther：hysj created on 2019/8/30
 * description：用车参数
 */
public class CarsBean {

    //服务车型：2 新能源；3 舒适型；4 豪华型；5 商务型（不支持一次性下多个订单）
    private int carType;

    //用车时间
    private String departureTime;

    //开始纬度
    private String startLt;

    //开始经度
    private String startLg;

    //开始经度
    private String endLg;

    //结束纬度
    private String endLt;

    //开始地点
    private String startName;

    //结束地点
    private String endName;

    //预估金额的key，一口价订单会对预估金额做校验，priceKey有效期为5分钟，如果priceKey过期或出发地经纬度有变化必须重新预估
    private String priceKey;

    //预估价格，单位分
    private double price;

    //预估时间(预估方法获得)
    private int duration;

    //城市ID
    private int cityCode;

    private int lineType;

    public int getCarType() {
        return carType;
    }

    public void setCarType(int carType) {
        this.carType = carType;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getStartLt() {
        return startLt;
    }

    public void setStartLt(String startLt) {
        this.startLt = startLt;
    }

    public String getStartLg() {
        return startLg;
    }

    public void setStartLg(String startLg) {
        this.startLg = startLg;
    }

    public String getEndLg() {
        return endLg;
    }

    public void setEndLg(String endLg) {
        this.endLg = endLg;
    }

    public String getEndLt() {
        return endLt;
    }

    public void setEndLt(String endLt) {
        this.endLt = endLt;
    }

    public String getStartName() {
        return startName;
    }

    public void setStartName(String startName) {
        this.startName = startName;
    }

    public String getEndName() {
        return endName;
    }

    public void setEndName(String endName) {
        this.endName = endName;
    }

    public String getPriceKey() {
        return priceKey;
    }

    public void setPriceKey(String priceKey) {
        this.priceKey = priceKey;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public int getLineType() {
        return lineType;
    }

    public void setLineType(int lineType) {
        this.lineType = lineType;
    }
}
