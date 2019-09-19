package com.nbhysj.coupon.common.Enum;

/**
 * @auther：hysj created on 2019/06/16
 * description：行程助手详情类型（民宿类型附加）
 *  MCH_HOTEL(酒店民宿类型)  MCH_HOTEL1:酒店 MCH_HOTEL2:民宿
 */
public enum MchTypeEnum {

    MCH_SCENIC(0, "MCH_SCENIC"), MCH_FOOD(1, "MCH_FOOD"), MCH_HOTEL(2, "MCH_HOTEL"), MCH_RECREATION(3, "MCH_RECREATION"), TRAFFIC(4, "TRAFFIC"), CAR(5, "CAR"), TRAIN(6, "TRAIN"), NOTE(7, "NOTE"), MCH_HOMESTAY(8, "MCH_HOMESTAY"), MCH_HOTEL1(9, "MCH_HOTEL1"), MCH_HOTEL2(10, "MCH_HOTEL2");

    private final int key;
    private final String value;

    private MchTypeEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    //根据key获取枚举
    public static String getEnumByKey(int key) {
        for (MchTypeEnum temp : MchTypeEnum.values()) {
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