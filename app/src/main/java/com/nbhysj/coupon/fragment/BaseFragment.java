package com.nbhysj.coupon.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.framework.AppManager;
import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.TUtil;
import com.nbhysj.coupon.util.LogUtil;
import com.nbhysj.coupon.view.MyToastView;
import com.nbhysj.coupon.dialog.LoadingDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * created by hysj on 2018/02/29.
 * description: fragment 基类
 */
public abstract class BaseFragment<T extends BasePresenter, E extends BaseModel> extends Fragment {
    protected T mPresenter;
    protected E mModel;
    private Unbinder binder;
    protected static Context mContext;
    public LoadingDialog mDialog;
    public AppManager appManager;
    public String userId;
    private boolean hasLoaded = false;

    private boolean isCreated = false;

    private boolean isVisibleToUser = false;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        View view = inflater.inflate(this.getLayoutId(), container, false);
        binder = ButterKnife.bind(this, view);
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        appManager = AppManager.getInstance();
        appManager.addActivity(getActivity());
        isCreated = true;
        this.view = view;

        // 用户信息保存
        this.initPresenter();
        this.initView(view);
        this.initData();
        lazyLoad(this.view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.onDestroy();//取消注册，以避免内存泄露
        }
        if (binder != null) {
            binder.unbind();
        }
        isCreated = false;
        hasLoaded = false;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        this.isVisibleToUser = isVisibleToUser;//注：关键步骤
        super.setUserVisibleHint(isVisibleToUser);
        lazyLoad(view);
    }

    private void lazyLoad(View view) {
        if (!isVisibleToUser || hasLoaded || !isCreated) {
            return;
        }
        lazyInitView(view);
        hasLoaded = true;//注：关键步骤，确保数据只加载一次
    }

    public abstract void lazyInitView(View view);

    public abstract int getLayoutId();

    /**
     * 简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
     */
    public abstract void initPresenter();

    protected abstract void initView(View v);

    protected abstract void initData();

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
     * token失效重新登陆
     */
    public void onReLogin(String message) {
        try {
            if (!TextUtils.isEmpty(message)) {
                Toast.makeText(mContext, getResources().getString(R.string.str_login_token_invalid), Toast.LENGTH_SHORT).show();
            }

            // Intent intent = new Intent(mContext, LoginActivity.class);
            // 如果这个activity已经启动了，就不产生新的activity
            //intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            //context.startActivityForResult(intent, Constant.REQUEST_CODE_UPDATE_UI);
            //  mContext.startActivity(intent);
            appManager.exitApp();
        } catch (Exception e) {
            LogUtil.e("跳转登录页面失败", e.getMessage());
        }
    }

    /**
     * Unfortunately Android doesn't have an official API to retrieve the height of
     * StatusBar. This is just a way to hack around, may not work on some devices.
     *
     * @return The height of StatusBar.
     */
    public int getStatusBarHeight() {
        Activity activity = getActivity();
        if (activity == null) return 0;

        Resources resources = getActivity().getResources();
        int result = 0;
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId);
        }
        Log.v("CollectedShop", "getStatusBarHeight: " + result);
        return result;
    }

    /**
     * show the progress dialog
     */
    public void showProgressDialog(Context context) {
        if (validateInternet()) {
            if (mDialog == null) {
                mDialog = new LoadingDialog(context);
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
     * turn to another activity with this activity
     *
     * @param cls the showing activity
     */
    protected void toActivity(Class<?> cls) {
        Intent intent = new Intent(getActivity(), cls);
        startActivity(intent);
    }

    /**
     * 隐藏输入法
     */
    public void hiddenIME() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context
                .INPUT_METHOD_SERVICE);
        IBinder token;
        if (null != getActivity().getCurrentFocus()) {
            token = getActivity().getCurrentFocus().getWindowToken();
        } else {
            token = getActivity().getWindow().getDecorView().getWindowToken();
        }
        imm.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
