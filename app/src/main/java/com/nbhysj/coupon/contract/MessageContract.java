package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.FollowUserStatusResponse;
import com.nbhysj.coupon.model.response.UserFansFollowResponse;
import io.reactivex.Observable;

/**
 * @auther：hysj created on 2019/10/16
 * description：我的模块
 */
public interface MessageContract {

    /**
     * 数据和业务模型
     **/
    interface Model extends BaseModel {

        //获取粉丝列表
        Observable<BackResult<UserFansFollowResponse>> getUserFansList();

        //关注
        Observable<BackResult<FollowUserStatusResponse>> userFollow(int userId);
    }

    interface View extends BaseView {

        void getUserFansListResult(BackResult<UserFansFollowResponse> res);

        void userFollowResult(BackResult<FollowUserStatusResponse> res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getUserFansList();

        public abstract void userFollow(int userId);

    }
}