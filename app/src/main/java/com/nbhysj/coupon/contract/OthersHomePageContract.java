package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.FollowUserStatusResponse;
import com.nbhysj.coupon.model.response.UserFansFollowResponse;
import com.nbhysj.coupon.model.response.UserPersonalHomePageResponse;

import io.reactivex.Observable;

/**
 * @auther：hysj created on 2019/10/19
 * description：获取他人信息
 */
public interface OthersHomePageContract {

    /**
     * 数据和业务模型
     **/
    interface Model extends BaseModel {

        //获取他人信息
        Observable<BackResult<UserPersonalHomePageResponse>> getOthersHomePageInfo(int userId);

        //关注
        Observable<BackResult<FollowUserStatusResponse>> userFollow(int userId);
    }

    interface View extends BaseView {

        void getOthersHomePageInfoResult(BackResult<UserPersonalHomePageResponse> res);

        void userFollowResult(BackResult<FollowUserStatusResponse> res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getOthersHomePageInfo(int userId);

        //关注
        public abstract void userFollow(int userId);
    }
}