package com.nbhysj.coupon.common.Enum;

/**
 * @auther：hysj created on 2019/10/31
 * description：优惠券领取类型
 */
public enum CouponReceivedTypeEnum {

    //0.已领取 1.已使用 2.已过期
    ALREADYRECEIVED("已领取", "ALREADYRECEIVED"), USED("已使用", "USED"), EXPIRED("已过期", "EXPIRED");

    private final String key;
    private final String value;

    CouponReceivedTypeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    //根据key获取枚举
    public static CouponReceivedTypeEnum getEnumByKey(int key) {
        for (CouponReceivedTypeEnum temp : CouponReceivedTypeEnum.values()) {
            if (temp.getKey().equals(key)) {
                return temp;
            }
        }
        return null;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
