package com.nbhysj.coupon.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.FineFoodListAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.DestinationContract;
import com.nbhysj.coupon.model.DestinationModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.DestinationResponse;
import com.nbhysj.coupon.model.response.HotScenicSpotResponse;
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.presenter.DestinationPresenter;
import com.nbhysj.coupon.ui.TravelAssistantLocationCityActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * @auther：hysj created on 2019/07/07
 * description：行程助手添加交通(火车)
 */
public class TravelAssistantTrafficAddTrainFragment extends BaseFragment<DestinationPresenter, DestinationModel> implements DestinationContract.View {
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

    public TravelAssistantTrafficAddTrainFragment() {
        // Required empty public constructor
    }

    public static TravelAssistantTrafficAddTrainFragment newInstance(int tripId) {
        TravelAssistantTrafficAddTrainFragment fragment = new TravelAssistantTrafficAddTrainFragment();
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

    @OnClick({R.id.tv_departure_the_city,R.id.tv_reaching_the_city})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.tv_departure_the_city:

                Intent intent = new Intent();
                intent.setClass(getActivity(), TravelAssistantLocationCityActivity.class);
                intent.putExtra("tripId",tripId);
                startActivityForResult(intent,0);
                break;
            case R.id.tv_reaching_the_city:

                break;
                default:break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0 && resultCode == RESULT_OK)
        {

        }
    }
}
