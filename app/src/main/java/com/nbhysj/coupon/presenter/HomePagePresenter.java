package com.nbhysj.coupon.presenter;

import com.nbhysj.coupon.contract.HomePageContract;
import com.nbhysj.coupon.contract.UserInfoContract;
import com.nbhysj.coupon.model.request.PostOprateRequest;
import com.nbhysj.coupon.model.request.PostsCollectionRequest;
import com.nbhysj.coupon.model.request.PostsCommentRequest;
import com.nbhysj.coupon.model.request.QueryByTopicRequest;
import com.nbhysj.coupon.model.request.UpdateUserInfoRequest;

/**
 * @auther：hysj created on 2019/04/03
 * description：用户模块Presenter
 */
public class HomePagePresenter extends HomePageContract.Presenter {

    @Override
    public void getHomePageIndex() {
        mRxManager.add(mModel.getHomePageIndex().subscribe(res -> mView.getHomePageIndexResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void queryByTopic(QueryByTopicRequest queryByTopicRequest) {
        mRxManager.add(mModel.queryByTopic(queryByTopicRequest).subscribe(res -> mView.queryByTopicResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getHomeAttention(int page, int pageSize) {
        mRxManager.add(mModel.getHomeAttention(page, pageSize).subscribe(res -> mView.getHomeAttentionResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getPostInfo(int id, String postKey, String latitude, String longitude) {
        mRxManager.add(mModel.getPostInfo(id, postKey,latitude,longitude).subscribe(res -> mView.getPostInfoResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void postOprate(PostOprateRequest postOprateRequest) {
        mRxManager.add(mModel.postOprate(postOprateRequest).subscribe(res -> mView.postOprateResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void postsCommentRequest(PostsCommentRequest postsCommentRequest) {
        mRxManager.add(mModel.postsCommentRequest(postsCommentRequest).subscribe(res -> mView.postsCommentResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getHomePageSearchAll(String queryType, String keyword) {
        mRxManager.add(mModel.getHomePageSearchAll(queryType,keyword).subscribe(res -> mView.getHomePageSearchAllResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getHomePageSearchByType(String queryType, String keyword,int pageNo,int pageSize) {
        mRxManager.add(mModel.getHomePageSearchByType(queryType,keyword,pageNo,pageSize).subscribe(res -> mView.getHomePageSearchByTypeResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void postCollection(PostsCollectionRequest postsCollectionRequest) {
        mRxManager.add(mModel.postsCollection(postsCollectionRequest).subscribe(res -> mView.postCollectionResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getFavoritesList(int page, int pageSize) {
        mRxManager.add(mModel.getFavoritesList(page,pageSize).subscribe(res -> mView.getFavoritesListResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void userFollow(int userId) {

        mRxManager.add(mModel.userFollow(userId).subscribe(res -> mView.userFollowResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void onStart() {

    }
}
