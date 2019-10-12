package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.request.MchCollectionRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.MchBangDanRankingResponse;
import com.nbhysj.coupon.model.response.MchCollectionResponse;
import com.nbhysj.coupon.model.response.MchHomestayDetailsResponse;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.model.response.ScenicSpotResponse;
import java.util.HashMap;
import io.reactivex.Observable;

/**
 * @auther：hysj created on 2019/06/06
 * description：民宿模块
 */
public interface HomestayContract {

    /**
     * 数据和业务模型
     **/
    interface Model extends BaseModel {

        //民宿栏目列表
        Observable<BackResult<ScenicSpotHomePageResponse>> getHomestayHomePage(String longitude, String latitude);

        //民宿列表-筛选
        Observable<BackResult<ScenicSpotResponse>> findHomestayByCate(HashMap<String, String> map);

        //民宿榜单
        Observable<BackResult<MchBangDanRankingResponse>> getHomestayBangDanRanking(int cityId);

        //民宿收藏
        Observable<BackResult<MchCollectionResponse>> mchCollection(MchCollectionRequest mchCollectionRequest);

        //民宿收藏
        Observable<BackResult<MchHomestayDetailsResponse>> getMchHomestayDetail(int mchId);

    }

    interface View extends BaseView {

        void getHomestayHomePageResult(BackResult<ScenicSpotHomePageResponse> res);

        void findHomestayByCateResult(BackResult<ScenicSpotResponse> res);

        void getHomestayBangDanRankingResult(BackResult<MchBangDanRankingResponse> res);

        void mchCollectionResult(BackResult<MchCollectionResponse> res);

        void getMchHomestayDetailResult(BackResult<MchHomestayDetailsResponse> res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getHomestayHomePage(String longitude, String latitude);

        //民宿列表-筛选
        public abstract void findHomestayByCate(HashMap<String, String> map);

        public abstract void getHomestayBangDanRanking(int cityId);

        //民宿收藏
        public abstract void mchCollection(MchCollectionRequest mchCollectionRequest);

        //民宿详情
        public abstract void getMchHomestayDetail(int mchId);
    }
}