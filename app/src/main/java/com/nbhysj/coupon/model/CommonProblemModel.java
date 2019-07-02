package com.nbhysj.coupon.model;


import com.nbhysj.coupon.contract.CommonProblemContract;
import com.nbhysj.coupon.contract.PublishPostContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.request.PublishPostRequest;
import com.nbhysj.coupon.model.response.ArticleWithCateResponse;
import com.nbhysj.coupon.model.response.BackResult;

import io.reactivex.Observable;

/**
 * created by hysj at 2019/03/25.
 * description :发布帖子 Model层
 */

public class CommonProblemModel implements CommonProblemContract.Model {

    @Override
    public Observable<BackResult<ArticleWithCateResponse>> getArticleWithCate() {
        return Api.getInstance().apiService.getArticleWithCate().compose(RxSchedulers.io_main());
    }
}
