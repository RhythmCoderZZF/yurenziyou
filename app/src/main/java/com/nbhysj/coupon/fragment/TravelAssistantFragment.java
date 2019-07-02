package com.nbhysj.coupon.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.MyTravelListAdapter;
import com.nbhysj.coupon.adapter.NearbyHotSellHotelsAdapter;
import com.nbhysj.coupon.adapter.TravelAssisantRecommendAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.TravelAssistantContract;
import com.nbhysj.coupon.model.TravelAssistantModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CountryBean;
import com.nbhysj.coupon.model.response.CreateTripResponse;
import com.nbhysj.coupon.model.response.TravelAssistantDetailCountryBean;
import com.nbhysj.coupon.model.response.TripDetailsResponse;
import com.nbhysj.coupon.model.response.TripHomePageResponse;
import com.nbhysj.coupon.model.response.TripScenicSpotAddCountryBean;
import com.nbhysj.coupon.presenter.TravelAssistantPresenter;
import com.nbhysj.coupon.ui.CalendarActivity;
import com.nbhysj.coupon.ui.HotelDetailsActivity;
import com.nbhysj.coupon.ui.MyBusinessCardActivity;
import com.nbhysj.coupon.ui.TravelAssistantDetailsActivity;
import com.nbhysj.coupon.ui.TravelAssistantEditActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.blurbehind.BlurBehind;
import com.nbhysj.coupon.util.blurbehind.OnBlurCompleteListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class TravelAssistantFragment extends BaseFragment<TravelAssistantPresenter, TravelAssistantModel> implements TravelAssistantContract.View {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //行程助手推荐
    @BindView(R.id.rv_recommended_for_you_travel)
    RecyclerView mRvTravelAssisantRecommend;
    //我的行程
    @BindView(R.id.rv_my_travel)
    RecyclerView mRvMyTravel;
    @BindView(R.id.rlyt_add_trip)
    RelativeLayout mRlytAddTrip;
    //无行程
    @BindView(R.id.llyt_no_trip_assistant)
    LinearLayout mLlytNoTripAssistant;
    //有行程
    @BindView(R.id.llyt_my_trip_assistant)
    LinearLayout mLlytMyTripAssistant;

    //有行程攻略列表
    private List<TripHomePageResponse.TripEntity> tripEntityList;
    //无行程攻略列表
    List<TripHomePageResponse.StrategyEntity> strategyList;
    private MyTravelListAdapter myTravelListAdapter;

    private TravelAssisantRecommendAdapter travelAssisantRecommendAdapter;

    public TravelAssistantFragment() {
        // Required empty public constructor
    }

    public static TravelAssistantFragment newInstance(String param1, String param2) {
        TravelAssistantFragment fragment = new TravelAssistantFragment();
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
        return R.layout.fragment_assistant;
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView(View v) {
        if (tripEntityList == null) {

            tripEntityList = new ArrayList<>();
        } else {
            tripEntityList.clear();
        }

        if (strategyList == null) {

            strategyList = new ArrayList<>();
        } else {
            strategyList.clear();
        }
        getTravelAssistantList();
        //行程助手推荐  无行程
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(layoutManager.VERTICAL);
        mRvTravelAssisantRecommend.setLayoutManager(layoutManager);
        travelAssisantRecommendAdapter = new TravelAssisantRecommendAdapter(getActivity());
        travelAssisantRecommendAdapter.setTravelAssisantStrategyList(strategyList);
        mRvTravelAssisantRecommend.setAdapter(travelAssisantRecommendAdapter);


        //我的行程列表   有行程
        LinearLayoutManager travelListAdapterLayoutManager = new LinearLayoutManager(getActivity());
        travelListAdapterLayoutManager.setOrientation(travelListAdapterLayoutManager.VERTICAL);
        mRvMyTravel.setLayoutManager(travelListAdapterLayoutManager);
        myTravelListAdapter = new MyTravelListAdapter(getActivity(), new MyTravelListAdapter.MyTravelListener() {
            @Override
            public void setMyTravelListener(int position) {
                int tripId = tripEntityList.get(position).getId();
                Intent intent = new Intent(getActivity(), TravelAssistantDetailsActivity.class);
                intent.putExtra("tripId", tripId);
                startActivityForResult(intent, 0);
            }

            @Override
            public void setMyTravelEditListener(int position) {
                BlurBehind.getInstance().execute(getActivity(), new OnBlurCompleteListener() {
                    @Override
                    public void onBlurComplete() {

                        Intent intent = new Intent(getActivity(), TravelAssistantEditActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                        startActivity(intent);
                    }
                });
            }
        });
        myTravelListAdapter.setMyTravelList(tripEntityList);
        mRvMyTravel.setAdapter(myTravelListAdapter);

    }

    @Override
    public void initData() {

    }

    @Override
    public void lazyInitView(View view) {

    }

    @Override
    public void getTravelAssistantHomePageResult(BackResult<TripHomePageResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    TripHomePageResponse tripHomePageResponse = res.getData();
                    List<TripHomePageResponse.TripEntity> tripList = tripHomePageResponse.getTrip();
                    if (tripList.size() > 0) {
                        mLlytNoTripAssistant.setVisibility(View.GONE);
                        mLlytMyTripAssistant.setVisibility(View.VISIBLE);
                        tripEntityList.addAll(tripList);
                        myTravelListAdapter.setMyTravelList(tripEntityList);
                        myTravelListAdapter.notifyDataSetChanged();
                    } else {
                        mLlytNoTripAssistant.setVisibility(View.VISIBLE);
                        mLlytMyTripAssistant.setVisibility(View.GONE);
                        strategyList = tripHomePageResponse.getStrategy();
                        travelAssisantRecommendAdapter.setTravelAssisantStrategyList(strategyList);
                        travelAssisantRecommendAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(getActivity(), Constants.getResultMsg(res.getMsg()));
                break;
        }
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
    public void delTripPlaceResult(BackResult res) {

    }

    @Override
    public void insertNoteResult(BackResult<CreateTripResponse> res) {

    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(getActivity(), Constants.getResultMsg(msg));
    }

    public void getTravelAssistantList() {
        if (validateInternet()) {

            mPresenter.getTravelAssistantHomePage();
        }
    }

    @OnClick({R.id.cardview_add_my_travel, R.id.rlyt_add_trip})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cardview_add_my_travel:

                toActivity(CalendarActivity.class);

                break;
            case R.id.rlyt_add_trip:
                toActivity(CalendarActivity.class);
                break;
            default:
                break;
        }

    }
}
