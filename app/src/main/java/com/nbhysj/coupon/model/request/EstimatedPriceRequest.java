package com.nbhysj.coupon.model.request;

/**
 * @auther：hysj created on 2019/8/27
 * description：
 */
public class EstimatedPriceRequest {

    //出发时间，非实时单必填(格式：yyyy-MM-dd HH:mm:ss)，预约单出发时间必须大于等于当前时间30分钟小于等于三天
    private String departureTime;

    //目标经度
    private String endLg;

    //目标纬度
    private String endLt;

    //上车经度
    private String startLg;

    //上车纬度
    private String startLt;

    //服务车型，多种车型用英文逗号分隔,如 1,2 ，1 出租车（暂无该车型）；2 新能源；3 舒适型；4 豪华型；5 商务型
    private String carType;

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
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

    public String getStartLg() {
        return startLg;
    }

    public void setStartLg(String startLg) {
        this.startLg = startLg;
    }

    public String getStartLt() {
        return startLt;
    }

    public void setStartLt(String startLt) {
        this.startLt = startLt;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }
}
