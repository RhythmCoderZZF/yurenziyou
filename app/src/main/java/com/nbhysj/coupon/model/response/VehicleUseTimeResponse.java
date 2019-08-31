package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2019/7/14
 * description：用车
 */
public class VehicleUseTimeResponse {

    //时间
    private String date;

    //用车时间
    private String vehicleUseTime;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVehicleUseTime() {
        return vehicleUseTime;
    }

    public void setVehicleUseTime(String vehicleUseTime) {
        this.vehicleUseTime = vehicleUseTime;
    }
}
