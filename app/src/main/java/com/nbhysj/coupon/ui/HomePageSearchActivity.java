package com.nbhysj.coupon.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.fragment.MyAllOrderListFragment;
import com.nbhysj.coupon.fragment.MyOrderFragmentManager;
import com.nbhysj.coupon.fragment.PendingCommentOrderListFragment;
import com.nbhysj.coupon.fragment.PendingPaymentListFragment;
import com.nbhysj.coupon.fragment.PendingTravelListFragment;
import com.nbhysj.coupon.fragment.RefundOrderListFragment;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.widget.HomePageSearchIndicator;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author hysj created at 2019/07/31.
 * description : 首页搜索
 */
public class HomePageSearchActivity extends BaseActivity {
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    private String[] titles = new String[]{"综合", "xiu", "住宿", "景点", "美食", "互动", "攻略"};
    private List<Fragment> fragmentList;
    @Override
    public int getLayoutId()
    {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_home_page_search;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        if(fragmentList == null){

            fragmentList = new ArrayList<>();
        } else {

            fragmentList.clear();
        }

        fragmentList.add(new MyAllOrderListFragment());
        fragmentList.add(new PendingPaymentListFragment());
        fragmentList.add(new PendingTravelListFragment());
        fragmentList.add(new PendingCommentOrderListFragment());
        fragmentList.add(new RefundOrderListFragment());
        fragmentList.add(new PendingCommentOrderListFragment());
        fragmentList.add(new RefundOrderListFragment());

        FragmentPagerAdapter adapter = new MyOrderFragmentManager(getSupportFragmentManager(), titles,fragmentList);
        viewPager.setAdapter(adapter);
        tabLayout.setViewPager(viewPager, titles);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }

    @OnClick({R.id.tv_cancel_search})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.tv_cancel_search:
                HomePageSearchActivity.this.finish();
                break;
                default:break;
        }
    }
}
