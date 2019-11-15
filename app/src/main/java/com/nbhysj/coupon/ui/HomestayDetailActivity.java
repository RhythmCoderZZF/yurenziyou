package com.nbhysj.coupon.ui;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps.model.LatLng;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.HomestayEquipmentAdapter;
import com.nbhysj.coupon.adapter.HomestayReservationAdapter;
import com.nbhysj.coupon.adapter.HomestayResourcesAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.HomestayContract;
import com.nbhysj.coupon.dialog.MchCouponReceiveDialog;
import com.nbhysj.coupon.dialog.ShareOprateDialog;
import com.nbhysj.coupon.model.HomestayModel;
import com.nbhysj.coupon.model.request.MchCollectionRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CommentUserEntity;
import com.nbhysj.coupon.model.response.CouponsBean;
import com.nbhysj.coupon.model.response.CouponsGetBean;
import com.nbhysj.coupon.model.response.HotelBean;
import com.nbhysj.coupon.model.response.MchBangDanRankingResponse;
import com.nbhysj.coupon.model.response.MchCollectionResponse;
import com.nbhysj.coupon.model.response.MchCommentEntity;
import com.nbhysj.coupon.model.response.MchCouponResponse;
import com.nbhysj.coupon.model.response.MchGoodsBean;
import com.nbhysj.coupon.model.response.MchHomestayDetailsResponse;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.model.response.ScenicSpotResponse;
import com.nbhysj.coupon.presenter.HomestayPresenter;
import com.nbhysj.coupon.systembar.StatusBarCompat;
import com.nbhysj.coupon.systembar.StatusBarUtil;
import com.nbhysj.coupon.util.DateUtil;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.PopupWindowUtil;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.nbhysj.coupon.util.Tools;
import com.nbhysj.coupon.view.HotelDetailBannerView;
import com.nbhysj.coupon.view.RecyclerScrollView;
import com.nbhysj.coupon.view.StarBarView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @auther：hysj created on 2019/05/30
 * description：民宿详情
 */
public class HomestayDetailActivity extends BaseActivity<HomestayPresenter, HomestayModel> implements HomestayContract.View, RecyclerScrollView.OnScrollListener {

    @BindView(R.id.toolbar_space)
    View mToolbarSpace;
    @BindView(R.id.banner_hotel_detail)
    HotelDetailBannerView mBannerViewHotelDetail;
    @BindView(R.id.scrollview_homestay_detail)
    RecyclerScrollView mScrollViewHomeStayDetail;
    @BindView(R.id.ibtn_back)
    ImageButton mImgBtnBack;
    //收藏
    @BindView(R.id.img_collection)
    ImageView mImgCollection;
    @BindView(R.id.img_menu)
    ImageView mImageMenu;
    //设备
    @BindView(R.id.rv_homestay_equipment)
    RecyclerView mRvHomestayEquipment;
    @BindView(R.id.llyt_evaluate)
    LinearLayout mLlytEvalute;
    @BindView(R.id.rlyt_scenic_spots_detail_header)
    RelativeLayout mRlytScenicSpostsDetail;
    //民宿
    @BindView(R.id.rv_homestay_room)
    RecyclerView mRvHomestayRoom;
    //民宿名字
    @BindView(R.id.tv_homestay_name)
    TextView mTvHomestayName;
    //民宿
    @BindView(R.id.rv_hot_selling_homestay_nearby)
    RecyclerView mRvHotSellingHomestaysNearby;
    //民宿标签
    @BindView(R.id.flowlayout_homestay_label)
    TagFlowLayout mFlowlayoutHomestayLabel;
    //民宿价格
    @BindView(R.id.tv_homestay_price)
    TextView mTvHomestayPrice;
    //房东头像
    @BindView(R.id.image_landlor_avatar)
    ImageView mImgLandorAvatar;
    //房东名
    @BindView(R.id.tv_landlor_name)
    TextView mTvLandlorName;
    //实名认证
    @BindView(R.id.llyt_authentication_status)
    LinearLayout mLlytAuthenticationStatus;
    //平均确认时长
    @BindView(R.id.tv_confirm_time)
    TextView mTvConfirmTime;
    //房源数量
    @BindView(R.id.tv_house_resouce_num)
    TextView mTvHouseResouceNum;
    //预定成功率
    @BindView(R.id.tv_booking_success_rate)
    TextView mTvBookSuccessRate;
    //房东评分
    @BindView(R.id.tv_landlor_score)
    TextView mTvLandorScore;
    //房源信息
    @BindView(R.id.tv_house_info)
    TextView mTvHouseInfo;
    //房源信息
    @BindView(R.id.llyt_house_info)
    LinearLayout mLlytHouseInfo;
    //评分
    @BindView(R.id.tv_comment_score)
    TextView mTvCommentScore;
    //民宿评分
    @BindView(R.id.starbar_homestay_score)
    StarBarView mStarBarViewHomestay;
    //民宿景点评分
    @BindView(R.id.tv_service_score)
    TextView mTvServiceScore;
    //民宿服务进度条
    @BindView(R.id.pb_service_score_progressbar)
    ProgressBar mProgressBarServiceScore;
    //民宿设施评分
    @BindView(R.id.tv_facility_score)
    TextView mTvFacilityScore;
    //民宿设施进度条
    @BindView(R.id.pb_facility_score_progressbar)
    ProgressBar mProgressBarfacilityScore;
    //民宿卫生评分
    @BindView(R.id.tv_hygiene_score)
    TextView mTvHygieneScore;
    //民宿卫生评分进度条
    @BindView(R.id.pb_hygiene_score_progressbar)
    ProgressBar mProgressBarHygieneScore;
    //用户评论
    @BindView(R.id.llyt_user_comment)
    LinearLayout mLlytUserComment;
    //评论描述
    @BindView(R.id.tv_expand_comment_des)
    TextView mTvExpandCommentDes;
    //评论者用户名
    @BindView(R.id.tv_commentator_username)
    TextView mTvCommentatorUsername;
    //评论时间
    @BindView(R.id.tv_comment_time)
    TextView mTvCommentTime;
    //评论者头像
    @BindView(R.id.image_comment_user_avatar)
    CircleImageView mCircleImgCommentUserAvatar;
    //总评论数
    @BindView(R.id.tv_total_comment_num)
    TextView mTvTotalCommentNum;
    //静态地图
    @BindView(R.id.img_static_map)
    ImageView mImageStaticMap;
    //民宿地址
    @BindView(R.id.tv_homestay_address)
    TextView mTvHomestayAddress;
    //押金
    @BindView(R.id.tv_deposit)
    TextView mTvDeposit;
    //发票状态
    @BindView(R.id.tv_invoice_status)
    TextView mTvInvoiceStatus;
    //入住时间
    @BindView(R.id.tv_check_in_time)
    TextView mTvCheckInTime;
    //离店时间
    @BindView(R.id.tv_departure_time)
    TextView mTvDepartureTime;
    //接待时间

    @BindView(R.id.tv_reception_time)
    TextView mTvReceptionTime;
    //儿童政策
    @BindView(R.id.img_child_rule)
    ImageView mImgChildRule;
    //儿童政策
    @BindView(R.id.tv_child_rule)
    TextView mTvChildRule;
    //抽烟
    @BindView(R.id.img_smoking_rule)
    ImageView mImgSmokingRule;
    //抽烟
    @BindView(R.id.tv_smoking_rule)
    TextView mTvSmokingRule;
    //做饭
    @BindView(R.id.img_cook_rule)
    ImageView mImgCookRule;
    //做饭
    @BindView(R.id.tv_cook_rule)
    TextView mTvCookRule;
    //商业拍摄
    @BindView(R.id.img_commercial_shooting_rule)
    ImageView mImgCommercialShooting;
    //商业拍摄
    @BindView(R.id.tv_commercial_shooting_rule)
    TextView mTvCommercialShooting;
    //额外加人
    @BindView(R.id.img_extra_person_rule)
    ImageView mImgExtraPersonRule;
    //额外加人
    @BindView(R.id.tv_extra_person_rule)
    TextView mTvExtraPersonRule;
    //接待老人
    @BindView(R.id.img_elderly_rule)
    ImageView mImgElderlyRule;
    //接待老人
    @BindView(R.id.tv_elderly_rule)
    TextView mTvElderlyRule;
    //携带宠物
    @BindView(R.id.img_pets_rule)
    ImageView mImgPetsRule;
    //携带宠物
    @BindView(R.id.tv_pets_rule)
    TextView mTvPetsRule;
    //聚会
    @BindView(R.id.img_party_rule)
    ImageView mImgPartyRule;
    //聚会
    @BindView(R.id.tv_party_rule)
    TextView mTvPartyRule;
    //额外加床
    @BindView(R.id.img_extra_bed_rule)
    ImageView mImgExtraBedRule;
    //额外加床
    @BindView(R.id.tv_extra_bed_rule)
    TextView mTvExtraBedRule;
    //接待贵宾
    @BindView(R.id.img_receive_foreign_guests_rule)
    ImageView mImgReceiveForeignGuestsRule;
    //接待贵宾
    @BindView(R.id.tv_receive_foreign_guests_rule)
    TextView mTvReceiveForeignGuestsRule;
    private PopupWindow mPopupWindow;

    private int height;
    private List<ImageView> viewList;
    private List<String> bannerList;
    //设备列表
    List<MchHomestayDetailsResponse.ServiceEntity> facilityList;

    private List<HotelBean> homestayResourcesList;

    //民宿商品列表
    private List<MchGoodsBean> mchHomestayGoodsList;

    private MchHomestayDetailsResponse homestayDetailsResponse;

    private MchHomestayDetailsResponse.MchDetailsEntity mchDetailsEntity;
    //商户id
    private int mchId;

    //商户名
    private String mchName;
    ///民宿预定
    private HomestayReservationAdapter homestayReservationAdapter;

    //民宿房源
    private HomestayResourcesAdapter homestayResourcesAdapter;

    //民宿设备
    private HomestayEquipmentAdapter homestayEquipmentAdapter;

    //用户评论列表
    private List<MchCommentEntity> commentList;

    //房源详情h5
    private String houseDetailsH5Url;

    //设备详情h5
    private String allFacilityH5Url;

    //收藏状态
    private int collectionStatus;

    //纬度
    private String mLatitude;

    //经度
    private String mLongitude;

    List<MchCouponResponse> mchCouponResponseList;

    private MchCouponReceiveDialog mchCouponReceiveDialog;

    //优惠券选择位置
    private int mGroupPosition;

    //优惠券选择位置
    private int mChildPosition;

    //优惠券id
    private int couponId;

    @Override
    public int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusBarCompat.translucentStatusBar(this, false);
        //修改状态栏字体颜色
        StatusBarUtil.setImmersiveStatusBar(this, true);
        return R.layout.activity_homestay_details;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        mchId = getIntent().getIntExtra("mchId", 0);

        if (facilityList == null) {

            facilityList = new ArrayList<>();

        } else {

            facilityList.clear();
        }

        //民宿列表
        if (homestayResourcesList == null) {

            homestayResourcesList = new ArrayList<>();
        } else {
            homestayResourcesList.clear();
        }

        //民宿商品列表
        if (mchHomestayGoodsList == null) {

            mchHomestayGoodsList = new ArrayList<>();
        } else {
            mchHomestayGoodsList.clear();
        }

        if (bannerList == null) {
            bannerList = new ArrayList<>();
        } else {
            bannerList.clear();
        }

        if (commentList == null) {

            commentList = new ArrayList<>();
        } else {
            commentList.clear();
        }
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


        if (bannerList.size() > 0) {

            for (int i = 0; i < bannerList.size(); i++) {
                ImageView image = new ImageView(HomestayDetailActivity.this);
                image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                //设置显示格式
                image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                viewList.add(image);
            }
        }

        mBannerViewHotelDetail.startLoop(false);
        // mBannerViewHotelDetail.setViewList(HomestayDetailActivity.this, viewList, bannerList);

        LinearLayoutManager linearLayout = new LinearLayoutManager(HomestayDetailActivity.this);
        linearLayout.setOrientation(linearLayout.VERTICAL);
        mRvHomestayRoom.setLayoutManager(linearLayout);
        homestayReservationAdapter = new HomestayReservationAdapter(HomestayDetailActivity.this, new HomestayReservationAdapter.HotelRoomItemListener() {
            @Override
            public void setHotelRoomItemListener(int position) {

                Intent intent = new Intent();
                intent.setClass(HomestayDetailActivity.this, HotelOrderActivity.class);
                MchGoodsBean mchGoodsBean = mchHomestayGoodsList.get(position);
                int goodId = mchGoodsBean.getId();
                intent.putExtra("goodsId",goodId);
                intent.putExtra("mchName",mchName);
                startActivity(intent);
            }
        });
        homestayReservationAdapter.setHomestayReservationList(mchHomestayGoodsList);
        mRvHomestayRoom.setAdapter(homestayReservationAdapter);

        //获取顶部图片高度后，设置滚动监听
        ViewTreeObserver vto = mBannerViewHotelDetail.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mBannerViewHotelDetail.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                height = mBannerViewHotelDetail.getHeight();
                mBannerViewHotelDetail.getWidth();

                mScrollViewHomeStayDetail.setScrolListener(HomestayDetailActivity.this);
            }
        });

        mLlytEvalute.getBackground().setAlpha(85);

        getMchHomestayDetail();
    }

    @Override
    public void initData() {

        //看了这个房的人还看了
        GridLayoutManager layoutManager = new GridLayoutManager(HomestayDetailActivity.this, 2);
        layoutManager.setOrientation(layoutManager.VERTICAL);
        mRvHotSellingHomestaysNearby.setLayoutManager(layoutManager);
        mRvHotSellingHomestaysNearby.addItemDecoration(new RecyclerViewItemDecoration(Tools.dip2px(HomestayDetailActivity.this, 10)));
        homestayResourcesAdapter = new HomestayResourcesAdapter(HomestayDetailActivity.this);
        homestayResourcesAdapter.setHomestayResourcesList(homestayResourcesList);
        mRvHotSellingHomestaysNearby.setAdapter(homestayResourcesAdapter);

        //设备列表
        GridLayoutManager homestayEquipmentLayoutManager = new GridLayoutManager(HomestayDetailActivity.this, 4);
        homestayEquipmentLayoutManager.setOrientation(homestayEquipmentLayoutManager.VERTICAL);
        mRvHomestayEquipment.setLayoutManager(homestayEquipmentLayoutManager);
        homestayEquipmentAdapter = new HomestayEquipmentAdapter(HomestayDetailActivity.this, new HomestayEquipmentAdapter.AllFacilityItemListener() {
            @Override
            public void setAllFacilityItemListenerCallback()
            {
                if(!TextUtils.isEmpty(houseDetailsH5Url)) {

                    Intent intent = new Intent();
                    intent.putExtra("url", allFacilityH5Url);
                    intent.putExtra("title", Constants.All_FACILITY_H5_TITEL);
                    intent.setClass(HomestayDetailActivity.this, WebActivity.class);
                    startActivity(intent);

                }
            }
        });
        homestayEquipmentAdapter.setEquipmentList(facilityList);
        mRvHomestayEquipment.setAdapter(homestayEquipmentAdapter);

    }

    /**
     * 为RecyclerView增加间距
     * 预设2列，如果是3列，则左右值不同
     */
    public class RecyclerViewItemDecoration extends RecyclerView.ItemDecoration {
        private int space = 0;
        private int pos;

        public RecyclerViewItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

            outRect.top = space;

            //该View在整个RecyclerView中位置。
            pos = parent.getChildAdapterPosition(view);

            //取模

            //两列的左边一列
            if (pos % 2 == 0) {
                outRect.left = space;
                outRect.right = space / 2;
            }

            //两列的右边一列
            if (pos % 2 == 1) {
                outRect.left = space / 2;
                outRect.right = space;
            }
        }
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @OnClick({R.id.ibtn_back,R.id.llyt_house_info,R.id.img_collection,R.id.img_static_map,R.id.img_menu,R.id.img_scenic_spot_forward,R.id.llyt_evaluate,R.id.tv_total_comment_num,R.id.tv_coupon_receive})
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.ibtn_back:

                HomestayDetailActivity.this.finish();

                break;
            case R.id.llyt_house_info:

                if(!TextUtils.isEmpty(houseDetailsH5Url)) {

                    intent.putExtra("url", houseDetailsH5Url);
                    intent.putExtra("title", Constants.HOUSE_INFO_H5_TITEL);
                    intent.setClass(HomestayDetailActivity.this, WebActivity.class);
                    startActivity(intent);

                }

                break;
            case R.id.img_collection:

                mchCollection();

                break;
            case R.id.img_static_map:
                    if(!TextUtils.isEmpty(mLatitude) &&
                            !TextUtils.isEmpty(mLongitude) && !TextUtils.isEmpty(mchName))
                    {
                        if (isInstalled("com.autonavi.minimap"))
                        {
                            openGaodeMapToGuide(mchName,mLatitude, mLongitude);

                        } else if(isInstalled("com.baidu.BaiduMap"))
                        {
                            goToBaiduMap(mchName,mLatitude, mLongitude);

                        } else {
                            showToast(HomestayDetailActivity.this, "请先安装高德地图客户端");
                        }

                }
                break;
            case R.id.img_menu:
                showPopupWindow(mImageMenu);
                break;
            case R.id.img_scenic_spot_forward:

                ShareOprateDialog shareOprateDialog = new ShareOprateDialog(HomestayDetailActivity.this, new ShareOprateDialog.OnSharePlatformItemClickListener() {
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
            case R.id.llyt_evaluate:

                intent.setClass(HomestayDetailActivity.this,MchCommentActivity.class);
                intent.putExtra("mchId",mchId);
                startActivity(intent);
                break;
            case R.id.tv_total_comment_num:
                intent.setClass(HomestayDetailActivity.this,MchCommentActivity.class);
                intent.putExtra("mchId",mchId);
                startActivity(intent);
                break;
            case R.id.tv_coupon_receive:

                queryMchCouponList();

                break;
            default:
                break;

        }
    }

    @Override
    public void getHomestayHomePageResult(BackResult<ScenicSpotHomePageResponse> res) {

    }

    @Override
    public void findHomestayByCateResult(BackResult<ScenicSpotResponse> res) {

    }

    @Override
    public void getHomestayBangDanRankingResult(BackResult<MchBangDanRankingResponse> res) {

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
                showToast(HomestayDetailActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getMchHomestayDetailResult(BackResult<MchHomestayDetailsResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    homestayDetailsResponse = res.getData();

                    mchDetailsEntity = homestayDetailsResponse.getMchDetails();
                    MchHomestayDetailsResponse.MchQuestionEntity mchQuestionEntity = homestayDetailsResponse.getMchQuestion(); //问题
                    bannerList = mchDetailsEntity.getRecommendPhoto();
                    double consumePrice = mchDetailsEntity.getConsumePrice();
                    String buildInfo = mchDetailsEntity.getBuildInfo();
                    MchHomestayDetailsResponse.LandlorEntity landlorEntity = homestayDetailsResponse.getLandlor();
                    String nickName = landlorEntity.getNickname();
                    int authenticationStatus = landlorEntity.getAuthenticationStatus(); //实名认证
                    String avatarUrl = landlorEntity.getAvatar();  //头像
                    int confirmTime = landlorEntity.getConfirmTime(); //平均确认时间
                    int homestayRoomNum = landlorEntity.getHomestayRoomNum(); //民宿房源数
                    int landorScore = landlorEntity.getScore();
                    int bookingSuccessRate = landlorEntity.getBookingSuccess();
                    mLatitude = mchDetailsEntity.getLatitude();
                    mLongitude = mchDetailsEntity.getLongitude();
                    if (bannerList.size() > 0) {

                        for (int i = 0; i < bannerList.size(); i++) {
                            ImageView image = new ImageView(HomestayDetailActivity.this);
                            image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                            //设置显示格式
                            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            viewList.add(image);
                        }
                    }

                    mBannerViewHotelDetail.startLoop(false);
                    mBannerViewHotelDetail.setViewList(HomestayDetailActivity.this, viewList, bannerList);

                    mTvHomestayPrice.setText(Tools.getTwoDecimalPoint(consumePrice));

                    mchHomestayGoodsList = homestayDetailsResponse.getMchGoods();     //酒店商品展示列表
                    mchName = mchDetailsEntity.getMchName();
                    //A级
                    int level = mchDetailsEntity.getLevel();
                    //商户地址
                    String address = mchDetailsEntity.getAddress();
                    List<MchHomestayDetailsResponse.TagsEntity> tagsEntityList = mchDetailsEntity.getTags();

                    if (tagsEntityList != null) {
                        if (tagsEntityList.size() > 0) {
                            mFlowlayoutHomestayLabel.setAdapter(new TagAdapter<MchHomestayDetailsResponse.TagsEntity>(tagsEntityList) {

                                @Override
                                public View getView(FlowLayout parent, int position, MchHomestayDetailsResponse.TagsEntity tagsEntity) {
                                    LayoutInflater mInflater = LayoutInflater.from(mContext);
                                    TextView tagName = (TextView) mInflater.inflate(R.layout.layout_flowlayout_tag_homestay,
                                            mFlowlayoutHomestayLabel, false);
                                    tagName.setText(tagsEntity.getTitle());
                                    return tagName;
                                }
                            });
                        }
                    }

                   // String content = "<font color=\"#FF4970\" size='1px' >" + address + "</font>";
                    if(!TextUtils.isEmpty(mchName))
                    {
                        mTvHomestayName.setText(mchName);
                    }

                    if(mchHomestayGoodsList != null)
                    {
                        homestayReservationAdapter.setHomestayReservationList(mchHomestayGoodsList);
                        homestayReservationAdapter.notifyDataSetChanged();
                    }

                    mTvBookSuccessRate.setText(bookingSuccessRate + "%");
                    mTvConfirmTime.setText(String.valueOf(confirmTime) + "分钟");
                    mTvLandlorName.setText(nickName);
                    mTvHouseResouceNum.setText(String.valueOf(homestayRoomNum));
                    mTvLandorScore.setText(String.valueOf(landorScore) + "分");
                    GlideUtil.loadImage(HomestayDetailActivity.this, avatarUrl, mImgLandorAvatar);

                    //实名认证
                    if (authenticationStatus == 0) {
                        mLlytAuthenticationStatus.setVisibility(View.GONE);

                    } else if (authenticationStatus == 1) {
                        mLlytAuthenticationStatus.setVisibility(View.VISIBLE);
                    }

                    MchHomestayDetailsResponse.ContentEntity contentEntity = mchDetailsEntity.getContent();
                    String houseInfo = contentEntity.getHouseInfo();
                    MchHomestayDetailsResponse.FacilityServiceEntity facilityServiceEntity = contentEntity.getService();
                    facilityList = facilityServiceEntity.getFacility();
                    allFacilityH5Url = facilityServiceEntity.getAllFacility();
                    houseDetailsH5Url = contentEntity.getHouseDetails();

                    //房源信息非空
                    if (!TextUtils.isEmpty(houseInfo)) {
                        mLlytHouseInfo.setVisibility(View.VISIBLE);
                        mTvHouseInfo.setText(houseInfo);

                    } else {
                        mLlytHouseInfo.setVisibility(View.GONE);
                    }

                    //设备详情
                    homestayEquipmentAdapter.setEquipmentList(facilityList);
                    homestayEquipmentAdapter.notifyDataSetChanged();

                    //综合评价
                    MchHomestayDetailsResponse.CommentEntity commentEntity = homestayDetailsResponse.getComment();
                    commentList = commentEntity.getComment();

                    MchHomestayDetailsResponse.ScoreEntity scoreEntity = commentEntity.getScore();
                    float commentScore = scoreEntity.getCommentScore();
                    double commentScore1 = scoreEntity.getCommentScore1();
                    double commentScore2 = scoreEntity.getCommentScore2();
                    double commentScore3 = scoreEntity.getCommentScore3();

                    mTvCommentScore.setText(String.valueOf(commentScore));
                    mStarBarViewHomestay.setStarMark(commentScore);

                    mTvServiceScore.setText(String.valueOf(commentScore1));
                    mProgressBarServiceScore.setProgress((int) commentScore1);
                    mTvFacilityScore.setText(String.valueOf(commentScore2));
                    mProgressBarfacilityScore.setProgress((int) commentScore2);
                    mTvHygieneScore.setText(String.valueOf(commentScore3));
                    mProgressBarHygieneScore.setProgress((int) commentScore3);

                    if (commentList != null) {

                        mLlytUserComment.setVisibility(View.VISIBLE);
                        if (commentList != null) {
                            int commentNum = commentList.size();
                            if (commentNum > 0) {
                                MchCommentEntity homestayCommentEntity = commentList.get(0);
                                String commentContent = homestayCommentEntity.getContent();

                                CommentUserEntity commentUser = homestayCommentEntity.getUser();
                                mTvExpandCommentDes.setText(commentContent);
                                String commentUserAvater = commentUser.getAvater();
                                String commentUserNickName = commentUser.getNickname();
                                long cTime = homestayCommentEntity.getCtime();
                                String commentTime = DateUtil.transferLongToDate(DateUtil.sDateYMDFormat, cTime);
                                mTvCommentTime.setText(commentTime);
                                mTvCommentatorUsername.setText(commentUserNickName);
                                GlideUtil.loadImage(HomestayDetailActivity.this, commentUserAvater, mCircleImgCommentUserAvatar);
                                mTvTotalCommentNum.setText("查看详查看全部" + commentNum + "条评论 >");


                            }
                        }

                    } else {
                        mLlytUserComment.setVisibility(View.GONE);
                    }


                    String staticMapUrl = mchDetailsEntity.getStaticMap();
                    GlideUtil.loadImage(HomestayDetailActivity.this, staticMapUrl, mImageStaticMap);

                    mTvHomestayAddress.setText(address);

                    String checkinTime = mchDetailsEntity.getCheckinTime();
                    String leaveTime = mchDetailsEntity.getLeaveTime();

                    if (contentEntity != null) {
                        MchHomestayDetailsResponse.NoticeEntity noticeEntity = contentEntity.getNotice();
                        String depositInfo = noticeEntity.getDepositInfo(); //押金
                        int invoiceStatus = noticeEntity.getInvoiceStatus();
                        String receptionInfo = noticeEntity.getReceptionInfo();
                        mTvCheckInTime.setText("入住日" + checkinTime + "后");
                        mTvDepartureTime.setText("离店日" + leaveTime + "之前");
                        mTvReceptionTime.setText(receptionInfo);

                        //发票状态
                        if (invoiceStatus == 0) {
                            mTvInvoiceStatus.setText(getResources().getString(R.string.str_non_invoiceable));
                        } else if (invoiceStatus == 1) {
                            mTvInvoiceStatus.setText(getResources().getString(R.string.str_invoiceable));
                        }

                        //押金
                        if(!TextUtils.isEmpty(depositInfo))
                        {
                            mTvDeposit.setText("线下支付给房东" + depositInfo);
                        }

                        int ruleChild = noticeEntity.getRuleChild(); //儿童政策
                        int ruleSmoking = noticeEntity.getRuleSmoking(); //抽烟
                        int ruleCook = noticeEntity.getRuleCook(); //做饭
                        int rulePhotograph = noticeEntity.getRulePhotograph(); //商业拍摄
                        int rulePerson = noticeEntity.getRulePerson();  //额外加人
                        int ruleElderly = noticeEntity.getRuleElderly(); //接待老人
                        int rulePets = noticeEntity.getRulePets(); // 携带宠物
                        int ruleParty = noticeEntity.getRuleParty();  //聚会
                        int ruleBed = noticeEntity.getRuleBed();     //额外加床
                        int ruleForeigner = noticeEntity.getRuleForeigner(); //接待外宾

                        //接待儿童
                        if (ruleChild == 1) {

                            mTvChildRule.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线

                            mTvChildRule.setTextColor(getResources().getColor(R.color.color_text_gray24));
                            mImgChildRule.setImageResource(R.mipmap.icon_homestay_rule_false);
                        } else if (ruleChild == 0) {

                            mTvChildRule.setTextColor(getResources().getColor(R.color.color_text_black7));
                            mImgChildRule.setImageResource(R.mipmap.icon_homestay_rule_true);
                        }

                        //抽烟
                        if (ruleSmoking == 1) {

                            mTvSmokingRule.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线

                            mTvSmokingRule.setTextColor(getResources().getColor(R.color.color_text_gray24));
                            mImgSmokingRule.setImageResource(R.mipmap.icon_homestay_rule_false);
                        } else if (ruleSmoking == 0) {

                            mTvSmokingRule.setTextColor(getResources().getColor(R.color.color_text_black7));
                            mImgSmokingRule.setImageResource(R.mipmap.icon_homestay_rule_true);
                        }

                        //做饭
                        if (ruleCook == 1) {

                            mTvCookRule.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线

                            mTvCookRule.setTextColor(getResources().getColor(R.color.color_text_gray24));
                            mImgCookRule.setImageResource(R.mipmap.icon_homestay_rule_false);
                        } else if (ruleCook == 0) {
                            mImgCookRule.setImageResource(R.mipmap.icon_homestay_rule_true);
                            mTvCookRule.setTextColor(getResources().getColor(R.color.color_text_black7));
                        }

                        //商业拍摄
                        if (rulePhotograph == 1) {
                            mImgCommercialShooting.setImageResource(R.mipmap.icon_homestay_rule_false);
                            mTvCommercialShooting.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线

                            mTvCookRule.setTextColor(getResources().getColor(R.color.color_text_gray24));

                        } else if (rulePhotograph == 0) {
                            mImgCommercialShooting.setImageResource(R.mipmap.icon_homestay_rule_true);
                            mTvCommercialShooting.setTextColor(getResources().getColor(R.color.color_text_black7));
                        }

                        //额外加人
                        if (rulePerson == 1) {

                            mTvExtraPersonRule.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线

                            mTvExtraPersonRule.setTextColor(getResources().getColor(R.color.color_text_gray24));
                            mImgExtraPersonRule.setImageResource(R.mipmap.icon_homestay_rule_false);

                        } else if (rulePerson == 0) {
                            mImgExtraPersonRule.setImageResource(R.mipmap.icon_homestay_rule_true);
                            mTvExtraPersonRule.setTextColor(getResources().getColor(R.color.color_text_black7));
                        }

                        //接待老人
                        if (ruleElderly == 1) {

                            mTvElderlyRule.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线

                            mTvElderlyRule.setTextColor(getResources().getColor(R.color.color_text_gray24));

                            mImgElderlyRule.setImageResource(R.mipmap.icon_homestay_rule_false);
                        } else if (ruleElderly == 0) {
                            mImgElderlyRule.setImageResource(R.mipmap.icon_homestay_rule_true);
                            mTvElderlyRule.setTextColor(getResources().getColor(R.color.color_text_black7));
                        }

                        //携带宠物
                        if (rulePets == 1) {

                            mTvPetsRule.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线

                            mTvPetsRule.setTextColor(getResources().getColor(R.color.color_text_gray24));
                            mImgPetsRule.setImageResource(R.mipmap.icon_homestay_rule_false);
                        } else if (rulePets == 0) {
                            mImgPetsRule.setImageResource(R.mipmap.icon_homestay_rule_true);
                            mTvPetsRule.setTextColor(getResources().getColor(R.color.color_text_black7));
                        }

                        //聚会
                        if (ruleParty == 1) {

                            mImgPartyRule.setImageResource(R.mipmap.icon_homestay_rule_false);
                            mTvPartyRule.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
                            mTvPartyRule.setTextColor(getResources().getColor(R.color.color_text_gray24));
                            mImgPartyRule.setImageResource(R.mipmap.icon_homestay_rule_false);
                        } else if (ruleParty == 0) {

                            mImgPartyRule.setImageResource(R.mipmap.icon_homestay_rule_true);
                            mTvPartyRule.setTextColor(getResources().getColor(R.color.color_text_black7));
                            mImgPartyRule.setImageResource(R.mipmap.icon_homestay_rule_true);
                        }

                        //额外加床
                        if (ruleBed == 1) {

                            mTvExtraBedRule.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线

                            mTvExtraBedRule.setTextColor(getResources().getColor(R.color.color_text_gray24));
                            mImgExtraBedRule.setImageResource(R.mipmap.icon_homestay_rule_false);
                        } else if (ruleBed == 0) {

                            mTvExtraBedRule.setTextColor(getResources().getColor(R.color.color_text_black7));
                            mImgExtraBedRule.setImageResource(R.mipmap.icon_homestay_rule_true);
                        }

                        //接待外宾
                        if (ruleForeigner == 1) {

                            mTvReceiveForeignGuestsRule.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线

                            mTvReceiveForeignGuestsRule.setTextColor(getResources().getColor(R.color.color_text_gray24));
                            mImgReceiveForeignGuestsRule.setImageResource(R.mipmap.icon_homestay_rule_false);
                        } else if (ruleForeigner == 0) {

                            mTvReceiveForeignGuestsRule.setTextColor(getResources().getColor(R.color.color_text_black7));
                            mImgReceiveForeignGuestsRule.setImageResource(R.mipmap.icon_homestay_rule_true);
                        }
                    }

                    //附近热销酒店
                    homestayResourcesList = homestayDetailsResponse.getNearbyHotel();
                    homestayResourcesAdapter.setHomestayResourcesList(homestayResourcesList);
                    homestayResourcesAdapter.notifyDataSetChanged();


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
                showToast(HomestayDetailActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void queryMchCouponListResult(BackResult<List<MchCouponResponse>> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    mchCouponResponseList = res.getData();
                    if(mchCouponResponseList != null) {
                        if (mchCouponReceiveDialog == null)
                        {
                            mchCouponReceiveDialog = new MchCouponReceiveDialog(mchCouponResponseList, new MchCouponReceiveDialog.MchCouponReceiveListener() {

                                @Override
                                public void setMchCouponReceiveCallback(int groupPosition, int childPosition, CouponsBean couponsBean) {
                                    mGroupPosition = groupPosition;
                                    mChildPosition = childPosition;
                                    couponId = couponsBean.getId();
                                    getCoupon();
                                }

                                @Override
                                public void setCouponListRefreshListener(RefreshLayout refreshLayout) {

                                    //   queryMchCouponList();
                                    // mchCouponReceiveDialog.setSmartRefreshLayoutLoadMoreFinish();
                                }
                            });

                            mchCouponReceiveDialog.show(getFragmentManager(), "优惠券领取");

                        } else {
                            mchCouponReceiveDialog.setAlbumCollectList(mchCouponResponseList);
                            mchCouponReceiveDialog.show();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(HomestayDetailActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getCouponResult(BackResult<CouponsGetBean> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    CouponsGetBean couponsGetBean = res.getData();
                    int canGetAgainStatus = couponsGetBean.getCanGetAgainStatus();
                    MchCouponResponse mchCouponResponse = mchCouponResponseList.get(mGroupPosition);
                    CouponsBean couponsBean = mchCouponResponse.getCoupons().get(mChildPosition);
                    couponsBean.setCanGetAgainStatus(canGetAgainStatus);

                    mchCouponReceiveDialog.setAlbumCollectList(mchCouponResponseList);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(HomestayDetailActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(HomestayDetailActivity.this, Constants.getResultMsg(msg));
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
                if(itemStr.equals(backHomePage))
                {
                    if(appManager != null)
                    {
                        appManager.finishActivity(MainActivity.class);
                    }
                    //  EventBus.getDefault().post("backHomePage");

                } else if(itemStr.equals(backMyCollection))
                {
                    //  intent.setClass(ScenicSpotDetailActivity.this, MainActivity.class);
                    //intent.putExtra("currentItem",3);

                    if(appManager != null)
                    {
                        appManager.finishActivity(MainActivity.class);
                    }

                    //  EventBus.getDefault().post("backMyCollection");

                } else if(itemStr.equals(backMyOrder))
                {
                    intent.setClass(HomestayDetailActivity.this, MyOrderActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                } else if(itemStr.equals(backMyMessage))
                {
                    intent.setClass(HomestayDetailActivity.this, MessageActivity.class);
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

    @Override
    public void onScroll(int y) {
        if (y <= height && y >= 0) {
            float scale = (float) y / height;
            float alpha = (255 * scale);

            mRlytScenicSpostsDetail.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
            mToolbarSpace.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
            mImgBtnBack.setImageDrawable(getResources().getDrawable(R.mipmap.icon_left_arrow_black));
            if(collectionStatus == 0) {
                mImgCollection.setImageResource(R.mipmap.icon_black_collection);
            }
            mImageMenu.setImageDrawable(getResources().getDrawable(R.mipmap.icon_black_menu_more));
            if (y <= 300) {
                mImgBtnBack.setImageDrawable(getResources().getDrawable(R.mipmap.icon_left_arrow_white));
                if(collectionStatus == 0) {
                    mImgCollection.setImageDrawable(getResources().getDrawable(R.mipmap.icon_white_collection));
                }
                mImageMenu.setImageDrawable(getResources().getDrawable(R.mipmap.icon_white_menu_more));
                mRlytScenicSpostsDetail.setBackgroundColor(Color.argb(0, 0, 0, 0));
                mToolbarSpace.setBackgroundColor(Color.argb(0, 0, 0, 0));
            }
        }
    }

    //获取酒店详情
    public void getMchHomestayDetail() {

        if(validateInternet()) {
            showProgressDialog(HomestayDetailActivity.this);
            mPresenter.getMchHomestayDetail(mchId);
        }
    }

    //商户收藏
    public void mchCollection(){

        if(validateInternet()) {
            showProgressDialog(HomestayDetailActivity.this);
            MchCollectionRequest mchCollectionRequest = new MchCollectionRequest();
            mchCollectionRequest.setDataId(mchId);
            mPresenter.mchCollection(mchCollectionRequest);
        }
    }

    //查询商户券列表
    public void queryMchCouponList(){

        if (validateInternet()) {
            showProgressDialog(HomestayDetailActivity.this);
            mPresenter.queryMchCouponList(mchId);
        }
    }

    //获取优惠券
    public void getCoupon(){

        if (validateInternet()) {
            showProgressDialog(HomestayDetailActivity.this);
            mPresenter.getCoupon(couponId);
        }
    }
}
