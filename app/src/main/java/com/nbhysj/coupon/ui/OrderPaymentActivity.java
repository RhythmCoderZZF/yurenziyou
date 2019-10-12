package com.nbhysj.coupon.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.common.Enum.PaymentTypeEnum;
import com.nbhysj.coupon.contract.OrderPaymentContract;
import com.nbhysj.coupon.model.OrderPaymentModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.LoginResponse;
import com.nbhysj.coupon.model.response.OrderPaymentResponse;
import com.nbhysj.coupon.model.response.OrderSubmitResponse;
import com.nbhysj.coupon.model.response.SerializableThirdPartyMap;
import com.nbhysj.coupon.pay.alipay.AuthResult;
import com.nbhysj.coupon.pay.alipay.PayResult;
import com.nbhysj.coupon.presenter.OrderPaymentPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.DateUtil;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.nbhysj.coupon.util.Tools;
import com.nbhysj.coupon.view.DestinationBannerView;
import com.nbhysj.coupon.view.HotelDetailBannerView;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.lang.ref.WeakReference;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/05/09
 * description:支付订单
 */
public class OrderPaymentActivity extends BaseActivity<OrderPaymentPresenter, OrderPaymentModel> implements OrderPaymentContract.View {
    //支付方式
    @BindView(R.id.rv_confirm_payment)
    RecyclerView mRvConfirmPayment;
    //微信支付
    @BindView(R.id.rlyt_wechat_payment)
    RelativeLayout mRlytWechatPayment;
    //支付宝支付
    @BindView(R.id.rlyt_alipay_payment)
    RelativeLayout mRlytAlipayPayment;
    @BindView(R.id.img_wechat_payment_check)
    ImageView mImgWechatPaymentCheck;
    @BindView(R.id.img_alipay_payment_check)
    ImageView mImgAlipayPaymentCheck;
    //剩余支付时间
    @BindView(R.id.tv_pay_exprire_time)
    TextView mTvPayExprireTime;
    //金额
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    //商品名字
    @BindView(R.id.tv_good_name)
    TextView mTvGoodName;
    private String paymentMethodType = PaymentTypeEnum.PAYMENT_TYPE_WECHAT.getValue();
    //订单号
    private String orderNo;
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    private BannerHandler mBannerHandler = null;
    public String oredrPaymentBodyParam;
    private IWXAPI api;
    private class BannerHandler extends Handler {
        private WeakReference<DestinationBannerView> weakReference = null;

        public BannerHandler(Context context) {
            super(Looper.getMainLooper());
            this.weakReference = new WeakReference(context);
        }

        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (this.weakReference != null) {
                switch (msg.what) {
                    case SDK_PAY_FLAG: {
                        @SuppressWarnings("unchecked")
                        PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                        /**
                         * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                         */
                        String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                        String resultStatus = payResult.getResultStatus();
                        // 判断resultStatus 为9000则代表支付成功
                        if (resultStatus.equals(Constants.PAYMENT_SUCCESS_CODE_ALIPAY)) {  //支付成功code
                            // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                            // showAlert(PayDemoActivity.this, getString(R.string.pay_success) + payResult);
                           // showToast(OrderPaymentActivity.this,getString(R.string.pay_success));
                            toActivity(MyOrderActivity.class);
                            finish();
                        } else if(TextUtils.equals(resultStatus,Constants.PAYMENT_CANCEL_CODE_ALIPAY)){  // 6001 操作取消

                            toActivity(MyOrderActivity.class);
                            finish();

                        } else {
                            // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                            showToast(OrderPaymentActivity.this,getString(R.string.pay_fail));

                            // showAlert(PayDemoActivity.this, getString(R.string.pay_failed) + payResult);
                        }

                        break;
                    }
                    case SDK_AUTH_FLAG: {
                        @SuppressWarnings("unchecked")
                        AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                        String resultStatus = authResult.getResultStatus();

                        // 判断resultStatus 为“9000”且result_code
                        // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                        if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                            // 获取alipay_open_id，调支付时作为参数extern_token 的value
                            // 传入，则支付账户为该授权账户
                            showToast(OrderPaymentActivity.this,getString(R.string.auth_success));
                        } else {
                            // 其他状态值则为授权失败
                            showToast(OrderPaymentActivity.this,getString(R.string.auth_failed));
                            // showAlert(PayDemoActivity.this, getString(R.string.auth_failed) + authResult);
                        }
                        break;
                    }
                    default:
                        break;
                }
            }
        }
    }

    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_payment_order;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setHeadBar(OrderPaymentActivity.this, getResources().getString(R.string.str_order_pay), R.mipmap.icon_left_arrow_black, "");

    }

    @Override
    public void initData() {

        if (this.mBannerHandler == null)
        {
            this.mBannerHandler = new BannerHandler(OrderPaymentActivity.this);
        }

        double price = getIntent().getDoubleExtra("price",0);
        String title = getIntent().getStringExtra("title");
        long payExprireTime = getIntent().getLongExtra("payExprireTime",0);
        orderNo = getIntent().getStringExtra("orderNo");

        mTvPrice.setText(Tools.getTwoDecimalPoint(price));
        if(!TextUtils.isEmpty(title)) {
            mTvGoodName.setText(title);
        }

        if(payExprireTime != 0) {
            mTvPayExprireTime.setVisibility(View.VISIBLE);
            mTvPayExprireTime.setText("剩余支付时间" + DateUtil.millisToStringShort(payExprireTime));
        } else {
            mTvPayExprireTime.setVisibility(View.GONE);
        }

        mRlytWechatPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paymentMethodType = PaymentTypeEnum.PAYMENT_TYPE_WECHAT.getValue();
                mImgWechatPaymentCheck.setBackgroundResource(R.mipmap.icon_payment_method_check_true);
                mImgAlipayPaymentCheck.setBackgroundResource(R.mipmap.icon_payment_method_check_false);
            }
        });

        mRlytAlipayPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paymentMethodType = PaymentTypeEnum.PAYMENT_TYPE_ALIPAY.getValue();
                mImgAlipayPaymentCheck.setBackgroundResource(R.mipmap.icon_payment_method_check_true);
                mImgWechatPaymentCheck.setBackgroundResource(R.mipmap.icon_payment_method_check_false);
            }
        });
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this,mModel);
    }

    @Override
    public void orderPaymentResult(BackResult<OrderPaymentResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    OrderPaymentResponse orderPaymentResponse = res.getData();

                    oredrPaymentBodyParam = orderPaymentResponse.getBody();                 //支付参数

                    String alipay = PaymentTypeEnum.PAYMENT_TYPE_ALIPAY.getValue();
                    String wechat = PaymentTypeEnum.PAYMENT_TYPE_WECHAT.getValue();

                    if(paymentMethodType.equals(wechat))
                    {
                        String appId = orderPaymentResponse.getAppid();
                        String partnerId = orderPaymentResponse.getPartnerid();
                        String extdata = orderPaymentResponse.getExtdata();
                        String nonceStr = orderPaymentResponse.getNoncestr();
                        String prepayId = orderPaymentResponse.getPrepayid();
                        String sign = orderPaymentResponse.getSign();
                        long timestamp = orderPaymentResponse.getTimestamp();
                        api = WXAPIFactory.createWXAPI(this, appId);
                       // paymentMethodType
                        PayReq req = new PayReq();
                        //req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
                        req.appId			= appId;
                        req.partnerId		= partnerId;
                        req.prepayId		= prepayId;
                        req.nonceStr		= nonceStr;
                        req.timeStamp		= String.valueOf(timestamp);
                        req.packageValue	= "Sign=WXPay";
                        req.sign			= sign;
                        req.extData			= extdata; // optional
                        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                        api.sendReq(req);

                    } else if(paymentMethodType.equals(alipay))
                    {
                       payAlipayV2();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                dismissProgressDialog();
                showToast(OrderPaymentActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(OrderPaymentActivity.this,Constants.getResultMsg(msg));
    }

    @OnClick({R.id.tv_confirm_payment})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_confirm_payment:
                orderPayment();
                break;
            default:
                break;
        }

    }


    /**
     * 支付宝支付业务示例
     */
    public void payAlipayV2() {

        final Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(OrderPaymentActivity.this);
                Map<String, String> result = alipay.payV2(oredrPaymentBodyParam, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mBannerHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    public void orderPayment(){

        if(validateInternet())
        {
            showProgressDialog(OrderPaymentActivity.this);
            mPresenter.orderPayment(orderNo,paymentMethodType);
        }
    }
}
