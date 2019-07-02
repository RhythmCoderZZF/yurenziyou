package com.nbhysj.coupon.model.response;

import java.io.Serializable;
import java.util.List;

/**
 * @auther：hysj created on 2019/4/4
 * description：
 */
public class HomePageSubTopicTagBean implements Serializable {

    private int page;
    private int pageSize;
    private int begin;
    private int pageCount;
    private int totalPage;
    private int hasNext;
    private int hasPrevious;
    private Object keyWords;
    private int id;
    private int collectionCount;
    private int commentCount;
    private int hits;
    private int postsType;
    private int userId;
    private String nickname;
    private String avater;
    private int zanCount;
    private String content;
    private String intro;
    private String latitude;
    private String longitude;
    private String photo;
    private String resourceUrl;
    private int photoWidth;
    private int photoHeight;
    private String selectedLongitude;
    private String selectedLatitude;
    //用户评论列表
    private List<PostsCommentsEntity> postsComments;
    private List<String> resources;
    //点赞用户头像
    private List<ZanAvatersEntity> zanAvaters;
    //主题标签
    private List<TopicsEntity> topics;
    //推荐感兴趣的用户
    private List<RecommendUsersEntity> recommendUsers;
    private int zanStatus;
    private Long ctime;
    private double distance;
    private boolean love;

    public boolean isLove() {
        return love;
    }

    public void setLove(boolean love) {
        this.love = love;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getHasNext() {
        return hasNext;
    }

    public void setHasNext(int hasNext) {
        this.hasNext = hasNext;
    }

    public int getHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(int hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public Object getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(Object keyWords) {
        this.keyWords = keyWords;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCollectionCount() {
        return collectionCount;
    }

    public void setCollectionCount(int collectionCount) {
        this.collectionCount = collectionCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getPostsType() {
        return postsType;
    }

    public void setPostsType(int postsType) {
        this.postsType = postsType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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


    public int getZanStatus() {
        return zanStatus;
    }

    public void setZanStatus(int zanStatus) {
        this.zanStatus = zanStatus;
    }

    public int getZanCount() {
        return zanCount;
    }

    public void setZanCount(int zanCount) {
        this.zanCount = zanCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public int getPhotoWidth() {
        return photoWidth;
    }

    public void setPhotoWidth(int photoWidth) {
        this.photoWidth = photoWidth;
    }

    public int getPhotoHeight() {
        return photoHeight;
    }

    public void setPhotoHeight(int photoHeight) {
        this.photoHeight = photoHeight;
    }

    public String getSelectedLongitude() {
        return selectedLongitude;
    }

    public void setSelectedLongitude(String selectedLongitude) {
        this.selectedLongitude = selectedLongitude;
    }

    public String getSelectedLatitude() {
        return selectedLatitude;
    }

    public void setSelectedLatitude(String selectedLatitude) {
        this.selectedLatitude = selectedLatitude;
    }

    public List<PostsCommentsEntity> getPostsComments() {
        return postsComments;
    }

    public void setPostsComments(List<PostsCommentsEntity> postsComments) {
        this.postsComments = postsComments;
    }

    public List<String> getResources() {
        return resources;
    }

    public void setResources(List<String> resources) {
        this.resources = resources;
    }

    public List<ZanAvatersEntity> getZanAvaters() {
        return zanAvaters;
    }

    public void setZanAvaters(List<ZanAvatersEntity> zanAvaters) {
        this.zanAvaters = zanAvaters;
    }

    public List<TopicsEntity> getTopics() {
        return topics;
    }

    public void setTopics(List<TopicsEntity> topics) {
        this.topics = topics;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    public class PostsCommentsEntity {

        private int userId;

        private String content;

        private int postsId;

        private int pid;

        private int commentType;

        private int authorId;

        private int zanCount;

        private int postsType;

        private String postsPhoto;

        private String postsContent;

        private String nickname;

        private String avater;

        private Long ctime;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getPostsId() {
            return postsId;
        }

        public void setPostsId(int postsId) {
            this.postsId = postsId;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public int getCommentType() {
            return commentType;
        }

        public void setCommentType(int commentType) {
            this.commentType = commentType;
        }

        public int getAuthorId() {
            return authorId;
        }

        public void setAuthorId(int authorId) {
            this.authorId = authorId;
        }

        public int getZanCount() {
            return zanCount;
        }

        public void setZanCount(int zanCount) {
            this.zanCount = zanCount;
        }

        public int getPostsType() {
            return postsType;
        }

        public void setPostsType(int postsType) {
            this.postsType = postsType;
        }

        public String getPostsPhoto() {
            return postsPhoto;
        }

        public void setPostsPhoto(String postsPhoto) {
            this.postsPhoto = postsPhoto;
        }

        public String getPostsContent() {
            return postsContent;
        }

        public void setPostsContent(String postsContent) {
            this.postsContent = postsContent;
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

        public Long getCtime() {
            return ctime;
        }

        public void setCtime(Long ctime) {
            this.ctime = ctime;
        }
    }

    public class ZanAvatersEntity {

        private int id;

        private int cityNum;

        private int collectionNum;

        private int del;

        private int fansNum;

        private int followNum;

        private int loginCount;

        private int orderNum;

        private int sex;

        private int socialStatus;

        private int strategyNum;

        private int userStatus;

        private int videoNum;

        private int zanNum;

        private String avater;

        private int pushStatus;

        private int privacyStatus;

        private String birthday;

        private String email;

        private String identityCard;

        private String liveCity;

        private String longitude;

        private String mobile;

        private String nickname;

        private String password;

        private String profile;

        private String qq;

        private String salt;

        private String username;

        private String wechat;

        private Long lastLoginTime;

        private String code;

        private String auth;

        private String emailCode;

        private Long ctime;

        private Long utime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCityNum() {
            return cityNum;
        }

        public void setCityNum(int cityNum) {
            this.cityNum = cityNum;
        }

        public int getCollectionNum() {
            return collectionNum;
        }

        public void setCollectionNum(int collectionNum) {
            this.collectionNum = collectionNum;
        }

        public int getDel() {
            return del;
        }

        public void setDel(int del) {
            this.del = del;
        }

        public int getFansNum() {
            return fansNum;
        }

        public void setFansNum(int fansNum) {
            this.fansNum = fansNum;
        }

        public int getFollowNum() {
            return followNum;
        }

        public void setFollowNum(int followNum) {
            this.followNum = followNum;
        }

        public int getLoginCount() {
            return loginCount;
        }

        public void setLoginCount(int loginCount) {
            this.loginCount = loginCount;
        }

        public int getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(int orderNum) {
            this.orderNum = orderNum;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getSocialStatus() {
            return socialStatus;
        }

        public void setSocialStatus(int socialStatus) {
            this.socialStatus = socialStatus;
        }

        public int getStrategyNum() {
            return strategyNum;
        }

        public void setStrategyNum(int strategyNum) {
            this.strategyNum = strategyNum;
        }

        public int getUserStatus() {
            return userStatus;
        }

        public void setUserStatus(int userStatus) {
            this.userStatus = userStatus;
        }

        public int getVideoNum() {
            return videoNum;
        }

        public void setVideoNum(int videoNum) {
            this.videoNum = videoNum;
        }

        public int getZanNum() {
            return zanNum;
        }

        public void setZanNum(int zanNum) {
            this.zanNum = zanNum;
        }

        public String getAvater() {
            return avater;
        }

        public void setAvater(String avater) {
            this.avater = avater;
        }

        public int getPushStatus() {
            return pushStatus;
        }

        public void setPushStatus(int pushStatus) {
            this.pushStatus = pushStatus;
        }

        public int getPrivacyStatus() {
            return privacyStatus;
        }

        public void setPrivacyStatus(int privacyStatus) {
            this.privacyStatus = privacyStatus;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getIdentityCard() {
            return identityCard;
        }

        public void setIdentityCard(String identityCard) {
            this.identityCard = identityCard;
        }

        public String getLiveCity() {
            return liveCity;
        }

        public void setLiveCity(String liveCity) {
            this.liveCity = liveCity;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getProfile() {
            return profile;
        }

        public void setProfile(String profile) {
            this.profile = profile;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getWechat() {
            return wechat;
        }

        public void setWechat(String wechat) {
            this.wechat = wechat;
        }

        public Long getLastLoginTime() {
            return lastLoginTime;
        }

        public void setLastLoginTime(Long lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getAuth() {
            return auth;
        }

        public void setAuth(String auth) {
            this.auth = auth;
        }

        public String getEmailCode() {
            return emailCode;
        }

        public void setEmailCode(String emailCode) {
            this.emailCode = emailCode;
        }

        public Long getCtime() {
            return ctime;
        }

        public void setCtime(Long ctime) {
            this.ctime = ctime;
        }

        public Long getUtime() {
            return utime;
        }

        public void setUtime(Long utime) {
            this.utime = utime;
        }
    }

    public class TopicsEntity {

        private int id;

        private int userId;

        private int homeIsNearby;

        private int del;

        private int status;

        private String intro;

        private String title;

        private Long ctime;

        private Long utime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getHomeIsNearby() {
            return homeIsNearby;
        }

        public void setHomeIsNearby(int homeIsNearby) {
            this.homeIsNearby = homeIsNearby;
        }

        public int getDel() {
            return del;
        }

        public void setDel(int del) {
            this.del = del;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Long getCtime() {
            return ctime;
        }

        public void setCtime(Long ctime) {
            this.ctime = ctime;
        }

        public Long getUtime() {
            return utime;
        }

        public void setUtime(Long utime) {
            this.utime = utime;
        }
    }

    //可能感兴趣的人
    public class RecommendUsersEntity {

        //用户id
        private int id;

        //用户昵称
        private String nickname;

        //用户头像
        private String avater;

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
    }

    public List<RecommendUsersEntity> getRecommendUsers() {
        return recommendUsers;
    }

    public void setRecommendUsers(List<RecommendUsersEntity> recommendUsers) {
        this.recommendUsers = recommendUsers;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "HomePageSubTopicTagBean{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", begin=" + begin +
                ", pageCount=" + pageCount +
                ", totalPage=" + totalPage +
                ", hasNext=" + hasNext +
                ", hasPrevious=" + hasPrevious +
                ", keyWords=" + keyWords +
                ", id=" + id +
                ", collectionCount=" + collectionCount +
                ", commentCount=" + commentCount +
                ", hits=" + hits +
                ", postsType=" + postsType +
                ", userId=" + userId +
                ", nickname='" + nickname + '\'' +
                ", avater='" + avater + '\'' +
                ", zanCount=" + zanCount +
                ", content='" + content + '\'' +
                ", intro='" + intro + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", photo='" + photo + '\'' +
                ", resourceUrl='" + resourceUrl + '\'' +
                ", photoWidth=" + photoWidth +
                ", photoHeight=" + photoHeight +
                ", selectedLongitude='" + selectedLongitude + '\'' +
                ", selectedLatitude='" + selectedLatitude + '\'' +
                ", postsComments=" + postsComments +
                ", resources=" + resources +
                ", zanAvaters=" + zanAvaters +
                ", topics=" + topics +
                ", ctime=" + ctime +
                '}';
    }
}
