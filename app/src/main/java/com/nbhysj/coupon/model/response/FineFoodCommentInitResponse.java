package com.nbhysj.coupon.model.response;
import java.util.List;

/**
 * @auther：hysj created on 2019/12/1
 * description：美食评价初始化接口
 */
public class FineFoodCommentInitResponse {

    private String orderNo;

    private String goodsType;

    private List<MchFoodCommentBean> mch;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public List<MchFoodCommentBean> getMch() {
        return mch;
    }

    public void setMch(List<MchFoodCommentBean> mch) {
        this.mch = mch;
    }
}
