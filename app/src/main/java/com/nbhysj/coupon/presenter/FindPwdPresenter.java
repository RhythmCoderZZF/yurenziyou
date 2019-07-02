package com.nbhysj.coupon.presenter;

import com.nbhysj.coupon.contract.FindPwdContract;
import com.nbhysj.coupon.model.request.FindPwdByEmailRequest;
import com.nbhysj.coupon.model.request.FindPwdByPhoneRequest;

/**
 * @auther：hysj created on 2019/03/05
 * description：登录Presenter
 */
public class FindPwdPresenter extends FindPwdContract.Presenter {


    @Override
    public void getFindPwdVerifyCode(String mobile) {
        mRxManager.add(mModel.getFindPwdVerifyCode(mobile).subscribe(res -> mView.getFindPwdVerifyCodeResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getSalt(String mobile) {
        mRxManager.add(mModel.getSalt(mobile).subscribe(res -> mView.getSaltResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void modifyPasswordByMobile(FindPwdByPhoneRequest findPwdRequest) {
        mRxManager.add(mModel.modifyPasswordByMobile(findPwdRequest).subscribe(res -> mView.modifyPasswordByMobileResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void updatePwdByEmailGetSalt(String email) {
        mRxManager.add(mModel.updatePwdByEmailGetSalt(email).subscribe(res -> mView.updatePwdByEmailGetSaltResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void sendEmail(String mobile) {
        mRxManager.add(mModel.sendEmail(mobile).subscribe(res -> mView.sendEmailResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void modifyPasswordByEmail(FindPwdByEmailRequest findPwdByEmailRequest) {
        mRxManager.add(mModel.modifyPasswordByEmail(findPwdByEmailRequest).subscribe(res -> mView.modifyPasswordByEmailResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void onStart() {

    }
}
