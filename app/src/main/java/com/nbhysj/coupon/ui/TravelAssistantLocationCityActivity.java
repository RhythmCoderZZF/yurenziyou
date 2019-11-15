package com.nbhysj.coupon.ui;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.nbhysj.coupon.BasicApplication;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.TravelAssistantLocationCityAdapter;
import com.nbhysj.coupon.adapter.TravelAssistantTrafficCitySelectAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.TravelAssistantContract;
import com.nbhysj.coupon.greendao.DaoSession;
import com.nbhysj.coupon.model.TravelAssistantModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CountryBean;
import com.nbhysj.coupon.model.response.CreateTripResponse;
import com.nbhysj.coupon.model.response.TravelAssistantDetailCountryBean;
import com.nbhysj.coupon.model.response.TripDetailsResponse;
import com.nbhysj.coupon.model.response.TripHomePageResponse;
import com.nbhysj.coupon.model.response.TripRouteMapResponse;
import com.nbhysj.coupon.model.response.TripScenicSpotAddCountryBean;
import com.nbhysj.coupon.model.response.WeatherResponse;
import com.nbhysj.coupon.presenter.TravelAssistantPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.ToolbarHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/07/07
 * description：行程助手定位城市选择
 */
public class TravelAssistantLocationCityActivity extends BaseActivity<TravelAssistantPresenter, TravelAssistantModel> implements TravelAssistantContract.View {

    //定位&历史列表
    @BindView(R.id.rv_location_city_and_history_district)
    RecyclerView mRvLocationCityAndHistoryDistrict;

    //宁波市区县列表
    @BindView(R.id.rv_district_city)
    RecyclerView mRvDistrictCity;

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    public AMapLocationClientOption mLocationOption = null;
    //纬度
    private double latitude;

    //经度
    private double longitude;

    //定位城市&历史城市
    private List<CountryBean> countryAllList;

    //城市选择
    private List<CountryBean> countrySelectList;

    //定位或者历史城市
    private TravelAssistantLocationCityAdapter travelAssistantLocationCityAdapter;

    //城市选择
    private TravelAssistantTrafficCitySelectAdapter trafficCitySelectAdapter;

    private int mTripId;

    private boolean isContainSameCountry = false;
    @Override
    public int getLayoutId()
    {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_travel_assistant_location_city_traffic_add;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setBar(TravelAssistantLocationCityActivity.this,getResources().getString(R.string.str_add_traffic),R.mipmap.icon_left_arrow_black,"");

        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        mLocationOption = new AMapLocationClientOption();
        //设置定位回调监听
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        mLocationOption.setNeedAddress(true);
        mLocationOption.setOnceLocation(true);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔
        mLocationOption.setInterval(0);
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.setLocationListener(mLocationListener);
        mLocationClient.startLocation();
    }

    @Override
    public void initData() {

        mTripId = getIntent().getIntExtra("tripId",0);
        if(countryAllList == null)
        {
            countryAllList = new ArrayList<>();

        } else {
            countryAllList.clear();
        }

        if(countrySelectList == null)
        {
            countrySelectList = new ArrayList<>();

        } else {
            countrySelectList.clear();
        }

        getCountyList();

    /*    //定位城市&历史选择城市
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(TravelAssistantLocationCityActivity.this,3);
        gridLayoutManager1.setOrientation(gridLayoutManager1.VERTICAL);
        mRvLocationCityAndHistoryDistrict.setLayoutManager(gridLayoutManager1);
        travelAssistantLocationCityAdapter = new TravelAssistantLocationCityAdapter(new TravelAssistantLocationCityAdapter.TravelAssistantLocationCityListener() {
            @Override
            public void setTravelAssistantLocationCityListener(CountryBean countryBean) {


            }
        });
        travelAssistantLocationCityAdapter.setTravelAssistantLocationCityList(countryAllList);
        mRvLocationCityAndHistoryDistrict.setAdapter(travelAssistantLocationCityAdapter);*/


        //城市选择
        GridLayoutManager gridLayoutManager = new GridLayoutManager(TravelAssistantLocationCityActivity.this,4);
        gridLayoutManager.setOrientation(gridLayoutManager.VERTICAL);
        mRvDistrictCity.setLayoutManager(gridLayoutManager);
        trafficCitySelectAdapter = new TravelAssistantTrafficCitySelectAdapter(new TravelAssistantTrafficCitySelectAdapter.TravelAssistantLocationCityListener() {
            @Override
            public void setTravelAssistantLocationCityListener(CountryBean countryBean) {

               // DaoSession daoSession = ((BasicApplication) getApplication()).getDaoSession();
               // daoSession.insert(countryBean);
              //  insertCountryData(countryBean);

                TravelAssistantLocationCityActivity.this.finish();
            }
        });
        trafficCitySelectAdapter.setTravelAssistantLocationCityList(countrySelectList);
        mRvDistrictCity.setAdapter(trafficCitySelectAdapter);
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this,mModel);
    }

    @Override
    public void getTravelAssistantHomePageResult(BackResult<TripHomePageResponse> res) {

    }

    @Override
    public void getCountyListResult(BackResult<List<CountryBean>> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                   List<CountryBean> countyList = res.getData();
                    trafficCitySelectAdapter.setTravelAssistantLocationCityList(countyList);
                    trafficCitySelectAdapter.notifyDataSetChanged();
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
    public void getTravelAssistantMchListResult(BackResult<TripScenicSpotAddCountryBean> res) {

    }

    @Override
    public void insertPlaceMchResult(BackResult<CreateTripResponse> res) {

    }

    @Override
    public void travelAssistantPlusADay(BackResult res) {

    }

    @Override
    public void insertNoteResult(BackResult<CreateTripResponse> res) {

    }

    @Override
    public void delTripPlaceResult(BackResult res) {

    }

    @Override
    public void delTripResult(BackResult res) {

    }

    @Override
    public void updateTripInformationResult(BackResult res) {

    }

    @Override
    public void getTripRouteMapResult(BackResult<TripRouteMapResponse> res) {

    }

    @Override
    public void getWeatherResult(BackResult<WeatherResponse> res) {

    }

    @Override
    public void intelligentProjectResult(BackResult<CreateTripResponse> res) {

    }

    @Override
    public void insertTrafficResult(BackResult<CreateTripResponse> res) {

    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(TravelAssistantLocationCityActivity.this, Constants.getResultMsg(msg));
    }

    /**
     * 声明定位回调监听器
     */
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    //定位成功回调信息，设置相关消息
                    amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    amapLocation.getLatitude();//获取纬度
                    amapLocation.getLongitude();//获取经度
                    amapLocation.getAccuracy();//获取精度信息
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date(amapLocation.getTime());
                    df.format(date);//定位时间
                    amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                    amapLocation.getCountry();//国家信息
                    amapLocation.getProvince();//省信息
                    amapLocation.getCity();//城市信息
                    amapLocation.getDistrict();//城区信息
                    amapLocation.getStreet();//街道信息
                    amapLocation.getStreetNum();//街道门牌号信息
                    amapLocation.getCityCode();//城市编码
                    amapLocation.getAdCode();//地区编码
                    amapLocation.getAoiName();//获取当前定位点的AOI信息
                    latitude = amapLocation.getLatitude();
                    longitude = amapLocation.getLongitude();
                    String city = amapLocation.getCity();
                    String adCode = amapLocation.getAdCode();
                   // getTravelAssistantLocationCity(adCode,city);

                    Log.v("pcw", "lat : " + latitude + " lon : " + longitude);
                    Log.v("pcw", "Country : " + amapLocation.getCountry() + " province : " + amapLocation.getProvince() + " City : " + amapLocation.getCity() + " District : " + amapLocation.getDistrict());

                } else {
                    //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }
            }
        }
    };

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mLocationClient != null)
        {
            mLocationClient.stopLocation();//停止定位
        }
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mLocationClient != null)
        {
            mLocationClient.onDestroy();//销毁定位客户端
        }
    }

    @OnClick({})
    public void onClick(View v) {
        switch (v.getId()) {

            default:
                break;
        }
    }

    //获取区县列表
    public void getCountyList()
    {
        if (validateInternet()) {

            mPresenter.getCountyList(mTripId);
        }
    }

    //获取行程助手定位城市
    public void getTravelAssistantLocationCity(String countyId,String city)
    {
        String locationCity = city.replace("市","");
        CountryBean countryBean = new CountryBean();
        countryBean.setCountyId(countyId);
        countryBean.setCounty(locationCity);
        countryBean.setTripId(mTripId);
        countryBean.setLocationCity(true);

        insertCountryData(countryBean);
    }

    private void insertCountryData(CountryBean countryBean) {
        try {
            List<CountryBean> countryList = queryAllCountry();
            countryAllList.clear();
            DaoSession daoSession = ((BasicApplication) getApplication()).getDaoSession();

            if (countryList.size() > 0) {
                for (int i = 0; i < countryList.size(); i++) {
                    String id = countryList.get(i).getCountyId();
                    String selectCountyId = countryBean.getCountyId();
                    if (id.equals(selectCountyId)) {

                        isContainSameCountry = true;
                    }
                }
                if (!isContainSameCountry) {

                    if (countryAllList.size() == 10) {

                        countryAllList.remove(countryAllList.get(countryAllList.size() - 1));

                    }
                    countryList.add(countryBean);
                    countryAllList.addAll(countryList);
                    deleteAll();
                    for (int i = 0; i < countryAllList.size(); i++) {
                        CountryBean countryBean1 = countryAllList.get(i);
                        countryBean1.setId(null);
                        daoSession.insert(countryBean1);
                    }
                }
            } else {

                daoSession.insert(countryBean);
            }
            List<CountryBean> countryAllList = queryAllCountry();
            travelAssistantLocationCityAdapter.setTravelAssistantLocationCityList(countryAllList);
            travelAssistantLocationCityAdapter.notifyDataSetChanged();
            isContainSameCountry = false;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<CountryBean> queryAllCountry()
    {
        List<CountryBean> countryBeanList = ((BasicApplication) getApplication()).getDaoSession().loadAll(CountryBean.class);
        return countryBeanList;
    }
    public void deleteAll() {
        DaoSession daoSession = ((BasicApplication) getApplication()).getDaoSession();
        daoSession.deleteAll(CountryBean.class);
    }
    }

