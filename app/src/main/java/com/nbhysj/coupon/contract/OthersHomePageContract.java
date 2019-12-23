package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.request.PostDeleteRequest;
import com.nbhysj.coupon.model.request.PostReportRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.FavoritesListResponse;
import com.nbhysj.coupon.model.response.FollowUserStatusResponse;
import com.nbhysj.coupon.model.response.MinePostZanListResponse;
import com.nbhysj.coupon.model.response.UserFansFollowResponse;
import com.nbhysj.coupon.model.response.UserPersonalHomePageResponse;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Query;

/**
 * @auther：hysj created on 2019/10/19
 * description：获取他人信息
 */
public interface OthersHomePageContract {

    /**
     * 数据和业务模型
     **/
    interface Model extends BaseModel {

        //获取他人信息
        Observable<BackResult<UserPersonalHomePageResponse>> getOthersHomePageInfo(int userId);

        //关注
        Observable<BackResult<FollowUserStatusResponse>> userFollow(int userId);

        //其他用户帖子分享列表
        Observable<ResponseBody> getOthersPostShareList(int userId);

        //其他用户收藏全部列表
        Observable<ResponseBody> getOtherCollectionAllList(int userId);

        //其他用户赞过列表
        Observable<BackResult<MinePostZanListResponse>> getOtherBeforeZan(int userId);

        //帖子删除
        Observable<BackResult> deletePost(PostDeleteRequest postDeleteRequest);

        //用户收藏的专辑列表
        Observable<BackResult> getOtherFindFavorites(String type,int page,int pageSize,int userId);
    }

    interface View extends BaseView {

        void getOthersHomePageInfoResult(BackResult<UserPersonalHomePageResponse> res);

        void userFollowResult(BackResult<FollowUserStatusResponse> res);

        void getOtherCollectionAllResult(ResponseBody res);

        void getOthersPostShareListResult(ResponseBody res);

        void getOtherBeforeZanResult(BackResult<MinePostZanListResponse> res);

        void deletePostResult(BackResult res);

        void getOtherFindFavoritesListResult(BackResult<FavoritesListResponse> res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getOthersHomePageInfo(int userId);

        //关注
        public abstract void userFollow(int userId);

        //其他用户帖子分享列表
        public abstract void getOthersPostShareList(int userId);

        //其他用户收藏全部列表
        public abstract void getOtherCollectionAllList(int userId);

        //其他用户赞过列表
        public abstract void getOtherBeforeZan(int userId);

        public abstract void deletePost(PostDeleteRequest postDeleteRequest);

        public abstract void getOtherFindFavoritesList(String type,int page,int pageSize,int userId);
    }
}