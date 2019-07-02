package com.nbhysj.coupon.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.FineFoodListAdapter;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/06/04
 * description：酒店民宿(房源)
 */
public class HousingResourcesFragment extends LazyFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //我的行程
    @BindView(R.id.rv_fine_food)
    RecyclerView mRvFineFood;
    @BindView(R.id.rv_house_resources)
    RecyclerView mRvHouseResources;

    public HousingResourcesFragment() {
        // Required empty public constructor
    }

    public static HousingResourcesFragment newInstance(String param1, String param2) {
        HousingResourcesFragment fragment = new HousingResourcesFragment();
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
        return R.layout.fragment_house_resources;
    }

    @Override
    public void initPresenter() {

    }


    @Override
    protected void initData() {

    }


    @Override
    public void lazyInitView(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        mRvFineFood.setLayoutManager(linearLayoutManager);
        FineFoodListAdapter fineFoodListAdapter = new FineFoodListAdapter(getActivity());
        // mRvFineFood.setAdapter(fineFoodListAdapter);
    }
}
