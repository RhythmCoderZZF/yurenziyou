package com.nbhysj.coupon.model.request;

/**
 * @auther：hysj created on 2019/3/5
 * description:找回密码
 */

public class FindPwdByPhoneRequest {

    private String mobile;

    private String password;

    private String auth;

    private String salt;

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}
