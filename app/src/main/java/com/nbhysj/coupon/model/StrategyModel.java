package com.nbhysj.coupon.model;
import com.nbhysj.coupon.contract.StrategyContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.request.StrategyCommentRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.StrategyCommentListResponse;
import com.nbhysj.coupon.model.response.StrategyResponse;
import io.reactivex.Observable;

/**
 * created by hysj at 2019/09/21.
 * description：攻略列表model层
 */

public class StrategyModel implements StrategyContract.Model {

    @Override
    public Observable<BackResult<StrategyResponse>> findAllStrategy(int pageNo, int pageSize) {
        return Api.getInstance().apiService.findAllStrategy(pageNo, pageSize).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult> addStrategyCommentRequest(StrategyCommentRequest strategyCommentRequest) {
        return Api.getInstance().apiService.addStrategyCommentRequest(strategyCommentRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<StrategyCommentListResponse>> getStrategyCommentList(int articleId, int pageNo, int pageSize) {
        return Api.getInstance().apiService.getStrategyCommentList(articleId,pageNo,pageSize).compose(RxSchedulers.io_main());
    }
}


