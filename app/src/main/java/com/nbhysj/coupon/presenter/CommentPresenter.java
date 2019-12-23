package com.nbhysj.coupon.presenter;

import com.nbhysj.coupon.contract.CommentContract;
import com.nbhysj.coupon.model.request.PostOprateRequest;
import com.nbhysj.coupon.model.request.PostsCommentRequest;

import java.util.Map;

/**
 * @auther：hysj created on 2019/09/24.
 * description：评论Presenter
 */
public class CommentPresenter extends CommentContract.Presenter {

    @Override
    public void postsCommentRequest(PostsCommentRequest postsCommentRequest) {
        mRxManager.add(mModel.postsCommentRequest(postsCommentRequest).subscribe(res -> mView.postsCommentResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getMchCommentList(Map<String, Object> map) {
        mRxManager.add(mModel.getMchCommentList(map).subscribe(res -> mView.getMchCommentListResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getAllPostsCommentListByArticleId(int articleId,int pageNo, int pageSize) {
        mRxManager.add(mModel.getAllPostsCommentListByArticleId(articleId,pageNo,pageSize).subscribe(res -> mView.getAllPostsCommentListByArticleIdResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void postOprate(PostOprateRequest postOprateRequest) {
        mRxManager.add(mModel.postOprate(postOprateRequest).subscribe(res -> mView.postOprateResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void onStart() {

    }
}
