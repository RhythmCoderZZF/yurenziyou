package com.nbhysj.coupon.model.request;

/**
 * @auther：hysj created on 2019/3/5
 * description:用户登录(用户手输账号密码)
 */

public class LoginByUsernameRequest {

    private String username;

    private String password;

    private String auth;

    private String salt;

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
