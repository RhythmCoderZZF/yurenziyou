package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.request.HotelHomestayOrderSubmitRequest;
import com.nbhysj.coupon.model.request.MchCollectionRequest;
import com.nbhysj.coupon.model.request.QueryByTicketRequest;
import com.nbhysj.coupon.model.request.UseCouponTicketRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CouponsGetBean;
import com.nbhysj.coupon.model.response.HotelMchDetailsResponse;
import com.nbhysj.coupon.model.response.HotelOrderInitResponse;
import com.nbhysj.coupon.model.response.MchBangDanRankingResponse;
import com.nbhysj.coupon.model.response.MchCateListResponse;
import com.nbhysj.coupon.model.response.MchCollectionResponse;
import com.nbhysj.coupon.model.response.MchCouponResponse;
import com.nbhysj.coupon.model.response.MchDetailsResponse;
import com.nbhysj.coupon.model.response.OrderSubmitResponse;
import com.nbhysj.coupon.model.response.QueryByTicketResponse;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.model.response.ScenicSpotResponse;
import com.nbhysj.coupon.model.response.UseCouponTicketResponse;

import java.util.HashMap;
import java.util.List;

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
        Observable<BackResult<HotelMchDetailsResponse>> getHotelMchDetail(int mchId);

        //酒店下单初始化页面
        Observable<BackResult<HotelOrderInitResponse>> getHotelHomestayOrderInit(int goodsId, String checkInAndOutTime);

        //酒店下单
        Observable<BackResult<OrderSubmitResponse>> hotelHomestayOrderSubmit(HotelHomestayOrderSubmitRequest hotelHomestayOrderSubmitRequest);

        //商户收藏
        Observable<BackResult<MchCollectionResponse>> mchCollection(MchCollectionRequest mchCollectionRequest);

        //查询日历价格下可选的所有优惠券
        Observable<BackResult<QueryByTicketResponse>> queryByTicket(QueryByTicketRequest queryByTicketRequest);

        //选择使用优惠券
        Observable<BackResult<UseCouponTicketResponse>> useCouponTicketRequest(UseCouponTicketRequest useCouponTicketRequest);

        //获取优惠券列表
        Observable<BackResult<List<MchCouponResponse>>> queryMchCouponList(int mchId);

        //领取优惠券
        Observable<BackResult<CouponsGetBean>> getCoupon(int couponId);

        //酒店类目列表
        Observable<BackResult<MchCateListResponse>> getHotelListByCateId(HashMap<String, String> map);

    }

    interface View extends BaseView {

        void getHotelHomePageResult(BackResult<ScenicSpotHomePageResponse> res);

        void findHotelByCateResult(BackResult<ScenicSpotResponse> res);

        void getHotelBangDanRankingResult(BackResult<MchBangDanRankingResponse> res);

        void getHotelMchDetailResult(BackResult<HotelMchDetailsResponse> res);

        void getHotelHomestayOrderInitResult(BackResult<HotelOrderInitResponse> res);

        void hotelHomestayOrderSubmitResult(BackResult<OrderSubmitResponse> res);

        void mchCollectionResult(BackResult<MchCollectionResponse> res);

        //订单提交返回
        void queryByTicketResult(BackResult<QueryByTicketResponse> res);

        void useCouponTicketResult(BackResult<UseCouponTicketResponse> res);

        void queryMchCouponListResult(BackResult<List<MchCouponResponse>> res);

        //领取优惠券
        void getCouponResult(BackResult<CouponsGetBean> res);

        void getHotelListByCateIdResult(BackResult<MchCateListResponse> res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getHotelHomePage(String longitude, String latitude);

        //酒店列表-筛选
        public abstract void findHotelByCate(HashMap<String, String> map);

        public abstract void getHotelBangDanRanking(int cityId);

        public abstract void getHotelMchDetail(int mchId);

        public abstract void getHotelHomestayOrderInit(int goodsId, String checkInAndOutTime);

        public abstract void hotelHomestayOrderSubmit(HotelHomestayOrderSubmitRequest hotelHomestayOrderSubmitRequest);

        public abstract void mchCollection(MchCollectionRequest mchCollectionRequest);

        public abstract void queryByTicket(QueryByTicketRequest queryByTicketRequest);

        public abstract void useCouponTicketRequest(UseCouponTicketRequest useCouponTicketRequest);

        public abstract void queryMchCouponList(int mchId);

        public abstract void getCoupon(int couponId);

        //酒店类目列表
        public abstract void getHotelListByCateId(HashMap<String, String> map);

    }
}