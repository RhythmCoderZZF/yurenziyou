package com.nbhysj.coupon.model.response;

import com.nbhysj.coupon.model.request.CarEstimatePriceBean;
import com.nbhysj.coupon.model.request.GoodsBeanRequest;

import java.util.List;

/**
 * @auther：hysj created on 2019/11/03
 * description：查询日历价格下可选的所有优惠券
 */
public class QueryByTicketResponse {

    //商品列表
    private List<CouponsBean> coupons;

    //优惠券数量
    private int num;

    public List<CouponsBean> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<CouponsBean> coupons) {
        this.coupons = coupons;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
