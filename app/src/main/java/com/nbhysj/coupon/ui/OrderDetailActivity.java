package com.nbhysj.coupon.ui;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.GoodsMealDetailAdapter;
import com.nbhysj.coupon.adapter.HotelQuestionAndAnswerAdapter;
import com.nbhysj.coupon.adapter.MchTypeAdapter;
import com.nbhysj.coupon.adapter.OrderDetailGuessYouLikeAdapter;
import com.nbhysj.coupon.adapter.OrderDetailProblemAdapter;
import com.nbhysj.coupon.adapter.OrderDetailVehicleUseAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.common.Enum.GoodsTypeEnum;
import com.nbhysj.coupon.common.Enum.MchTypeEnum;
import com.nbhysj.coupon.common.Enum.OrderDetailStatusEnum;
import com.nbhysj.coupon.contract.OrderDetailContract;
import com.nbhysj.coupon.dialog.OrderPriceDetailsDialog;
import com.nbhysj.coupon.dialog.OrderVerificationCodeDialog;
import com.nbhysj.coupon.fragment.OrderDetailScenicFragmentManager;
import com.nbhysj.coupon.fragment.ShoppingMallFragment;
import com.nbhysj.coupon.model.OrderDetailModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.OrderDetailGuessBean;
import com.nbhysj.coupon.model.response.OrderDetailResponse;
import com.nbhysj.coupon.model.response.OrderSubmitResponse;
import com.nbhysj.coupon.model.response.UserOrderListResponse;
import com.nbhysj.coupon.presenter.OrderDetailPresenter;
import com.nbhysj.coupon.widget.MyViewPager;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/03/08
 * description：订单详情
 */
public class OrderDetailActivity extends BaseActivity<OrderDetailPresenter, OrderDetailModel> implements OrderDetailContract.View {
    @BindView(R.id.toolbar_space)
    View mToolbarSpace;
    //订单详情头部背景
    @BindView(R.id.rlyt_header_bg_order_detail)
    RelativeLayout mRlytHeaderBgOrderDetail;
    //订单状态背景
    @BindView(R.id.llyt_order_status)
    LinearLayout mLlytOrderStatus;
    //订单状态背景三角
    @BindView(R.id.img_order_status_triangle)
    ImageView mImgOrderStatusTriangle;

    //订单支付
    @BindView(R.id.tv_go_order_payment)
    TextView mTvOrderPayment;

    //评价
    @BindView(R.id.tv_order_evaluate)
    TextView mTvOrderEvaluate;
    //再次预定
    @BindView(R.id.tv_rebook_goods)
    TextView mTvRebookGoods;
    //订单详情景点切换
    @BindView(R.id.viewpager_scenic_spot)
    MyViewPager mViewPagerScenicSpot;
    //圆点
    @BindView(R.id.llyt_dot_view)
    LinearLayout mLlytDotView;
    //可能遇到的问题
    @BindView(R.id.rv_common_problem)
    RecyclerView mRvCommonProblem;
    //问题
    @BindView(R.id.llyt_question_and_answer)
    LinearLayout mLlytQuestionAndAnswer;
    //酒店问答
    @BindView(R.id.rv_hotel_questions_and_answers)
    RecyclerView mRvHotelQuestionAndAnswers;
    //猜你喜欢
    @BindView(R.id.rv_guess_you_like)
    RecyclerView mRvGuessYouLike;
    //二维码
    @BindView(R.id.image_qr_code)
    ImageView mImgQRCode;
    //推荐评分标签
    @BindView(R.id.flowlayout_recommended_score)
    TagFlowLayout mTagRecommendedScore;
    //价格明细
    @BindView(R.id.tv_price_details)
    TextView mTvPriceDetails;
    //套餐
    @BindView(R.id.rv_package_details)
    RecyclerView mRvPackageDetails;
    //查看更多
    @BindView(R.id.tv_view_more)
    TextView mTvViewMore;

    //订单状态
    @BindView(R.id.tv_order_status)
    TextView mTvOrderStatus;
    //订单号
    @BindView(R.id.tv_order_no)
    TextView mTvOrderNo;

    @BindView(R.id.tv_explain)
    TextView mTvExplain;
    //总金额
    @BindView(R.id.tv_actual_payment_fee)
    TextView mTvActualPaymentFee;
    //二维码订单号
    @BindView(R.id.tv_qr_order_no)
    TextView mTvQROrderNo;
    //商品可用数
    @BindView(R.id.tv_goods_valid_num)
    TextView mTvGoodsValidNum;
    //联系人电话
    @BindView(R.id.tv_contacts)
    TextView mTvContacts;
    //联系人名字
    @BindView(R.id.tv_contacts_name)
    TextView mTvContactsName;
    //联系人电话
    @BindView(R.id.tv_contacts_phone)
    TextView mTvContactsPhone;
    //发票抬头
    @BindView(R.id.tv_invoice_payable)
    TextView mTvInvoicePayable;
    //纳税人识别号
    @BindView(R.id.tv_taxpayer_identification_number)
    TextView mTvTaxpayerIdentificationNumber;
    //可能遇到的问题
    @BindView(R.id.llyt_common_problem)
    LinearLayout mLlytCommonProblem;
    //联系商家
    @BindView(R.id.tv_contact_businessmen)
    TextView mTvContactBusinessmen;
    //用车
    @BindView(R.id.rv_vehicle_use)
    RecyclerView mRvVehicleUse;
    //商品过期时间
    @BindView(R.id.tv_goods_time)
    TextView mTvGoodsTime;
    //发票信息
    @BindView(R.id.llyt_order_invoice)
    LinearLayout mLlytOrderInvoice;
    //商品总数量
    @BindView(R.id.tv_goods_total_num)
    TextView mTvGoodsTotalNum;
    //商品总金额
    @BindView(R.id.tv_goods_total_price)
    TextView mTvGoodsTotalPrice;
    //推荐预定
    @BindView(R.id.llyt_order_recommended_book_goods)
    LinearLayout mLlytOrderRecommendBookGoods;

    //申请全部退款
    @BindView(R.id.tv_apply_for_all_order_refund)
    TextView mTvApplyForAllOrderRefund;

    //发票明细
    @BindView(R.id.tv_invoice_detail)
    TextView mTvInvoiceDetail;

    private String tel;

    /**
     * 放圆点的View的list
     **/
    private List<View> dotViewsList;

    String[] hotelRecommendScoreOptions = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    //订单编号
    private String orderNo;

    //商品列表
    private List<OrderDetailResponse.OrderGoodsEntity> orderGoodsList;

    //用车列表
    private List<OrderDetailResponse.OrderCarEntity> orderCarList;

    //可能会遇到的问题
    private List<OrderDetailResponse.AnswerEntity> problemList;

    //问答
    List<OrderDetailResponse.AnswerEntity> answerList;

    private List<OrderDetailGuessBean> guessYouLikeList;

    private OrderDetailProblemAdapter orderDetailProblemAdapter;

    //套餐|| 单个商品信息
    private GoodsMealDetailAdapter goodMealDetailAdapter;

    //订单详情用车
    OrderDetailVehicleUseAdapter orderDetailVehicleUseAdapter;

    //猜你喜欢
    OrderDetailGuessYouLikeAdapter gussYouLikeAdapter;

    OrderPriceDetailsDialog orderPriceDetailsDialog;

    OrderVerificationCodeDialog verificationCodeDialog;

    //酒店推荐评分
    int hotelRecommendScore = 0;

    //商户Id
    private int dataId;

    //商户类型
    private String goodsType;

    OrderDetailResponse orderDetailResponse;

    HotelQuestionAndAnswerAdapter hotelQuestionAndAnswerAdapter;

    //商品标题
    private String goodsTitle;

    //实付金额
    private String payFee;

    private final int PARTIAL_APPLY_For_REfUND_REQUEST_CODE = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_detail;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        //沉浸式
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

        orderNo = getIntent().getStringExtra("orderNo");
        if (dotViewsList == null) {
            dotViewsList = new ArrayList<View>();
        } else {
            dotViewsList.clear();
        }

        if (orderGoodsList == null) {

            orderGoodsList = new ArrayList<>();
        } else {
            orderGoodsList.clear();
        }

        if (orderCarList == null) {

            orderCarList = new ArrayList<>();
        } else {

            orderCarList.clear();
        }

        if (problemList == null) {

            problemList = new ArrayList<>();
        } else {

            problemList.clear();
        }

        if (guessYouLikeList == null) {

            guessYouLikeList = new ArrayList<>();
        } else {

            guessYouLikeList.clear();
        }

        if (answerList == null) {

            answerList = new ArrayList<>();
        } else {
            answerList.clear();
        }
        mTvOrderEvaluate.getBackground().setAlpha(20);
        mTvRebookGoods.getBackground().setAlpha(20);
        mTvOrderPayment.getBackground().setAlpha(20);

        mRvCommonProblem.setHasFixedSize(true);
        mRvCommonProblem.setLayoutManager(new GridLayoutManager(mContext, 3));
        orderDetailProblemAdapter = new OrderDetailProblemAdapter(OrderDetailActivity.this);
        orderDetailProblemAdapter.setOrderDetailProblemList(problemList);
        mRvCommonProblem.setAdapter(orderDetailProblemAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(OrderDetailActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvHotelQuestionAndAnswers.setLayoutManager(linearLayoutManager);
        hotelQuestionAndAnswerAdapter = new HotelQuestionAndAnswerAdapter(OrderDetailActivity.this);
        hotelQuestionAndAnswerAdapter.setQuestionContentList(answerList);
        mRvHotelQuestionAndAnswers.setAdapter(hotelQuestionAndAnswerAdapter);

        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(OrderDetailActivity.this,
                        2);
        mRvGuessYouLike.setLayoutManager(gridLayoutManager);
        gussYouLikeAdapter = new OrderDetailGuessYouLikeAdapter(OrderDetailActivity.this);
        gussYouLikeAdapter.setGuessYouLikeList(guessYouLikeList);
        mRvGuessYouLike.setAdapter(gussYouLikeAdapter);
        mRvGuessYouLike.addItemDecoration(new RecyclerItemDecoration(20, 2));

    }

    public class RecyclerItemDecoration extends RecyclerView.ItemDecoration {
        private int itemSpace;
        private int itemNum;

        /**
         * @param itemSpace item间隔
         * @param itemNum   每行item的个数
         */
        public RecyclerItemDecoration(int itemSpace, int itemNum) {
            this.itemSpace = itemSpace;
            this.itemNum = itemNum;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
                /*if (parent.getChildLayoutPosition(view)%itemNum == 0){  //parent.getChildLayoutPosition(view) 获取view的下标
                    outRect.left = 0;
                } else {
                    outRect.left = itemSpace;
                }*/
            outRect.left = itemSpace;
            outRect.right = itemSpace;
            //outRect.right = itemSpace;
        }
    }

    @Override
    public void initData() {

        getOrderDetail();

        List<String> optionList = Arrays.asList(hotelRecommendScoreOptions);
        TagAdapter tagAdapter = new TagAdapter<String>(optionList) {
            @Override
            public View getView(FlowLayout parent, int position, String option) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.layout_tag_recommended_score,
                        mTagRecommendedScore, false);
                TextView tv = view.findViewById(R.id.tv_flowlayout);
                tv.setText(option);

                return view;
            }
        };
        //tagAdapter.setSelectedList(4);
        mTagRecommendedScore.setMaxSelectCount(1);
        mTagRecommendedScore.setAdapter(tagAdapter);
        mTagRecommendedScore.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {

                Set<Integer> selectPosSet = mTagRecommendedScore.getSelectedList();
                Iterator it = selectPosSet.iterator();
                while (it.hasNext()) {
                    int index = (int) it.next();
                    hotelRecommendScore = Integer.parseInt(hotelRecommendScoreOptions[index]);

                    willingToRecommendScore();
                }
                return true;
            }
        });
        // tagAdapter.setSelectedList(0);

        //订单详情商品模块
        LinearLayoutManager linearLayout = new LinearLayoutManager(mContext);
        linearLayout.setOrientation(linearLayout.VERTICAL);
        mRvPackageDetails.setLayoutManager(linearLayout);
        goodMealDetailAdapter = new GoodsMealDetailAdapter(OrderDetailActivity.this, new GoodsMealDetailAdapter.PartialApplyForRefundListener() {
            @Override
            public void setPartialApplyForRefundListener(int orderGoodsId, String goodsType) {

                Intent intent = new Intent();
                intent.setClass(mContext, PartialApplyForRefundActivity.class);
                intent.putExtra("orderGoodsId", orderGoodsId);
                intent.putExtra("goodsType", goodsType);
                startActivityForResult(intent, PARTIAL_APPLY_For_REfUND_REQUEST_CODE);
            }
        });
        goodMealDetailAdapter.setOrderMealList(orderGoodsList);
        mRvPackageDetails.setAdapter(goodMealDetailAdapter);


        //订单详情用车模块
        LinearLayoutManager orderDetailVehicleLinearLayout = new LinearLayoutManager(mContext);
        orderDetailVehicleLinearLayout.setOrientation(LinearLayoutManager.VERTICAL);
        mRvVehicleUse.setLayoutManager(orderDetailVehicleLinearLayout);
        orderDetailVehicleUseAdapter = new OrderDetailVehicleUseAdapter(OrderDetailActivity.this, new OrderDetailVehicleUseAdapter.PartialApplyForRefundListener() {
            @Override
            public void setPartialApplyForRefundListener(int orderGoodsId, String goodsType) {

                Intent intent = new Intent();
                intent.setClass(mContext, PartialApplyForRefundActivity.class);
                intent.putExtra("orderGoodsId", orderGoodsId);
                intent.putExtra("goodsType", goodsType);
                startActivityForResult(intent, PARTIAL_APPLY_For_REfUND_REQUEST_CODE);
            }
        });
        orderDetailVehicleUseAdapter.setVehicleUseList(orderCarList);
        mRvVehicleUse.setAdapter(orderDetailVehicleUseAdapter);

    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    /**
     * ViewPager的监听器 当ViewPager中页面的状态发生改变时调用
     */
    class MyPageChangeListener implements ViewPager.OnPageChangeListener {
        private int a;

        @Override
        public void onPageScrollStateChanged(int state) {
           /* if (count > 1) {
                if (state == 1) {

                  //  timer.cancel();

                    // timerTask.cancel();
                    stat = true;
                }
                if (state == 0) {
                    if (stat) {
                        startViewPager();
                    }
                    stat = false;
                }
            }*/
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int pos) {
            //  currentItem = pos;
            a = pos % orderGoodsList.size();
            for (int i = 0; i < orderGoodsList.size(); i++) {
                if (i == a) {
                    ((View) dotViewsList.get(a))
                            .setBackgroundResource(R.mipmap.icon_point_select);
                } else {
                    ((View) dotViewsList.get(i))
                            .setBackgroundResource(R.mipmap.icon_point_unselect);
                }
            }
        }
    }

    @OnClick({R.id.image_qr_code, R.id.tv_price_details, R.id.iv_back, R.id.tv_view_more, R.id.tv_contact_businessmen,
            R.id.tv_rebook_goods, R.id.tv_apply_for_all_order_refund, R.id.tv_go_order_payment, R.id.tv_order_evaluate})
    public void onClick(View view) {
        Intent mIntent = new Intent();
        switch (view.getId()) {
            case R.id.image_qr_code:

                if (verificationCodeDialog == null) {

                    verificationCodeDialog = new OrderVerificationCodeDialog(OrderDetailActivity.this).builder();
                    verificationCodeDialog.setContent(orderNo);
                }

                verificationCodeDialog.show();

                break;
            case R.id.tv_price_details:

                if (orderPriceDetailsDialog == null) {
                    orderPriceDetailsDialog = new OrderPriceDetailsDialog();
                    orderPriceDetailsDialog.setOrderPriceDetailList(OrderDetailActivity.this, orderDetailResponse);
                }
                orderPriceDetailsDialog.show(getFragmentManager(), "");
                break;
            case R.id.iv_back:

                OrderDetailActivity.this.finish();

                break;
            case R.id.tv_view_more:

                toActivity(MoreQuestionsActivity.class);

                break;
            case R.id.tv_contact_businessmen:

                if (!TextUtils.isEmpty(tel)) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(tel));
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    } else {
                        Toast.makeText(OrderDetailActivity.this, "没有插电话卡，不能拨打电话！", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.tv_rebook_goods:
                String goodsTypeTicket = GoodsTypeEnum.GOODS_TICKET.getValue();
                String goodsTypeHotel = GoodsTypeEnum.MCH_HOTEL1.getValue();
                String goodsTypeHotelHomestay = GoodsTypeEnum.MCH_HOTEL2.getValue();
                String goodsTypeRecreation = GoodsTypeEnum.GOODS_RECREATION.getValue();
                String goodsTypeGroup = GoodsTypeEnum.GROUP_GOODS.getValue();

                if (goodsType.equals(goodsTypeTicket)) {  //景点
                    String mchScenicType = MchTypeEnum.MCH_SCENIC.getValue();
                    mIntent.setClass(mContext, ScenicSpotDetailActivity.class);
                    mIntent.putExtra("mchId", dataId);
                    mIntent.putExtra("mchType", mchScenicType);
                    mContext.startActivity(mIntent);

                } else if (goodsType.equals(goodsTypeRecreation)) {  //互动

                    mIntent.setClass(mContext, ScenicSpotDetailActivity.class);
                    String mchRecreationType = MchTypeEnum.MCH_RECREATION.getValue();
                    mIntent.putExtra("mchId", dataId);
                    mIntent.putExtra("mchType", mchRecreationType);
                    mContext.startActivity(mIntent);

                } else if (goodsType.equals(goodsTypeGroup)) { //组合

                    mIntent.setClass(mContext, GroupMchDetailsActivity.class);
                    mIntent.putExtra("packageId", dataId);
                    mContext.startActivity(mIntent);

                } else if (goodsType.equals(goodsTypeHotel)) {  //酒店

                    mIntent.setClass(mContext, HotelDetailsActivity.class);
                    mIntent.putExtra("mchId", dataId);
                    mContext.startActivity(mIntent);

                }else if (goodsType.equals(goodsTypeHotelHomestay)) {  //民宿

                    mIntent.setClass(mContext, HomestayDetailActivity.class);
                    mIntent.putExtra("mchId", dataId);
                    mContext.startActivity(mIntent);

                }

                break;
            case R.id.tv_apply_for_all_order_refund:

                mIntent.setClass(OrderDetailActivity.this, AllRefundApplyActivity.class);
                startActivity(mIntent);
                break;
            case R.id.tv_go_order_payment:

                Intent intent = new Intent();

                intent.setClass(OrderDetailActivity.this, OrderPaymentActivity.class);
                if(!TextUtils.isEmpty(payFee)) {
                    intent.putExtra("price", Double.valueOf(payFee));
                }
                intent.putExtra("title", goodsTitle);
                intent.putExtra("payExprireTime", 0);
                intent.putExtra("orderNo", orderNo);
                startActivity(intent);

                break;
            case R.id.tv_order_evaluate:
                if (!TextUtils.isEmpty(goodsType)) {
                    String mchGroupType = GoodsTypeEnum.GROUP_GOODS.getValue();
                    if (goodsType.equals(mchGroupType))
                    {
                        mIntent.setClass(OrderDetailActivity.this, OrderGroupEvaluateActivity.class);
                        mIntent.putExtra("orderNo", orderNo);
                        mContext.startActivity(mIntent);
                    } else {

                        if (orderGoodsList != null && orderGoodsList.size() > 0) {

                            OrderDetailResponse.OrderGoodsEntity goodsEntity = orderGoodsList.get(0);
                            if (goodsEntity != null) {
                                int orderGoodsId = goodsEntity.getOrderGoodsId();
                                mIntent.setClass(OrderDetailActivity.this, OrderEvaluateActivity.class);
                                mIntent.putExtra("orderGoodsId", orderGoodsId);
                                startActivityForResult(mIntent, 0);
                            }
                        }
                    }
                }

                break;
            default:
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void getOrderDetailResult(BackResult<OrderDetailResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    orderDetailResponse = res.getData();
                    if (orderDetailResponse != null) {
                        goodsTitle = orderDetailResponse.getTitle();
                        String moneySum = orderDetailResponse.getMoneySum();
                        int goodsSum = orderDetailResponse.getGoodSum();
                        tel = orderDetailResponse.getTel();
                        int canRefundAllStatus = orderDetailResponse.getCanRefundAllStatus();
                        int commentStatus = orderDetailResponse.getCommentStatus();
                        mTvGoodsTotalNum.setText(String.valueOf(goodsSum));

                        //   mTvGoodsTotalPrice.setText(moneySum);

                        if (canRefundAllStatus == 0) {
                            mTvApplyForAllOrderRefund.setVisibility(View.GONE);
                        }
                        if (canRefundAllStatus == 1) {
                            mTvApplyForAllOrderRefund.setVisibility(View.VISIBLE);
                        }


                        String orderStatus = orderDetailResponse.getOrderStatus();
                        OrderDetailResponse.OrderEntity orderEntity = orderDetailResponse.getOrder();
                        //商品列表
                        orderGoodsList = orderDetailResponse.getOrderGoods();
                        if (orderGoodsList != null) {

                            goodMealDetailAdapter.setOrderMealList(orderGoodsList);
                            goodMealDetailAdapter.notifyDataSetChanged();
                        }
                        FragmentPagerAdapter adapter = new OrderDetailScenicFragmentManager(getSupportFragmentManager(), orderGoodsList);
                        mViewPagerScenicSpot.setAdapter(adapter);
                        mViewPagerScenicSpot.setOffscreenPageLimit(2);
                        mViewPagerScenicSpot.setOnPageChangeListener(new MyPageChangeListener());
                        mLlytDotView.removeAllViews();
                        dotViewsList.clear();
                        for (int i = 0; i < orderGoodsList.size(); i++) {

                            ImageView dotView = new ImageView(OrderDetailActivity.this);
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                    FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
                            params.leftMargin = 4;
                            params.rightMargin = 4;
                            if (i == 0) {
                                dotView.setBackgroundResource(R.mipmap.icon_point_select);
                            } else {
                                dotView.setBackgroundResource(R.mipmap.icon_point_unselect);
                            }
                            mLlytDotView.addView(dotView, params);
                            dotViewsList.add(dotView);
                        }

                        String orderNo = orderEntity.getOrderNo();
                        dataId = orderEntity.getDataId();
                        String explain = orderEntity.getExplain();
                        String totalFee = orderEntity.getTotalFee();  //总金额
                        payFee = orderEntity.getPayFee();   //在线支付金额
                        String discountFee = orderEntity.getDiscountFee(); //折扣金额
                        String code = orderEntity.getCode();

                        goodsType = orderEntity.getGoodType();

                        mTvOrderStatus.setText(orderStatus);
                        mTvOrderNo.setText("订单号 " + orderNo);
                        mTvExplain.setText(explain);
                        mTvQROrderNo.setText(code);
                        mTvActualPaymentFee.setText(payFee);   //在线支付金额
                        mTvGoodsTotalPrice.setText(totalFee);  //总金额
                        String orderSuccessFulTradeValue = OrderDetailStatusEnum.ORDER_SUCCESSFUL_TRADE.getValue();
                        String orderPendingPaymentValue = OrderDetailStatusEnum.ORDER_PENDING_PAYMENT.getValue();
                        String orderPaymentTimeoutValue = OrderDetailStatusEnum.ORDER_PAYMENT_TIMEOUT.getValue();
                        String orderTradeCancelValue = OrderDetailStatusEnum.ORDER_PAYMENT_CANCEL.getValue();
                        if (!TextUtils.isEmpty(orderStatus)) {

                            if (orderStatus.equals(orderSuccessFulTradeValue)) {  //交易成功

                                mRlytHeaderBgOrderDetail.setBackgroundResource(R.drawable.bg_gradient_green_my_order_detail);
                                mToolbarSpace.setBackgroundResource(R.drawable.bg_gradient_green_my_order_detail);
                                mLlytOrderStatus.setBackgroundResource(R.drawable.bg_gradient_green_my_order_detail);
                                mImgOrderStatusTriangle.setImageResource(R.drawable.shape_inverted_triangle_green);

                                mTvRebookGoods.setVisibility(View.VISIBLE);
                                mTvOrderPayment.setVisibility(View.GONE);
                                mLlytOrderRecommendBookGoods.setVisibility(View.VISIBLE);

                                //0:不可评论 1:可评论
                                if (commentStatus == 0) {
                                    mTvOrderEvaluate.setVisibility(View.GONE);
                                }
                                if (commentStatus == 1) {
                                    mTvOrderEvaluate.setVisibility(View.VISIBLE);
                                }

                            } else if (orderStatus.equals(orderPendingPaymentValue)) {  //交易待支付

                                mRlytHeaderBgOrderDetail.setBackgroundResource(R.drawable.bg_gradient_blue_my_order_detail);
                                mToolbarSpace.setBackgroundResource(R.drawable.bg_gradient_blue_my_order_detail);
                                mLlytOrderStatus.setBackgroundResource(R.drawable.bg_gradient_blue_my_order_detail);
                                mImgOrderStatusTriangle.setImageResource(R.drawable.shape_inverted_triangle_blue);

                                mTvRebookGoods.setVisibility(View.GONE);
                                mTvOrderEvaluate.setVisibility(View.GONE);
                                mTvOrderPayment.setVisibility(View.VISIBLE);
                                mLlytOrderRecommendBookGoods.setVisibility(View.GONE);

                            } else if (orderStatus.equals(orderPaymentTimeoutValue)) {  //支付超时
                                mRlytHeaderBgOrderDetail.setBackgroundResource(R.drawable.bg_gradient_red_my_order_detail);
                                mToolbarSpace.setBackgroundResource(R.drawable.bg_gradient_red_my_order_detail);
                                mLlytOrderStatus.setBackgroundResource(R.drawable.bg_gradient_red_my_order_detail);
                                mImgOrderStatusTriangle.setImageResource(R.drawable.shape_inverted_triangle_red);

                                mTvRebookGoods.setVisibility(View.VISIBLE);
                                mTvOrderEvaluate.setVisibility(View.GONE);
                                mTvOrderPayment.setVisibility(View.GONE);
                                mLlytOrderRecommendBookGoods.setVisibility(View.GONE);

                            } else if (orderStatus.equals(orderTradeCancelValue)) {    //交易取消

                                mRlytHeaderBgOrderDetail.setBackgroundResource(R.drawable.bg_gradient_yellow_my_order_detail);
                                mToolbarSpace.setBackgroundResource(R.drawable.bg_gradient_yellow_my_order_detail);
                                mLlytOrderStatus.setBackgroundResource(R.drawable.bg_gradient_yellow_my_order_detail);
                                mImgOrderStatusTriangle.setImageResource(R.drawable.shape_inverted_triangle_yellow);

                                mTvRebookGoods.setVisibility(View.VISIBLE);
                                mTvOrderEvaluate.setVisibility(View.GONE);
                                mTvOrderPayment.setVisibility(View.GONE);
                                mLlytOrderRecommendBookGoods.setVisibility(View.GONE);
                            }
                        }

                        int goodSum = orderDetailResponse.getGoodSum();
                        String goodsTime = orderDetailResponse.getGoodsTime();
                        mTvGoodsValidNum.setText(goodSum + "件商品可用");
                        mTvGoodsTime.setText(goodsTime + "到期");

                        //订单用户
                        OrderDetailResponse.OrderUserEntity orderUserEntity = orderDetailResponse.getOrderUser();
                        String realName = orderUserEntity.getRealname();
                        String mobile = orderUserEntity.getMobile();

                        if (goodsType.equals(GoodsTypeEnum.getEnumByKey(0).getValue())) {
                            mTvContacts.setText("联系人");

                        } else if (goodsType.equals(GoodsTypeEnum.getEnumByKey(2).getValue())) {
                            mTvContacts.setText("入住人");
                        }

                        mTvContactsName.setText(realName);
                        mTvContactsPhone.setText(mobile);

                        //发票抬头信息
                        OrderDetailResponse.OrderInvoiceEntity orderInvoiceEntity = orderDetailResponse.getOrderInvoice();
                        if (orderInvoiceEntity != null) {
                            String title = orderInvoiceEntity.getTitle();
                            if (!TextUtils.isEmpty(title)) {

                                mLlytOrderInvoice.setVisibility(View.VISIBLE);
                                String invoiceNo = orderInvoiceEntity.getNo();
                                mTvInvoicePayable.setText(title);
                                mTvTaxpayerIdentificationNumber.setText(invoiceNo);
                            } else {

                                mLlytOrderInvoice.setVisibility(View.GONE);
                            }
                        } else {
                            mLlytOrderInvoice.setVisibility(View.GONE);
                        }

                        //可能遇到的问题
                        List<OrderDetailResponse.AnswerEntity> answerList = orderDetailResponse.getAnswer();
                        if (answerList != null && answerList.size() > 0) {

                            mLlytCommonProblem.setVisibility(View.VISIBLE);
                            orderDetailProblemAdapter.setOrderDetailProblemList(answerList);
                            orderDetailProblemAdapter.notifyDataSetChanged();

                        } else {
                            mLlytCommonProblem.setVisibility(View.GONE);
                        }

                        //问答
                        List<OrderDetailResponse.AnswerEntity> notAnswerList = orderDetailResponse.getNotAnswer();
                        if (notAnswerList != null && notAnswerList.size() > 0) {
                            mLlytQuestionAndAnswer.setVisibility(View.VISIBLE);
                            hotelQuestionAndAnswerAdapter.setQuestionContentList(notAnswerList);
                            hotelQuestionAndAnswerAdapter.notifyDataSetChanged();

                        } else {
                            mLlytQuestionAndAnswer.setVisibility(View.GONE);
                        }

                        //用车列表
                        orderCarList = orderDetailResponse.getOrderCar();
                        if (orderCarList != null && orderCarList.size() > 0) {
                            orderDetailVehicleUseAdapter.setVehicleUseList(orderCarList);
                            orderDetailVehicleUseAdapter.notifyDataSetChanged();
                        }

                        //猜你喜欢
                        guessYouLikeList = orderDetailResponse.getGuess();
                        if (guessYouLikeList != null && guessYouLikeList.size() > 0) {
                            gussYouLikeAdapter.setGuessYouLikeList(guessYouLikeList);
                            gussYouLikeAdapter.notifyDataSetChanged();

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(OrderDetailActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void willingToRecommendScoreResult(BackResult res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    System.out.print(res.getMsg());


                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(OrderDetailActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {
        dismissProgressDialog();
        showToast(OrderDetailActivity.this, Constants.getResultMsg(msg));
    }

    //获取订单详情
    public void getOrderDetail() {

        if (validateInternet()) {
            showProgressDialog(OrderDetailActivity.this);
            mPresenter.getOrderDetail(orderNo);
        }
    }

    //酒店推荐评分
    public void willingToRecommendScore() {
        if (validateInternet()) {

            showProgressDialog(OrderDetailActivity.this);
            mPresenter.willingToRecommendScore(orderNo, hotelRecommendScore);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PARTIAL_APPLY_For_REfUND_REQUEST_CODE:

                if (resultCode == RESULT_OK) {


                }
                break;
            default:
                break;
        }
    }
}
