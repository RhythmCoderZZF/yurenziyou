package com.nbhysj.coupon.model;
import com.nbhysj.coupon.contract.ReportContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.request.PostReportRequest;
import com.nbhysj.coupon.model.request.UserReportRequest;
import com.nbhysj.coupon.model.response.BackResult;

import io.reactivex.Observable;

/**
 * created by hysj at 2019/11/25.
 * description:举报 Model层
 */

public class ReportModel implements ReportContract.Model {

    @Override
    public Observable<BackResult> userReport(UserReportRequest userReportRequest) {
        return Api.getInstance().apiService.userReport(userReportRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult> postReport(PostReportRequest postReportRequest) {
        return Api.getInstance().apiService.postReport(postReportRequest).compose(RxSchedulers.io_main());
    }
}
