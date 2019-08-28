package com.nbhysj.coupon.ui;

import android.content.Context;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.LoginContract;
import com.nbhysj.coupon.model.LoginModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.LoginResponse;
import com.nbhysj.coupon.model.response.ThirdPartyLoginStatusResponse;
import com.nbhysj.coupon.model.response.UserInfoResponse;
import com.nbhysj.coupon.presenter.LoginPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.ToolbarHelper;

import java.net.URLDecoder;
import java.net.URLEncoder;

import butterknife.BindView;

/**
 * created by hysj on 2019/08/27.
 */
public class WebActivity extends BaseActivity<LoginPresenter, LoginModel> implements LoginContract.View {
    @BindView(R.id.pb_web)
    ProgressBar myProgressBar;
    @BindView(R.id.webview)
    WebView webview;

    private int mType;
    private String joinus;
    private String about;
    private String service;


    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_web;
    }

   /* @Override
    public void initView() {
        Intent intent = getIntent();
        mType = intent.getIntExtra("type", 0);
        service = intent.getStringExtra("service");

    }*/

    @Override
    public void initData() {
        try {
            ToolbarHelper.setBar(this, "用车", R.mipmap.icon_left_arrow_black);
            String carH5Url = getIntent().getStringExtra("carH5Url");
            String encodedURL = URLEncoder.encode("https://sandbox-mobile.caocaokeji.cn/pay-travel/home", "UTF-8");
            setWebView("https://sandbox-mobile.caocaokeji.cn/pay-travel/home?client_id=d40e8cab04052bc1&timestamp=1566904554718&ext_user_id=49&sign=dfdc7d2e747dcea4f46b203fe1aa6be04ef94990&startLg=121.583030&startLt=121.583030&startType=0&callback_info=%7B%22userId%22:49%7D");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    public static void startWebActivity(Context context, String url) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }


    public void setWebView(String url) {

        WebSettings settings = webview.getSettings();
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setJavaScriptEnabled(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setHorizontalScrollBarEnabled(true);
        webview.setVerticalScrollBarEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);

        webview.getSettings().setAppCacheMaxSize(1024 * 1024 * 8);
        String appCachePath = getApplicationContext().getCacheDir()
                .getAbsolutePath();
        webview.getSettings().setAppCachePath(appCachePath);
       // webview.getSettings().setAllowFileAccess(true);
        webview.getSettings().setAppCacheEnabled(true);
      //  webview.getSettings().setDefaultTextEncodingName("UTF-8");
        /*if (Build.VERSION.SDK_INT >= 21) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW );
      // }


		/*if (Build.VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN)
		{*/
      //  webview.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webview.loadUrl(url);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);

                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {

                handler.proceed();//接受证书
                super.onReceivedSslError(view, handler, error);
            }

        });

        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                //这里将textView换成你的progress来设置进度
                if (null != myProgressBar) {
                    if (newProgress == 0) {
                        myProgressBar.setVisibility(View.VISIBLE);
                    }
                    myProgressBar.setProgress(newProgress);
                    myProgressBar.postInvalidate();
                    if (newProgress == 100) {
                        myProgressBar.setVisibility(View.GONE);
                    }
                }
            }
        });

    }



/*
    private void initWeb() {
        switch (mType) {
            case 0:
                setWebView(service);
                  ToolbarHelper.setBar(this, "关于", R.mipmap.nav_ico_back_black);
                break;
            case 2:
                setWebView(about);
                ToolbarHelper.setBar(this, "关于", R.mipmap.nav_ico_back_black);
                break;
                default:
                break;
        }
    }*/

    @Override
    public void getLoginVerifyCodeResult(BackResult res) {

    }

    @Override
    public void getLoginSaltResult(BackResult<String> res) {

    }

    @Override
    public void loginResult(BackResult<LoginResponse> res) {

    }

    @Override
    public void getUserInfoResult(BackResult<UserInfoResponse> res) {

    }

    @Override
    public void getThirdPartyLoginStatusResult(BackResult<ThirdPartyLoginStatusResponse> res) {

    }

    @Override
    public void thirdPartyLoginResult(BackResult<LoginResponse> res) {

    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void showMsg(String msg) {
        showToast(this, Constants.getResultMsg(msg));
    }


}
