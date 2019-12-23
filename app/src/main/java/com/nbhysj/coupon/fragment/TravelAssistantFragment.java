package com.nbhysj.coupon.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.MyTravelListAdapter;
import com.nbhysj.coupon.adapter.StrategyListAdapter;
import com.nbhysj.coupon.adapter.TravelAssisantRecommendAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.TravelAssistantContract;
import com.nbhysj.coupon.model.TravelAssistantModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CountryBean;
import com.nbhysj.coupon.model.response.CreateTripResponse;
import com.nbhysj.coupon.model.response.StrategyBean;
import com.nbhysj.coupon.model.response.TravelAssistantDetailCountryBean;
import com.nbhysj.coupon.model.response.TripDetailsResponse;
import com.nbhysj.coupon.model.response.TripHomePageResponse;
import com.nbhysj.coupon.model.response.TripRouteMapResponse;
import com.nbhysj.coupon.model.response.TripScenicSpotAddCountryBean;
import com.nbhysj.coupon.model.response.WeatherResponse;
import com.nbhysj.coupon.presenter.TravelAssistantPresenter;
import com.nbhysj.coupon.ui.CalendarActivity;
import com.nbhysj.coupon.ui.PhoneQuickLoginActivity;
import com.nbhysj.coupon.ui.TravelAssistantDetailsActivity;
import com.nbhysj.coupon.ui.TravelAssistantEditActivity;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.nbhysj.coupon.util.blurbehind.BlurBehind;
import com.nbhysj.coupon.util.blurbehind.OnBlurCompleteListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * @auther：hysj created on 2019/03/08
 * description：行程助手首页列表
 */
public class TravelAssistantFragment extends BaseFragment<TravelAssistantPresenter, TravelAssistantModel> implements TravelAssistantContract.View {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private boolean isFristCreate = true;
    private boolean isLoginFristCreate = true;
    public static final int TRIP_EDIT_RESULT_CODE = 10000;
    private int userId;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
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
    //行程助手攻略推荐
    @BindView(R.id.llyt_trip_strategy_recommend)
    LinearLayout mLlytStrategyRecommend;

    //有行程攻略列表
    private List<TripHomePageResponse.TripEntity> tripEntityList;
    //无行程攻略列表
    List<StrategyBean> strategyList;
    private MyTravelListAdapter myTravelListAdapter;

    private TravelAssisantRecommendAdapter strategyRecommendListAdapter;

    private int mPosition;
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
     //   getTravelAssistantList();
        //行程助手推荐  无行程
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(layoutManager.VERTICAL);
        mRvTravelAssisantRecommend.setLayoutManager(layoutManager);
        strategyRecommendListAdapter = new TravelAssisantRecommendAdapter(getActivity());
        strategyRecommendListAdapter.setTravelAssisantStrategyList(strategyList);
        mRvTravelAssisantRecommend.setAdapter(strategyRecommendListAdapter);

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
                mPosition = position;
                BlurBehind.getInstance().execute(getActivity(), new OnBlurCompleteListener() {
                    @Override
                    public void onBlurComplete() {
                        Bundle bundle = new Bundle();
                        Intent intent = new Intent(getActivity(), TravelAssistantEditActivity.class);
                        TripHomePageResponse.TripEntity tripEntity = tripEntityList.get(position);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        bundle.putSerializable("tripEntity",tripEntity);
                        intent.putExtras(bundle);
                        startActivityForResult(intent,0);
                    }
                });
            }
        });
        myTravelListAdapter.setMyTravelList(tripEntityList);
        mRvMyTravel.setAdapter(myTravelListAdapter);

    }

    @Override
    public void initData() {

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tripEntityList.clear();
                        myTravelListAdapter.notifyDataSetChanged();
                        getTravelAssistantList();
                    }
                }, 100);

            }
        });
    }

    @Override
    public void lazyInitView(View view) {

    }

    @Override
    public void getTravelAssistantHomePageResult(BackResult<TripHomePageResponse> res) {
        dismissProgressDialog();
        if (mSmartRefreshLayout != null)
        {
            mSmartRefreshLayout.finishRefresh();
        }
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

                    }

                    //行程助手攻略推荐
                    strategyList = tripHomePageResponse.getStrategy();
                    if(strategyList != null) {

                        if(strategyList.size() > 0) {
                            mLlytStrategyRecommend.setVisibility(View.VISIBLE);
                            strategyRecommendListAdapter.setTravelAssisantStrategyList(strategyList);
                            strategyRecommendListAdapter.notifyDataSetChanged();
                        }else {
                            mLlytStrategyRecommend.setVisibility(View.GONE);
                        }
                    } else {
                        mLlytStrategyRecommend.setVisibility(View.GONE);
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
    public void travelAssistantPlusADay(BackResult res) {

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
    public void intelligentProjectResult(BackResult<CreateTripResponse> res) {

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
        String token = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.TOKEN, "");
        switch (v.getId()) {
            case R.id.cardview_add_my_travel:

                if (!TextUtils.isEmpty(token))
                {
                    toActivity(CalendarActivity.class);
                } else {
                    onReLogin("");
                }
                break;
            case R.id.rlyt_add_trip:
                if (!TextUtils.isEmpty(token))
                {
                    toActivity(CalendarActivity.class);
                } else {
                    onReLogin("");
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void insertTrafficResult(BackResult<CreateTripResponse> res) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 0 && resultCode == RESULT_OK){
            //tripEntityList.clear();
            //getTravelAssistantList();
            TripHomePageResponse.TripEntity tripEntity = tripEntityList.get(mPosition);

            tripEntityList.remove(tripEntity);
            myTravelListAdapter.setMyTravelList(tripEntityList);
            myTravelListAdapter.notifyDataSetChanged();
        } else if(requestCode == 0 && resultCode == TRIP_EDIT_RESULT_CODE){ //编辑保存成功
            TripHomePageResponse.TripEntity tripEntity = (TripHomePageResponse.TripEntity)data.getSerializableExtra("tripEntity");
            if(tripEntity != null && tripEntityList != null)
            {
                for (int i = 0; i < tripEntityList.size(); i++) {
                    if (i == mPosition) {
                        TripHomePageResponse.TripEntity entity = tripEntityList.get(i);
                        entity.setTitle(tripEntity.getTitle());
                        entity.setStartDate(tripEntity.getStartDate());
                        entity.setEndDate(tripEntity.getEndDate());
                    }
                }
            }
            myTravelListAdapter.setMyTravelList(tripEntityList);
            myTravelListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        String token = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.TOKEN, "");
        if (!TextUtils.isEmpty(token)) {
            if (isLoginFristCreate) {
                userId = (int) SharedPreferencesUtils.getData(SharedPreferencesUtils.USER_ID, 0);
                if (validateInternet()) {
                    getTravelAssistantList();
                    isLoginFristCreate = false;
                }
            } else {
                isLoginFristCreate = false;
            }
        } else {
            if (isFristCreate) {
                toActivity(PhoneQuickLoginActivity.class);
                isFristCreate = false;

            }
        }
       // isLoginFristCreate = false;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {

            String token = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.TOKEN, "");
            if (TextUtils.isEmpty(token)) {

                toActivity(PhoneQuickLoginActivity.class);
            }
        }
    }

}
