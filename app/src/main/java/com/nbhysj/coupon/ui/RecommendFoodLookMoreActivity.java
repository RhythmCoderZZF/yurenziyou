package com.nbhysj.coupon.ui;


import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.FineFoodListAdapter;
import com.nbhysj.coupon.adapter.RecommendDeliciousFoodAdapter;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/03/02
 * description: 推荐美食
 */
public class RecommendFoodLookMoreActivity extends BaseActivity {
    @BindView(R.id.toolbar_space)
    View mToolbarSpace;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    //美食热门榜
    @BindView(R.id.rv_recommended_dishes)
    RecyclerView mRvRecommendedDishes;

    @Override
    public int getLayoutId() {
        return R.layout.activity_recommend_dishes;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setHeadBar(RecommendFoodLookMoreActivity.this, getResources().getString(R.string.str_recommended_dishes), R.mipmap.icon_left_arrow_black, "");

        ViewGroup.LayoutParams layoutParams = mToolbarSpace.getLayoutParams();//取控件当前的布局参数
        layoutParams.height = getStatusBarHeight();// 控件的高强制设成状态栏高度
        mToolbarSpace.setLayoutParams(layoutParams); //使设置好的布局参数应用到控件</pre>
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbarSpace.setVisibility(View.VISIBLE);
        } else {
            mToolbarSpace.setVisibility(View.GONE);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RecommendFoodLookMoreActivity.this);
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        mRvRecommendedDishes.setLayoutManager(linearLayoutManager);
        RecommendDeliciousFoodAdapter recommendDeliciousFoodAdapter = new RecommendDeliciousFoodAdapter(RecommendFoodLookMoreActivity.this);
        mRvRecommendedDishes.setAdapter(recommendDeliciousFoodAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }
}