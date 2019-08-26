package com.nbhysj.coupon.ui;

import android.os.Bundle;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.util.ToolbarHelper;

/**
 * @auther：hysj created on 2019/03/02
 * description：用车地址选择
 */
public class VehicleAddressSelectionActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_vehicle_address_selection;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setBar(VehicleAddressSelectionActivity.this,getResources().getString(R.string.str_address_select),R.mipmap.icon_left_arrow_black);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }
}
