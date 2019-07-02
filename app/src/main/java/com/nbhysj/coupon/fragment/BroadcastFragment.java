package com.nbhysj.coupon.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.BroadcastItemAdapter;
import com.nbhysj.coupon.adapter.MyTravelListAdapter;
import com.nbhysj.coupon.adapter.TravelAssisantRecommendAdapter;
import com.nbhysj.coupon.model.response.ImageData;
import com.nbhysj.coupon.ui.BroadcastActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BroadcastFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.rv_broadcast)
    RecyclerView mRvBroadcastList;
    private List<ImageData> imageDataList = new ArrayList<>();

    public BroadcastFragment() {
        // Required empty public constructor
    }

    public static BroadcastFragment newInstance(String param1, String param2) {
        BroadcastFragment fragment = new BroadcastFragment();
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
        return R.layout.fragment_broadcast;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView(View v) {


        if (imageDataList == null) {

            imageDataList = new ArrayList<>();
        } else {
            imageDataList.clear();
        }

        ImageData imageData = new ImageData();
        imageData.setUrl("http://pic44.nipic.com/20140723/19276212_171901262000_2.jpg");

        ImageData imageData1 = new ImageData();
        imageData1.setUrl("http://gss0.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/lvpics/w=1000/sign=a669f57d3a12b31bc76cc929b628377a/503d269759ee3d6d801feef140166d224f4ade2b.jpg");

        ImageData imageData2 = new ImageData();
        imageData2.setUrl("http://img.juimg.com/tuku/yulantu/140818/330657-140QPJ62723.jpg");
        imageDataList.add(imageData);
        imageDataList.add(imageData1);
        imageDataList.add(imageData2);

        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        // 设置布局管理器
        mRvBroadcastList.setLayoutManager(layoutManager);

        BroadcastItemAdapter broadcastItemAdapter = new BroadcastItemAdapter(getActivity());
        broadcastItemAdapter.setBroadcastList(imageDataList);
        mRvBroadcastList.setAdapter(broadcastItemAdapter);

    }

    @Override
    public void initData() {

    }

    @Override
    public void lazyInitView(View view) {

    }

}
