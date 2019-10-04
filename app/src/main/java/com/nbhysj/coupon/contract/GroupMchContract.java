package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.GroupMchDetailsResponse;
import com.nbhysj.coupon.model.response.GroupMchResponse;
import com.nbhysj.coupon.model.response.MchDetailsResponse;

import io.reactivex.Observable;

/**
 * @auther：hysj created on 2019/09/22
 * description：组合模块
 */
public interface GroupMchContract {

    /**
     * 数据和业务模型
     **/
    interface Model extends BaseModel {

        //组合列表
        Observable<BackResult<GroupMchResponse>> getGroupMchHomePage();

        //组合详情
        Observable<BackResult<GroupMchDetailsResponse>> getGroupMchDetail(int packageId);
    }

    interface View extends BaseView {

        void getGroupMchHomePageResult(BackResult<GroupMchResponse> res);

        void getGroupMchDetailResult(BackResult<GroupMchDetailsResponse> res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getGroupMchHomePage();

        public abstract void getGroupMchDetail(int packageId);
    }
}