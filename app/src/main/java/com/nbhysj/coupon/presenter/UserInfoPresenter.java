package com.nbhysj.coupon.presenter;

import com.nbhysj.coupon.contract.UserInfoContract;
import com.nbhysj.coupon.model.request.UpdateUserInfoRequest;

/**
 * @auther：hysj created on 2019/03/05
 * description：用户模块Presenter
 */
public class UserInfoPresenter extends UserInfoContract.Presenter {

    @Override
    public void getUserInfo(int userId) {
        mRxManager.add(mModel.getUserInfo(userId).subscribe(res -> mView.getUserInfoResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void updateInformation(UpdateUserInfoRequest updateUserInfoRequest) {
        mRxManager.add(mModel.updateInformation(updateUserInfoRequest).subscribe(res -> mView.updateInformationResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void userLogout() {
        mRxManager.add(mModel.userLogout().subscribe(res -> mView.userLogoutResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getThirdPartyLoginStatus(int userId) {
        mRxManager.add(mModel.getThirdPartyLoginStatus(userId).subscribe(res -> mView.getThirdPartyLoginStatusResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getMyCard(int userId) {
        mRxManager.add(mModel.getMyCard(userId).subscribe(res -> mView.getMyCardResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void onStart() {

    }
}
