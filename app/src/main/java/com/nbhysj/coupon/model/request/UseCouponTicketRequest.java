package com.nbhysj.coupon.model.request;

import java.util.List;

/**
 * @auther：hysj created on 2019/11/04
 * description：选择使用优惠券
 */
public class UseCouponTicketRequest {

    private List<Integer> chooseIds;

    private int newUseId;

    //商品列表
    private List<GoodsBeanRequest> goods;

    //用车参数
    private List<CarEstimatePriceBean> cars;

    public List<GoodsBeanRequest> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBeanRequest> goods) {
        this.goods = goods;
    }

    public List<CarEstimatePriceBean> getCars() {
        return cars;
    }

    public void setCars(List<CarEstimatePriceBean> cars) {
        this.cars = cars;
    }

    public int getNewUseId() {
        return newUseId;
    }

    public void setNewUseId(int newUseId) {
        this.newUseId = newUseId;
    }

    public List<Integer> getChooseIds() {
        return chooseIds;
    }

    public void setChooseIds(List<Integer> chooseIds) {
        this.chooseIds = chooseIds;
    }
}
