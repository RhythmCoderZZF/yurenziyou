package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/9/3
 * description：用户订单
 */
public class UserOrderListResponse {

    private List<OrderTypeEntity> result;

    private BasePaginationResult page;

    public List<OrderTypeEntity> getResult() {
        return result;
    }

    public void setResult(List<OrderTypeEntity> result) {
        this.result = result;
    }

    public BasePaginationResult getPage() {
        return page;
    }

    public void setPage(BasePaginationResult page) {
        this.page = page;
    }

    public class OrderTypeEntity {

        private String orderType;

        private String orderStatus;

        private String orderNo;

        private String orderRefundNo;

        private List<MchsEntity> mchs;

        private long outTime;

        private int goodsNum;

        private double totalPrice;

        private int canRefundStatus;

        private BuyAginEntity buyAginVO;

        private StatusEntity statusVO;

        //支付过期时间
        private long payExprireTime;

        private List<OrderListCarEntity> orderListCarVO;

        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getOrderRefundNo() {
            return orderRefundNo;
        }

        public void setOrderRefundNo(String orderRefundNo) {
            this.orderRefundNo = orderRefundNo;
        }

        public List<MchsEntity> getMchs() {
            return mchs;
        }

        public void setMchs(List<MchsEntity> mchs) {
            this.mchs = mchs;
        }

        public long getOutTime() {
            return outTime;
        }

        public void setOutTime(long outTime) {
            this.outTime = outTime;
        }

        public int getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(int goodsNum) {
            this.goodsNum = goodsNum;
        }

        public double getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
        }

        public int getCanRefundStatus() {
            return canRefundStatus;
        }

        public void setCanRefundStatus(int canRefundStatus) {
            this.canRefundStatus = canRefundStatus;
        }

        public BuyAginEntity getBuyAginVO() {
            return buyAginVO;
        }

        public void setBuyAginVO(BuyAginEntity buyAginVO) {
            this.buyAginVO = buyAginVO;
        }

        public StatusEntity getStatusVO() {
            return statusVO;
        }

        public void setStatusVO(StatusEntity statusVO) {
            this.statusVO = statusVO;
        }

        public List<OrderListCarEntity> getOrderListCarVO() {
            return orderListCarVO;
        }

        public void setOrderListCarVO(List<OrderListCarEntity> orderListCarVO) {
            this.orderListCarVO = orderListCarVO;
        }


        public long getPayExprireTime() {
            return payExprireTime;
        }

        public void setPayExprireTime(long payExprireTime) {
            this.payExprireTime = payExprireTime;
        }
    }

    public class MchsEntity {

        private int mchId;

        private String photo;

        private String address;

        private String mchName;

        private int commentStatus;

        private List<GoodsEntity> goodsVOList;

        public int getMchId() {
            return mchId;
        }

        public void setMchId(int mchId) {
            this.mchId = mchId;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getMchName() {
            return mchName;
        }

        public void setMchName(String mchName) {
            this.mchName = mchName;
        }

        public int getCommentStatus() {
            return commentStatus;
        }

        public void setCommentStatus(int commentStatus) {
            this.commentStatus = commentStatus;
        }

        public List<GoodsEntity> getGoodsVOList() {
            return goodsVOList;
        }

        public void setGoodsVOList(List<GoodsEntity> goodsVOList) {
            this.goodsVOList = goodsVOList;
        }
    }

    public class GoodsEntity {

        private int goodsId;

        private String goodsType;

        private String goodsTitle;

        private int goodsNum;

        private double goodsPrice;

        private String goodsTime;

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public String getGoodsType() {
            return goodsType;
        }

        public void setGoodsType(String goodsType) {
            this.goodsType = goodsType;
        }

        public String getGoodsTitle() {
            return goodsTitle;
        }

        public void setGoodsTitle(String goodsTitle) {
            this.goodsTitle = goodsTitle;
        }

        public int getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(int goodsNum) {
            this.goodsNum = goodsNum;
        }

        public double getGoodsPrice() {
            return goodsPrice;
        }

        public void setGoodsPrice(double goodsPrice) {
            this.goodsPrice = goodsPrice;
        }

        public String getGoodsTime() {
            return goodsTime;
        }

        public void setGoodsTime(String goodsTime) {
            this.goodsTime = goodsTime;
        }
    }

    public class BuyAginEntity {

        private String buyType;

        private int buyId;

        public String getBuyType() {
            return buyType;
        }

        public void setBuyType(String buyType) {
            this.buyType = buyType;
        }

        public int getBuyId() {
            return buyId;
        }

        public void setBuyId(int buyId) {
            this.buyId = buyId;
        }
    }

    public class StatusEntity {

        //可支付状态
        private int canPayStatus;

        //可取消状态
        private int canCancelStatus;

        //可删除状态
        private int canDelStatus;

        //可再次状态
        private int canBuyAgainStatus;

        //评论状态
        private int commentStatus;

        //用车状态
        private int useCarStatus;

        public int getCanPayStatus() {
            return canPayStatus;
        }

        public void setCanPayStatus(int canPayStatus) {
            this.canPayStatus = canPayStatus;
        }

        public int getCanCancelStatus() {
            return canCancelStatus;
        }

        public void setCanCancelStatus(int canCancelStatus) {
            this.canCancelStatus = canCancelStatus;
        }

        public int getCanDelStatus() {
            return canDelStatus;
        }

        public void setCanDelStatus(int canDelStatus) {
            this.canDelStatus = canDelStatus;
        }

        public int getCanBuyAgainStatus() {
            return canBuyAgainStatus;
        }

        public void setCanBuyAgainStatus(int canBuyAgainStatus) {
            this.canBuyAgainStatus = canBuyAgainStatus;
        }

        public int getCommentStatus() {
            return commentStatus;
        }

        public void setCommentStatus(int commentStatus) {
            this.commentStatus = commentStatus;
        }

        public int getUseCarStatus() {
            return useCarStatus;
        }

        public void setUseCarStatus(int useCarStatus) {
            this.useCarStatus = useCarStatus;
        }
    }

    public class OrderListCarEntity{

        private String useCarTime;

        private String startName;

        private String endName;

        private double carPrice;

        public String getUseCarTime() {
            return useCarTime;
        }

        public void setUseCarTime(String useCarTime) {
            this.useCarTime = useCarTime;
        }

        public String getStartName() {
            return startName;
        }

        public void setStartName(String startName) {
            this.startName = startName;
        }

        public String getEndName() {
            return endName;
        }

        public void setEndName(String endName) {
            this.endName = endName;
        }

        public double getCarPrice() {
            return carPrice;
        }

        public void setCarPrice(double carPrice) {
            this.carPrice = carPrice;
        }
    }



}
