package com.nbhysj.coupon.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.flyco.tablayout.SlidingTabLayout;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.FlashSaleGrabAtOnceAdapter;
import com.nbhysj.coupon.fragment.FollowFragment;
import com.nbhysj.coupon.fragment.HotelSelectionFragment;
import com.nbhysj.coupon.fragment.InteractiveSelectionFragment;
import com.nbhysj.coupon.fragment.LocalFoodFragment;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.view.StickyScrollView;
import com.nbhysj.coupon.widget.MyViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author hysj created at 2019/4/19.
 * description:商城特卖
 */
public class ShoppingMallSpecialSaleActivity extends BaseActivity {

    //限时特卖马上抢适配列表
    @BindView(R.id.rv_flash_sale_immediate_robbery)
    RecyclerView mRvFlashSaleImmediateRobbery;

    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.viewpager)
    MyViewPager viewpager;
    @BindView(R.id.stick_scrollview)
    StickyScrollView mStickyScrollView;
    private String[] titles = new String[]{"关注", "推荐", "附近"};
    private FlashSaleGrabAtOnceAdapter flashSaleGrabAtOnceAdapter;
    private ArrayList<Fragment> fragments;
    private LocalFoodFragment localFoodFragment;
    private InteractiveSelectionFragment interactiveSelectionFragment;
    private HotelSelectionFragment hotelSelectionFragment;

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_shopping_mall_special_sale;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        fragments = new ArrayList<>();
        List<Fragment> fragments1 = getSupportFragmentManager().getFragments();
        if (fragments1 != null && fragments1.size() > 1) {

            localFoodFragment = (LocalFoodFragment) fragments1.get(0);
            interactiveSelectionFragment = (InteractiveSelectionFragment) fragments1.get(1);
            hotelSelectionFragment = (HotelSelectionFragment) fragments1.get(2);

        } else {

            localFoodFragment = new LocalFoodFragment();
            interactiveSelectionFragment = new InteractiveSelectionFragment();
            hotelSelectionFragment = new HotelSelectionFragment();

        }

        fragments.add(localFoodFragment);
        fragments.add(interactiveSelectionFragment);
        fragments.add(hotelSelectionFragment);

        tabLayout.setViewPager(viewpager, titles, ShoppingMallSpecialSaleActivity.this, fragments);

        LinearLayoutManager hotelReputationLinearLayout = new LinearLayoutManager(ShoppingMallSpecialSaleActivity.this);
        hotelReputationLinearLayout.setOrientation(hotelReputationLinearLayout.HORIZONTAL);
        mRvFlashSaleImmediateRobbery.setLayoutManager(hotelReputationLinearLayout);
        flashSaleGrabAtOnceAdapter = new FlashSaleGrabAtOnceAdapter(ShoppingMallSpecialSaleActivity.this);
        mRvFlashSaleImmediateRobbery.setAdapter(flashSaleGrabAtOnceAdapter);
    }

    @Override
    public void initData() {
        mStickyScrollView.setScrolListener(new StickyScrollView.OnScrollListener() {
            @Override
            public void onScroll(int scrollY) {


            }
        });

        mStickyScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    Log.i("TAG", "BOTTOM SCROLL");


                    showToast(ShoppingMallSpecialSaleActivity.this, "已加载全部数据...");
                }
            }
        });
    }

    @Override
    public void initPresenter() {

    }
}
