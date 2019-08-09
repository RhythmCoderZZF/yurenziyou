package com.nbhysj.coupon.ui;
import android.os.Bundle;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.util.ToolbarHelper;

public class LetMeAnswerActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_let_me_answer;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setBar(LetMeAnswerActivity.this,getResources().getString(R.string.str_let_me_answer),R.mipmap.icon_left_arrow_black);
    }

    @Override
    public void initData() {


    }

    @Override
    public void initPresenter() {

    }
}
