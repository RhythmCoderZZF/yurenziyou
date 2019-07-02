package com.nbhysj.coupon.presenter;

import com.nbhysj.coupon.contract.HomePageContract;
import com.nbhysj.coupon.contract.UserInfoContract;
import com.nbhysj.coupon.model.request.QueryByTopicRequest;
import com.nbhysj.coupon.model.request.UpdateUserInfoRequest;

/**
 * @auther：hysj created on 2019/04/03
 * description：用户模块Presenter
 */
public class HomePagePresenter extends HomePageContract.Presenter {

    @Override
    public void getHomePageIndex() {
        mRxManager.add(mModel.getHomePageIndex().subscribe(res -> mView.getHomePageIndexResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void queryByTopic(QueryByTopicRequest queryByTopicRequest) {
        mRxManager.add(mModel.queryByTopic(queryByTopicRequest).subscribe(res -> mView.queryByTopicResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getHomeAttention(int page, int pageSize) {
        mRxManager.add(mModel.getHomeAttention(page, pageSize).subscribe(res -> mView.getHomeAttentionResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void onStart() {

    }
}
