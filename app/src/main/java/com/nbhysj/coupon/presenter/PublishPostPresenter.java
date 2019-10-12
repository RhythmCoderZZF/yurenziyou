package com.nbhysj.coupon.presenter;

import com.nbhysj.coupon.contract.PublishPostContract;
import com.nbhysj.coupon.contract.UserInfoContract;
import com.nbhysj.coupon.model.request.MerchantRequest;
import com.nbhysj.coupon.model.request.PublishPostRequest;
import com.nbhysj.coupon.model.request.TopicRequest;
import com.nbhysj.coupon.model.request.UpdateUserInfoRequest;

/**
 * @auther：hysj created on 2019/03/25
 * description：帖子发布Presenter
 */
public class PublishPostPresenter extends PublishPostContract.Presenter {

    @Override
    public void publishPost(PublishPostRequest publishPostRequest) {
        mRxManager.add(mModel.publishPost(publishPostRequest).subscribe(res -> mView.publishPostResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getMerchantList(String cityId, String mchName, int page, int pageSize) {
        mRxManager.add(mModel.getMerchantList(cityId, mchName, page, pageSize).subscribe(res -> mView.getMerchantListResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getHotTagsTopicList(String type) {
        mRxManager.add(mModel.getHotTagsTopicList(type).subscribe(res -> mView.getHotTagsTopicListResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void createTopic(TopicRequest topicRequest) {
        mRxManager.add(mModel.createTopic(topicRequest).subscribe(res -> mView.createTopicResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void topicSearch(String type, String param) {
        mRxManager.add(mModel.topicSearch(type, param).subscribe(res -> mView.topicSearchResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void onStart() {

    }
}
