package com.nbhysj.coupon.model.response;

import java.io.Serializable;
import java.util.List;

/**
 * @auther：hysj created on 2019/10/09
 * description：首页搜索商户返回
 */
public class HomeSearchMchTypeBean implements Serializable {

    private String id;

    private int dataId;
    //商户名字字段(榜单)
    private String dataName;

    private String mchNo;

    private int recreationId;

    private String mchName;

    private String mchType;

    private String mchType2;

    private String photo;

    private String longitude;

    private String latitude;

    private String address;

    private String tel;

    private float commentScore;

    private String intro;

    private int businessStatus;

    private String company;

    private int consumePrice;

    private String level;

    private String hotelType;

    private String county;

    private String city;

    private int commentNum;

    private List<String> tags;

    private String countyId;

    private String distance;

    private long ctime;

    private String hotelTypeName;

    /***  订单详情(猜你喜欢)字段 ***/

    private int mchId;

    private int commentCount;

    private int price;

    /********* 民宿字段 **********/

    private int loveStatus;

    private String lord;

    private String avatar;

    private int authenticationStatus;

    /*********   攻略 首页搜索   *************/
    private int collectionNum;

    private String strategyH5Url;

    private String searchType;

    private String hotelType1;

    private String hotelType2;

    private int zanNum;

    private String title2;

    private String title;

    private String searchTitle;

    private String content;

    /**************   组合    ********************/

    private String packageType;

    private String marketPrice;

    /*************    帖子    **********************/

    private String resourceUrl;

    private String postsType;

    public int getLoveStatus() {
        return loveStatus;
    }

    public void setLoveStatus(int loveStatus) {
        this.loveStatus = loveStatus;
    }

    public String getLord() {
        return lord;
    }

    public void setLord(String lord) {
        this.lord = lord;
    }

    public int getAuthenticationStatus() {
        return authenticationStatus;
    }

    public void setAuthenticationStatus(int authenticationStatus) {
        this.authenticationStatus = authenticationStatus;
    }

    public int getMchId() {
        return mchId;
    }

    public void setMchId(int mchId) {
        this.mchId = mchId;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }

    /************************/


    public int getDataId() {
        return dataId;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getMchNo() {
        return mchNo;
    }

    public void setMchNo(String mchNo) {
        this.mchNo = mchNo;
    }

    public int getRecreationId() {
        return recreationId;
    }

    public void setRecreationId(int recreationId) {
        this.recreationId = recreationId;
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

    public String getMchType2() {
        return mchType2;
    }

    public void setMchType2(String mchType2) {
        this.mchType2 = mchType2;
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

    public float getCommentScore() {
        return commentScore;
    }

    public void setCommentScore(float commentScore) {
        this.commentScore = commentScore;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(int businessStatus) {
        this.businessStatus = businessStatus;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getConsumePrice() {
        return consumePrice;
    }

    public void setConsumePrice(int consumePrice) {
        this.consumePrice = consumePrice;
    }

    public void setHotelType(String hotelType) {
        this.hotelType = hotelType;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getCountyId() {
        return countyId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public class TagsEntity {

        private int id;

        private String mchNo;

        private int mchId;

        private String mchType;

        private String title;

        private String value;

        private int zanNum;

        private long cTime;

        private long uTime;

        private int del;

        private int supplierId;

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

        public int getMchId() {
            return mchId;
        }

        public void setMchId(int mchId) {
            this.mchId = mchId;
        }

        public String getMchType() {
            return mchType;
        }

        public void setMchType(String mchType) {
            this.mchType = mchType;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public int getZanNum() {
            return zanNum;
        }

        public void setZanNum(int zanNum) {
            this.zanNum = zanNum;
        }

        public long getcTime() {
            return cTime;
        }

        public void setcTime(long cTime) {
            this.cTime = cTime;
        }

        public long getuTime() {
            return uTime;
        }

        public void setuTime(long uTime) {
            this.uTime = uTime;
        }

        public int getDel() {
            return del;
        }

        public void setDel(int del) {
            this.del = del;
        }

        public int getSupplierId() {
            return supplierId;
        }

        public void setSupplierId(int supplierId) {
            this.supplierId = supplierId;
        }
    }

    public int getCollectionNum() {
        return collectionNum;
    }

    public void setCollectionNum(int collectionNum) {
        this.collectionNum = collectionNum;
    }

    public String getStrategyH5Url() {
        return strategyH5Url;
    }

    public void setStrategyH5Url(String strategyH5Url) {
        this.strategyH5Url = strategyH5Url;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getHotelType2() {
        return hotelType2;
    }

    public void setHotelType2(String hotelType2) {
        this.hotelType2 = hotelType2;
    }

    public int getZanNum() {
        return zanNum;
    }

    public void setZanNum(int zanNum) {
        this.zanNum = zanNum;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSearchTitle() {
        return searchTitle;
    }

    public void setSearchTitle(String searchTitle) {
        this.searchTitle = searchTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getHotelTypeName() {
        return hotelTypeName;
    }

    public void setHotelTypeName(String hotelTypeName) {
        this.hotelTypeName = hotelTypeName;
    }

    public String getHotelType() {
        return hotelType;
    }

    public String getHotelType1() {
        return hotelType1;
    }

    public void setHotelType1(String hotelType1) {
        this.hotelType1 = hotelType1;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public String getPostsType() {
        return postsType;
    }

    public void setPostsType(String postsType) {
        this.postsType = postsType;
    }
}
