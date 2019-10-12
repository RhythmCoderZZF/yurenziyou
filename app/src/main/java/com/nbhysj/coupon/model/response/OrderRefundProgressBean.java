package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2019/10/10
 * description：订单退款进程
 */
public class OrderRefundProgressBean {

    private String refundProgressDesc;

    private String refundProgressTime;

    public String getRefundProgressDesc() {
        return refundProgressDesc;
    }

    public void setRefundProgressDesc(String refundProgressDesc) {
        this.refundProgressDesc = refundProgressDesc;
    }

    public String getRefundProgressTime() {
        return refundProgressTime;
    }

    public void setRefundProgressTime(String refundProgressTime) {
        this.refundProgressTime = refundProgressTime;
    }
}
