package com.nbhysj.coupon.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
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
import com.nbhysj.coupon.adapter.HotelDetailRoomAdapter;
import com.nbhysj.coupon.adapter.HotelNearbyAdapter;
import com.nbhysj.coupon.adapter.MchDetailCouponListAdapter;
import com.nbhysj.coupon.adapter.NearbyHotSellHotelsAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.common.Enum.SharePlatformEnum;
import com.nbhysj.coupon.contract.HotelContract;
import com.nbhysj.coupon.dialog.HotelDetailsSupplementDialog;
import com.nbhysj.coupon.dialog.MchCouponReceiveDialog;
import com.nbhysj.coupon.dialog.ShareOprateDialog;
import com.nbhysj.coupon.framework.Net;
import com.nbhysj.coupon.model.HotelModel;
import com.nbhysj.coupon.model.request.MchCollectionRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CouponsBean;
import com.nbhysj.coupon.model.response.CouponsGetBean;
import com.nbhysj.coupon.model.response.HotelBean;
import com.nbhysj.coupon.model.response.HotelMchDetailsResponse;
import com.nbhysj.coupon.model.response.LabelEntity;
import com.nbhysj.coupon.model.response.MchCateListResponse;
import com.nbhysj.coupon.model.response.MchCollectionResponse;
import com.nbhysj.coupon.model.response.MchCommentEntity;
import com.nbhysj.coupon.model.response.HotelOrderInitResponse;
import com.nbhysj.coupon.model.response.MchBangDanRankingResponse;
import com.nbhysj.coupon.model.response.MchCouponResponse;
import com.nbhysj.coupon.model.response.MchDetailsResponse;
import com.nbhysj.coupon.model.response.MchGoodsBean;
import com.nbhysj.coupon.model.response.NearbyTypeResponse;
import com.nbhysj.coupon.model.response.OrderSubmitResponse;
import com.nbhysj.coupon.model.response.QueryByTicketResponse;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.model.response.ScenicSpotResponse;
import com.nbhysj.coupon.model.response.UseCouponTicketResponse;
import com.nbhysj.coupon.pay.wechat.PayConstants;
import com.nbhysj.coupon.presenter.HotelPresenter;
import com.nbhysj.coupon.systembar.StatusBarCompat;
import com.nbhysj.coupon.systembar.StatusBarUtil;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.PopupWindowUtil;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.nbhysj.coupon.util.Tools;
import com.nbhysj.coupon.view.HotelDetailBannerView;
import com.nbhysj.coupon.view.RecyclerScrollView;
import com.nbhysj.coupon.view.StarBarView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
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
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/05/09
 * description：酒店详情
 */
public class HotelDetailsActivity extends BaseActivity<HotelPresenter, HotelModel> implements HotelContract.View, RecyclerScrollView.OnScrollListener {
    @BindView(R.id.toolbar_space)
    View mToolbarSpace;
    @BindView(R.id.banner_hotel_detail)
    HotelDetailBannerView mBannerViewHotelDetail;
    //酒店房间筛选
    @BindView(R.id.rv_hotel_room)
    RecyclerView mRvHotelRoom;
    @BindView(R.id.flowlayout_hotel_comment_label)
    TagFlowLayout mTagFlowHotelCommentLabel;
    @BindView(R.id.rv_user_comment)
    RecyclerView mRvUserComment;
    @BindView(R.id.rv_hotel_periphery_recommendation)
    RecyclerView mRvHotelPeriphery;
    @BindView(R.id.tv_delicious_food)
    TextView mTvDeliciousFood;
    @BindView(R.id.tv_entertainment)
    TextView mTvEntertainment;
    @BindView(R.id.tv_scenic_spot)
    TextView mTvScenicSpot;
    @BindView(R.id.view_delicious_food)
    View mViewDeliciousFood;
    @BindView(R.id.view_entertainment)
    View mViewEntertainment;
    @BindView(R.id.view_scenic_spot)
    View mViewScenicSpot;
    //附近热销酒店
    @BindView(R.id.rv_hot_selling_hotels_nearby)
    RecyclerView mRvHotSellingHotelsNearby;
    //酒店名
    @BindView(R.id.tv_hotel_name)
    TextView mHotelName;
    //酒店评分
    @BindView(R.id.tv_hotel_comment_score)
    TextView mTvHotelCommentScore;
    //星级评分
    @BindView(R.id.starbar_store)
    StarBarView mStarBarView;
    //评论数
    @BindView(R.id.tv_comment_num)
    TextView mTvCommentNum;
    @BindView(R.id.tv_user_comment_num)
    TextView mTvUserCommentNum;
    //商户地址
    @BindView(R.id.tv_mch_address)
    TextView mTvMchAddress;
    @BindView(R.id.scrollview_scenic_spot_detail)
    RecyclerScrollView mScrollViewScenicSpotDetail;
    @BindView(R.id.rlyt_scenic_spots_detail_header)
    RelativeLayout mRlytScenicSpostsDetail;
    @BindView(R.id.ibtn_back)
    ImageButton mImgBtnBack;
    //收藏
    @BindView(R.id.img_collection)
    ImageView mImgCollection;
    //菜单
    @BindView(R.id.img_menu)
    ImageView mImageMenu;
    //住客评分
    @BindView(R.id.tv_occupant_score)
    TextView mTvOccupantScore;
    //服务分
    @BindView(R.id.tv_service_score)
    TextView mTvServiceScore;
    //设施分
    @BindView(R.id.tv_facilities_score)
    TextView mTvFacilitiesScore;
    //卫生分
    @BindView(R.id.tv_hygiene_score)
    TextView mTvHygieneScore;
    //评分星级
    @BindView(R.id.starbar_occupant_score)
    StarBarView mStarBarViewOccupantScore;
    //问题内容
    @BindView(R.id.tv_question_content)
    TextView mTvQuestionContent;
    //回答内容
    @BindView(R.id.tv_answer_content)
    TextView mTvAnswerContent;
    //设备详情标签
    @BindView(R.id.flowlayout_device)
    TagFlowLayout mTagFlowLayoutDevice;
    //酒店入离时间
    @BindView(R.id.tv_hotel_checkin_and_leave_time)
    TextView mTvHotelCheckinAndLeaveTime;
    //膳食安排
    @BindView(R.id.tv_diet_info)
    TextView mTvDietInfo;
    //是否允许携带宠物
    @BindView(R.id.tv_pets_status)
    TextView mTvPetsStatus;
    //附近热销酒店
    @BindView(R.id.tv_nearby_sell_well_hotel_num)
    TextView mNearbySellWellHotelNum;
    //优惠券领取
    @BindView(R.id.rv_coupon_receive_tag)
    RecyclerView mRvCouponReceiveTag;

    @BindView(R.id.rlyt_coupon)
    RelativeLayout mRlytCoupon;

    @BindView(R.id.img_scenic_spot_forward)
    ImageView mImgScenicSpotForward;
    private List<ImageView> viewList;
    private List<String> bannerList;

    private HotelMchDetailsResponse mchDetailsResponse;

    private HotelMchDetailsResponse.MchDetailsEntity mchDetailsEntity;

    //评论列表
    List<MchCommentEntity> commentList;

    //用户评论标签
    List<LabelEntity> labelEntityList;
    //商户id
    private static int mchId;
    //商户名
    private String mchName;

    private TagAdapter tagAdapter;
    private int height;

    //酒店房间类型适配器
    HotelDetailRoomAdapter hotelMealDetailAdapter;

    //酒店商品列表
    private List<MchGoodsBean> mchHotelGoodsList;

    //private HotelDetailUserCommentAdapter hotelDetailUserCommentAdapter;

    //酒店附近
    private HotelNearbyAdapter hotelNearbyAdapter;

    private NearbyHotSellHotelsAdapter nearbyHotSellHotelsAdapter;

    //附近
    private HotelMchDetailsResponse.NearbyEntity nearbyEntity;

    //附近列表
    private List<NearbyTypeResponse> nearbyTypList;

    List<HotelBean> hotSellingHotelsNearbyList;

    private HotelDetailsSupplementDialog hotelDetailsSupplementDialog;

    //设施详情h5
    private String allFacilityDetailsH5Url;

    //订房必读h5
    private String bookingInformationDetailsH5Url;

    //纬度
    private String latitude;
    //经度
    private String longitude;

    //收藏状态
    private int collectionStatus;

    private PopupWindow mPopupWindow;

    private MchDetailCouponListAdapter mchDetailCouponListAdapter;

    private List<CouponsBean> couponsList;

    private MchCouponReceiveDialog mchCouponReceiveDialog;

    List<MchCouponResponse> mchCouponResponseList;

    //优惠券位置
    private int mGroupPosition;

    //优惠券位置
    private int mChildPosition;

    //优惠券id
    private int couponId;
    private static String photoUrl;

    private static IWXAPI api;

    static Bitmap bitmap = null;

    private ShareOprateDialog shareOprateDialog;

    //地址
    private String address;

    @Override
    public int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusBarCompat.translucentStatusBar(this, false);
        //修改状态栏字体颜色
        StatusBarUtil.setImmersiveStatusBar(this, true);
        return R.layout.activity_hotel_details;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ViewGroup.LayoutParams layoutParams = mToolbarSpace.getLayoutParams();//取控件当前的布局参数
        layoutParams.height = getStatusBarHeight();// 控件的高强制设成状态栏高度

        mToolbarSpace.setLayoutParams(layoutParams); //使设置好的布局参数应用到控件</pre>
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbarSpace.setVisibility(View.VISIBLE);
        } else {
            mToolbarSpace.setVisibility(View.GONE);
        }

        mchId = getIntent().getIntExtra("mchId", 0);
        api = WXAPIFactory.createWXAPI(this, PayConstants.APP_ID, false);
        getMchDetails();
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

        if (mchHotelGoodsList == null) {

            mchHotelGoodsList = new ArrayList<>();
        } else {
            mchHotelGoodsList.clear();
        }

        if (commentList == null) {

            commentList = new ArrayList<>();

        } else {

            commentList.clear();
        }

        if (nearbyTypList == null) {

            nearbyTypList = new ArrayList<>();
        } else {
            nearbyTypList.clear();
        }

        if (hotSellingHotelsNearbyList == null) {

            hotSellingHotelsNearbyList = new ArrayList<>();
        } else {
            hotSellingHotelsNearbyList.clear();
        }

        if (couponsList == null) {

            couponsList = new ArrayList<>();
        } else {

            couponsList.clear();
        }

        if (mchCouponResponseList == null) {
            mchCouponResponseList = new ArrayList<>();
        } else {
            mchCouponResponseList.clear();
        }
        LinearLayoutManager linearLayout = new LinearLayoutManager(HotelDetailsActivity.this);
        linearLayout.setOrientation(linearLayout.VERTICAL);
        mRvHotelRoom.setLayoutManager(linearLayout);
        hotelMealDetailAdapter = new HotelDetailRoomAdapter(HotelDetailsActivity.this, new HotelDetailRoomAdapter.HotelRoomItemListener() {
            @Override
            public void setHotelRoomItemListener(int position) {

                if (hotelDetailsSupplementDialog == null) {
                    MchGoodsBean mchGoodsBean = mchHotelGoodsList.get(position);
                    String mchName = mchDetailsEntity.getMchName();
                    hotelDetailsSupplementDialog = new HotelDetailsSupplementDialog(new HotelDetailsSupplementDialog.ReservationImmdiateListener() {
                        @Override
                        public void setReservationImmdiateCallback() {

                            String token = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.TOKEN, "");

                            if (!TextUtils.isEmpty(token)) {
                                Intent intent = new Intent();
                                intent.setClass(HotelDetailsActivity.this, HotelOrderActivity.class);
                                int goodId = mchGoodsBean.getId();
                                intent.putExtra("goodsId", goodId);
                                intent.putExtra("mchName", mchName);
                                startActivity(intent);

                            } else {
                                onReLogin("");
                            }

                        }
                    }, mchGoodsBean, mchName);
                }
                hotelDetailsSupplementDialog.show(getFragmentManager(), "酒店详情补充");

            }
        });
        hotelMealDetailAdapter.setMchHotelGoodsList(mchHotelGoodsList);
        mRvHotelRoom.setAdapter(hotelMealDetailAdapter);


        LinearLayoutManager couponReceiveLinearLayoutManager = new LinearLayoutManager(HotelDetailsActivity.this);
        couponReceiveLinearLayoutManager.setOrientation(couponReceiveLinearLayoutManager.HORIZONTAL);
        mRvCouponReceiveTag.setLayoutManager(couponReceiveLinearLayoutManager);
        mchDetailCouponListAdapter = new MchDetailCouponListAdapter(HotelDetailsActivity.this, new MchDetailCouponListAdapter.CouponReceiveListener() {
            @Override
            public void setCouponReceiveCallback(int position) {

                //  showToast(ScenicSpotDetailActivity.this," " + position);
            }
        });
        mchDetailCouponListAdapter.setCouponList(couponsList);
        mRvCouponReceiveTag.setAdapter(mchDetailCouponListAdapter);
    }

    @Override
    public void initData() {

        LinearLayoutManager deliciousFoodLinearLayout = new LinearLayoutManager(HotelDetailsActivity.this);
        deliciousFoodLinearLayout.setOrientation(deliciousFoodLinearLayout.HORIZONTAL);
        mRvHotelPeriphery.setLayoutManager(deliciousFoodLinearLayout);
        hotelNearbyAdapter = new HotelNearbyAdapter(HotelDetailsActivity.this);
        hotelNearbyAdapter.setHotelNearbyList(nearbyTypList);
        mRvHotelPeriphery.setAdapter(hotelNearbyAdapter);

        //附近热销酒店
        LinearLayoutManager layoutManager = new LinearLayoutManager(HotelDetailsActivity.this);
        layoutManager.setOrientation(layoutManager.VERTICAL);
        mRvHotSellingHotelsNearby.setLayoutManager(layoutManager);
        nearbyHotSellHotelsAdapter = new NearbyHotSellHotelsAdapter(HotelDetailsActivity.this);
        nearbyHotSellHotelsAdapter.setHotSellHotelList(hotSellingHotelsNearbyList);
        mRvHotSellingHotelsNearby.setAdapter(nearbyHotSellHotelsAdapter);

        //获取顶部图片高度后，设置滚动监听
        ViewTreeObserver vto = mBannerViewHotelDetail.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mBannerViewHotelDetail.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                height = mBannerViewHotelDetail.getHeight();
                mScrollViewScenicSpotDetail.setScrolListener(HotelDetailsActivity.this);
            }
        });
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @OnClick({R.id.llyt_delicious_food, R.id.llyt_entertainment, R.id.llyt_scenic_spot, R.id.ibtn_back, R.id.rlyt_hotel_location, R.id.rlyt_all_facility_details, R.id.rlyt_booking_information
            , R.id.rlyt_nearby_hotel, R.id.img_collection, R.id.rlyt_look_user_all_comment, R.id.img_menu, R.id.img_scenic_spot_forward, R.id.tv_coupon_receive, R.id.rlyt_question_num,R.id.llyt_look_user_all_comment})
    public void onClick(View v) {
        Typeface normalFont = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL);
        mTvEntertainment.setTypeface(normalFont);
        Typeface boldFont = Typeface.create(Typeface.DEFAULT, Typeface.BOLD);
        mTvEntertainment.setTypeface(boldFont);
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.llyt_delicious_food:
                mViewDeliciousFood.setVisibility(View.VISIBLE);
                mViewEntertainment.setVisibility(View.GONE);
                mViewScenicSpot.setVisibility(View.GONE);
                mTvDeliciousFood.setTypeface(boldFont);
                mTvEntertainment.setTypeface(normalFont);
                mTvScenicSpot.setTypeface(normalFont);
                if (nearbyEntity != null) {
                    nearbyTypList = nearbyEntity.getFood();
                    if (nearbyTypList != null) {
                        hotelNearbyAdapter.setHotelNearbyList(nearbyTypList);
                        hotelNearbyAdapter.notifyDataSetChanged();
                    }
                }


                break;
            case R.id.llyt_entertainment:
                mViewEntertainment.setVisibility(View.VISIBLE);
                mViewDeliciousFood.setVisibility(View.GONE);
                mViewScenicSpot.setVisibility(View.GONE);
                mTvEntertainment.setTypeface(boldFont);
                mTvDeliciousFood.setTypeface(normalFont);
                mTvScenicSpot.setTypeface(normalFont);
                if (nearbyEntity != null) {
                    nearbyTypList = nearbyEntity.getRecreation();
                    if (nearbyTypList != null) {
                        hotelNearbyAdapter.setHotelNearbyList(nearbyTypList);
                        hotelNearbyAdapter.notifyDataSetChanged();
                    }
                }

                break;
            case R.id.llyt_scenic_spot:
                mViewScenicSpot.setVisibility(View.VISIBLE);
                mViewDeliciousFood.setVisibility(View.GONE);
                mViewEntertainment.setVisibility(View.GONE);
                mTvScenicSpot.setTypeface(boldFont);
                mTvDeliciousFood.setTypeface(normalFont);
                mTvEntertainment.setTypeface(normalFont);
                hotelNearbyAdapter.notifyDataSetChanged();
                if (nearbyEntity != null) {
                    nearbyTypList = nearbyEntity.getScenic();
                    if (nearbyTypList != null) {
                        hotelNearbyAdapter.setHotelNearbyList(nearbyTypList);
                        hotelNearbyAdapter.notifyDataSetChanged();
                    }
                }
                break;
            case R.id.ibtn_back:

                HotelDetailsActivity.this.finish();

                break;
            case R.id.rlyt_hotel_location:
                Bundle bundle = new Bundle();
                bundle.putSerializable("mchDetailsEntity", mchDetailsEntity);
                intent.putExtras(bundle);
                intent.setClass(HotelDetailsActivity.this, ScenicSpotsDetailLocationMapActivity.class);
                startActivity(intent);
                break;
            case R.id.rlyt_all_facility_details:
                if (!TextUtils.isEmpty(allFacilityDetailsH5Url)) {

                    intent.putExtra("url", allFacilityDetailsH5Url);
                    intent.putExtra("title", Constants.All_FACILITY_DETAIL_H5_TITEL);
                    intent.setClass(HotelDetailsActivity.this, WebActivity.class);
                    startActivity(intent);

                }
                break;
            case R.id.rlyt_booking_information:
                if (!TextUtils.isEmpty(bookingInformationDetailsH5Url)) {

                    intent.putExtra("url", bookingInformationDetailsH5Url);
                    intent.putExtra("title", Constants.RESERVATION_MUST_BE_READ_H5_TITEL);
                    intent.setClass(HotelDetailsActivity.this, WebActivity.class);
                    startActivity(intent);

                }
                break;
            case R.id.rlyt_nearby_hotel:
                intent.setClass(HotelDetailsActivity.this, NearbyHotelListActivity.class);
                intent.putExtra("longitude", longitude);
                intent.putExtra("latitude", latitude);
                startActivity(intent);
                break;
            case R.id.img_collection:
                String token = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.TOKEN, "");

                if (!TextUtils.isEmpty(token)) {
                    mchCollection();
                } else {
                    onReLogin("");
                }

                break;
            case R.id.rlyt_look_user_all_comment:

                intent.setClass(HotelDetailsActivity.this, MchCommentActivity.class);
                intent.putExtra("mchId", mchId);
                startActivity(intent);

                break;
            case R.id.llyt_look_user_all_comment:
                intent.setClass(HotelDetailsActivity.this, MchCommentActivity.class);
                intent.putExtra("mchId", mchId);
                startActivity(intent);
                break;
            case R.id.img_menu:
                showPopupWindow(mImageMenu);
                break;
            case R.id.img_scenic_spot_forward:

                if (shareOprateDialog == null) {
                    shareOprateDialog = new ShareOprateDialog(HotelDetailsActivity.this, new ShareOprateDialog.OnSharePlatformItemClickListener() {
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
            case R.id.tv_coupon_receive:

                queryMchCouponList();

                break;

            case R.id.rlyt_question_num:
                intent.putExtra("mchId", mchId);
                intent.putExtra("mchPhotoUrl", photoUrl);
                intent.putExtra("address", address);
                intent.setClass(HotelDetailsActivity.this, MoreQuestionsActivity.class);
                startActivity(intent);
                break;
            default:
                break;

        }
    }

    @Override
    public void getHotelHomePageResult(BackResult<ScenicSpotHomePageResponse> res) {

    }

    @Override
    public void findHotelByCateResult(BackResult<ScenicSpotResponse> res) {

    }

    @Override
    public void getHotelBangDanRankingResult(BackResult<MchBangDanRankingResponse> res) {

    }

    @Override
    public void hotelHomestayOrderSubmitResult(BackResult<OrderSubmitResponse> res) {

    }

    @Override
    public void getHotelListByCateIdResult(BackResult<MchCateListResponse> res) {

    }

    @Override
    public void mchCollectionResult(BackResult<MchCollectionResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    MchCollectionResponse mchCollectionResponse = res.getData();
                    collectionStatus = mchCollectionResponse.getCollectionStatus();

                    if (collectionStatus == 0) {
                        mImgCollection.setImageResource(R.mipmap.icon_white_collection);


                    } else if (collectionStatus == 1) {

                        mImgCollection.setImageResource(R.mipmap.icon_green_has_collection);

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(HotelDetailsActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void queryByTicketResult(BackResult<QueryByTicketResponse> res) {

    }

    @Override
    public void useCouponTicketResult(BackResult<UseCouponTicketResponse> res) {

    }

    @Override
    public void getHotelMchDetailResult(BackResult<HotelMchDetailsResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    mchDetailsResponse = res.getData();

                    mchDetailsEntity = mchDetailsResponse.getMchDetails();
                    HotelMchDetailsResponse.MchQuestionEntity mchQuestionEntity = mchDetailsResponse.getMchQuestion(); //问题
                    couponsList = mchDetailsResponse.getCoupons();    //优惠券
                    mchHotelGoodsList = mchDetailsResponse.getMchGoods();     //酒店商品展示列表
                    latitude = mchDetailsEntity.getLatitude();
                    longitude = mchDetailsEntity.getLongitude();
                    mchName = mchDetailsEntity.getMchName();
                    //A级
                    int level = mchDetailsEntity.getLevel();
                    //评价
                    int commentNum = mchDetailsEntity.getCommentNum();
                    double mConsumePrice = mchDetailsEntity.getConsumePrice();
                    float mCommentScore = mchDetailsEntity.getCommentScore();

                    collectionStatus = mchDetailsEntity.getUserCollectState();

                    if (collectionStatus == 0) {
                        mImgCollection.setImageResource(R.mipmap.icon_white_collection);


                    } else if (collectionStatus == 1) {

                        mImgCollection.setImageResource(R.mipmap.icon_green_has_collection);

                    }

                    List<HotelMchDetailsResponse.TagsEntity> tagsEntityList = mchDetailsEntity.getTags();

                    mTvHotelCommentScore.setText(String.valueOf(mCommentScore));
                    mStarBarView.setIntegerMark(false);

                    mStarBarView.setStarMark(mCommentScore);
                    mTvCommentNum.setText(commentNum + "评论");
                    mTvUserCommentNum.setText(commentNum + "评论");
                    //banner
                    bannerList = mchDetailsEntity.getRecommendPhoto();

                    if (bannerList != null && bannerList.size() > 0) {

                        for (int i = 0; i < bannerList.size(); i++) {
                            ImageView image = new ImageView(HotelDetailsActivity.this);
                            image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                            //设置显示格式
                            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            viewList.add(image);
                        }
                    }

                    mBannerViewHotelDetail.startLoop(false);
                    mBannerViewHotelDetail.setViewList(HotelDetailsActivity.this, viewList, bannerList);
                    //酒店名称
                    mHotelName.setText(mchName);
                    //酒店地址
                    address = mchDetailsEntity.getAddress();
                    if (!TextUtils.isEmpty(address)) {
                        mTvMchAddress.setText(address);
                    }

                    if (mchHotelGoodsList != null) {

                        hotelMealDetailAdapter.setMchHotelGoodsList(mchHotelGoodsList);
                        hotelMealDetailAdapter.notifyDataSetChanged();
                    }

                    HotelMchDetailsResponse.CommentEntity commentEntity = mchDetailsResponse.getComment();
                    commentList = commentEntity.getComment();

                    HotelMchDetailsResponse.ScoreEntity scoreEntity = commentEntity.getScore();
                    labelEntityList = commentEntity.getLabel();

                    int mCommentNum = scoreEntity.getCommentNum();
                    float commentScore = scoreEntity.getCommentScore();
                    double serviceCommentScore = scoreEntity.getCommentScore1();
                    double facilitiesCommentScore = scoreEntity.getCommentScore2();
                    double hygieneScoreCommentScore = scoreEntity.getCommentScore3();

                    mTvOccupantScore.setText(Tools.getFormatDecimalPoint(commentScore));
                    mTvServiceScore.setText("服务" + Tools.getFormatDecimalPoint(serviceCommentScore));
                    mTvFacilitiesScore.setText("设施" + Tools.getFormatDecimalPoint(facilitiesCommentScore));
                    mTvHygieneScore.setText("卫生" + Tools.getFormatDecimalPoint(hygieneScoreCommentScore));

                  /*  if (commentList != null) {
                        hotelDetailUserCommentAdapter.setHotelDetailUserCommentList(commentList);
                        hotelDetailUserCommentAdapter.notifyDataSetChanged();
                    }*/

                    mStarBarViewOccupantScore.setIntegerMark(false);
                    mStarBarViewOccupantScore.setStarMark(commentScore);

                    tagAdapter = new TagAdapter<LabelEntity>(labelEntityList) {
                        @Override
                        public View getView(FlowLayout parent, int position, LabelEntity option) {
                            View view = LayoutInflater.from(mContext).inflate(R.layout.layout_flowlayout_tag_gray_frame,
                                    mTagFlowHotelCommentLabel, false);
                            TextView tv = view.findViewById(R.id.tv_flowlayout);
                            tv.setText(option.getTitle());
                            return view;
                        }
                    };
                    mTagFlowHotelCommentLabel.setMaxSelectCount(1);
                    mTagFlowHotelCommentLabel.setAdapter(tagAdapter);

                    mTagFlowHotelCommentLabel.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                        @Override
                        public boolean onTagClick(View view, int position, FlowLayout parent) {
                            String content = "";
                            Set<Integer> selectPosSet = mTagFlowHotelCommentLabel.getSelectedList();
                            Iterator it = selectPosSet.iterator();
                            while (it.hasNext()) {
                                int index = (int) it.next();
                                //content = options[index];
                                // MoveToPosition(layoutManager,index);


                            }
                            return true;
                        }
                    });
                    tagAdapter.setSelectedList(0);
                    String questionContent = mchQuestionEntity.getQuestionContent();
                    String answerContent = mchQuestionEntity.getAnswerContent();
                    //问
                    if (!TextUtils.isEmpty(questionContent))
                    {
                        mTvQuestionContent.setText(questionContent);
                    }
                    //答
                    if (!TextUtils.isEmpty(answerContent)) {

                        mTvAnswerContent.setText(answerContent);
                    }
                    bookingInformationDetailsH5Url = mchDetailsEntity.getBookingInformationDetails();

                    allFacilityDetailsH5Url = mchDetailsEntity.getAllFacilityDetails();

                    //设备详情
                    List<HotelMchDetailsResponse.ServiceEntity> tagsList = mchDetailsEntity.getServiceJson();

                    mTagFlowLayoutDevice.setAdapter(new TagAdapter<HotelMchDetailsResponse.ServiceEntity>(tagsList) {

                        @Override
                        public View getView(FlowLayout parent, int position, HotelMchDetailsResponse.ServiceEntity tagsEntity) {
                            LayoutInflater mInflater = LayoutInflater.from(mContext);
                            LinearLayout mLlytDevice = (LinearLayout) mInflater.inflate(R.layout.layout_flowlayout_device_detail,
                                    mTagFlowLayoutDevice, false);
                            TextView mTvDeviceTitle = mLlytDevice.findViewById(R.id.tv_device_title);
                            ImageView mImgDevice = mLlytDevice.findViewById(R.id.img_device);
                            String devicePhoto = tagsEntity.getPhoto();
                            mTvDeviceTitle.setText(tagsEntity.getTitle());
                            GlideUtil.loadImage(HotelDetailsActivity.this, devicePhoto, mImgDevice);
                            return mLlytDevice;
                        }
                    });

                    String checkinTime = mchDetailsEntity.getCheckinTime();
                    String leaveTime = mchDetailsEntity.getLeaveTime();
                    mTvHotelCheckinAndLeaveTime.setText("入店时间 " + checkinTime + "以后   离店时间" + leaveTime + "以前");

                    //膳食安排
                    String dietInfo = mchDetailsEntity.getDietInfo();
                    mTvDietInfo.setText(dietInfo);

                    //是否可以携带宠物
                    int petsStatus = mchDetailsEntity.getPetsStatus();
                    if (petsStatus == 0) {

                        mTvPetsStatus.setText("不允许带宠物");

                    } else if (petsStatus == 1) {

                        mTvPetsStatus.setText("允许带宠物");
                    }

                    //酒店周边
                    nearbyEntity = mchDetailsResponse.getNearby();
                    nearbyTypList = nearbyEntity.getFood();
                    if (nearbyTypList != null) {
                        hotelNearbyAdapter.setHotelNearbyList(nearbyTypList);
                        hotelNearbyAdapter.notifyDataSetChanged();
                    }

                    //附近热销酒店
                    HotelMchDetailsResponse.NearbyHotelEntity nearbyHotelEntity = mchDetailsResponse.getNearbyHotel();
                    hotSellingHotelsNearbyList = nearbyHotelEntity.getHotel();
                    int hotelCount = nearbyHotelEntity.getHotelCount();
                    mNearbySellWellHotelNum.setText("(" + hotelCount + "家)");
                    nearbyHotSellHotelsAdapter.setHotSellHotelList(hotSellingHotelsNearbyList);
                    nearbyHotSellHotelsAdapter.notifyDataSetChanged();

                    if (couponsList.size() > 0) {
                        mRlytCoupon.setVisibility(View.VISIBLE);
                        mchDetailCouponListAdapter.setCouponList(couponsList);
                        mchDetailCouponListAdapter.notifyDataSetChanged();
                    } else {
                        mRlytCoupon.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(HotelDetailsActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getHotelHomestayOrderInitResult(BackResult<HotelOrderInitResponse> res) {

    }

    @Override
    public void queryMchCouponListResult(BackResult<List<MchCouponResponse>> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    mchCouponResponseList = res.getData();
                    if (mchCouponResponseList != null) {
                        if (mchCouponReceiveDialog == null) {
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
                showToast(HotelDetailsActivity.this, Constants.getResultMsg(res.getMsg()));
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
                showToast(HotelDetailsActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(HotelDetailsActivity.this, Constants.getResultMsg(msg));
    }

    @Override
    public void onScroll(int y) {

        if (y <= height && y >= 0) {
            float scale = (float) y / height;
            float alpha = (255 * scale);

            mRlytScenicSpostsDetail.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
            mToolbarSpace.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
            mImgBtnBack.setImageDrawable(getResources().getDrawable(R.mipmap.icon_left_arrow_black));
            if (collectionStatus == 0) {
                mImgCollection.setImageResource(R.mipmap.icon_black_collection);
            }
            mImgScenicSpotForward.setImageResource(R.mipmap.icon_black_share);
            mImageMenu.setImageDrawable(getResources().getDrawable(R.mipmap.icon_black_menu_more));
            if (y <= 300) {
                mImgBtnBack.setImageDrawable(getResources().getDrawable(R.mipmap.icon_left_arrow_white));
                if (collectionStatus == 0) {
                    mImgCollection.setImageDrawable(getResources().getDrawable(R.mipmap.icon_white_collection));
                }
                mImgScenicSpotForward.setImageResource(R.mipmap.icon_white_share);
                mImageMenu.setImageDrawable(getResources().getDrawable(R.mipmap.icon_white_menu_more));
                mRlytScenicSpostsDetail.setBackgroundColor(Color.argb(0, 0, 0, 0));
                mToolbarSpace.setBackgroundColor(Color.argb(0, 0, 0, 0));
            }
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
                    intent.setClass(HotelDetailsActivity.this, MyOrderActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                } else if (itemStr.equals(backMyMessage)) {
                    intent.setClass(HotelDetailsActivity.this, MessageActivity.class);
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

    //获取酒店详情
    public void getMchDetails() {

        if (validateInternet()) {
            showProgressDialog(HotelDetailsActivity.this);
            mPresenter.getHotelMchDetail(mchId);
        }
    }

    //查询商户券列表
    public void queryMchCouponList() {

        if (validateInternet()) {
            showProgressDialog(HotelDetailsActivity.this);
            mPresenter.queryMchCouponList(mchId);
        }
    }

    //获取优惠券
    public void getCoupon() {

        if (validateInternet()) {
            showProgressDialog(HotelDetailsActivity.this);
            mPresenter.getCoupon(couponId);
        }
    }

    //商户收藏
    public void mchCollection() {

        if (validateInternet()) {
            showProgressDialog(HotelDetailsActivity.this);
            MchCollectionRequest mchCollectionRequest = new MchCollectionRequest();
            mchCollectionRequest.setDataId(mchId);
            mPresenter.mchCollection(mchCollectionRequest);
        }
    }

    private void thirdShare(SHARE_MEDIA platform, String photoUrl) {
        String webUrl = Net.H5_YURENZIYOU_DOWNLOAD_GUIDE_PAGE_URL;
        UMImage image = new UMImage(HotelDetailsActivity.this, photoUrl);                    //资源文件
        UMWeb umWeb = new UMWeb(webUrl, HotelDetailsActivity.this.getResources().getString(R.string.app_name), "鱼人自游是宁波海洋世界旗下一站式旅游服务平台,产品及服务覆盖门票预订,景点评价及景点打折门票查询,酒店预订,美食推荐、还有更详细的旅游攻略.", image); //URL 标题 描述 封面图
        new ShareAction(HotelDetailsActivity.this)
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
            Toast.makeText(HotelDetailsActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(HotelDetailsActivity.this, "取消了", Toast.LENGTH_LONG).show();
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
                miniProgramObj.path = Net.MCH_HOTEL_MINIPTOGRAM_URL + mchId; //小程序页面路径；对于小游戏，可以只传入 query 部分，来实现传参效果，如：传入 "?foo=bar"
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
