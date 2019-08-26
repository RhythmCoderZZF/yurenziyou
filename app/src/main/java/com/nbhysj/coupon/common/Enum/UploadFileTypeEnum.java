package com.nbhysj.coupon.common.Enum;

/**
 * @auther：hysj created on 2019/3/28
 * description：1.图片 2.音频 3.视频 4.动图
 */
public enum UploadFileTypeEnum {

    IMAGE(1, "images"), AUDIO(2, "audio"), VIDEO(3, "video"), GIF(4, "gif");

    private final int key;
    private final String value;

    private UploadFileTypeEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    //根据key获取枚举
    public static UploadFileTypeEnum getEnumByKey(int key) {
        for (UploadFileTypeEnum temp : UploadFileTypeEnum.values()) {
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
