package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2019/10/31
 * description：优惠券
 */
public class CouponsGetBean {

    //优惠券当否还能获取
    private int canGetAgainStatus;

    public int getCanGetAgainStatus() {
        return canGetAgainStatus;
    }

    public void setCanGetAgainStatus(int canGetAgainStatus) {
        this.canGetAgainStatus = canGetAgainStatus;
    }
}
