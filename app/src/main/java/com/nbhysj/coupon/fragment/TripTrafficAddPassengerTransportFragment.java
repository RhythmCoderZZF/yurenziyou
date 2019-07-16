package com.nbhysj.coupon.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.DestinationContract;
import com.nbhysj.coupon.model.DestinationModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.DestinationResponse;
import com.nbhysj.coupon.model.response.HotScenicSpotResponse;
import com.nbhysj.coupon.presenter.DestinationPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/07/07
 * description：行程助手添加交通(客运)
 */
public class TripTrafficAddPassengerTransportFragment extends BaseFragment<DestinationPresenter, DestinationModel> implements DestinationContract.View {
    private static final String ARG_PARAM_TRIPID = "tripId";

    //列车时间
    @BindView(R.id.tv_train_time)
    TextView mTvTrainTime;

    //列车时间
    @BindView(R.id.tv_departure_the_city)
    TextView mTvDepartureTheCity;

    //列车时间
    @BindView(R.id.tv_reaching_the_city)
    TextView mTvReachingTheCity;

    //出行时间
    private String trainTime;

    //行程id
    private int tripId;
    public TripTrafficAddPassengerTransportFragment() {
        // Required empty public constructor
    }

    public static TripTrafficAddPassengerTransportFragment newInstance(int tripId) {
        TripTrafficAddPassengerTransportFragment fragment = new TripTrafficAddPassengerTransportFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM_TRIPID, tripId);
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
        return R.layout.fragment_travel_assistant_traffic_add_train;
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }


    @Override
    protected void initView(View v) {


        trainTime = getActivity().getIntent().getStringExtra("trainTime");       //出行时间
        tripId = getActivity().getIntent().getIntExtra(ARG_PARAM_TRIPID,0); //行程id
        mTvTrainTime.setText(trainTime);

    }

    @Override
    public void initData() {

    }

    @Override
    public void getDestinationHomePageResult(BackResult<DestinationResponse> res) {

    }

    @Override
    public void findMchBycityNameResult(BackResult<HotScenicSpotResponse> res) {
    }

    @Override
    public void lazyInitView(View view) {

    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(getActivity(), Constants.getResultMsg(msg));

    }

    @OnClick({R.id.tv_join_the_itinerary})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.tv_join_the_itinerary:

                break;
                default:break;

        }
    }
}
