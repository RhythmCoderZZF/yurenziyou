package com.nbhysj.coupon.common.Enum;

/**
 * @auther：hysj created on 2019/10/20
 * description：我的收藏类型
 */
public enum MineCollectionTypeEnum {

    //帖子类型 1:帖子 2:攻略 3:商户 4:商品
    POSTS("POSTS", "分享"),STRATEGY("STRATEGY", "攻略"),MCH("MCH", "商户"),GOODS("GOODS", "商品");

    private final String key;
    private final String value;

    MineCollectionTypeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    //根据key获取枚举
    public static MineCollectionTypeEnum getEnumByKey(String key) {
        for (MineCollectionTypeEnum temp : MineCollectionTypeEnum.values()) {
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
