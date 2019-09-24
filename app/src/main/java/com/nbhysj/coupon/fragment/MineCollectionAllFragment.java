package com.nbhysj.coupon.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.MineCollectionAllItemAdapter;

import butterknife.BindView;

public class MineCollectionAllFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.rv_mine_collection_all)
    RecyclerView mRvMineCollectionAll;

    public MineCollectionAllFragment() {
        // Required empty public constructor
    }

    public static MineCollectionAllFragment newInstance(String param1, String param2) {
        MineCollectionAllFragment fragment = new MineCollectionAllFragment();
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
        return R.layout.fragment_collection_all;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView(View v) {

        //我的收藏全部
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(layoutManager.VERTICAL);
        mRvMineCollectionAll.setLayoutManager(layoutManager);
        MineCollectionAllItemAdapter collectionAllItemAdapter = new MineCollectionAllItemAdapter(getActivity());
        mRvMineCollectionAll.setAdapter(collectionAllItemAdapter);

    }

    @Override
    public void initData() {

    }

    @Override
    public void lazyInitView(View view) {

    }

}
