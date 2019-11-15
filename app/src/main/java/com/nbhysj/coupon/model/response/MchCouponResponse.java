package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/11/2
 * description：领取商户券
 */
public class MchCouponResponse {

    private String type;

    private List<CouponsBean> coupons;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<CouponsBean> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<CouponsBean> coupons) {
        this.coupons = coupons;
    }
}
