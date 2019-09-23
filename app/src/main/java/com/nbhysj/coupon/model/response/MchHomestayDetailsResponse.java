package com.nbhysj.coupon.model.response;

import java.io.Serializable;
import java.util.List;

/**
 * @auther：hysj created on 2019/09/19
 * description：民宿详情
 */
public class MchHomestayDetailsResponse implements Serializable {

    //房东信息
    private LandlorEntity landlor;

    //商户详情
    private MchDetailsEntity mchDetails;

    private MchQuestionEntity mchQuestion;

    private List<MchGoodsBean> mchGoods;

    private CommentEntity comment;

    private List<HotelBean> nearbyHotel;

    public LandlorEntity getLandlor() {
        return landlor;
    }

    public void setLandlor(LandlorEntity landlor) {
        this.landlor = landlor;
    }

    public MchDetailsEntity getMchDetails() {
        return mchDetails;
    }

    public void setMchDetails(MchDetailsEntity mchDetails) {
        this.mchDetails = mchDetails;
    }

    public MchQuestionEntity getMchQuestion() {
        return mchQuestion;
    }

    public void setMchQuestion(MchQuestionEntity mchQuestion) {
        this.mchQuestion = mchQuestion;
    }

    public List<MchGoodsBean> getMchGoods() {
        return mchGoods;
    }

    public void setMchGoods(List<MchGoodsBean> mchGoods) {
        this.mchGoods = mchGoods;
    }

    public CommentEntity getComment() {
        return comment;
    }

    public void setComment(CommentEntity comment) {
        this.comment = comment;
    }

    public List<HotelBean> getNearbyHotel() {
        return nearbyHotel;
    }

    public void setNearbyHotel(List<HotelBean> nearbyHotel) {
        this.nearbyHotel = nearbyHotel;
    }

    public class MchDetailsEntity implements Serializable {

        private int id;
        //商户id
        private int mchId;

        private String mchType;

        private String mchType2;

        private int supplierId;

        private String mchName;

        private String cityID;

        private int commentNum;

        private int collectionNum;

        private String intro;

        private String photo;

        private List<String> recommendPhoto;

        private int level;

        private String nationalLevel;

        private String openTime;

        private List<ServiceEntity> serviceJson;

        private ContentEntity content;

        private String discountInfo;

        private String seoDesc;

        private String address;

        private String longitude;

        private String latitude;

        private String bookingInfo;

        private String tel;

        private float commentScore;

        private int consumePrice;

        private String mchRanking;

        private String buildInfo;

        private int userCollectState;

        private int mchPhotoCount;

        private String checkinTime;

        private String leaveTime;

        private List<TagsEntity> tags;

        //是否允许携带宠物 Integer
        private int petsStatus;

        //膳食政策 String
        private String dietInfo;

        //是否携带宠物 String
        private String petsInfo;

        //地图 String
        private String staticMap;

        private String allFacilityDetails;

        private String bookingInformationDetails;

        public int getMchId() {
            return mchId;
        }

        public void setMchId(int mchId) {
            this.mchId = mchId;
        }

        public String getMchType() {
            return mchType;
        }

        public void setMchType(String mchType) {
            this.mchType = mchType;
        }

        public int getSupplierId() {
            return supplierId;
        }

        public void setSupplierId(int supplierId) {
            this.supplierId = supplierId;
        }

        public String getMchName() {
            return mchName;
        }

        public void setMchName(String mchName) {
            this.mchName = mchName;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public int getConsumePrice() {
            return consumePrice;
        }

        public void setConsumePrice(int consumePrice) {
            this.consumePrice = consumePrice;
        }

        public List<String> getRecommendPhoto() {
            return recommendPhoto;
        }

        public void setRecommendPhoto(List<String> recommendPhoto) {
            this.recommendPhoto = recommendPhoto;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getOpenTime() {
            return openTime;
        }

        public void setOpenTime(String openTime) {
            this.openTime = openTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<ServiceEntity> getServiceJson() {
            return serviceJson;
        }

        public void setServiceJson(List<ServiceEntity> serviceJson) {
            this.serviceJson = serviceJson;
        }

        public String getMchType2() {
            return mchType2;
        }

        public void setMchType2(String mchType2) {
            this.mchType2 = mchType2;
        }

        public String getNationalLevel() {
            return nationalLevel;
        }

        public void setNationalLevel(String nationalLevel) {
            this.nationalLevel = nationalLevel;
        }

        public ContentEntity getContent() {
            return content;
        }

        public void setContent(ContentEntity content) {
            this.content = content;
        }

        public String getDiscountInfo() {
            return discountInfo;
        }

        public void setDiscountInfo(String discountInfo) {
            this.discountInfo = discountInfo;
        }

        public String getSeoDesc() {
            return seoDesc;
        }

        public void setSeoDesc(String seoDesc) {
            this.seoDesc = seoDesc;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getBookingInfo() {
            return bookingInfo;
        }

        public void setBookingInfo(String bookingInfo) {
            this.bookingInfo = bookingInfo;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public float getCommentScore() {
            return commentScore;
        }

        public void setCommentScore(float commentScore) {
            this.commentScore = commentScore;
        }

        public String getMchRanking() {
            return mchRanking;
        }

        public void setMchRanking(String mchRanking) {
            this.mchRanking = mchRanking;
        }

        public String getBuildInfo() {
            return buildInfo;
        }

        public void setBuildInfo(String buildInfo) {
            this.buildInfo = buildInfo;
        }

        public int getUserCollectState() {
            return userCollectState;
        }

        public void setUserCollectState(int userCollectState) {
            this.userCollectState = userCollectState;
        }

        public int getMchPhotoCount() {
            return mchPhotoCount;
        }

        public void setMchPhotoCount(int mchPhotoCount) {
            this.mchPhotoCount = mchPhotoCount;
        }

        public String getCheckinTime() {
            return checkinTime;
        }

        public void setCheckinTime(String checkinTime) {
            this.checkinTime = checkinTime;
        }

        public String getLeaveTime() {
            return leaveTime;
        }

        public void setLeaveTime(String leaveTime) {
            this.leaveTime = leaveTime;
        }

        public List<TagsEntity> getTags() {
            return tags;
        }

        public void setTags(List<TagsEntity> tags) {
            this.tags = tags;
        }

        public int getPetsStatus() {
            return petsStatus;
        }

        public void setPetsStatus(int petsStatus) {
            this.petsStatus = petsStatus;
        }

        public String getDietInfo() {
            return dietInfo;
        }

        public void setDietInfo(String dietInfo) {
            this.dietInfo = dietInfo;
        }

        public String getPetsInfo() {
            return petsInfo;
        }

        public void setPetsInfo(String petsInfo) {
            this.petsInfo = petsInfo;
        }

        public String getCityID() {
            return cityID;
        }

        public void setCityID(String cityID) {
            this.cityID = cityID;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public int getCollectionNum() {
            return collectionNum;
        }

        public void setCollectionNum(int collectionNum) {
            this.collectionNum = collectionNum;
        }

        public String getStaticMap() {
            return staticMap;
        }

        public void setStaticMap(String staticMap) {
            this.staticMap = staticMap;
        }

        public String getAllFacilityDetails() {
            return allFacilityDetails;
        }

        public void setAllFacilityDetails(String allFacilityDetails) {
            this.allFacilityDetails = allFacilityDetails;
        }

        public String getBookingInformationDetails() {
            return bookingInformationDetails;
        }

        public void setBookingInformationDetails(String bookingInformationDetails) {
            this.bookingInformationDetails = bookingInformationDetails;
        }
    }

    public class ContentEntity implements Serializable {

        private String houseInfo;

        private FacilityServiceEntity service;

        private String houseDetails;

        private NoticeEntity notice;

        private RefundEntity refund;

        public String getHouseInfo() {
            return houseInfo;
        }

        public void setHouseInfo(String houseInfo) {
            this.houseInfo = houseInfo;
        }

        public FacilityServiceEntity getService() {
            return service;
        }

        public void setService(FacilityServiceEntity service) {
            this.service = service;
        }

        public String getHouseDetails() {
            return houseDetails;
        }

        public void setHouseDetails(String houseDetails) {
            this.houseDetails = houseDetails;
        }

        public NoticeEntity getNotice() {
            return notice;
        }

        public void setNotice(NoticeEntity notice) {
            this.notice = notice;
        }

        public RefundEntity getRefund() {
            return refund;
        }

        public void setRefund(RefundEntity refund) {
            this.refund = refund;
        }
    }

    public class MchTagsEntity {

        private int id;

        private String mchNo;

        private int mchId;

        private String mchType;

        private String title;

        private String value;

        private int zanNum;

        private long cTime;

        private long uTime;

        private int del;

        private int supplierId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMchNo() {
            return mchNo;
        }

        public void setMchNo(String mchNo) {
            this.mchNo = mchNo;
        }

        public int getMchId() {
            return mchId;
        }

        public void setMchId(int mchId) {
            this.mchId = mchId;
        }

        public String getMchType() {
            return mchType;
        }

        public void setMchType(String mchType) {
            this.mchType = mchType;
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

        public int getZanNum() {
            return zanNum;
        }

        public void setZanNum(int zanNum) {
            this.zanNum = zanNum;
        }

        public long getcTime() {
            return cTime;
        }

        public void setcTime(long cTime) {
            this.cTime = cTime;
        }

        public long getuTime() {
            return uTime;
        }

        public void setuTime(long uTime) {
            this.uTime = uTime;
        }

        public int getDel() {
            return del;
        }

        public void setDel(int del) {
            this.del = del;
        }

        public int getSupplierId() {
            return supplierId;
        }

        public void setSupplierId(int supplierId) {
            this.supplierId = supplierId;
        }
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

    public class MchQuestionEntity implements Serializable {

        private int questionId;

        private String questionContent;

        private int answerCount;

        private int questionCount;

        private String answerContent;

        public int getQuestionId() {
            return questionId;
        }

        public void setQuestionId(int questionId) {
            this.questionId = questionId;
        }

        public String getQuestionContent() {
            return questionContent;
        }

        public void setQuestionContent(String questionContent) {
            this.questionContent = questionContent;
        }

        public int getAnswerCount() {
            return answerCount;
        }

        public void setAnswerCount(int answerCount) {
            this.answerCount = answerCount;
        }

        public int getQuestionCount() {
            return questionCount;
        }

        public void setQuestionCount(int questionCount) {
            this.questionCount = questionCount;
        }

        public String getAnswerContent() {
            return answerContent;
        }

        public void setAnswerContent(String answerContent) {
            this.answerContent = answerContent;
        }
    }

    public class CommentEntity {

        private int commentNum;

        private ScoreEntity score;

        private List<MchCommentEntity> comment;

        private List<LabelEntity> label;

        public ScoreEntity getScore() {
            return score;
        }

        public void setScore(ScoreEntity score) {
            this.score = score;
        }

        public List<MchCommentEntity> getComment() {
            return comment;
        }

        public void setComment(List<MchCommentEntity> comment) {
            this.comment = comment;
        }

        public List<LabelEntity> getLabel() {
            return label;
        }

        public void setLabel(List<LabelEntity> label) {
            this.label = label;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }
    }

    public class ScoreEntity implements Serializable {

        private int commentNum;

        private float commentScore;

        private double commentScore1;

        private double commentScore2;

        private double commentScore3;

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public float getCommentScore() {
            return commentScore;
        }

        public void setCommentScore(float commentScore) {
            this.commentScore = commentScore;
        }

        public double getCommentScore1() {
            return commentScore1;
        }

        public void setCommentScore1(double commentScore1) {
            this.commentScore1 = commentScore1;
        }

        public double getCommentScore2() {
            return commentScore2;
        }

        public void setCommentScore2(double commentScore2) {
            this.commentScore2 = commentScore2;
        }

        public double getCommentScore3() {
            return commentScore3;
        }

        public void setCommentScore3(double commentScore3) {
            this.commentScore3 = commentScore3;
        }
    }

    public class LabelEntity implements Serializable{

        private String title;

        private int count;

        private int value;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public class ServiceEntity implements Serializable{

        private String value;

        private String title;

        private String photo;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }
    }

    public class NearbyHotelEntity {

        private List<HotelBean> nearbyHotel;

        public List<HotelBean> getNearbyHotel() {
            return nearbyHotel;
        }

        public void setNearbyHotel(List<HotelBean> nearbyHotel) {
            this.nearbyHotel = nearbyHotel;
        }
    }
    //房东
    public class LandlorEntity{

        private int id;

        private int supplierId;

        private String nickname;

        private String avatar;

        private String motto;

        private int authenticationStatus;

        private int bookingSuccess;

        private String content;

        private int confirmTime;

        private int homestayRoomNum;

        private int score;

        private int commentCount;

        private long ctime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSupplierId() {
            return supplierId;
        }

        public void setSupplierId(int supplierId) {
            this.supplierId = supplierId;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getMotto() {
            return motto;
        }

        public void setMotto(String motto) {
            this.motto = motto;
        }

        public int getAuthenticationStatus() {
            return authenticationStatus;
        }

        public void setAuthenticationStatus(int authenticationStatus) {
            this.authenticationStatus = authenticationStatus;
        }

        public int getBookingSuccess() {
            return bookingSuccess;
        }

        public void setBookingSuccess(int bookingSuccess) {
            this.bookingSuccess = bookingSuccess;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getConfirmTime() {
            return confirmTime;
        }

        public void setConfirmTime(int confirmTime) {
            this.confirmTime = confirmTime;
        }

        public int getHomestayRoomNum() {
            return homestayRoomNum;
        }

        public void setHomestayRoomNum(int homestayRoomNum) {
            this.homestayRoomNum = homestayRoomNum;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public long getCtime() {
            return ctime;
        }

        public void setCtime(long ctime) {
            this.ctime = ctime;
        }
    }

    public class FacilityServiceEntity{

        private List<ServiceEntity> facility;

        private int facilityNum;

        private String allFacility;

        public List<ServiceEntity> getFacility() {
            return facility;
        }

        public void setFacility(List<ServiceEntity> facility) {
            this.facility = facility;
        }

        public int getFacilityNum() {
            return facilityNum;
        }

        public void setFacilityNum(int facilityNum) {
            this.facilityNum = facilityNum;
        }

        public String getAllFacility() {
            return allFacility;
        }

        public void setAllFacility(String allFacility) {
            this.allFacility = allFacility;
        }
    }

    public class NoticeEntity{

        private String receptionInfo;

        private int invoiceStatus;

        private String depositInfo;

        private String intro;

        private String confineInfo;

        private String houseInfo;

        private String ruleInfo;

        private int ruleChild;

        private int ruleElderly;

        private int ruleSmoking;

        private int rulePets;

        private int ruleCook;

        private int ruleParty;

        private int rulePhotograph;

        private int ruleBed;

        private int rulePerson;

        private int ruleForeigner;

        private long ctime;

        public String getReceptionInfo() {
            return receptionInfo;
        }

        public void setReceptionInfo(String receptionInfo) {
            this.receptionInfo = receptionInfo;
        }

        public int getInvoiceStatus() {
            return invoiceStatus;
        }

        public void setInvoiceStatus(int invoiceStatus) {
            this.invoiceStatus = invoiceStatus;
        }

        public String getDepositInfo() {
            return depositInfo;
        }

        public void setDepositInfo(String depositInfo) {
            this.depositInfo = depositInfo;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getConfineInfo() {
            return confineInfo;
        }

        public void setConfineInfo(String confineInfo) {
            this.confineInfo = confineInfo;
        }

        public String getHouseInfo() {
            return houseInfo;
        }

        public void setHouseInfo(String houseInfo) {
            this.houseInfo = houseInfo;
        }

        public String getRuleInfo() {
            return ruleInfo;
        }

        public void setRuleInfo(String ruleInfo) {
            this.ruleInfo = ruleInfo;
        }

        public int getRuleChild() {
            return ruleChild;
        }

        public void setRuleChild(int ruleChild) {
            this.ruleChild = ruleChild;
        }

        public int getRuleElderly() {
            return ruleElderly;
        }

        public void setRuleElderly(int ruleElderly) {
            this.ruleElderly = ruleElderly;
        }

        public int getRuleSmoking() {
            return ruleSmoking;
        }

        public void setRuleSmoking(int ruleSmoking) {
            this.ruleSmoking = ruleSmoking;
        }

        public int getRulePets() {
            return rulePets;
        }

        public void setRulePets(int rulePets) {
            this.rulePets = rulePets;
        }

        public int getRuleCook() {
            return ruleCook;
        }

        public void setRuleCook(int ruleCook) {
            this.ruleCook = ruleCook;
        }

        public int getRuleParty() {
            return ruleParty;
        }

        public void setRuleParty(int ruleParty) {
            this.ruleParty = ruleParty;
        }

        public int getRulePhotograph() {
            return rulePhotograph;
        }

        public void setRulePhotograph(int rulePhotograph) {
            this.rulePhotograph = rulePhotograph;
        }

        public int getRuleBed() {
            return ruleBed;
        }

        public void setRuleBed(int ruleBed) {
            this.ruleBed = ruleBed;
        }

        public int getRulePerson() {
            return rulePerson;
        }

        public void setRulePerson(int rulePerson) {
            this.rulePerson = rulePerson;
        }

        public int getRuleForeigner() {
            return ruleForeigner;
        }

        public void setRuleForeigner(int ruleForeigner) {
            this.ruleForeigner = ruleForeigner;
        }

        public long getCtime() {
            return ctime;
        }

        public void setCtime(long ctime) {
            this.ctime = ctime;
        }
    }

    public class RefundEntity{

        private int id;

        private int goodsId;

        private int mchId;

        private int refundDay;

        private int refundPercentage;

        private String mchNo;

        private String refundHour;

        private int supplierId;

        private long utime;

        private long ctime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public int getMchId() {
            return mchId;
        }

        public void setMchId(int mchId) {
            this.mchId = mchId;
        }

        public int getRefundDay() {
            return refundDay;
        }

        public void setRefundDay(int refundDay) {
            this.refundDay = refundDay;
        }

        public int getRefundPercentage() {
            return refundPercentage;
        }

        public void setRefundPercentage(int refundPercentage) {
            this.refundPercentage = refundPercentage;
        }

        public String getMchNo() {
            return mchNo;
        }

        public void setMchNo(String mchNo) {
            this.mchNo = mchNo;
        }


        public String getRefundHour() {
            return refundHour;
        }

        public void setRefundHour(String refundHour) {
            this.refundHour = refundHour;
        }

        public int getSupplierId() {
            return supplierId;
        }

        public void setSupplierId(int supplierId) {
            this.supplierId = supplierId;
        }

        public long getUtime() {
            return utime;
        }

        public void setUtime(long utime) {
            this.utime = utime;
        }

        public long getCtime() {
            return ctime;
        }

        public void setCtime(long ctime) {
            this.ctime = ctime;
        }
    }
}
