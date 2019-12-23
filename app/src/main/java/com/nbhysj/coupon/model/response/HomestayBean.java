package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2019/12/23
 * description：
 */
public class HomestayBean {

    private int id;

    private String mchType;

    private String mchType2;

    private int mchId;

    private String title;

    private int intoNum;

    private String acreage;

    private String bedType;

    private int bedNum;

    private String photo;

    private double marketPrice;

    private double defaultPrice;

    private String roomType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getIntoNum() {
        return intoNum;
    }

    public void setIntoNum(int intoNum) {
        this.intoNum = intoNum;
    }

    public String getAcreage() {
        return acreage;
    }

    public void setAcreage(String acreage) {
        this.acreage = acreage;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public int getBedNum() {
        return bedNum;
    }

    public void setBedNum(int bedNum) {
        this.bedNum = bedNum;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public void setDefaultPrice(double defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
}
