package com.nbhysj.coupon.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

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
import com.nbhysj.coupon.util.LogUtil;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.nbhysj.coupon.util.ToolbarHelper;

import java.net.URLDecoder;

import butterknife.BindView;

/**
 * created by hysj on 2019/11/27.
 * descreiption:webview 攻略
 */
public class StrategyWebActivity extends BaseActivity<LoginPresenter, LoginModel> implements LoginContract.View {
    @BindView(R.id.pb_web)
    ProgressBar myProgressBar;
    @BindView(R.id.webview)
    WebView webview;

    String backPage = "apps://page/interaction/pop";

    String loginPage = "apps://page/push/login";

    String strategyH5Page = "apps://page/push";

    String strategyAlertFailed = "apps://page/alert";

    String strategyCommentUrl = "apps://strategy/commentList";

    String strategyUrl;

    //攻略id
    private int articleId;
    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_strategy_webview;
    }

    @Override
    public void initData() {
        try {

            strategyUrl = getIntent().getStringExtra("url");
            articleId = getIntent().getIntExtra("articleId",0);
            // String encodedURL = URLEncoder.encode("https://sandbox-mobile.caocaokeji.cn/pay-travel/home", "UTF-8");
            setWebView(strategyUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    public static void startWebActivity(Context context, String url) {
        Intent intent = new Intent(context, StrategyWebActivity.class);
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
        // Android 调用 Js 第一种方法：使用下边方式来写
        // 给 WebView 添加 JavaScript接口  参数1：   ；参数2：JS中的变量名
        webview.addJavascriptInterface(new JsInterface(), "obj");
        //  webview.getSettings().setDefaultTextEncodingName("UTF-8");
        /*if (Build.VERSION.SDK_INT >= 21) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW );
      // }


		/*if (Build.VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN)
		{*/
        //  webview.getSettings().setAllowUniversalAccessFromFileURLs(true);
        String token = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.TOKEN, "");
        webview.loadUrl(url + "&token=" + token);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                try {
                    if (!TextUtils.isEmpty(url)) {
                        String decodeUrl = URLDecoder.decode(url, "UTF-8");
                        if (backPage.equals(url)) {

                            StrategyWebActivity.this.finish();

                        } else if (loginPage.equals(url)) {

                            onReLogin("");
                            return true;
                        } else if (url.contains(strategyH5Page)) {

                            url = url.substring(17, url.length());
                        } else if (url.contains(strategyAlertFailed)) {

                            String alert = decodeUrl.substring(25, decodeUrl.length());
                            showToast(StrategyWebActivity.this, alert);
                        } else if (url.contains(strategyCommentUrl))
                        {
                            Intent intent = new Intent();
                            intent.putExtra("articleId",articleId);
                            intent.setClass(StrategyWebActivity.this, StrategyCommentListActivity.class);
                            startActivity(intent);
                            return true;
                        }

                        Uri uri = Uri.parse(url);
                        LogUtil.e("打印Scheme", uri.getScheme() + "==" + url);
                        if ("http".equals(uri.getScheme()) || "https".equals(uri.getScheme())) {
                            view.loadUrl(url);
                            return true;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
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

    public class JsInterface {
        // Android 调用 Js 方法1 中的返回值
        @JavascriptInterface
        public void collection(int result) {
            Toast.makeText(StrategyWebActivity.this, "Android调用Js方法有返回值，返回结果是 -> " + result, Toast.LENGTH_SHORT).show();
        }

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
    public void getLoginSaltResult(BackResult res) {

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
