package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/6/13
 * description：商品订单对象
 */
public class MchGoodsBean {

    /******** 公共字段 *********/

    private double defaultPrice;

    private double marketPrice;

    private int sellNum;

    private String title;

   /*******  景点商品   *******/

    private int goodsId;

    private String bookingInfo;

    private String refundSettings;

    private String ticketIntoType;

    /*******  酒店民宿商品   *******/
    private int id;

    //面积
    private String acreage;

    //早餐状态
    private int breakfastStatus;

    private int commentNum;

    //电梯状态
    private int elevatorStatus;

    private int floor;

    private int insuranceStatus;

    private int intoNum;

    private int mchId;

    private int score;

    private int toiletStatus;

    private int wifiStatus;

    private int windowStatus;

    private String bedInfo;

    private String photo;

    private String roomNote;

    private String servicePhone;

    private String serviceTime;

    private int bedType;

    private String breakfastInfo;

    private String refundInfo;

    private String roomDetails;

    private List<String> photos;

    private long ctime;

    /********  组合  *********/
    private int packageId;

    private List<String> containCosts;

    private String goodsBuyNotes;

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public double getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(int defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public String getBookingInfo() {
        return bookingInfo;
    }

    public void setBookingInfo(String bookingInfo) {
        this.bookingInfo = bookingInfo;
    }

    public int getSellNum() {
        return sellNum;
    }

    public void setSellNum(int sellNum) {
        this.sellNum = sellNum;
    }

    public String getRefundSettings() {
        return refundSettings;
    }

    public void setRefundSettings(String refundSettings) {
        this.refundSettings = refundSettings;
    }

    public String getTicketIntoType() {
        return ticketIntoType;
    }

    public void setTicketIntoType(String ticketIntoType) {
        this.ticketIntoType = ticketIntoType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAcreage() {
        return acreage;
    }

    public void setAcreage(String acreage) {
        this.acreage = acreage;
    }

    public int getBreakfastStatus() {
        return breakfastStatus;
    }

    public void setBreakfastStatus(int breakfastStatus) {
        this.breakfastStatus = breakfastStatus;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public int getElevatorStatus() {
        return elevatorStatus;
    }

    public void setElevatorStatus(int elevatorStatus) {
        this.elevatorStatus = elevatorStatus;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getInsuranceStatus() {
        return insuranceStatus;
    }

    public void setInsuranceStatus(int insuranceStatus) {
        this.insuranceStatus = insuranceStatus;
    }

    public int getIntoNum() {
        return intoNum;
    }

    public void setIntoNum(int intoNum) {
        this.intoNum = intoNum;
    }

    public int getMchId() {
        return mchId;
    }

    public void setMchId(int mchId) {
        this.mchId = mchId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getToiletStatus() {
        return toiletStatus;
    }

    public void setToiletStatus(int toiletStatus) {
        this.toiletStatus = toiletStatus;
    }

    public int getWifiStatus() {
        return wifiStatus;
    }

    public void setWifiStatus(int wifiStatus) {
        this.wifiStatus = wifiStatus;
    }

    public int getWindowStatus() {
        return windowStatus;
    }

    public void setWindowStatus(int windowStatus) {
        this.windowStatus = windowStatus;
    }

    public String getBedInfo() {
        return bedInfo;
    }

    public void setBedInfo(String bedInfo) {
        this.bedInfo = bedInfo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRoomNote() {
        return roomNote;
    }

    public void setRoomNote(String roomNote) {
        this.roomNote = roomNote;
    }

    public String getServicePhone() {
        return servicePhone;
    }

    public void setServicePhone(String servicePhone) {
        this.servicePhone = servicePhone;
    }

    public String getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getBedType() {
        return bedType;
    }

    public void setBedType(int bedType) {
        this.bedType = bedType;
    }

    public String getBreakfastInfo() {
        return breakfastInfo;
    }

    public void setBreakfastInfo(String breakfastInfo) {
        this.breakfastInfo = breakfastInfo;
    }

    public String getRefundInfo() {
        return refundInfo;
    }

    public void setRefundInfo(String refundInfo) {
        this.refundInfo = refundInfo;
    }

    public String getRoomDetails() {
        return roomDetails;
    }

    public void setRoomDetails(String roomDetails) {
        this.roomDetails = roomDetails;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public List<String> getContainCosts() {
        return containCosts;
    }

    public void setContainCosts(List<String> containCosts) {
        this.containCosts = containCosts;
    }

    public String getGoodsBuyNotes() {
        return goodsBuyNotes;
    }

    public void setGoodsBuyNotes(String goodsBuyNotes) {
        this.goodsBuyNotes = goodsBuyNotes;
    }
}
