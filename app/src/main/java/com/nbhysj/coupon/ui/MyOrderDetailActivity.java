package com.nbhysj.coupon.ui;

import android.graphics.Color;
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
import com.nbhysj.coupon.adapter.HotelAdapter;
import com.nbhysj.coupon.adapter.HotelQuestionAndAnswerAdapter;
import com.nbhysj.coupon.adapter.OrderDetailProblemAdapter;
import com.nbhysj.coupon.adapter.SetMealDetailAdapter;
import com.nbhysj.coupon.dialog.OrderPriceDetailsDialog;
import com.nbhysj.coupon.dialog.PurchaseSuccessDialog;
import com.nbhysj.coupon.fragment.MyOrderFragmentManager;
import com.nbhysj.coupon.fragment.OrderDetailScenicFragmentManager;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.HotTagsTopicBean;
import com.nbhysj.coupon.model.response.OrderDetailScenicSpotReponse;
import com.nbhysj.coupon.view.SlideShowView;
import com.nbhysj.coupon.widget.ContentViewPager;
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
public class MyOrderDetailActivity extends BaseActivity {
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
    ContentViewPager mViewPagerScenicSpot;
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
    /**
     * 放圆点的View的list
     **/
    private List<View> dotViewsList;

    private List<OrderDetailScenicSpotReponse> mOrderDetailScenicSpotList;
    String[] options = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

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

        mTvEvaluate.getBackground().setAlpha(6);
        mTvRebook.getBackground().setAlpha(6);


        mRvCommonProblem.setHasFixedSize(true);
        mRvCommonProblem.setLayoutManager(new GridLayoutManager(mContext, 3));

        List<String> problemList = new ArrayList<>();
        problemList.add("酒店押金退还");
        problemList.add("酒店可以开专用");
        problemList.add("未入住退款吗");
        problemList.add("酒店押金退还");
        problemList.add("酒店可以开专用");
        problemList.add("未入住退款吗");

        OrderDetailProblemAdapter orderDetailProblemAdapter = new OrderDetailProblemAdapter(MyOrderDetailActivity.this);
        orderDetailProblemAdapter.setOrderDetailProblemList(problemList);
        mRvCommonProblem.setAdapter(orderDetailProblemAdapter);

        List<String> questionList = new ArrayList<>();
        questionList.add("隔音如何，晚上安静吗，睡觉会不会被影...");
        questionList.add("基础设施如何?");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        //linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvHotelQuestionAndAnswers.setLayoutManager(linearLayoutManager);
        HotelQuestionAndAnswerAdapter hotelQuestionAndAnswerAdapter = new HotelQuestionAndAnswerAdapter(MyOrderDetailActivity.this);
        hotelQuestionAndAnswerAdapter.setQuestionContentList(questionList);
        mRvHotelQuestionAndAnswers.setAdapter(hotelQuestionAndAnswerAdapter);

        LinearLayoutManager linearLayout = new LinearLayoutManager(mContext);
        //linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvGuessYouLike.setLayoutManager(linearLayout);
        HotelAdapter hotelAdapter = new HotelAdapter(MyOrderDetailActivity.this);
        hotelAdapter.setHotelContentList(questionList);
        mRvGuessYouLike.setAdapter(hotelAdapter);
    }

    @Override
    public void initData() {

        if (dotViewsList == null) {
            dotViewsList = new ArrayList<View>();
        } else {
            dotViewsList.clear();
        }

        if (mOrderDetailScenicSpotList == null) {

            mOrderDetailScenicSpotList = new ArrayList<>();
        } else {
            mOrderDetailScenicSpotList.clear();
        }

        OrderDetailScenicSpotReponse orderDetailScenicSpotReponse = new OrderDetailScenicSpotReponse();
        orderDetailScenicSpotReponse.setScenicSpotAddress("宁波市 鄞州区 桑田路936号");
        orderDetailScenicSpotReponse.setScenicSpotName("宁波海洋世界");
        orderDetailScenicSpotReponse.setTicketType("家庭票A(两大一小)");
        orderDetailScenicSpotReponse.setUseTime("3月20日");

        OrderDetailScenicSpotReponse orderDetailScenicSpotReponse1 = new OrderDetailScenicSpotReponse();
        orderDetailScenicSpotReponse1.setScenicSpotAddress("宁波东钱湖野生动物园");
        orderDetailScenicSpotReponse1.setScenicSpotName("宁波雅戈尔动物园");
        orderDetailScenicSpotReponse1.setTicketType("家庭票A(两大一小)");
        orderDetailScenicSpotReponse1.setUseTime("4月10日");

        OrderDetailScenicSpotReponse orderDetailScenicSpotReponse2 = new OrderDetailScenicSpotReponse();
        orderDetailScenicSpotReponse2.setScenicSpotAddress("浙江省宁波市镇海新城");
        orderDetailScenicSpotReponse2.setScenicSpotName("宁波植物园");
        orderDetailScenicSpotReponse2.setTicketType("家庭票A(两大一小)");
        orderDetailScenicSpotReponse2.setUseTime("3月10日");

        mOrderDetailScenicSpotList.add(orderDetailScenicSpotReponse);
        mOrderDetailScenicSpotList.add(orderDetailScenicSpotReponse1);
        mOrderDetailScenicSpotList.add(orderDetailScenicSpotReponse2);

        FragmentPagerAdapter adapter = new OrderDetailScenicFragmentManager(getSupportFragmentManager(), mOrderDetailScenicSpotList);
        mViewPagerScenicSpot.setAdapter(adapter);
        mViewPagerScenicSpot.setOffscreenPageLimit(2);
        mViewPagerScenicSpot.setOnPageChangeListener(new MyPageChangeListener());
        mLlytDotView.removeAllViews();

        for (int i = 0; i < 3; i++) {

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

        List<String> optionList = Arrays.asList(options);
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
                String content = "";
                Set<Integer> selectPosSet = mTagRecommendedScore.getSelectedList();
                Iterator it = selectPosSet.iterator();
                while (it.hasNext()) {
                    int index = (int) it.next();
                    content = options[index];
                }
                Toast.makeText(MyOrderDetailActivity.this, content + "", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        tagAdapter.setSelectedList(4);

        LinearLayoutManager linearLayout = new LinearLayoutManager(mContext);
        linearLayout.setOrientation(linearLayout.VERTICAL);
        mRvPackageDetails.setLayoutManager(linearLayout);
        SetMealDetailAdapter setMealDetailAdapter = new SetMealDetailAdapter(MyOrderDetailActivity.this);
        mRvPackageDetails.setAdapter(setMealDetailAdapter);

    }

    @Override
    public void initPresenter() {

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
            a = pos % 3;
            for (int i = 0; i < 3; i++) {
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

    @OnClick({R.id.image_qr_code, R.id.tv_price_details, R.id.iv_back, R.id.tv_view_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_qr_code:

                PurchaseSuccessDialog purchaseSuccessDialog = new PurchaseSuccessDialog(MyOrderDetailActivity.this).builder();
                purchaseSuccessDialog.show();

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
            default:
                break;
        }
    }
}
