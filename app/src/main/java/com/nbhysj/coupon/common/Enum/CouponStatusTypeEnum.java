package com.nbhysj.coupon.common.Enum;

/**
 * @auther：hysj created on 2019/10/31
 * description：优惠券领取类型
 */
public enum CouponStatusTypeEnum {

    //10未使用 11占用中 12已使用 13已过期 14已作废
    COUPON_NOT_USED(10, "去使用"),COUPON_OCCUPYING(11, "占用中"), COUPON_USED(12, "已使用"), COUPON_EXPIRED(13, "已过期"), COUPON_INVALID(14, "1已作废");

    private final int key;
    private final String value;

    CouponStatusTypeEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    //根据key获取枚举
    public static CouponStatusTypeEnum getEnumByKey(int key) {
        for (CouponStatusTypeEnum temp : CouponStatusTypeEnum.values()) {
            if (temp.getKey() == key)
            {
                return temp;
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
