package com.nbhysj.coupon.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.FmPagerAdapter;
import com.nbhysj.coupon.adapter.TravelAssistantDetailFmPagerAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.TravelAssistantContract;
import com.nbhysj.coupon.dialog.OprateDialog;
import com.nbhysj.coupon.dialog.TravelAssistantAddDialog;
import com.nbhysj.coupon.fragment.TravelAssisantDetailFragment;
import com.nbhysj.coupon.framework.RxBus;
import com.nbhysj.coupon.model.TravelAssistantModel;
import com.nbhysj.coupon.model.request.CreateTripRequest;
import com.nbhysj.coupon.model.request.DeleteTripPlaceRequest;
import com.nbhysj.coupon.model.request.TravelAssistantAddOneDayRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CountryBean;
import com.nbhysj.coupon.model.response.CreateTripResponse;
import com.nbhysj.coupon.model.response.HomePageResponse;
import com.nbhysj.coupon.model.response.TravelAssistantDetailCountryBean;
import com.nbhysj.coupon.model.response.TripDetailsResponse;
import com.nbhysj.coupon.model.response.TripHomePageResponse;
import com.nbhysj.coupon.model.response.TripScenicSpotAddCountryBean;
import com.nbhysj.coupon.model.response.WeatherResponse;
import com.nbhysj.coupon.presenter.TravelAssistantPresenter;
import com.nbhysj.coupon.systembar.StatusBarCompat;
import com.nbhysj.coupon.systembar.StatusBarUtil;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.RecyclerScrollView;
import com.nbhysj.coupon.widget.MyIndicator;
import com.nbhysj.coupon.widget.MyViewPager;
import com.nbhysj.coupon.widget.TripAssistantIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/03/08
 * description：行程详情
 */
public class TravelAssistantDetailsActivity extends BaseActivity<TravelAssistantPresenter, TravelAssistantModel> implements TravelAssistantContract.View, TravelAssisantDetailFragment.DeleteTravelAssistantPlaceListener, RecyclerScrollView.OnScrollListener {
    @BindView(R.id.indicator)
    TripAssistantIndicator mIndicator;
    @BindView(R.id.pager)
    MyViewPager pager;
    @BindView(R.id.toolbar_space)
    View mToolbarSpace;
    @BindView(R.id.img_my_travel_detail_header)
    ImageView mImgTravelDetailHeader;
    @BindView(R.id.recyclerView_travel_assisant_detail)
    RecyclerScrollView mRvTravelAssisantDetail;

    //返回按钮
    @BindView(R.id.iv_back)
    ImageView mImgBack;
    //地图入口图标
    @BindView(R.id.img_map)
    ImageView mImgMap;
    //头部
    @BindView(R.id.rlyt_scenic_spots_detail_header)
    RelativeLayout mRlytScenicSpotDetailHeader;

    //温度
    @BindView(R.id.tv_temperature)
    TextView mTvTemperature;

    //天气状况
    @BindView(R.id.tv_weather)
    TextView mTvWeather;

    //天气
    @BindView(R.id.img_weather)
    ImageView mImgWeather;

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private TravelAssisantDetailFragment travelAssisantDetailFragment;
    TravelAssistantDetailFmPagerAdapter fmPagerAdapter;
    private String countyPhotoUrl;
    private int mTripId;
    private int mDayIndex = 1;
    private int mCurrentItemPosition = 0;
    List<TripDetailsResponse.DetailsEntity> detailsList;
    private int height;
    private TravelAssistantAddDialog travelAssistantAddDialog;
    private boolean isFirstEntry = true;

    @Override
    public int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusBarCompat.translucentStatusBar(this, false);
        //修改状态栏字体颜色
        StatusBarUtil.setImmersiveStatusBar(this, true);
        return R.layout.activity_itinerary_details;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mTripId = getIntent().getIntExtra("tripId", 0);
        ViewGroup.LayoutParams layoutParams = mToolbarSpace.getLayoutParams();//取控件当前的布局参数
        layoutParams.height = getStatusBarHeight();// 控件的高强制设成状态栏高度
        mToolbarSpace.setLayoutParams(layoutParams); //使设置好的布局参数应用到控件</pre>
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbarSpace.setVisibility(View.VISIBLE);
        } else {
            mToolbarSpace.setVisibility(View.GONE);
        }

        if (detailsList == null) {

            detailsList = new ArrayList<>();
        } else {

            detailsList.clear();
        }

        //获取顶部图片高度后，设置滚动监听
        ViewTreeObserver vto = mImgTravelDetailHeader.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mImgTravelDetailHeader.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                height = mImgTravelDetailHeader.getHeight();
                mImgTravelDetailHeader.getWidth();

                mRvTravelAssisantDetail.setScrolListener(TravelAssistantDetailsActivity.this);
            }
        });

        fmPagerAdapter = new TravelAssistantDetailFmPagerAdapter(getSupportFragmentManager());
        fmPagerAdapter.setTravelAssistantDetailList(fragments, detailsList);
        pager.setAdapter(fmPagerAdapter);
        mIndicator.setViewPager(pager, 0);
    }

    @Override
    public void initData() {
        getTravelAssistantDetails();
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    public void getTravelAssistantHomePageResult(BackResult<TripHomePageResponse> res) {

    }

    @Override
    public void getCountyListResult(BackResult<List<CountryBean>> res) {

    }

    @Override
    public void getCountyWebListResult(BackResult<List<TravelAssistantDetailCountryBean>> res) {

    }

    @Override
    public void getCreateTripResult(BackResult<CreateTripResponse> res) {

    }

    @Override
    public void getTravelAssistantMchListResult(BackResult<TripScenicSpotAddCountryBean> res) {

    }

    @Override
    public void delTripPlaceResult(BackResult res) {


    }

    @Override
    public void getTripDetailsResult(BackResult<TripDetailsResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                // try {


                fragments.clear();
                TripDetailsResponse tripDetailsResponse = res.getData();
                countyPhotoUrl = tripDetailsResponse.getCountyPhoto();
                String countyId = tripDetailsResponse.getCountyId();
                if(isFirstEntry){

                    getWeather(Constants.CITY_CODE);
                    isFirstEntry = false;
                }

                GlideUtil.loadImage(mContext, countyPhotoUrl, mImgTravelDetailHeader);

                detailsList = tripDetailsResponse.getDetails();

                if (detailsList.size() > 0) {

                    for (int i = 0; i < detailsList.size(); i++) {
                        TripDetailsResponse.DetailsEntity tripDetailsEntity = detailsList.get(i);
                        travelAssisantDetailFragment = new TravelAssisantDetailFragment().newInstance(mTripId, mDayIndex);
                        travelAssisantDetailFragment.setTravelAssisantDetailList(tripDetailsEntity);
                        fragments.add(travelAssisantDetailFragment);
                    }

                }
                if (fmPagerAdapter != null) {
                    fmPagerAdapter.setTravelAssistantDetailList(fragments, detailsList);
                    fmPagerAdapter.notifyDataSetChanged();
                }

                if (pager != null) {
                    pager.getAdapter().notifyDataSetChanged();
                }

                mIndicator.notifyDataSetChanged();

               // mDayIndex = detailsList.get(0).getDayIndex();

                mIndicator.setMyOnPageChangeListener(new TripAssistantIndicator.MyOnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {

                        mCurrentItemPosition = position;
                        TripDetailsResponse.DetailsEntity tripDetailsEntity = detailsList.get(position);
                        mDayIndex = tripDetailsEntity.getDayIndex();

                       // travelAssisantDetailFragment.setTravelAssisantList(tripDetailsEntity);
                        // showToast(TravelAssistantDetailsActivity.this,dayIndex+"");
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
                //  isTripInit = false;
             /*         String mBroadcastAction = Constants.BROCAST_ACTION_TRIP_ASSISANT;
                Intent intent = new Intent();
                //指定发送广播的频道
                intent.setAction(mBroadcastAction);
                //发送广播的数据
                sendBroadcast(intent);*/
                // 发送带 tag 为 "my tag" 的 String 类型事件
                // RxBus.$().post("with tag", "my tag");

               /* } catch (Exception e) {
                    e.printStackTrace();
                }*/
                break;
            default:
                showToast(TravelAssistantDetailsActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void insertPlaceMchResult(BackResult<CreateTripResponse> res) {

    }

    @Override
    public void insertNoteResult(BackResult<CreateTripResponse> res) {

    }

    @Override
    public void travelAssistantPlusADay(BackResult res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    getTravelAssistantDetails();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(TravelAssistantDetailsActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void delTripResult(BackResult res) {

    }
    @Override
    public void updateTripInformationResult(BackResult res) {

    }

    @Override
    public void getTripRouteMapResult(BackResult res) {

    }

    @Override
    public void getWeatherResult(BackResult<WeatherResponse> res)
    {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    WeatherResponse weatherResponse = res.getData();
                    String weather = weatherResponse.getWeather();
                    String temperature = weatherResponse.getTemperature();
                    String photoUrl = weatherResponse.getPhoto();
                    mTvTemperature.setText(temperature + "°");
                    mTvWeather.setText(weather);
                    GlideUtil.loadImage(TravelAssistantDetailsActivity.this,photoUrl,mImgWeather);

                    System.out.print(weatherResponse);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(TravelAssistantDetailsActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(TravelAssistantDetailsActivity.this, Constants.getResultMsg(msg));
    }

    @OnClick({R.id.img_add_my_travel_content, R.id.iv_back, R.id.llyt_travel_assistant_preview, R.id.img_map})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.img_add_my_travel_content:
                // labelList3.remove(0);
                //fragments.remove(0);
/*
                for (int i = 0;i < detailsList.size();i++) {

                    detailsList.get(0).setCircuit("1111");
                }

                fmPagerAdapter.notifyDataSetChanged();

                pager.getAdapter().notifyDataSetChanged();

                mIndicator.setViewPager(pager,0);*/
                if (travelAssistantAddDialog == null) {
                    travelAssistantAddDialog = new TravelAssistantAddDialog();
                    travelAssistantAddDialog.setTravelAssistantAddListener(new TravelAssistantAddDialog.TravelAssistantAddListener() {
                        @Override
                        public void travelAssistantAddListener(int position) {
                            Intent intent = new Intent();
                            intent.putExtra("tripId", mTripId);
                            intent.putExtra("dayIndex", mDayIndex);
                            if (position == 0) {

                                intent.setClass(TravelAssistantDetailsActivity.this, TravelAssisantScenicSpotAddActivity.class);
                                startActivityForResult(intent, 0);

                            } if (position == 1) {

                                intent.setClass(TravelAssistantDetailsActivity.this, TravelAssisantFineFoodAddActivity.class);
                                startActivityForResult(intent, 0);

                            }if (position == 2) {

                                intent.setClass(TravelAssistantDetailsActivity.this, TravelAssisantHotelHomestayAddActivity.class);
                                startActivityForResult(intent, 0);

                            } if (position == 3) {

                                intent.setClass(TravelAssistantDetailsActivity.this, TravelAssisantInteractionAddActivity.class);
                                startActivityForResult(intent, 0);

                            } else if (position == 4) {

                                intent.setClass(TravelAssistantDetailsActivity.this, TravelAssistantAddTrafficActivity.class);

                                startActivityForResult(intent, 0);
                            } else if (position == 5) {

                               travelAssistantPlusADay();

                            } else if (position == 6) {

                                intent.setClass(TravelAssistantDetailsActivity.this, TravelAssistantRemarksActivity.class);
                                intent.putExtra("isAddRemarks", true); //编辑 false : 添加 true
                                startActivityForResult(intent, 0);
                            }
                        }
                    });
                }
                travelAssistantAddDialog.show(getFragmentManager(), "行程助手添加");
                break;
            case R.id.iv_back:

                TravelAssistantDetailsActivity.this.finish();

                break;
            case R.id.llyt_travel_assistant_preview:


                intent.setClass(TravelAssistantDetailsActivity.this, TripPreviewActivity.class);
                intent.putExtra("tripId",mTripId);
                startActivity(intent);

                break;
            case R.id.img_map:

                intent.setClass(TravelAssistantDetailsActivity.this, TravelAssistantRoutePlanMapActivity.class);
                intent.putExtra("tripId",mTripId);
                startActivity(intent);
                break;
            default:
                break;

        }
    }

    //获取行程助手详情
    public void getTravelAssistantDetails() {

        if (validateInternet()) {

            showProgressDialog(TravelAssistantDetailsActivity.this);
            mPresenter.getTripDetails(mTripId);

        }
    }

    //获取天气接口
    public void getWeather(int cityCode) {

        if (validateInternet()) {

            showProgressDialog(TravelAssistantDetailsActivity.this);
            mPresenter.getWeather(cityCode);

        }
    }

    //行程助手增加一天
    public void travelAssistantPlusADay() {

        if (validateInternet()) {

            showProgressDialog(TravelAssistantDetailsActivity.this);
            mDialog.setTitle("正在新增......");
            TravelAssistantAddOneDayRequest addOneDayRequest = new TravelAssistantAddOneDayRequest();
            addOneDayRequest.setTripId(mTripId);
            mPresenter.travelAssistantPlusADay(addOneDayRequest);

        }
    }

    //删除行程助手行程点
    public void delTripPlace(int tripPlaceId) {

        if (validateInternet()) {

            DeleteTripPlaceRequest deleteTripPlaceRequest = new DeleteTripPlaceRequest();
            deleteTripPlaceRequest.setId(tripPlaceId);
            mPresenter.delTripPlace(deleteTripPlaceRequest);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) {
            getTravelAssistantDetails();
           /* if(fmPagerAdapter != null) {
                fmPagerAdapter.notifyDataSetChanged();
            }

            if(pager != null) {
                pager.getAdapter().notifyDataSetChanged();
            }

            mIndicator.setViewPager(pager,0);*/

            // showToast(TravelAssistantDetailsActivity.this,"2222");
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        for (int indext = 0; indext < fragmentManager.getFragments().size(); indext++) {
            if (indext == mCurrentItemPosition) {
                Fragment fragment = fragmentManager.getFragments().get(indext); //找到第一层Fragment
                if (fragment == null)
                    Log.w("", "Activity result no fragment exists for index: 0x"
                            + Integer.toHexString(requestCode));
                else
                    handleResult(fragment, requestCode, resultCode, data);
            }
        }
    }

    @Override
    public void setDeleteTravelAssistantPlaceListener(int position, int tripPlaceId) {

    }

    /**
     * 递归调用，对所有的子Fragment生效
     *
     * @param fragment
     * @param requestCode
     * @param resultCode
     * @param data
     */
    private void handleResult(Fragment fragment, int requestCode, int resultCode, Intent data) {
        fragment.onActivityResult(requestCode, resultCode, data);//调用每个Fragment的onActivityResult
        Log.e("", "MyBaseFragmentActivity");
        List<Fragment> childFragment = fragment.getChildFragmentManager().getFragments(); //找到第二层Fragment
        if (childFragment != null)
            for (Fragment f : childFragment)
                if (f != null) {
                    handleResult(f, requestCode, resultCode, data);
                }
        if (childFragment == null)
            Log.e("", "MyBaseFragmentActivity1111");

    }

    @Override
    public void onScroll(int y) {
        if (y <= height && y >= 0) {
            float scale = (float) y / height;
            float alpha = (255 * scale);
            mRlytScenicSpotDetailHeader.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
            mToolbarSpace.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
            mImgBack.setImageDrawable(getResources().getDrawable(R.mipmap.icon_left_arrow_black));
            mImgMap.setImageResource(R.mipmap.icon_black_map_label);
            if (y <= 100) {
                mRlytScenicSpotDetailHeader.setBackgroundColor(getResources().getColor(R.color.transparent));
                mImgBack.setImageDrawable(getResources().getDrawable(R.mipmap.icon_left_arrow_white));
                mImgMap.setImageDrawable(getResources().getDrawable(R.mipmap.icon_white_map_label));
                mToolbarSpace.getBackground().setAlpha(255);
            }
        }
    }
}
