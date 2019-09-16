package com.nbhysj.coupon.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.DestinationScenicSpotsAdapter;
import com.nbhysj.coupon.adapter.ScenicSpotClassificationAdapter;
import com.nbhysj.coupon.adapter.ScenicSpotsListAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.common.Enum.MchTypeEnum;
import com.nbhysj.coupon.contract.ScenicSpotContract;
import com.nbhysj.coupon.model.ScenicSpotModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.MchAlbumResponse;
import com.nbhysj.coupon.model.response.MchBangDanRankingResponse;
import com.nbhysj.coupon.model.response.MchDetailsResponse;
import com.nbhysj.coupon.model.response.NetFriendAlbumResponse;
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.model.response.ScenicSpotResponse;
import com.nbhysj.coupon.model.response.TourGuideBean;
import com.nbhysj.coupon.presenter.ScenicSpotPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.view.StickyScrollView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/0/02
 * description：风景名胜
 */
public class ShoppingMallScenicSpotActivity extends BaseActivity<ScenicSpotPresenter, ScenicSpotModel> implements ScenicSpotContract.View {
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    //热门景点
    @BindView(R.id.rv_popular_scenic_spots)
    RecyclerView mRvPopularScenicSpots;
    //景点分类
    @BindView(R.id.rv_scenic_spot_classification)
    RecyclerView mRvScenicSpotClassification;
    //附近热门
    @BindView(R.id.tv_popular_nearby)
    TextView mTvPopularNearby;
    //综合排序
    @BindView(R.id.tv_comprehensive_sorting)
    TextView mTvComprehensiveSorting;
    //超值预定
    @BindView(R.id.tv_super_value_reservation)
    TextView mTvSuperValueReservation;
    //距离优先
    @BindView(R.id.tv_distance_first)
    TextView mTvDistanceFirst;
    @BindView(R.id.rlyt_back)
    RelativeLayout mRlytBack;
    @BindView(R.id.sticky_scrollview)
    StickyScrollView mStickyScrollView;
    @BindView(R.id.rv_hot_scenic_spot)
    RecyclerView mRvHotScenicSpot;
    //景点栏目
    private DestinationScenicSpotsAdapter destinationScenicSpotsAdapter;
    //景点分类
    private ScenicSpotClassificationAdapter scenicSpotClassificationAdapter;
    //景点适配器
    private ScenicSpotsListAdapter scenicSpotsListAdapter;
    //景点中间分类
    List<ScenicSpotHomePageResponse.CateEntity> mCateEntityList;
    //景点栏目
    List<MchTypeBean> mScenicSpotHotList;
    //景点列表
    List<MchTypeBean> mScenicSpotList;
    //加载
    @BindView(R.id.llyt_progress_bar_loading)
    LinearLayout mLlytProgressBarLoading;
    @BindView(R.id.progressbar_load_more)
    ProgressBar mProgressBarLoadMore;
    @BindView(R.id.tv_load_more)
    TextView mTvLoadMore;

    private int mPage = 1;
    private int mPageSize = 10;
    int mTotalPageCount;
    //排序规则类型 FJ=附近热门 ZH=综合排序 默认FJ
    private String mSortStr = "FJ";

    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private String mLatitude = "";
    private String mLongitude = "";
    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_shopping_mall_scenic_spot;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        if (mCateEntityList == null) {

            mCateEntityList = new ArrayList<>();
        } else {
            mCateEntityList.clear();
        }
        if (mScenicSpotHotList == null) {

            mScenicSpotHotList = new ArrayList<>();
        } else {
            mScenicSpotHotList.clear();
        }
        if (mScenicSpotList == null) {

            mScenicSpotList = new ArrayList<>();
        } else {
            mScenicSpotList.clear();
        }


        initLocation();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShoppingMallScenicSpotActivity.this);
        linearLayoutManager.setOrientation(linearLayoutManager.HORIZONTAL);
        mRvPopularScenicSpots.setLayoutManager(linearLayoutManager);
        destinationScenicSpotsAdapter = new DestinationScenicSpotsAdapter(ShoppingMallScenicSpotActivity.this);
        destinationScenicSpotsAdapter.setPopularScenicSpotsList(mScenicSpotHotList);
        mRvPopularScenicSpots.setAdapter(destinationScenicSpotsAdapter);

        //景点分类
        LinearLayoutManager scenicSpotClassificationLinearLayout = new LinearLayoutManager(ShoppingMallScenicSpotActivity.this);
        scenicSpotClassificationLinearLayout.setOrientation(linearLayoutManager.HORIZONTAL);
        mRvScenicSpotClassification.setLayoutManager(scenicSpotClassificationLinearLayout);
        scenicSpotClassificationAdapter = new ScenicSpotClassificationAdapter(ShoppingMallScenicSpotActivity.this, MchTypeEnum.MCH_SCENIC.getValue());
        scenicSpotClassificationAdapter.setScenicSpotClassificationList(mCateEntityList);
        mRvScenicSpotClassification.setAdapter(scenicSpotClassificationAdapter);

        LinearLayoutManager scenicSpotsLinearLayoutManager = new LinearLayoutManager(ShoppingMallScenicSpotActivity.this);
        scenicSpotsLinearLayoutManager.setOrientation(scenicSpotsLinearLayoutManager.VERTICAL);
        mRvHotScenicSpot.setLayoutManager(scenicSpotsLinearLayoutManager);
        scenicSpotsListAdapter = new ScenicSpotsListAdapter(ShoppingMallScenicSpotActivity.this);
        scenicSpotsListAdapter.setPopularScenicSpotsList(mScenicSpotList);
        mRvHotScenicSpot.setAdapter(scenicSpotsListAdapter);

    }

    @Override
    public void initData() {
        mRlytBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ShoppingMallScenicSpotActivity.this.finish();
            }
        });

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        mPage = 1;
                        mScenicSpotList.clear();
                        //scenicSpotsListAdapter.notifyDataSetChanged();
                        findScenicByCate();

                    }
                }, 100);
            }
        });

        mStickyScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {


                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    loadData();
                }
            }
        });

        mStickyScrollView.setScrolListener(new StickyScrollView.OnScrollListener() {
            @Override
            public void onScroll(int scrollY) {

                if (scrollY == (mStickyScrollView.getChildAt(0).getMeasuredHeight() - mStickyScrollView.getMeasuredHeight())) {
                    loadData();
                }
            }
        });
    }

    public void loadData() {
        mLlytProgressBarLoading.setVisibility(View.VISIBLE);
        if (mScenicSpotList.size() != mTotalPageCount) {
            mProgressBarLoadMore.setVisibility(View.VISIBLE);
            mTvLoadMore.setText(getResources().getString(R.string.loading));
            showProgressDialog(ShoppingMallScenicSpotActivity.this);
            mPage++;
            findScenicByCate();
        } else {
            mProgressBarLoadMore.setVisibility(View.GONE);
            mTvLoadMore.setText(getResources().getString(R.string.str_loading_no_more));
        }
    }

    @Override
    public void getScenicSpotHomePageResult(BackResult<ScenicSpotHomePageResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                /*    if (isOnLoadMore) {

                        // mSmartRefreshLayout.finishLoadMore();
                    } else {

                        recommendFriendsList.clear();
                        recommendFriendsAdapter.notifyDataSetChanged();
                    }*/

                    //景点栏
                    mScenicSpotHotList = res.getData().getHot();
                    destinationScenicSpotsAdapter.setPopularScenicSpotsList(mScenicSpotHotList);
                    destinationScenicSpotsAdapter.notifyDataSetChanged();

                    //景点分类
                    mCateEntityList = res.getData().getCate();
                    scenicSpotClassificationAdapter.setScenicSpotClassificationList(mCateEntityList);
                    scenicSpotClassificationAdapter.notifyDataSetChanged();

                    List<MchTypeBean> scenicSpotList = res.getData().getMch().getOverflow().getResult();
                    mScenicSpotList.addAll(scenicSpotList);
                    scenicSpotsListAdapter.setPopularScenicSpotsList(mScenicSpotList);
                    scenicSpotsListAdapter.notifyDataSetChanged();
                    BasePaginationResult pageBean = res.getData().getMch().getOverflow().getPage();
                    mTotalPageCount = pageBean.getPageCount();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(ShoppingMallScenicSpotActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void findScenicByCateResult(BackResult<ScenicSpotResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    if (mPage == 1) {

                        if (mSmartRefreshLayout != null) {
                            mSmartRefreshLayout.finishRefresh();
                            mProgressBarLoadMore.setVisibility(View.GONE);
                            mTvLoadMore.setText(getResources().getString(R.string.str_pull_up_loading));
                        }
                    }
                    List<MchTypeBean> scenicSpotList = res.getData().getResult();
                    BasePaginationResult pageBean = res.getData().getPage();
                    mTotalPageCount = pageBean.getPageCount();
                    mScenicSpotList.addAll(scenicSpotList);
                    scenicSpotsListAdapter.setPopularScenicSpotsList(mScenicSpotList);
                    scenicSpotsListAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(ShoppingMallScenicSpotActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getMchDetailsResult(BackResult<MchDetailsResponse> res) {

    }

    @Override
    public void getScenicBangDanRankingResult(BackResult<MchBangDanRankingResponse> res) {

    }

    @Override
    public void getTourGuideListResult(BackResult<List<TourGuideBean>> res) {

    }

    @Override
    public void getMchAlbumListResult(BackResult<MchAlbumResponse> res) {

    }

    @Override
    public void getNetFriendAlbumListResult(BackResult<NetFriendAlbumResponse> res) {

    }

    @Override
    public void showMsg(String msg) {
        dismissProgressDialog();
        showToast(ShoppingMallScenicSpotActivity.this, Constants.getResultMsg(msg));

    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }


    @OnClick({R.id.tv_popular_nearby, R.id.tv_comprehensive_sorting, R.id.tv_super_value_reservation, R.id.tv_distance_first})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.tv_popular_nearby:
                mTvPopularNearby.setTextColor(getResources().getColor(R.color.txt_font_black2));
                mTvPopularNearby.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
                mTvComprehensiveSorting.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvSuperValueReservation.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvDistanceFirst.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvComprehensiveSorting.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mTvSuperValueReservation.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mTvDistanceFirst.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mPage = 1;
                mScenicSpotList.clear();
                //  scenicSpotsListAdapter.notifyDataSetChanged();
                mSortStr = "FJ";
                showProgressDialog(ShoppingMallScenicSpotActivity.this);
                findScenicByCate();
                break;
            case R.id.tv_comprehensive_sorting:
                mTvComprehensiveSorting.setTextColor(getResources().getColor(R.color.txt_font_black2));
                mTvComprehensiveSorting.setTypeface(Typeface.DEFAULT, Typeface.BOLD);

                mTvPopularNearby.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvSuperValueReservation.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvDistanceFirst.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvPopularNearby.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mTvSuperValueReservation.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mTvDistanceFirst.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mPage = 1;
                mScenicSpotList.clear();
                //   scenicSpotsListAdapter.notifyDataSetChanged();
                mSortStr = "ZH";
                showProgressDialog(ShoppingMallScenicSpotActivity.this);
                findScenicByCate();

                break;
            case R.id.tv_super_value_reservation:
                mTvSuperValueReservation.setTextColor(getResources().getColor(R.color.txt_font_black2));
                mTvComprehensiveSorting.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvPopularNearby.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvDistanceFirst.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvSuperValueReservation.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
                mTvComprehensiveSorting.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mTvPopularNearby.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mTvDistanceFirst.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mPage = 1;
                mScenicSpotList.clear();
                // scenicSpotsListAdapter.notifyDataSetChanged();
                mSortStr = "CZ";
                showProgressDialog(ShoppingMallScenicSpotActivity.this);
                findScenicByCate();
                break;
            case R.id.tv_distance_first:
                mTvDistanceFirst.setTextColor(getResources().getColor(R.color.txt_font_black2));
                mTvSuperValueReservation.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvComprehensiveSorting.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvPopularNearby.setTextColor(getResources().getColor(R.color.color_text_gray20));

                mTvDistanceFirst.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
                mTvSuperValueReservation.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mTvComprehensiveSorting.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mTvPopularNearby.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mPage = 1;
                mScenicSpotList.clear();
                // scenicSpotsListAdapter.notifyDataSetChanged();
                mSortStr = "CZ";
                showProgressDialog(ShoppingMallScenicSpotActivity.this);
                findScenicByCate();
                break;
            default:
                break;
        }
    }

    /**
     * RecyclerView 移动到当前位置，
     *
     * @param manager       设置RecyclerView对应的manager
     * @param mRecyclerView 当前的RecyclerView
     * @param n             要跳转的位置
     */
    public static void MoveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView, int n) {
        int firstItem = manager.findFirstVisibleItemPosition();
        int lastItem = manager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            mRecyclerView.scrollToPosition(n);
        } else if (n <= lastItem) {
            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
            mRecyclerView.scrollBy(0, top);
        } else {
            mRecyclerView.scrollToPosition(n);
        }
    }

    /* RecyclerView 移动到当前位置，
     *
     * @param manager  设置RecyclerView对应的manager
     * @param n  要跳转的位置
     */
    public static void MoveToPosition(LinearLayoutManager manager, int n) {
        manager.scrollToPositionWithOffset(n, 0);
        manager.setStackFromEnd(true);
    }

    public void getScenicSpotHomePage() {

        if (validateInternet()) {

            mPresenter.getScenicSpotHomePage(mLongitude, mLatitude);
        }
    }

    public void findScenicByCate() {
        if (validateInternet()) {

            HashMap<String, String> scenicSpotByCateRequest = new HashMap<>();

            scenicSpotByCateRequest.put("Sorting", mSortStr);
            scenicSpotByCateRequest.put("page", String.valueOf(mPage));
            scenicSpotByCateRequest.put("pageSize", String.valueOf(mPageSize));
            scenicSpotByCateRequest.put("longitude", mLongitude);
            scenicSpotByCateRequest.put("latitude",mLatitude);
            mPresenter.findScenicByCate(scenicSpotByCateRequest);
        }
    }



    /**
     * 初始化定位
     */
    private void initLocation(){
        //初始化client
        locationClient = new AMapLocationClient(ShoppingMallScenicSpotActivity.this);
        locationOption = getDefaultOption();
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(locationListener);

        startLocation();
    }


    /**
     * 默认的定位参数
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(true);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        mOption.setGeoLanguage(AMapLocationClientOption.GeoLanguage.DEFAULT);//可选，设置逆地理信息的语言，默认值为默认语言（根据所在地区选择语言）
        return mOption;
    }

    /**
     * 开始定位
     */
    private void startLocation() {
        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }


    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            if (null != location) {
                mLongitude = String.valueOf(location.getLongitude());
                mLatitude = String.valueOf(location.getLatitude());

                showProgressDialog(ShoppingMallScenicSpotActivity.this);
                getScenicSpotHomePage();
             /*   StringBuffer sb = new StringBuffer();
                //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
                if(location.getErrorCode() == 0){
                    sb.append("定位成功" + "\n");
                    sb.append("定位类型: " + location.getLocationType() + "\n");
                    sb.append("经    度    : " + location.getLongitude() + "\n");
                    sb.append("纬    度    : " + location.getLatitude() + "\n");
                    sb.append("精    度    : " + location.getAccuracy() + "米" + "\n");
                    sb.append("提供者    : " + location.getProvider() + "\n");

                    sb.append("速    度    : " + location.getSpeed() + "米/秒" + "\n");
                    sb.append("角    度    : " + location.getBearing() + "\n");
                    // 获取当前提供定位服务的卫星个数
                    sb.append("星    数    : " + location.getSatellites() + "\n");
                    sb.append("国    家    : " + location.getCountry() + "\n");
                    sb.append("省            : " + location.getProvince() + "\n");
                    sb.append("市            : " + location.getCity() + "\n");
                    sb.append("城市编码 : " + location.getCityCode() + "\n");
                    sb.append("区            : " + location.getDistrict() + "\n");
                    sb.append("区域 码   : " + location.getAdCode() + "\n");
                    sb.append("地    址    : " + location.getAddress() + "\n");
                    sb.append("兴趣点    : " + location.getPoiName() + "\n");
                    //定位完成的时间
                    sb.append("定位时间: " + MapUtils.formatUTC(location.getTime(), "yyyy-MM-dd HH:mm:ss") + "\n");
                } else {
                    //定位失败
                    sb.append("定位失败" + "\n");
                    sb.append("错误码:" + location.getErrorCode() + "\n");
                    sb.append("错误信息:" + location.getErrorInfo() + "\n");
                    sb.append("错误描述:" + location.getLocationDetail() + "\n");
                }
                sb.append("***定位质量报告***").append("\n");
                sb.append("* WIFI开关：").append(location.getLocationQualityReport().isWifiAble() ? "开启":"关闭").append("\n");
                sb.append("* GPS状态：").append(getGPSStatusString(location.getLocationQualityReport().getGPSStatus())).append("\n");
                sb.append("* GPS星数：").append(location.getLocationQualityReport().getGPSSatellites()).append("\n");
                sb.append("* 网络类型：" + location.getLocationQualityReport().getNetworkType()).append("\n");
                sb.append("* 网络耗时：" + location.getLocationQualityReport().getNetUseTime()).append("\n");
                sb.append("****************").append("\n");
                //定位之后的回调时间
                sb.append("回调时间: " + MapUtils.formatUTC(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss") + "\n");

                //解析定位结果，
                String result = sb.toString();*/
            } else {
            }
        }
    };

}
