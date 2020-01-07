package com.nbhysj.coupon.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.nbhysj.coupon.adapter.MchCommentAdapter;
import com.nbhysj.coupon.adapter.ShopRecommendDeliciousFoodAdapter;
import com.nbhysj.coupon.adapter.UserCommentAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.common.Enum.MchTypeEnum;
import com.nbhysj.coupon.common.Enum.SharePlatformEnum;
import com.nbhysj.coupon.contract.FineFoodContract;
import com.nbhysj.coupon.dialog.ShareOprateDialog;
import com.nbhysj.coupon.framework.Net;
import com.nbhysj.coupon.model.FineFoodModel;
import com.nbhysj.coupon.model.request.MchCollectionRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.FineFoodCommentInitResponse;
import com.nbhysj.coupon.model.response.FoodRecommendListResponse;
import com.nbhysj.coupon.model.response.LabelEntity;
import com.nbhysj.coupon.model.response.MchBangDanRankingResponse;
import com.nbhysj.coupon.model.response.MchCateListResponse;
import com.nbhysj.coupon.model.response.MchCollectionResponse;
import com.nbhysj.coupon.model.response.MchCommentEntity;
import com.nbhysj.coupon.model.response.MchFoodBean;
import com.nbhysj.coupon.model.response.MchFoodDetailResponse;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.model.response.ScenicSpotResponse;
import com.nbhysj.coupon.pay.wechat.PayConstants;
import com.nbhysj.coupon.presenter.FineFoodPresenter;
import com.nbhysj.coupon.systembar.StatusBarCompat;
import com.nbhysj.coupon.systembar.StatusBarUtil;
import com.nbhysj.coupon.util.PopupWindowUtil;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.nbhysj.coupon.util.Tools;
import com.nbhysj.coupon.view.RecyclerScrollView;
import com.nbhysj.coupon.view.ScenicSpotDetailBannerView;
import com.nbhysj.coupon.view.StarBarView;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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
    private MchCommentAdapter userCommentAdapter;
    //商户id
    private static int mchId;
    private String address;
    //商户名
    private String mchName;

    //纬度
    private String mLatitude;

    //经度
    private String mLongitude;

    //收藏状态
    private int collectionStatus;
    private ShopRecommendDeliciousFoodAdapter recommendDeliciousFoodAdapter;
    static Bitmap bitmap = null;

    private static IWXAPI api;

    private static String photoUrl;

    private ShareOprateDialog shareOprateDialog;
    @Override
    public int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusBarCompat.translucentStatusBar(this, false);
        //修改状态栏字体颜色
        StatusBarUtil.setImmersiveStatusBar(this, true);
        return R.layout.activity_food_detail;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mchId = getIntent().getIntExtra("mchId", 0);

        api = WXAPIFactory.createWXAPI(this, PayConstants.APP_ID, false);
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
        userCommentAdapter = new MchCommentAdapter(FoodDetailActivity.this);
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
    public void findFoodsListByCateIdResult(BackResult<MchCateListResponse> res) {

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
                    photoUrl = mchDetailsEntity.getPhoto();
                    float commentScore = mchDetailsEntity.getCommentScore();
                    double consumePrice = mchDetailsEntity.getConsumePrice();
                    int mchCommentNum = mchDetailsEntity.getCommentNum();

                    mLatitude = mchDetailsEntity.getLatitude();
                    mLongitude = mchDetailsEntity.getLongitude();

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
                    mTvMchFoodCommentScoreTag.setText("口味:" + tasteCommentScore + "  环境:" + environmentCommentScore + "  服务:" + serviceCommentScore);
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
    public void getGoodsFoodRecommendList(BackResult<FoodRecommendListResponse> res) {

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
                        showToast(FoodDetailActivity.this,getResources().getString(R.string.str_collection_cancel));

                    } else if(collectionStatus == 1){

                        showToast(FoodDetailActivity.this,getResources().getString(R.string.str_collection_success));
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

    @OnClick({R.id.ibtn_back, R.id.rlyt_mch_ranking, R.id.img_menu, R.id.tv_mch_address, R.id.img_scenic_spot_forward
    ,R.id.tv_more_businesses_nearby, R.id.img_collection,R.id.tv_recommend_delicious_food_look_more,R.id.tv_look_user_all_comment,R.id.rlyt_fine_food_comment,R.id.tv_user_comment_num})
    public void onClick(View v) {
        String token = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.TOKEN, "");
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.ibtn_back:
                FoodDetailActivity.this.finish();
                break;
            case R.id.rlyt_mch_ranking:

                if (!TextUtils.isEmpty(token))
                {
                    toActivity(FineFoodBangDanListActivity.class);
                } else {
                    onReLogin("");
                }
                break;
            case R.id.img_menu:
                showPopupWindow(mImageMenu);
                break;
            case R.id.tv_mch_address:
                Bundle bundle = new Bundle();

                bundle.putSerializable("mchDetailsEntity", mchDetailsEntity);
                intent.putExtras(bundle);
                intent.setClass(FoodDetailActivity.this, FoodDetailLocationMapActivity.class);
                startActivity(intent);
                break;
            case R.id.img_scenic_spot_forward:

                if(shareOprateDialog == null)
                {
                    shareOprateDialog = new ShareOprateDialog(FoodDetailActivity.this, new ShareOprateDialog.OnSharePlatformItemClickListener() {
                        @Override
                        public void onSharePlatformItemClick(SHARE_MEDIA sharePlatform) {

                            try {
                                if (bannerList != null && bannerList.size() > 0) {
                                    String sharePlatformStr = sharePlatform.toString();
                                    photoUrl = bannerList.get(0);
                                    String wechatFriend = SharePlatformEnum.WECHAT_FRIEND.getValue();
                                    if (sharePlatformStr.equals(wechatFriend)) {

                                        new Thread(saveFileRunnable).start();

                                    } else {

                                        thirdShare(sharePlatform, photoUrl);
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).builder().setCancelable(true).setCanceledOnTouchOutside(true);
                }
                shareOprateDialog.show();
                break;

            case R.id.tv_more_businesses_nearby:

                intent.setClass(FoodDetailActivity.this,NearbyFoodListActivity.class);
                intent.putExtra("longitude",mLongitude);
                intent.putExtra("latitude",mLatitude);
                startActivity(intent);

                break;
            case R.id.img_collection:

                mchCollection();

                break;
            case R.id.tv_recommend_delicious_food_look_more:

                intent.setClass(FoodDetailActivity.this,FoodRecommendationListActivity.class);
                intent.putExtra("mchId",mchId);
                startActivity(intent);

                break;
            case R.id.tv_look_user_all_comment:

                intent.setClass(FoodDetailActivity.this,MchCommentActivity.class);
                intent.putExtra("mchId",mchId);
                startActivity(intent);

                break;
            case R.id.rlyt_fine_food_comment:
                if (!TextUtils.isEmpty(token))
                {
                    intent.setClass(FoodDetailActivity.this,FineFoodEvaluateActivity.class);
                    intent.putExtra("mchId",mchId);
                    startActivity(intent);

                } else {
                    onReLogin("");
                }

                break;
            case R.id.tv_user_comment_num:

                intent.setClass(FoodDetailActivity.this,MchCommentActivity.class);
                intent.putExtra("mchId",mchId);
                startActivity(intent);

                break;

            default:
                break;

        }
    }

    @Override
    public void getFoodCommentIndexResult(BackResult<FineFoodCommentInitResponse> res) {

    }

    @Override
    public void fineFoodCommentResult(BackResult res) {

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

                String itemStr = ((TextView) v).getText().toString();
                String backHomePage = getResources().getString(R.string.str_back_home_page);
                String backMyCollection = getResources().getString(R.string.str_back_my_collection);
                String backMyOrder = getResources().getString(R.string.str_back_my_order);
                String backMyMessage = getResources().getString(R.string.str_back_my_message);

                Intent intent = new Intent();
                if (itemStr.equals(backHomePage)) {

                    if (appManager != null)
                    {
                        appManager.finishActivity(MainActivity.class);
                    }

                    Intent mIntent = new Intent(Constants.BROADCAST_ACTION_MAIN_BACK);
                    mIntent.putExtra(Constants.BROADCAST_ACTION_ARG_OPRATE,Constants.BROADCAST_ACTION_BACK_SHOPPING_MALL);
                    sendBroadcast(mIntent);

                } else if (itemStr.equals(backMyCollection)) {
                    String token = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.TOKEN, "");

                    if (!TextUtils.isEmpty(token))
                    {
                        if (appManager != null)
                        {
                            appManager.finishActivity(MainActivity.class);
                        }

                        Intent mIntent = new Intent(Constants.BROADCAST_ACTION_MAIN_BACK);
                        mIntent.putExtra(Constants.BROADCAST_ACTION_ARG_OPRATE,Constants.BROADCAST_ACTION_BACK_MY_COLLECTION);
                        sendBroadcast(mIntent);

                    } else {
                        onReLogin("");
                    }

                } else if (itemStr.equals(backMyOrder)) {
                    intent.setClass(FoodDetailActivity.this, MyOrderActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                } else if (itemStr.equals(backMyMessage)) {
                    intent.setClass(FoodDetailActivity.this, MessageActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }

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

    //获取美食详情
    public void getMchFoodDetails() {

        mPresenter.getFoodDetail(mchId);
    }

    //商户收藏
    public void mchCollection(){

        if(validateInternet()) {
            showProgressDialog(FoodDetailActivity.this);
            MchCollectionRequest mchCollectionRequest = new MchCollectionRequest();
            mchCollectionRequest.setDataId(mchId);
            mPresenter.mchCollection(mchCollectionRequest);
        }
    }

    private void thirdShare(SHARE_MEDIA platform, String photoUrl) {
        String webUrl = Net.H5_YURENZIYOU_DOWNLOAD_GUIDE_PAGE_URL;
        UMImage image = new UMImage(FoodDetailActivity.this, photoUrl);                    //资源文件
        UMWeb umWeb = new UMWeb(webUrl, FoodDetailActivity.this.getResources().getString(R.string.app_name), "鱼人自游是宁波海洋世界旗下一站式旅游服务平台,产品及服务覆盖门票预订,景点评价及景点打折门票查询,酒店预订,美食推荐、还有更详细的旅游攻略.", image); //URL 标题 描述 封面图
        new ShareAction(FoodDetailActivity.this)
                .setPlatform(platform)//传入平台
                .withText("HiVideo")//标题
                .withMedia(umWeb)
                .setCallback(shareListener)//回调监听器
                .share();
    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            //   Toast.makeText(getActivity(), "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(FoodDetailActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(FoodDetailActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    private static Runnable saveFileRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                URL url = new URL(photoUrl);
                //打开输入流
                InputStream inputStream = url.openStream();
                //对网上资源进行下载转换位图图片
                Bitmap bmp = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
                Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 500, 500, true);
                bmp.recycle();
                WXMiniProgramObject miniProgramObj = new WXMiniProgramObject();
                miniProgramObj.webpageUrl = "http:192.168.1.140:8083/"; // 兼容低版本的网页链接
                miniProgramObj.miniprogramType = WXMiniProgramObject.MINIPTOGRAM_TYPE_RELEASE;// 正式版:0，测试版:1，体验版:2
                miniProgramObj.userName = "gh_8f591c4ee659";     // 小程序原始id
                miniProgramObj.path = Net.MCH_FOODS_MINIPTOGRAM_URL + mchId; //小程序页面路径；对于小游戏，可以只传入 query 部分，来实现传参效果，如：传入 "?foo=bar"
                WXMediaMessage msg = new WXMediaMessage(miniProgramObj);
                msg.title = "鱼人自游";                    // 小程序消息title
                msg.description = "帖子分享";               // 小程序消息desc
                msg.thumbData = compressImage(thumbBmp);                      // 小程序消息封面图片，小于128k

                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = buildTransaction("miniProgram");
                req.message = msg;
                req.scene = SendMessageToWX.Req.WXSceneSession;  // 目前只支持会话
                api.sendReq(req);

                if (bitmap != null) {
                    bitmap.recycle();
                    bitmap = null;
                }

                //saveFile(mBitmap);
                //   mSaveMessage = "图片保存成功！";
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //   messageHandler.sendMessage(messageHandler.obtainMessage());
        }
    };

    private static String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    private static byte[] compressImage(Bitmap bitmapImage) {
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
            int options = 50;
            while (baos.toByteArray().length / 1024 > 100) {    //循环判断如果压缩后图片是否大于100kb,大于继续压缩
                baos.reset();//重置baos即清空baos
                options -= 10;//每次都减少10
                bitmapImage.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中

            }
            //ByteArrayInputStream isBm = new ByteArrayInputStream());//把压缩后的数据baos存放到ByteArrayInputStream中
            //   bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }

}
