package com.nbhysj.coupon.common.Enum;

/**
 * @auther：hysj created on 2019/3/19
 * description：1.头像 2.帖子
 */
public enum UploadTypeEnum {

    AVATAR("1", "头像"), NOTE("2", "帖子");

    private final String key;
    private final String value;

    private UploadTypeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    //根据key获取枚举
    public static UploadTypeEnum getEnumByKey(String key) {
        for (UploadTypeEnum temp : UploadTypeEnum.values()) {
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
