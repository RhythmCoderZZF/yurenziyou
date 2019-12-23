package com.nbhysj.coupon.presenter;

import com.nbhysj.coupon.contract.MineContract;
import com.nbhysj.coupon.model.request.CollectionBatchMchDeleteRequest;
import com.nbhysj.coupon.model.request.CollectionBatchPostsDeleteRequest;
import com.nbhysj.coupon.model.request.PostDeleteRequest;

/**
 * @auther：hysj created on 2019/04/03
 * description：用户模块Presenter
 */
public class MinePresenter extends MineContract.Presenter {

    @Override
    public void onStart() {

    }

    @Override
    public void getMyPostShareList() {
        mRxManager.add(mModel.getMyPostShareList().subscribe(res -> mView.getMyPostShareListResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getMinePostZanList(int pageNo,int pageSize) {
        mRxManager.add(mModel.getMinePostZanList(pageNo,pageSize).subscribe(res -> mView.getMinePostZanListResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getMineCollectionAllList() {
        mRxManager.add(mModel.getMineCollectionAllList().subscribe(res -> mView.getMineCollectionAllListResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getMineCollectionDetail(String type,int pageNo,int pageSize) {
        mRxManager.add(mModel.getMineCollectionDetail(type,pageNo,pageSize).subscribe(res -> mView.getMineCollectionDetailResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void collectionMchBatchDeleteContentRequest(CollectionBatchMchDeleteRequest collectionBatchDeleteRequest) {
        mRxManager.add(mModel.collectionBatchDeleteContentRequest(collectionBatchDeleteRequest).subscribe(res -> mView.collectionMchBatchDeleteContentResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void collectionPostsBatchDeleteRequest(CollectionBatchPostsDeleteRequest collectionBatchDeleteRequest) {
        mRxManager.add(mModel.collectionPostsBatchDeleteRequest(collectionBatchDeleteRequest).subscribe(res -> mView.collectionPostsBatchDeleteResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getZanMsgList(int pageNo, int pageSize) {
        mRxManager.add(mModel.getZanMsgList(pageNo,pageSize).subscribe(res -> mView.getZanMsgListResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getCollectionMsgList(int pageNo, int pageSize) {
        mRxManager.add(mModel.getCollectionMsgList(pageNo,pageSize).subscribe(res -> mView.getCollectionMsgListResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void deletePost(PostDeleteRequest postDeleteRequest) {
        mRxManager.add(mModel.deletePost(postDeleteRequest).subscribe(res -> mView.deletePostResult(res), e -> mView.showMsg(e.getMessage())));
    }
}
