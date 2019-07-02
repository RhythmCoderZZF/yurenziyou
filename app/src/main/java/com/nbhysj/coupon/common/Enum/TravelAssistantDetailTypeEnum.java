package com.nbhysj.coupon.common.Enum;

/**
 * @auther：hysj created on 2019/06/16
 * description：行程助手详情类型
 */
public enum TravelAssistantDetailTypeEnum {

    MCH_SCENIC(0, "MCH_SCENIC"), MCH_FOOD(1, "MCH_FOOD"), MCH_HOTEL(2, "MCH_HOTEL"), MCH_RECREATION(3, "MCH_RECREATION"), TRAFFIC(4, "TRAFFIC"), CAR(5, "CAR"), TRAIN(6, "TRAIN"), NOTE(7, "NOTE");

    private final int key;
    private final String value;

    private TravelAssistantDetailTypeEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    //根据key获取枚举
    public static String getEnumByKey(int key) {
        for (TravelAssistantDetailTypeEnum temp : TravelAssistantDetailTypeEnum.values()) {
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