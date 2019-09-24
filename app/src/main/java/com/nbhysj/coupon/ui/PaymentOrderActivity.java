package com.nbhysj.coupon.ui;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.OrderSubmitResponse;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.ToolbarHelper;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/05/09
 * description:支付订单
 */
public class PaymentOrderActivity extends BaseActivity {
    @BindView(R.id.toolbar_space)
    View mToolbarSpace;
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
    private int paymentMethodType = 1;

    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_payment_order;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setHeadBar(PaymentOrderActivity.this, getResources().getString(R.string.str_order_pay), R.mipmap.icon_left_arrow_black, "");
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
    }

    @Override
    public void initData() {


        OrderSubmitResponse orderSubmitResponse = (OrderSubmitResponse)getIntent().getSerializableExtra("orderSubmitResponse");
        if(orderSubmitResponse != null)
        {
            int price = orderSubmitResponse.getPrice();
            String orderNo = orderSubmitResponse.getOrderNo();
            String title = orderSubmitResponse.getTitle();
            long payExprireTime = orderSubmitResponse.getPayExprireTime();

            mTvPrice.setText(String.valueOf(price));
            mTvGoodName.setText(title);
            mTvPayExprireTime.setText("1");
        }
        mRlytWechatPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paymentMethodType = 1;
                mImgWechatPaymentCheck.setBackgroundResource(R.mipmap.icon_payment_method_check_true);
                mImgAlipayPaymentCheck.setBackgroundResource(R.mipmap.icon_payment_method_check_false);
            }
        });

        mRlytAlipayPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paymentMethodType = 2;
                mImgAlipayPaymentCheck.setBackgroundResource(R.mipmap.icon_payment_method_check_true);
                mImgWechatPaymentCheck.setBackgroundResource(R.mipmap.icon_payment_method_check_false);
            }
        });
    }

    @Override
    public void initPresenter() {

    }

    @OnClick({R.id.tv_confirm_payment})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_confirm_payment:

                showToast(PaymentOrderActivity.this, paymentMethodType + "");


                break;
            default:
                break;
        }

    }
}
