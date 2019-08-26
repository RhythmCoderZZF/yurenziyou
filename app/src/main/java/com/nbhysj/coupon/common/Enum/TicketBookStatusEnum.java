package com.nbhysj.coupon.common.Enum;

/**
 * @auther：hysj created on 2019/8/23
 * description：0.不可预订 1.可预订
 */
public enum TicketBookStatusEnum {

    BOOKABLE(1, "bookable"), UN_BOOKABLE(0, "unbookable");

    private final int key;
    private final String value;

     TicketBookStatusEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    //根据key获取枚举
    public static TicketBookStatusEnum getEnumByKey(int key) {
        for (TicketBookStatusEnum temp : TicketBookStatusEnum.values()) {
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
