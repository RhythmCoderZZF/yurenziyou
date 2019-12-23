package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/12/23
 * description：
 */
public class LandlordDetailResonse {

    private LandlordBean landlord;

    private List<HomestayBean> Property;

    public LandlordBean getLandlord() {
        return landlord;
    }

    public void setLandlord(LandlordBean landlord) {
        this.landlord = landlord;
    }

    public List<HomestayBean> getProperty() {
        return Property;
    }

    public void setProperty(List<HomestayBean> property) {
        Property = property;
    }
}
