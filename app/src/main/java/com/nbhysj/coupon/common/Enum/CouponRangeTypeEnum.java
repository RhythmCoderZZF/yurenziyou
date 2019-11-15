package com.nbhysj.coupon.common.Enum;

/**
 * @auther：hysj created on 2019/10/31
 * description：优惠券领取类型
 */
public enum CouponRangeTypeEnum {

    MCH_SCENIC(0, "MCH_SCENIC"), MCH_HOTEL(1, "MCH_HOTEL"), MCH_RECREATION(2, "MCH_RECREATION"),CAR(3, "CAR"),  MCH_HOTEL1(4, "MCH_HOTEL1"), MCH_HOTEL2(5, "MCH_HOTEL2"),ALL(6,"ALL"),MCH_GOODS(7,"MCH_GOODS");

    private final int key;
    private final String value;

     CouponRangeTypeEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    //根据key获取枚举
    public static String getEnumByKey(int key) {
        for (CouponRangeTypeEnum temp : CouponRangeTypeEnum.values()) {
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
