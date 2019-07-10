package com.nbhysj.coupon.fragment;

import android.os.Bundle;
import android.view.View;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.DestinationContract;
import com.nbhysj.coupon.model.DestinationModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.DestinationResponse;
import com.nbhysj.coupon.model.response.HotScenicSpotResponse;
import com.nbhysj.coupon.presenter.DestinationPresenter;

/**
 * @auther：hysj created on 2019/07/07
 * description：行程助手添加交通(驾车)
 */
public class TravelAssistantTrafficAddDriveFragment extends BaseFragment<DestinationPresenter, DestinationModel> implements DestinationContract.View {
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

}
