package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.request.PostOprateRequest;
import com.nbhysj.coupon.model.request.PostsCollectionRequest;
import com.nbhysj.coupon.model.request.PostsCommentRequest;
import com.nbhysj.coupon.model.request.QueryByTopicRequest;
import com.nbhysj.coupon.model.request.UpdateUserInfoRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.FavoritesCollectionResponse;
import com.nbhysj.coupon.model.response.FavoritesListResponse;
import com.nbhysj.coupon.model.response.FavoritesResponse;
import com.nbhysj.coupon.model.response.FollowUserStatusResponse;
import com.nbhysj.coupon.model.response.HomePageAllSearchResponse;
import com.nbhysj.coupon.model.response.HomePageResponse;
import com.nbhysj.coupon.model.response.HomePageTypeSearchResponse;
import com.nbhysj.coupon.model.response.PostInfoDetailResponse;
import com.nbhysj.coupon.model.response.PraiseOrCollectResponse;

import io.reactivex.Observable;
import retrofit2.http.Query;

/**
 * @auther：hysj created on 2019/04/03
 * description：首页模块
 */
public interface HomePageContract {

    /**
     * 数据和业务模型
     **/
    interface Model extends BaseModel {

        //获取首页信息
        Observable<BackResult<HomePageResponse>> getHomePageIndex();

        //获取首页信息根据子标签
        Observable<BackResult<HomePageResponse>> queryByTopic(QueryByTopicRequest queryByTopicRequest);

        //首页关注
        Observable<BackResult<HomePageResponse>> getHomeAttention(int page, int pageSize);

        //帖子详情
        Observable<BackResult<PostInfoDetailResponse>> getPostInfo(int id,String postKey,String latitude,String longitude);

        //帖子操作: 1点赞帖子 2点赞帖子的评论 postsType
        Observable<BackResult<PraiseOrCollectResponse>> postOprate(PostOprateRequest postOprateRequest);

        //帖子评论
        Observable<BackResult> postsCommentRequest(PostsCommentRequest postsCommentRequest);

        //首页搜索全部
        Observable<BackResult<HomePageAllSearchResponse>> getHomePageSearchAll(String queryType,String keyword);

        //首页搜索根据商户类型
        Observable<BackResult<HomePageTypeSearchResponse>> getHomePageSearchByType(String queryType, String keyword,int page, int pageSize);

        //帖子收藏
        Observable<BackResult<FavoritesCollectionResponse>> postsCollection(PostsCollectionRequest postsCollectionRequest);

        //专辑列表
        Observable<BackResult<FavoritesListResponse>> getFavoritesList(int page, int pageSize);

        //关注
        Observable<BackResult<FollowUserStatusResponse>> userFollow(int userId);
    }

    interface View extends BaseView {

        void getHomePageIndexResult(BackResult<HomePageResponse> res);

        void queryByTopicResult(BackResult<HomePageResponse> res);

        void getHomeAttentionResult(BackResult<HomePageResponse> res);

        void getPostInfoResult(BackResult<PostInfoDetailResponse> res);

        void postOprateResult(BackResult<PraiseOrCollectResponse> res);

        void postCollectionResult(BackResult<FavoritesCollectionResponse> res);

        //专辑列表
        void getFavoritesListResult(BackResult<FavoritesListResponse> res);

        void postsCommentResult(BackResult res);

        void getHomePageSearchAllResult(BackResult<HomePageAllSearchResponse> res);

        void getHomePageSearchByTypeResult(BackResult<HomePageTypeSearchResponse> res);

        void userFollowResult(BackResult<FollowUserStatusResponse> res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getHomePageIndex();

        public abstract void queryByTopic(QueryByTopicRequest queryByTopicRequest);

        //首页关注
        public abstract void getHomeAttention(int page, int pageSize);

        //帖子详情
        public abstract void getPostInfo(int id,String postKey,String latitude,String longitude);

        public abstract void postOprate(PostOprateRequest postOprateRequest);

        //帖子评论
        public abstract void postsCommentRequest(PostsCommentRequest postsCommentRequest);

        //首页搜索全部
        public abstract void getHomePageSearchAll(String queryType,String keyword);

        //首页根据类型搜索
        public abstract void getHomePageSearchByType(String queryType,String keyword,int page, int pageSize);

        //帖子收藏
        public abstract void postCollection(PostsCollectionRequest postsCollectionRequest);

        //专辑列表
        public abstract void getFavoritesList(int page, int pageSize);

        //关注
        public abstract void userFollow(int userId);
    }
}