package com.nbhysj.coupon.model.request;

/**
 * @auther：hysj created on 2019/3/15
 * description：旅客信息
 */
public class TravellerInfoRequest {

    //数据id
    private int id;

    //用户id
    private int userId;

    //真实姓名
    private String realname;

    //证件类型
    private String identityType;

    //证件号码
    private String identityNo;

    //手机号
    private String mobile;

    //是否设置为本人  1:本人 0:非本人
    private int selfStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getSelfStatus() {
        return selfStatus;
    }

    public void setSelfStatus(int selfStatus) {
        this.selfStatus = selfStatus;
    }
}
