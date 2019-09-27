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
    private List<PostCommentBean> postsComments;
    private List<String> resources;
    //点赞用户头像
    private List<ZanAvatersBean> zanAvaters;
    //主题标签
    private List<TopicsBean> topics;
    //推荐感兴趣的用户
    private List<RecommendInterestUsersBean> recommendUsers;
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

    public List<PostCommentBean> getPostsComments() {
        return postsComments;
    }

    public void setPostsComments(List<PostCommentBean> postsComments) {
        this.postsComments = postsComments;
    }

    public List<String> getResources() {
        return resources;
    }

    public void setResources(List<String> resources) {
        this.resources = resources;
    }

    public List<ZanAvatersBean> getZanAvaters() {
        return zanAvaters;
    }

    public void setZanAvaters(List<ZanAvatersBean> zanAvaters) {
        this.zanAvaters = zanAvaters;
    }

    public List<TopicsBean> getTopics() {
        return topics;
    }

    public void setTopics(List<TopicsBean> topics) {
        this.topics = topics;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    public List<RecommendInterestUsersBean> getRecommendUsers() {
        return recommendUsers;
    }

    public void setRecommendUsers(List<RecommendInterestUsersBean> recommendUsers) {
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
