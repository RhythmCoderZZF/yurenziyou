package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/8/20
 * description：门票订单提交界面初始化接口
 */
public class OrderSubmitInitResponse {

    private List<TravellersEntity> travellers;

    private List<GoodsPriceEntity> goodsPrice;

    public List<TravellersEntity> getTravellers() {
        return travellers;
    }

    public void setTravellers(List<TravellersEntity> travellers) {
        this.travellers = travellers;
    }

    public List<GoodsPriceEntity> getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(List<GoodsPriceEntity> goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public class TravellersEntity{

        private int id;

        private long cTime;

        private int del;

        private int sex;

        private int status;

        private long uTime;

        private int userId;

        private String identityNo;

        private String identityType;

        private String nation;

        private String nationality;

        private String realname;

        private int travellerType;

        private String birthday;

        private String mobile;

        private int selfStatus;

        //是否选中游客
        private boolean isTravellerSelect;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getcTime() {
            return cTime;
        }

        public void setcTime(long cTime) {
            this.cTime = cTime;
        }

        public int getDel() {
            return del;
        }

        public void setDel(int del) {
            this.del = del;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public long getuTime() {
            return uTime;
        }

        public void setuTime(long uTime) {
            this.uTime = uTime;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getIdentityNo() {
            return identityNo;
        }

        public void setIdentityNo(String identityNo) {
            this.identityNo = identityNo;
        }

        public String getIdentityType() {
            return identityType;
        }

        public void setIdentityType(String identityType) {
            this.identityType = identityType;
        }

        public String getNation() {
            return nation;
        }

        public void setNation(String nation) {
            this.nation = nation;
        }

        public String getNationality() {
            return nationality;
        }

        public void setNationality(String nationality) {
            this.nationality = nationality;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public int getTravellerType() {
            return travellerType;
        }

        public void setTravellerType(int travellerType) {
            this.travellerType = travellerType;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getSelfStatus() {
            return selfStatus;
        }

        public void setSelfStatus(int selfStatus) {
            this.selfStatus = selfStatus;
        }

        public boolean isTravellerSelect() {
            return isTravellerSelect;
        }

        public void setTravellerSelect(boolean travellerSelect) {
            isTravellerSelect = travellerSelect;
        }
    }

    public class GoodsPriceEntity{

        private int bookingDay;

        private int bookingHour;

        private int bookingMinute;

        //入园方式:无需换票，直接验证入园TICKET_CHANGE_NO换票入园TICKET_CHANGE
        private String bookingType;

        private int bookingMaxDay;

        private int buyMax;

        private int goodsId;

        private int mchId;

        private String title;

        private int defaultPrice;

        private int insuranceStatus;

        private int adult;

        private int children;

        private String photo;

        private String exclusiveNote;

        private String goodsItemJson;

        private String goodsItemNote;

        private String mchName;

        private String contactsJson;

        private String userJson;

        private int buyMin;

        private String confineInfo;

        private int intoNum;

        //(退款模式)0.随时退 1.有条件退 2.不可退款
        private String refundSettings;

        private String refundNote;

        private int twoConfirmStatus;

        private List<GoodsPriceDatesResponse> goodsPriceDates;

        private int sellStatus;

        private String ticketIntoType;

        private int otherPrice;

        private int otherMarketPrice;

        private int otherId;

        public int getSellStatus() {
            return sellStatus;
        }

        public void setSellStatus(int sellStatus) {
            this.sellStatus = sellStatus;
        }

        public String getTicketIntoType() {
            return ticketIntoType;
        }

        public void setTicketIntoType(String ticketIntoType) {
            this.ticketIntoType = ticketIntoType;
        }

        public int getOtherPrice() {
            return otherPrice;
        }

        public void setOtherPrice(int otherPrice) {
            this.otherPrice = otherPrice;
        }

        public int getOtherMarketPrice() {
            return otherMarketPrice;
        }

        public void setOtherMarketPrice(int otherMarketPrice) {
            this.otherMarketPrice = otherMarketPrice;
        }

        public int getOtherId() {
            return otherId;
        }

        public void setOtherId(int otherId) {
            this.otherId = otherId;
        }

        public int getBookingDay() {
            return bookingDay;
        }

        public void setBookingDay(int bookingDay) {
            this.bookingDay = bookingDay;
        }

        public int getBookingHour() {
            return bookingHour;
        }

        public void setBookingHour(int bookingHour) {
            this.bookingHour = bookingHour;
        }

        public int getBookingMinute() {
            return bookingMinute;
        }

        public void setBookingMinute(int bookingMinute) {
            this.bookingMinute = bookingMinute;
        }

        public String getBookingType() {
            return bookingType;
        }

        public void setBookingType(String bookingType) {
            this.bookingType = bookingType;
        }

        public int getBookingMaxDay() {
            return bookingMaxDay;
        }

        public void setBookingMaxDay(int bookingMaxDay) {
            this.bookingMaxDay = bookingMaxDay;
        }

        public int getBuyMax() {
            return buyMax;
        }

        public void setBuyMax(int buyMax) {
            this.buyMax = buyMax;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public int getMchId() {
            return mchId;
        }

        public void setMchId(int mchId) {
            this.mchId = mchId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getDefaultPrice() {
            return defaultPrice;
        }

        public void setDefaultPrice(int defaultPrice) {
            this.defaultPrice = defaultPrice;
        }

        public int getInsuranceStatus() {
            return insuranceStatus;
        }

        public void setInsuranceStatus(int insuranceStatus) {
            this.insuranceStatus = insuranceStatus;
        }

        public int getAdult() {
            return adult;
        }

        public void setAdult(int adult) {
            this.adult = adult;
        }

        public int getChildren() {
            return children;
        }

        public void setChildren(int children) {
            this.children = children;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getExclusiveNote() {
            return exclusiveNote;
        }

        public void setExclusiveNote(String exclusiveNote) {
            this.exclusiveNote = exclusiveNote;
        }

        public String getGoodsItemJson() {
            return goodsItemJson;
        }

        public void setGoodsItemJson(String goodsItemJson) {
            this.goodsItemJson = goodsItemJson;
        }

        public String getGoodsItemNote() {
            return goodsItemNote;
        }

        public void setGoodsItemNote(String goodsItemNote) {
            this.goodsItemNote = goodsItemNote;
        }

        public String getMchName() {
            return mchName;
        }

        public void setMchName(String mchName) {
            this.mchName = mchName;
        }

        public String getContactsJson() {
            return contactsJson;
        }

        public void setContactsJson(String contactsJson) {
            this.contactsJson = contactsJson;
        }

        public String getUserJson() {
            return userJson;
        }

        public void setUserJson(String userJson) {
            this.userJson = userJson;
        }

        public int getBuyMin() {
            return buyMin;
        }

        public void setBuyMin(int buyMin) {
            this.buyMin = buyMin;
        }

        public String getConfineInfo() {
            return confineInfo;
        }

        public void setConfineInfo(String confineInfo) {
            this.confineInfo = confineInfo;
        }

        public int getIntoNum() {
            return intoNum;
        }

        public void setIntoNum(int intoNum) {
            this.intoNum = intoNum;
        }

        public String getRefundSettings() {
            return refundSettings;
        }

        public void setRefundSettings(String refundSettings) {
            this.refundSettings = refundSettings;
        }

        public String getRefundNote() {
            return refundNote;
        }

        public void setRefundNote(String refundNote) {
            this.refundNote = refundNote;
        }

        public int getTwoConfirmStatus() {
            return twoConfirmStatus;
        }

        public void setTwoConfirmStatus(int twoConfirmStatus) {
            this.twoConfirmStatus = twoConfirmStatus;
        }

        public List<GoodsPriceDatesResponse> getGoodsPriceDates() {
            return goodsPriceDates;
        }

        public void setGoodsPriceDates(List<GoodsPriceDatesResponse> goodsPriceDates) {
            this.goodsPriceDates = goodsPriceDates;
        }
    }
}
