package com.nbhysj.coupon.model.request;

/**
 * @auther：hysj created on 2019/11/3
 * description：预估价格(如果有车辆必有)
 */
public class CarEstimatePriceBean {

    private double estimatePrice;

    public double getEstimatePrice() {
        return estimatePrice;
    }

    public void setEstimatePrice(double estimatePrice) {
        this.estimatePrice = estimatePrice;
    }
}
