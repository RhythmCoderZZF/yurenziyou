package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/9/22
 * description：组合商户
 */
public class GroupMchResponse {

    private String banner;

    //标签
    private List<TagsEntity> tags;

    private List<GroupEntity> group;

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public List<TagsEntity> getTags() {
        return tags;
    }

    public void setTags(List<TagsEntity> tags) {
        this.tags = tags;
    }

    public List<GroupEntity> getGroup() {
        return group;
    }

    public void setGroup(List<GroupEntity> group) {
        this.group = group;
    }

    public class TagsEntity{

        private int id;

        private int pid;

        private String title;

        private int del;

        private String publicTagsType;

        private long utime;

        private long ctime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getDel() {
            return del;
        }

        public void setDel(int del) {
            this.del = del;
        }

        public String getPublicTagsType() {
            return publicTagsType;
        }

        public void setPublicTagsType(String publicTagsType) {
            this.publicTagsType = publicTagsType;
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
    public class GroupEntity{

        private String title;

        private List<PackageVOSEntity> packageVOS;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<PackageVOSEntity> getPackageVOS() {
            return packageVOS;
        }

        public void setPackageVOS(List<PackageVOSEntity> packageVOS) {
            this.packageVOS = packageVOS;
        }
    }

    public class PackageVOSEntity{

        private int id;

        //标题
        private String title;

        //简介
        private String intro;

        //市场价格
        private int marketPrice;

        //照片
        private String photo;

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

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public int getMarketPrice() {
            return marketPrice;
        }

        public void setMarketPrice(int marketPrice) {
            this.marketPrice = marketPrice;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }
    }
}
