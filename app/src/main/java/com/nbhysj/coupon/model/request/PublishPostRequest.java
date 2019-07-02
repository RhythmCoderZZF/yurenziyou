package com.nbhysj.coupon.model.request;

import java.util.List;

/**
 * @auther：hysj created on 2019/3/25
 * description：发布帖子
 */
public class PublishPostRequest {

    //贴子内容
    private String content;

    //贴子简介
    private String intro;

    //经度
    private String latitude;

    //纬度
    private String longitude;

    //商户id集合
    private List<Integer> mchIds;

    //资源集合
    private List<ResourceInfoEntity> resource;

    //用户选择位置纬度
    private String selectedLatitude;

    //用户选择位置经度
    private String selectedLongitude;

    //主题的id,即标签id
    private List<Integer> topicIds;

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

    public List<Integer> getMchIds() {
        return mchIds;
    }

    public void setMchIds(List<Integer> mchIds) {
        this.mchIds = mchIds;
    }

    public List<ResourceInfoEntity> getResource() {
        return resource;
    }

    public void setResource(List<ResourceInfoEntity> resource) {
        this.resource = resource;
    }

    public String getSelectedLatitude() {
        return selectedLatitude;
    }

    public void setSelectedLatitude(String selectedLatitude) {
        this.selectedLatitude = selectedLatitude;
    }

    public String getSelectedLongitude() {
        return selectedLongitude;
    }

    public void setSelectedLongitude(String selectedLongitude) {
        this.selectedLongitude = selectedLongitude;
    }

    public List<Integer> getTopicIds() {
        return topicIds;
    }

    public void setTopicIds(List<Integer> topicIds) {
        this.topicIds = topicIds;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public class ResourceInfoEntity {

        //资源宽度
        private int width;

        //资源高度
        private int height;

        //资源排序
        private int sort;

        //资源类型 1图 2声 3视频 4动图
        private int type;

        //资源url
        private String url;

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }
    }
}
