package com.nbhysj.coupon.model.response;

import java.io.Serializable;
import java.util.List;

/**
 * @auther：hysj created on 2019/12/1
 * description：美食评价初始化
 */
public class MchFoodCommentBean {

    private int orderGoodsId;

    private int mchId;

    private String mchName;

    private String mchType;

    private List<OrderCommentMchTagBean> mchTag;

    public int getOrderGoodsId() {
        return orderGoodsId;
    }

    public void setOrderGoodsId(int orderGoodsId) {
        this.orderGoodsId = orderGoodsId;
    }

    public int getMchId() {
        return mchId;
    }

    public void setMchId(int mchId) {
        this.mchId = mchId;
    }

    public String getMchName() {
        return mchName;
    }

    public void setMchName(String mchName) {
        this.mchName = mchName;
    }

    public String getMchType() {
        return mchType;
    }

    public void setMchType(String mchType) {
        this.mchType = mchType;
    }

    public List<OrderCommentMchTagBean> getMchTag() {
        return mchTag;
    }

    public void setMchTag(List<OrderCommentMchTagBean> mchTag) {
        this.mchTag = mchTag;
    }

    public class TagsEntity implements Serializable {

        private int id;

        private int mchId;

        private String title;

        private String value;

        private String ctime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMchId() {
            return mchId;
        }

        public void setMchId(int mchId) {
            this.mchId = mchId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }
    }
}
