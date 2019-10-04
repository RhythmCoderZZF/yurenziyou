package com.nbhysj.coupon.common.Enum;

/**
 * @auther：hysj created on 2019/10/01
 * description: PAYMENT_TYPE_WECHAT:微信 PAYMENT_TYPE_ALIPAY:支付宝
 */
public enum PaymentTypeEnum {

    PAYMENT_TYPE_WECHAT(0, "WECHAT_PAY"), PAYMENT_TYPE_ALIPAY(1, "ALIPAY");

    private final int key;
    private final String value;

    private PaymentTypeEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    //根据key获取枚举
    public static String getEnumByKey(int key) {
        for (PaymentTypeEnum temp : PaymentTypeEnum.values()) {
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