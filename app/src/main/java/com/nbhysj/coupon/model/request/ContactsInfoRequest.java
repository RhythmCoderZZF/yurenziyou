package com.nbhysj.coupon.model.request;

/**
 * @auther：hysj created on 2019/3/15
 * description：联系人信息
 */
public class ContactsInfoRequest {

    private int id;

    //用户id
    private int userId;

    //真实姓名
    private String realname;

    //微信号
    private String wechat;

    //手机号
    private String mobile;

    //邮箱
    private String email;

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

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
