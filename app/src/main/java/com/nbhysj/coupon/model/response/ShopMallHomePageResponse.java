package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/6/1
 * description：商城首页
 */
public class ShopMallHomePageResponse {

    //首页banner
    private List<String> bigBanners;

    //中间小banner
    private List<String> smallBanners;

    //景点列表
    private List<MchTypeBean> scenicList;

    //美食
    private List<DeliciousFoodResponse> foodList;

    //酒店列表
    private List<MchTypeBean> hotelList;

    //商户目的地
    private List<MchCitiesBean> mchCities;

    //旅行主题
    private List<TravelBannersEntity> travelBanners;

    //自由行
    private List<GroupGoodsBean> groupGoodsVO;

    //猜你喜欢
    private List<GuessEntity> guess;


    public List<String> getBigBanners() {
        return bigBanners;
    }

    public void setBigBanners(List<String> bigBanners) {
        this.bigBanners = bigBanners;
    }

    public List<String> getSmallBanners() {
        return smallBanners;
    }

    public void setSmallBanners(List<String> smallBanners) {
        this.smallBanners = smallBanners;
    }

    public List<MchTypeBean> getScenicList() {
        return scenicList;
    }

    public void setScenicList(List<MchTypeBean> scenicList) {
        this.scenicList = scenicList;
    }

    public List<DeliciousFoodResponse> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<DeliciousFoodResponse> foodList) {
        this.foodList = foodList;
    }

    public List<MchTypeBean> getHotelList() {
        return hotelList;
    }

    public void setHotelList(List<MchTypeBean> hotelList) {
        this.hotelList = hotelList;
    }

    public List<MchCitiesBean> getMchCities() {
        return mchCities;
    }

    public void setMchCities(List<MchCitiesBean> mchCities) {
        this.mchCities = mchCities;
    }

    public List<TravelBannersEntity> getTravelBanners() {
        return travelBanners;
    }

    public void setTravelBanners(List<TravelBannersEntity> travelBanners) {
        this.travelBanners = travelBanners;
    }

    public List<GroupGoodsBean> getGroupGoodsVO() {
        return groupGoodsVO;
    }

    public void setGroupGoodsVO(List<GroupGoodsBean> groupGoodsVO) {
        this.groupGoodsVO = groupGoodsVO;
    }


    public List<GuessEntity> getGuess() {
        return guess;
    }

    public void setGuess(List<GuessEntity> guess) {
        this.guess = guess;
    }

    /* //酒店列表
    private List<HotelBean> hotelList;

    //旅行主题
    private List<TravelBannersEntity> travelBanners;

    //自由行
    private List<GroupGoodsBean> groupGoodsVO;

    //猜你喜欢
    private List<GuessEntity> guess;

    //美食
    private List<DeliciousFoodResponse> foodList;



    //未读消息
    private int unreadNum;

    //首页banner
    private List<String> bigBanners;

    //景点列表
    private List<MchTypeBean> scenicList;

    private List<LimitedSaleBean> limitedSale;

    public List<String> getSmallBanners() {
        return smallBanners;
    }

    public void setSmallBanners(List<String> smallBanners) {
        this.smallBanners = smallBanners;
    }

    public List<HotelBean> getHotelList() {
        return hotelList;
    }

    public void setHotelList(List<HotelBean> hotelList) {
        this.hotelList = hotelList;
    }

    public List<TravelBannersEntity> getTravelBanners() {
        return travelBanners;
    }

    public void setTravelBanners(List<TravelBannersEntity> travelBanners) {
        this.travelBanners = travelBanners;
    }

    public List<GroupGoodsBean> getGroupGoodsVO() {
        return groupGoodsVO;
    }

    public void setGroupGoodsVO(List<GroupGoodsBean> groupGoodsVO) {
        this.groupGoodsVO = groupGoodsVO;
    }

    public List<GuessEntity> getGuess() {
        return guess;
    }

    public void setGuess(List<GuessEntity> guess) {
        this.guess = guess;
    }

    public List<DeliciousFoodResponse> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<DeliciousFoodResponse> foodList) {
        this.foodList = foodList;
    }

    public List<MchCitiesBean> getMchCities() {
        return mchCities;
    }

    public void setMchCities(List<MchCitiesBean> mchCities) {
        this.mchCities = mchCities;
    }

    public int getUnreadNum() {
        return unreadNum;
    }

    public void setUnreadNum(int unreadNum) {
        this.unreadNum = unreadNum;
    }

    public List<String> getBigBanners() {
        return bigBanners;
    }

    public void setBigBanners(List<String> bigBanners) {
        this.bigBanners = bigBanners;
    }

    public List<MchTypeBean> getScenicList() {
        return scenicList;
    }

    public void setScenicList(List<MchTypeBean> scenicList) {
        this.scenicList = scenicList;
    }

    public List<LimitedSaleBean> getLimitedSale() {
        return limitedSale;
    }

    public void setLimitedSale(List<LimitedSaleBean> limitedSale) {
        this.limitedSale = limitedSale;
    }

    public class TravelBannersEntity {

        private int id;
        private int del;
        private long startTime;
        private long endTime;
        private int sort;
        private String bannerFlag;
        private String photo;
        private String title;
        private String url;
        private String urlType;
        private int status;
        private String intro;
        private long ctime;
        private long utime;
        private Object tails;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getDel() {
            return del;
        }

        public void setDel(int del) {
            this.del = del;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getBannerFlag() {
            return bannerFlag;
        }

        public void setBannerFlag(String bannerFlag) {
            this.bannerFlag = bannerFlag;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrlType() {
            return urlType;
        }

        public void setUrlType(String urlType) {
            this.urlType = urlType;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public long getCtime() {
            return ctime;
        }

        public void setCtime(long ctime) {
            this.ctime = ctime;
        }

        public long getUtime() {
            return utime;
        }

        public void setUtime(long utime) {
            this.utime = utime;
        }

        public Object getTails() {
            return tails;
        }

        public void setTails(Object tails) {
            this.tails = tails;
        }
    }



    /**
     * 猜你喜欢
     */
    public class GuessEntity {

        private int id;

        private int supplierId;

        private String mchNo;

        private String mchName;

        private String mchType;

        private String discountInfo;

        private String mchType2;

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

        private int commentScore;

        private int commentScore1;
        private int commentScore2;
        private int commentScore3;
        private int sellNum;
        private String intro;
        private String seoTitle;
        private String seoKeywords;

        private String seoDesc;

        private int status;

        private int businessStatus;
        private int sort;

        private int bookingStatus;

        private String bookingInfo;

        private int promotionStatus;

        private String creditCode;

        private String company;

        private String businessLicense;

        private String defaultPwd;

        private int del;

        private double consumePrice;

        private int recommStatus;

        private int commentNum;

        private long ctime;

        private long utime;

        ///   private TailsEntity tails;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSupplierId() {
            return supplierId;
        }

        public void setSupplierId(int supplierId) {
            this.supplierId = supplierId;
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

        public String getDiscountInfo() {
            return discountInfo;
        }

        public void setDiscountInfo(String discountInfo) {
            this.discountInfo = discountInfo;
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

        public int getCommentScore() {
            return commentScore;
        }

        public void setCommentScore(int commentScore) {
            this.commentScore = commentScore;
        }

        public int getCommentScore1() {
            return commentScore1;
        }

        public void setCommentScore1(int commentScore1) {
            this.commentScore1 = commentScore1;
        }

        public int getCommentScore2() {
            return commentScore2;
        }

        public void setCommentScore2(int commentScore2) {
            this.commentScore2 = commentScore2;
        }

        public int getCommentScore3() {
            return commentScore3;
        }

        public void setCommentScore3(int commentScore3) {
            this.commentScore3 = commentScore3;
        }

        public int getSellNum() {
            return sellNum;
        }

        public void setSellNum(int sellNum) {
            this.sellNum = sellNum;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getSeoTitle() {
            return seoTitle;
        }

        public void setSeoTitle(String seoTitle) {
            this.seoTitle = seoTitle;
        }

        public String getSeoKeywords() {
            return seoKeywords;
        }

        public void setSeoKeywords(String seoKeywords) {
            this.seoKeywords = seoKeywords;
        }

        public String getSeoDesc() {
            return seoDesc;
        }

        public void setSeoDesc(String seoDesc) {
            this.seoDesc = seoDesc;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getBusinessStatus() {
            return businessStatus;
        }

        public void setBusinessStatus(int businessStatus) {
            this.businessStatus = businessStatus;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getBookingStatus() {
            return bookingStatus;
        }

        public void setBookingStatus(int bookingStatus) {
            this.bookingStatus = bookingStatus;
        }

        public String getBookingInfo() {
            return bookingInfo;
        }

        public void setBookingInfo(String bookingInfo) {
            this.bookingInfo = bookingInfo;
        }

        public int getPromotionStatus() {
            return promotionStatus;
        }

        public void setPromotionStatus(int promotionStatus) {
            this.promotionStatus = promotionStatus;
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

        public String getDefaultPwd() {
            return defaultPwd;
        }

        public void setDefaultPwd(String defaultPwd) {
            this.defaultPwd = defaultPwd;
        }

        public int getDel() {
            return del;
        }

        public void setDel(int del) {
            this.del = del;
        }

        public double getConsumePrice() {
            return consumePrice;
        }

        public void setConsumePrice(double consumePrice) {
            this.consumePrice = consumePrice;
        }

        public int getRecommStatus() {
            return recommStatus;
        }

        public void setRecommStatus(int recommStatus) {
            this.recommStatus = recommStatus;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public long getCtime() {
            return ctime;
        }

        public void setCtime(long ctime) {
            this.ctime = ctime;
        }

        public long getUtime() {
            return utime;
        }

        public void setUtime(long utime) {
            this.utime = utime;
        }

      /*  public TailsEntity getTails() {
            return tails;
        }

        public void setTails(TailsEntity tails) {
            this.tails = tails;
        }*/
    }

    public class TailsEntity {

        private long recommTime;

        private boolean mchPrice;

        public long getRecommTime() {
            return recommTime;
        }

        public void setRecommTime(long recommTime) {
            this.recommTime = recommTime;
        }

        public boolean isMchPrice() {
            return mchPrice;
        }

        public void setMchPrice(boolean mchPrice) {
            this.mchPrice = mchPrice;
        }
    }

    public class TravelBannersEntity {

        private int id;
        private int del;
        private long startTime;
        private long endTime;
        private int sort;
        private String bannerFlag;
        private String photo;
        private String title;
        private String url;
        private String urlType;
        private int status;
        private String intro;
        private long ctime;
        private long utime;
        private Object tails;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getDel() {
            return del;
        }

        public void setDel(int del) {
            this.del = del;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getBannerFlag() {
            return bannerFlag;
        }

        public void setBannerFlag(String bannerFlag) {
            this.bannerFlag = bannerFlag;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrlType() {
            return urlType;
        }

        public void setUrlType(String urlType) {
            this.urlType = urlType;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public long getCtime() {
            return ctime;
        }

        public void setCtime(long ctime) {
            this.ctime = ctime;
        }

        public long getUtime() {
            return utime;
        }

        public void setUtime(long utime) {
            this.utime = utime;
        }

        public Object getTails() {
            return tails;
        }

        public void setTails(Object tails) {
            this.tails = tails;
        }
    }
}
