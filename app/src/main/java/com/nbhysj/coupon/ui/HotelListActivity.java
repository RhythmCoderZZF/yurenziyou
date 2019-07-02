package com.nbhysj.coupon.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.NearbyScenicSpotAdapter;
import com.nbhysj.coupon.model.response.NearbyScenicSpotsResponse;
import com.nbhysj.coupon.util.ToolbarHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/05/09
 * description：酒店列表
 */
public class HotelListActivity extends BaseActivity {
    @BindView(R.id.rv_hotels_near_scenic_spots)
    RecyclerView mRvHotelNearScenicSpots;
    @BindView(R.id.toolbar_space)
    View mToolbarSpace;

    @Override
    public int getLayoutId() {
        return R.layout.activity_hotel_near_scenic_spots_list;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ViewGroup.LayoutParams layoutParams = mToolbarSpace.getLayoutParams();//取控件当前的布局参数
        layoutParams.height = getStatusBarHeight();// 控件的高强制设成状态栏高度
        mToolbarSpace.setLayoutParams(layoutParams); //使设置好的布局参数应用到控件</pre>
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbarSpace.setVisibility(View.VISIBLE);
        } else {
            mToolbarSpace.setVisibility(View.GONE);
        }
        ToolbarHelper.setHeadBar(HotelListActivity.this, "景点附近酒店", R.mipmap.icon_left_arrow_black, "");

        List<NearbyScenicSpotsResponse> nearbyScenicSpotsList = new ArrayList<>();
        NearbyScenicSpotsResponse nearbyScenicSpots = new NearbyScenicSpotsResponse();
        nearbyScenicSpots.setScenicSpotsName("ZMAX潮漫酒店(宁波余姚丰山路店)");
        nearbyScenicSpots.setScenicSpotsPhoto("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1556527244878&di=eb36e56d0934343f22c4ad150ca0c356&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2Ff13d1f7516401ba843953d76787739424792c0ae36e85-wJr1dZ_fw658");
        nearbyScenicSpots.setScenicSpotsDistance("465");
        nearbyScenicSpots.setStarLevel(4);
        nearbyScenicSpots.setScenicSpotsTicketPrice("558");
        nearbyScenicSpots.setScenicSpotsScore("4.6");

        NearbyScenicSpotsResponse nearbyScenicSpots1 = new NearbyScenicSpotsResponse();
        nearbyScenicSpots1.setScenicSpotsName("ZMAX潮漫酒店(宁波余姚丰山路店)");
        nearbyScenicSpots1.setScenicSpotsPhoto("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1556527244878&di=d6b28cf6f432b2721474d6bedd06351c&imgtype=0&src=http%3A%2F%2Fimage.qmango.com%2Fhotelimg%2Fc2%2F286035%2F%25E5%25A4%25A7%25E5%25BA%258A2%25E7%25B1%25B32.jpg");
        nearbyScenicSpots1.setScenicSpotsDistance("666");
        nearbyScenicSpots1.setStarLevel(3);
        nearbyScenicSpots1.setScenicSpotsTicketPrice("233");
        nearbyScenicSpots1.setScenicSpotsScore("5.6");

        NearbyScenicSpotsResponse nearbyScenicSpots2 = new NearbyScenicSpotsResponse();
        nearbyScenicSpots2.setScenicSpotsName("ZMAX潮漫酒店(宁波余姚丰山路店)");
        nearbyScenicSpots2.setScenicSpotsPhoto("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1556527244878&di=553e9cd0077f885858577244364141a9&imgtype=0&src=http%3A%2F%2Fimage.qmango.com%2Fhotelimg%2Fc2%2F203939%2F%25E5%258F%258C%25E4%25BA%25BA%25E5%25AE%25A2%25E6%2588%25BF1_%25E7%259C%258B%25E5%259B%25BE%25E7%258E%258B.jpg");
        nearbyScenicSpots2.setScenicSpotsDistance("553");
        nearbyScenicSpots2.setStarLevel(3);
        nearbyScenicSpots2.setScenicSpotsTicketPrice("344");
        nearbyScenicSpots2.setScenicSpotsScore("8.6");

        nearbyScenicSpotsList.add(nearbyScenicSpots);
        nearbyScenicSpotsList.add(nearbyScenicSpots1);
        nearbyScenicSpotsList.add(nearbyScenicSpots2);


        LinearLayoutManager layoutManager = new LinearLayoutManager(HotelListActivity.this);
        layoutManager.setOrientation(layoutManager.VERTICAL);
        mRvHotelNearScenicSpots.setLayoutManager(layoutManager);
       /* HotelsNearbyScenicSpotAdapter nearbyScenicSpotAdapter = new HotelsNearbyScenicSpotAdapter(HotelListActivity.this);
        nearbyScenicSpotAdapter.setNearbyScenicSpotsList(nearbyScenicSpotsList);
        mRvHotelNearScenicSpots.setAdapter(nearbyScenicSpotAdapter);*/
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }
}
