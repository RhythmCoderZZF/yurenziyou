package com.nbhysj.coupon.model.response;

import java.io.Serializable;
import java.util.List;

/**
 * @auther：hysj created on 2019/4/3
 * description：帖子详情返回
 */
public class PostInfoDetailResponse implements Serializable {

    private List<PostInfoEntity> result;

    private PageBean page;

    public List<PostInfoEntity> getResult() {
        return result;
    }

    public void setResult(List<PostInfoEntity> result) {
        this.result = result;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public static class PostInfoEntity {

        private int id;

        private int collectionCount;

        private int commentCount;

        private int hits;

        private int postsType;

        private int themeId;

        private int userId;

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

        private List<PostCommentBean> postsComments;

        private List<String> resources;

        private List<ZanAvatersBean> zanAvaters;

        private String nickname;

        private String avater;

        private List<TopicsBean> topics;

        private int zanStatus;

        private List<RecommendInterestUsersBean> recommendUsers;

        private long ctime;

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

        public int getThemeId() {
            return themeId;
        }

        public void setThemeId(int themeId) {
            this.themeId = themeId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
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

        public List<TopicsBean> getTopics() {
            return topics;
        }

        public void setTopics(List<TopicsBean> topics) {
            this.topics = topics;
        }

        public int getZanStatus() {
            return zanStatus;
        }

        public void setZanStatus(int zanStatus) {
            this.zanStatus = zanStatus;
        }

        public List<RecommendInterestUsersBean> getRecommendUsers() {
            return recommendUsers;
        }

        public void setRecommendUsers(List<RecommendInterestUsersBean> recommendUsers) {
            this.recommendUsers = recommendUsers;
        }

        public long getCtime() {
            return ctime;
        }

        public void setCtime(long ctime) {
            this.ctime = ctime;
        }

        public List<ZanAvatersBean> getZanAvaters() {
            return zanAvaters;
        }

        public void setZanAvaters(List<ZanAvatersBean> zanAvaters) {
            this.zanAvaters = zanAvaters;
        }
    }

    public static class PageBean {
        /**
         * page : 1
         * pageSize : 10
         * begin : 0
         * pageCount : 24
         * totalPage : 3
         * hasNext : 1
         * hasPrevious : 0
         * keyWords : null
         */

        private int page;
        private int pageSize;
        private int begin;
        private int pageCount;
        private int totalPage;
        private int hasNext;
        private int hasPrevious;
        private Object keyWords;

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
    }

    public class SmallTagEntity {

        private int id;

        private int userId;

        private int homeIsNearby;

        private int del;

        private int status;

        private String intro;

        private String title;

        private Long utime;

        private Long ctime;

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

        public Long getUtime() {
            return utime;
        }

        public void setUtime(Long utime) {
            this.utime = utime;
        }

        public Long getCtime() {
            return ctime;
        }

        public void setCtime(Long ctime) {
            this.ctime = ctime;
        }
    }
}