package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.request.PostReportRequest;
import com.nbhysj.coupon.model.request.UserReportRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.FollowUserStatusResponse;
import com.nbhysj.coupon.model.response.UserPersonalHomePageResponse;

import io.reactivex.Observable;

/**
 * @auther：hysj created on 2019/11/25
 * description：举报
 */
public interface ReportContract {

    /**
     * 数据和业务模型
     **/
    interface Model extends BaseModel {

        //帖子举报
        Observable<BackResult> postReport(PostReportRequest postReportRequest);

        //用户举报
        Observable<BackResult> userReport(UserReportRequest userReportRequest);

    }

    interface View extends BaseView {

        void postReportResult(BackResult res);

        void userReportResult(BackResult res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        //帖子举报
        public abstract void postReport(PostReportRequest postReportRequest);

        //用户举报
        public abstract void userReport(UserReportRequest userReportRequest);
    }
}