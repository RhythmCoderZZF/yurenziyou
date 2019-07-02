package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/4/14
 * description：分享
 */
public class ShareResponse {

    private Long time;

    private List<ShareEntity> shareList;

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public List<ShareEntity> getShareList() {
        return shareList;
    }

    public void setShareList(List<ShareEntity> shareList) {
        this.shareList = shareList;
    }

    public class ShareEntity {

        private String title;

        private String content;

        private List<String> imageList;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<String> getImageList() {
            return imageList;
        }

        public void setImageList(List<String> imageList) {
            this.imageList = imageList;
        }
    }
}
