package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.MchBangDanRankingResponse;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.model.response.ScenicSpotResponse;

import java.util.HashMap;

import io.reactivex.Observable;

/**
 * @auther：hysj created on 2019/09/16
 * description：互动模块
 */
public interface RecreationContract {

    /**
     * 数据和业务模型
     **/
    interface Model extends BaseModel {

        //互动栏目列表
        Observable<BackResult<ScenicSpotHomePageResponse>> getRecreationHomePage(String longitude, String latitude);

        //互动列表-筛选
        Observable<BackResult<ScenicSpotResponse>> findRecreationByCate(HashMap<String, String> map);

        //互动榜单
        Observable<BackResult<MchBangDanRankingResponse>> getRecreationDanRanking(int cityId);
    }

    interface View extends BaseView {

        void getRecreationHomePageResult(BackResult<ScenicSpotHomePageResponse> res);

        void findRecreationByCateResult(BackResult<ScenicSpotResponse> res);

        void getRecreationDanRankingResult(BackResult<MchBangDanRankingResponse> res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getRecreationHomePage(String longitude, String latitude);

        //互动列表-筛选
        public abstract void findRecreationByCate(HashMap<String, String> map);

        public abstract void getRecreationBangDanRanking(int cityId);
    }
}