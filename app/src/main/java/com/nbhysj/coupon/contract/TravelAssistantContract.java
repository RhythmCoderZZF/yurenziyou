package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.request.AddMchRequest;
import com.nbhysj.coupon.model.request.AddRemarksRequest;
import com.nbhysj.coupon.model.request.CreateTripRequest;
import com.nbhysj.coupon.model.request.DeleteTripPlaceRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CountryBean;
import com.nbhysj.coupon.model.response.CreateTripResponse;
import com.nbhysj.coupon.model.response.TravelAssistantDetailCountryBean;
import com.nbhysj.coupon.model.response.TripDetailsResponse;
import com.nbhysj.coupon.model.response.TripHomePageResponse;
import com.nbhysj.coupon.model.response.TripScenicSpotAddCountryBean;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;

/**
 * @auther：hysj created on 2019/06/13
 * description：行程助手模块
 */
public interface TravelAssistantContract {
    /**
     * 数据和业务模型
     **/
    interface Model extends BaseModel {

        //行程助手首页列表
        Observable<BackResult<TripHomePageResponse>> getTravelAssistantHomePage();

        //行程助手获取区县列表
        Observable<BackResult<List<CountryBean>>> getCountyList(int tripId);

        //区县选择
        Observable<BackResult<List<TravelAssistantDetailCountryBean>>> getCountyWebList(HashMap<String, String> tripIdMap);

        //创建行程
        Observable<BackResult<CreateTripResponse>> createTrip(CreateTripRequest createTripRequest);

        //行程详情
        Observable<BackResult<TripDetailsResponse>> getTripDetails(int tripId);

        //获取行程助手商户列表
        Observable<BackResult<TripScenicSpotAddCountryBean>> getTravelAssistantMchList(int tripId, String countyId, String mchType, int page, int pageSize);

        //添加商户
        Observable<BackResult<CreateTripResponse>> insertPlaceMch(AddMchRequest addMchRequest);

        //添加备注
        Observable<BackResult<CreateTripResponse>> insertNote(AddRemarksRequest addRemarksRequest);

        //行程点删除
        Observable<BackResult> delTripPlace(DeleteTripPlaceRequest deleteTripPlaceRequest);
    }

    interface View extends BaseView {

        void getTravelAssistantHomePageResult(BackResult<TripHomePageResponse> res);

        void getCountyListResult(BackResult<List<CountryBean>> res);

        void getCountyWebListResult(BackResult<List<TravelAssistantDetailCountryBean>> res);

        void getCreateTripResult(BackResult<CreateTripResponse> res);

        void getTripDetailsResult(BackResult<TripDetailsResponse> res);

        void getTravelAssistantMchListResult(BackResult<TripScenicSpotAddCountryBean> res);

        void insertPlaceMchResult(BackResult<CreateTripResponse> res);

        void insertNoteResult(BackResult<CreateTripResponse> res);

        void delTripPlaceResult(BackResult res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getTravelAssistantHomePage();

        //（添加景点||添加住宿）获取区县列表
        public abstract void getCountyList(int tripId);

        //获取区县列表
        public abstract void getCountyWebList(HashMap<String, String> tripIdMap);

        public abstract void createTrip(CreateTripRequest createTripRequest);

        public abstract void getTripDetails(int tripId);

        public abstract void getTravelAssistantMchList(int tripId, String countyId, String mchType, int page, int pageSize);

        public abstract void insertPlaceMch(AddMchRequest addMchRequest);

        public abstract void insertRemarks(AddRemarksRequest addRemarksRequest);

        public abstract void delTripPlace(DeleteTripPlaceRequest deleteTripPlaceRequest);
    }
}