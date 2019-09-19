package com.nbhysj.coupon.model.response;

import java.io.Serializable;
import java.util.List;

/**
 * @auther：hysj created on 2019/6/9
 * description：景点详情
 */
public class MchDetailsResponse implements Serializable {

    //游玩指南
    private List<VisitGuideEntity> visitGuide;

    //商户详情
    private MchDetailsEntity mchDetails;

    private MchQuestionEntity mchQuestion;

    private List<MchGoodsBean> mchGoods;

    private CommentEntity comment;

    //附近
    private NearbyEntity nearby;

    private NearbyHotelEntity nearbyHotel;

    public List<VisitGuideEntity> getVisitGuide() {
        return visitGuide;
    }

    public void setVisitGuide(List<VisitGuideEntity> visitGuide) {
        this.visitGuide = visitGuide;
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

    public NearbyEntity getNearby() {
        return nearby;
    }

    public void setNearby(NearbyEntity nearby) {
        this.nearby = nearby;
    }

    public NearbyHotelEntity getNearbyHotel() {
        return nearbyHotel;
    }

    public void setNearbyHotel(NearbyHotelEntity nearbyHotel) {
        this.nearbyHotel = nearbyHotel;
    }

    public class VisitGuideEntity implements Serializable {

        private int id;

        private int mchId;

        private String playItemType;

        private String title;

        private int newStatus;

        private long newStartDate;

        private long newEndDate;

        private String crowdJson;

        private String photo;

        private String word;

        private String intro;

        private String area;

        private int feeStatus;

        private String feeNote;

        private int privilegeStatus;

        private String privilegeNote;

        private String openTime;

        private int limitStatus;

        private String limitNote;

        private int adultStatus;

        private int limitPersonNum;

        private int thrillingStar;

        private int charmStar;

        private long ctime;

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

        public String getPlayItemType() {
            return playItemType;
        }

        public void setPlayItemType(String playItemType) {
            this.playItemType = playItemType;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getNewStatus() {
            return newStatus;
        }

        public void setNewStatus(int newStatus) {
            this.newStatus = newStatus;
        }

        public long getNewStartDate() {
            return newStartDate;
        }

        public void setNewStartDate(long newStartDate) {
            this.newStartDate = newStartDate;
        }

        public long getNewEndDate() {
            return newEndDate;
        }

        public void setNewEndDate(long newEndDate) {
            this.newEndDate = newEndDate;
        }

        public String getCrowdJson() {
            return crowdJson;
        }

        public void setCrowdJson(String crowdJson) {
            this.crowdJson = crowdJson;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public int getFeeStatus() {
            return feeStatus;
        }

        public void setFeeStatus(int feeStatus) {
            this.feeStatus = feeStatus;
        }

        public String getFeeNote() {
            return feeNote;
        }

        public void setFeeNote(String feeNote) {
            this.feeNote = feeNote;
        }

        public int getPrivilegeStatus() {
            return privilegeStatus;
        }

        public void setPrivilegeStatus(int privilegeStatus) {
            this.privilegeStatus = privilegeStatus;
        }

        public String getPrivilegeNote() {
            return privilegeNote;
        }

        public void setPrivilegeNote(String privilegeNote) {
            this.privilegeNote = privilegeNote;
        }

        public String getOpenTime() {
            return openTime;
        }

        public void setOpenTime(String openTime) {
            this.openTime = openTime;
        }

        public int getLimitStatus() {
            return limitStatus;
        }

        public void setLimitStatus(int limitStatus) {
            this.limitStatus = limitStatus;
        }

        public String getLimitNote() {
            return limitNote;
        }

        public void setLimitNote(String limitNote) {
            this.limitNote = limitNote;
        }

        public int getAdultStatus() {
            return adultStatus;
        }

        public void setAdultStatus(int adultStatus) {
            this.adultStatus = adultStatus;
        }

        public int getLimitPersonNum() {
            return limitPersonNum;
        }

        public void setLimitPersonNum(int limitPersonNum) {
            this.limitPersonNum = limitPersonNum;
        }

        public int getThrillingStar() {
            return thrillingStar;
        }

        public void setThrillingStar(int thrillingStar) {
            this.thrillingStar = thrillingStar;
        }

        public int getCharmStar() {
            return charmStar;
        }

        public void setCharmStar(int charmStar) {
            this.charmStar = charmStar;
        }

        public long getCtime() {
            return ctime;
        }

        public void setCtime(long ctime) {
            this.ctime = ctime;
        }
    }

    public class MchDetailsEntity implements Serializable {

        private int id;
        //商户id
        private int mchId;

        private String mchType;

        private int supplierId;

        private String mchName;

        private String cityID;

        private int commentNum;

        private int collectionNum;

        private String intro;

        private String photo;

        private List<String> recommendPhoto;

        private int level;

        private String openTime;

        private List<ServiceEntity> serviceJson;

        private List<ContentEntity> content;

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

        public List<ContentEntity> getContent() {
            return content;
        }

        public void setContent(List<ContentEntity> content) {
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

        private int mchId;

        private String mchNo;

        private String title;

        private List<String> photoJson;

        private String content;

        private int sort;

        public int getMchId() {
            return mchId;
        }

        public void setMchId(int mchId) {
            this.mchId = mchId;
        }

        public String getMchNo() {
            return mchNo;
        }

        public void setMchNo(String mchNo) {
            this.mchNo = mchNo;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getPhotoJson() {
            return photoJson;
        }

        public void setPhotoJson(List<String> photoJson) {
            this.photoJson = photoJson;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
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

        private List<SubCommentEntity> comment;

        private List<LabelEntity> label;

        public ScoreEntity getScore() {
            return score;
        }

        public void setScore(ScoreEntity score) {
            this.score = score;
        }

        public List<SubCommentEntity> getComment() {
            return comment;
        }

        public void setComment(List<SubCommentEntity> comment) {
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

    public class SubCommentEntity implements Serializable {

        private int id;

        private int score;

        private String content;

        private List<String> photo;

        private int zanNum;

        private int top;

        private String reply;

        private UserEntity user;

        private int hitsNum;

        private String mchDetails;

        private int goodsId;

        private String goodsTitle;

        private long payTime;

        private long ctime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<String> getPhoto() {
            return photo;
        }

        public void setPhoto(List<String> photo) {
            this.photo = photo;
        }

        public int getZanNum() {
            return zanNum;
        }

        public void setZanNum(int zanNum) {
            this.zanNum = zanNum;
        }

        public int getTop() {
            return top;
        }

        public void setTop(int top) {
            this.top = top;
        }

        public String getReply() {
            return reply;
        }

        public void setReply(String reply) {
            this.reply = reply;
        }

        public UserEntity getUser() {
            return user;
        }

        public void setUser(UserEntity user) {
            this.user = user;
        }

        public int getHitsNum() {
            return hitsNum;
        }

        public void setHitsNum(int hitsNum) {
            this.hitsNum = hitsNum;
        }

        public String getMchDetails() {
            return mchDetails;
        }

        public void setMchDetails(String mchDetails) {
            this.mchDetails = mchDetails;
        }

        public long getCtime() {
            return ctime;
        }

        public void setCtime(long ctime) {
            this.ctime = ctime;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public String getGoodsTitle() {
            return goodsTitle;
        }

        public void setGoodsTitle(String goodsTitle) {
            this.goodsTitle = goodsTitle;
        }

        public long getPayTime() {
            return payTime;
        }

        public void setPayTime(long payTime) {
            this.payTime = payTime;
        }
    }

    public class UserEntity implements Serializable {

        private int mchFlag;

        private int id;

        private String nickname;

        private String avater;

        private String photos;

        public int getMchFlag() {
            return mchFlag;
        }

        public void setMchFlag(int mchFlag) {
            this.mchFlag = mchFlag;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAvater() {
            return avater;
        }

        public void setAvater(String avater) {
            this.avater = avater;
        }

        public String getPhotos() {
            return photos;
        }

        public void setPhotos(String photos) {
            this.photos = photos;
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

    public class NearbyEntity implements Serializable {

        private List<NearbyTypeResponse> hotel;

        private List<NearbyTypeResponse> scenic;

        private List<NearbyTypeResponse> food;

        private List<NearbyTypeResponse> group;

        //酒店(娱乐)
        private List<NearbyTypeResponse> recreation;

        public List<NearbyTypeResponse> getHotel() {
            return hotel;
        }

        public void setHotel(List<NearbyTypeResponse> hotel) {
            this.hotel = hotel;
        }

        public List<NearbyTypeResponse> getScenic() {
            return scenic;
        }

        public void setScenic(List<NearbyTypeResponse> scenic) {
            this.scenic = scenic;
        }

        public List<NearbyTypeResponse> getFood() {
            return food;
        }

        public void setFood(List<NearbyTypeResponse> food) {
            this.food = food;
        }

        public List<NearbyTypeResponse> getGroup() {
            return group;
        }

        public void setGroup(List<NearbyTypeResponse> group) {
            this.group = group;
        }

        public List<NearbyTypeResponse> getRecreation() {
            return recreation;
        }

        public void setRecreation(List<NearbyTypeResponse> recreation) {
            this.recreation = recreation;
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

    public class NearbyHotelEntity{

        private int hotelCount;

        private List<HotelBean> hotel;

        public int getHotelCount() {
            return hotelCount;
        }

        public void setHotelCount(int hotelCount) {
            this.hotelCount = hotelCount;
        }

        public List<HotelBean> getHotel() {
            return hotel;
        }

        public void setHotel(List<HotelBean> hotel) {
            this.hotel = hotel;
        }
    }

}
