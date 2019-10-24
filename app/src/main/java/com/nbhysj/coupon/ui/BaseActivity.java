package com.nbhysj.coupon.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.ColorInt;
import android.support.v4.graphics.ColorUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.amap.api.maps.model.LatLng;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.framework.AppManager;
import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.IBaseActivity;
import com.nbhysj.coupon.framework.TUtil;
import com.nbhysj.coupon.systembar.StatusBarCompat;
import com.nbhysj.coupon.systembar.StatusBarUtil;
import com.nbhysj.coupon.util.LogUtil;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.nbhysj.coupon.dialog.LoadingDialog;
import com.nbhysj.coupon.view.MyToastView;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @auther：hysj created on 2019/01/23
 * description：Activity 基类
 */

public abstract class BaseActivity<T extends BasePresenter, E extends BaseModel> extends RxAppCompatActivity implements IBaseActivity {
    protected Context mContext;
    protected T mPresenter;
    protected E mModel;
    private Unbinder binder;
    public LoadingDialog mDialog;
    public static BaseActivity mTopActivity;
    public AppManager appManager;
    public int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        String name = this.getComponentName().getClassName();
        boolean flag = name.contains("LoadingActivity");
        if (flag) {
            if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
                finish();
                return;
            }
        }
        mContext = this;
        this.initBundleParams(savedInstanceState);
        this.setContentView(this.getLayoutId());
        binder = ButterKnife.bind(this);
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        appManager = AppManager.getInstance();
        appManager.addActivity(this);
        this.initPresenter();
        this.initView(savedInstanceState);
        this.initData();

    }

    @Override
    public void initBundleParams(Bundle savedInstanceState) {

    }

    public abstract int getLayoutId();

    public abstract void initView(Bundle savedInstanceState);

    public abstract void initData();

    /**
     * 简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
     */
    public abstract void initPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();//取消注册，以避免内存泄露
        }
        if (appManager != null) {
            appManager.finishSingleActivity(this);
        }

        dismissProgressDialog();
        if (binder != null) {
            binder.unbind();
        }

        if (mTopActivity == this) {
            mTopActivity = null;
        }
    }

    /**
     * @param @return
     * @return boolean
     * @throws
     * @Title: network connection;
     * @Description: Determine whether the network connection
     */
    public boolean validateInternet() {
        ConnectivityManager manager = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager == null) {
            promptNoConnect();
            return false;
        } else {
            NetworkInfo[] info = manager.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        promptNoConnect();
        return false;
    }

    public void promptNoConnect() {

        Toast.makeText(mContext, getResources().getString(R.string.str_no_net_connect), Toast.LENGTH_SHORT).show();
    }

    public void showToast(Context context, String message) {

        // Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        MyToastView.showText(context, message);
    }

    /**
     * show the progress dialog
     */
    public void showProgressDialog(Context context) {
        if (validateInternet()) {
            if (mDialog == null) {
                mDialog = new LoadingDialog(mContext);
            }
            mDialog.setCanceledOnTouchOutside(false);
            mDialog.setCancelable(true);

            if (mDialog.isShowing() == false) {

                mDialog.show();
            }
        }
    }

    /**
     * Dismiss the progress dialog
     */
    public void dismissProgressDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public String getCurrentVersionName() {
        try {
            PackageManager manager = mContext.getPackageManager();
            PackageInfo info = manager.getPackageInfo(
                    mContext.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public int getLocalVersionCode() {
        PackageManager pm = getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 隐藏输入法
     */
    public void hiddenIME() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context
                .INPUT_METHOD_SERVICE);
        IBinder token;
        if (null != getCurrentFocus()) {
            token = getCurrentFocus().getWindowToken();
        } else {
            token = mTopActivity.getWindow().getDecorView().getWindowToken();
        }
        imm.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * token失效重新登陆
     */
    public void onReLogin() {
        try {
           /* if (!TextUtils.isEmpty(message)) {
                Toast.makeText(mContext, getResources().getString(R.string.str_login_token_invalid), Toast.LENGTH_SHORT).show();
            }*/

           /*  Intent intent = new Intent(mContext, LoginActivity.class);
             mContext.startActivity(intent);*/
            // 如果这个activity已经启动了，就不产生新的activity

            //intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            //context.startActivityForResult(intent, Constant.REQUEST_CODE_UPDATE_UI);
            SharedPreferencesUtils.clearSharePreference();

            // appManager.exitApp(this);
        } catch (Exception e) {
            LogUtil.e("跳转登录页面失败", e.getMessage());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTopActivity = this;
        // userId = (Integer) SharedPreferencesUtils.getData(SharedPreferencesUtils.USER_ID,0);
    }

    /**
     * turn to another activity with this activity
     *
     * @param cls the showing activity
     */
    protected void toActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    /**
     * turn to another activity with this activity
     *
     * @param cls the showing activity
     */
    protected void toActivityForResult(Class<?> cls, int requestCode) {
        Intent intent = new Intent(this, cls);
        startActivityForResult(intent, requestCode);
    }

    /**
     * turn to another activity with this activity
     *
     * @param cls the showing activity
     */
    protected void toActivityWithParameters(Class<?> cls, Intent intent) {
        startActivity(intent);
    }

    /**
     * turn to another activity with this activity finished
     *
     * @param bundle the message will use in next activity
     * @param cls    the showing activity
     */
    protected void toActivityWithFinish(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        this.finish();
    }

    /**
     * Unfortunately Android doesn't have an official API to retrieve the height of
     * StatusBar. This is just a way to hack around, may not work on some devices.
     *
     * @return The height of StatusBar.
     */
    public int getStatusBarHeight() {
        Activity activity = this;
        if (activity == null) return 0;

        Resources resources = mContext.getResources();
        int result = 0;
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId);
        }
        Log.v("CollectedShop", "getStatusBarHeight: " + result);
        return result;
    }

    /**
     * Android 6.0 以上设置状态栏颜色
     */
    protected void setStatusBar(@ColorInt int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // 设置状态栏底色颜色
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(color);

            // 如果亮色，设置状态栏文字为黑色
            if (isLightColor(color)) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            }
        }
    }

    /**
     * 判断颜色是不是亮色
     *
     * @param color
     * @return
     * @from https://stackoverflow.com/questions/24260853/check-if-color-is-dark-or-light-in-android
     */
    private boolean isLightColor(@ColorInt int color) {
        return ColorUtils.calculateLuminance(color) >= 0.5;
    }

    /**
     * 获取StatusBar颜色，默认白色
     *
     * @return
     */
    protected @ColorInt
    int getStatusBarColor() {
        return Color.WHITE;

    }

    public void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        // 隐藏软键盘
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
    }

    /**
     * 监听回退键
     */
    @Override
    public void onBackPressed() {
        if (mDialog != null) {
            if (mDialog.isShowing()) {
                mDialog.cancel();
            } else {
                finish();
            }
        } else {
            finish();
        }
    }


    //获取用户Id
    public int getSharedPreferencesUserId() {

        userId = (Integer) SharedPreferencesUtils.getData(SharedPreferencesUtils.USER_ID, 0);

        return userId;
    }

    /**
     * 跳转百度地图
     */
    public void goToBaiduMap(String mchName,String mLatitude,String mLongitude) {
       /* if (!isInstalled("com.baidu.BaiduMap")) {
            showToast(getActivity(), "请先安装百度地图客户端");
            return;
        }*/
        Intent intent = new Intent();
        intent.setData(Uri.parse("baidumap://map/direction?destination=latlng:"
                + mLatitude + ","
                + mLongitude + "|name:" + mchName + // 终点
                "&mode=driving" + // 导航路线方式
                "&src=" + getPackageName()));
        startActivity(intent); // 启动调用
    }


    /**
     * 跳转高德地图
     */
    public void goToGaodeMap(String mchName,String mLatitude,String mLongitude) {
      /*  if (!isInstalled("com.autonavi.minimap")) {
            //showToast(getActivity(), "请先安装高德地图客户端");
            return;
        }*/
        double latitude = Double.parseDouble(mLatitude);
        double longitude = Double.parseDouble(mLongitude);
        LatLng endPoint = BD2GCJ(new LatLng(latitude, longitude));//坐标转换
        StringBuffer stringBuffer = new StringBuffer("androidamap://navi?sourceApplication=").append("amap");
        stringBuffer.append("&lat=").append(endPoint.latitude)
                .append("&lon=").append(endPoint.longitude).append("&keywords=" + mchName)
                .append("&dev=").append(0)
                .append("&style=").append(2);
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(stringBuffer.toString()));
        intent.setPackage("com.autonavi.minimap");
        startActivity(intent);
    }
    /**
     * 打开高德地图
     * https://lbs.amap.com/api/amap-mobile/guide/android/route
     */
    public void openGaodeMapToGuide(String mchName,String mLatitude,String mLongitude) {
        String url;
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        String curLatitude = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.LATITUDE,"");
        String curLongitude = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.LONGITUDE,"");
        Log.i("高德定位：","经度："+curLatitude+" ,纬度："+curLongitude);
        if (Double.parseDouble(curLatitude) == 0.0 || Double.parseDouble(curLongitude) == 0.0){
            url = "androidamap://route?sourceApplication=amap&dlat="+mLatitude+"&dlon="+mLongitude+"&dname="+mchName+"&dev=0&t=1";
        }else {
            url = "androidamap://route?sourceApplication=amap&slat="+curLatitude+"&slon="+curLongitude
                    +"&dlat="+mLatitude+"&dlon="+mLongitude+"&dname="+mchName+"&dev=0&t=1";
        }


        Uri uri = Uri.parse(url);
        //将功能Scheme以URI的方式传入data
        intent.setData(uri);
        //启动该页面即可
        startActivity(intent);
    }


    /**
     * BD-09 坐标转换成 GCJ-02 坐标
     */
    public static LatLng BD2GCJ(LatLng bd) {
        double x = bd.longitude - 0.0065, y = bd.latitude - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * Math.PI);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * Math.PI);

        double lng = z * Math.cos(theta);//lng
        double lat = z * Math.sin(theta);//lat
        return new LatLng(lat, lng);
    }

    /**
     * GCJ-02 坐标转换成 BD-09 坐标
     */
    public static LatLng GCJ2BD(LatLng bd) {
        double x = bd.longitude, y = bd.latitude;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * Math.PI);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * Math.PI);
        double tempLon = z * Math.cos(theta) + 0.0065;
        double tempLat = z * Math.sin(theta) + 0.006;
        return new LatLng(tempLat, tempLon);
    }


    /**
     * 检测程序是否安装
     *
     * @param packageName
     * @return
     */
    public boolean isInstalled(String packageName) {
        PackageManager manager = mContext.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> installedPackages = manager.getInstalledPackages(0);
        if (installedPackages != null) {
            for (PackageInfo info : installedPackages) {
                if (info.packageName.equals(packageName))
                    return true;
            }
        }
        return false;
    }

}
