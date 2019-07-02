package com.nbhysj.coupon.model.request;

/**
 * @auther：hysj created on 2019/3/5
 * description:查询商户
 */

public class MerchantRequest {

    //城市id
    private String cityId;

    //商户名
    private String mchName;

    private int page;

    private int pageSize;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getMchName() {
        return mchName;
    }

    public void setMchName(String mchName) {
        this.mchName = mchName;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
