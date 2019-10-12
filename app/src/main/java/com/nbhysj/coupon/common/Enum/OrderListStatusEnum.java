package com.nbhysj.coupon.common.Enum;

/**
 * @auther：hysj created on 2019/10/01
 * description: 订单列表状态 0:交易成功 1:待付款 2:交易关闭
 */
public enum OrderListStatusEnum {

    ORDER_SUCCESSFUL_TRADE(0, "交易成功"), ORDER_PENDING_PAYMENT(1, "待付款"), ORDER_PAYMENT_CLOSE(2, "交易关闭");

    private final int key;
    private final String value;

    private OrderListStatusEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    //根据key获取枚举
    public static String getEnumByKey(int key) {
        for (OrderListStatusEnum temp : OrderListStatusEnum.values()) {
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