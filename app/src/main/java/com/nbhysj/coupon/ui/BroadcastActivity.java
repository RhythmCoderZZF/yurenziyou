package com.nbhysj.coupon.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.BroadcastItemAdapter;
import com.nbhysj.coupon.model.response.ImageData;
import com.nbhysj.coupon.util.ToolbarHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/03/13
 * description：广播
 */
public class BroadcastActivity extends BaseActivity {

    @BindView(R.id.rv_broadcast)
    RecyclerView mRvBroadcastList;
    private List<ImageData> imageDataList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_broadcast;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setBar(BroadcastActivity.this, getResources().getString(R.string.str_broadcast), R.mipmap.nav_ico_back_black);

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
        LinearLayoutManager layoutManager = new LinearLayoutManager(BroadcastActivity.this);
        // 设置布局管理器
        mRvBroadcastList.setLayoutManager(layoutManager);

        BroadcastItemAdapter broadcastItemAdapter = new BroadcastItemAdapter(BroadcastActivity.this);
        broadcastItemAdapter.setBroadcastList(imageDataList);
        mRvBroadcastList.setAdapter(broadcastItemAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }
}
