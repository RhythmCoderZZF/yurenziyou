package com.nbhysj.coupon.common.Enum;

/**
 * @auther：hysj created on 2019/8/23
 * description：1.FJ(附近)  2.CZ(评分)  3.ZH(销量)  4.""
 */
public enum MchSearchConditionEnum {

    FJ(0, "FJ"),CZ(1, "CZ"),ZH(2, "ZH"),JL(3, "JL");

    private final int key;
    private final String value;

     MchSearchConditionEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    //根据key获取枚举
    public static MchSearchConditionEnum getEnumByKey(int key) {
        for (MchSearchConditionEnum temp : MchSearchConditionEnum.values()) {
            if (temp.getKey() == key) {
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
