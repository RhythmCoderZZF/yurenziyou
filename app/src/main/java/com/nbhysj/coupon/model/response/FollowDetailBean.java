package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj create on 2019/2/21
 * description：关注详情
 */
public class FollowDetailBean extends UserInfoBean {

    private String url;

    private String time;

    private List<BannerUrlBO> bannerList;

    private List<String> tagFlowLayout;

    private List<UserEntity> interestUserEntity;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setBannerList(List<BannerUrlBO> bannerList) {
        this.bannerList = bannerList;
    }

    public List<BannerUrlBO> getBannerList() {
        return bannerList;
    }

    public List<String> getTagFlowLayout() {
        return tagFlowLayout;
    }

    public void setTagFlowLayout(List<String> tagFlowLayout) {
        this.tagFlowLayout = tagFlowLayout;
    }

    public List<UserEntity> getInterestUserEntity() {
        return interestUserEntity;
    }

    public void setInterestUserEntity(List<UserEntity> interestUserEntity) {
        this.interestUserEntity = interestUserEntity;
    }

    public class UserEntity {

        private String avatar;

        private String username;

        private String des;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }
    }
}
