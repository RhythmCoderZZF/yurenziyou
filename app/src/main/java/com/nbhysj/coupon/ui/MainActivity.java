package com.nbhysj.coupon.ui;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.fragment.CameraFragment;
import com.nbhysj.coupon.fragment.HomeFragment;
import com.nbhysj.coupon.fragment.MineFragment;
import com.nbhysj.coupon.fragment.ShoppingMallFragment;
import com.nbhysj.coupon.fragment.TravelAssistantFragment;
import com.nbhysj.coupon.systembar.StatusBarCompat;
import com.nbhysj.coupon.systembar.StatusBarUtil;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.nbhysj.coupon.view.BottomNavigationViewHelper;
import com.umeng.socialize.UMShareAPI;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther：hysj created on 2019/03/08
 * description：主页面
 */
public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private FragmentTransaction ft;
    private Fragment CURRENT_FRAGMENT;
    private List<Fragment> fragments;
    /* @BindView(R.id.llyt_main)
     LinearLayout mLlytMain;*/
    private int NOTIFI_ID = 1007;
    //Jpush注册id
    private String uid;
    //用户id
    private String userId;
    private String itemTitle = "";
    private BottomNavigationView navigation;
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private String mLatitude = "";
    private String mLongitude = "";
    private enum TabFragment {
        main(R.id.navigation_main, HomeFragment.class),
        house(R.id.navigation_house, ShoppingMallFragment.class),
        camera(R.id.navigation_camera, CameraFragment.class),
        footprint(R.id.navigation_footprint, TravelAssistantFragment.class),
        mine(R.id.navigation_mine, MineFragment.class),
        ;

        private Fragment fragment;
        private final int menuId;
        private final Class<? extends Fragment> clazz;


        TabFragment(@IdRes int menuId, Class<? extends Fragment> clazz) {
            this.menuId = menuId;
            this.clazz = clazz;
        }

        @NonNull
        public Fragment fragment() {
            if (fragment == null) {
                try {
                    fragment = clazz.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                    fragment = new Fragment();
                }
            }
            return fragment;
        }

        public static TabFragment from(int itemId) {
            for (TabFragment fragment : values()) {
                if (fragment.menuId == itemId) {
                    return fragment;
                }
            }
            return main;
        }

        public static void onDestroy() {
            for (TabFragment fragment : values()) {
                fragment.fragment = null;
            }
        }
    }

    @Override
    public int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusBarCompat.translucentStatusBar(this, false);
        //修改状态栏字体颜色
        StatusBarUtil.setImmersiveStatusBar(this, true);
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        initLocation();
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        initView();
    }

    public void initView() {

       int currentItem = getIntent().getIntExtra("currentItem",0);
        //沉浸式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new ShoppingMallFragment());
        fragments.add(new CameraFragment());
        fragments.add(new TravelAssistantFragment());
        fragments.add(new MineFragment());
        //  msgListCountResult();
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        navigation.setSelectedItemId(TabFragment.values()[0].menuId);
        navigation.setItemIconTintList(null);
        setBottomNavigationItem(navigation, 8, 8, 8);
        adjustNavigationIcoSize(navigation);

        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.READ_LOGS,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.SET_DEBUG_APP,
                    Manifest.permission.SYSTEM_ALERT_WINDOW,
                    Manifest.permission.GET_ACCOUNTS,
                    Manifest.permission.WRITE_APN_SETTINGS,
                    Manifest.permission.RECORD_AUDIO};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }
      //  EventBus.getDefault().register(this);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {


    }

    public void adjustNavigationIcoSize(BottomNavigationView navigation) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigation.getChildAt(0);
        for (int i = 0; i < menuView.getChildCount(); i++) {
            final View iconView = menuView.getChildAt(i).findViewById(android.support.design.R.id.icon);
            final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
            final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, displayMetrics);
            layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, displayMetrics);
            iconView.setLayoutParams(layoutParams);
        }
    }

    public void goFragment(int index) {

        ft = getSupportFragmentManager().beginTransaction();
        if (null != CURRENT_FRAGMENT) {
            ft.hide(CURRENT_FRAGMENT);
        }
        Fragment fragment = getSupportFragmentManager()
                .findFragmentByTag(fragments.get(index).getClass().getName());
        if (fragment == null) {
            fragment = fragments.get(index);
        }
        CURRENT_FRAGMENT = fragment;
        if (!fragment.isAdded()) {
            ft.add(R.id.frame, fragment, fragment.getClass().getName());
        } else {
            ft.show(fragment);
        }
        ft.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
       /* getSupportFragmentManager()
                .beginTransaction()
                //.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.framelayout, TabFragment.from(item.getItemId()).fragment())
                .commit();*/
        int index = 0;

        String title = item.getTitle().toString();
        if (!TextUtils.isEmpty(title)) {
            if (!itemTitle.equals(title)) {
                if (title.equals("首页")) {
                    index = 0;
                } else if (title.equals("商城")) {
                    index = 1;
                } else if (title.equals("助手")) {
                    index = 3;
                } else if (title.equals("我的")) {
                    index = 4;
                }
                itemTitle = title;
                ft = getSupportFragmentManager().beginTransaction();
                if (null != CURRENT_FRAGMENT) {
                    ft.hide(CURRENT_FRAGMENT);
                }
                Fragment fragment = getSupportFragmentManager()
                        .findFragmentByTag(fragments.get(index).getClass().getName());
                if (fragment == null) {
                    fragment = fragments.get(index);
                }
                CURRENT_FRAGMENT = fragment;
                if (!fragment.isAdded()) {
                    ft.add(R.id.frame, fragment, fragment.getClass().getName());
                } else {
                    ft.show(fragment);
                }
                ft.commit();
            }
        } else {

            toActivity(PublishNoteActivity.class);

        }
        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        TabFragment.onDestroy();
        UMShareAPI.get(this).release();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        UMShareAPI.get(this).onSaveInstanceState(outState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        try {

            if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {

                exitApp();
                return false;
            }

            return false;
        } catch (Exception e) {
            Log.d("返回错误：", e.toString());
        }
        return true;
    }

    public void exitApp() {
       /* if ((System.currentTimeMillis() - exitTime) > 2000) {

            Snackbar snackbar = Snackbar.make(mLlytMain, "再按一次退出", Snackbar.LENGTH_LONG).setDuration(1000);
            View view = snackbar.getView();//获取Snackbar的view
            if (view != null) {
                view.setBackgroundColor(getResources().getColor(R.color.colorPrimary));//修改view的背景色
                ((TextView) view.findViewById(R.id.snackbar_text)).setTextColor(getResources().getColor(R.color.white));//获取Snackbar的message控件，修改字体颜色
            }
            snackbar.show();
            exitTime = System.currentTimeMillis();
        } else {
            moveTaskToBack(true);
        }*/

        moveTaskToBack(true);
    }


    /*@Override
    public void msgListCountResult(NoReadNumBean res) {
        try {
            dismissProgressDialog();
            switch (res.getStatus_code()) {
                case Constants.SUCCESS_CODE:
                    NoReadNumBean.DataBean readNum = res.getData();
                    int count = readNum.getCount();
                    if (count > 0) {
                        mTvCount.setVisibility(View.VISIBLE);
                        if (count > 99) {

                            mTvCount.setText("99+");
                        } else {
                            mTvCount.setText(String.valueOf(count));
                        }
                    } else {
                        mTvCount.setVisibility(View.GONE);
                    }

                    //如果没有消息，不需要显示的时候那只需要将它隐藏即可
                    //count.setVisibility(View.GONE);
                    //设置应用在桌面上显示的角标
                    if (!Build.MANUFACTURER.equalsIgnoreCase(MobileBrand.XIAOMI)) {
                        BadgeNumberManager.from(MainActivity.this).setBadgeNumber(count);
                    } else {
                        setXiaomiBadgeNumber(count);
                  *//*  btnSetBadge.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setXiaomiBadgeNumber();
                        }
                    },3000);*//*
                        //小米手机如果在应用内直接调用设置角标的方法，设置角标会不生效,因为在退出应用的时候角标会自动消除
                        //这里先退出应用，延迟3秒后再进行角标的设置，模拟在后台收到推送并更新角标的情景
                        // moveTaskToBack(true);
                    }
                    break;
                case Constants.UNAUTHORIZED_401:
                    //  updateToken();
                    break;
                case Constants.UNAUTHORIZED_402:
                    onReLogin(res.getMessage());
                    break;
                default:
                    showToast(MainActivity.this, Constants.getResultMsg(res.getMessage()));
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/

    private void setXiaomiBadgeNumber(int count) {

        String content = "收到" + 1 + "条消息";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext());
        builder.setSmallIcon(R.mipmap.ic_launcher_round);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setTicker("收到" + 1 + "条消息");
        builder.setWhen(System.currentTimeMillis());
        Intent intent = new Intent(getBaseContext(), MainActivity.class);

        intent.setAction(getApplicationContext().getPackageName());
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pi = PendingIntent.getActivity(getBaseContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pi);
        builder.setContentTitle(getResources().getText(R.string.app_name));
        builder.setContentText(content);

        final Notification n = builder.build();
        int defaults = Notification.DEFAULT_LIGHTS;

        defaults |= Notification.DEFAULT_SOUND;

        defaults |= Notification.DEFAULT_VIBRATE;

        n.defaults = defaults;
        n.flags = Notification.FLAG_SHOW_LIGHTS | Notification.FLAG_AUTO_CANCEL;

        setBadgeOfXiaomi(MainActivity.this, n, NOTIFI_ID, count);

      /*  NotificationManager notificationManager = (NotificationManager) MainActivity.this.
                getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(MainActivity.this)
                .setSmallIcon(MainActivity.this.getApplicationInfo().icon)
                .setWhen(System.currentTimeMillis())
                .setContentTitle("推送标题")
                .setContentText("我是推送内容")
                .setTicker("ticker")
                .setAutoCancel(true)
                .build();
        //相邻的两次角标设置如果数字相同的话，好像下一次会不生效
        BadgeNumberManagerXiaoMi.setBadgeNumber(notification,2);
        notificationManager.notify(1000, notification);*/
    }

    //小米
    private static void setBadgeOfXiaomi(final Context context, final Notification notification, final int NOTIFI_ID, final int num) {

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                try {
                    Field field = notification.getClass().getDeclaredField("extraNotification");

                    Object extraNotification = field.get(notification);

                    Method method = extraNotification.getClass().getDeclaredMethod("setMessageCount", int.class);

                    method.invoke(extraNotification, num);

                } catch (Exception e) {

                    e.printStackTrace();
                    Log.e("Xiaomi" + " Badge error", "set Badge failed");

                }
//                mNotificationManager.notify(0,notification);
                NotificationManager notifyMgr = (NotificationManager) (context.getSystemService(NOTIFICATION_SERVICE));
                if (num != 0) {
                    notifyMgr.notify(NOTIFI_ID, notification);
                } else {
                    notifyMgr.cancel(NOTIFI_ID);
                }
            }
        }, 550);
    }

    @Override
    protected void onResume() {
        super.onResume();

     /*   String token = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.TOKEN, "");
        if (TextUtils.isEmpty(token)) {
            goFragment(0);
            navigation.setSelectedItemId(TabFragment.values()[0].menuId);
        }*/
    }


    private void setBottomNavigationItem(BottomNavigationView bottomNavigationBar, int space, int imgLen, int textSize) {
        Class barClass = bottomNavigationBar.getClass();
        Field[] fields = barClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            if (field.getName().equals("mTabContainer")) {
                try {
                    //反射得到 mTabContainer
                    LinearLayout mTabContainer = (LinearLayout) field.get(bottomNavigationBar);
                    for (int j = 0; j < mTabContainer.getChildCount(); j++) {
                        //获取到容器内的各个Tab
                        View view = mTabContainer.getChildAt(j);
                        //获取到Tab内的各个显示控件
                        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dip2px(56));
                        FrameLayout container = (FrameLayout) view.findViewById(R.id.fixed_bottom_navigation_container);
                        container.setLayoutParams(params);
                        container.setPadding(dip2px(12), dip2px(0), dip2px(12), dip2px(0));

                        //获取到Tab内的文字控件
                        TextView labelView = (TextView) view.findViewById(com.ashokvarma.bottomnavigation.R.id.fixed_bottom_navigation_title);
                        //计算文字的高度DP值并设置，setTextSize为设置文字正方形的对角线长度，所以：文字高度（总内容高度减去间距和图片高度）*根号2即为对角线长度，此处用DP值，设置该值即可。
                        labelView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
                        labelView.setIncludeFontPadding(false);
                        labelView.setPadding(0, 0, 0, dip2px(20 - textSize - space / 2));

                        //获取到Tab内的图像控件
                        ImageView iconView = (ImageView) view.findViewById(com.ashokvarma.bottomnavigation.R.id.fixed_bottom_navigation_icon);
                        //设置图片参数，其中，MethodUtils.dip2px()：换算dp值
                        params = new FrameLayout.LayoutParams(20, 20);
                        params.setMargins(0, 0, 0, 10);
                        params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
                        iconView.setLayoutParams(params);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int dip2px(float dpValue) {
        final float scale = getApplication().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    /**
     * 初始化定位
     */
    private void initLocation() {

        //初始化client
        locationClient = new AMapLocationClient(MainActivity.this);
        locationOption = getDefaultOption();
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(locationListener);

        startLocation();
    }


    /**
     * 默认的定位参数
     *
     * @since 2.8.0
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
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

                SharedPreferencesUtils.saveLongitudeAndLatitudeData(mLatitude,mLongitude);
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

   /* @Subscribe
    public void onEvent(String oprate) {

         if(oprate.equals("backHomePage"))
         {
             goFragment(0);
             navigation.setSelectedItemId(TabFragment.values()[0].menuId);
         } else if(oprate.equals("backMyCollection"))
         {
             goFragment(4);
             navigation.setSelectedItemId(TabFragment.values()[4].menuId);
         }
    }*/
}
