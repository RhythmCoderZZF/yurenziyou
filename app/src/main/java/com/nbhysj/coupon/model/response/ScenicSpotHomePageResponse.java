package com.nbhysj.coupon.model.response;

import java.io.Serializable;
import java.util.List;

/**
 * @auther：hysj created on 2019/4/3
 * description：景点首页返回
 */
public class ScenicSpotHomePageResponse implements Serializable {

    //景点分类
    private List<CateEntity> cate;

    //商户
    private Overflow mch;

    //附近热门
    private List<MchTypeBean> hot;

    public Overflow getMch() {
        return mch;
    }

    public void setMch(Overflow mch) {
        this.mch = mch;
    }

    public List<MchTypeBean> getHot() {
        return hot;
    }

    public void setHot(List<MchTypeBean> hot) {
        this.hot = hot;
    }

    public List<CateEntity> getCate() {
        return cate;
    }

    public void setCate(List<CateEntity> cate) {
        this.cate = cate;
    }

    public class CateEntity {

        private int id;

        private int num;

        private String title;

        private String photo;

        private String mchType;

        private long ctime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMchType() {
            return mchType;
        }

        public void setMchType(String mchType) {
            this.mchType = mchType;
        }

        public long getCtime() {
            return ctime;
        }

        public void setCtime(long ctime) {
            this.ctime = ctime;
        }
    }

    public class Overflow {

        ScenicSpotResult overflow;

        ScenicSpotResult distance;

        ScenicSpotResult ranking;

        ScenicSpotResult nearby;

        public ScenicSpotResult getOverflow() {
            return overflow;
        }

        public void setOverflow(ScenicSpotResult overflow) {
            this.overflow = overflow;
        }

        public ScenicSpotResult getDistance() {
            return distance;
        }

        public void setDistance(ScenicSpotResult distance) {
            this.distance = distance;
        }

        public ScenicSpotResult getRanking() {
            return ranking;
        }

        public void setRanking(ScenicSpotResult ranking) {
            this.ranking = ranking;
        }

        public ScenicSpotResult getNearby() {
            return nearby;
        }

        public void setNearby(ScenicSpotResult nearby) {
            this.nearby = nearby;
        }
    }

    public class ScenicSpotResult {

        BasePaginationResult page;

        private List<MchTypeBean> result;

        public BasePaginationResult getPage() {
            return page;
        }

        public void setPage(BasePaginationResult page) {
            this.page = page;
        }

        public List<MchTypeBean> getResult() {
            return result;
        }

        public void setResult(List<MchTypeBean> result) {
            this.result = result;
        }
    }
}







