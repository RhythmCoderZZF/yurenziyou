package com.nbhysj.coupon.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Enum.MchTypeEnum;
import com.nbhysj.coupon.model.response.MchGoodsBean;
import com.nbhysj.coupon.ui.GroupMchOrderSubmitActivity;
import com.nbhysj.coupon.ui.HotelOrderActivity;
import com.nbhysj.coupon.ui.OrderSubmitActivity;
import com.nbhysj.coupon.util.LogUtil;
import com.nbhysj.coupon.view.HotelDetailSupplementBannerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther：hysj created on 2019/09/23
 * description：购票须知
 */
public class PurchaseInstructionsDialog extends DialogFragment {
    private Context context;
    private View view;
    MchGoodsBean mchHotelGoodsBean;
    WebView webview;
    ProgressBar myProgressBar;
    //默认价格
    private TextView mTvDefaultPrice;
    //市场价格
    private TextView mTvMarketPrice;
    //立即预购
    private TextView mTvReservationImmdiate;
    private String mchName;
    private String mchType;
    //已减
    private TextView mTvAlreadyReducedPrice;

    public PurchaseInstructionsDialog() {

    }

    @SuppressLint("ValidFragment")
    public PurchaseInstructionsDialog(MchGoodsBean mchHotelGoodsBean, String mchType, String mchName) {

        this.mchHotelGoodsBean = mchHotelGoodsBean;
        this.mchType = mchType;
        this.mchName = mchName;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        context = getActivity();
        view = LayoutInflater.from(context).inflate(R.layout.layout_purchase_instructions_dialog, null);

        Dialog dialog = new Dialog(context, R.style.CustomDatePickerDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // must be called before set content
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);

        // 设置宽度为屏宽、靠近屏幕底部
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);

        initView();
        return dialog;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    private void initView() {

        RelativeLayout mRlytPurchaseInstructions = view.findViewById(R.id.rlyt_purchase_instructions);
        TextView mTvHotelTitle = view.findViewById(R.id.tv_title);

        ImageView mImgHotelDetailSupplementCancel = view.findViewById(R.id.img_hotel_detail_supplememt_cancel);
        webview = view.findViewById(R.id.webview);
        mTvDefaultPrice = view.findViewById(R.id.tv_default_price);
        mTvMarketPrice = view.findViewById(R.id.tv_market_price);
        mTvReservationImmdiate = view.findViewById(R.id.tv_immediate_reservation);
        mTvAlreadyReducedPrice = view.findViewById(R.id.tv_already_reduced_price);

        myProgressBar = view.findViewById(R.id.pb_web);
        if(mchHotelGoodsBean != null) {
            String title = mchHotelGoodsBean.getTitle();
            double marketPrice = mchHotelGoodsBean.getMarketPrice();
            double defaultPrice = mchHotelGoodsBean.getDefaultPrice();
            mTvHotelTitle.setText(title);
            mTvDefaultPrice.setText(String.valueOf(defaultPrice));
            mTvMarketPrice.setText("¥" + String.valueOf(marketPrice));

            double discountAmount = marketPrice - defaultPrice;
            mTvDefaultPrice.setText(String.valueOf(defaultPrice));
            mTvAlreadyReducedPrice.setText("已减" + String.valueOf(discountAmount) + "元");

            mTvMarketPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
        }
        mRlytPurchaseInstructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();
            }
        });

        mImgHotelDetailSupplementCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();
            }
        });

        mTvReservationImmdiate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mchScenicType = MchTypeEnum.MCH_SCENIC.getValue();
                String mchRecreationType = MchTypeEnum.MCH_RECREATION.getValue();
                String mchGroupType = MchTypeEnum.MCH_GROUP.getValue();
                Intent intent = new Intent();

                int goodsId = 0;
                if(mchType.equals(mchScenicType) || mchType.equals(mchRecreationType))
                {
                    intent.putExtra("mchType", mchType);
                    goodsId = mchHotelGoodsBean.getGoodsId();
                    intent.setClass(context, OrderSubmitActivity.class);

                } else if(mchType.equals(mchGroupType))
                {
                    goodsId = mchHotelGoodsBean.getId();
                    intent.setClass(context, GroupMchOrderSubmitActivity.class);
                }

              ;
                intent.putExtra("goodsId", goodsId);
                startActivity(intent);
            }
        });
        String goodsBuyNotes = mchHotelGoodsBean.getGoodsBuyNotes();
        setWebView(goodsBuyNotes);

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
        String appCachePath = context.getApplicationContext().getCacheDir()
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

                Uri uri = Uri.parse(url);
                LogUtil.e("打印Scheme", uri.getScheme() + "==" + url);
                if (!"http".equals(uri.getScheme()) || !"https".equals(uri.getScheme())) {
                    return false;
                } else {
                    view.loadUrl(url);
                    return false;
                }
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

    @Override
    public void show(FragmentManager manager, String tag) {
        //KeyBoardUtils.closeKeybord((Activity) context);
        super.show(manager, tag);
    }


}
