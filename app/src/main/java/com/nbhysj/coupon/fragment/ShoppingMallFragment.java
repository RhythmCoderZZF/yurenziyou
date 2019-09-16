package com.nbhysj.coupon.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.DeliciousFoodRecommendAdapter;
import com.nbhysj.coupon.adapter.HomestayAdapter;
import com.nbhysj.coupon.adapter.HotelAdapter;
import com.nbhysj.coupon.adapter.IndependentTravelAdapter;
import com.nbhysj.coupon.adapter.PopularScenicSpotsAdapter;
import com.nbhysj.coupon.adapter.ScenicSpotMchDestinationAdapter;
import com.nbhysj.coupon.adapter.ShopMallIndexSmallBannerAdapter;
import com.nbhysj.coupon.adapter.ShoppingMallGuessYouLikeAdapter;
import com.nbhysj.coupon.adapter.ShoppingMallMenuAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.ShopMallHomePageContract;
import com.nbhysj.coupon.model.ShopMallHomePageModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CarH5UrlResponse;
import com.nbhysj.coupon.model.response.DeliciousFoodResponse;
import com.nbhysj.coupon.model.response.GroupGoodsBean;
import com.nbhysj.coupon.model.response.MchCitiesBean;
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.model.response.ShopMallHomePageResponse;
import com.nbhysj.coupon.model.response.ShoppingMallMenuBean;
import com.nbhysj.coupon.presenter.ShopMallHomePagePresenter;
import com.nbhysj.coupon.ui.CombinationListActivity;
import com.nbhysj.coupon.ui.DestinationSearchActivity;
import com.nbhysj.coupon.ui.FineGoodListActivity;
import com.nbhysj.coupon.ui.ShoppingMallFineFoodActivity;
import com.nbhysj.coupon.ui.ShoppingMallHomestayActivity;
import com.nbhysj.coupon.ui.ShoppingMallHotelActivity;
import com.nbhysj.coupon.ui.ShoppingMallInteractionActivity;
import com.nbhysj.coupon.ui.ShoppingMallScenicSpotActivity;
import com.nbhysj.coupon.ui.ShoppingMallScreeningActivity;
import com.nbhysj.coupon.ui.ShoppingMallSpecialSaleActivity;
import com.nbhysj.coupon.ui.WebActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.RadiusGradientSpanUtil;
import com.nbhysj.coupon.view.BannerView;
import com.nbhysj.coupon.view.GlideImageView;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import java.util.ArrayList;
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
    @BindView(R.id.image_flash_sale_one)
    ImageView mImageFlashSaleOne;
    //限时特卖图片2
    @BindView(R.id.image_flash_sale_two)
    GlideImageView mImageFlashSaleTwo;
    //限时特卖图片3
    @BindView(R.id.image_flash_sale_three)
    GlideImageView mImageFlashSaleThree;
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
    ImageView mImgTravelThemeOne;
    @BindView(R.id.tv_travel_theme_one_title)
    TextView mTvTravelThemeOneTitle;
    @BindView(R.id.tv_travel_theme_one_des)
    TextView mTvTravelThemeOneDes;
    //旅行主题2
    @BindView(R.id.img_travel_theme_two)
    ImageView mImgTravelThemeTwo;
    @BindView(R.id.tv_travel_theme_two_title)
    TextView mTvTravelThemeTwoTitle;
    @BindView(R.id.tv_travel_theme_two_des)
    TextView mTvTravelThemeTwoDes;
    //旅行主题3
    @BindView(R.id.img_travel_theme_three)
    ImageView mImgTravelThemeThree;
    @BindView(R.id.tv_travel_theme_three_title)
    TextView mTvTravelThemeThreeTitle;
    @BindView(R.id.tv_travel_theme_three_des)
    TextView mTvTravelThemeThreeDes;
    //旅行主题4
    @BindView(R.id.img_travel_theme_four)
    ImageView mImgTravelThemeFour;
    @BindView(R.id.tv_travel_theme_four_title)
    TextView mTvTravelThemeFourTitle;
    @BindView(R.id.tv_travel_theme_four_des)
    TextView mTvTravelThemeFourDes;
    //旅行主题5
    @BindView(R.id.img_travel_theme_five)
    ImageView mImgTravelThemeFive;
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
    //限时特卖人民币符号
    @BindView(R.id.tv_flash_sale_one_rmb_symbol)
    TextView mTvFlashSaleOneRMBSymbol;
    @BindView(R.id.tv_flash_sale_one_per_capita_price)
    TextView mTvFlashSaleOnePerCapitaPrice;
    @BindView(R.id.tv_flash_sale_one_rise)
    TextView mTvFlashSaleOneRise;

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
        getShopMallHomePageData();


        //  mBannerview = (BannerView) v.findViewById(R.id.banner);
        // mBannerview.startLoop(true);
        mBannerview.setViewList(getActivity(), bannerList);

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        getShopMallHomePageData();

                    }
                }, 100);
            }
        });
    }

    @Override
    public void initData() {

        if(shoppingMallMenuList == null){

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
                    //  toActivity(MutiScrollDemoActivity.class);
                } else if (position == 4) {
                    toActivity(ShoppingMallHomestayActivity.class); //民宿
                } else if (position == 5) {
                    toActivity(ShoppingMallInteractionActivity.class);  //互动
                } else if (position == 6) {
                    toActivity(CombinationListActivity.class);
                } else if (position == 7) {                              //用车

                   // toActivity(IntroductionOfLandlordActivity.class);
                    getCarH5Url();
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

        //第一个是上下文，第二个是圆角的弧度
        RequestOptions myOptions = new RequestOptions()
                .transform(new GlideRoundTransform(getActivity(), 5));

        Glide.with(this)
                .load("http://pic32.nipic.com/20130823/13339320_183302468194_2.jpg")
                .apply(myOptions)
                .into(mImageFlashSaleTwo);

        Glide.with(this)
                .load("http://pic32.nipic.com/20130823/13339320_183302468194_2.jpg")
                .apply(myOptions)
                .into(mImageFlashSaleThree);

        Glide.with(this)
                .load("http://pic32.nipic.com/20130823/13339320_183302468194_2.jpg")
                .apply(myOptions)
                .into(mImageFlashSaleOne);


        mTvFlashSaleOneRMBSymbol.setText(RadiusGradientSpanUtil.getRadiusGradientSpan("¥", 0xFFF7418C, 0xFFFBAB66));
        mTvFlashSaleOnePerCapitaPrice.setText(RadiusGradientSpanUtil.getRadiusGradientSpan("156", 0xFFF7418C, 0xFFFBAB66));


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
                    bannerList = res.getData().getBigBanners();
                    //小Banner
                    List<String> smallBanners = res.getData().getSmallBanners();

                    shopMallIndexSmallBannerAdapter.setShopMallIndexSmallBannerList(smallBanners);
                    shopMallIndexSmallBannerAdapter.notifyDataSetChanged();

                    //大banner
                    bannerList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1555675742935&di=4c82bf091f644525979cefd0402888df&imgtype=0&src=http%3A%2F%2Fimg2.ph.126.net%2FLQILaNyFbFqu5AZFbSu1kw%3D%3D%2F6631433903071736684.jpg");
                    bannerList.add("http://pic32.nipic.com/20130823/13339320_183302468194_2.jpg");
                    mBannerview.setViewList(getActivity(), bannerList);

                    //景点列表
                    popularScenicSpotsList = res.getData().getScenicList();
                    popularScenicSpotsAdapter.setPopularScenicSpotsList(popularScenicSpotsList);
                    popularScenicSpotsAdapter.notifyDataSetChanged();

                    //美食列表
                    deliciousFoodList = res.getData().getFoodList();
                    deliciousFoodRecommendAdapter.setDeliciousFoodRecommendList(deliciousFoodList);
                    deliciousFoodRecommendAdapter.notifyDataSetChanged();

                    //民宿
                    hotelList = res.getData().getHotelList();
                    mHotelAdapter.setHotelList(hotelList);
                    mHotelAdapter.notifyDataSetChanged();

                    //目的地
                    mchCities = res.getData().getMchCities();
                    scenicSpotClassificationAdapter.setScenicSpotMchCitiesDestinationList(mchCities);
                    scenicSpotClassificationAdapter.notifyDataSetChanged();

                    //旅行主题
                    List<ShopMallHomePageResponse.TravelBannersEntity> travelBannersList = res.getData().getTravelBanners();
                    //旅行主题1
                    ShopMallHomePageResponse.TravelBannersEntity travelThemeOne = travelBannersList.get(0);
                    String travelThemeOnePhotoUrl = travelThemeOne.getPhoto();
                    GlideUtil.loadCornersTransformImage(getActivity(), travelThemeOnePhotoUrl, 5, mImgTravelThemeOne);
                    mTvTravelThemeOneTitle.setText(travelThemeOne.getTitle());
                    mTvTravelThemeOneDes.setText(travelThemeOne.getIntro());

                    //旅行主题2
                    ShopMallHomePageResponse.TravelBannersEntity travelThemeTwo = travelBannersList.get(1);
                    String travelThemeTwoPhotoUrl = travelThemeOne.getPhoto();
                    GlideUtil.loadCornersTransformImage(getActivity(), travelThemeTwoPhotoUrl, 5, mImgTravelThemeTwo);
                    mTvTravelThemeTwoTitle.setText(travelThemeTwo.getTitle());
                    mTvTravelThemeTwoDes.setText(travelThemeOne.getIntro());

                    //旅行主题3
                    ShopMallHomePageResponse.TravelBannersEntity travelThemeThree = travelBannersList.get(2);
                    String travelThemeThreePhotoUrl = travelThemeThree.getPhoto();
                    GlideUtil.loadCornersTransformImage(getActivity(), travelThemeThreePhotoUrl, 5, mImgTravelThemeThree);
                    mTvTravelThemeThreeTitle.setText(travelThemeThree.getTitle());
                    mTvTravelThemeThreeDes.setText(travelThemeThree.getIntro());

                    //旅行主题4
                    ShopMallHomePageResponse.TravelBannersEntity travelThemeFour = travelBannersList.get(3);
                    String travelThemeFourPhotoUrl = travelThemeThree.getPhoto();
                    GlideUtil.loadCornersTransformImage(getActivity(), travelThemeFourPhotoUrl, 5, mImgTravelThemeFour);
                    mTvTravelThemeFourTitle.setText(travelThemeFour.getTitle());
                    mTvTravelThemeFourDes.setText(travelThemeFour.getIntro());

                    //旅行主题5
                    ShopMallHomePageResponse.TravelBannersEntity travelThemeFive = travelBannersList.get(4);
                    String travelThemeFivePhotoUrl = travelThemeFive.getPhoto();
                    GlideUtil.loadCornersTransformImage(getActivity(), travelThemeFivePhotoUrl, 5, mImgTravelThemeFive);
                    mTvTravelThemeFiveTitle.setText(travelThemeFive.getTitle());
                    mTvTravelThemeFiveDes.setText(travelThemeFive.getIntro());

                    //自由行
                    groupGoodsList = res.getData().getGroupGoodsVO();
                    independentTravelAdapter.setIndependentTravelList(groupGoodsList);
                    independentTravelAdapter.notifyDataSetChanged();

                    //猜你喜欢
                    guessEntityList = res.getData().getGuess();
                    mallGuessYouLikeAdapter.setGuessYouLikeList(guessEntityList);
                    mallGuessYouLikeAdapter.notifyDataSetChanged();


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

    @OnClick({R.id.ll_search, R.id.tv_view_more_popular_scenic_spots, R.id.tv_view_more_delicious_food, R.id.rlyt_flash_sale_one})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_search:

                toActivity(DestinationSearchActivity.class);

                break;
            case R.id.tv_view_more_popular_scenic_spots:

                toActivity(ShoppingMallScreeningActivity.class);

                break;
            case R.id.tv_view_more_delicious_food:

                toActivity(FineGoodListActivity.class);

                break;
            case R.id.rlyt_flash_sale_one:
                toActivity(ShoppingMallSpecialSaleActivity.class);
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

                    Intent intent = new Intent();
                    intent.putExtra("carH5Url",carH5Url);
                    intent.setClass(getActivity(), WebActivity.class);
                    startActivity(intent);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(getActivity(), Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    public void getCarH5Url()
    {
        if(validateInternet())
        {
            showProgressDialog(getActivity());
            mPresenter.getCarH5Url("121.583030","29.790590");
        }
    }

    public List<ShoppingMallMenuBean> getShopMallMenuList(){

        ShoppingMallMenuBean shoppingMallFamousScenery = new ShoppingMallMenuBean();
        shoppingMallFamousScenery.setTitle("风景名胜");
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
}
