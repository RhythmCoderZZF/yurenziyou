package com.nbhysj.coupon.model.request;

/**
 * @auther：hysj created on 2019/3/15
 * description：收件人信息
 */
public class RecipientsInfoRequest {

    //数据id
    private int id;

    //收货地址
    private int address;

    //省
    private String province;

    //城市
    private String city;

    //区县
    private String county;

    //手机号
    private String mobile;

    //收货人
    private String consignee;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }
}
