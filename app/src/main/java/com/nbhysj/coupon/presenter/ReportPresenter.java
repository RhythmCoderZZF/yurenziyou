package com.nbhysj.coupon.presenter;
import com.nbhysj.coupon.contract.ReportContract;
import com.nbhysj.coupon.model.request.PostReportRequest;
import com.nbhysj.coupon.model.request.UserReportRequest;

/**
 * @auther：hysj created on 2019/11/25
 * description：举报Presenter
 */
public class ReportPresenter extends ReportContract.Presenter {

    @Override
    public void onStart() {

    }

    @Override
    public void postReport(PostReportRequest postReportRequest) {
        mRxManager.add(mModel.postReport(postReportRequest).subscribe(res -> mView.postReportResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void userReport(UserReportRequest userReportRequest) {
        mRxManager.add(mModel.userReport(userReportRequest).subscribe(res -> mView.userReportResult(res), e -> mView.showMsg(e.getMessage())));
    }
}
