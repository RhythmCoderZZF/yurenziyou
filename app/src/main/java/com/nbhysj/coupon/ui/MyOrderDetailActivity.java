package com.nbhysj.coupon.ui;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.GoodMealDetailAdapter;
import com.nbhysj.coupon.adapter.HotelQuestionAndAnswerAdapter;
import com.nbhysj.coupon.adapter.MchTypeAdapter;
import com.nbhysj.coupon.adapter.OrderDetailProblemAdapter;
import com.nbhysj.coupon.adapter.OrderDetailVehicleUseAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.common.Enum.GoodsTypeEnum;
import com.nbhysj.coupon.contract.OrderDetailContract;
import com.nbhysj.coupon.dialog.OrderPriceDetailsDialog;
import com.nbhysj.coupon.dialog.OrderVerificationCodeDialog;
import com.nbhysj.coupon.fragment.OrderDetailScenicFragmentManager;
import com.nbhysj.coupon.model.OrderDetailModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.OrderDetailGuessBean;
import com.nbhysj.coupon.model.response.OrderDetailResponse;
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
public class MyOrderDetailActivity extends BaseActivity<OrderDetailPresenter, OrderDetailModel> implements OrderDetailContract.View {
    @BindView(R.id.toolbar_space)
    View mToolbarSpace;
    //评价
    @BindView(R.id.tv_evaluate)
    TextView mTvEvaluate;
    //再次预定
    @BindView(R.id.tv_rebook)
    TextView mTvRebook;
    //订单详情景点切换
    @BindView(R.id.viewpager_scenic_spot)
    MyViewPager mViewPagerScenicSpot;
    //圆点
    @BindView(R.id.llyt_dot_view)
    LinearLayout mLlytDotView;
    //可能遇到的问题
    @BindView(R.id.rv_common_problem)
    RecyclerView mRvCommonProblem;
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
    @BindView(R.id.tv_total_fee)
    TextView mTvTotalFee;
    //二维码订单号
    @BindView(R.id.tv_qr_order_no)
    TextView mTvQROrderNo;
    //商品可用数
    @BindView(R.id.tv_good_num)
    TextView mTvGoodNum;
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

    /**
     * 放圆点的View的list
     **/
    private List<View> dotViewsList;

    // private List<OrderDetailScenicSpotReponse> mOrderDetailScenicSpotList;
    String[] hotelRecommendScoreOptions = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    //订单编号
    private String orderNo;

    //商品列表
    private List<OrderDetailResponse.OrderGoodsEntity> orderGoodsList;

    //用车列表
    private List<OrderDetailResponse.OrderCarEntity> orderCarList;

    //可能会遇到的问题
    private List<OrderDetailResponse.AnswerEntity> problemList;

    private List<OrderDetailGuessBean> guessYouLikeList;

    private OrderDetailProblemAdapter orderDetailProblemAdapter;

    //套餐|| 单个商品信息
    private GoodMealDetailAdapter goodMealDetailAdapter;

    //订单详情用车
    OrderDetailVehicleUseAdapter orderDetailVehicleUseAdapter;

    //猜你喜欢
    MchTypeAdapter gussYouLikeAdapter;

    OrderPriceDetailsDialog orderPriceDetailsDialog;

    OrderVerificationCodeDialog verificationCodeDialog;

    //酒店推荐评分
    int hotelRecommendScore = 0;
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

        if(orderCarList == null){

            orderCarList = new ArrayList<>();
        } else {

            orderCarList.clear();
        }

        if (problemList == null) {

            problemList = new ArrayList<>();
        } else {

            problemList.clear();
        }

        if(guessYouLikeList == null){

            guessYouLikeList = new ArrayList<>();
        } else {

            guessYouLikeList.clear();
        }
        mTvEvaluate.getBackground().setAlpha(20);
        mTvRebook.getBackground().setAlpha(20);


        mRvCommonProblem.setHasFixedSize(true);
        mRvCommonProblem.setLayoutManager(new GridLayoutManager(mContext, 3));


        orderDetailProblemAdapter = new OrderDetailProblemAdapter(MyOrderDetailActivity.this);
        orderDetailProblemAdapter.setOrderDetailProblemList(problemList);
        mRvCommonProblem.setAdapter(orderDetailProblemAdapter);

        List<String> questionList = new ArrayList<>();
        questionList.add("隔音如何，晚上安静吗，睡觉会不会被影...");
        questionList.add("基础设施如何?");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyOrderDetailActivity.this);
        //linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvHotelQuestionAndAnswers.setLayoutManager(linearLayoutManager);
        HotelQuestionAndAnswerAdapter hotelQuestionAndAnswerAdapter = new HotelQuestionAndAnswerAdapter(MyOrderDetailActivity.this);
        hotelQuestionAndAnswerAdapter.setQuestionContentList(questionList);
        mRvHotelQuestionAndAnswers.setAdapter(hotelQuestionAndAnswerAdapter);

        LinearLayoutManager linearLayout = new LinearLayoutManager(MyOrderDetailActivity.this);
        //linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        /*mRvGuessYouLike.setLayoutManager(linearLayout);
        gussYouLikeAdapter = new MchTypeAdapter(MyOrderDetailActivity.this);
        gussYouLikeAdapter.setMchTypeDetailList(guessYouLikeList);
        mRvGuessYouLike.setAdapter(gussYouLikeAdapter);*/

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
        goodMealDetailAdapter = new GoodMealDetailAdapter(MyOrderDetailActivity.this);
        goodMealDetailAdapter.setOrderMealList(orderGoodsList);
        mRvPackageDetails.setAdapter(goodMealDetailAdapter);


        //订单详情用车模块
        LinearLayoutManager orderDetailVehicleLinearLayout = new LinearLayoutManager(mContext);
        orderDetailVehicleLinearLayout.setOrientation(LinearLayoutManager.VERTICAL);
        mRvVehicleUse.setLayoutManager(orderDetailVehicleLinearLayout);
        orderDetailVehicleUseAdapter = new OrderDetailVehicleUseAdapter();
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

    @OnClick({R.id.image_qr_code, R.id.tv_price_details, R.id.iv_back, R.id.tv_view_more, R.id.tv_contact_businessmen})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_qr_code:

                if(verificationCodeDialog == null) {

                    verificationCodeDialog = new OrderVerificationCodeDialog(MyOrderDetailActivity.this).builder();
                    verificationCodeDialog.setContent("http://www.baidu.com");
                }

                verificationCodeDialog.show();

                break;
            case R.id.tv_price_details:

                OrderPriceDetailsDialog orderPriceDetailsDialog = new OrderPriceDetailsDialog();
                orderPriceDetailsDialog.show(getFragmentManager(), "");
                break;
            case R.id.iv_back:

                MyOrderDetailActivity.this.finish();

                break;
            case R.id.tv_view_more:

                toActivity(MoreQuestionsActivity.class);

                break;
            case R.id.tv_contact_businessmen:

                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:10086"));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(MyOrderDetailActivity.this, "没有插电话卡，不能拨打电话！", Toast.LENGTH_SHORT).show();
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

                    OrderDetailResponse orderDetailResponse = res.getData();
                    String orderStatus = orderDetailResponse.getOrderStatus();
                    OrderDetailResponse.OrderEntity orderEntity = orderDetailResponse.getOrder();

                    FragmentPagerAdapter adapter = new OrderDetailScenicFragmentManager(getSupportFragmentManager(), orderGoodsList);
                    mViewPagerScenicSpot.setAdapter(adapter);
                    mViewPagerScenicSpot.setOffscreenPageLimit(2);
                    mViewPagerScenicSpot.setOnPageChangeListener(new MyPageChangeListener());
                    mLlytDotView.removeAllViews();
                    dotViewsList.clear();
                    for (int i = 0; i < orderGoodsList.size(); i++) {

                        ImageView dotView = new ImageView(MyOrderDetailActivity.this);
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
                    String explain = orderEntity.getExplain();
                    String totalFee = orderEntity.getTotalFee();
                    String code = orderEntity.getCode();
                    int goodSum = orderDetailResponse.getGoodSum();
                    String goodType = orderEntity.getGoodType();

                    mTvOrderStatus.setText(orderStatus);
                    mTvOrderNo.setText("订单号 " + orderNo);
                    mTvExplain.setText(explain);
                    mTvTotalFee.setText(totalFee);  //在线总支付

                    mTvQROrderNo.setText(code);
                    mTvGoodNum.setText(goodSum + "件商品可用");

                    //订单用户
                    OrderDetailResponse.OrderUserEntity orderUserEntity = orderDetailResponse.getOrderUser();
                    String realName = orderUserEntity.getRealname();
                    String mobile = orderUserEntity.getMobile();

                    if (goodType.equals(GoodsTypeEnum.getEnumByKey(0).getValue())) {
                        mTvContacts.setText("联系人");

                    } else if (goodType.equals(GoodsTypeEnum.getEnumByKey(2).getValue())) {
                        mTvContacts.setText("入住人");
                    }

                    mTvContactsName.setText(realName);
                    mTvContactsPhone.setText(mobile);

                    //发票抬头信息
                    OrderDetailResponse.OrderInvoiceEntity orderInvoiceEntity = orderDetailResponse.getOrderInvoice();
                    if (orderInvoiceEntity != null) {
                        String title = orderInvoiceEntity.getTitle();
                        String invoiceNo = orderInvoiceEntity.getNo();
                        mTvInvoicePayable.setText(title);
                        mTvTaxpayerIdentificationNumber.setText(invoiceNo);
                    }

                    List<OrderDetailResponse.AnswerEntity> answerList = orderDetailResponse.getAnswer();
                    OrderDetailResponse.AnswerEntity answerEntity = new OrderDetailResponse().new AnswerEntity();
                    answerEntity.setContent("酒店押金退还");

                    OrderDetailResponse.AnswerEntity answerEntity1 = new OrderDetailResponse().new AnswerEntity();
                    answerEntity1.setContent("酒店可以开专用");

                    OrderDetailResponse.AnswerEntity answerEntity2 = new OrderDetailResponse().new AnswerEntity();
                    answerEntity2.setContent("未入住退款吗");

                    OrderDetailResponse.AnswerEntity answerEntity3 = new OrderDetailResponse().new AnswerEntity();
                    answerEntity3.setContent("酒店押金退还");

                    OrderDetailResponse.AnswerEntity answerEntity4 = new OrderDetailResponse().new AnswerEntity();
                    answerEntity4.setContent("酒店可以开专用...");

                    OrderDetailResponse.AnswerEntity answerEntity5 = new OrderDetailResponse().new AnswerEntity();
                    answerEntity5.setContent("未入住退款吗");

                    answerList.add(answerEntity);
                    answerList.add(answerEntity1);
                    answerList.add(answerEntity2);
                    answerList.add(answerEntity3);
                    answerList.add(answerEntity4);
                    answerList.add(answerEntity5);

                    orderDetailProblemAdapter.setOrderDetailProblemList(answerList);
                    orderDetailProblemAdapter.notifyDataSetChanged();

                    //商品列表
                    orderGoodsList = orderDetailResponse.getOrderGoods();
                    goodMealDetailAdapter.setOrderMealList(orderGoodsList);
                    goodMealDetailAdapter.notifyDataSetChanged();

                    //用车列表
                    orderCarList = orderDetailResponse.getOrderCar();
                    orderDetailVehicleUseAdapter.setVehicleUseList(orderCarList);
                    orderDetailVehicleUseAdapter.notifyDataSetChanged();

                    //猜你喜欢
                 /*   guessYouLikeList = orderDetailResponse.getGuess();
                    gussYouLikeAdapter.setMchTypeDetailList(guessYouLikeList);
                    gussYouLikeAdapter.notifyDataSetChanged();*/

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(MyOrderDetailActivity.this, Constants.getResultMsg(res.getMsg()));
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
                showToast(MyOrderDetailActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {
        dismissProgressDialog();
        showToast(MyOrderDetailActivity.this, Constants.getResultMsg(msg));
    }

    //获取订单详情
    public void getOrderDetail() {

        if (validateInternet()) {
            showProgressDialog(MyOrderDetailActivity.this);
            mPresenter.getOrderDetail(orderNo);
        }
    }

    //酒店推荐评分
    public void willingToRecommendScore()
    {
        if (validateInternet()) {

            showProgressDialog(MyOrderDetailActivity.this);
            mPresenter.willingToRecommendScore(orderNo,hotelRecommendScore);
        }
    }
}
