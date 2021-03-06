package com.nbhysj.coupon.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.TravelAssistantAddInteractionAdapter;
import com.nbhysj.coupon.adapter.TravelAssistantAddScenicSpotAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.common.Enum.MchTypeEnum;
import com.nbhysj.coupon.contract.TravelAssistantContract;
import com.nbhysj.coupon.model.TravelAssistantModel;
import com.nbhysj.coupon.model.request.AddMchRequest;
import com.nbhysj.coupon.model.response.AddCountyResponse;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.CountryBean;
import com.nbhysj.coupon.model.response.CreateTripResponse;
import com.nbhysj.coupon.model.response.TravelAssistantDetailCountryBean;
import com.nbhysj.coupon.model.response.TripDetailsResponse;
import com.nbhysj.coupon.model.response.TripHomePageResponse;
import com.nbhysj.coupon.model.response.TripRouteMapResponse;
import com.nbhysj.coupon.model.response.TripScenicSpotAddCountryBean;
import com.nbhysj.coupon.model.response.WeatherResponse;
import com.nbhysj.coupon.presenter.TravelAssistantPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/03/08
 * description：行程详情添加景点
 */
public class TravelAssisantAddInteractionFragment extends BaseFragment<TravelAssistantPresenter, TravelAssistantModel> implements TravelAssistantContract.View {
    private static final String TRIPID = "tripId";
    private static final String COUNTYID = "countyId";
    private static final String DAY_INDEX = "dayIndex";

    private int mTripId;
    private String mCountyId;
    //天数索引
    private int mDayIndex;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    //添加风景
    @BindView(R.id.rv_add_scenic_spot)
    RecyclerView mRvAddScenicSpot;
    @BindView(R.id.rlyt_no_data)
    RelativeLayout mRlytNoData;
    private List<TripScenicSpotAddCountryBean.TravelAssistantAddScenicSpotEntity> travelAssistantAddScenicSpotList;
    private TravelAssistantAddInteractionAdapter assistantAddInteractionAdapter;
    private boolean isOnLoadMore = false;
    private int mPage = 1;
    private int mPageSize = 10;
    private int mTotalPageCount;
    private AddScenicSpotListener addScenicSpotListener;

    public TravelAssisantAddInteractionFragment() {
        // Required empty public constructor
    }

    public static TravelAssisantAddInteractionFragment newInstance(int mTripId, String mCountyId, int dayIndex) {
        TravelAssisantAddInteractionFragment fragment = new TravelAssisantAddInteractionFragment();
        Bundle args = new Bundle();
        args.putInt(TRIPID, mTripId);
        args.putString(COUNTYID, mCountyId);
        args.putInt(DAY_INDEX, dayIndex);
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
        return R.layout.fragment_travel_assistant_add_scenic_spot;
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView(View v) {

        mTripId = getArguments().getInt(TRIPID, 0);
        mCountyId = getArguments().getString(COUNTYID, "");
        mDayIndex = getArguments().getInt(DAY_INDEX, 0);

        if (travelAssistantAddScenicSpotList == null) {

            travelAssistantAddScenicSpotList = new ArrayList<>();

        } else {
            travelAssistantAddScenicSpotList.clear();
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(layoutManager.VERTICAL);
        mRvAddScenicSpot.setLayoutManager(layoutManager);
        assistantAddInteractionAdapter = new TravelAssistantAddInteractionAdapter(getActivity(), new TravelAssistantAddInteractionAdapter.ScenicSpotAddListener() {
            @Override
            public void setScenicSpotAddListener(TripScenicSpotAddCountryBean.TravelAssistantAddScenicSpotEntity travelAssistantAddScenicSpotEntity) {

                int mchId = travelAssistantAddScenicSpotEntity.getMchId();
                addScenicSpotRequest(mchId);
                //
            }
        });
        assistantAddInteractionAdapter.setTravelAssistantInteractionList(travelAssistantAddScenicSpotList);
        mRvAddScenicSpot.setAdapter(assistantAddInteractionAdapter);
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
    public void getTravelAssistantMchListResult(BackResult<TripScenicSpotAddCountryBean> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    TripScenicSpotAddCountryBean tripScenicSpotAddCountryBean = res.getData();
                    List<TripScenicSpotAddCountryBean.TravelAssistantAddScenicSpotEntity> scenicSpotList = tripScenicSpotAddCountryBean.getResult();

                    if (isOnLoadMore) {

                        mSmartRefreshLayout.finishLoadMore();
                    } else {

                        travelAssistantAddScenicSpotList.clear();
                        assistantAddInteractionAdapter.notifyDataSetChanged();
                        mSmartRefreshLayout.finishRefresh();
                        mSmartRefreshLayout.setNoMoreData(false);
                    }
                    BasePaginationResult paginationResult = tripScenicSpotAddCountryBean.getPage();
                    mTotalPageCount = paginationResult.getPageCount();
                    if (mTotalPageCount == 0) {
                        mRlytNoData.setVisibility(View.VISIBLE);
                    } else {
                        mRlytNoData.setVisibility(View.GONE);
                    }
                    if (scenicSpotList != null) {
                        travelAssistantAddScenicSpotList.addAll(scenicSpotList);
                    }
                    assistantAddInteractionAdapter.setTravelAssistantInteractionList(travelAssistantAddScenicSpotList);
                    assistantAddInteractionAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
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
    public void insertTrafficResult(BackResult<CreateTripResponse> res) {

    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(getActivity(), Constants.getResultMsg(msg));
    }

    @Override
    public void initData() {

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        mPage = 1;
                        travelAssistantAddScenicSpotList.clear();
                        //scenicSpotsListAdapter.notifyDataSetChanged();
                        getTravelAssisantAddScenicSpot();

                    }
                }, 100);
            }
        });


        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        isOnLoadMore = true;
                        try {
                            if (mTotalPageCount == travelAssistantAddScenicSpotList.size()) {
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            } else {
                                mPage++;
                                getTravelAssisantAddScenicSpot();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 200);
            }
        });
    }

    @Override
    public void lazyInitView(View view) {
        getTravelAssisantAddScenicSpot();
    }

    @Override
    public void getTripDetailsResult(BackResult<TripDetailsResponse> res) {

    }

    @Override
    public void insertCountyResult(BackResult<AddCountyResponse> res) {

    }

    @Override
    public void intelligentProjectResult(BackResult<CreateTripResponse> res) {

    }

    @Override
    public void insertPlaceMchResult(BackResult<CreateTripResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    if (assistantAddInteractionAdapter != null)
                    {
                        assistantAddInteractionAdapter.notifyDataSetChanged();
                    }

                    if (addScenicSpotListener != null) {
                        addScenicSpotListener.setAddScenicSpotListener();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }

    @Override
    public void insertNoteResult(BackResult<CreateTripResponse> res) {

    }

    @Override
    public void delTripPlaceResult(BackResult res) {

    }

    //根据城市获取景点
    public void getTravelAssisantAddScenicSpot() {

        if (validateInternet()) {

            String mchScenicType = MchTypeEnum.getEnumByKey(0);
            mPresenter.getTravelAssistantMchList(mTripId, mCountyId, mchScenicType, mPage, mPageSize);
        }
    }

    public void addScenicSpotRequest(int mchId) {

        if (validateInternet()) {

            AddMchRequest addMchRequest = new AddMchRequest();
            addMchRequest.setMchId(mchId);
            addMchRequest.setDayIndex(mDayIndex);
            addMchRequest.setTripId(mTripId);
            String mchType = MchTypeEnum.getEnumByKey(3);
            addMchRequest.setTripPlaceType(mchType);
            mPresenter.insertPlaceMch(addMchRequest);
        }
    }

    public interface AddScenicSpotListener {

        void setAddScenicSpotListener();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            addScenicSpotListener = (AddScenicSpotListener) context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
