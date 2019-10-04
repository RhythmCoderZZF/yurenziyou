package com.nbhysj.coupon.model;

import com.nbhysj.coupon.contract.GroupMchContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.GroupMchDetailsResponse;
import com.nbhysj.coupon.model.response.GroupMchResponse;
import com.nbhysj.coupon.model.response.MchDetailsResponse;

import io.reactivex.Observable;

/**
 * created by hysj at 2019/09/22.
 * description:组合列表model层
 */

public class GroupMchModel implements GroupMchContract.Model {

    @Override
    public Observable<BackResult<GroupMchResponse>> getGroupMchHomePage() {
        return Api.getInstance().apiService.getGroupHomePage().compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<GroupMchDetailsResponse>> getGroupMchDetail(int packageId) {
        return Api.getInstance().apiService.getGroupMchDetail(packageId).compose(RxSchedulers.io_main());
    }
}


