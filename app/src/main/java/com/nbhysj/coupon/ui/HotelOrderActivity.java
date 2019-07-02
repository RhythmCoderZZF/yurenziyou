package com.nbhysj.coupon.ui;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.util.ToolbarHelper;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/05/09
 * description：酒店下单
 */
public class HotelOrderActivity extends BaseActivity {
    @BindView(R.id.toolbar_space)
    View mToolbarSpace;

    @Override
    public int getLayoutId() {
        return R.layout.activity_hotel_order;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setHeadBar(HotelOrderActivity.this, "鱼人酒店（宁波总部)", R.mipmap.icon_left_arrow_black, "");

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

    }

    @Override
    public void initPresenter() {

    }
}
