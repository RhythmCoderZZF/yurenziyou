package com.nbhysj.coupon.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.TravelAssistantCountryPagerAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.TravelAssistantContract;
import com.nbhysj.coupon.fragment.TravelAssisantAddFineFoodFragment;
import com.nbhysj.coupon.fragment.TravelAssisantAddScenicSpotFragment;
import com.nbhysj.coupon.model.TravelAssistantModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CountryBean;
import com.nbhysj.coupon.model.response.CreateTripResponse;
import com.nbhysj.coupon.model.response.TravelAssistantDetailCountryBean;
import com.nbhysj.coupon.model.response.TripDetailsResponse;
import com.nbhysj.coupon.model.response.TripHomePageResponse;
import com.nbhysj.coupon.model.response.TripScenicSpotAddCountryBean;
import com.nbhysj.coupon.model.response.WeatherResponse;
import com.nbhysj.coupon.presenter.TravelAssistantPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/07/09
 * description：添加美食
 */
public class TravelAssisantFineFoodAddActivity extends BaseActivity<TravelAssistantPresenter, TravelAssistantModel> implements TravelAssistantContract.View, TravelAssisantAddFineFoodFragment.AddFineFoodListener {

    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private List<CountryBean> countyList;
    private int mTripId, mDayIndex;
    private TravelAssistantCountryPagerAdapter travelAssistantCountryPagerAdapter;
    private int ADD_COUNTY_CODE = 0;

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_add_scenic_spot;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        mTripId = getIntent().getIntExtra("tripId", 0);
        mDayIndex = getIntent().getIntExtra("dayIndex", 0);
        getCountyList();
        if (countyList == null) {

            countyList = new ArrayList<>();
        } else {

            countyList.clear();
        }

      /*  for (int i = 0; i < topics.length; i++)
        {
            fragments.add(new TravelAssisantAddScenicSpotFragment());
        }*/
        travelAssistantCountryPagerAdapter = new TravelAssistantCountryPagerAdapter(getSupportFragmentManager());
        travelAssistantCountryPagerAdapter.setTravelAssistantCountryList(fragments, countyList);
        mViewPager.setAdapter(travelAssistantCountryPagerAdapter);
        tabLayout.setViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(3);
        //tabLayout.notifyDataSetChanged();

    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    public void getTravelAssistantHomePageResult(BackResult<TripHomePageResponse> res) {

    }

    @Override
    public void updateTripInformationResult(BackResult res) {

    }

    @Override
    public void getTripRouteMapResult(BackResult res) {

    }

    @Override
    public void getCountyListResult(BackResult<List<CountryBean>> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    countyList = res.getData();

                    if (countyList != null) {
                        if (countyList.size() > 0) {
                            for (int i = 0; i < countyList.size(); i++) {
                                String countyId = countyList.get(i).getCountyId();
                                fragments.add(new TravelAssisantAddFineFoodFragment().newInstance(mTripId, countyId, mDayIndex));
                            }
                            travelAssistantCountryPagerAdapter.setTravelAssistantCountryList(fragments, countyList);
                            travelAssistantCountryPagerAdapter.notifyDataSetChanged();
                            mViewPager.getAdapter().notifyDataSetChanged();
                            tabLayout.notifyDataSetChanged();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }

    @Override
    public void getTravelAssistantMchListResult(BackResult<TripScenicSpotAddCountryBean> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {


                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }

    @Override
    public void getCountyWebListResult(BackResult<List<TravelAssistantDetailCountryBean>> res) {

    }

    @Override
    public void getCreateTripResult(BackResult<CreateTripResponse> res) {

    }

    @Override
    public void getTripDetailsResult(BackResult<TripDetailsResponse> res) {

    }

    @Override
    public void insertPlaceMchResult(BackResult<CreateTripResponse> res) {

    }

    @Override
    public void delTripPlaceResult(BackResult res) {

    }

    @Override
    public void insertNoteResult(BackResult<CreateTripResponse> res) {

    }
    @Override
    public void travelAssistantPlusADay(BackResult res) {

    }

    @Override
    public void delTripResult(BackResult res) {

    }

    @Override
    public void getWeatherResult(BackResult<WeatherResponse> res) {

    }

    @Override
    public void insertTrafficResult(BackResult<CreateTripResponse> res) {

    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(TravelAssisantFineFoodAddActivity.this, Constants.getResultMsg(msg));
    }

    @OnClick({R.id.rlyt_back, R.id.img_add_scenic_spot})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlyt_back:

                TravelAssisantFineFoodAddActivity.this.finish();

                break;
            case R.id.img_add_scenic_spot:

                Intent intent = new Intent();
                intent.setClass(TravelAssisantFineFoodAddActivity.this, TravelAssistanSelectCountyActivity.class);
                intent.putExtra("tripId", mTripId);
                startActivityForResult(intent, ADD_COUNTY_CODE);

                break;
            default:
                break;
        }
    }

    @Override
    public void intelligentProjectResult(BackResult<CreateTripResponse> res) {

    }

    @Override
    public void setAddFineFoodListener() {

        setResult(RESULT_OK);
        TravelAssisantFineFoodAddActivity.this.finish();

    }

    //获取区县列表
    public void getCountyList() {

        if (validateInternet()) {

            mPresenter.getCountyList(mTripId);
        }
    }
}
