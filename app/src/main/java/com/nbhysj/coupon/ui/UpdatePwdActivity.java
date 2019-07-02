package com.nbhysj.coupon.ui;

import android.os.Bundle;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.util.ToolbarHelper;

/**
 * @auther：hysj created on 2019/03/02
 * description：修改密码
 */
public class UpdatePwdActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_update_pwd;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ToolbarHelper.setBar(UpdatePwdActivity.this, "", R.mipmap.nav_ico_back_black);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }
}
