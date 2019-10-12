package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2019/10/9
 * description：退款详情
 */
public class OrderRefundDetailResponse {

    //退款订单好
    private String orderRefundNo;

    //退款状态
    private int refundStatus;

    private String refundDesc;

    private String paymentFlag;

    //退款流水号
    private String transactionNo;

    private double refundFee;

    private String accountName;

    private String refundTime;

    private String laterTime;

    private String phone;

    private int needDays;

    private String ctime;

    public String getOrderRefundNo() {
        return orderRefundNo;
    }

    public void setOrderRefundNo(String orderRefundNo) {
        this.orderRefundNo = orderRefundNo;
    }

    public int getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(int refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getRefundDesc() {
        return refundDesc;
    }

    public void setRefundDesc(String refundDesc) {
        this.refundDesc = refundDesc;
    }

    public String getPaymentFlag() {
        return paymentFlag;
    }

    public void setPaymentFlag(String paymentFlag) {
        this.paymentFlag = paymentFlag;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public double getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(double refundFee) {
        this.refundFee = refundFee;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(String refundTime) {
        this.refundTime = refundTime;
    }

    public String getLaterTime() {
        return laterTime;
    }

    public void setLaterTime(String laterTime) {
        this.laterTime = laterTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getNeedDays() {
        return needDays;
    }

    public void setNeedDays(int needDays) {
        this.needDays = needDays;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }
}
