package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.request.PublishPostRequest;
import com.nbhysj.coupon.model.request.TopicRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.HotTagsTopicBean;
import com.nbhysj.coupon.model.response.MerchantListResponse;
import com.nbhysj.coupon.model.response.TagTopicSearchResponse;
import com.nbhysj.coupon.model.response.TopicResponse;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * @auther：hysj created on 2019/03/25
 * description：发布帖子模块
 */
public interface PublishPostContract {

    /**
     * 数据和业务模型
     **/
    interface Model extends BaseModel {

        //发布帖子
        Observable<BackResult> publishPost(PublishPostRequest publishPostRequest);

        //获取商户列表
        Observable<BackResult<MerchantListResponse>> getMerchantList(String cityId, String mchName, int page, int pageSize);

        //获取热门标签
        Observable<ResponseBody> getHotTagsTopicList(String type); //topic 类型：城市（city）或话题（topic）

        //话题搜索
        Observable<BackResult<TagTopicSearchResponse>> topicSearch(String type, String param); //topic 类型：城市（city）或话题（topic）

        //创建话题
        Observable<BackResult<HotTagsTopicBean>> createTopic(TopicRequest topicRequest); //话题标题 话题简介
    }

    interface View extends BaseView {

        void publishPostResult(BackResult res);

        void getMerchantListResult(BackResult<MerchantListResponse> res);

        void getHotTagsTopicListResult(ResponseBody res);

        void topicSearchResult(BackResult<TagTopicSearchResponse> res);

        void createTopicResult(BackResult<HotTagsTopicBean> res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void publishPost(PublishPostRequest publishPostRequest);

        public abstract void getMerchantList(String cityId, String mchName, int page, int pageSize);

        public abstract void getHotTagsTopicList(String type);

        public abstract void topicSearch(String type, String param);

        public abstract void createTopic(TopicRequest topicRequest);

    }
}