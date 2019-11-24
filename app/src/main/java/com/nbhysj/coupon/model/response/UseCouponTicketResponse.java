package com.nbhysj.coupon.model.response;

import com.nbhysj.coupon.model.request.CarEstimatePriceBean;
import com.nbhysj.coupon.model.request.GoodsBeanRequest;

import java.util.List;

/**
 * @auther：hysj created on 2019/11/05
 * description：选择使用优惠券
 */
public class UseCouponTicketResponse {

    private double discount;

    private List<Integer> chooseId;

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public List<Integer> getChooseId() {
        return chooseId;
    }

    public void setChooseId(List<Integer> chooseId) {
        this.chooseId = chooseId;
    }
}
