package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/6/12
 * description：目的地
 */
public class DestinationResponse {

    private List<HomestaysEntity> homestays;

    //省
    private String province;

    //市
    private String city;

    private String county;

    //攻略
    private List<MchTypeBean> Strategys;

    //互动
    private List<MchTypeBean> Interaction;


    private List<BannerBean> banner;

    private int cityId;

    private List<MchTypeBean> mches;

    private List<MchTypeBean> Food;

    public List<HomestaysEntity> getHomestays() {
        return homestays;
    }

    public void setHomestays(List<HomestaysEntity> homestays) {
        this.homestays = homestays;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public List<MchTypeBean> getStrategys() {
        return Strategys;
    }

    public void setStrategys(List<MchTypeBean> strategys) {
        Strategys = strategys;
    }

    public List<MchTypeBean> getInteraction() {
        return Interaction;
    }

    public void setInteraction(List<MchTypeBean> interaction) {
        Interaction = interaction;
    }

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public List<MchTypeBean> getMches() {
        return mches;
    }

    public void setMches(List<MchTypeBean> mches) {
        this.mches = mches;
    }

    public List<MchTypeBean> getFood() {
        return Food;
    }

    public void setFood(List<MchTypeBean> food) {
        Food = food;
    }

    public class HomestaysEntity {

        private String mchName;

        private String photo;

        private int mchId;

        private List<BaseTagsBean> tags;

        public String getMchName() {
            return mchName;
        }

        public void setMchName(String mchName) {
            this.mchName = mchName;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public int getMchId() {
            return mchId;
        }

        public void setMchId(int mchId) {
            this.mchId = mchId;
        }

        public List<BaseTagsBean> getTags() {
            return tags;
        }

        public void setTags(List<BaseTagsBean> tags) {
            this.tags = tags;
        }
    }

}
