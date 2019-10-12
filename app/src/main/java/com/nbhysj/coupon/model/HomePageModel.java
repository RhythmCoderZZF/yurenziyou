package com.nbhysj.coupon.model;


import com.nbhysj.coupon.contract.HomePageContract;
import com.nbhysj.coupon.contract.UserInfoContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.request.PostOprateRequest;
import com.nbhysj.coupon.model.request.PostsCommentRequest;
import com.nbhysj.coupon.model.request.QueryByTopicRequest;
import com.nbhysj.coupon.model.request.UpdateUserInfoRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.HomePageAllSearchResponse;
import com.nbhysj.coupon.model.response.HomePageResponse;
import com.nbhysj.coupon.model.response.HomePageSubTopicTagReponse;
import com.nbhysj.coupon.model.response.HomePageTypeSearchResponse;
import com.nbhysj.coupon.model.response.PostInfoDetailResponse;
import com.nbhysj.coupon.model.response.PraiseOrCollectResponse;
import com.nbhysj.coupon.model.response.ThirdPartyLoginStatusResponse;
import com.nbhysj.coupon.model.response.UserInfoResponse;

import io.reactivex.Observable;

/**
 * created by hysj at 2019/04/03.
 * description :首页Model层
 */

public class HomePageModel implements HomePageContract.Model {

    @Override
    public Observable<BackResult<HomePageResponse>> getHomePageIndex() {
        return Api.getInstance().apiService.getHomePageIndex().compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<HomePageResponse>> queryByTopic(QueryByTopicRequest queryByTopicRequest) {
        return Api.getInstance().apiService.queryByTopic(queryByTopicRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<HomePageResponse>> getHomeAttention(int page, int pageSize) {
        return Api.getInstance().apiService.getHomeAttention(page, pageSize).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<PostInfoDetailResponse>> getPostInfo(int id, String postKey, String longitude, String latitude) {
        return Api.getInstance().apiService.getPostInfo(id, postKey,latitude,longitude).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<PraiseOrCollectResponse>> postOprate(PostOprateRequest postOprateRequest) {
        return Api.getInstance().apiService.postOprate(postOprateRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult> postsCommentRequest(PostsCommentRequest postsCommentRequest) {
        return Api.getInstance().apiService.postsCommentRequest(postsCommentRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<HomePageAllSearchResponse>> getHomePageSearchAll(String queryType, String keyword) {
            return Api.getInstance().apiService.getHomePageSearchALL(queryType,keyword).compose(RxSchedulers.io_main());
        }

    @Override
    public Observable<BackResult<HomePageTypeSearchResponse>> getHomePageSearchByType(String queryType, String keyword) {
        return Api.getInstance().apiService.getHomePageSearchByType(queryType,keyword).compose(RxSchedulers.io_main());
    }

}
