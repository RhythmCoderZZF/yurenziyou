package com.nbhysj.coupon.model.request;

/**
 * @auther：hysj created on 2019/3/15
 * description：1.删除旅客信息 2.删除联系人信息 3.收件人信息   id
 */
public class DeleteTravellerInfoRequest {

    //数据id
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
