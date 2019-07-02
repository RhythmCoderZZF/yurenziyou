package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.request.ThirdPartyLoginCreateUserBind;
import com.nbhysj.coupon.model.request.ThirdPartyLoginRequest;
import com.nbhysj.coupon.model.request.UpdateUserInfoRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.LoginResponse;
import com.nbhysj.coupon.model.response.ThirdPartyLoginResponse;
import com.nbhysj.coupon.model.response.ThirdPartyLoginStatusResponse;
import com.nbhysj.coupon.model.response.UserInfoResponse;

import io.reactivex.Observable;

/**
 * @auther：hysj created on 2019/03/22
 * description：账户管理模块
 */
public interface AccountManagementContract {

    /**
     * 数据和业务模型
     **/
    interface Model extends BaseModel {

        //列表显示第三方绑定状态
        Observable<BackResult<ThirdPartyLoginStatusResponse>> getThirdPartyLoginStatus(int userId);

        //获取用户信息
        Observable<BackResult<UserInfoResponse>> getUserInfo(int userId);

        //第三方绑定
        Observable<BackResult<LoginResponse>> thirdPartyLoginCreateUserBind(ThirdPartyLoginCreateUserBind thirdPartyLoginCreateUserBind);

        //三方绑定验证码发送
        Observable<BackResult> getRegisterverifyCode(String mobile);
    }

    interface View extends BaseView {

        //第三方绑定状态返回
        void getThirdPartyLoginStatusResult(BackResult<ThirdPartyLoginStatusResponse> res);

        void getUserInfoResult(BackResult<UserInfoResponse> res);

        //第三方绑定返回
        void getThirdPartyLoginCreateUserResult(BackResult<LoginResponse> res);

        void getRegisterVerifyCodeResult(BackResult res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getThirdPartyLoginStatus(int userId);

        //获取用户信息
        public abstract void getUserInfo(int userId);

        public abstract void getThirdPartyLoginCreateUser(ThirdPartyLoginCreateUserBind thirdPartyLoginCreateUserBind);

        public abstract void getRegisterVerifyCode(String mobile);
    }
}