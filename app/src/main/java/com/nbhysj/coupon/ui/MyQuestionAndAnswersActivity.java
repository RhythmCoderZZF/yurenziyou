package com.nbhysj.coupon.ui;


import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.util.ToolbarHelper;

import net.lucode.hackware.magicindicator.MagicIndicator;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/03/02
 * description：更多问题
 */
public class MyQuestionAndAnswersActivity extends BaseActivity {
    private String[] mTitles = new String[]{"待我来答", "提问", "同问", "回答"};

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @Override
    public int getLayoutId() {
        return R.layout.activity_my_question_and_answers;
    }

    @Override
    public void initView(Bundle savedInstanceState)
    {
        ToolbarHelper.setBar(MyQuestionAndAnswersActivity.this,getResources().getString(R.string.str_question_and_answers),R.mipmap.icon_left_arrow_black);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }
}
