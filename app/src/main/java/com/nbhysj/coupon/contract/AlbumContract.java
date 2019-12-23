package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.request.CreateFavoritesRequest;
import com.nbhysj.coupon.model.request.FavoritesBatchDeleteContentRequest;
import com.nbhysj.coupon.model.request.FavoritesBatchMoveContentRequest;
import com.nbhysj.coupon.model.request.FavoritesDeleteRequest;
import com.nbhysj.coupon.model.request.UpdateFavoritesRequest;
import com.nbhysj.coupon.model.response.AlbumFavoritesDetail;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CreateFavoritesResponse;
import com.nbhysj.coupon.model.response.FavoritesListResponse;
import com.nbhysj.coupon.model.response.FavoritesResponse;

import java.util.List;

import io.reactivex.Observable;

/**
 * @auther：hysj created on 2019/10/15
 * description：专辑模块
 */
public interface AlbumContract {

    /**
     * 数据和业务模型
     **/
    interface Model extends BaseModel {

        //创建专辑
        Observable<BackResult<CreateFavoritesResponse>> createFavorites(CreateFavoritesRequest createFavoritesRequest);

        //专辑收藏列表
        Observable<BackResult<FavoritesListResponse>> getFavoritesCollectionList(int page, int pageSize);

        //修改专辑
        Observable<BackResult> updateFavorites(UpdateFavoritesRequest updateFavoritesRequest);

        //查询专辑内容（这个接口暂无用到）
        Observable<BackResult<FavoritesResponse>> queryUserFavorites(int id, int page, int pageSize);

        //查询专辑内容
        Observable<BackResult<AlbumFavoritesDetail>> getAlbumFavoritesDetail(int favoritesId);

        //批量移动专辑内容到另一个专辑
        Observable<BackResult> albumFavoritesbatchMoveContent(FavoritesBatchMoveContentRequest favoritesBatchMoveContentRequest);  //collectionId:收藏的帖子id  专辑id

        //批量移动专辑内容到另一个专辑
        Observable<BackResult> albumFavoritesbatchDeleteContent(FavoritesBatchDeleteContentRequest favoritesBatchDeleteContentRequest);  //collectionId:收藏的帖子id  专辑id

        //专辑列表
        Observable<BackResult<FavoritesListResponse>> getFavoritesList(int page, int pageSize);

        //删除专辑
        Observable<BackResult> delFavoritesRequest(FavoritesDeleteRequest favoritesDeleteRequest);

    }

    interface View extends BaseView {

        void createFavoritesResult(BackResult<CreateFavoritesResponse> res);

        void getFavoritesCollectionListResult(BackResult<FavoritesListResponse> res);

        void updateFavoritesResult(BackResult res);

        void queryUserFavoritesResult(BackResult<FavoritesResponse> res);

        void getAlbumFavoritesDetailResult(BackResult<AlbumFavoritesDetail> res);

        void albumFavoritesbatchMoveContentResult(BackResult res);

        void albumFavoritesbatchDeleteContentResult(BackResult res);

        //专辑列表
        void getFavoritesListResult(BackResult<FavoritesListResponse> res);

        void delFavoritesRequest(BackResult res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void createFavorites(CreateFavoritesRequest createFavoritesRequest);

        public abstract void getFavoritesCollectionList(int page, int pageSize);

        public abstract void updateFavorites(UpdateFavoritesRequest updateFavoritesRequest);

        public abstract void queryUserFavorites(int id,int page, int pageSize);

        public abstract void getAlbumFavoritesDetail(int favoritesId);

        public abstract void albumFavoritesbatchMoveContent(FavoritesBatchMoveContentRequest favoritesBatchMoveContentRequest);//collectionId:收藏的帖子id  专辑id

        public abstract void albumFavoritesbatchDeleteContent(FavoritesBatchDeleteContentRequest favoritesBatchDeleteContentRequest);//collectionId:收藏的帖子id  专辑id

        //专辑列表
        public abstract void getFavoritesList(int page, int pageSize);

        //删除专辑
        public abstract void delFavoritesRequest(FavoritesDeleteRequest favoritesDeleteRequest);
    }
}