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
 * description：酒店模块
 */
public interface HotelContract {

    /**
     * 数据和业务模型
     **/
    interface Model extends BaseModel {

        //酒店栏目列表
        Observable<BackResult<ScenicSpotHomePageResponse>> getHotelHomePage(String longitude, String latitude);

        //酒店列表-筛选
        Observable<BackResult<ScenicSpotResponse>> findHotelByCate(HashMap<String, String> map);

        //酒店榜单
        Observable<BackResult<MchBangDanRankingResponse>> getHotelBangDanRanking(int cityId);

    }

    interface View extends BaseView {

        void getHotelHomePageResult(BackResult<ScenicSpotHomePageResponse> res);

        void findHotelByCateResult(BackResult<ScenicSpotResponse> res);

        void getHotelBangDanRankingResult(BackResult<MchBangDanRankingResponse> res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getHotelHomePage(String longitude, String latitude);

        //酒店列表-筛选
        public abstract void findHotelByCate(HashMap<String, String> map);

        public abstract void getHotelBangDanRanking(int cityId);

    }
}