package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.request.AddCountyRequest;
import com.nbhysj.coupon.model.request.AddMchRequest;
import com.nbhysj.coupon.model.request.AddRemarksRequest;
import com.nbhysj.coupon.model.request.AddTrafficRequest;
import com.nbhysj.coupon.model.request.CreateTripRequest;
import com.nbhysj.coupon.model.request.DeleteTripPlaceRequest;
import com.nbhysj.coupon.model.request.DeleteTripRequest;
import com.nbhysj.coupon.model.request.EditTripSubmitRequest;
import com.nbhysj.coupon.model.request.IntelligentTripRequest;
import com.nbhysj.coupon.model.request.TravelAssistantAddOneDayRequest;
import com.nbhysj.coupon.model.response.AddCountyResponse;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CountryBean;
import com.nbhysj.coupon.model.response.CreateTripResponse;
import com.nbhysj.coupon.model.response.TravelAssistantDetailCountryBean;
import com.nbhysj.coupon.model.response.TripDetailsResponse;
import com.nbhysj.coupon.model.response.TripHomePageResponse;
import com.nbhysj.coupon.model.response.TripRouteMapResponse;
import com.nbhysj.coupon.model.response.TripScenicSpotAddCountryBean;
import com.nbhysj.coupon.model.response.WeatherResponse;

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

        //智能规划
        Observable<BackResult<CreateTripResponse>> intelligentProject(IntelligentTripRequest intelligentTripRequest);

        //行程详情
        Observable<BackResult<TripDetailsResponse>> getTripDetails(int tripId);

        //获取行程助手商户列表
        Observable<BackResult<TripScenicSpotAddCountryBean>> getTravelAssistantMchList(int tripId, String countyId, String mchType, int page, int pageSize);

        //添加商户
        Observable<BackResult<CreateTripResponse>> insertPlaceMch(AddMchRequest addMchRequest);

        //(行程助手)增加一天
        Observable<BackResult> travelAssistantPlusADay(TravelAssistantAddOneDayRequest addOneDayRequest);

        //添加区县
        Observable<BackResult<AddCountyResponse>> insertCounty(AddCountyRequest addCountyRequest);

        //添加交通
        Observable<BackResult<CreateTripResponse>> insertTraffic(AddTrafficRequest addTrafficRequest);

        //添加备注
        Observable<BackResult<CreateTripResponse>> insertNote(AddRemarksRequest addRemarksRequest);

        //行程点删除
        Observable<BackResult> delTripPlace(DeleteTripPlaceRequest deleteTripPlaceRequest);

        //行程删除
        Observable<BackResult> delTrip(DeleteTripRequest deleteTripRequest);

        //行程编辑保存
        Observable<BackResult> updateTripInformation(EditTripSubmitRequest editTripSubmitRequest);

        //行程地图路线
        Observable<BackResult<TripRouteMapResponse>> getTripRouteMap(int tripId);

        //获取天气接口
        Observable<BackResult<WeatherResponse>> getWeather(int cityCode);

    }

    interface View extends BaseView {

        void getTravelAssistantHomePageResult(BackResult<TripHomePageResponse> res);

        void getCountyListResult(BackResult<List<CountryBean>> res);

        void getCountyWebListResult(BackResult<List<TravelAssistantDetailCountryBean>> res);

        void getCreateTripResult(BackResult<CreateTripResponse> res);

        void getTripDetailsResult(BackResult<TripDetailsResponse> res);

        void getTravelAssistantMchListResult(BackResult<TripScenicSpotAddCountryBean> res);

        void insertPlaceMchResult(BackResult<CreateTripResponse> res);

        void intelligentProjectResult(BackResult<CreateTripResponse> res);

        //增加一天
        void travelAssistantPlusADay(BackResult res);

        //添加区县
        void insertCountyResult(BackResult<AddCountyResponse> res);

        //添加备注
        void insertNoteResult(BackResult<CreateTripResponse> res);

        //添加交通
        void insertTrafficResult(BackResult<CreateTripResponse> res);

        //行程详情行程点删除
        void delTripPlaceResult(BackResult res);

        //行程删除
        void delTripResult(BackResult res);

        //行程编辑保存
        void updateTripInformationResult(BackResult res);

        //行程地图路线
        void getTripRouteMapResult(BackResult<TripRouteMapResponse> res);

        //获取天气接口
        void getWeatherResult(BackResult<WeatherResponse> res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getTravelAssistantHomePage();

        //（添加景点||添加住宿）获取区县列表
        public abstract void getCountyList(int tripId);

        //获取区县列表
        public abstract void getCountyWebList(HashMap<String, String> tripIdMap);

        public abstract void createTrip(CreateTripRequest createTripRequest);

        //只能规划
        public abstract void intelligentProject(IntelligentTripRequest intelligentTripRequest);

        public abstract void getTripDetails(int tripId);

        public abstract void getTravelAssistantMchList(int tripId, String countyId, String mchType, int page, int pageSize);

        public abstract void insertPlaceMch(AddMchRequest addMchRequest);

        //增加一天
        public abstract void travelAssistantPlusADay(TravelAssistantAddOneDayRequest addOneDayRequest);

        //添加区县
        public abstract void insertCounty(AddCountyRequest addCountyRequest);

        //添加备注
        public abstract void insertRemarks(AddRemarksRequest addRemarksRequest);

        //添加交通
        public abstract void insertTraffic(AddTrafficRequest addTrafficRequest);

        //行程点删除
        public abstract void delTripPlace(DeleteTripPlaceRequest deleteTripPlaceRequest);

        //行程删除
        public abstract void delTrip(DeleteTripRequest deleteTripRequest);

        //行程编辑保存
        public abstract void updateTripInformation(EditTripSubmitRequest editTripSubmitRequest);

        //行程地图路线
        public abstract void getTripRouteMap(int tripId);

        //获取天气接口
        public abstract void getWeather(int cityCode);

    }
}