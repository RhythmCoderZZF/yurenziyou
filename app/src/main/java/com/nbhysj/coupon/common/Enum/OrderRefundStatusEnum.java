package com.nbhysj.coupon.common.Enum;

/**
 * @auther：hysj created on 2019/9/27
 * description：订单退款状态
 */
public enum OrderRefundStatusEnum {

    //0.退款中 1.专车 2.酒店客房 3.互动项目 4.组合商品 5.菜品
    ORDER_REFUNDING(0, "退款中"), ORDER_REFUND_SUCCESSFUL(1, "退款成功"),  ORDER_REFUND_CLOSED(2, "退款关闭");

    private final int key;
    private final String value;

    OrderRefundStatusEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    //根据key获取枚举
    public static OrderRefundStatusEnum getEnumByKey(int key) {
        for (OrderRefundStatusEnum temp : OrderRefundStatusEnum.values()) {
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
