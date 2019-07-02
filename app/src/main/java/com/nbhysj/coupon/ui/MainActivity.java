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
    private Fragment fragment;
    private long exitTime = 0;
    /* @BindView(R.id.llyt_main)
     LinearLayout mLlytMain;*/
    private TextView mTvCount;
    private int NOTIFI_ID = 1007;
    //Jpush注册id
    private String uid;
    //用户id
    private String userId;
    private String itemTitle = "";
    private BottomNavigationView navigation;

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

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        initView();
    }

    public void initView() {

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
        setBottomNavigationItem(navigation, 8, 8, 8);
        adjustNavigationIcoSize(navigation);
        //navigation.setSelectedItemId(TabFragment.values()[0].menuId);
    /*    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }*/


       /* fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new BuyHouseFragment());
        //   fragments.add(new HouseFragment());
        fragments.add(new EyeFragment());
        fragments.add(new MineFragment());*/

      /* // notSetStatusBarColor();
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
*/

        //获取整个的NavigationView
     /*   BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigation.getChildAt(0);

        //这里就是获取所添加的每一个Tab(或者叫menu)，
        View tab = menuView.getChildAt(3);
        BottomNavigationItemView itemView = (BottomNavigationItemView) tab;

        //加载我们的角标View，新创建的一个布局
        View badge = LayoutInflater.from(this).inflate(R.layout.layout_menu_badge, menuView, false);

        //添加到Tab上
        itemView.addView(badge);
        mTvCount = (TextView) badge.findViewById(R.id.tv_msg_count);*/

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

        String token = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.TOKEN, "");
        if (TextUtils.isEmpty(token)) {
            goFragment(0);
            navigation.setSelectedItemId(TabFragment.values()[0].menuId);
        }
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


}
