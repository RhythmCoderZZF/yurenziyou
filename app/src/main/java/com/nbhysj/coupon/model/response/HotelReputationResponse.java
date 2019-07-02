package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2019/4/19
 * description：酒店民宿推荐
 */
public class HotelReputationResponse {

    //酒店名字
    private String hotelName;

    //酒店评分
    private String hotelRating;

    //酒店描述
    private String hotelDes;

    //酒店星级
    private int hotelStar;

    //酒店星级
    private String hotelType;

    //酒店位置
    private String hotelLocation;

    //点评数
    private int commentNumber;

    //酒店民宿照片
    private String hotelPhoto;

    //酒店民宿人均价格
    private int perCapitaPrice;

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelRating() {
        return hotelRating;
    }

    public void setHotelRating(String hotelRating) {
        this.hotelRating = hotelRating;
    }

    public String getHotelDes() {
        return hotelDes;
    }

    public void setHotelDes(String hotelDes) {
        this.hotelDes = hotelDes;
    }

    public int getHotelStar() {
        return hotelStar;
    }

    public void setHotelStar(int hotelStar) {
        this.hotelStar = hotelStar;
    }

    public String getHotelType() {
        return hotelType;
    }

    public void setHotelType(String hotelType) {
        this.hotelType = hotelType;
    }

    public String getHotelLocation() {
        return hotelLocation;
    }

    public void setHotelLocation(String hotelLocation) {
        this.hotelLocation = hotelLocation;
    }

    public int getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(int commentNumber) {
        this.commentNumber = commentNumber;
    }

    public String getHotelPhoto() {
        return hotelPhoto;
    }

    public void setHotelPhoto(String hotelPhoto) {
        this.hotelPhoto = hotelPhoto;
    }

    public int getPerCapitaPrice() {
        return perCapitaPrice;
    }

    public void setPerCapitaPrice(int perCapitaPrice) {
        this.perCapitaPrice = perCapitaPrice;
    }
}
