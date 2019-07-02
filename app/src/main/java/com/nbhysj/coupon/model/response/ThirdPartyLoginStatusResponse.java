package com.nbhysj.coupon.model.response;

import java.io.Serializable;

/**
 * @auther：hysj created on 2019/3/22
 * description：第三方状态绑定对象
 */
public class ThirdPartyLoginStatusResponse implements Serializable {

    //QQ状态
    private boolean QQ;

    //微博状态
    private boolean WEIBO;

    //微信
    private boolean WECHAT;

    //邮箱
    private boolean EMAIL;

    //手机号
    private boolean MOBILE;

    public boolean isQQ() {
        return QQ;
    }

    public void setQQ(boolean QQ) {
        this.QQ = QQ;
    }

    public boolean isWEIBO() {
        return WEIBO;
    }

    public void setWEIBO(boolean WEIBO) {
        this.WEIBO = WEIBO;
    }

    public boolean isWECHAT() {
        return WECHAT;
    }

    public void setWECHAT(boolean WECHAT) {
        this.WECHAT = WECHAT;
    }

    public boolean isEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(boolean EMAIL) {
        this.EMAIL = EMAIL;
    }

    public boolean isMOBILE() {
        return MOBILE;
    }

    public void setMOBILE(boolean MOBILE) {
        this.MOBILE = MOBILE;
    }
}
