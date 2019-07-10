package com.nbhysj.coupon.presenter;

import com.nbhysj.coupon.contract.TravelAssistantContract;
import com.nbhysj.coupon.model.request.AddMchRequest;
import com.nbhysj.coupon.model.request.AddRemarksRequest;
import com.nbhysj.coupon.model.request.CreateTripRequest;
import com.nbhysj.coupon.model.request.DeleteTripPlaceRequest;
import com.nbhysj.coupon.model.request.TravelAssistantAddOneDayRequest;

import java.util.HashMap;

/**
 * @auther：hysj created on 2019/06/05
 * description:行程助手 Presenter
 */
public class TravelAssistantPresenter extends TravelAssistantContract.Presenter {

    @Override
    public void getTravelAssistantHomePage() {
        mRxManager.add(mModel.getTravelAssistantHomePage().subscribe(res -> mView.getTravelAssistantHomePageResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getCountyList(int tripId) {
        mRxManager.add(mModel.getCountyList(tripId).subscribe(res -> mView.getCountyListResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getCountyWebList(HashMap<String, String> tripIdMap) {
        mRxManager.add(mModel.getCountyWebList(tripIdMap).subscribe(res -> mView.getCountyWebListResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void createTrip(CreateTripRequest createTripRequest) {
        mRxManager.add(mModel.createTrip(createTripRequest).subscribe(res -> mView.getCreateTripResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getTripDetails(int tripId) {
        mRxManager.add(mModel.getTripDetails(tripId).subscribe(res -> mView.getTripDetailsResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getTravelAssistantMchList(int tripId, String countyId, String mchType, int page, int pageSize) {
        mRxManager.add(mModel.getTravelAssistantMchList(tripId, countyId, mchType, page, pageSize).subscribe(res -> mView.getTravelAssistantMchListResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void insertPlaceMch(AddMchRequest addMchRequest) {
        mRxManager.add(mModel.insertPlaceMch(addMchRequest).subscribe(res -> mView.insertPlaceMchResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void delTripPlace(DeleteTripPlaceRequest deleteTripPlaceRequest) {
        mRxManager.add(mModel.delTripPlace(deleteTripPlaceRequest).subscribe(res -> mView.delTripPlaceResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void insertRemarks(AddRemarksRequest addRemarksRequest) {
        mRxManager.add(mModel.insertNote(addRemarksRequest).subscribe(res -> mView.insertNoteResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void travelAssistantPlusADay(TravelAssistantAddOneDayRequest addOneDayRequest) {
        mRxManager.add(mModel.travelAssistantPlusADay(addOneDayRequest).subscribe(res -> mView.travelAssistantPlusADay(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void onStart() {

    }
}
