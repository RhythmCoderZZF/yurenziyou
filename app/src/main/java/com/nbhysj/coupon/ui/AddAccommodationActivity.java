package com.nbhysj.coupon.ui;


import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.AddAccommodationAdapter;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/03/08
 * description：添加住宿
 */
public class AddAccommodationActivity extends BaseActivity {
    @BindView(R.id.toolbar_space)
    View mToolbarSpace;
    //住宿列表
    @BindView(R.id.rv_select_accommodation)
    RecyclerView mRvSelectAccommodation;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_accommodation;
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
    }

    @Override
    public void initData() {

        LinearLayoutManager linearLayout = new LinearLayoutManager(AddAccommodationActivity.this);
        linearLayout.setOrientation(linearLayout.VERTICAL);
        mRvSelectAccommodation.setLayoutManager(linearLayout);
        AddAccommodationAdapter setMealDetailAdapter = new AddAccommodationAdapter(AddAccommodationActivity.this);
        mRvSelectAccommodation.setAdapter(setMealDetailAdapter);
    }

    @Override
    public void initPresenter() {

    }
}
