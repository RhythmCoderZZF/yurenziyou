package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.ScenicBangDanRankingResponse;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.model.response.ScenicSpotResponse;

import java.util.HashMap;

import io.reactivex.Observable;

/**
 * @auther：hysj created on 2019/06/06
 * description：美食模块
 */
public interface FineFoodContract {

    /**
     * 数据和业务模型
     **/
    interface Model extends BaseModel {

        //美食栏目列表
        Observable<BackResult<ScenicSpotHomePageResponse>> getFineFoodHomePage(String longitude, String latitude);

        //美食列表-筛选
        Observable<BackResult<ScenicSpotResponse>> findFoodByCate(HashMap<String, String> map);

        //美食榜单
        Observable<BackResult<ScenicBangDanRankingResponse>> getScenicBangDanRanking(int cityId);
    }

    interface View extends BaseView {

        void getFineFoodHomePageResult(BackResult<ScenicSpotHomePageResponse> res);

        void findFoodByCateResult(BackResult<ScenicSpotResponse> res);

        void getScenicBangDanRankingResult(BackResult<ScenicBangDanRankingResponse> res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getFineFoodHomePage(String longitude, String latitude);

        //美食列表-筛选
        public abstract void findFoodByCate(HashMap<String, String> map);

        public abstract void getScenicBangDanRanking(int cityId);
    }
}