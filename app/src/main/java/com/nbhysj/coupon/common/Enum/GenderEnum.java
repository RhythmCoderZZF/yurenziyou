package com.nbhysj.coupon.common.Enum;

/**
 * @auther：hysj created on 2019/3/17
 * description：性别
 */
public enum GenderEnum {

    UNkNOWN(0, "未知"), MALE(1, "男"), FEMALE(2, "女"), SECRECY(3, "保密");

    private final int key;
    private final String value;

    private GenderEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    //根据key获取枚举
    public static GenderEnum getEnumByKey(int key) {
        for (GenderEnum temp : GenderEnum.values()) {
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