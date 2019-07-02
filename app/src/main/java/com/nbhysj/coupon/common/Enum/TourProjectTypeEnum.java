package com.nbhysj.coupon.common.Enum;

/**
 * @auther：hysj created on 2019/06/13
 * description：游玩项目类型
 */
public enum TourProjectTypeEnum {

    SCENERY(0, "SCENERY"), SHOW(1, "SHOW"), PLAY(2, "PLAY");

    private final int key;
    private final String value;

    private TourProjectTypeEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    //根据key获取枚举
    public static String getEnumByKey(int key) {
        for (TourProjectTypeEnum temp : TourProjectTypeEnum.values()) {
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