package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2019/8/29
 * description：车类型
 */
public class CarTypeBean {

    //车类型
    private String carTypeName;

    //车类型code
    private int carTypeCode;

    public String getCarTypeName() {
        return carTypeName;
    }

    public void setCarTypeName(String carTypeName) {
        this.carTypeName = carTypeName;
    }

    public int getCarTypeCode() {
        return carTypeCode;
    }

    public void setCarTypeCode(int carTypeCode) {
        this.carTypeCode = carTypeCode;
    }
}
