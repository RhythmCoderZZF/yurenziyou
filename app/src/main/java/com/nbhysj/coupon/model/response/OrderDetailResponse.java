package com.nbhysj.coupon.model.response;

import java.io.Serializable;
import java.util.List;

/**
 * @auther：hysj created on 2019/9/6
 * description：订单详情
 */
public class OrderDetailResponse implements Serializable{

    private String title;

    private String orderStatus;

    private String goodsTime;

    private String validSum;

    //(单)商家(组合)客服电话
    private String tel;

    private List<AnswerEntity> notAnswer;

    private List<AnswerEntity> Answer;

    private OrderUserEntity orderUser;

    private OrderInvoiceEntity orderInvoice;

    //猜你喜欢
    private List<OrderDetailGuessBean> guess;

    private OrderEntity order;

    //用车订单
    private List<OrderCarEntity> orderCar;

    private String moneySum;

    private int goodSum;

    private List<OrderGoodsEntity> orderGoods;

    //0//全部退款 0不可退 1可退
    private int canRefundAllStatus;

    //commentStatus 1可评论 0不可评论
    private int commentStatus;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getGoodsTime() {
        return goodsTime;
    }

    public void setGoodsTime(String goodsTime) {
        this.goodsTime = goodsTime;
    }

    public String getValidSum() {
        return validSum;
    }

    public void setValidSum(String validSum) {
        this.validSum = validSum;
    }

    public List<AnswerEntity> getNotAnswer() {
        return notAnswer;
    }

    public void setNotAnswer(List<AnswerEntity> notAnswer) {
        this.notAnswer = notAnswer;
    }

    public List<AnswerEntity> getAnswer() {
        return Answer;
    }

    public void setAnswer(List<AnswerEntity> answer) {
        Answer = answer;
    }

    public OrderUserEntity getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(OrderUserEntity orderUser) {
        this.orderUser = orderUser;
    }

    public OrderInvoiceEntity getOrderInvoice() {
        return orderInvoice;
    }

    public void setOrderInvoice(OrderInvoiceEntity orderInvoice) {
        this.orderInvoice = orderInvoice;
    }

    public List<OrderDetailGuessBean> getGuess() {
        return guess;
    }

    public void setGuess(List<OrderDetailGuessBean> guess) {
        this.guess = guess;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public List<OrderCarEntity> getOrderCar() {
        return orderCar;
    }

    public void setOrderCar(List<OrderCarEntity> orderCar) {
        this.orderCar = orderCar;
    }

    public String getMoneySum() {
        return moneySum;
    }

    public void setMoneySum(String moneySum) {
        this.moneySum = moneySum;
    }

    public int getGoodSum() {
        return goodSum;
    }

    public void setGoodSum(int goodSum) {
        this.goodSum = goodSum;
    }

    public List<OrderGoodsEntity> getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(List<OrderGoodsEntity> orderGoods) {
        this.orderGoods = orderGoods;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getCanRefundAllStatus() {
        return canRefundAllStatus;
    }

    public void setCanRefundAllStatus(int canRefundAllStatus) {
        this.canRefundAllStatus = canRefundAllStatus;
    }

    public int getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(int commentStatus) {
        this.commentStatus = commentStatus;
    }

    public class AnswerEntity implements Serializable{

        private int id;

        private int mchNo;

        private int mchId;

        private int userId;

        private int answerId;

        //内容
        private String content;

        private int hits;

        private int status;

        private int del;

        private int mchDel;

        private int supplierId;

        private int mchAnswerId;

        private long ctime;

        private long utime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMchNo() {
            return mchNo;
        }

        public void setMchNo(int mchNo) {
            this.mchNo = mchNo;
        }

        public int getMchId() {
            return mchId;
        }

        public void setMchId(int mchId) {
            this.mchId = mchId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getAnswerId() {
            return answerId;
        }

        public void setAnswerId(int answerId) {
            this.answerId = answerId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getHits() {
            return hits;
        }

        public void setHits(int hits) {
            this.hits = hits;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getDel() {
            return del;
        }

        public void setDel(int del) {
            this.del = del;
        }

        public int getMchDel() {
            return mchDel;
        }

        public void setMchDel(int mchDel) {
            this.mchDel = mchDel;
        }

        public int getSupplierId() {
            return supplierId;
        }

        public void setSupplierId(int supplierId) {
            this.supplierId = supplierId;
        }

        public int getMchAnswerId() {
            return mchAnswerId;
        }

        public void setMchAnswerId(int mchAnswerId) {
            this.mchAnswerId = mchAnswerId;
        }

        public long getCtime() {
            return ctime;
        }

        public void setCtime(long ctime) {
            this.ctime = ctime;
        }

        public long getUtime() {
            return utime;
        }

        public void setUtime(long utime) {
            this.utime = utime;
        }
    }

    public class OrderUserEntity implements Serializable{

        private int id;

        private String realname;

        private String mobile;

        private int supSum;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getSupSum() {
            return supSum;
        }

        public void setSupSum(int supSum) {
            this.supSum = supSum;
        }
    }

    public class OrderInvoiceEntity implements Serializable{

        private int id;

        private String title;

        private String item;

        private String no;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }
    }

    public class OrderEntity implements Serializable{

        private String totalFee;

        private String discountFee;

        private String explain;

        private String goodType;

        private int dataId;

        private String orderNo;

        private String payFee;

        private String base64Code;

        private String code;

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getTotalFee() {
            return totalFee;
        }

        public void setTotalFee(String totalFee) {
            this.totalFee = totalFee;
        }

        public String getDiscountFee() {
            return discountFee;
        }

        public void setDiscountFee(String discountFee) {
            this.discountFee = discountFee;
        }

        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
            this.explain = explain;
        }

        public String getGoodType() {
            return goodType;
        }

        public void setGoodType(String goodType) {
            this.goodType = goodType;
        }

        public int getDataId() {
            return dataId;
        }

        public void setDataId(int dataId) {
            this.dataId = dataId;
        }

        public String getPayFee() {
            return payFee;
        }

        public void setPayFee(String payFee) {
            this.payFee = payFee;
        }

        public String getBase64Code() {
            return base64Code;
        }

        public void setBase64Code(String base64Code) {
            this.base64Code = base64Code;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

    //用车对象
    public class OrderCarEntity implements Serializable{

        private int orderId;

        private String orderNo;

        private String estimatePrice;

        private String goodsType;

        private String orderStatusTitle;

        private String startAddress;

        private String endAddress;

        private String departureTime;

        private String cancelNote;

        private String isRefund;

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getEstimatePrice() {
            return estimatePrice;
        }

        public void setEstimatePrice(String estimatePrice) {
            this.estimatePrice = estimatePrice;
        }

        public String getGoodsType() {
            return goodsType;
        }

        public void setGoodsType(String goodsType) {
            this.goodsType = goodsType;
        }

        public String getOrderStatusTitle() {
            return orderStatusTitle;
        }

        public void setOrderStatusTitle(String orderStatusTitle) {
            this.orderStatusTitle = orderStatusTitle;
        }

        public String getStartAddress() {
            return startAddress;
        }

        public void setStartAddress(String startAddress) {
            this.startAddress = startAddress;
        }

        public String getEndAddress() {
            return endAddress;
        }

        public void setEndAddress(String endAddress) {
            this.endAddress = endAddress;
        }

        public String getDepartureTime() {
            return departureTime;
        }

        public void setDepartureTime(String departureTime) {
            this.departureTime = departureTime;
        }

        public String getCancelNote() {
            return cancelNote;
        }

        public void setCancelNote(String cancelNote) {
            this.cancelNote = cancelNote;
        }

        public String getIsRefund() {
            return isRefund;
        }

        public void setIsRefund(String isRefund) {
            this.isRefund = isRefund;
        }
    }

    public class OrderGoodsEntity implements Serializable {

        private int orderGoodsId;

        private String note;

        private AddressEntity address;

        private String goodsId;

        private String goodType;

        //商户名
        private String mchName;

        private String surplusNum;

        private NoteParamEntity noteParam;

        private String goodTime;

        private String price;

        private String goodsTitle;

        private String tel;

        private String openTime;

        private String goodsNum;

        private String usedNum;

        private String goodsTotalFee;

        private int canRefundStatus;

        public int getOrderGoodsId() {
            return orderGoodsId;
        }

        public void setOrderGoodsId(int orderGoodsId) {
            this.orderGoodsId = orderGoodsId;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public AddressEntity getAddress() {
            return address;
        }

        public void setAddress(AddressEntity address) {
            this.address = address;
        }

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public String getGoodType() {
            return goodType;
        }

        public void setGoodType(String goodType) {
            this.goodType = goodType;
        }

        public String getMchName() {
            return mchName;
        }

        public void setMchName(String mchName) {
            this.mchName = mchName;
        }

        public String getSurplusNum() {
            return surplusNum;
        }

        public void setSurplusNum(String surplusNum) {
            this.surplusNum = surplusNum;
        }

        public NoteParamEntity getNoteParam() {
            return noteParam;
        }

        public void setNoteParam(NoteParamEntity noteParam) {
            this.noteParam = noteParam;
        }

        public String getGoodTime() {
            return goodTime;
        }

        public void setGoodTime(String goodTime) {
            this.goodTime = goodTime;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getGoodsTitle() {
            return goodsTitle;
        }

        public void setGoodsTitle(String goodsTitle) {
            this.goodsTitle = goodsTitle;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getOpenTime() {
            return openTime;
        }

        public void setOpenTime(String openTime) {
            this.openTime = openTime;
        }

        public String getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(String goodsNum) {
            this.goodsNum = goodsNum;
        }

        public String getUsedNum() {
            return usedNum;
        }

        public void setUsedNum(String usedNum) {
            this.usedNum = usedNum;
        }

        public String getGoodsTotalFee() {
            return goodsTotalFee;
        }

        public void setGoodsTotalFee(String goodsTotalFee) {
            this.goodsTotalFee = goodsTotalFee;
        }

        public int getCanRefundStatus() {
            return canRefundStatus;
        }

        public void setCanRefundStatus(int canRefundStatus) {
            this.canRefundStatus = canRefundStatus;
        }
    }

    public class AddressEntity implements Serializable{

        //地址
        private String address;

        //省会
        private String province;

        //城市
        private String city;

        private String county;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCounty() {
            return county;
        }

        public void setCounty(String county) {
            this.county = county;
        }
    }

    public class NoteParamEntity implements Serializable{

        private String latitude;

        private String longitude;

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }
    }


}
