package com.nbhysj.coupon.presenter;

import com.nbhysj.coupon.contract.AlbumContract;
import com.nbhysj.coupon.contract.CommentContract;
import com.nbhysj.coupon.model.request.CreateFavoritesRequest;
import com.nbhysj.coupon.model.request.FavoritesBatchDeleteContentRequest;
import com.nbhysj.coupon.model.request.FavoritesBatchMoveContentRequest;
import com.nbhysj.coupon.model.request.FavoritesDeleteRequest;
import com.nbhysj.coupon.model.request.PostOprateRequest;
import com.nbhysj.coupon.model.request.PostsCommentRequest;
import com.nbhysj.coupon.model.request.UpdateFavoritesRequest;

/**
 * @auther：hysj created on 2019/09/24.
 * description：专辑Presenter
 */
public class AlbumPresenter extends AlbumContract.Presenter {

    @Override
    public void createFavorites(CreateFavoritesRequest createFavoritesRequest) {
        mRxManager.add(mModel.createFavorites(createFavoritesRequest).subscribe(res -> mView.createFavoritesResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getFavoritesCollectionList(int page, int pageSize) {
        mRxManager.add(mModel.getFavoritesCollectionList(page,pageSize).subscribe(res -> mView.getFavoritesCollectionListResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void updateFavorites(UpdateFavoritesRequest updateFavoritesRequest) {
        mRxManager.add(mModel.updateFavorites(updateFavoritesRequest).subscribe(res -> mView.updateFavoritesResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void queryUserFavorites(int id, int page, int pageSize) {
        mRxManager.add(mModel.queryUserFavorites(id,page,pageSize).subscribe(res -> mView.queryUserFavoritesResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getAlbumFavoritesDetail(int favoritesId) {
        mRxManager.add(mModel.getAlbumFavoritesDetail(favoritesId).subscribe(res -> mView.getAlbumFavoritesDetailResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void albumFavoritesbatchMoveContent(FavoritesBatchMoveContentRequest favoritesBatchMoveContentRequest) {
        mRxManager.add(mModel.albumFavoritesbatchMoveContent(favoritesBatchMoveContentRequest).subscribe(res -> mView.albumFavoritesbatchMoveContentResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void albumFavoritesbatchDeleteContent(FavoritesBatchDeleteContentRequest favoritesBatchDeleteContentRequest) {
        mRxManager.add(mModel.albumFavoritesbatchDeleteContent(favoritesBatchDeleteContentRequest).subscribe(res -> mView.albumFavoritesbatchDeleteContentResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getFavoritesList(int page, int pageSize) {
        mRxManager.add(mModel.getFavoritesList(page,pageSize).subscribe(res -> mView.getFavoritesListResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void delFavoritesRequest(FavoritesDeleteRequest favoritesDeleteRequest) {
        mRxManager.add(mModel.delFavoritesRequest(favoritesDeleteRequest).subscribe(res -> mView.delFavoritesRequest(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void onStart() {

    }
}
