package com.nbhysj.coupon.common.Enum;

/**
 * @auther：hysj created on 2019/3/17
 * description：
 */
public enum SharePlatformEnum {

    WECHAT_FRIEND(0, "WEIXIN"), WECHAT_FRIENDSHIP_CIRCLE(1, "WEIXIN_CIRCLE"), QQ(2, "QQ"), SECRECY(3, "QZONE");

    private final int key;
    private final String value;

    private SharePlatformEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    //根据key获取枚举
    public static String getEnumByKey(int key) {
        for (SharePlatformEnum temp : SharePlatformEnum.values()) {
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