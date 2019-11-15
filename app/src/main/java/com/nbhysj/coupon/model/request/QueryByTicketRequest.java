package com.nbhysj.coupon.model.request;

import java.util.List;

/**
 * @auther：hysj created on 2019/11/03
 * description：查询日历价格下可选的所有优惠券
 */
public class QueryByTicketRequest {

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
}
