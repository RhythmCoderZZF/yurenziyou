package com.nbhysj.coupon.presenter;

import com.nbhysj.coupon.contract.MessageContract;

/**
 * @auther：hysj created on 2019/10/18
 * description：消息模块Presenter
 */
public class MessagePresenter extends MessageContract.Presenter {

    @Override
    public void onStart() {

    }
    @Override
    public void getUserFansList() {
        mRxManager.add(mModel.getUserFansList().subscribe(res -> mView.getUserFansListResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void userFollow(int userId) {
        mRxManager.add(mModel.userFollow(userId).subscribe(res -> mView.userFollowResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getAttentionInit() {
        mRxManager.add(mModel.getAttentionInit().subscribe(res -> mView.getAttentionInitResult(res), e -> mView.showMsg(e.getMessage())));
    }
    @Override
    public void getUserFollow(int pageNo, int pageSize) {
        mRxManager.add(mModel.getUserFollow(pageNo,pageSize).subscribe(res -> mView.getUserFollowResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getMessageList() {
        mRxManager.add(mModel.getMessageList().subscribe(res -> mView.getMessageListResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getZanAndCollectionMsg(int pageNo, int pageSize) {
        mRxManager.add(mModel.getZanAndCollectionMsg(pageNo,pageSize).subscribe(res -> mView.getZanAndCollectionMsgResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getPostsCommentAndAnswer(int pageNo, int pageSize) {
        mRxManager.add(mModel.getPostsCommentAndAnswer(pageNo,pageSize).subscribe(res -> mView.getPostsCommentAndAnswerResult(res), e -> mView.showMsg(e.getMessage())));
    }
}
