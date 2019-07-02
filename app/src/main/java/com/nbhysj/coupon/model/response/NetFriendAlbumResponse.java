package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/6/13
 * description：网友相册
 */
public class NetFriendAlbumResponse {

    private List<NetFriendAlbumEntity> userPhotos;

    private BasePaginationResult page;

    public BasePaginationResult getPage() {
        return page;
    }

    public void setPage(BasePaginationResult page) {
        this.page = page;
    }

    public List<NetFriendAlbumEntity> getUserPhotos() {
        return userPhotos;
    }

    public void setUserPhotos(List<NetFriendAlbumEntity> userPhotos) {
        this.userPhotos = userPhotos;
    }

    public class NetFriendAlbumEntity {
        private int id;

        private String mchNo;

        private int mchId;

        private int recommStatus;

        private long recommTime;

        private int commentId;

        private String photo;

        private int supplierId;

        private long ctime;

        private TailsEntity tails;

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

        public int getRecommStatus() {
            return recommStatus;
        }

        public void setRecommStatus(int recommStatus) {
            this.recommStatus = recommStatus;
        }

        public long getRecommTime() {
            return recommTime;
        }

        public void setRecommTime(long recommTime) {
            this.recommTime = recommTime;
        }

        public int getCommentId() {
            return commentId;
        }

        public void setCommentId(int commentId) {
            this.commentId = commentId;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public int getSupplierId() {
            return supplierId;
        }

        public void setSupplierId(int supplierId) {
            this.supplierId = supplierId;
        }

        public long getCtime() {
            return ctime;
        }

        public void setCtime(long ctime) {
            this.ctime = ctime;
        }

        public TailsEntity getTails() {
            return tails;
        }

        public void setTails(TailsEntity tails) {
            this.tails = tails;
        }

        public class TailsEntity {

            private int userId;

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }
        }
    }
}
