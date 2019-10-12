package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2019/9/26
 * description：部分订单退款初始化页面对象
 */
public class OrderRefundInitResponse {

    //订单号
    private String orderNo;

    //子订单id
    private int orderGoodsId;

    private String title;

    private String goodsTime;

    private String photo;

    //使用数
    private int usedNum;

    //可退数
    private int validNum;

    //商品总数
    private int sumNum;

    //单价
    private String price;

    //扣款额
    private String deductPrice;

    //扣款原因
    private String deductNote;

    private int refundPercentage;

    //起始地
    private String startName;

    //截止地
    private String endName;

    //商品类型
    private String goodsType;

    //总金额
    private String totalPrice;

    public int getSumNum() {
        return sumNum;
    }

    public void setSumNum(int sumNum) {
        this.sumNum = sumNum;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getOrderGoodsId() {
        return orderGoodsId;
    }

    public void setOrderGoodsId(int orderGoodsId) {
        this.orderGoodsId = orderGoodsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGoodsTime() {
        return goodsTime;
    }

    public void setGoodsTime(String goodsTime) {
        this.goodsTime = goodsTime;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getUsedNum() {
        return usedNum;
    }

    public void setUsedNum(int usedNum) {
        this.usedNum = usedNum;
    }

    public int getValidNum() {
        return validNum;
    }

    public void setValidNum(int validNum) {
        this.validNum = validNum;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDeductPrice() {
        return deductPrice;
    }

    public void setDeductPrice(String deductPrice) {
        this.deductPrice = deductPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDeductNote() {
        return deductNote;
    }

    public void setDeductNote(String deductNote) {
        this.deductNote = deductNote;
    }

    public int getRefundPercentage() {
        return refundPercentage;
    }

    public void setRefundPercentage(int refundPercentage) {
        this.refundPercentage = refundPercentage;
    }

    public String getStartName() {
        return startName;
    }

    public void setStartName(String startName) {
        this.startName = startName;
    }

    public String getEndName() {
        return endName;
    }

    public void setEndName(String endName) {
        this.endName = endName;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getTotalPrice() {
        return totalPrice;
    }
}
