package com.nbhysj.coupon.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.TravelAssistantDetailAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.TravelAssistantContract;
import com.nbhysj.coupon.dialog.OprateDialog;
import com.nbhysj.coupon.framework.RxBus;
import com.nbhysj.coupon.model.TravelAssistantModel;
import com.nbhysj.coupon.model.request.DeleteTripPlaceRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CountryBean;
import com.nbhysj.coupon.model.response.CreateTripResponse;
import com.nbhysj.coupon.model.response.TravelAssistantDetailCountryBean;
import com.nbhysj.coupon.model.response.TripDetailsResponse;
import com.nbhysj.coupon.model.response.TripHomePageResponse;
import com.nbhysj.coupon.model.response.TripScenicSpotAddCountryBean;
import com.nbhysj.coupon.presenter.TravelAssistantPresenter;
import com.nbhysj.coupon.ui.LoginActivity;
import com.nbhysj.coupon.ui.TravelAssistantDetailsActivity;
import com.nbhysj.coupon.ui.TravelAssistantRemarksActivity;
import com.umeng.socialize.UMShareAPI;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;

/**
 * @auther：hysj created on 2019/03/08
 * description：行程详情
 */
public class TravelAssisantDetailFragment extends BaseFragment<TravelAssistantPresenter, TravelAssistantModel> implements TravelAssistantContract.View {
    private static final String TRIPID = "tripId";
    private static final String COUNTYID = "countyId";
    private static final String DAY_INDEX = "dayIndex";

    private int mTripId;
    //天数索引
    private int mDayIndex;

    @BindView(R.id.rv_travel_assistant_detail)
    RecyclerView mRvTravelAssistantDetail;
    @BindView(R.id.tv_scenic_spot_flow)
    TextView mTvScenicSpotFlow;
    @BindView(R.id.tv_travel_assistant_detail_day)
    TextView mTvtravelAssistantDetailDay;

    TripDetailsResponse.DetailsEntity detailsEntity;
    private List<TripDetailsResponse.TripDetailsEntity> tripDetailsList;
    TravelAssistantDetailAdapter travelAssistantDetailAdapter;

    private int mPosition;
    private DeleteTravelAssistantPlaceListener deleteTravelAssistantPlaceListener;
    private TripDetailsResponse.TripDetailsEntity mTripDetailsEntity;
    private int REMARKS_EDIT_REQUEST_CODE = 1;

    public TravelAssisantDetailFragment() {
        // Required empty public constructor
    }

    public void setTravelAssisantDetailList(TripDetailsResponse.DetailsEntity detailsEntity) {

        this.detailsEntity = detailsEntity;
        if (tripDetailsList == null) {

            tripDetailsList = new ArrayList<>();
        } else {
            tripDetailsList.clear();
        }


         /*RxBus.$().(this, "my tag", new RxBus.Callback<String>() {
             @Override
             public void onEvent(String s) {
                 Log.e("eventTag", s);
             }
         });*/

    }

    public static TravelAssisantDetailFragment newInstance(int mTripId, int dayIndex) {
        TravelAssisantDetailFragment fragment = new TravelAssisantDetailFragment();
        Bundle args = new Bundle();
        args.putInt(TRIPID, mTripId);
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
        return R.layout.fragment_travel_assistant_detail;
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView(View v) {

        mTripId = getArguments().getInt(TRIPID, 0);
        mDayIndex = getArguments().getInt(DAY_INDEX, 0);
        mTvScenicSpotFlow.getBackground().setAlpha(85);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(layoutManager.VERTICAL);
        mRvTravelAssistantDetail.setLayoutManager(layoutManager);
        travelAssistantDetailAdapter = new TravelAssistantDetailAdapter(getActivity(), new TravelAssistantDetailAdapter.TravelAssistantDetailItemListener() {
            @Override
            public void setItemDeleteListener(int position, TripDetailsResponse.TripDetailsEntity tripDetailsEntity) {

                mPosition = position;
                OprateDialog oprateDialog = new OprateDialog(getActivity()).builder().setTitle("确认要删除该行程吗");
                oprateDialog.setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

                oprateDialog.setPositiveButton("确认", getResources().getColor(R.color.color_high_light_green), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int tripPlaceId = tripDetailsEntity.getTripPlaceId();
                        showProgressDialog(getActivity());
                        mDialog.setTitle("正在删除...");
                        delTripPlace(tripPlaceId);
                    }
                });

                oprateDialog.show();

                // deleteTravelAssistantPlaceListener.setDeleteTravelAssistantPlaceListener(position,tripPlaceId);

            }

            @Override
            public void setItemRemarksEditListener(int position, TripDetailsResponse.TripDetailsEntity tripDetailsEntity) {
                mPosition = position;
                mTripDetailsEntity = tripDetailsEntity;
                String title = tripDetailsEntity.getTitle();
                Intent intent = new Intent();
                intent.setClass(getActivity(), TravelAssistantRemarksActivity.class);
                intent.putExtra("remarks", title);
                intent.putExtra("tripId", mTripId);
                intent.putExtra("dayIndex", mDayIndex);
                intent.putExtra("isAddRemarks", false); //编辑 false : 添加 true
                getActivity().startActivityForResult(intent, REMARKS_EDIT_REQUEST_CODE);

            }
        });

        travelAssistantDetailAdapter.setTravelAssistantDetailList(tripDetailsList);
        mRvTravelAssistantDetail.setAdapter(travelAssistantDetailAdapter);

        String circuit = detailsEntity.getCircuit();
        int dayIndex = detailsEntity.getDayIndex();

        mTvtravelAssistantDetailDay.setText(String.valueOf("D" + dayIndex + "."));
        if (!TextUtils.isEmpty(circuit)) {
            mTvScenicSpotFlow.setVisibility(View.VISIBLE);
            mTvScenicSpotFlow.setText(circuit);
        } else {
            mTvScenicSpotFlow.setVisibility(View.GONE);
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void lazyInitView(View view) {
        tripDetailsList = detailsEntity.getTripDetails();

        travelAssistantDetailAdapter.setTravelAssistantDetailList(tripDetailsList);
        travelAssistantDetailAdapter.notifyDataSetChanged();
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
    public void delTripPlaceResult(BackResult res) {

        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:

                TripDetailsResponse.TripDetailsEntity tripDetailsEntity = tripDetailsList.get(mPosition);
                tripDetailsList.remove(tripDetailsEntity);
                travelAssistantDetailAdapter.setTravelAssistantDetailList(tripDetailsList);
                travelAssistantDetailAdapter.notifyDataSetChanged();

                break;
            default:
                showToast(getActivity(), Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void insertNoteResult(BackResult<CreateTripResponse> res) {

    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(getActivity(), Constants.getResultMsg(msg));
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            String action = intent.getAction();
            if (Constants.BROCAST_ACTION_TRIP_ASSISANT.equals(action)) {

                // showToast(getActivity(),"111");
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    public interface DeleteTravelAssistantPlaceListener {

        void setDeleteTravelAssistantPlaceListener(int position, int tripPlaceId);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            deleteTravelAssistantPlaceListener = (DeleteTravelAssistantPlaceListener) context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删除行程助手行程点
    public void delTripPlace(int tripPlaceId) {

        if (validateInternet()) {

            DeleteTripPlaceRequest deleteTripPlaceRequest = new DeleteTripPlaceRequest();
            deleteTripPlaceRequest.setId(tripPlaceId);
            mPresenter.delTripPlace(deleteTripPlaceRequest);

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REMARKS_EDIT_REQUEST_CODE && resultCode == RESULT_OK) {
            String remarks = data.getStringExtra("remarks");

            tripDetailsList = detailsEntity.getTripDetails();
            tripDetailsList.get(mPosition).setTitle(remarks);
            travelAssistantDetailAdapter.setTravelAssistantDetailList(tripDetailsList);
            travelAssistantDetailAdapter.notifyDataSetChanged();
           /* if(fmPagerAdapter != null) {
                fmPagerAdapter.notifyDataSetChanged();
            }

            if(pager != null) {
                pager.getAdapter().notifyDataSetChanged();
            }

            mIndicator.setViewPager(pager,0);*/

            // showToast(TravelAssistantDetailsActivity.this,"2222");
        }
    }
}
