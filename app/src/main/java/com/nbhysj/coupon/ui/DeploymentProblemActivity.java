package com.nbhysj.coupon.ui;


import android.os.Bundle;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.util.ToolbarHelper;

public class DeploymentProblemActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_deployment_problem;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ToolbarHelper.setBar(DeploymentProblemActivity.this, getResources().getString(R.string.str_my_ask_question), R.mipmap.nav_ico_back_black);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }
}
