package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/9/18
 * description：酒店下单
 */
public class HotelOrderInitResponse {

    //离店时间
    private String leaveTime;

    //入住时间
    private String intoTime;

    private RefundRulesEntity refundRules;

    //金额
    private double price;

    private int openCarStatus;

    //商户名
    private String mchName;

    private List<String> sign;

    private GoodsEntity goods;

    private int refundRate;

    private String invoice;

    private String intoAndLeaveDesc;

    public GoodsEntity getGoods() {
        return goods;
    }

    public void setGoods(GoodsEntity goods) {
        this.goods = goods;
    }

    public String getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(String leaveTime) {
        this.leaveTime = leaveTime;
    }

    public String getIntoTime() {
        return intoTime;
    }

    public void setIntoTime(String intoTime) {
        this.intoTime = intoTime;
    }

    public RefundRulesEntity getRefundRules() {
        return refundRules;
    }

    public void setRefundRules(RefundRulesEntity refundRules) {
        this.refundRules = refundRules;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getOpenCarStatus() {
        return openCarStatus;
    }

    public void setOpenCarStatus(int openCarStatus) {
        this.openCarStatus = openCarStatus;
    }

    public String getMchName() {
        return mchName;
    }

    public void setMchName(String mchName) {
        this.mchName = mchName;
    }

    public List<String> getSign() {
        return sign;
    }

    public void setSign(List<String> sign) {
        this.sign = sign;
    }

    public int getRefundRate() {
        return refundRate;
    }

    public void setRefundRate(int refundRate) {
        this.refundRate = refundRate;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getIntoAndLeaveDesc() {
        return intoAndLeaveDesc;
    }

    public void setIntoAndLeaveDesc(String intoAndLeaveDesc) {
        this.intoAndLeaveDesc = intoAndLeaveDesc;
    }

    public class RefundRulesEntity {

        private String refundTime;

        //退款百分比
        private int refundPercentage;

        public String getRefundTime() {
            return refundTime;
        }

        public void setRefundTime(String refundTime) {
            this.refundTime = refundTime;
        }

        public int getRefundPercentage() {
            return refundPercentage;
        }

        public void setRefundPercentage(int refundPercentage) {
            this.refundPercentage = refundPercentage;
        }


    }

    public class GoodsEntity {

        private int id;

        private String acreage;

        private int breakfastStatus;

        private int toiletStatus;

        private int wifiStatus;

        private int windowStatus;

        private String title;

        private String roomType;

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getRoomType() {
            return roomType;
        }

        public void setRoomType(String roomType) {
            this.roomType = roomType;
        }
    }
}
