package com.nbhysj.coupon.common.Enum;

/**
 * @auther：hysj created on 2019/3/19
 * description：1.qq 2.微信 3.新浪微博
 */
public enum ThirdPartyLoginTypeEnum {

    QQ(0, "QQ"), WECHAT(1, "WECHAT"), WEIBO(2, "WEIBO");

    private final int key;
    private final String value;

    private ThirdPartyLoginTypeEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    //根据key获取枚举
    public static ThirdPartyLoginTypeEnum getEnumByKey(int key) {
        for (ThirdPartyLoginTypeEnum temp : ThirdPartyLoginTypeEnum.values()) {
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
