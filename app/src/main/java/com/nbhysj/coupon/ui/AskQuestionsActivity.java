package com.nbhysj.coupon.ui;

import android.os.Bundle;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.ToolbarHelper;

/**
 * @auther：hysj created on 2019/04/10
 * description: 我要提问
 */
public class AskQuestionsActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_ask_questions;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        StatusBarCompat.setStatusBarColor(this, -131077);
        ToolbarHelper.setBar(AskQuestionsActivity.this, getResources().getString(R.string.str_my_ask_question), R.mipmap.nav_ico_back_black);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }
}
