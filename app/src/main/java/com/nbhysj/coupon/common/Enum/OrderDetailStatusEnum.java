package com.nbhysj.coupon.common.Enum;

/**
 * @auther：hysj created on 2019/10/01
 * description: 订单详情状态
 */
public enum OrderDetailStatusEnum {

    ORDER_SUCCESSFUL_TRADE(0, "交易成功"), ORDER_PENDING_PAYMENT(1, "交易待支付"), ORDER_PAYMENT_CANCEL(2, "交易取消"), ORDER_PAYMENT_TIMEOUT(3, "交易超时"), ORDER_ERROR(4, "错误订单");

    private final int key;
    private final String value;

    private OrderDetailStatusEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    //根据key获取枚举
    public static String getEnumByKey(int key) {
        for (OrderDetailStatusEnum temp : OrderDetailStatusEnum.values()) {
            if (temp.getKey() == key) {
                return temp.value;
            }
        }
        return null;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}