package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.request.MchCollectionRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CouponsGetBean;
import com.nbhysj.coupon.model.response.MchAlbumResponse;
import com.nbhysj.coupon.model.response.MchBangDanRankingResponse;
import com.nbhysj.coupon.model.response.MchCollectionResponse;
import com.nbhysj.coupon.model.response.MchCouponResponse;
import com.nbhysj.coupon.model.response.MchDetailsResponse;
import com.nbhysj.coupon.model.response.NetFriendAlbumResponse;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.model.response.ScenicSpotResponse;
import com.nbhysj.coupon.model.response.TourGuideBean;
import java.util.HashMap;
import java.util.List;
import io.reactivex.Observable;

/**
 * @auther：hysj created on 2019/06/06
 * description：景点模块
 */
public interface ScenicSpotContract {

    /**
     * 数据和业务模型
     **/
    interface Model extends BaseModel {

        //景点栏目列表
        Observable<BackResult<ScenicSpotHomePageResponse>> getScenicSpotHomePage(String longitude, String latitude);

        //景点列表-筛选
        Observable<BackResult<ScenicSpotResponse>> findScenicByCate(HashMap<String, String> map);

        //景点榜单
        Observable<BackResult<MchBangDanRankingResponse>> getScenicBangDanRanking(int cityId);

        //景点详情
        Observable<BackResult<MchDetailsResponse>> getMchDetails(int mchId);

        //游玩指南列表
        Observable<BackResult<List<TourGuideBean>>> getTourGuideList(HashMap<String, String> map);

        //商家相册
        Observable<BackResult<MchAlbumResponse>> getMchAlbumList(int mchId);

        //网友相册
        Observable<BackResult<NetFriendAlbumResponse>> getNetFriendAlbumList(int mchId, int page, int pageSize);

        //商户收藏
        Observable<BackResult<MchCollectionResponse>> mchCollection(MchCollectionRequest mchCollectionRequest);

        //获取优惠券列表
        Observable<BackResult<List<MchCouponResponse>>> queryMchCouponList(int mchId);

        //领取优惠券
        Observable<BackResult<CouponsGetBean>> getCoupon(int couponId);
    }

    interface View extends BaseView {

        void getScenicSpotHomePageResult(BackResult<ScenicSpotHomePageResponse> res);

        void findScenicByCateResult(BackResult<ScenicSpotResponse> res);

        void getScenicBangDanRankingResult(BackResult<MchBangDanRankingResponse> res);

        void getMchDetailsResult(BackResult<MchDetailsResponse> res);

        void getTourGuideListResult(BackResult<List<TourGuideBean>> res);

        void getMchAlbumListResult(BackResult<MchAlbumResponse> res);

        void getNetFriendAlbumListResult(BackResult<NetFriendAlbumResponse> res);

        void mchCollectionResult(BackResult<MchCollectionResponse> res);

        void queryMchCouponListResult(BackResult<List<MchCouponResponse>> res);

        //领取优惠券
        void getCouponResult(BackResult<CouponsGetBean> res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getScenicSpotHomePage(String longitude, String latitude);

        //景点列表-筛选
        public abstract void findScenicByCate(HashMap<String, String> map);

        public abstract void getScenicBangDanRanking(int cityId);

        public abstract void getMchDetails(int mchId);

        public abstract void getTourGuideList(HashMap<String, String> map);

        public abstract void getMchAlbumList(int mchId);

        public abstract void getNetFriendAlbumList(int mchId, int page, int pageSize);

        public abstract void mchCollection(MchCollectionRequest mchCollectionRequest);

        public abstract void queryMchCouponList(int mchId);

        public abstract void getCoupon(int couponId);
    }
}