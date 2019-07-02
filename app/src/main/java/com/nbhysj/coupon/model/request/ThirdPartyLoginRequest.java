package com.nbhysj.coupon.model.request;

/**
 * @auther：hysj created on 2019/3/22
 * description：第三方登录参数
 */
public class ThirdPartyLoginRequest {

    //登陆类型 微信WECHAT 腾讯QQ 微博WEIBO
    private String loginType;

    //用户标识（当使用微信或qq登陆openid为必选项）
    private String openid;

    //用户统一标识（当使用微博登陆时uniodID为必选项）
    private String unionid;

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
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
}
