package com.nbhysj.coupon.ui;

import android.os.Bundle;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.ToolbarHelper;

/**
 * @auther：hysj created on 2019/04/28
 * description：退款详情
 */
public class RefundDetailsActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_refund_details;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setBar(RefundDetailsActivity.this,getResources().getString(R.string.str_refund_detail),R.mipmap.icon_left_arrow_black);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }
}
