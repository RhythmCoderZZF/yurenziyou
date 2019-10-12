package com.nbhysj.coupon.common.Enum;

/**
 * @auther：hysj created on 2019/9/5
 * description：订单类型
 */
public enum GoodsTypeEnum {

    //0.景区门票 1.专车 2.酒店客房 3.互动项目 4.组合商品 5.菜品
    GOODS_TICKET(0, "GOODS_TICKET"), GOODS_CAR(1, "GOODS_CAR"),  GOODS_HOTEL_ROOM(2, "GOODS_HOTEL_ROOM"), GOODS_RECREATION(3, "GOODS_RECREATION"),GROUP_GOODS(4, "GROUP_GOODS"),ITEM_FOOD(5, "ITEM_FOOD"), MCH_HOTEL1(9, "MCH_HOTEL1"), MCH_HOTEL2(10, "MCH_HOTEL2");

    private final int key;
    private final String value;

    GoodsTypeEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    //根据key获取枚举
    public static GoodsTypeEnum getEnumByKey(int key) {
        for (GoodsTypeEnum temp : GoodsTypeEnum.values()) {
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
