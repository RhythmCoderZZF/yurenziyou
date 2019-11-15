package com.nbhysj.coupon.model;


import com.nbhysj.coupon.contract.MineContract;
import com.nbhysj.coupon.contract.OthersHomePageContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.FollowUserStatusResponse;
import com.nbhysj.coupon.model.response.UserPersonalHomePageResponse;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * created by hysj at 2019/10/16.
 * description :他人首页信息 Model层
 */

public class OthersHomePageModel implements OthersHomePageContract.Model {

    @Override
    public Observable<BackResult<UserPersonalHomePageResponse>> getOthersHomePageInfo(int userId) {
        return Api.getInstance().apiService.getOthershomePageInfo(userId).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<FollowUserStatusResponse>> userFollow(int userId) {
        return Api.getInstance().apiService.userFollow(userId).compose(RxSchedulers.io_main());
    }
}
