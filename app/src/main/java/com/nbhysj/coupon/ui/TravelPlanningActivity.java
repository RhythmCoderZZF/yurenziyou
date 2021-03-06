package com.nbhysj.coupon.ui;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.TravelAssistantContract;
import com.nbhysj.coupon.model.TravelAssistantModel;
import com.nbhysj.coupon.model.request.CreateTripRequest;
import com.nbhysj.coupon.model.request.IntelligentTripRequest;
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
    //开始时间和结束时间
    private String startDate, endDate;
    //0:智能规划 1:自己规划
    private int mPlanningTripFlag = 0;

    private int REQUEST_CODE_TRAVEL_PLANNING = 10000;
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
    public void getTripRouteMapResult(BackResult<TripRouteMapResponse> res) {

    }

    @Override
    public void getCreateTripResult(BackResult<CreateTripResponse> res) {
        try {


            switch (res.getCode()) {
                case Constants.SUCCESS_CODE:
                    try {

                        CreateTripResponse createTripResponse = res.getData();
                        int tripId = createTripResponse.getTripId();
                        if (mPlanningTripFlag == 0) {  //智能规划

                            intelligentTripRequest(tripId);

                        } else if (mPlanningTripFlag == 1) { //自己规划
                            dismissProgressDialog();
                            Intent intent = new Intent();
                            intent.putExtra("tripId", tripId);
                            intent.putExtra("planningComplete", 1);  //规划完成需要刷新
                            intent.setClass(TravelPlanningActivity.this, TravelAssistantDetailsActivity.class);
                            startActivityForResult(intent,REQUEST_CODE_TRAVEL_PLANNING);
                        }

                    } catch (Exception e) {
                        dismissProgressDialog();
                        e.printStackTrace();
                    }
                    break;
                default:
                    dismissProgressDialog();
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
    public void getWeatherResult(BackResult<WeatherResponse> res) {

    }

    @Override
    public void delTripResult(BackResult res) {

    }

    @Override
    public void updateTripInformationResult(BackResult res) {

    }

    @Override
    public void insertCountyResult(BackResult<AddCountyResponse> res) {

    }

    @Override
    public void insertTrafficResult(BackResult<CreateTripResponse> res) {

    }

    @Override
    public void intelligentProjectResult(BackResult<CreateTripResponse> res) {
        dismissProgressDialog();
        try {
            switch (res.getCode()) {
                case Constants.SUCCESS_CODE:
                    try {

                        CreateTripResponse createTripResponse = res.getData();
                        int tripId = createTripResponse.getTripId();

                        Intent intent = new Intent();
                        intent.putExtra("tripId", tripId);
                        intent.putExtra("planningComplete", 1);  //规划完成需要刷新
                        intent.setClass(TravelPlanningActivity.this, TravelAssistantDetailsActivity.class);
                        startActivityForResult(intent,REQUEST_CODE_TRAVEL_PLANNING);
                       // setResult(RESULT_OK);
                        //TravelPlanningActivity.this.finish();
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
    public void showMsg(String msg) {
        dismissProgressDialog();
        showToast(TravelPlanningActivity.this, Constants.getResultMsg(msg));
    }

    @OnClick({R.id.iv_back, R.id.tv_help_me_planning, R.id.tv_self_planning})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:

                TravelPlanningActivity.this.finish();

                break;

            case R.id.tv_help_me_planning:
                mPlanningTripFlag = 0;
                createTrip();
                break;
            case R.id.tv_self_planning:
                mPlanningTripFlag = 1;
                createTrip();
                break;
            default:
                break;
        }
    }

    public void createTrip() {

        if (validateInternet()) {

            showProgressDialog(TravelPlanningActivity.this);
            CreateTripRequest createTripRequest = new CreateTripRequest();
            createTripRequest.setCountyId(countyIdList);
            createTripRequest.setStartDate(startDate);
            createTripRequest.setEndDate(endDate);
            mPresenter.createTrip(createTripRequest);

        }
    }

    //智能规划
    public void intelligentTripRequest(int mTripId) {

        if (validateInternet()) {

            IntelligentTripRequest intelligentTripRequest = new IntelligentTripRequest();
            intelligentTripRequest.setCountyId(countyIdList);
            intelligentTripRequest.setTripId(mTripId);
            mPresenter.intelligentProject(intelligentTripRequest);

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_TRAVEL_PLANNING && resultCode == RESULT_OK)
        {
            setResult(RESULT_OK);
            TravelPlanningActivity.this.finish();
        }
    }
}
