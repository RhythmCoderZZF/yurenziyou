package com.nbhysj.coupon.presenter;

import com.nbhysj.coupon.contract.AccountManagementContract;
import com.nbhysj.coupon.model.request.ThirdPartyLoginCreateUserBind;
import com.nbhysj.coupon.model.request.ThirdPartyLoginRequest;

/**
 * @auther：hysj created on 2019/03/05
 * description：账户管理模块Presenter
 */
public class AccountmanagementPresenter extends AccountManagementContract.Presenter {

    @Override
    public void getThirdPartyLoginStatus(int userId) {
        mRxManager.add(mModel.getThirdPartyLoginStatus(userId).subscribe(res -> mView.getThirdPartyLoginStatusResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getThirdPartyLoginCreateUser(ThirdPartyLoginCreateUserBind thirdPartyLoginCreateUserBind) {
        mRxManager.add(mModel.thirdPartyLoginCreateUserBind(thirdPartyLoginCreateUserBind).subscribe(res -> mView.getThirdPartyLoginCreateUserResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getRegisterVerifyCode(String mobile) {
        mRxManager.add(mModel.getRegisterverifyCode(mobile).subscribe(res -> mView.getRegisterVerifyCodeResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getUserInfo(int userId) {
        mRxManager.add(mModel.getUserInfo(userId).subscribe(res -> mView.getUserInfoResult(res), e -> mView.showMsg(e.getMessage())));
    }


    @Override
    public void onStart() {

    }
}
