package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2019/10/31
 * description：优惠券
 */
public class CouponsBean {

    private int id;

    private int auto;

    private int couponExclusive;

    private int del;

    private long endTime;

    private int showStatus;

    private long startTime;

    private int status;

    private int weight;

    private String couponRange;

    private String couponType;

    private int userMaxNum;

    private String intro;

    private String photo;

    private String title;

    private int getStatus;

    private double fullFee;

    private double discountFee;

    private long utime;

    private long ctime;

    //优惠券列表
    private int couponId;

    private int userCouponId;

    private int couponStatus;

    private long usedStatus;

    private int userId;

    private String couponCode;

    //0不能领 1可以领
    private int canGetAgainStatus;

    //可使用状态
    private int canUseStatus;

    private int canUseNum;

    //优惠券选择
    private boolean isCouponSelect;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuto() {
        return auto;
    }

    public void setAuto(int auto) {
        this.auto = auto;
    }

    public int getCouponExclusive() {
        return couponExclusive;
    }

    public void setCouponExclusive(int couponExclusive) {
        this.couponExclusive = couponExclusive;
    }

    public int getDel() {
        return del;
    }

    public void setDel(int del) {
        this.del = del;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(int showStatus) {
        this.showStatus = showStatus;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getCouponRange() {
        return couponRange;
    }

    public void setCouponRange(String couponRange) {
        this.couponRange = couponRange;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public int getUserMaxNum() {
        return userMaxNum;
    }

    public void setUserMaxNum(int userMaxNum) {
        this.userMaxNum = userMaxNum;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
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

    public int getGetStatus() {
        return getStatus;
    }

    public void setGetStatus(int getStatus) {
        this.getStatus = getStatus;
    }

    public double getFullFee() {
        return fullFee;
    }

    public void setFullFee(double fullFee) {
        this.fullFee = fullFee;
    }

    public double getDiscountFee() {
        return discountFee;
    }

    public void setDiscountFee(double discountFee) {
        this.discountFee = discountFee;
    }

    public long getUtime() {
        return utime;
    }

    public void setUtime(long utime) {
        this.utime = utime;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public int getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(int couponStatus) {
        this.couponStatus = couponStatus;
    }

    public long getUsedStatus() {
        return usedStatus;
    }

    public void setUsedStatus(long usedStatus) {
        this.usedStatus = usedStatus;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public int getUserCouponId() {
        return userCouponId;
    }

    public void setUserCouponId(int userCouponId) {
        this.userCouponId = userCouponId;
    }

    public int getCanGetAgainStatus() {
        return canGetAgainStatus;
    }

    public void setCanGetAgainStatus(int canGetAgainStatus) {
        this.canGetAgainStatus = canGetAgainStatus;
    }

    public int getCanUseStatus() {
        return canUseStatus;
    }

    public void setCanUseStatus(int canUseStatus) {
        this.canUseStatus = canUseStatus;
    }

    public int getCanUseNum() {
        return canUseNum;
    }

    public void setCanUseNum(int canUseNum) {
        this.canUseNum = canUseNum;
    }

    public boolean isCouponSelect() {
        return isCouponSelect;
    }

    public void setCouponSelect(boolean couponSelect) {
        isCouponSelect = couponSelect;
    }
}
