package com.nbhysj.coupon.model.response;

import android.renderscript.ScriptIntrinsicYuvToRGB;

import java.util.List;

/**
 * @auther：hysj created on 2019/11/10
 * description：关注
 */
public class AttentionResponse {

    private List<UserFansFollowBean> recommend;

    private AttentionEntity list;

    public List<UserFansFollowBean> getRecommend() {
        return recommend;
    }

    public void setRecommend(List<UserFansFollowBean> recommend) {
        this.recommend = recommend;
    }

    public AttentionEntity getList() {
        return list;
    }

    public void setList(AttentionEntity list) {
        this.list = list;
    }

    public class AttentionEntity{

        private List<UserFansFollowBean> result;

        private BasePaginationResult page;

        public List<UserFansFollowBean> getResult() {
            return result;
        }

        public void setResult(List<UserFansFollowBean> result) {
            this.result = result;
        }

        public BasePaginationResult getPage() {
            return page;
        }

        public void setPage(BasePaginationResult page) {
            this.page = page;
        }
    }
}
