package com.nbhysj.coupon.ui;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
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
import com.nbhysj.coupon.adapter.HomestayEquipmentAdapter;
import com.nbhysj.coupon.adapter.HomestayReservationAdapter;
import com.nbhysj.coupon.adapter.NearbyHotSellHotelsAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.HomestayContract;
import com.nbhysj.coupon.model.HomestayModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BannerUrlBO;
import com.nbhysj.coupon.model.response.EquipmentResponse;
import com.nbhysj.coupon.model.response.HotelBean;
import com.nbhysj.coupon.model.response.MchBangDanRankingResponse;
import com.nbhysj.coupon.model.response.MchDetailsResponse;
import com.nbhysj.coupon.model.response.MchGoodsBean;
import com.nbhysj.coupon.model.response.MchHomestayDetailsResponse;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.model.response.ScenicSpotResponse;
import com.nbhysj.coupon.model.response.ScenicSpotsUserCommentResponse;
import com.nbhysj.coupon.presenter.HomestayPresenter;
import com.nbhysj.coupon.systembar.StatusBarCompat;
import com.nbhysj.coupon.systembar.StatusBarUtil;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.Tools;
import com.nbhysj.coupon.view.HotelDetailBannerView;
import com.nbhysj.coupon.view.RecyclerScrollView;
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
 * @auther：hysj created on 2019/05/30
 * description：民宿详情
 */
public class HomestayDetailActivity extends BaseActivity<HomestayPresenter, HomestayModel> implements HomestayContract.View, RecyclerScrollView.OnScrollListener {

    @BindView(R.id.toolbar_space)
    View mToolbarSpace;
    @BindView(R.id.banner_hotel_detail)
    HotelDetailBannerView mBannerViewHotelDetail;
    @BindView(R.id.flowlayout_hotel_comment_label)
    TagFlowLayout mTagFlowHotelCommentLabel;
    @BindView(R.id.rv_user_comment)
    RecyclerView mRvUserComment;
    @BindView(R.id.rv_hotel_periphery_recommendation)
    RecyclerView mRvHotelPeriphery;
    @BindView(R.id.scrollview_homestay_detail)
    RecyclerScrollView mScrollViewHomeStayDetail;
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
    //问题内容
    @BindView(R.id.tv_question_content)
    TextView mTvQuestionContent;
    //回答内容
    @BindView(R.id.tv_answer_content)
    TextView mTvAnswerContent;
    //民宿
    @BindView(R.id.rv_hot_selling_homestay_nearby)
    RecyclerView mRvHotSellingHomestaysNearby;
    private int height;
    private List<ImageView> viewList;
    private List<String> bannerList;
    List<EquipmentResponse> equipmentResponseList = null;

    private List<HotelBean> hotSellingHotelsNearbyList;

    //民宿商品列表
    private List<MchGoodsBean> mchHomestayGoodsList;

    private MchHomestayDetailsResponse homestayDetailsResponse;

    private MchHomestayDetailsResponse.MchDetailsEntity mchDetailsEntity;
    //商户id
    private int mchId;

    //商户名
    private String mchName;
    private HomestayReservationAdapter homestayReservationAdapter;

    private NearbyHotSellHotelsAdapter nearbyHotSellHotelsAdapter;

    List<MchHomestayDetailsResponse.SubCommentEntity> commentList;

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

        if (equipmentResponseList == null) {

            equipmentResponseList = new ArrayList<>();

        } else {

            equipmentResponseList.clear();
        }

        //民宿
        if (hotSellingHotelsNearbyList == null) {

            hotSellingHotelsNearbyList = new ArrayList<>();
        } else {
            hotSellingHotelsNearbyList.clear();
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

        List<String> fineFoodTagList = new ArrayList<>();
        fineFoodTagList.add("服务热情(500)");
        fineFoodTagList.add("实惠(20)");
        fineFoodTagList.add("干净卫生(200)");
        fineFoodTagList.add("服务热情(500)");
        fineFoodTagList.add("刷差评(200)");
        TagAdapter tagAdapter = new TagAdapter<String>(fineFoodTagList) {
            @Override
            public View getView(FlowLayout parent, int position, String option) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.layout_flowlayout_tag_gray_frame,
                        mTagFlowHotelCommentLabel, false);
                TextView tv = view.findViewById(R.id.tv_flowlayout);
                tv.setText(option);

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

        List<ScenicSpotsUserCommentResponse> spotsUserCommentResponseList = new ArrayList<>();
        List<String> userCommentPhotoList = new ArrayList<>();
        userCommentPhotoList.add("http://img5.imgtn.bdimg.com/it/u=3300305952,1328708913&fm=26&gp=0.jpg");
        userCommentPhotoList.add("http://d.hiphotos.baidu.com/lvpics/w=1000/sign=e2347e78217f9e2f703519082f00eb24/730e0cf3d7ca7bcb49f90bb1b8096b63f724a8aa.jpg");
        userCommentPhotoList.add("http://i0.hexunimg.cn/2011-08-18/132587770.jpg");
        ScenicSpotsUserCommentResponse userCommentResponse = new ScenicSpotsUserCommentResponse();
        userCommentResponse.setId(1);
        userCommentResponse.setUsername("陈发发");
        userCommentResponse.setCommentPublishTime("2019-04-29");
        userCommentResponse.setContent("第一次去海洋馆真的超级幸运,先去看的是剧场表扬 互动环节的时候很幸运被抽中和白鲸接触来了一个深海之吻超级感动,后面又到水族参观,最喜欢的是哒哒哒哒哒哒多多");
        userCommentResponse.setStarLevel(3);
        userCommentResponse.setUserAvatarPhoto("http://pic9.nipic.com/20100901/4753218_163400058451_2.jpg");
        userCommentResponse.setUserCommentPhotoList(userCommentPhotoList);

        spotsUserCommentResponseList.add(userCommentResponse);
        LinearLayoutManager userCommentLayoutManager = new LinearLayoutManager(HomestayDetailActivity.this);
        userCommentLayoutManager.setOrientation(userCommentLayoutManager.VERTICAL);
        mRvUserComment.setLayoutManager(userCommentLayoutManager);
       /* HotelDetailUserCommentAdapter hotelDetailUserCommentAdapter = new HotelDetailUserCommentAdapter(HomestayDetailActivity.this);
        hotelDetailUserCommentAdapter.setHotelDetailUserCommentList(spotsUserCommentResponseList);
        mRvUserComment.setAdapter(hotelDetailUserCommentAdapter);*/

    /*    LinearLayoutManager deliciousFoodLinearLayout = new LinearLayoutManager(HomestayDetailActivity.this);
        deliciousFoodLinearLayout.setOrientation(deliciousFoodLinearLayout.HORIZONTAL);
        mRvHotelPeriphery.setLayoutManager(deliciousFoodLinearLayout);
        Home hotelPeripheryAdapter = new HotelPeripheryAdapter(HomestayDetailActivity.this);
        mRvHotelPeriphery.setAdapter(hotelPeripheryAdapter);*/

        //看了这个房的人还看了
        LinearLayoutManager layoutManager = new LinearLayoutManager(HomestayDetailActivity.this);
        layoutManager.setOrientation(layoutManager.VERTICAL);
        mRvHotSellingHomestaysNearby.setLayoutManager(layoutManager);
        nearbyHotSellHotelsAdapter = new NearbyHotSellHotelsAdapter(HomestayDetailActivity.this);
        nearbyHotSellHotelsAdapter.setHotSellHotelList(hotSellingHotelsNearbyList);
        mRvHotSellingHomestaysNearby.setAdapter(nearbyHotSellHotelsAdapter);

        List<EquipmentResponse> equipmentResponseList = initEquipmentList();
        //附近热销酒店
        GridLayoutManager homestayEquipmentLayoutManager = new GridLayoutManager(HomestayDetailActivity.this, 4);
        homestayEquipmentLayoutManager.setOrientation(homestayEquipmentLayoutManager.VERTICAL);
        mRvHomestayEquipment.setLayoutManager(homestayEquipmentLayoutManager);
        HomestayEquipmentAdapter homestayEquipmentAdapter = new HomestayEquipmentAdapter(HomestayDetailActivity.this);
        homestayEquipmentAdapter.setEquipmentList(equipmentResponseList);
        mRvHomestayEquipment.setAdapter(homestayEquipmentAdapter);

    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @OnClick({R.id.llyt_delicious_food, R.id.llyt_entertainment, R.id.llyt_scenic_spot, R.id.ibtn_back})
    public void onClick(View v) {
        Typeface normalFont = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL);
        mTvEntertainment.setTypeface(normalFont);
        Typeface boldFont = Typeface.create(Typeface.DEFAULT, Typeface.BOLD);
        mTvEntertainment.setTypeface(boldFont);
        switch (v.getId()) {
            case R.id.llyt_delicious_food:
                mViewDeliciousFood.setVisibility(View.VISIBLE);
                mViewEntertainment.setVisibility(View.GONE);
                mViewScenicSpot.setVisibility(View.GONE);
                mTvDeliciousFood.setTypeface(boldFont);
                mTvEntertainment.setTypeface(normalFont);
                mTvScenicSpot.setTypeface(normalFont);
                break;
            case R.id.llyt_entertainment:
                mViewEntertainment.setVisibility(View.VISIBLE);
                mViewDeliciousFood.setVisibility(View.GONE);
                mViewScenicSpot.setVisibility(View.GONE);
                mTvEntertainment.setTypeface(boldFont);
                mTvDeliciousFood.setTypeface(normalFont);
                mTvScenicSpot.setTypeface(normalFont);
                break;
            case R.id.llyt_scenic_spot:
                mViewScenicSpot.setVisibility(View.VISIBLE);
                mViewDeliciousFood.setVisibility(View.GONE);
                mViewEntertainment.setVisibility(View.GONE);
                mTvScenicSpot.setTypeface(boldFont);
                mTvDeliciousFood.setTypeface(normalFont);
                mTvEntertainment.setTypeface(normalFont);
                break;
            case R.id.ibtn_back:

                HomestayDetailActivity.this.finish();

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
    public void mchCollectionResult(BackResult res) {

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

                    mchHomestayGoodsList = homestayDetailsResponse.getMchGoods();     //酒店商品展示列表
                    //latitude = mchDetailsEntity.getLatitude();
                    //longitude = mchDetailsEntity.getLongitude();

                    mchName = mchDetailsEntity.getMchName();
                    //A级
                    int level = mchDetailsEntity.getLevel();
                    //评价
                    int commentNum = mchDetailsEntity.getCommentNum();
                    int mConsumePrice = mchDetailsEntity.getConsumePrice();
                    float mCommentScore = mchDetailsEntity.getCommentScore();
                    //商户地址
                    String address = mchDetailsEntity.getAddress();
                    List<MchHomestayDetailsResponse.TagsEntity> tagsEntityList = mchDetailsEntity.getTags();
                    mTvHomestayName.setText(Html.fromHtml(mchName + "<font color='#FF666666' size='10'>" + address + "</font>"));
                /*     mTvHotelCommentScore.setText(String.valueOf(mCommentScore));
                    mStarBarView.setIntegerMark(false);

                    mStarBarView.setStarMark(mCommentScore);
                    mTvCommentNum.setText(commentNum + "评论");
*/
                    //banner
                    bannerList = mchDetailsEntity.getRecommendPhoto();

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


                    homestayReservationAdapter.setHomestayReservationList(mchHomestayGoodsList);
                    homestayReservationAdapter.notifyDataSetChanged();

                    MchHomestayDetailsResponse.CommentEntity commentEntity = homestayDetailsResponse.getComment();
                    commentList = commentEntity.getComment();

                    MchHomestayDetailsResponse.ScoreEntity scoreEntity = commentEntity.getScore();

                   /* if (commentList != null)
                    {
                        hotelDetailUserCommentAdapter.setHotelDetailUserCommentList(commentList);
                        hotelDetailUserCommentAdapter.notifyDataSetChanged();
                    }

                    mStarBarViewOccupantScore.setIntegerMark(false);
                    mStarBarViewOccupantScore.setStarMark(commentScore);

                    tagAdapter = new TagAdapter<MchDetailsResponse.LabelEntity>(labelEntityList) {
                        @Override
                        public View getView(FlowLayout parent, int position, MchDetailsResponse.LabelEntity option) {
                            View view = LayoutInflater.from(mContext).inflate(R.layout.layout_flowlayout_tag_gray_frame,
                                    mTagFlowHotelCommentLabel, false);
                            TextView tv = view.findViewById(R.id.tv_flowlayout);
                            tv.setText(option.getTitle());
                            return view;
                        }
                    };
                    mTagFlowHotelCommentLabel.setMaxSelectCount(1);
                    mTagFlowHotelCommentLabel.setAdapter(tagAdapter);*/

               /*     mTagFlowHotelCommentLabel.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
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
                    tagAdapter.setSelectedList(0);*/
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
                    List<MchHomestayDetailsResponse.ServiceEntity> tagsList = mchDetailsEntity.getServiceJson();

                   /* mTagFlowLayoutDevice.setAdapter(new TagAdapter<MchDetailsResponse.ServiceEntity>(tagsList) {

                        @Override
                        public View getView(FlowLayout parent, int position, MchDetailsResponse.ServiceEntity tagsEntity) {
                            LayoutInflater  mInflater = LayoutInflater.from(mContext);
                            LinearLayout mLlytDevice = (LinearLayout) mInflater.inflate(R.layout.layout_flowlayout_device_detail,
                                    mTagFlowLayoutDevice, false);
                            TextView mTvDeviceTitle = mLlytDevice.findViewById(R.id.tv_device_title);
                            ImageView mImgDevice = mLlytDevice.findViewById(R.id.img_device);
                            String devicePhoto = tagsEntity.getPhoto();
                            mTvDeviceTitle.setText(tagsEntity.getTitle());
                            GlideUtil.loadImage(HotelDetailsActivity.this,devicePhoto,mImgDevice);
                            return mLlytDevice;
                        }
                    });*/

                    String checkinTime = mchDetailsEntity.getCheckinTime();
                    String leaveTime = mchDetailsEntity.getLeaveTime();

                    //附近热销酒店
                    hotSellingHotelsNearbyList = homestayDetailsResponse.getNearbyHotel();
                    nearbyHotSellHotelsAdapter.setHotSellHotelList(hotSellingHotelsNearbyList);
                    nearbyHotSellHotelsAdapter.notifyDataSetChanged();

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

    public List<EquipmentResponse> initEquipmentList() {
        EquipmentResponse equipmentResponse = new EquipmentResponse();
        equipmentResponse.setImage(R.mipmap.icon_central_heating);
        equipmentResponse.setName("暖气");
        EquipmentResponse equipmentResponse1 = new EquipmentResponse();
        equipmentResponse1.setImage(R.mipmap.icon_wireless_internet_access);
        equipmentResponse1.setName("WI-FI");
        EquipmentResponse equipmentResponse2 = new EquipmentResponse();
        equipmentResponse2.setImage(R.mipmap.icon_necessaries);
        equipmentResponse2.setName("生活必需品");
        EquipmentResponse equipmentResponse3 = new EquipmentResponse();
        equipmentResponse3.setImage(R.mipmap.icon_hair_drier);
        equipmentResponse3.setName("吹风机");
        EquipmentResponse equipmentResponse4 = new EquipmentResponse();
        equipmentResponse4.setImage(R.mipmap.icon_television);
        equipmentResponse4.setName("电视机");
        EquipmentResponse equipmentResponse5 = new EquipmentResponse();
        equipmentResponse5.setImage(R.mipmap.icon_washing_machine);
        equipmentResponse5.setName("洗衣机");
        EquipmentResponse equipmentResponse6 = new EquipmentResponse();
        equipmentResponse6.setImage(R.mipmap.icon_air_conditioner);
        equipmentResponse6.setName("空调");
        /*EquipmentResponse equipmentResponse7 = new EquipmentResponse();
        equipmentResponse7.setImage(R.mipmap.icon_washing_machine);
        equipmentResponse7.setName("洗衣机");
        EquipmentResponse equipmentResponse8 = new EquipmentResponse();
        equipmentResponse8.setImage(R.mipmap.icon_air_conditioner);
        equipmentResponse8.setName("空调");*/
        equipmentResponseList.add(equipmentResponse);
        equipmentResponseList.add(equipmentResponse1);
        equipmentResponseList.add(equipmentResponse2);
        equipmentResponseList.add(equipmentResponse3);
        equipmentResponseList.add(equipmentResponse4);
        equipmentResponseList.add(equipmentResponse5);
        equipmentResponseList.add(equipmentResponse6);
      /*  equipmentResponseList.add(equipmentResponse7);
        equipmentResponseList.add(equipmentResponse8);*/
        return equipmentResponseList;
    }

    //获取酒店详情
    public void getMchHomestayDetail() {

        mPresenter.getMchHomestayDetail(mchId);
    }

}
