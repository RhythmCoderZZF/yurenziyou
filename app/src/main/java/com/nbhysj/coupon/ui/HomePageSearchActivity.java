package com.nbhysj.coupon.ui;


import android.os.Bundle;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.widget.HomePageSearchIndicator;

import butterknife.BindView;

/**
 * @author hysj created at 2019/07/31.
 * description : 首页搜索
 */
public class HomePageSearchActivity extends BaseActivity {

    //搜索
    @BindView(R.id.indicator_homepage_search)
    HomePageSearchIndicator mHomePageSearchIndicator;

    @Override
    public int getLayoutId()
    {
        return R.layout.activity_home_page_search;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }
}
