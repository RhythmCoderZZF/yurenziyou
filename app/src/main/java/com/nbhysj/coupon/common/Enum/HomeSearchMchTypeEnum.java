package com.nbhysj.coupon.common.Enum;

/**
 * @auther：hysj created on 2019/10/21
 * description：首页搜索商户类型
 *
 */
public enum HomeSearchMchTypeEnum {

    MCH_SCENIC(0, "MCH_SCENIC"), MCH_FOOD(1, "MCH_FOOD"), MCH_RECREATION(3, "MCH_RECREATION"), MCH_HOTEL(2, "MCH_HOTEL"), MCH_HOTEL1(9, "MCH_HOTEL1"), MCH_HOTEL2(10, "MCH_HOTEL2"), MCH_CITY(4, "MCH_CITY"), STRATEGY(5, "STRATEGY"), POST(6, "POST"),GROUP(11, "GROUP"), ALL(7, "ALL");

    private final int key;
    private final String value;

    private HomeSearchMchTypeEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    //根据key获取枚举
    public static String getEnumByKey(int key) {
        for (HomeSearchMchTypeEnum temp : HomeSearchMchTypeEnum.values()) {
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