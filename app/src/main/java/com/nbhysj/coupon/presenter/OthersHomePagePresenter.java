package com.nbhysj.coupon.presenter;
import com.nbhysj.coupon.contract.OthersHomePageContract;
import com.nbhysj.coupon.model.request.PostDeleteRequest;
import com.nbhysj.coupon.model.request.PostReportRequest;

/**
 * @auther：hysj created on 2019/10/19
 * description：他人首页信息Presenter
 */
public class OthersHomePagePresenter extends OthersHomePageContract.Presenter {

    @Override
    public void onStart() {

    }

    @Override
    public void getOthersHomePageInfo(int userId) {

        mRxManager.add(mModel.getOthersHomePageInfo(userId).subscribe(res -> mView.getOthersHomePageInfoResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void userFollow(int userId) {
        mRxManager.add(mModel.userFollow(userId).subscribe(res -> mView.userFollowResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getOthersPostShareList(int userId) {
        mRxManager.add(mModel.getOthersPostShareList(userId).subscribe(res -> mView.getOthersPostShareListResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getOtherCollectionAllList(int userId) {
        mRxManager.add(mModel.getOtherCollectionAllList(userId).subscribe(res -> mView.getOtherCollectionAllResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getOtherBeforeZan(int userId) {
        mRxManager.add(mModel.getOtherBeforeZan(userId).subscribe(res -> mView.getOtherBeforeZanResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void deletePost(PostDeleteRequest postDeleteRequest) {
        mRxManager.add(mModel.deletePost(postDeleteRequest).subscribe(res -> mView.deletePostResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getOtherFindFavoritesList(String type, int page, int pageSize, int userId) {
        mRxManager.add(mModel.getOtherFindFavorites(type,page,pageSize,userId).subscribe(res -> mView.getOtherFindFavoritesListResult(res), e -> mView.showMsg(e.getMessage())));
    }

}
