package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2019/12/23
 * description：房东信息
 */
public class LandlordBean {

    private int id;

    private int supplierId;

    private String nickname;

    private String avatar;

    private String motto;

    private int authenticationStatus;

    private int bookingSuccess;

    private String content;

    private int confirmTime;

    private int homestayRoomNum;

    private int score;

    private int commentCount;

    private long ctime;

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public int getAuthenticationStatus() {
        return authenticationStatus;
    }

    public void setAuthenticationStatus(int authenticationStatus) {
        this.authenticationStatus = authenticationStatus;
    }

    public int getBookingSuccess() {
        return bookingSuccess;
    }

    public void setBookingSuccess(int bookingSuccess) {
        this.bookingSuccess = bookingSuccess;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(int confirmTime) {
        this.confirmTime = confirmTime;
    }

    public int getHomestayRoomNum() {
        return homestayRoomNum;
    }

    public void setHomestayRoomNum(int homestayRoomNum) {
        this.homestayRoomNum = homestayRoomNum;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }
}
