package com.nbhysj.coupon.presenter;

import com.nbhysj.coupon.contract.MessageContract;

/**
 * @auther：hysj created on 2019/10/18
 * description：消息模块Presenter
 */
public class MessagePresenter extends MessageContract.Presenter {

    @Override
    public void onStart() {

    }
    @Override
    public void getUserFansList() {
        mRxManager.add(mModel.getUserFansList().subscribe(res -> mView.getUserFansListResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void userFollow(int userId) {
        mRxManager.add(mModel.userFollow(userId).subscribe(res -> mView.userFollowResult(res), e -> mView.showMsg(e.getMessage())));
    }
}
