package com.nbhysj.coupon.presenter;

import com.nbhysj.coupon.contract.RegisterContract;
import com.nbhysj.coupon.model.request.LoginRequest;
import com.nbhysj.coupon.model.request.RegisterUserRequest;

/**
 * @auther：hysj created on 2019/03/05
 * description：注册Presenter
 */
public class RegisterPresenter extends RegisterContract.Presenter {

    @Override
    public void getRegisterVerifyCode(String mobile) {
        mRxManager.add(mModel.getRegisterverifyCode(mobile).subscribe(res -> mView.getRegisterVerifyCodeResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void registerUser(RegisterUserRequest userRequest) {
        mRxManager.add(mModel.registerUser(userRequest).subscribe(res -> mView.registerUserResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getSalt(String mobile) {
        mRxManager.add(mModel.getSalt(mobile).subscribe(res -> mView.getSaltResult(res), e -> mView.showMsg(e.getMessage())));
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
    public void onStart() {

    }
}
