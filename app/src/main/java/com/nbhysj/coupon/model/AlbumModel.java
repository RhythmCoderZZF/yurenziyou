package com.nbhysj.coupon.model;

import com.nbhysj.coupon.contract.AlbumContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.request.CreateFavoritesRequest;
import com.nbhysj.coupon.model.request.FavoritesBatchDeleteContentRequest;
import com.nbhysj.coupon.model.request.FavoritesBatchMoveContentRequest;
import com.nbhysj.coupon.model.request.UpdateFavoritesRequest;
import com.nbhysj.coupon.model.response.AlbumFavoritesDetail;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CreateFavoritesResponse;
import com.nbhysj.coupon.model.response.FavoritesListResponse;
import com.nbhysj.coupon.model.response.FavoritesResponse;

import java.util.List;

import io.reactivex.Observable;

/**
 * created by hysj at 2019/10/15.
 * description：专辑 model层
 */

public class AlbumModel implements AlbumContract.Model {
    @Override
    public Observable<BackResult<CreateFavoritesResponse>> createFavorites(CreateFavoritesRequest createFavoritesRequest) {
        return Api.getInstance().apiService.createFavorites(createFavoritesRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<FavoritesListResponse>> getFavoritesCollectionList(int page, int pageSize) {
        return Api.getInstance().apiService.getFavoritesCollectionList(page, pageSize).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult> updateFavorites(UpdateFavoritesRequest updateFavoritesRequest) {
        return Api.getInstance().apiService.updateFavorites(updateFavoritesRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<FavoritesResponse>> queryUserFavorites(int id, int page, int pageSize) {
        return Api.getInstance().apiService.queryUserFavorites(id, page, pageSize).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<AlbumFavoritesDetail>> getAlbumFavoritesDetail(int favoritesId) {
        return Api.getInstance().apiService.getAlbumFavoritesDetail(favoritesId).compose(RxSchedulers.io_main());
    }


    @Override
    public Observable<BackResult> albumFavoritesbatchMoveContent(FavoritesBatchMoveContentRequest favoritesBatchMoveContentRequest) {
        return Api.getInstance().apiService.albumFavoritesbatchMoveContent(favoritesBatchMoveContentRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult> albumFavoritesbatchDeleteContent(FavoritesBatchDeleteContentRequest favoritesBatchDeleteContentRequest) {
        return Api.getInstance().apiService.albumFavoritesDeleteContent(favoritesBatchDeleteContentRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<FavoritesListResponse>> getFavoritesList(int page, int pageSize) {
        return Api.getInstance().apiService.getUserFindFavoritesList(page,pageSize).compose(RxSchedulers.io_main());
    }
}
