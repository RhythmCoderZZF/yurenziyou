package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.request.FindPwdByEmailRequest;
import com.nbhysj.coupon.model.request.FindPwdByPhoneRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.LoginResponse;

import io.reactivex.Observable;

/**
 * @auther：hysj created on 2019/03/05
 * description：找回密码模块
 */
public interface FindPwdContract {

    /**
     * 数据和业务模型
     **/
    interface Model extends BaseModel {

        Observable<BackResult> getFindPwdVerifyCode(String mobile);

        Observable<BackResult<Object>> getSalt(String mobile);

        //通过手机号修改密码
        Observable<BackResult> modifyPasswordByMobile(FindPwdByPhoneRequest findPwdByPhoneRequest);

        Observable<BackResult<Object>> updatePwdByEmailGetSalt(String email);

        Observable<BackResult> sendEmail(String mobile);

        //通过邮箱修改密码
        Observable<BackResult> modifyPasswordByEmail(FindPwdByEmailRequest findPwdByEmailRequest);
    }

    interface View extends BaseView {

        void getFindPwdVerifyCodeResult(BackResult res);

        void getSaltResult(BackResult<Object> res);

        void modifyPasswordByMobileResult(BackResult<LoginResponse> res);

        void updatePwdByEmailGetSaltResult(BackResult<Object> res);

        void sendEmailResult(BackResult res);

        void modifyPasswordByEmailResult(BackResult res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getFindPwdVerifyCode(String mobile);

        public abstract void getSalt(String mobile);

        public abstract void modifyPasswordByMobile(FindPwdByPhoneRequest findPwdByPhoneRequest);

        //通过邮箱修改密码获取盐
        public abstract void updatePwdByEmailGetSalt(String email);

        //发送邮箱
        public abstract void sendEmail(String mobile);

        //通过邮箱修改密码
        public abstract void modifyPasswordByEmail(FindPwdByEmailRequest findPwdByEmailRequest);
    }
}