package com.nbhysj.coupon.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.OrderRefundInitAdapter;
import com.nbhysj.coupon.adapter.RefundReasonSelectAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.OrderRefundContract;
import com.nbhysj.coupon.model.OrderDetailModel;
import com.nbhysj.coupon.model.OrderRefundModel;
import com.nbhysj.coupon.model.request.OrderAllRefundRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.OrderAllRefundInitResponse;
import com.nbhysj.coupon.model.response.OrderRefundDetailResponse;
import com.nbhysj.coupon.model.response.OrderRefundInitResponse;
import com.nbhysj.coupon.presenter.OrderRefundPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.nbhysj.coupon.util.Tools;
import com.nbhysj.coupon.view.MyRecycleView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/09/27
 * description：全部退款申请
 */
public class AllRefundApplyActivity extends BaseActivity<OrderRefundPresenter, OrderRefundModel> implements OrderRefundContract.View {

    //商品全部退款
    @BindView(R.id.rv_order_all_refund)
    MyRecycleView mRvOrderAllRefund;
    //退款商品总价
    @BindView(R.id.tv_refund_goods_total_price)
    TextView mTvRefundGoodsTotalPrice;
    //折扣价格
    @BindView(R.id.tv_discount_price)
    TextView mTvDiscountPrice;
    //实退金额
    @BindView(R.id.tv_actual_refund_amount)
    TextView mTvActualRefundAmount;
    //退款原因选择
    @BindView(R.id.rv_refund_reason_select)
    MyRecycleView mRvRefundReasonSelect;
    //退款理由
    @BindView(R.id.edt_refund_reason)
    EditText mEdtRefundReason;

    private List<String> mRefundReasonSelectList;
    private List<OrderRefundInitResponse> orderRefundIniteList;

    //订单号
    private String orderNo;
    //折扣价格
    private double discountPrice;
    private OrderRefundInitAdapter orderRefundInitAdapter;
    //退款理由
    private String refundReason;

    //退款理由
    private String orderRefundReason;
    private List<OrderAllRefundRequest.RefundGoodsEntity> refundGoodsListRequest;

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_all_refund_apply;
    }



    @Override
    public void initView(Bundle savedInstanceState) {

        orderNo = getIntent().getStringExtra("orderNo");
        ToolbarHelper.setBar(AllRefundApplyActivity.this, getResources().getString(R.string.str_apply_for_refund), R.mipmap.icon_left_arrow_black);
        if (orderRefundIniteList == null) {

            orderRefundIniteList = new ArrayList<>();
        } else {
            orderRefundIniteList.clear();
        }

        if (refundGoodsListRequest == null) {

            refundGoodsListRequest = new ArrayList<>();
        } else {
            refundGoodsListRequest.clear();
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AllRefundApplyActivity.this);
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        mRvOrderAllRefund.setLayoutManager(linearLayoutManager);
        orderRefundInitAdapter = new OrderRefundInitAdapter(AllRefundApplyActivity.this);
        orderRefundInitAdapter.setOrderAllRefundList(orderRefundIniteList);
        mRvOrderAllRefund.setAdapter(orderRefundInitAdapter);
    }

    @Override
    public void initData() {

        getOrderForAllRefund();

        if (mRefundReasonSelectList == null) {

            mRefundReasonSelectList = new ArrayList<>();
        } else {

            mRefundReasonSelectList.clear();
        }

        mRefundReasonSelectList.add("个人计划有变");
        mRefundReasonSelectList.add("商户无法接待");
        mRefundReasonSelectList.add("其他网站购买");
        mRefundReasonSelectList.add("产品定错(时间,数量等)");
        mRefundReasonSelectList.add("价格不够优惠");


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AllRefundApplyActivity.this);
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        mRvRefundReasonSelect.setLayoutManager(linearLayoutManager);

        RefundReasonSelectAdapter refundReasonSelectAdapter = new RefundReasonSelectAdapter(new RefundReasonSelectAdapter.RefundReasonSelectListener() {
            @Override
            public void setRefundReasonSelectListener(String reason) {

                refundReason = reason;

            }
        });
        refundReasonSelectAdapter.setRefundReasonList(mRefundReasonSelectList);
        mRvRefundReasonSelect.setAdapter(refundReasonSelectAdapter);
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
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    OrderAllRefundInitResponse orderAllRefundInitResponse = res.getData();
                    orderRefundIniteList = orderAllRefundInitResponse.getGoods();
                    String orderNo = orderAllRefundInitResponse.getOrderNo();
                    discountPrice = orderAllRefundInitResponse.getDiscountPrice();
                    double refundPrice = orderAllRefundInitResponse.getRefundPrice();
                    double price = orderAllRefundInitResponse.getPrice();

                    mTvDiscountPrice.setText(Tools.getTwoDecimalPoint(discountPrice));   //减免金额
                    mTvActualRefundAmount.setText(Tools.getTwoDecimalPoint(refundPrice)); //实退金额
                    mTvRefundGoodsTotalPrice.setText(Tools.getTwoDecimalPoint(price));   //退款总金额

                    if (orderRefundIniteList != null) {
                        orderRefundInitAdapter.setOrderAllRefundList(orderRefundIniteList);
                        orderRefundInitAdapter.notifyDataSetChanged();
                    }

                    refundGoodsListRequest.clear();
                    for (int i = 0; i < orderRefundIniteList.size(); i++) {
                        OrderRefundInitResponse orderRefundInitResponse = orderRefundIniteList.get(i);
                        int orderGoodsId = orderRefundInitResponse.getOrderGoodsId();
                        double deductPrice = Double.parseDouble(orderRefundInitResponse.getDeductPrice());
                        double unitPrice = Double.parseDouble(orderRefundInitResponse.getPrice());
                        String goodsType = orderRefundInitResponse.getGoodsType();
                        int validNum = orderRefundInitResponse.getValidNum();
                        int sumNum = orderRefundInitResponse.getSumNum();
                        int refundPercentage = orderRefundInitResponse.getRefundPercentage();

                        OrderAllRefundRequest.RefundGoodsEntity refundGoodsEntity = new OrderAllRefundRequest().new RefundGoodsEntity();
                        refundGoodsEntity.setOrderGoodsId(orderGoodsId); //商品订单id
                        refundGoodsEntity.setGoodsType(goodsType);   //商品类型
                        double goodsRefundPrice = (unitPrice * sumNum - deductPrice) * refundPercentage / 100;
                        String goodsRefundPriceStr = Tools.getTwoDecimalPoint(goodsRefundPrice);
                        refundGoodsEntity.setGoodsRefundPrice(goodsRefundPriceStr);  //商品退款金额
                        refundGoodsEntity.setNum(validNum);   //商品数量
                        refundGoodsListRequest.add(refundGoodsEntity);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(AllRefundApplyActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void orderAllRefundSubmitResult(BackResult res) {

        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:

                EventBus.getDefault().post("refundOprate");
                setResult(RESULT_OK);
                finish();

                break;
            default:
                showToast(AllRefundApplyActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }

    }

    @Override
    public void getOrderRefundDetailResult(BackResult<OrderRefundDetailResponse> res) {

    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(mContext, Constants.getResultMsg(msg));
    }

    //获取全部退款金额初始化页面
    public void getOrderForAllRefund() {

        if (validateInternet()) {

            showProgressDialog(AllRefundApplyActivity.this);
            mPresenter.getOrderAllRefundDataInit(orderNo);
        }
    }

    //替补退款提交
    public void orderAllRefundSubmit() {

        if (validateInternet()) {



            String mRefundReasonInput = mEdtRefundReason.getText().toString().trim();

            //退款理由
            if (TextUtils.isEmpty(refundReason) && TextUtils.isEmpty(mRefundReasonInput)) {
                showToast(AllRefundApplyActivity.this, "请选择或填写退款原因");
                return;

            } else if (!TextUtils.isEmpty(refundReason) && TextUtils.isEmpty(mRefundReasonInput)) {
                orderRefundReason = refundReason;
            } else if (TextUtils.isEmpty(refundReason) && !TextUtils.isEmpty(mRefundReasonInput)) {

                orderRefundReason = mRefundReasonInput;

            } else if (!TextUtils.isEmpty(refundReason) && !TextUtils.isEmpty(mRefundReasonInput)) {
                orderRefundReason = refundReason + " " + mRefundReasonInput;
            }

            showProgressDialog(AllRefundApplyActivity.this);
            OrderAllRefundRequest orderAllRefundRequest = new OrderAllRefundRequest();
            String discountPriceStr = Tools.getTwoDecimalPoint(discountPrice);
            orderAllRefundRequest.setDiscountPrice(discountPriceStr);
            orderAllRefundRequest.setRefundGoods(refundGoodsListRequest);
            orderAllRefundRequest.setNote(orderRefundReason);
            mPresenter.orderAllRefundSubmit(orderAllRefundRequest);
        }
    }

    @OnClick({R.id.tv_refund_confirm})
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv_refund_confirm:

                orderAllRefundSubmit();
                break;
            default:
                break;

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
