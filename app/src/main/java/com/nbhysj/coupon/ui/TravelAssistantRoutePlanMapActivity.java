package com.nbhysj.coupon.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.TravelAssistantDailyMapRouteAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.TravelAssistantContract;
import com.nbhysj.coupon.model.TravelAssistantModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CountryBean;
import com.nbhysj.coupon.model.response.CreateTripResponse;
import com.nbhysj.coupon.model.response.TravelAssistantDetailCountryBean;
import com.nbhysj.coupon.model.response.TripDetailsResponse;
import com.nbhysj.coupon.model.response.TripHomePageResponse;
import com.nbhysj.coupon.model.response.TripMapResponse;
import com.nbhysj.coupon.model.response.TripRouteMapResponse;
import com.nbhysj.coupon.model.response.TripScenicSpotAddCountryBean;
import com.nbhysj.coupon.presenter.TravelAssistantPresenter;
import com.nbhysj.coupon.systembar.StatusBarCompat;
import com.nbhysj.coupon.systembar.StatusBarUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/05/09
 * description：行程助手地图路线规划
 */
public class TravelAssistantRoutePlanMapActivity extends BaseActivity<TravelAssistantPresenter, TravelAssistantModel> implements TravelAssistantContract.View, AMap.OnMarkerClickListener, AMap.InfoWindowAdapter,
        PoiSearch.OnPoiSearchListener, LocationSource, AMap.OnMapClickListener {

    @BindView(R.id.toolbar_space)
    View mToolbarSpace;
    @BindView(R.id.rv_day_number)
    RecyclerView mRvDayNumber;

    private MapView mapView;
    private AMap aMap;

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;
    private double lat;
    private double lon;

    private int tripId;

    //标志点
    private List<LatLonPoint> marketList;

    //经纬度
    List<LatLng> latLngList;

    //每一天行程
    List<List<TripMapResponse>> tripMapEntityList;

    private TravelAssistantDailyMapRouteAdapter dailyMapRouteAdapter;

    @Override
    public int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusBarCompat.translucentStatusBar(this, false);
        //修改状态栏字体颜色
        StatusBarUtil.setImmersiveStatusBar(this, true);
        return R.layout.activity_travel_assistant_route_plan_map;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        ViewGroup.LayoutParams layoutParams = mToolbarSpace.getLayoutParams();//取控件当前的布局参数
        layoutParams.height = getStatusBarHeight();// 控件的高强制设成状态栏高度
        mToolbarSpace.setLayoutParams(layoutParams); //使设置好的布局参数应用到控件</pre>
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbarSpace.setVisibility(View.VISIBLE);
        } else {
            mToolbarSpace.setVisibility(View.GONE);
        }

        tripId = getIntent().getIntExtra("tripId", 0);

        if (marketList == null) {

            marketList = new ArrayList<>();
        } else {
            marketList.clear();
        }

        if (latLngList == null) {

            latLngList = new ArrayList<LatLng>();

        } else {

            latLngList.clear();
        }

        if (tripMapEntityList == null) {

            tripMapEntityList = new ArrayList<>();
        } else {
            tripMapEntityList.clear();
        }

        //初始化地图控件
        mapView = (MapView) findViewById(R.id.map);
        //必须要写
        mapView.onCreate(savedInstanceState);

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
        init();
    }

    @Override
    public void initData() {

        getTripRouteMap();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(TravelAssistantRoutePlanMapActivity.this);
        linearLayoutManager.setOrientation(linearLayoutManager.HORIZONTAL);
        mRvDayNumber.setLayoutManager(linearLayoutManager);
        dailyMapRouteAdapter = new TravelAssistantDailyMapRouteAdapter(TravelAssistantRoutePlanMapActivity.this, new TravelAssistantDailyMapRouteAdapter.TravelAssistantDailyMapRouteListener() {
            @Override
            public void setTravelAssistantDailyMapRouteListener(List<TripMapResponse> tripMapResponseList) {

                setUpMap(tripMapResponseList);
            }
        });
        dailyMapRouteAdapter.setTravelAssistantDailyMapRouteList(tripMapEntityList);
        mRvDayNumber.setAdapter(dailyMapRouteAdapter);

    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        View view = getLayoutInflater().inflate(R.layout.layout_poi_keyword_search_uri,
                null);
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(marker.getTitle());

        TextView snippet = (TextView) view.findViewById(R.id.snippet);
        snippet.setText(marker.getSnippet());
        return view;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        marker.showInfoWindow();
        return false;
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {

    }

    @Override
    public void deactivate() {

        mLocationClient = null;
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {

    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

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
                    lat = amapLocation.getLatitude();
                    lon = amapLocation.getLongitude();
                    Log.v("pcw", "lat : " + lat + " lon : " + lon);
                    Log.v("pcw", "Country : " + amapLocation.getCountry() + " province : " + amapLocation.getProvince() + " City : " + amapLocation.getCity() + " District : " + amapLocation.getDistrict());


                    // 设置当前地图显示为当前位置
                    aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 13));
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(new LatLng(lat, lon));
                    markerOptions.title("当前位置");
                    markerOptions.visible(true);
                    BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.icon_travel_assistant_location));
                    markerOptions.icon(bitmapDescriptor);
                    aMap.addMarker(markerOptions);

                    // 设置当前地图显示为当前位置
               /*     aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(29.853077,121.582652), 13));
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(new LatLng(29.853077, 121.582652));
                    markerOptions.title("当前位置");
                    markerOptions.visible(true);
                    BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.icon_travel_assistant_location));
                    markerOptions.icon(bitmapDescriptor);
                    aMap.addMarker(markerOptions);*/


                } else {
                    //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }
            }
        }
    };



/*    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_map);


    }*/

    /**
     * * 初始化AMap对象
     */
    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
        }
        /*if (!TextUtils.isEmpty(mLatitude) && !TextUtils.isEmpty(mLongitude)) {
            double latitude = Double.parseDouble(mLatitude);
            double longitude = Double.parseDouble(mLongitude);
            // 设置当前地图显示为当前位置
            aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 16));
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(new LatLng(latitude, longitude));
            markerOptions.title(mchName);
            markerOptions.snippet(address);
            markerOptions.visible(true);
            BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.icon_hotel_location));
            markerOptions.icon(bitmapDescriptor);
            aMap.addMarker(markerOptions);
            // Marker marker = aMap.getMapScreenMarkers().get(0);
            //marker.showInfoWindow();*/
        // setUpMap();
        //  }
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mLocationClient != null) {
            mLocationClient.stopLocation();//停止定位
        }
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        if (mLocationClient != null) {
            mLocationClient.onDestroy();//销毁定位客户端。
        }
    }

    @OnClick({R.id.img_back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:

                TravelAssistantRoutePlanMapActivity.this.finish();

                break;
            default:
                break;
        }
    }

    protected Bitmap getMyBitmap(String pm_val) {
        Bitmap bitmap = BitmapDescriptorFactory.fromResource(
                R.mipmap.icon_place_label).getBitmap();
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                bitmap.getHeight());
        Canvas canvas = new Canvas(bitmap);
        TextPaint textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(35);
        Typeface font = Typeface.create(Typeface.DEFAULT_BOLD, Typeface.BOLD);
        textPaint.setFakeBoldText(true);
        textPaint.setTypeface(font);
        textPaint.setColor(getResources().getColor(R.color.color_text_black7));
        if (pm_val.length() == 1){
            canvas.drawText(pm_val, 20, 38, textPaint);// 设置bitmap上面的文字位置
        } else if (pm_val.length() == 2){

            canvas.drawText(pm_val, 10, 38, textPaint);// 设置bitmap上面的文字位置
        }

        return bitmap;
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
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    TripRouteMapResponse tripRouteMapResponse = res.getData();

                    //每一天的行程点
                    tripMapEntityList = tripRouteMapResponse.getData();

                    List<TripMapResponse> tripAllMapResponseList = tripRouteMapResponse.getTripList();

                    for (TripMapResponse tripMapResponse : tripAllMapResponseList) {
                        double latitude = Double.parseDouble(tripMapResponse.getLatitude());
                        double longitude = Double.parseDouble(tripMapResponse.getLongitude());
                        latLngList.add(new LatLng(latitude, longitude));
                        marketList.add(new LatLonPoint(latitude, longitude));
                    }

                    dailyMapRouteAdapter.setTravelAssistantDailyMapRouteList(tripMapEntityList);
                    dailyMapRouteAdapter.notifyDataSetChanged();
                    setUpMap(tripAllMapResponseList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(TravelAssistantRoutePlanMapActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(TravelAssistantRoutePlanMapActivity.this, Constants.getResultMsg(msg));
    }

    public void getTripRouteMap() {

        showProgressDialog(TravelAssistantRoutePlanMapActivity.this);
        mPresenter.getTripRouteMap(tripId);
    }

    /**
     * 配置定位参数
     */
    private void setUpMap(List<TripMapResponse> allTripMapList) {
        aMap.clear();
        Marker marker = null;
        if (allTripMapList != null) {
            if (allTripMapList.size() > 0) {
                for (int i = 0; i < allTripMapList.size(); i++) {

                    TripMapResponse tripMapResponse = allTripMapList.get(i);

                    double latitude = Double.parseDouble(tripMapResponse.getLatitude());
                    double longitude = Double.parseDouble(tripMapResponse.getLongitude());
                    int position = i + 1;
                    String title = tripMapResponse.getTitle();

                    aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
                            latitude,//1.设置纬度 latitude  2./设置经度 longitude     
                            longitude), 13));

                    marker = aMap.addMarker(new MarkerOptions()
                            .position(new LatLng(latitude,//设置纬度       
                                    longitude))//设置经度                 
                            .title(title)//设置标题   
                            .setFlat(false) // 将Marker设置为贴地显示，可以双指下拉地图查看效果 
                            .icon(BitmapDescriptorFactory.fromBitmap(getMyBitmap(String.valueOf(position))))
                            .draggable(true));
                }
                marker.showInfoWindow();

                //起点位置和  地图界面大小控制
                aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngList.get(0), 13));
                aMap.setMapTextZIndex(2);
                // 绘制一条带有纹理的直线
                aMap.addPolyline((new PolylineOptions())
                        //手动数据测试
                        //.add(new LatLng(26.57, 106.71),new LatLng(26.14,105.55),new LatLng(26.58, 104.82), new LatLng(30.67, 104.06))
                        //集合数据
                        .addAll(latLngList)
                        //线的宽度
                        .width(10).setDottedLine(false).geodesic(true)
                        //颜色
                        .color(Color.argb(255, 0, 238, 238)));
            }
        }
    }
}
