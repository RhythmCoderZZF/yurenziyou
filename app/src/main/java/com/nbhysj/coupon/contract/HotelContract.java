package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.request.HotelHomestayOrderSubmitRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.HotelOrderInitResponse;
import com.nbhysj.coupon.model.response.MchBangDanRankingResponse;
import com.nbhysj.coupon.model.response.MchDetailsResponse;
import com.nbhysj.coupon.model.response.OrderSubmitResponse;
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

        //酒店详情
        Observable<BackResult<MchDetailsResponse>> getMchDetails(int mchId);

        //酒店下单初始化页面
        Observable<BackResult<HotelOrderInitResponse>> getHotelHomestayOrderInit(int goodsId, String checkInAndOutTime);

        //酒店下单
        Observable<BackResult<OrderSubmitResponse>> hotelHomestayOrderSubmit(HotelHomestayOrderSubmitRequest hotelHomestayOrderSubmitRequest);

    }

    interface View extends BaseView {

        void getHotelHomePageResult(BackResult<ScenicSpotHomePageResponse> res);

        void findHotelByCateResult(BackResult<ScenicSpotResponse> res);

        void getHotelBangDanRankingResult(BackResult<MchBangDanRankingResponse> res);

        void getMchDetailsResult(BackResult<MchDetailsResponse> res);

        void getHotelHomestayOrderInitResult(BackResult<HotelOrderInitResponse> res);

        void hotelHomestayOrderSubmitResult(BackResult<OrderSubmitResponse> res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getHotelHomePage(String longitude, String latitude);

        //酒店列表-筛选
        public abstract void findHotelByCate(HashMap<String, String> map);

        public abstract void getHotelBangDanRanking(int cityId);

        public abstract void getMchDetails(int mchId);

        public abstract void getHotelHomestayOrderInit(int goodsId, String checkInAndOutTime);

        public abstract void hotelHomestayOrderSubmit(HotelHomestayOrderSubmitRequest hotelHomestayOrderSubmitRequest);

    }
}