package com.nbhysj.coupon.common.Enum;

/**
 * @auther：hysj created on 2019/4/1
 * description：1.城市 2.主题
 */
public enum HotTagsTypeEnum {

    CITY(1, "image"), TOPIC(2, "topic");

    private final int key;
    private final String value;

    private HotTagsTypeEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    //根据key获取枚举
    public static HotTagsTypeEnum getEnumByKey(int key) {
        for (HotTagsTypeEnum temp : HotTagsTypeEnum.values()) {
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
