package com.nbhysj.coupon.model.request;

/**
 * @auther：hysj created on 2019/3/22
 * description：首次第三方登陆需注册
 */
public class ThirdPartyLoginCreateUserBind {

    //登陆类型 微信WECHAT 腾讯QQ 微博WEIBO
    private String loginType;

    //手机号码
    private String mobile;

    //手机验证码
    private String auth;

    //用户TOKEN
    private String accessToken;

    //用户标识（当使用微信或qq注册openid为必选项）
    private String openid;

    //用户统一标识（当使用微博注册时uniodID为必选项）,当注册微信时微信用户信息存在unionid时(建议选择)
    private String unionid;

    //用户信息uid(当使用微博注册openid为必选项)
    private String uid;

    //1:等于绑定 0:等于注册
    private int bind;

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getBind() {
        return bind;
    }

    public void setBind(int bind) {
        this.bind = bind;
    }
}
