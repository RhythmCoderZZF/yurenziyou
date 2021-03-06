package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.request.LoginRequest;
import com.nbhysj.coupon.model.request.RegisterUserRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.LoginResponse;
import com.nbhysj.coupon.model.response.UserInfoResponse;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * @auther：hysj created on 2019/03/05
 * description：注册模块
 */
public interface RegisterContract {

    /**
     * 数据和业务模型
     **/
    interface Model extends BaseModel {

        Observable<BackResult> getRegisterverifyCode(String mobile);

        Observable<BackResult> registerUser(RegisterUserRequest registerUserRequest);

        Observable<BackResult<Object>> getSalt(String mobile);

        Observable<BackResult<LoginResponse>> login(LoginRequest loginRequest);

        //获取用户信息
        Observable<BackResult<UserInfoResponse>> getUserInfo(int userId);

    }

    interface View extends BaseView {

        void getRegisterVerifyCodeResult(BackResult res);

        void registerUserResult(BackResult res);

        void getSaltResult(BackResult<Object> res);

        void loginResult(BackResult<LoginResponse> res);

        void getUserInfoResult(BackResult<UserInfoResponse> res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getRegisterVerifyCode(String mobile);

        public abstract void registerUser(RegisterUserRequest registerUserRequest);

        public abstract void getSalt(String mobile);

        public abstract void login(LoginRequest loginRequest);

        public abstract void getUserInfo(int userId);
    }
}