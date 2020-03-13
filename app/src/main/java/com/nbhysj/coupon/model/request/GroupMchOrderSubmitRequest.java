package com.nbhysj.coupon.model.request;

import com.nbhysj.coupon.model.response.GoodsBean;

import java.util.List;

/**
 * @auther：hysj created on 2019/8/30
 * description：下单接口
 */
public class GroupMchOrderSubmitRequest {

    //组合Id
    private int groupId;

    //数量
    private int num;

    //旅客Id
    private int userTravelerId;

    //商品列表
    private List<GoodsBeanRequest> goods;

    //用车参数
    private List<CarsBean> cars;

    //是否用车 1=用0=不用
    private int carStatus;

    //是否用车（0:不用  1:用车）
    private int useCarStatus;

    public int getUserTravelerId() {
        return userTravelerId;
    }

    public void setUserTravelerId(int userTravelerId) {
        this.userTravelerId = userTravelerId;
    }

    public List<GoodsBeanRequest> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBeanRequest> goods) {
        this.goods = goods;
    }

    public List<CarsBean> getCars() {
        return cars;
    }

    public void setCars(List<CarsBean> cars) {
        this.cars = cars;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(int carStatus) {
        this.carStatus = carStatus;
    }

    public int getUseCarStatus() {
        return useCarStatus;
    }

    public void setUseCarStatus(int useCarStatus) {
        this.useCarStatus = useCarStatus;
    }
}
