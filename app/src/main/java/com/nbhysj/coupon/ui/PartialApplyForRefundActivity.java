package com.nbhysj.coupon.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.RefundReasonSelectAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.common.Enum.GoodsTypeEnum;
import com.nbhysj.coupon.contract.OrderRefundContract;
import com.nbhysj.coupon.model.OrderRefundModel;
import com.nbhysj.coupon.model.request.OrderPartialRefundRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.OrderAllRefundInitResponse;
import com.nbhysj.coupon.model.response.OrderRefundDetailResponse;
import com.nbhysj.coupon.model.response.OrderRefundInitResponse;
import com.nbhysj.coupon.presenter.OrderRefundPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.nbhysj.coupon.util.Tools;
import com.nbhysj.coupon.view.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/09/26
 * description：申请部分退款
 */
public class PartialApplyForRefundActivity extends BaseActivity<OrderRefundPresenter, OrderRefundModel> implements OrderRefundContract.View {
    //商品票(非用车)
    @BindView(R.id.rlyt_goods_ticket)
    RelativeLayout mRlytGoodsTicket;
    //商品图片
    @BindView(R.id.image_goods_ticket)
    RoundedImageView mImgGoodsTicket;
    //商品标题
    @BindView(R.id.tv_goods_title)
    TextView mTvGoodsTitle;
    //已使用数量
    @BindView(R.id.tv_has_been_used_num)
    TextView mTvHasBeenUsedNum;
    //申请可退数量
    @BindView(R.id.tv_quantity_of_refund_num)
    TextView mTvGoodsRefundNum;
    //退款数量选择
    @BindView(R.id.tv_can_refund_num_select)
    TextView mTvCanRefundNumSelect;
    //折扣说明
    @BindView(R.id.tv_deduct_note)
    TextView mTvDeductNote;
    //商品单价
    @BindView(R.id.tv_goods_item_price)
    TextView mTvGoodsItemPrice;

    //退款商品总价
    @BindView(R.id.tv_refund_goods_total_price)
    TextView mTvRefundGoodsTotalPrice;
    //折扣价格
    @BindView(R.id.tv_discount_price)
    TextView mTvDiscountPrice;
    //实退金额
    @BindView(R.id.tv_actual_refund_amount)
    TextView mTvActualRefundAmount;

    //车辆使用
    @BindView(R.id.llyt_order_refund_vehicle_use_item)
    LinearLayout mLlytOrderRefundVehicleUseItem;

    @BindView(R.id.rv_refund_reason_select)
    RecyclerView mRvRefundReasonSelect;
    //退款理由
    @BindView(R.id.edt_refund_reason)
    EditText mEdtRefundReason;

    //商品数量
    @BindView(R.id.tv_goods_num)
    TextView mTvGoodsNum;

     /***********  用车   **************/
    //用车金额单价
    @BindView(R.id.tv_vehicle_expenses)
    TextView mTvVehicleExpenses;

    //出发点
    @BindView(R.id.tv_starting_point)
    TextView mTvStartPoint;

    //目的地
    @BindView(R.id.tv_destination_name)
    TextView mTvDesTinationName;

    //用车时间
    @BindView(R.id.tv_vehicle_use_time)
    TextView mTvVehicleUseTime;

    //退款原因选择
    private List<String> mRefundReasonSelectList;
    private int refundNum = 1;
    //订单商品id
    private int orderGoodsId;
    //商品类型
    private String goodsType;

    //订单可退数量
    private int validNum;

    //商品总数量
    private int goodsTotalNum;

    //折扣价格
    private String deductPrice;

    //商品金额
    private double goodsRefundPrice;

    //退款金额
    private double deductPriceDouble;

    //订单号
    private String orderNo;

    private String unitPrice;
    //退款理由
    private String refundReason;

    //商品总价
    double goodsTotalPrice;

    //退款折扣百分比
    private int refundPercentage;

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_apply_for_refund;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setBar(PartialApplyForRefundActivity.this, getResources().getString(R.string.str_apply_for_refund), R.mipmap.icon_left_arrow_black, "");

        orderGoodsId = getIntent().getIntExtra("orderGoodsId", 0);  //子订单id
        goodsType = getIntent().getStringExtra("goodsType");  //商品类型

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

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PartialApplyForRefundActivity.this);
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
    public void initData() {

        getPartialApplyForRefund();
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @OnClick({R.id.img_reduce_refund_num, R.id.img_add_refund_num, R.id.tv_apply_for_refund})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_reduce_refund_num:

                if (refundNum > 1) {
                    refundNum--;
                    mTvCanRefundNumSelect.setText(String.valueOf(refundNum));
                    getActualRefundAmount();
                }
                break;
            case R.id.img_add_refund_num:

                if (refundNum == validNum) {

                    showToast(PartialApplyForRefundActivity.this, "最大不能超过可退数量");

                } else {

                    refundNum++;
                    mTvCanRefundNumSelect.setText(String.valueOf(refundNum));
                    getActualRefundAmount();
                }
                break;
            case R.id.tv_apply_for_refund:

                partialApplyForRefundSubmit();

                break;
            default:
                break;
        }
    }

    @Override
    public void getOrderPartialRefundDataInitResult(BackResult<OrderRefundInitResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    OrderRefundInitResponse orderRefundInitResponse = res.getData();

                    orderNo = orderRefundInitResponse.getOrderNo();
                    goodsType = orderRefundInitResponse.getGoodsType();
                    int usedNum = orderRefundInitResponse.getUsedNum(); //已使用数量
                    validNum = orderRefundInitResponse.getValidNum(); //可退款数量
                    goodsTotalNum = orderRefundInitResponse.getSumNum();  //商品总数量
                    unitPrice = orderRefundInitResponse.getPrice();  //商品单价
                    refundPercentage = orderRefundInitResponse.getRefundPercentage();
                    refundNum = goodsTotalNum - usedNum;
                    //用车类型
                    String goodsCarType = GoodsTypeEnum.GOODS_CAR.getValue();
                    //普通商品类型
                    String goodsTicketType = GoodsTypeEnum.GOODS_TICKET.getValue();

                    String deductNote = orderRefundInitResponse.getDeductNote();  //折扣信息
                    deductPrice = orderRefundInitResponse.getDeductPrice();  //减免优惠
                    if (!goodsType.equals(goodsCarType) || goodsType.equals(goodsTicketType)) {
                        mRlytGoodsTicket.setVisibility(View.VISIBLE);
                        mLlytOrderRefundVehicleUseItem.setVisibility(View.GONE);

                        String title = orderRefundInitResponse.getTitle();
                        // String totalPrice = orderRefundInitResponse.getTotalPrice();  //退款商品总价

                        String photo = orderRefundInitResponse.getPhoto();

                        mTvGoodsTitle.setText(title);
                        if (!TextUtils.isEmpty(unitPrice)) {
                            int sumNum = validNum + usedNum;
                            goodsTotalPrice = Double.parseDouble(unitPrice) * sumNum;
                            mTvRefundGoodsTotalPrice.setText("¥ " + goodsTotalPrice);
                            mTvGoodsNum.setText("x" + sumNum);
                        }
                        mTvGoodsRefundNum.setText(String.valueOf(validNum));
                        mTvGoodsItemPrice.setText(unitPrice);
                        getActualRefundAmount();

                        mTvHasBeenUsedNum.setText(String.valueOf(usedNum));
                        mTvCanRefundNumSelect.setText(String.valueOf(validNum));
                        GlideUtil.loadImage(PartialApplyForRefundActivity.this, photo, mImgGoodsTicket);

                    } else if (goodsType.equals(goodsCarType))  //用车类型
                    {
                        String startAddressName = orderRefundInitResponse.getStartName();
                        String endAddressName = orderRefundInitResponse.getEndName();
                        String goodsTime = orderRefundInitResponse.getGoodsTime();
                        mTvVehicleExpenses.setText(unitPrice);
                        mTvStartPoint.setText(startAddressName);
                        mTvDesTinationName.setText(endAddressName);
                        mTvVehicleUseTime.setText(goodsTime);
                        mLlytOrderRefundVehicleUseItem.setVisibility(View.VISIBLE);
                        mRlytGoodsTicket.setVisibility(View.GONE);
                    }

                    //折扣信息
                    if (!TextUtils.isEmpty(deductNote)) {
                        mTvDeductNote.setText(deductNote);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Constants.USER_NOT_LOGIN_CODE:
                toActivity(PhoneQuickLoginActivity.class);
                break;
            default:
                showToast(PartialApplyForRefundActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void orderPartialRefundSubmitResult(BackResult res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:

                showToast(PartialApplyForRefundActivity.this,"退款成功");
                setResult(RESULT_OK);
                finish();

                break;
            case Constants.USER_NOT_LOGIN_CODE:
                toActivity(PhoneQuickLoginActivity.class);
                break;
            default:
                showToast(PartialApplyForRefundActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }

    }

    @Override
    public void getOrderAllRefundDataInitResult(BackResult<OrderAllRefundInitResponse> res) {

    }

    @Override
    public void orderAllRefundSubmitResult(BackResult res) {

    }

    @Override
    public void getOrderRefundDetailResult(BackResult<OrderRefundDetailResponse> res) {

    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(PartialApplyForRefundActivity.this, Constants.getResultMsg(msg));
    }

    //部分退款初始化页面
    public void getPartialApplyForRefund() {

        if (validateInternet()) {

            showProgressDialog(PartialApplyForRefundActivity.this);
            mPresenter.getOrderPartialRefundDataInit(orderGoodsId, goodsType);
        }
    }

    //部分退款
    public void partialApplyForRefundSubmit() {

        if (validateInternet()) {


            mDialog.setTitle("正在退款...");

            //退款理由
            String mRefundReasonInput = mEdtRefundReason.getText().toString().trim();
            if (TextUtils.isEmpty(refundReason) && TextUtils.isEmpty(mRefundReasonInput)) {
                showToast(PartialApplyForRefundActivity.this, "请选择或填写退款原因");

                return;
            }
            showProgressDialog(PartialApplyForRefundActivity.this);
            OrderPartialRefundRequest partialApplyForRefundRequest = new OrderPartialRefundRequest();
            //订单商品id
            partialApplyForRefundRequest.setOrderGoodsId(orderGoodsId);
            //扣款信息
            partialApplyForRefundRequest.setDiscountPrice(deductPrice);
            deductPriceDouble = Double.parseDouble(deductPrice);

            //商品退款总金额
            partialApplyForRefundRequest.setGoodsRefundPrice(String.valueOf(goodsRefundPrice));
            //商品类型
            partialApplyForRefundRequest.setGoodsType(goodsType);
            //退款数量
            partialApplyForRefundRequest.setNum(refundNum);
            //订单号
            partialApplyForRefundRequest.setOrderNo(orderNo);

            if (!TextUtils.isEmpty(refundReason) && TextUtils.isEmpty(mRefundReasonInput)) {
                partialApplyForRefundRequest.setNote(refundReason);

            } else if (TextUtils.isEmpty(refundReason) && !TextUtils.isEmpty(mRefundReasonInput)) {
                partialApplyForRefundRequest.setNote(mRefundReasonInput);

            } else if (!TextUtils.isEmpty(refundReason) && !TextUtils.isEmpty(mRefundReasonInput)) {
                partialApplyForRefundRequest.setNote(refundReason + " " + mRefundReasonInput);
            }
            mPresenter.orderPartialRefundSubmit(partialApplyForRefundRequest);
        }
    }

    //获取实际退款金额
    public void getActualRefundAmount()
    {
        goodsRefundPrice = Double.parseDouble(unitPrice) * refundNum - deductPriceDouble;
        mTvActualRefundAmount.setText("¥ " + goodsRefundPrice);
    }
}
