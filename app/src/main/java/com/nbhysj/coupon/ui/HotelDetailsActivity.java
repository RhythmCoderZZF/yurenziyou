package com.nbhysj.coupon.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.HotelDetailRoomAdapter;
import com.nbhysj.coupon.adapter.HotelNearbyAdapter;
import com.nbhysj.coupon.adapter.NearbyHotSellHotelsAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.HotelContract;
import com.nbhysj.coupon.dialog.HotelDetailsSupplementDialog;
import com.nbhysj.coupon.model.HotelModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.GroupMchDetailsResponse;
import com.nbhysj.coupon.model.response.HotelBean;
import com.nbhysj.coupon.model.response.LabelEntity;
import com.nbhysj.coupon.model.response.MchCommentEntity;
import com.nbhysj.coupon.model.response.HotelOrderInitResponse;
import com.nbhysj.coupon.model.response.MchBangDanRankingResponse;
import com.nbhysj.coupon.model.response.MchDetailsResponse;
import com.nbhysj.coupon.model.response.MchGoodsBean;
import com.nbhysj.coupon.model.response.NearbyTypeResponse;
import com.nbhysj.coupon.model.response.OrderSubmitResponse;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.model.response.ScenicSpotResponse;
import com.nbhysj.coupon.presenter.HotelPresenter;
import com.nbhysj.coupon.systembar.StatusBarCompat;
import com.nbhysj.coupon.systembar.StatusBarUtil;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.Tools;
import com.nbhysj.coupon.view.HotelDetailBannerView;
import com.nbhysj.coupon.view.RecyclerScrollView;
import com.nbhysj.coupon.view.StarBarView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

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

    private List<ImageView> viewList;
    private List<String> bannerList;

    private MchDetailsResponse mchDetailsResponse;

    private MchDetailsResponse.MchDetailsEntity mchDetailsEntity;

    //评论列表
    List<MchCommentEntity> commentList;

    //用户评论标签
    List<LabelEntity> labelEntityList;
    //商户id
    private int mchId;
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
    private MchDetailsResponse.NearbyEntity nearbyEntity;

    //附近列表
    private List<NearbyTypeResponse> nearbyTypList;

    List<HotelBean> hotSellingHotelsNearbyList;

    private HotelDetailsSupplementDialog hotelDetailsSupplementDialog;

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

        LinearLayoutManager linearLayout = new LinearLayoutManager(HotelDetailsActivity.this);
        linearLayout.setOrientation(linearLayout.VERTICAL);
        mRvHotelRoom.setLayoutManager(linearLayout);
        hotelMealDetailAdapter = new HotelDetailRoomAdapter(HotelDetailsActivity.this, new HotelDetailRoomAdapter.HotelRoomItemListener() {
            @Override
            public void setHotelRoomItemListener(int position) {

                if (hotelDetailsSupplementDialog == null) {
                    MchGoodsBean mchGoodsBean = mchHotelGoodsList.get(position);
                    String mchName = mchDetailsEntity.getMchName();
                    hotelDetailsSupplementDialog = new HotelDetailsSupplementDialog(mchGoodsBean, mchName);
                }
                hotelDetailsSupplementDialog.show(getFragmentManager(), "酒店详情补充");

            }
        });
        hotelMealDetailAdapter.setMchHotelGoodsList(mchHotelGoodsList);
        mRvHotelRoom.setAdapter(hotelMealDetailAdapter);

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

    @OnClick({R.id.llyt_delicious_food, R.id.llyt_entertainment, R.id.llyt_scenic_spot, R.id.ibtn_back, R.id.llyt_hotel_location})
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
            case R.id.llyt_hotel_location:
                Bundle bundle = new Bundle();
                bundle.putSerializable("mchDetailsEntity", mchDetailsEntity);
                intent.putExtras(bundle);
                intent.setClass(HotelDetailsActivity.this, ScenicSpotsDetailLocationMapActivity.class);
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
    public void getMchDetailsResult(BackResult<MchDetailsResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    mchDetailsResponse = res.getData();

                    mchDetailsEntity = mchDetailsResponse.getMchDetails();
                    MchDetailsResponse.MchQuestionEntity mchQuestionEntity = mchDetailsResponse.getMchQuestion(); //问题

                    mchHotelGoodsList = mchDetailsResponse.getMchGoods();     //酒店商品展示列表
                    //latitude = mchDetailsEntity.getLatitude();
                    //longitude = mchDetailsEntity.getLongitude();

                    mchName = mchDetailsEntity.getMchName();
                    //A级
                    int level = mchDetailsEntity.getLevel();
                    //评价
                    int commentNum = mchDetailsEntity.getCommentNum();
                    double mConsumePrice = mchDetailsEntity.getConsumePrice();
                    float mCommentScore = mchDetailsEntity.getCommentScore();

                    List<MchDetailsResponse.TagsEntity> tagsEntityList = mchDetailsEntity.getTags();
                    mHotelName.setText(mchName);
                    mTvHotelCommentScore.setText(String.valueOf(mCommentScore));
                    mStarBarView.setIntegerMark(false);

                    mStarBarView.setStarMark(mCommentScore);
                    mTvCommentNum.setText(commentNum + "评论");

                    //banner
                    bannerList = mchDetailsEntity.getRecommendPhoto();

                    if (bannerList.size() > 0) {

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


                    //商户地址
                    String address = mchDetailsEntity.getAddress();
                    mTvMchAddress.setText(address);

                    hotelMealDetailAdapter.setMchHotelGoodsList(mchHotelGoodsList);
                    hotelMealDetailAdapter.notifyDataSetChanged();

                    MchDetailsResponse.CommentEntity commentEntity = mchDetailsResponse.getComment();
                    commentList = commentEntity.getComment();

                    MchDetailsResponse.ScoreEntity scoreEntity = commentEntity.getScore();
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
                    if (!TextUtils.isEmpty(questionContent)) {
                        mTvQuestionContent.setText(questionContent);
                    }
                    //答
                    if (!TextUtils.isEmpty(answerContent)) {

                        mTvAnswerContent.setText(answerContent);
                    }

                    //设备详情
                    List<MchDetailsResponse.ServiceEntity> tagsList = mchDetailsEntity.getServiceJson();

                    mTagFlowLayoutDevice.setAdapter(new TagAdapter<MchDetailsResponse.ServiceEntity>(tagsList) {

                        @Override
                        public View getView(FlowLayout parent, int position, MchDetailsResponse.ServiceEntity tagsEntity) {
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
                    MchDetailsResponse.NearbyHotelEntity nearbyHotelEntity = mchDetailsResponse.getNearbyHotel();
                    hotSellingHotelsNearbyList = nearbyHotelEntity.getHotel();
                    int hotelCount = nearbyHotelEntity.getHotelCount();
                    mNearbySellWellHotelNum.setText("(" + hotelCount + "家)");
                    nearbyHotSellHotelsAdapter.setHotSellHotelList(hotSellingHotelsNearbyList);
                    nearbyHotSellHotelsAdapter.notifyDataSetChanged();

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
            mImgCollection.setImageResource(R.mipmap.icon_black_collection);
            mImageMenu.setImageDrawable(getResources().getDrawable(R.mipmap.icon_black_menu_more));
            if (y <= 300) {
                mImgBtnBack.setImageDrawable(getResources().getDrawable(R.mipmap.icon_left_arrow_white));
                mImgCollection.setImageDrawable(getResources().getDrawable(R.mipmap.icon_white_collection));
                mImageMenu.setImageDrawable(getResources().getDrawable(R.mipmap.icon_white_menu_more));
                mRlytScenicSpostsDetail.setBackgroundColor(Color.argb(0, 0, 0, 0));
                mToolbarSpace.setBackgroundColor(Color.argb(0, 0, 0, 0));
            }
        }
    }

    //获取酒店详情
    public void getMchDetails() {

        mPresenter.getMchDetails(mchId);
    }
}
