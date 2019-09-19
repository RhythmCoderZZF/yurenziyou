package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.request.LoginRequest;
import com.nbhysj.coupon.model.request.RegisterUserRequest;
import com.nbhysj.coupon.model.request.ThirdPartyLoginCreateUserBind;
import com.nbhysj.coupon.model.request.ThirdPartyLoginRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.LoginResponse;
import com.nbhysj.coupon.model.response.ThirdPartyLoginResponse;
import com.nbhysj.coupon.model.response.ThirdPartyLoginStatusResponse;
import com.nbhysj.coupon.model.response.UserInfoResponse;

import io.reactivex.Observable;

/**
 * @auther：hysj created on 2019/03/05
 * description：登录模块
 */
public interface LoginContract {

    /**
     * 数据和业务模型
     **/
    interface Model extends BaseModel {

        Observable<BackResult> getLoginVerifyCode(String mobile);

        Observable<BackResult> getLoginSalt(String username);

        Observable<BackResult<LoginResponse>> login(LoginRequest loginRequest);

        //获取用户信息
        Observable<BackResult<UserInfoResponse>> getUserInfo(int userId);

        //列表显示第三方绑定状态
        Observable<BackResult<ThirdPartyLoginStatusResponse>> getThirdPartyLoginStatus(int userId);

        //第三方绑定判断(1.未绑定跳转到绑定页面 2.已绑定直接进入)
        Observable<BackResult<LoginResponse>> thirdPartyLogin(ThirdPartyLoginRequest thirdPartyLoginRequest);

    }

    interface View extends BaseView {

        void getLoginVerifyCodeResult(BackResult res);

        void getLoginSaltResult(BackResult res);

        void loginResult(BackResult<LoginResponse> res);

        void getUserInfoResult(BackResult<UserInfoResponse> res);

        //第三方绑定状态返回
        void getThirdPartyLoginStatusResult(BackResult<ThirdPartyLoginStatusResponse> res);

        //第三方绑定
        void thirdPartyLoginResult(BackResult<LoginResponse> res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getLoginVerifyCode(String mobile);

        public abstract void getLoginSalt(String username);

        public abstract void login(LoginRequest loginRequest);

        public abstract void getUserInfo(int userId);

        public abstract void getThirdPartyLoginStatus(int userId);

        public abstract void thirdPartyLogin(ThirdPartyLoginRequest thirdPartyLoginRequest);

    }
}