package com.nbhysj.coupon.model;


import com.nbhysj.coupon.contract.PublishPostContract;
import com.nbhysj.coupon.contract.UserInfoContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.request.MerchantRequest;
import com.nbhysj.coupon.model.request.PublishPostRequest;
import com.nbhysj.coupon.model.request.TopicRequest;
import com.nbhysj.coupon.model.request.UpdateUserInfoRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.HotTagsTopicBean;
import com.nbhysj.coupon.model.response.MerchantListResponse;
import com.nbhysj.coupon.model.response.TagTopicSearchResponse;
import com.nbhysj.coupon.model.response.ThirdPartyLoginStatusResponse;
import com.nbhysj.coupon.model.response.TopicResponse;
import com.nbhysj.coupon.model.response.UserInfoResponse;

import java.util.List;

import io.reactivex.Observable;

/**
 * created by hysj at 2019/03/25.
 * description :发布帖子 Model层
 */

public class PublishPostModel implements PublishPostContract.Model {

    @Override
    public Observable<BackResult> publishPost(PublishPostRequest publishPostRequest) {
        return Api.getInstance().apiService.createPost(publishPostRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<MerchantListResponse>> getMerchantList(String cityId, String mchName, int page, int pageSize) {
        return Api.getInstance().apiService.getMerchantList(cityId, mchName, page, pageSize).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<List<HotTagsTopicBean>>> getHotTagsTopicList(String type) {
        return Api.getInstance().apiService.getHotTagsTopicList(type).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<HotTagsTopicBean>> createTopic(TopicRequest topicRequest) {
        return Api.getInstance().apiService.topicCreate(topicRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<TagTopicSearchResponse>> topicSearch(String type, String param) {
        return Api.getInstance().apiService.topicSearch(type, param).compose(RxSchedulers.io_main());
    }
}
