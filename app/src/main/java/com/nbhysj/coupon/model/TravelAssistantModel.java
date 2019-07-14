package com.nbhysj.coupon.model;

import com.nbhysj.coupon.contract.TravelAssistantContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.request.AddMchRequest;
import com.nbhysj.coupon.model.request.AddRemarksRequest;
import com.nbhysj.coupon.model.request.CreateTripRequest;
import com.nbhysj.coupon.model.request.DeleteTripPlaceRequest;
import com.nbhysj.coupon.model.request.DeleteTripRequest;
import com.nbhysj.coupon.model.request.EditTripSubmitRequest;
import com.nbhysj.coupon.model.request.TravelAssistantAddOneDayRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CountryBean;
import com.nbhysj.coupon.model.response.CreateTripResponse;
import com.nbhysj.coupon.model.response.TravelAssistantDetailCountryBean;
import com.nbhysj.coupon.model.response.TripDetailsResponse;
import com.nbhysj.coupon.model.response.TripHomePageResponse;
import com.nbhysj.coupon.model.response.TripRouteMapResponse;
import com.nbhysj.coupon.model.response.TripScenicSpotAddCountryBean;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;

/**
 * created by hysj at 2019/06/01.
 * description : 行程助手 Model层
 */

public class TravelAssistantModel implements TravelAssistantContract.Model {

    @Override
    public Observable<BackResult<TripHomePageResponse>> getTravelAssistantHomePage() {
        return Api.getInstance().apiService.getTripHomePage().compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<List<CountryBean>>> getCountyList(int tripId) {
        return Api.getInstance().apiService.getCountyList(tripId).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<List<TravelAssistantDetailCountryBean>>> getCountyWebList(HashMap<String, String> tripIdMap) {
        return Api.getInstance().apiService.getCountyWebList(tripIdMap).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<CreateTripResponse>> createTrip(CreateTripRequest createTripRequest) {
        return Api.getInstance().apiService.createTrip(createTripRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<TripDetailsResponse>> getTripDetails(int tripId) {
        return Api.getInstance().apiService.getTravelAssistantDetail(tripId).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<TripScenicSpotAddCountryBean>> getTravelAssistantMchList(int tripId, String countyId, String mchType, int page, int pageSize) {
        return Api.getInstance().apiService.getTravelAssistantMchList(tripId, countyId, mchType, page, pageSize).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<CreateTripResponse>> insertPlaceMch(AddMchRequest addMchRequest) {
        return Api.getInstance().apiService.insertPlaceMch(addMchRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult> delTripPlace(DeleteTripPlaceRequest deleteTripPlaceRequest) {
        return Api.getInstance().apiService.delTripPlace(deleteTripPlaceRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<CreateTripResponse>> insertNote(AddRemarksRequest addRemarksRequest) {
        return Api.getInstance().apiService.insertNote(addRemarksRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult> travelAssistantPlusADay(TravelAssistantAddOneDayRequest addOneDayRequest) {
        return Api.getInstance().apiService.travelAssistantPlusADay(addOneDayRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult> delTrip(DeleteTripRequest deleteTripRequest) {
        return Api.getInstance().apiService.delTrip(deleteTripRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult> updateTripInformation(EditTripSubmitRequest editTripSubmitRequest) {
        return Api.getInstance().apiService.updateTripInformation(editTripSubmitRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<TripRouteMapResponse>> getTripRouteMap(int tripId) {
        return Api.getInstance().apiService.getTripMap(tripId).compose(RxSchedulers.io_main());
    }
}
