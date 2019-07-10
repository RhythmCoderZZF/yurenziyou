package com.nbhysj.coupon.ui;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.TravelAssistantContract;
import com.nbhysj.coupon.model.TravelAssistantModel;
import com.nbhysj.coupon.model.request.CreateTripRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CountryBean;
import com.nbhysj.coupon.model.response.CreateTripResponse;
import com.nbhysj.coupon.model.response.TravelAssistantDetailCountryBean;
import com.nbhysj.coupon.model.response.TripDetailsResponse;
import com.nbhysj.coupon.model.response.TripHomePageResponse;
import com.nbhysj.coupon.model.response.TripScenicSpotAddCountryBean;
import com.nbhysj.coupon.presenter.TravelAssistantPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.ToolbarHelper;

import java.util.List;

import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/05/09
 * description：行程规划
 */
public class TravelPlanningActivity extends BaseActivity<TravelAssistantPresenter, TravelAssistantModel> implements TravelAssistantContract.View {

    private List<Integer> countyIdList;
    private String startDate, endDate;

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_travel_planning;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setHeadBar(TravelPlanningActivity.this, getResources().getString(R.string.str_travel_planning), R.mipmap.icon_left_arrow_black, "");
    }

    @Override
    public void initData() {

        countyIdList = getIntent().getIntegerArrayListExtra("countyIdList");
        startDate = getIntent().getStringExtra("startDate");
        endDate = getIntent().getStringExtra("endDate");

    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    public void getTravelAssistantHomePageResult(BackResult<TripHomePageResponse> res) {

    }

    @Override
    public void getCountyListResult(BackResult<List<CountryBean>> res) {

    }

    @Override
    public void getCountyWebListResult(BackResult<List<TravelAssistantDetailCountryBean>> res) {

    }

    @Override
    public void getTravelAssistantMchListResult(BackResult<TripScenicSpotAddCountryBean> res) {

    }

    @Override
    public void insertPlaceMchResult(BackResult<CreateTripResponse> res) {

    }

    @Override
    public void getCreateTripResult(BackResult<CreateTripResponse> res) {
        try {

            dismissProgressDialog();
            switch (res.getCode()) {
                case Constants.SUCCESS_CODE:
                    try {

                        CreateTripResponse createTripResponse = res.getData();
                        int tripId = createTripResponse.getTripId();
                        Intent intent = new Intent();
                        intent.putExtra("tripId", tripId);
                        intent.setClass(TravelPlanningActivity.this, TravelAssistantDetailsActivity.class);
                        startActivity(intent);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    showToast(TravelPlanningActivity.this, Constants.getResultMsg(res.getMsg()));
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getTripDetailsResult(BackResult<TripDetailsResponse> res) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void delTripPlaceResult(BackResult res) {

    }

    @Override
    public void insertNoteResult(BackResult<CreateTripResponse> res) {

    }

    @Override
    public void travelAssistantPlusADay(BackResult res) {

    }

    @Override
    public void showMsg(String msg) {
        dismissProgressDialog();
        showToast(TravelPlanningActivity.this, Constants.getResultMsg(msg));
    }

    @OnClick({R.id.iv_back, R.id.tv_help_me_planning})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:

                TravelPlanningActivity.this.finish();

                break;

            case R.id.tv_help_me_planning:


                createTrip();
                break;
            default:
                break;
        }
    }

    public void createTrip() {

        if (validateInternet()) {

            CreateTripRequest createTripRequest = new CreateTripRequest();
            createTripRequest.setCountyId(countyIdList);
            createTripRequest.setStartDate(startDate);
            createTripRequest.setEndDate(endDate);
            mPresenter.createTrip(createTripRequest);

        }
    }

}
