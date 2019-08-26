package com.nbhysj.coupon.common.Enum;

/**
 * @auther：hysj created on 2019/8/23
 * description：(退款模式)0.随时退 1.有条件退 2.不可退款
 */
public enum TicketRefundSettingsEnum
{
    REFUND_ANYTIME("REFUND_ANYTIME","随时退"), REFUND_CONDITIONAL("REFUND_CONDITIONAL","有条件退"), REFUND_REFUSE("REFUND_REFUSE","不可退款");

    private final String key;
    private final String value;

     TicketRefundSettingsEnum(String key, String value)
     {
        this.key = key;
        this.value = value;
     }

    //根据key获取枚举
    public static String getEnumValueByKey(String key) {
        for (TicketRefundSettingsEnum temp : TicketRefundSettingsEnum.values()) {
            if (temp.getKey().equals(key)) {
                return temp.value;
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
