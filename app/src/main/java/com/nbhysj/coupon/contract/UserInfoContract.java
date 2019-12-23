package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.request.LoginRequest;
import com.nbhysj.coupon.model.request.RegisterUserRequest;
import com.nbhysj.coupon.model.request.UpdateUserInfoRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.LoginResponse;
import com.nbhysj.coupon.model.response.MyCardResponse;
import com.nbhysj.coupon.model.response.ThirdPartyLoginStatusResponse;
import com.nbhysj.coupon.model.response.UserInfoResponse;

import io.reactivex.Observable;

/**
 * @auther：hysj created on 2019/03/12
 * description：获取用户信息模块
 */
public interface UserInfoContract {

    /**
     * 数据和业务模型
     **/
    interface Model extends BaseModel {

        //获取用户信息
        Observable<BackResult<UserInfoResponse>> getUserInfo(int userId);

        //更新用户信息
        Observable<BackResult> updateInformation(UpdateUserInfoRequest updateUserInfoRequest);

        //用户退出
        Observable<BackResult> userLogout();

        //第三方绑定状态
        Observable<BackResult<ThirdPartyLoginStatusResponse>> getThirdPartyLoginStatus(int userId);

        //我的名片
        Observable<BackResult<MyCardResponse>> getMyCard(int userId);
    }

    interface View extends BaseView {

        void getUserInfoResult(BackResult<UserInfoResponse> res);

        void updateInformationResult(BackResult res);

        void userLogoutResult(BackResult res);

        //第三方绑定状态返回
        void getThirdPartyLoginStatusResult(BackResult<ThirdPartyLoginStatusResponse> res);

        void getMyCardResult(BackResult<MyCardResponse> res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getUserInfo(int userId);

        public abstract void updateInformation(UpdateUserInfoRequest updateUserInfoRequest);

        public abstract void userLogout();

        public abstract void getThirdPartyLoginStatus(int userId);

        public abstract void getMyCard(int userId);
    }
}