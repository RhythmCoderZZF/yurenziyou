package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.request.MchCollectionRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.FoodRecommendListResponse;
import com.nbhysj.coupon.model.response.MchBangDanRankingResponse;
import com.nbhysj.coupon.model.response.MchCollectionResponse;
import com.nbhysj.coupon.model.response.MchDetailsResponse;
import com.nbhysj.coupon.model.response.MchFoodDetailResponse;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.model.response.ScenicSpotResponse;

import java.util.HashMap;

import io.reactivex.Observable;

/**
 * @auther：hysj created on 2019/09/16
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
        Observable<BackResult<MchBangDanRankingResponse>> getFoodBangDanRanking(int cityId);

        //美食详情
        Observable<BackResult<MchFoodDetailResponse>> getFoodDetail(int mchId);

        //商户收藏
        Observable<BackResult<MchCollectionResponse>> mchCollection(MchCollectionRequest mchCollectionRequest);

        //获取美食推荐
        Observable<BackResult<FoodRecommendListResponse>> getGoodsFoodRecommendList(int mchId);
    }

    interface View extends BaseView {

        void getFineFoodHomePageResult(BackResult<ScenicSpotHomePageResponse> res);

        void findFoodByCateResult(BackResult<ScenicSpotResponse> res);

        void getFoodBangDanRankingResult(BackResult<MchBangDanRankingResponse> res);

        void getFoodDetailResult(BackResult<MchFoodDetailResponse> res);

        void mchCollectionResult(BackResult<MchCollectionResponse> res);

        void getGoodsFoodRecommendList(BackResult<FoodRecommendListResponse> res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getFineFoodHomePage(String longitude, String latitude);

        //美食列表-筛选
        public abstract void findFoodByCate(HashMap<String, String> map);

        public abstract void getFoodBangDanRanking(int cityId);

        public abstract void getFoodDetail(int mchId);

        public abstract void mchCollection(MchCollectionRequest mchCollectionRequest);

        public abstract void getGoodsFoodRecommendList(int mchId);
    }
}