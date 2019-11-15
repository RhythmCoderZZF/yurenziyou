package com.nbhysj.coupon.fragment;

import android.os.Bundle;
import android.view.View;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.DestinationContract;
import com.nbhysj.coupon.contract.TravelAssistantContract;
import com.nbhysj.coupon.model.DestinationModel;
import com.nbhysj.coupon.model.TravelAssistantModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CountryBean;
import com.nbhysj.coupon.model.response.CreateTripResponse;
import com.nbhysj.coupon.model.response.DestinationResponse;
import com.nbhysj.coupon.model.response.HotScenicSpotResponse;
import com.nbhysj.coupon.model.response.TravelAssistantDetailCountryBean;
import com.nbhysj.coupon.model.response.TripDetailsResponse;
import com.nbhysj.coupon.model.response.TripHomePageResponse;
import com.nbhysj.coupon.model.response.TripRouteMapResponse;
import com.nbhysj.coupon.model.response.TripScenicSpotAddCountryBean;
import com.nbhysj.coupon.model.response.WeatherResponse;
import com.nbhysj.coupon.presenter.DestinationPresenter;
import com.nbhysj.coupon.presenter.TravelAssistantPresenter;

import java.util.List;

/**
 * @auther：hysj created on 2019/07/07
 * description：行程助手添加交通(驾车)
 */
public class TravelAssistantTrafficAddDriveFragment extends BaseFragment<TravelAssistantPresenter, TravelAssistantModel> implements TravelAssistantContract.View {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public TravelAssistantTrafficAddDriveFragment() {
        // Required empty public constructor
    }

    public static TravelAssistantTrafficAddDriveFragment newInstance(String param1, String param2) {
        TravelAssistantTrafficAddDriveFragment fragment = new TravelAssistantTrafficAddDriveFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_travel_assistant_traffic_add_drive;
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView(View v) {

    }

    @Override
    public void initData() {

    }

    @Override
    public void lazyInitView(View view) {

    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(getActivity(), Constants.getResultMsg(msg));

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
    public void getCreateTripResult(BackResult<CreateTripResponse> res) {

    }

    @Override
    public void getTripDetailsResult(BackResult<TripDetailsResponse> res) {

    }

    @Override
    public void getTravelAssistantMchListResult(BackResult<TripScenicSpotAddCountryBean> res) {

    }

    @Override
    public void insertPlaceMchResult(BackResult<CreateTripResponse> res) {

    }

    @Override
    public void intelligentProjectResult(BackResult<CreateTripResponse> res) {

    }

    @Override
    public void travelAssistantPlusADay(BackResult res) {

    }

    @Override
    public void insertNoteResult(BackResult<CreateTripResponse> res) {

    }

    @Override
    public void delTripPlaceResult(BackResult res) {

    }

    @Override
    public void delTripResult(BackResult res) {

    }

    @Override
    public void updateTripInformationResult(BackResult res) {

    }

    @Override
    public void getTripRouteMapResult(BackResult<TripRouteMapResponse> res) {

    }

    @Override
    public void getWeatherResult(BackResult<WeatherResponse> res) {

    }

    @Override
    public void insertTrafficResult(BackResult<CreateTripResponse> res) {

    }
}
