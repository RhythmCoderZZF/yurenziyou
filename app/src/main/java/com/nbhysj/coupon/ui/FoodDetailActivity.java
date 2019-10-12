package com.nbhysj.coupon.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.FoodNearbyMoreAdapter;
import com.nbhysj.coupon.adapter.ScenicSpotDetailUserCommentAdapter;
import com.nbhysj.coupon.adapter.ShopRecommendDeliciousFoodAdapter;
import com.nbhysj.coupon.adapter.UserCommentAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.FineFoodContract;
import com.nbhysj.coupon.dialog.ShareOprateDialog;
import com.nbhysj.coupon.model.FineFoodModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.LabelEntity;
import com.nbhysj.coupon.model.response.MchBangDanRankingResponse;
import com.nbhysj.coupon.model.response.MchCollectionResponse;
import com.nbhysj.coupon.model.response.MchCommentEntity;
import com.nbhysj.coupon.model.response.MchDetailsResponse;
import com.nbhysj.coupon.model.response.MchFoodBean;
import com.nbhysj.coupon.model.response.MchFoodDetailResponse;
import com.nbhysj.coupon.model.response.MchGoodsBean;
import com.nbhysj.coupon.model.response.NearbyTypeResponse;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.model.response.ScenicSpotResponse;
import com.nbhysj.coupon.presenter.FineFoodPresenter;
import com.nbhysj.coupon.systembar.StatusBarCompat;
import com.nbhysj.coupon.systembar.StatusBarUtil;
import com.nbhysj.coupon.util.PopupWindowUtil;
import com.nbhysj.coupon.util.Tools;
import com.nbhysj.coupon.view.RecyclerScrollView;
import com.nbhysj.coupon.view.ScenicSpotDetailBannerView;
import com.nbhysj.coupon.view.StarBarView;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/09/28
 * description：美食详情
 */
public class FoodDetailActivity extends BaseActivity<FineFoodPresenter, FineFoodModel> implements FineFoodContract.View, RecyclerScrollView.OnScrollListener {

    @BindView(R.id.toolbar_space)
    View mToolbarSpace;
    @BindView(R.id.banner_delicious_food)
    ScenicSpotDetailBannerView bannerView;

    @BindView(R.id.scrollview_food_recommendation)
    RecyclerScrollView mScrollViewFoodRecommendation;
    @BindView(R.id.ibtn_back)
    ImageButton mImgBtnBack;
    @BindView(R.id.rlyt_food_header)
    RelativeLayout mRlytFoodHeader;
    //收藏
    @BindView(R.id.img_collection)
    ImageView mImgCollection;
    //美食人均价
    @BindView(R.id.tv_average_price_per_person)
    TextView mTvAveragePricePerPerson;
    //商户评论数
    @BindView(R.id.tv_mch_comment_num)
    TextView mTvMchCommentNum;
    //商户美食标签
    @BindView(R.id.tv_mch_food_commentscore_tag)
    TextView mTvMchFoodCommentScoreTag;

    @BindView(R.id.rv_user_comment)
    RecyclerView mRvUserComment;
    //商户附近
    @BindView(R.id.rv_delicious_food_more_recommendation)
    RecyclerView mRvDeliciousFoodMoreRecommendation;
    @BindView(R.id.img_menu)
    ImageView mImageMenu;
    //详情页转发
    @BindView(R.id.img_scenic_spot_forward)
    ImageView mImgScenicSpotForward;
    //美食商户名字
    @BindView(R.id.tv_mch_name)
    TextView mTvMchName;
    //星级
    @BindView(R.id.starbar)
    StarBarView mStarBarView;
    //开放时间
    @BindView(R.id.tv_open_time)
    TextView mTvOpenTime;
    //位置
    @BindView(R.id.tv_mch_address)
    TextView mTvMchFoodAddress;
    //排行榜
    @BindView(R.id.tv_mch_ranking)
    TextView mTvMchRanking;
    //用户评论数量
    @BindView(R.id.tv_user_comment_num)
    TextView mTvUserCommentNum;
    //美食推荐
    @BindView(R.id.rv_shop_recommend_delicious_food)
    RecyclerView mRvShopRecommendDeliciousFood;
    //美食推荐布局
    @BindView(R.id.llyt_fine_food_recommend)
    LinearLayout mLlytFineFoodRecommend;
    //用户评论
    @BindView(R.id.llyt_user_comment)
    LinearLayout mLlytUserComment;
    //附近商户
    @BindView(R.id.llyt_nearby_food)
    LinearLayout mLlytNearbyFood;
    //美食用户评价标签
    @BindView(R.id.flowlayout_food_recommend_label)
    TagFlowLayout mTagFlowFoodRecommentLabel;

    private int height;
    private List<ImageView> viewList;
    private List<String> bannerList;
    private PopupWindow mPopupWindow;
    MchFoodDetailResponse mchDetailsResponse;
    MchFoodDetailResponse.MchDetailsEntity mchDetailsEntity;
    //用户评论标签
    List<LabelEntity> labelEntityList;
    //用户评论列表
    List<MchCommentEntity> userCommentList;
    //景点列表
    List<MchFoodDetailResponse.NearbyFoodEntity> nearbyFoodsList;
    List<MchFoodBean> mchRecommendFoodList;
    private UserCommentAdapter userCommentLabelAdapter;
    //附近商户
    private  FoodNearbyMoreAdapter foodNearbyMoreAdapter;
    private ScenicSpotDetailUserCommentAdapter userCommentAdapter;
    //商户名
    private int mchId;
    private String address;
    //商户名
    private String mchName;
    //纬度
    String latitude;
    //经度
    String longitude;

    //收藏状态
    private int collectionStatus;
    private ShopRecommendDeliciousFoodAdapter recommendDeliciousFoodAdapter;

    @Override
    public int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusBarCompat.translucentStatusBar(this, false);
        //修改状态栏字体颜色
        StatusBarUtil.setImmersiveStatusBar(this, true);
        return R.layout.activity_food_recommendation;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mchId = getIntent().getIntExtra("mchId", 0);
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

        if (labelEntityList == null) {

            labelEntityList = new ArrayList<>();

        } else {

            labelEntityList.clear();
        }

        if (userCommentList == null) {

            userCommentList = new ArrayList<>();
        } else {

            userCommentList.clear();
        }

        if (nearbyFoodsList == null) {

            nearbyFoodsList = new ArrayList<>();
        } else {
            nearbyFoodsList.clear();
        }

        if (mchRecommendFoodList == null) {

            mchRecommendFoodList = new ArrayList<>();
        } else {
            mchRecommendFoodList.clear();
        }

        if (viewList == null) {
            viewList = new ArrayList<ImageView>();
        } else {
            viewList.clear();
        }
        if (bannerList == null) {
            bannerList = new ArrayList<>();
        } else {
            bannerList.clear();
        }

        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(FoodDetailActivity.this,
                        3);
        mRvShopRecommendDeliciousFood.setLayoutManager(gridLayoutManager);
        recommendDeliciousFoodAdapter = new ShopRecommendDeliciousFoodAdapter(FoodDetailActivity.this);
        recommendDeliciousFoodAdapter.setMchFoodsList(mchRecommendFoodList);
        mRvShopRecommendDeliciousFood.setAdapter(recommendDeliciousFoodAdapter);
        mRvShopRecommendDeliciousFood.addItemDecoration(new RecyclerItemDecoration(11, 3));

        LinearLayoutManager userCommentLayoutManager = new LinearLayoutManager(FoodDetailActivity.this);
        userCommentLayoutManager.setOrientation(userCommentLayoutManager.VERTICAL);
        mRvUserComment.setLayoutManager(userCommentLayoutManager);
        userCommentAdapter = new ScenicSpotDetailUserCommentAdapter(FoodDetailActivity.this);
        userCommentAdapter.setScenicSpotsUserCommentList(userCommentList);
        mRvUserComment.setAdapter(userCommentAdapter);

        GridLayoutManager moreRecommendationGridLayoutManager =
                new GridLayoutManager(FoodDetailActivity.this,
                        2);
        mRvDeliciousFoodMoreRecommendation.setLayoutManager(moreRecommendationGridLayoutManager);
        foodNearbyMoreAdapter = new FoodNearbyMoreAdapter(FoodDetailActivity.this);
        foodNearbyMoreAdapter.setFoodNearbyList(nearbyFoodsList);
        mRvDeliciousFoodMoreRecommendation.setAdapter(foodNearbyMoreAdapter);
        mRvDeliciousFoodMoreRecommendation.addItemDecoration(new RecyclerItemDecoration(11, 2));
    }

    @Override
    public void initData() {
        showProgressDialog(FoodDetailActivity.this);
        getMchFoodDetails();

        //获取顶部图片高度后，设置滚动监听
        ViewTreeObserver vto = bannerView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                bannerView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                height = bannerView.getHeight();
                bannerView.getWidth();

                mScrollViewFoodRecommendation.setScrolListener(FoodDetailActivity.this);
            }
        });



    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    public void getFineFoodHomePageResult(BackResult<ScenicSpotHomePageResponse> res) {

    }

    @Override
    public void findFoodByCateResult(BackResult<ScenicSpotResponse> res) {

    }

    @Override
    public void getFoodBangDanRankingResult(BackResult<MchBangDanRankingResponse> res) {

    }

    @Override
    public void getFoodDetailResult(BackResult<MchFoodDetailResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    bannerList.clear();
                    mchDetailsResponse = res.getData();

                    mchDetailsEntity = mchDetailsResponse.getMchDetails();
                    MchFoodDetailResponse.MchQuestionEntity mchQuestionEntity = mchDetailsResponse.getMchQuestion(); //问题
                    MchFoodDetailResponse.CommentEntity commentEntity = mchDetailsResponse.getComment();


                    mchName = mchDetailsEntity.getMchName();
                    String photoUrl = mchDetailsEntity.getPhoto();
                    float commentScore = mchDetailsEntity.getCommentScore();
                    double consumePrice = mchDetailsEntity.getConsumePrice();
                    int mchCommentNum = mchDetailsEntity.getCommentNum();

                    mTvMchName.setText(mchName);
                    bannerList.add(photoUrl);
                    if (bannerList.size() > 0) {

                        for (int i = 0; i < bannerList.size(); i++) {
                            ImageView image = new ImageView(FoodDetailActivity.this);
                            image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                            //设置显示格式
                            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            viewList.add(image);
                        }
                    }
                    bannerView.setViewList(FoodDetailActivity.this, viewList, bannerList, new ScenicSpotDetailBannerView.ScenicSpotDetailBannerViewListener() {
                        @Override
                        public void setScenicSpotDetailBannerViewListener(int curPos) {

                        }

                        @Override
                        public void bannerOnclickListener() {


                        }
                    });

                    mStarBarView.setIntegerMark(false);
                    mStarBarView.setStarMark(commentScore);

                    mTvAveragePricePerPerson.setText(Tools.getTwoDecimalPoint(consumePrice));

                    float tasteCommentScore = mchDetailsEntity.getCommentScore1();
                    float environmentCommentScore = mchDetailsEntity.getCommentScore2();
                    float serviceCommentScore = mchDetailsEntity.getCommentScore3();
                    mTvMchFoodCommentScoreTag.setText("味道:" + tasteCommentScore + "  环境:" + environmentCommentScore + "  服务:" + serviceCommentScore);
                    mTvMchCommentNum.setText(String.valueOf(mchCommentNum) + "评价");

                    //开放时间
                    String openTime = mchDetailsEntity.getOpenTime();
                    mTvOpenTime.setText(openTime);

                    //商家位置
                    address = mchDetailsEntity.getAddress();
                    mTvMchFoodAddress.setText(address);

                    MchFoodDetailResponse.ScoreEntity scoreEntity = commentEntity.getScore();
                    labelEntityList = commentEntity.getLabel();
                    int mUserCommentNum = scoreEntity.getCommentNum();
                    mTvUserCommentNum.setText("更多评论" + "(" + String.valueOf(mUserCommentNum) + "条)");

                    //排行榜
                    String mchRanking = mchDetailsEntity.getMchRanking();
                    if (!TextUtils.isEmpty(mchRanking)) {
                        mTvMchRanking.setText(mchRanking);
                    }
                    mchRecommendFoodList = mchDetailsResponse.getMchGoods();     //美食推荐列表

                    if (mchRecommendFoodList != null) {
                        if (mchRecommendFoodList.size() > 0) {
                            mLlytFineFoodRecommend.setVisibility(View.VISIBLE);
                            recommendDeliciousFoodAdapter.setMchFoodsList(mchRecommendFoodList);
                            recommendDeliciousFoodAdapter.notifyDataSetChanged();
                        } else {
                            mLlytFineFoodRecommend.setVisibility(View.GONE);
                        }

                    } else {
                        mLlytFineFoodRecommend.setVisibility(View.GONE);
                    }

                    //用户评论
                    userCommentList = commentEntity.getComment();
                    if (userCommentList != null) {
                        if (userCommentList.size() > 0) {
                            userCommentAdapter.setScenicSpotsUserCommentList(userCommentList);
                            userCommentAdapter.notifyDataSetChanged();
                        } else {
                            mLlytUserComment.setVisibility(View.GONE);
                        }
                    } else {

                        mLlytUserComment.setVisibility(View.GONE);
                    }

                    //附近美食
                    nearbyFoodsList = mchDetailsResponse.getNearbyFood();
                    if (nearbyFoodsList != null) {
                        if (nearbyFoodsList.size() > 0)
                        {
                            foodNearbyMoreAdapter.setFoodNearbyList(nearbyFoodsList);
                            foodNearbyMoreAdapter.notifyDataSetChanged();
                        } else {
                            mLlytNearbyFood.setVisibility(View.GONE);
                        }
                    } else {

                        mLlytNearbyFood.setVisibility(View.GONE);
                    }

                    collectionStatus = mchDetailsEntity.getUserCollectState();

                    if(collectionStatus == 0)
                    {
                        mImgCollection.setImageResource(R.mipmap.icon_white_collection);


                    } else if(collectionStatus == 1){

                        mImgCollection.setImageResource(R.mipmap.icon_green_has_collection);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(FoodDetailActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void mchCollectionResult(BackResult<MchCollectionResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    MchCollectionResponse mchCollectionResponse = res.getData();
                    collectionStatus = mchCollectionResponse.getCollectionStatus();

                    if(collectionStatus == 0)
                    {
                        mImgCollection.setImageResource(R.mipmap.icon_white_collection);


                    } else if(collectionStatus == 1){

                        mImgCollection.setImageResource(R.mipmap.icon_green_has_collection);

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(FoodDetailActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }


    @Override
    public void showMsg(String msg) {
        dismissProgressDialog();
        showToast(FoodDetailActivity.this, Constants.getResultMsg(msg));
    }

    @Override
    public void onScroll(int y) {
        if (y <= height && y >= 0) {
            float scale = (float) y / height;
            float alpha = (255 * scale);
//          Log.i("TAG","alpha--->"+alpha);

            //layout全部透明
//          layoutHead.setAlpha(scale);

            mRlytFoodHeader.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
            mToolbarSpace.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
            mImgBtnBack.setImageDrawable(getResources().getDrawable(R.mipmap.icon_left_arrow_black));
            if(collectionStatus == 0) {
                mImgCollection.setImageResource(R.mipmap.icon_black_collection);
            }
            mImageMenu.setImageDrawable(getResources().getDrawable(R.mipmap.icon_black_menu_more));
            mImgScenicSpotForward.setImageDrawable(getResources().getDrawable(R.mipmap.icon_black_share));
            if (y <= 100) {
                mImgBtnBack.setImageDrawable(getResources().getDrawable(R.mipmap.icon_left_arrow_white));
                if(collectionStatus == 0) {
                    mImgCollection.setImageDrawable(getResources().getDrawable(R.mipmap.icon_white_collection));
                }
                mImageMenu.setImageDrawable(getResources().getDrawable(R.mipmap.icon_white_menu_more));
                mImgScenicSpotForward.setImageDrawable(getResources().getDrawable(R.mipmap.icon_white_share));
                mRlytFoodHeader.getBackground().setAlpha(255);
                mToolbarSpace.getBackground().setAlpha(255);
            }
        }
    }

    @OnClick({R.id.ibtn_back, R.id.rlyt_mch_ranking, R.id.img_menu, R.id.tv_mch_address, R.id.img_scenic_spot_forward,R.id.tv_see_user_comment_all
    })
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.ibtn_back:
                FoodDetailActivity.this.finish();
                break;
            case R.id.rlyt_mch_ranking:

                toActivity(FineFoodBangDanListActivity.class);

                break;
            case R.id.img_menu:
                showPopupWindow(mImageMenu);
                break;
            case R.id.tv_mch_address:
                Bundle bundle = new Bundle();

                bundle.putSerializable("mchDetailsEntity", mchDetailsEntity);
                intent.putExtras(bundle);
                intent.setClass(FoodDetailActivity.this, ScenicSpotsDetailLocationMapActivity.class);
                startActivity(intent);
                break;
            case R.id.img_scenic_spot_forward:

                ShareOprateDialog shareOprateDialog = new ShareOprateDialog(FoodDetailActivity.this, new ShareOprateDialog.OnSharePlatformItemClickListener() {
                    @Override
                    public void onSharePlatformItemClick(String sharePlatform) {

                        //   showToast(getActivity(),sharePlatform);

                       /* new ShareAction(ScenicSpotDetailActivity.this)
                                .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                                .withText("hello")//分享内容
                                .setCallback(umShareListener)//回调监听器
                                .share();*/

                    }
                }).builder().setCancelable(true).setCanceledOnTouchOutside(true);
                shareOprateDialog.show();

                break;

            default:
                break;

        }
    }


    private void showPopupWindow(View anchorView) {
        View contentView = getPopupWindowContentView();
        mPopupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        // 设置pop出入动画
        mPopupWindow.setAnimationStyle(R.style.pop_animation);
        // 设置好参数之后再show
        int windowPos[] = PopupWindowUtil.calculatePopWindowPos(anchorView, contentView);
        int xOff = 20; // 可以自己调整偏移
        int yOff = 2; // 可以自己调整偏移
        windowPos[0] -= xOff;
        // windowPos[1] = yOff;
        mPopupWindow.showAtLocation(anchorView, Gravity.TOP | Gravity.START, windowPos[0], windowPos[1]);
    }

    private View getPopupWindowContentView() {
        // 一个自定义的布局，作为显示的内容
        int layoutId = R.layout.layout_popup_scenic_spot_detail_menu;   // 布局ID
        View contentView = LayoutInflater.from(this).inflate(layoutId, null);
        View.OnClickListener menuItemOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Click " + ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
            }
        };
        contentView.findViewById(R.id.menu_item1).setOnClickListener(menuItemOnClickListener);
        contentView.findViewById(R.id.menu_item2).setOnClickListener(menuItemOnClickListener);
        contentView.findViewById(R.id.menu_item3).setOnClickListener(menuItemOnClickListener);
        contentView.findViewById(R.id.menu_item4).setOnClickListener(menuItemOnClickListener);
        return contentView;
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

    public void getMchFoodDetails() {

        mPresenter.getFoodDetail(mchId);
    }
}
