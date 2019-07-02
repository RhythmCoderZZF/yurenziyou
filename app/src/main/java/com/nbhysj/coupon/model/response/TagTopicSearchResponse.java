package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/4/2
 * description：标签主题搜索
 */
public class TagTopicSearchResponse {

    private List<HotTagsTopicBean> result;

    public List<HotTagsTopicBean> getResult() {
        return result;
    }

    public void setResult(List<HotTagsTopicBean> result) {
        this.result = result;
    }

}
