package com.nbhysj.coupon.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.DeliciousFoodRecommendAdapter;
import com.nbhysj.coupon.adapter.HotelAdapter;
import com.nbhysj.coupon.adapter.IndependentTravelAdapter;
import com.nbhysj.coupon.adapter.PopularScenicSpotsAdapter;
import com.nbhysj.coupon.adapter.ScenicSpotMchDestinationAdapter;
import com.nbhysj.coupon.adapter.ShopMallIndexSmallBannerAdapter;
import com.nbhysj.coupon.adapter.ShoppingMallGuessYouLikeAdapter;
import com.nbhysj.coupon.adapter.ShoppingMallMenuAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.ShopMallHomePageContract;
import com.nbhysj.coupon.dialog.VehicleServiceAgreementTipsDialog;
import com.nbhysj.coupon.model.ShopMallHomePageModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CarH5UrlResponse;
import com.nbhysj.coupon.model.response.DeliciousFoodResponse;
import com.nbhysj.coupon.model.response.GoodsBean;
import com.nbhysj.coupon.model.response.GroupGoodsBean;
import com.nbhysj.coupon.model.response.LimitedSaleBean;
import com.nbhysj.coupon.model.response.MchCitiesBean;
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.model.response.ShopMallHomePageResponse;
import com.nbhysj.coupon.model.response.ShoppingMallMenuBean;
import com.nbhysj.coupon.model.response.WeatherResponse;
import com.nbhysj.coupon.presenter.ShopMallHomePagePresenter;
import com.nbhysj.coupon.ui.DestinationSearchActivity;
import com.nbhysj.coupon.ui.FineFoodBangDanListActivity;
import com.nbhysj.coupon.ui.GroupMchListActivity;
import com.nbhysj.coupon.ui.HotelDetailsActivity;
import com.nbhysj.coupon.ui.HotelOrderActivity;
import com.nbhysj.coupon.ui.MessageActivity;
import com.nbhysj.coupon.ui.OrderSubmitActivity;
import com.nbhysj.coupon.ui.ScenicSpotBangDanListActivity;
import com.nbhysj.coupon.ui.ShoppingMallFineFoodActivity;
import com.nbhysj.coupon.ui.ShoppingMallHomestayActivity;
import com.nbhysj.coupon.ui.ShoppingMallHotelActivity;
import com.nbhysj.coupon.ui.ShoppingMallInteractionActivity;
import com.nbhysj.coupon.ui.ShoppingMallScenicSpotActivity;
import com.nbhysj.coupon.ui.ShoppingMallScreeningActivity;
import com.nbhysj.coupon.ui.ShoppingMallSpecialSaleActivity;
import com.nbhysj.coupon.ui.StrategyActivity;
import com.nbhysj.coupon.ui.TravelAssistantDetailsActivity;
import com.nbhysj.coupon.ui.WebActivity;
import com.nbhysj.coupon.util.DateUtil;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.RadiusGradientSpanUtil;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.nbhysj.coupon.util.Tools;
import com.nbhysj.coupon.view.BannerView;
import com.nbhysj.coupon.view.GlideImageView;
import com.nbhysj.coupon.view.RoundedImageView;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/04/17
 * description：商城首页
 */
public class ShoppingMallFragment extends BaseFragment<ShopMallHomePagePresenter, ShopMallHomePageModel> implements ShopMallHomePageContract.View {

    @BindView(R.id.toolbar_space)
    View mToolbarSpace;
    //商城模块分类
    @BindView(R.id.rv_shopping_mall_menu_classify)
    RecyclerView mRvShoppingMallMenuClassify;
    //限时特卖图片1
    @BindView(R.id.image_limited_sale_one)
    RoundedImageView mImageFlashSaleOne;
    //限时特卖1折扣
    @BindView(R.id.tv_limited_sale_one_discount)
    TextView mTvLimitedSaleOneDiscount;
    //限时特卖1标题
    @BindView(R.id.tv_limited_sale_one_title)
    TextView mTvLimitedSaleOneTitle;
    //限时特卖1价格
    @BindView(R.id.tv_limited_sale_one_price)
    TextView mTvLimitedSaleOnePrice;
    //限时特卖图片2
    @BindView(R.id.image_limited_sale_two)
    RoundedImageView mImageFlashSaleTwo;
    //限时特卖2折扣
    @BindView(R.id.tv_limited_sale_two_discount)
    TextView mTvLimitedSaleTwoDiscount;
    //限时特卖2标题
    @BindView(R.id.tv_limited_sale_two_title)
    TextView mTvLimitedSaleTwoTitle;
    //限时特卖2价格
    @BindView(R.id.tv_limited_sale_two_price)
    TextView mTvLimitedSaleTwoPrice;

    //限时特卖图片3
    @BindView(R.id.image_limited_sale_three)
    RoundedImageView mImageFlashSaleThree;
    //限时特卖2折扣
    @BindView(R.id.tv_limited_sale_three_discount)
    TextView mTvLimitedSaleThreeDiscount;
    //限时特卖2标题
    @BindView(R.id.tv_limited_sale_three_title)
    TextView mTvLimitedSaleThreeTitle;
    //限时特卖2价格
    @BindView(R.id.tv_limited_sale_three_price)
    TextView mTvLimitedSaleThreePrice;

    //热门景点
    @BindView(R.id.rv_popular_scenic_spots)
    RecyclerView mRvPopularScenicSpots;
    //美食推荐
    @BindView(R.id.rv_delicious_food_recommendation)
    RecyclerView mRvDeliciousFoodRecommendation;
    //酒店民宿
    @BindView(R.id.rv_hotel_reputation)
    RecyclerView mRvHotelReputation;
    //景点分类
    @BindView(R.id.rv_scenic_spot_classification)
    RecyclerView mRvScenicSpotClassification;
    //旅行主题
    @BindView(R.id.img_travel_theme_one)
    RoundedImageView mImgTravelThemeOne;
    @BindView(R.id.tv_travel_theme_one_title)
    TextView mTvTravelThemeOneTitle;
    @BindView(R.id.tv_travel_theme_one_des)
    TextView mTvTravelThemeOneDes;
    //旅行主题2
    @BindView(R.id.img_travel_theme_two)
    RoundedImageView mImgTravelThemeTwo;
    @BindView(R.id.tv_travel_theme_two_title)
    TextView mTvTravelThemeTwoTitle;
    @BindView(R.id.tv_travel_theme_two_des)
    TextView mTvTravelThemeTwoDes;
    //旅行主题3
    @BindView(R.id.img_travel_theme_three)
    RoundedImageView mImgTravelThemeThree;
    @BindView(R.id.tv_travel_theme_three_title)
    TextView mTvTravelThemeThreeTitle;
    @BindView(R.id.tv_travel_theme_three_des)
    TextView mTvTravelThemeThreeDes;
    //旅行主题4
    @BindView(R.id.img_travel_theme_four)
    RoundedImageView mImgTravelThemeFour;
    @BindView(R.id.tv_travel_theme_four_title)
    TextView mTvTravelThemeFourTitle;
    @BindView(R.id.tv_travel_theme_four_des)
    TextView mTvTravelThemeFourDes;
    //旅行主题5
    @BindView(R.id.img_travel_theme_five)
    RoundedImageView mImgTravelThemeFive;
    @BindView(R.id.tv_travel_theme_five_title)
    TextView mTvTravelThemeFiveTitle;
    @BindView(R.id.tv_travel_theme_five_des)
    TextView mTvTravelThemeFiveDes;
    //商城自由行
    @BindView(R.id.rv_independent_travel)
    RecyclerView mRvIndependentTravel;
    //商城猜你喜欢
    @BindView(R.id.rv_guess_you_like)
    RecyclerView mRvGuessYouLike;
    //商城横条
    @BindView(R.id.rv_shopping_mall_small_banner)
    RecyclerView mRvShoppingMallSmallBanner;
    @BindView(R.id.banner)
    BannerView mBannerview;
    //商城刷新
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    //限时特卖1人民币符号
    @BindView(R.id.tv_flash_sale_one_rmb_symbol)
    TextView mTvLimitedSaleOneRMBSymbol;
    @BindView(R.id.tv_flash_sale_one_rise)
    TextView mTvLimitedSaleOneRise;

    //限时特卖2人民币符号
    @BindView(R.id.tv_flash_sale_two_rmb_symbol)
    TextView mTvLimitedSaleTwoRMBSymbol;
    @BindView(R.id.tv_flash_sale_two_rise)
    TextView mTvLimitedSaleTwoRise;

    //限时特卖3人民币符号
    @BindView(R.id.tv_flash_sale_three_rmb_symbol)
    TextView mTvLimitedSaleThreeRMBSymbol;
    @BindView(R.id.tv_flash_sale_three_rise)
    TextView mTvLimitedSaleThreeRise;
    //温度
    @BindView(R.id.tv_temperature)
    TextView mTvTemperature;

    //天气状况
    @BindView(R.id.tv_weather)
    TextView mTvWeather;

    //天气
    @BindView(R.id.img_weather)
    ImageView mImgWeather;

    //限时特卖小时
    @BindView(R.id.tv_limited_sale_hour)
    TextView mTvLimitedSaleHour;

    //限时特卖分
    @BindView(R.id.tv_limited_sale_minute)
    TextView mTvLimitedSaleMinute;

    //限时特卖秒
    @BindView(R.id.tv_limited_sale_second)
    TextView mTvLimitedSaleSecond;

    @BindView(R.id.llyt_limited_sale)
    LinearLayout mLlytLimitedSale;

    private ShoppingMallMenuAdapter shoppingMallMenuAdapter;
    //热门景点
    private PopularScenicSpotsAdapter popularScenicSpotsAdapter;
    //美食
    private DeliciousFoodRecommendAdapter deliciousFoodRecommendAdapter;
    //酒店民宿
    private HotelAdapter mHotelAdapter;
    //目的地分类
    private ScenicSpotMchDestinationAdapter scenicSpotClassificationAdapter;

    private IndependentTravelAdapter independentTravelAdapter;
    //猜你喜欢
    private ShoppingMallGuessYouLikeAdapter mallGuessYouLikeAdapter;

    //大banner
    private List<String> bannerList;
    //横条小banner
    private List<String> shopMallIndexSmallList;
    //热门景区
    private List<MchTypeBean> popularScenicSpotsList;
    //美食列表
    private List<DeliciousFoodResponse> deliciousFoodList;
    //目的地
    private List<MchCitiesBean> mchCities;
    //酒店
    List<MchTypeBean> hotelList;
    //自由行
    List<GroupGoodsBean> groupGoodsList;
    //猜你喜欢
    List<ShopMallHomePageResponse.GuessEntity> guessEntityList;

    private ShopMallIndexSmallBannerAdapter shopMallIndexSmallBannerAdapter;

    List<ShoppingMallMenuBean> shoppingMallMenuList;

    private boolean timerIsInit=false;//判断数据是否初始化

    private VehicleServiceAgreementTipsDialog vehicleServiceAgreementTipsDialog;
    private long endTime;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            endTime--;
            if(endTime>0){
            String formatLongToTimeStr = formatLongToTimeStr(endTime);
            String[] split = formatLongToTimeStr.split("：");
            for (int i = 0; i < split.length; i++) {
                if(i==0){
                    mTvLimitedSaleHour.setText
                            (split[0]);
                }
                if(i==1){
                    mTvLimitedSaleMinute.setText(split[1]);
                }
                if(i==2){
                    mTvLimitedSaleSecond.setText(split[2]);
                }
            }
                handler.postDelayed(this, 1000);
            } else {

                mTvLimitedSaleHour.setText("00");

                mTvLimitedSaleMinute.setText("00");

                mTvLimitedSaleSecond.setText("00");
            }
        }
    };


    @Override
    public int getLayoutId() {
        return R.layout.fragment_shopping_mall;
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView(View v) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);
            getActivity().getWindow().getDecorView()
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

        bannerList = new ArrayList<>();
        getWeather(Constants.CITY_CODE);
        getShopMallHomePageData();

        //  mBannerview = (BannerView) v.findViewById(R.id.banner);
        mBannerview.startLoop(true);
        mBannerview.setViewList(getActivity(), bannerList);

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        handler.removeCallbacks(runnable);
                        getShopMallHomePageData();

                    }
                }, 100);
            }
        });
    }
    public String formatLongToTimeStr(Long l) {
        int hour = 0;
        int minute = 0;
        int second = 0;
        String hourStr;
        String minuteStr;
        String secondStr;
        second = l.intValue() ;
        if (second > 60) {
            minute = second / 60;   //取整
            second = second % 60;   //取余
        }

        if (minute > 60) {
            hour = minute / 60;
            minute = minute % 60;
        }
        if(hour < 10){

            hourStr =  "0" + hour;
        } else {
            hourStr = String.valueOf(hour);
        }

        if(minute < 10){

            minuteStr =  "0" + minute;
        } else {
            minuteStr = String.valueOf(minute);
        }

        if(second < 10){

            secondStr =  "0" + second;
        } else {
            secondStr = String.valueOf(second);
        }

        String strtime = hourStr+"："+minuteStr+"："+secondStr;
        return strtime;

    }

    @Override
    public void initData() {

        if (shoppingMallMenuList == null) {

            shoppingMallMenuList = new ArrayList<>();
        } else {

            shoppingMallMenuList.clear();
        }

        if (shopMallIndexSmallList == null) {

            shopMallIndexSmallList = new ArrayList<>();
        } else {

            shopMallIndexSmallList.clear();
        }

        if (popularScenicSpotsList == null) {

            popularScenicSpotsList = new ArrayList<>();
        } else {

            popularScenicSpotsList.clear();
        }

        if (deliciousFoodList == null) {

            deliciousFoodList = new ArrayList<>();
        } else {

            deliciousFoodList.clear();
        }

        if (hotelList == null) {

            hotelList = new ArrayList<>();
        } else {

            hotelList.clear();
        }

        if (mchCities == null) {

            mchCities = new ArrayList<>();
        } else {
            mchCities.clear();
        }

        if (guessEntityList == null) {

            guessEntityList = new ArrayList<>();
        } else {

            guessEntityList.clear();
        }

        if (groupGoodsList == null) {

            groupGoodsList = new ArrayList<>();
        } else {
            groupGoodsList.clear();
        }

        //获取商城首页分类
        List<ShoppingMallMenuBean> shoppingMallMenuList = getShopMallMenuList();

        GridLayoutManager mManagerLayout = new GridLayoutManager(getActivity(), 4);
        mRvShoppingMallMenuClassify.setLayoutManager(mManagerLayout);

        //解决数据加载不完的问题
        mRvShoppingMallMenuClassify.setNestedScrollingEnabled(false);
        mRvShoppingMallMenuClassify.setHasFixedSize(true);
        //解决数据加载完成后, 没有停留在顶部的问题
        mRvShoppingMallMenuClassify.setFocusable(false);
        shoppingMallMenuAdapter = new ShoppingMallMenuAdapter(getActivity(), new ShoppingMallMenuAdapter.ShoppingMallMenuListener() {
            @Override
            public void setShoppingMallMenuListener(int position) {

                if (position == 0) {

                    toActivity(ShoppingMallScenicSpotActivity.class); //景点

                } else if (position == 1) {
                    toActivity(ShoppingMallFineFoodActivity.class);  //美食
                } else if (position == 2) {
                    toActivity(ShoppingMallHotelActivity.class); //酒店
                } else if (position == 3) {
                    toActivity(StrategyActivity.class);  //攻略
                } else if (position == 4) {
                    toActivity(ShoppingMallHomestayActivity.class); //民宿
                } else if (position == 5) {
                    toActivity(ShoppingMallInteractionActivity.class);  //互动
                } else if (position == 6) {
                    toActivity(GroupMchListActivity.class);          //组合
                } else if (position == 7) {                              //用车
                   // toActivity(OrderSubmitActivity.class);
                    // toActivity(IntroductionOfLandlordActivity.class);
                    getCarH5Url();
                   // showVehicleUseAddDialog();
                }
            }
        });
        shoppingMallMenuAdapter.setShoppingMallMenuList(shoppingMallMenuList);
        mRvShoppingMallMenuClassify.setAdapter(shoppingMallMenuAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(linearLayoutManager.HORIZONTAL);
        mRvShoppingMallSmallBanner.setLayoutManager(linearLayoutManager);
        shopMallIndexSmallBannerAdapter = new ShopMallIndexSmallBannerAdapter(getActivity());
        shopMallIndexSmallBannerAdapter.setShopMallIndexSmallBannerList(shopMallIndexSmallList);
        mRvShoppingMallSmallBanner.setAdapter(shopMallIndexSmallBannerAdapter);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity());
        linearLayoutManager1.setOrientation(linearLayoutManager1.HORIZONTAL);
        mRvPopularScenicSpots.setLayoutManager(linearLayoutManager1);
        popularScenicSpotsAdapter = new PopularScenicSpotsAdapter(getActivity());
        popularScenicSpotsAdapter.setPopularScenicSpotsList(popularScenicSpotsList);
        mRvPopularScenicSpots.setAdapter(popularScenicSpotsAdapter);

        LinearLayoutManager deliciousFoodLinearLayout = new LinearLayoutManager(getActivity());
        deliciousFoodLinearLayout.setOrientation(linearLayoutManager.HORIZONTAL);
        mRvDeliciousFoodRecommendation.setLayoutManager(deliciousFoodLinearLayout);
        deliciousFoodRecommendAdapter = new DeliciousFoodRecommendAdapter(getActivity());
        deliciousFoodRecommendAdapter.setDeliciousFoodRecommendList(deliciousFoodList);
        mRvDeliciousFoodRecommendation.setAdapter(deliciousFoodRecommendAdapter);

        LinearLayoutManager hotelReputationLinearLayout = new LinearLayoutManager(getActivity());
        hotelReputationLinearLayout.setOrientation(hotelReputationLinearLayout.VERTICAL);
        mRvHotelReputation.setLayoutManager(hotelReputationLinearLayout);
        mHotelAdapter = new HotelAdapter(getActivity());
        mHotelAdapter.setHotelList(hotelList);
        mRvHotelReputation.setAdapter(mHotelAdapter);

        LinearLayoutManager scenicSpotClassificationLinearLayout = new LinearLayoutManager(getActivity());
        scenicSpotClassificationLinearLayout.setOrientation(linearLayoutManager.HORIZONTAL);
        mRvScenicSpotClassification.setLayoutManager(scenicSpotClassificationLinearLayout);
        scenicSpotClassificationAdapter = new ScenicSpotMchDestinationAdapter(getActivity());
        scenicSpotClassificationAdapter.setScenicSpotMchCitiesDestinationList(mchCities);
        mRvScenicSpotClassification.setAdapter(scenicSpotClassificationAdapter);

        LinearLayoutManager independentTravelLinearLayout = new LinearLayoutManager(getActivity());
        independentTravelLinearLayout.setOrientation(linearLayoutManager.HORIZONTAL);
        mRvIndependentTravel.setLayoutManager(independentTravelLinearLayout);
        independentTravelAdapter = new IndependentTravelAdapter(getActivity());
        independentTravelAdapter.setIndependentTravelList(groupGoodsList);
        mRvIndependentTravel.setAdapter(independentTravelAdapter);

        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(getActivity(),
                        2);
        mRvGuessYouLike.setLayoutManager(gridLayoutManager);
        mallGuessYouLikeAdapter = new ShoppingMallGuessYouLikeAdapter(getActivity());
        mallGuessYouLikeAdapter.setGuessYouLikeList(guessEntityList);
        mRvGuessYouLike.setAdapter(mallGuessYouLikeAdapter);
        mRvGuessYouLike.addItemDecoration(new RecyclerItemDecoration(11, 2));
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
    public void getShopMallHomePageDataResult(BackResult<ShopMallHomePageResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    mSmartRefreshLayout.finishRefresh();
                    ShopMallHomePageResponse shopMallHomePageResponse = res.getData();
                    bannerList = shopMallHomePageResponse.getBigBanners();

                    //大banner
                    mBannerview.setViewList(getActivity(), bannerList);

                    //景点列表
                    popularScenicSpotsList = shopMallHomePageResponse.getScenicList();
                    popularScenicSpotsAdapter.setPopularScenicSpotsList(popularScenicSpotsList);
                    popularScenicSpotsAdapter.notifyDataSetChanged();

                    //小Banner
                    List<String> smallBanners = shopMallHomePageResponse.getSmallBanners();
                    shopMallIndexSmallBannerAdapter.setShopMallIndexSmallBannerList(smallBanners);
                    shopMallIndexSmallBannerAdapter.notifyDataSetChanged();

                    //限时特卖
                    LimitedSaleBean limitedSaleBean = shopMallHomePageResponse.getLimitedSale();
                    List<GoodsBean> goodsList = limitedSaleBean.getGoods();
                    if(goodsList == null)
                    {
                        mLlytLimitedSale.setVisibility(View.GONE);
                    } else {
                        mLlytLimitedSale.setVisibility(View.VISIBLE);
                    }
                    List<GoodsBean> limitedSaleGoodsList = limitedSaleBean.getGoods();
                    if (limitedSaleGoodsList != null && limitedSaleGoodsList.size() > 0) {

                        //限时特卖1
                        GoodsBean goodsBeanOne = limitedSaleGoodsList.get(0);
                        String limitedSaleOneGoodsPhoto = goodsBeanOne.getGoodsPhoto();
                        String limitedSaleOneGoodsName = goodsBeanOne.getGoodsName();
                        String limitedSaleOneSalesDiscount = goodsBeanOne.getSalesDiscount();
                        double goodsPrice = goodsBeanOne.getGoodsPrice();
                        GlideUtil.loadImage(getActivity(), limitedSaleOneGoodsPhoto, mImageFlashSaleOne);
                        mTvLimitedSaleOneTitle.setText(limitedSaleOneGoodsName);
                        mTvLimitedSaleOneDiscount.setText(limitedSaleOneSalesDiscount + "折");
                        mTvLimitedSaleOneRMBSymbol.setText(RadiusGradientSpanUtil.getRadiusGradientSpan("¥", 0xFFF7418C, 0xFFFBAB66));
                        mTvLimitedSaleOnePrice.setText(RadiusGradientSpanUtil.getRadiusGradientSpan(Tools.getTwoDecimalPoint(goodsPrice), 0xFFF7418C, 0xFFFBAB66));

                        if(limitedSaleGoodsList.size() > 1)
                        {
                            //限时特卖2
                            GoodsBean goodsBeanTwo = limitedSaleGoodsList.get(1);
                            String limitedSaleTwoGoodsPhoto = goodsBeanTwo.getGoodsPhoto();
                            String limitedSaleTwoGoodsName = goodsBeanTwo.getGoodsName();
                            String limitedSaleTwoSalesDiscount = goodsBeanTwo.getSalesDiscount();
                            GlideUtil.loadImage(getActivity(), limitedSaleTwoGoodsPhoto, mImageFlashSaleTwo);
                            mTvLimitedSaleTwoTitle.setText(limitedSaleTwoGoodsName);
                            mTvLimitedSaleTwoDiscount.setText(limitedSaleTwoSalesDiscount + "折");
                            mTvLimitedSaleTwoRMBSymbol.setText(RadiusGradientSpanUtil.getRadiusGradientSpan("¥", 0xFFF7418C, 0xFFFBAB66));
                            mTvLimitedSaleTwoPrice.setText(RadiusGradientSpanUtil.getRadiusGradientSpan(Tools.getTwoDecimalPoint(goodsPrice), 0xFFF7418C, 0xFFFBAB66));
                        }

                        if(limitedSaleGoodsList.size() > 2) {
                            //限时特卖3
                            GoodsBean goodsBeanThree = limitedSaleGoodsList.get(2);
                            String limitedSaleThreeGoodsPhoto = goodsBeanThree.getGoodsPhoto();
                            String limitedSaleThreeGoodsName = goodsBeanThree.getGoodsName();
                            String limitedSaleThreeSalesDiscount = goodsBeanThree.getSalesDiscount();
                            GlideUtil.loadImage(getActivity(), limitedSaleThreeGoodsPhoto, mImageFlashSaleThree);
                            mTvLimitedSaleThreeTitle.setText(limitedSaleThreeGoodsName);
                            mTvLimitedSaleThreeDiscount.setText(limitedSaleThreeSalesDiscount + "折");
                            mTvLimitedSaleThreeRMBSymbol.setText(RadiusGradientSpanUtil.getRadiusGradientSpan("¥", 0xFFF7418C, 0xFFFBAB66));
                            mTvLimitedSaleThreePrice.setText(RadiusGradientSpanUtil.getRadiusGradientSpan(Tools.getTwoDecimalPoint(goodsPrice), 0xFFF7418C, 0xFFFBAB66));
                        }
                    }

                    //美食列表
                    deliciousFoodList = shopMallHomePageResponse.getFoodList();
                    deliciousFoodRecommendAdapter.setDeliciousFoodRecommendList(deliciousFoodList);
                    deliciousFoodRecommendAdapter.notifyDataSetChanged();

                    //民宿
                    hotelList = shopMallHomePageResponse.getHotelList();
                    mHotelAdapter.setHotelList(hotelList);
                    mHotelAdapter.notifyDataSetChanged();

                    if(mchCities != null) {
                        //目的地
                        mchCities = shopMallHomePageResponse.getMchCities();
                        scenicSpotClassificationAdapter.setScenicSpotMchCitiesDestinationList(mchCities);
                        scenicSpotClassificationAdapter.notifyDataSetChanged();
                    }

                    //旅行主题
                    List<ShopMallHomePageResponse.TravelBannersEntity> travelBannersList = shopMallHomePageResponse.getTravelBanners();
                    //旅行主题1
                    if(travelBannersList != null && travelBannersList.size() > 0)
                    {
                        ShopMallHomePageResponse.TravelBannersEntity travelThemeOne = travelBannersList.get(0);
                        String travelThemeOnePhotoUrl = travelThemeOne.getPhoto();
                        GlideUtil.loadImage(getActivity(), travelThemeOnePhotoUrl, mImgTravelThemeOne);
                        mTvTravelThemeOneTitle.setText(travelThemeOne.getTitle());
                        mTvTravelThemeOneDes.setText(travelThemeOne.getIntro());
                    }

                    //旅行主题2
                    if(travelBannersList != null && travelBannersList.size() > 1)
                    {
                        ShopMallHomePageResponse.TravelBannersEntity travelThemeTwo = travelBannersList.get(1);
                        String travelThemeTwoPhotoUrl = travelThemeTwo.getPhoto();
                        GlideUtil.loadImage(getActivity(), travelThemeTwoPhotoUrl, mImgTravelThemeTwo);
                        mTvTravelThemeTwoTitle.setText(travelThemeTwo.getTitle());
                        mTvTravelThemeTwoDes.setText(travelThemeTwo.getIntro());
                    }

                    //旅行主题3
                    if(travelBannersList != null && travelBannersList.size() > 2) {
                        ShopMallHomePageResponse.TravelBannersEntity travelThemeThree = travelBannersList.get(2);
                        String travelThemeThreePhotoUrl = travelThemeThree.getPhoto();
                        GlideUtil.loadImage(getActivity(), travelThemeThreePhotoUrl, mImgTravelThemeThree);
                        mTvTravelThemeThreeTitle.setText(travelThemeThree.getTitle());
                        mTvTravelThemeThreeDes.setText(travelThemeThree.getIntro());
                    }

                    //旅行主题4
                    if(travelBannersList != null && travelBannersList.size() > 3)
                    {
                        ShopMallHomePageResponse.TravelBannersEntity travelThemeFour = travelBannersList.get(3);
                        String travelThemeFourPhotoUrl = travelThemeFour.getPhoto();
                        GlideUtil.loadImage(getActivity(), travelThemeFourPhotoUrl, mImgTravelThemeFour);
                        mTvTravelThemeFourTitle.setText(travelThemeFour.getTitle());
                        mTvTravelThemeFourDes.setText(travelThemeFour.getIntro());
                    }

                    //旅行主题5
                    if(travelBannersList != null && travelBannersList.size() > 4) {
                        ShopMallHomePageResponse.TravelBannersEntity travelThemeFive = travelBannersList.get(4);
                        String travelThemeFivePhotoUrl = travelThemeFive.getPhoto();
                        GlideUtil.loadImage(getActivity(), travelThemeFivePhotoUrl, mImgTravelThemeFive);
                        mTvTravelThemeFiveTitle.setText(travelThemeFive.getTitle());
                        mTvTravelThemeFiveDes.setText(travelThemeFive.getIntro());
                    }
                    groupGoodsList = shopMallHomePageResponse.getGroupGoodsVO();
                    //自由行
                    if(groupGoodsList != null && groupGoodsList.size() > 0)
                    {
                        independentTravelAdapter.setIndependentTravelList(groupGoodsList);
                        independentTravelAdapter.notifyDataSetChanged();
                    }

                    //猜你喜欢
                    guessEntityList = shopMallHomePageResponse.getGuess();
                    if(guessEntityList != null && guessEntityList.size() > 0)
                    {
                        mallGuessYouLikeAdapter.setGuessYouLikeList(guessEntityList);
                        mallGuessYouLikeAdapter.notifyDataSetChanged();
                    }

                    long endTimeLong = limitedSaleBean.getEndTime();
                    long currentTime = System.currentTimeMillis() / 1000;
                    endTime = endTimeLong - currentTime;

                    handler.postDelayed(runnable, 1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(getActivity(), Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(getActivity(), Constants.getResultMsg(msg));
    }

    public void getShopMallHomePageData() {

        if (validateInternet()) {

            mPresenter.getShopMallHomePageData();
        }
    }

    @OnClick({R.id.ll_search, R.id.tv_view_more_popular_scenic_spots, R.id.tv_view_more_delicious_food, R.id.rlyt_flash_sale,R.id.tv_view_more_independent_travel,R.id.tv_message_tag,R.id.tv_view_more_hotel_reputation})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_search:

                toActivity(DestinationSearchActivity.class);

                break;
            case R.id.tv_view_more_popular_scenic_spots:

                toActivity(ScenicSpotBangDanListActivity.class);

                break;
            case R.id.tv_view_more_delicious_food:
                String token = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.TOKEN, "");

                if (!TextUtils.isEmpty(token))
                {
                    toActivity(FineFoodBangDanListActivity.class);
                } else {
                    onReLogin("");
                }

                break;
            case R.id.rlyt_flash_sale:
                toActivity(ShoppingMallSpecialSaleActivity.class);
                break;
            case R.id.tv_view_more_independent_travel:
                toActivity(GroupMchListActivity.class);          //组合
                break;
            case R.id.tv_message_tag:
                toActivity(MessageActivity.class);
                break;
            case R.id.tv_view_more_hotel_reputation:
                toActivity(ShoppingMallScreeningActivity.class);
                break;
            default:
                break;
        }

    }

    @Override
    public void lazyInitView(View view) {

    }

    @Override
    public void getCarH5UrlResult(BackResult<CarH5UrlResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    CarH5UrlResponse carH5UrlResponse = res.getData();
                    String carH5Url = carH5UrlResponse.getUrl();

                    if (!TextUtils.isEmpty(carH5Url)) {
                        Intent intent = new Intent();
                        intent.putExtra("url", carH5Url);
                        intent.putExtra("title", Constants.VEHICLE_USE_H5_TITLE);
                        intent.setClass(getActivity(), WebActivity.class);
                        startActivity(intent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(getActivity(), Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getWeatherResult(BackResult<WeatherResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    WeatherResponse weatherResponse = res.getData();
                    String weather = weatherResponse.getWeather();
                    String temperature = weatherResponse.getTemperature();
                    String photoUrl = weatherResponse.getPhoto();
                    mTvTemperature.setText(temperature + "°");
                    mTvWeather.setText(weather);
                    GlideUtil.loadImage(getActivity(),photoUrl,mImgWeather);

                    System.out.print(weatherResponse);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(getActivity(), Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    public void getCarH5Url() {
        if (validateInternet()) {
            showProgressDialog(getActivity());
            String latitude = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.LATITUDE, "");
            String longitude = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.LONGITUDE, "");
            mPresenter.getCarH5Url(longitude, latitude);
        }
    }

    public List<ShoppingMallMenuBean> getShopMallMenuList() {

        ShoppingMallMenuBean shoppingMallFamousScenery = new ShoppingMallMenuBean();
        shoppingMallFamousScenery.setTitle("景点");
        shoppingMallFamousScenery.setIcon(R.mipmap.icon_tab_famous_scenery);

        ShoppingMallMenuBean shoppingMallFine = new ShoppingMallMenuBean();
        shoppingMallFine.setTitle("美食");
        shoppingMallFine.setIcon(R.mipmap.icon_tab_fine_food);

        ShoppingMallMenuBean shoppingMallHomestay = new ShoppingMallMenuBean();
        shoppingMallHomestay.setTitle("酒店");
        shoppingMallHomestay.setIcon(R.mipmap.icon_tab_hotel);

        ShoppingMallMenuBean shoppingMallStrategy = new ShoppingMallMenuBean();
        shoppingMallStrategy.setTitle("攻略");
        shoppingMallStrategy.setIcon(R.mipmap.icon_tab_strategy);

        ShoppingMallMenuBean shoppingMallParentChildTour = new ShoppingMallMenuBean();
        shoppingMallParentChildTour.setTitle("民宿");
        shoppingMallParentChildTour.setIcon(R.mipmap.icon_tab_homestay);

        ShoppingMallMenuBean shoppingMallInteraction = new ShoppingMallMenuBean();
        shoppingMallInteraction.setTitle("互动");
        shoppingMallInteraction.setIcon(R.mipmap.icon_tab_interaction);

        ShoppingMallMenuBean shoppingMallIndependentTravel = new ShoppingMallMenuBean();
        shoppingMallIndependentTravel.setTitle("自由行");
        shoppingMallIndependentTravel.setIcon(R.mipmap.icon_tab_combination_independent_travel);

        ShoppingMallMenuBean shoppingMallUseCar = new ShoppingMallMenuBean();
        shoppingMallUseCar.setTitle("用车");
        shoppingMallUseCar.setIcon(R.mipmap.icon_tab_vehicle_use);

        shoppingMallMenuList.add(shoppingMallFamousScenery);
        shoppingMallMenuList.add(shoppingMallFine);
        shoppingMallMenuList.add(shoppingMallHomestay);
        shoppingMallMenuList.add(shoppingMallStrategy);
        shoppingMallMenuList.add(shoppingMallParentChildTour);
        shoppingMallMenuList.add(shoppingMallInteraction);
        shoppingMallMenuList.add(shoppingMallIndependentTravel);
        shoppingMallMenuList.add(shoppingMallUseCar);

        return shoppingMallMenuList;
    }

    //获取天气接口
    public void getWeather(int cityCode) {

        if (validateInternet()) {

            //showProgressDialog(getActivity());
            mPresenter.getWeather(cityCode);

        }
    }

    @Override
    public void onPause() {
        super.onPause();
       /* if (handler != null) {
            handler.removeCallbacks(runnable);
        }*/
    }

    //页面销毁时，销毁线程
    @Override
    public void onDestroy() {
        super.onDestroy();

        if (handler != null) {
            handler.removeCallbacks(runnable);
        }
    }
}
