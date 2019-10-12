package com.nbhysj.coupon.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.OrderRefundProgressDetailAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.common.Enum.OrderRefundStatusEnum;
import com.nbhysj.coupon.contract.OrderRefundContract;
import com.nbhysj.coupon.dialog.OrderVerificationCodeDialog;
import com.nbhysj.coupon.model.OrderRefundModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.OrderAllRefundInitResponse;
import com.nbhysj.coupon.model.response.OrderRefundDetailResponse;
import com.nbhysj.coupon.model.response.OrderRefundInitResponse;
import com.nbhysj.coupon.model.response.OrderRefundProgressBean;
import com.nbhysj.coupon.presenter.OrderRefundPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.nbhysj.coupon.util.Tools;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/04/28
 * description：退款详情
 */
public class RefundDetailsActivity extends BaseActivity<OrderRefundPresenter, OrderRefundModel> implements OrderRefundContract.View {

    @BindView(R.id.tv_toolbar_right)
    TextView mTvToolBarOprate;

    //支付标识
    @BindView(R.id.tv_payment_flag)
    TextView mTvPaymentFlag;

    //账户名
    @BindView(R.id.tv_account_name)
    TextView mTvAccountName;


    @BindView(R.id.tv_account_later_time)
    TextView mTvAccountLaterTime;

    //流水交易号
    @BindView(R.id.tv_transaction_no)
    TextView mTvTransactionNo;

    //退款金额
    @BindView(R.id.tv_refund_amount)
    TextView mTvRefundAmount;

    //客服电话
    @BindView(R.id.tv_customer_service_mobile)
    TextView mTvCustomerServiceMobile;

    //退款标识
    @BindView(R.id.img_refund_success_flag)
    ImageView mImgRefundSuccessFlag;

    //退款描述
    @BindView(R.id.tv_refund_des)
    TextView mTvRefundDes;

    @BindView(R.id.rv_refund_progress)
    RecyclerView mRvRefundProgress;

    //客服电话
    private String tel;

    //退款订单号
    private String orderRefundNo;

    //退款状态
    private int orderRefundStatus;

    private List<OrderRefundProgressBean> orderRefundProgressList;

    private OrderRefundProgressDetailAdapter orderRefundProgressDetailAdapter;

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_refund_details;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setBar(RefundDetailsActivity.this, getResources().getString(R.string.str_refund_detail), R.mipmap.icon_left_arrow_black);
        orderRefundNo = getIntent().getStringExtra("orderRefundNo");

        if(orderRefundProgressList == null){

            orderRefundProgressList = new ArrayList<>();
        } else {
            orderRefundProgressList.clear();
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RefundDetailsActivity.this);
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        mRvRefundProgress.setLayoutManager(linearLayoutManager);
        orderRefundProgressDetailAdapter = new OrderRefundProgressDetailAdapter(RefundDetailsActivity.this);
        orderRefundProgressDetailAdapter.setOrderRefundProgressList(orderRefundProgressList);
        mRvRefundProgress.setAdapter(orderRefundProgressDetailAdapter);
    }

    @Override
    public void initData() {
        mTvToolBarOprate.setText("完成");
        mTvToolBarOprate.setTextColor(getResources().getColor(R.color.color_text_blue8));

        getOrderRefundDetail();
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    public void getOrderPartialRefundDataInitResult(BackResult<OrderRefundInitResponse> res) {

    }

    @Override
    public void orderPartialRefundSubmitResult(BackResult res) {

    }

    @Override
    public void getOrderAllRefundDataInitResult(BackResult<OrderAllRefundInitResponse> res) {

    }

    @Override
    public void orderAllRefundSubmitResult(BackResult res) {

    }

    @Override
    public void getOrderRefundDetailResult(BackResult<OrderRefundDetailResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:

                OrderRefundDetailResponse orderRefundDetailResponse = res.getData();
                String accountName = orderRefundDetailResponse.getAccountName();
                String laterTime = orderRefundDetailResponse.getLaterTime();
                double refundFee = orderRefundDetailResponse.getRefundFee();
                String refundDesc = orderRefundDetailResponse.getRefundDesc();
                String paymentFlag = orderRefundDetailResponse.getPaymentFlag();
                String transActionNo = orderRefundDetailResponse.getTransactionNo();
                tel = orderRefundDetailResponse.getPhone();
                int needDays = orderRefundDetailResponse.getNeedDays();
                int refundStatus = orderRefundDetailResponse.getRefundStatus();
                String ctime = orderRefundDetailResponse.getCtime();
                String refundTime = orderRefundDetailResponse.getRefundTime();

                mTvPaymentFlag.setText( "(" + paymentFlag + ")");

                mTvAccountName.setText(accountName);
                mTvAccountLaterTime.setText(laterTime);
                mTvTransactionNo.setText(transActionNo);
                mTvRefundAmount.setText("¥" + Tools.getTwoDecimalPoint(refundFee));

                orderRefundStatus = OrderRefundStatusEnum.ORDER_REFUND_SUCCESSFUL.getKey();

                OrderRefundProgressBean orderRefundProgressBean = new OrderRefundProgressBean();
                orderRefundProgressBean.setRefundProgressDesc("提交退款申请");
                orderRefundProgressBean.setRefundProgressTime(ctime);

                OrderRefundProgressBean orderRefundProgressBean1 = new OrderRefundProgressBean();
                orderRefundProgressBean1.setRefundProgressDesc(refundDesc);
                orderRefundProgressBean1.setRefundProgressTime(refundTime);
                orderRefundProgressList.add(orderRefundProgressBean);
                orderRefundProgressList.add(orderRefundProgressBean1);

                orderRefundProgressDetailAdapter.setOrderRefundProgressList(orderRefundProgressList);
                orderRefundProgressDetailAdapter.notifyDataSetChanged();

                if (refundStatus == orderRefundStatus)
                {
                    mImgRefundSuccessFlag.setVisibility(View.VISIBLE);

                } else {

                    mImgRefundSuccessFlag.setVisibility(View.GONE);
                }

                if (!TextUtils.isEmpty(tel))
                {
                    mTvRefundDes.setText(Html.fromHtml("预计支付宝会在" + needDays + "日内处理完成，详细进度可使用交易号" + "<font color='#4895F2'>" + transActionNo + "</font>" + "拨打支付宝客服" + "<font color='#4895F2'>" + tel + "</font>" + "进行查询。"));
                }
                break;
            default:
                showToast(RefundDetailsActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(RefundDetailsActivity.this, Constants.getResultMsg(msg));
    }


    @OnClick({R.id.tv_customer_service_mobile, R.id.tv_toolbar_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_customer_service_mobile:

                if (!TextUtils.isEmpty(tel)) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(tel));
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    } else {
                        showToast(RefundDetailsActivity.this, "没有插电话卡，不能拨打电话！");
                    }
                }
                break;

            case R.id.tv_toolbar_right:

                RefundDetailsActivity.this.finish();
                break;
            default:
                break;
        }

    }

    public void getOrderRefundDetail() {

        if (validateInternet()) {

            showProgressDialog(RefundDetailsActivity.this);
            mPresenter.getOrderRefundDetail(orderRefundNo);
        }
    }
}

