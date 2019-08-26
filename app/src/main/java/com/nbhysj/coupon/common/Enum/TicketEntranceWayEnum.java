package com.nbhysj.coupon.common.Enum;

/**
 * @auther：hysj created on 2019/8/23
 * description：0.无需换票,直接验证入园 1.换票入园
 */
public enum TicketEntranceWayEnum {

    TICKET_CHANGE_NO("TICKET_CHANGE_NO", "无需换票"), TICKET_CHANGE("TICKET_CHANGE", "换票入园");

    private final String key;
    private final String value;

    TicketEntranceWayEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    //根据key获取枚举
    public static String getEnumValueByKey(String key) {
        for (TicketEntranceWayEnum temp : TicketEntranceWayEnum.values()) {
            if (temp.key.equals(key)) {
                return temp.value;
            }
        }
        return null;
    }


}
