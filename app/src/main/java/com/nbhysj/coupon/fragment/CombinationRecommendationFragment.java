package com.nbhysj.coupon.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.TravelAssisantRecommendAdapter;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/06/04
 * description：组合推荐
 */
public class CombinationRecommendationFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //行程助手推荐
    @BindView(R.id.rv_recommended_for_you_travel)
    RecyclerView mRvTravelAssisantRecommend;
    //我的行程
    @BindView(R.id.rv_my_travel)
    RecyclerView mRvMyTravel;

    public CombinationRecommendationFragment() {
        // Required empty public constructor
    }

    public static CombinationRecommendationFragment newInstance(String param1, String param2) {
        CombinationRecommendationFragment fragment = new CombinationRecommendationFragment();
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

    }

    @Override
    public void initView(View v) {

        //行程助手推荐
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(layoutManager.VERTICAL);
        mRvTravelAssisantRecommend.setLayoutManager(layoutManager);
        TravelAssisantRecommendAdapter travelAssisantRecommendAdapter = new TravelAssisantRecommendAdapter(getActivity());
        mRvTravelAssisantRecommend.setAdapter(travelAssisantRecommendAdapter);


        //我的行程列表
        LinearLayoutManager travelListAdapterLayoutManager = new LinearLayoutManager(getActivity());
        travelListAdapterLayoutManager.setOrientation(travelListAdapterLayoutManager.VERTICAL);
        mRvMyTravel.setLayoutManager(travelListAdapterLayoutManager);
        // MyTravelListAdapter myTravelListAdapter = new MyTravelListAdapter(getActivity());
        //mRvMyTravel.setAdapter(myTravelListAdapter);

    }

    @Override
    public void initData() {

    }

    @Override
    public void lazyInitView(View view) {

    }
}
