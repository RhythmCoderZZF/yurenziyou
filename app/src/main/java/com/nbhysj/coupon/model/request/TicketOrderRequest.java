package com.nbhysj.coupon.model.request;

import java.util.List;

/**
 * @auther：hysj created on 2019/8/30
 * description：下单接口
 */
public class TicketOrderRequest {

    //旅客Id
    private int userTravelerId;

    //商品列表
    private List<GoodsBean> goods;

    //是否用车 1=用0=不用
    private int carStatus;

    //用车参数
    private List<CarsBean> cars;

    public int getUserTravelerId() {
        return userTravelerId;
    }

    public void setUserTravelerId(int userTravelerId) {
        this.userTravelerId = userTravelerId;
    }

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    public int getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(int carStatus) {
        this.carStatus = carStatus;
    }

    public List<CarsBean> getCars() {
        return cars;
    }

    public void setCars(List<CarsBean> cars) {
        this.cars = cars;
    }
}
