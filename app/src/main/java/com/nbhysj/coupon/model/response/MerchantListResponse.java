package com.nbhysj.coupon.model.response;

import java.io.Serializable;
import java.util.List;

/**
 * @auther：hysj created on 2019/3/30
 * description：商户
 */
public class MerchantListResponse implements Serializable {

    //商户列表返回
    private List<MerchantEntity> result;

    private BasePaginationResult page;

    public List<MerchantEntity> getResult() {
        return result;
    }

    public void setResult(List<MerchantEntity> result) {
        this.result = result;
    }

    public BasePaginationResult getPage() {
        return page;
    }

    public void setPage(BasePaginationResult page) {
        this.page = page;
    }

    public class MerchantEntity implements Serializable {

        private int mchId;

        private String mchName;

        private String mchType;

        private int level;

        private String intro;

        private int score;

        private int commentNum;

        private String photo;

        private String seoDesc;

        private int selectedStatus;

        private String tags;

        private double price;

        public int getMchId() {
            return mchId;
        }

        public void setMchId(int mchId) {
            this.mchId = mchId;
        }

        public String getMchName() {
            return mchName;
        }

        public void setMchName(String mchName) {
            this.mchName = mchName;
        }

        public String getMchType() {
            return mchType;
        }

        public void setMchType(String mchType) {
            this.mchType = mchType;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getSeoDesc() {
            return seoDesc;
        }

        public void setSeoDesc(String seoDesc) {
            this.seoDesc = seoDesc;
        }

        public int getSelectedStatus() {
            return selectedStatus;
        }

        public void setSelectedStatus(int selectedStatus) {
            this.selectedStatus = selectedStatus;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }


  /*  public class MerchantEntity implements Serializable {

        private int id;

        private String mchNo;

        private String mchName;

        private String mchType;

        private String photo;

        private String longitude;

        private String latitude;

        private String province;

        private String city;

        private String county;

        private String provinceId;

        private String cityId;

        private String countyId;

        private String address;

        private String tel;

        private String commentScore;

        private String sellNum;

        private String intro;

        private String creditCode;

        private String company;

        private String businessLicense;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMchNo() {
            return mchNo;
        }

        public void setMchNo(String mchNo) {
            this.mchNo = mchNo;
        }

        public String getMchName() {
            return mchName;
        }

        public void setMchName(String mchName) {
            this.mchName = mchName;
        }

        public String getMchType() {
            return mchType;
        }

        public void setMchType(String mchType) {
            this.mchType = mchType;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
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

        public String getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(String provinceId) {
            this.provinceId = provinceId;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public String getCountyId() {
            return countyId;
        }

        public void setCountyId(String countyId) {
            this.countyId = countyId;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getCommentScore() {
            return commentScore;
        }

        public void setCommentScore(String commentScore) {
            this.commentScore = commentScore;
        }

        public String getSellNum() {
            return sellNum;
        }

        public void setSellNum(String sellNum) {
            this.sellNum = sellNum;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getCreditCode() {
            return creditCode;
        }

        public void setCreditCode(String creditCode) {
            this.creditCode = creditCode;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getBusinessLicense() {
            return businessLicense;
        }

        public void setBusinessLicense(String businessLicense) {
            this.businessLicense = businessLicense;
        }
    }*/

}
