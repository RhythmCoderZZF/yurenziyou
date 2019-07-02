package com.nbhysj.coupon.presenter;

import com.nbhysj.coupon.contract.LoginContract;
import com.nbhysj.coupon.model.request.LoginRequest;
import com.nbhysj.coupon.model.request.ThirdPartyLoginCreateUserBind;
import com.nbhysj.coupon.model.request.ThirdPartyLoginRequest;

/**
 * @auther：hysj created on 2019/03/05
 * description：登录Presenter
 */
public class LoginPresenter extends LoginContract.Presenter {

    @Override
    public void getLoginVerifyCode(String mobile) {
        mRxManager.add(mModel.getLoginVerifyCode(mobile).subscribe(res -> mView.getLoginVerifyCodeResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getLoginSalt(String mobile) {
        mRxManager.add(mModel.getLoginSalt(mobile).subscribe(res -> mView.getLoginSaltResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void login(LoginRequest loginRequest) {
        mRxManager.add(mModel.login(loginRequest).subscribe(res -> mView.loginResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getUserInfo(int userId) {
        mRxManager.add(mModel.getUserInfo(userId).subscribe(res -> mView.getUserInfoResult(res), e -> mView.showMsg(e.getMessage())));
    }


    @Override
    public void getThirdPartyLoginStatus(int userId) {
        mRxManager.add(mModel.getThirdPartyLoginStatus(userId).subscribe(res -> mView.getThirdPartyLoginStatusResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void thirdPartyLogin(ThirdPartyLoginRequest thirdPartyLoginRequest) {
        mRxManager.add(mModel.thirdPartyLogin(thirdPartyLoginRequest).subscribe(res -> mView.thirdPartyLoginResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void onStart() {

    }
}
