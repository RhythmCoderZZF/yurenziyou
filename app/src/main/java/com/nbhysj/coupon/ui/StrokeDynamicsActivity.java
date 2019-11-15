package com.nbhysj.coupon.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.StrokeDynamicsItemAdapter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.ToolbarHelper;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/03/02
 * description：行程动态
 */
public class StrokeDynamicsActivity extends BaseActivity {
    //行程动态列表
    @BindView(R.id.rv_stroke_dynamics)
    RecyclerView mRvStrokeDynamicsList;

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_stroke_dynamics;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setBar(StrokeDynamicsActivity.this, getResources().getString(R.string.str_stroke_dynamics), R.mipmap.nav_ico_back_black);
        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(StrokeDynamicsActivity.this);
        // 设置布局管理器
        mRvStrokeDynamicsList.setLayoutManager(layoutManager);

       // StrokeDynamicsItemAdapter broadcastItemAdapter = new StrokeDynamicsItemAdapter(StrokeDynamicsActivity.this);
        //mRvStrokeDynamicsList.setAdapter(broadcastItemAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }
}
