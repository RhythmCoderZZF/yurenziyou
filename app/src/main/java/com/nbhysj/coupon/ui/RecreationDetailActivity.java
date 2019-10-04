package com.nbhysj.coupon.ui;

import android.content.Intent;
import android.graphics.Color;
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
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.AdmissionTicketExpandableAdapter;
import com.nbhysj.coupon.adapter.GroupListAdapter;
import com.nbhysj.coupon.adapter.NearbyScenicSpotAdapter;
import com.nbhysj.coupon.adapter.PlayGuideAdapter;
import com.nbhysj.coupon.adapter.ScenicSpotDetailUserCommentAdapter;
import com.nbhysj.coupon.adapter.UserCommentAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.ScenicSpotContract;
import com.nbhysj.coupon.dialog.ShareOprateDialog;
import com.nbhysj.coupon.model.ScenicSpotModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.HomePageResponse;
import com.nbhysj.coupon.model.response.LabelEntity;
import com.nbhysj.coupon.model.response.MchAlbumResponse;
import com.nbhysj.coupon.model.response.MchBangDanRankingResponse;
import com.nbhysj.coupon.model.response.MchCommentEntity;
import com.nbhysj.coupon.model.response.MchDetailsResponse;
import com.nbhysj.coupon.model.response.MchGoodsBean;
import com.nbhysj.coupon.model.response.NearbyTypeResponse;
import com.nbhysj.coupon.model.response.NetFriendAlbumResponse;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.model.response.ScenicSpotResponse;
import com.nbhysj.coupon.model.response.TourGuideBean;
import com.nbhysj.coupon.presenter.ScenicSpotPresenter;
import com.nbhysj.coupon.systembar.StatusBarCompat;
import com.nbhysj.coupon.systembar.StatusBarUtil;
import com.nbhysj.coupon.util.PopupWindowUtil;
import com.nbhysj.coupon.view.ExpandableTextView;
import com.nbhysj.coupon.view.RecyclerScrollView;
import com.nbhysj.coupon.view.ScenicSpotDetailBannerView;
import com.nbhysj.coupon.view.StarBarView;
import com.nbhysj.coupon.widget.NearbyTabIndicator;
import com.nbhysj.coupon.widget.NestedExpandaleListView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/04/28
 * description：景点详情
 */
public class ScenicSpotDetailActivity extends BaseActivity<ScenicSpotPresenter, ScenicSpotModel> implements ScenicSpotContract.View, RecyclerScrollView.OnScrollListener {
    @BindView(R.id.toolbar_space)
    View mToolbarSpace;
    @BindView(R.id.banner)
    ScenicSpotDetailBannerView bannerView;
    @BindView(R.id.tv_banner_num)
    TextView mTvBannerNum;
    //景点标签
    @BindView(R.id.flowlayout_scenic_spot_label)
    TagFlowLayout mTagFlowlayoutScenicSpotLabel;
    @BindView(R.id.scrollview_scenic_spot_detail)
    RecyclerScrollView mScrollViewScenicSpotDetail;
    @BindView(R.id.ibtn_back)
    ImageButton mImgBtnBack;
    @BindView(R.id.rlyt_scenic_spots_detail_header)
    RelativeLayout mRlytScenicSpostsDetail;
    //收藏
    @BindView(R.id.img_collection)
    ImageView mImgCollection;
    //游玩指南
    @BindView(R.id.rv_play_guide)
    RecyclerView mRvPlayGuide;
    @BindView(R.id.expandable_list_admission_ticket)
    NestedExpandaleListView mExpandableListTicket;
    @BindView(R.id.rv_user_comment_search_tag)
    RecyclerView mRvUserCommentSearchTag;
    @BindView(R.id.rv_user_comment)
    RecyclerView mRvUserComment;
    @BindView(R.id.indicator)
    NearbyTabIndicator mTabIndicator;
    @BindView(R.id.rv_near_the_scenic_spot)
    RecyclerView mRvNearScenicSpot;
    @BindView(R.id.img_menu)
    ImageView mImageMenu;
    //详情页转发
    @BindView(R.id.img_scenic_spot_forward)
    ImageView mImgScenicSpotForward;
    //景点名字
    @BindView(R.id.tv_scenic_spot_name)
    TextView mTvScenicSpotName;
    //价格
    @BindView(R.id.tv_per_capita_price)
    TextView mTvPerCapitaPrice;
    //评分
    @BindView(R.id.tv_comment_score)
    TextView mTvCommentScore;
    //星级
    @BindView(R.id.starbar)
    StarBarView mStarBarView;
    //简介
    @BindView(R.id.tv_intro)
    ExpandableTextView mTvIntro;
    //评价数量
    @BindView(R.id.tv_evaluate_num)
    TextView mTvEvaluateNum;
    //位置
    @BindView(R.id.tv_scenic_spot_location)
    TextView mTvScenicSpotLocation;
    //排行榜
    @BindView(R.id.tv_mch_ranking)
    TextView mTvMchRanking;
    //优待政策
    @BindView(R.id.tv_discount_info)
    TextView mTvDiscountInfo;
    //开放时间
    @BindView(R.id.tv_opening_hours)
    TextView mTvOpeningHours;
    //问题数量
    @BindView(R.id.tv_question_num)
    TextView mTvQuestionNum;
    //问题内容
    @BindView(R.id.tv_question_content)
    TextView mTvQuestionContent;
    //回答数量
    @BindView(R.id.tv_answer_num)
    TextView mTvAnswerNum;
    //用户评论数量
    @BindView(R.id.tv_user_comment_num)
    TextView mTvUserCommentNum;
    //位置
    @BindView(R.id.rlyt_scenic_spot_location)
    RelativeLayout mRlytScenicSpotLocation;
    private int height;
    private List<ImageView> viewList;
    private List<String> bannerList;
    private PopupWindow mPopupWindow;
    private PlayGuideAdapter playGuideAdapter;
    MchDetailsResponse mchDetailsResponse;
    MchDetailsResponse.MchDetailsEntity mchDetailsEntity;
    private List<MchDetailsResponse.VisitGuideEntity> visitGuideList;
    //用户评论标签
    List<LabelEntity> labelEntityList;
    //评论列表
    List<MchCommentEntity> commentList;
    //景点列表
    List<NearbyTypeResponse> nearbyScenicSpotsList;
    //组合
    List<NearbyTypeResponse> groupGoodsList;
    private UserCommentAdapter userCommentAdapter;
    //景点
    private NearbyScenicSpotAdapter nearbyScenicSpotAdapter;
    private ScenicSpotDetailUserCommentAdapter scenicSpotDetailUserCommentAdapter;
    private GroupListAdapter groupListAdapter;
    //商户名
    private int mchId;
    private String address;
    //商户名
    private String mchName;
    //纬度
    String latitude;
    //经度
    String longitude;

    //查看全部景点信息
    private String mchByNotesH5Url;

    @Override
    public int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusBarCompat.translucentStatusBar(this, false);
        //修改状态栏字体颜色
        StatusBarUtil.setImmersiveStatusBar(this, true);
        return R.layout.activity_scenic_spot_detail;
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
        if (visitGuideList == null) {

            visitGuideList = new ArrayList<>();
        } else {
            visitGuideList.clear();
        }

        if (labelEntityList == null) {

            labelEntityList = new ArrayList<>();

        } else {

            labelEntityList.clear();
        }

        if (commentList == null) {

            commentList = new ArrayList<>();
        } else {

            commentList.clear();
        }

        if (nearbyScenicSpotsList == null) {

            nearbyScenicSpotsList = new ArrayList<>();
        } else {
            nearbyScenicSpotsList.clear();
        }

        if (groupGoodsList == null) {

            groupGoodsList = new ArrayList<>();
        } else {
            groupGoodsList.clear();
        }

        if(viewList == null) {
            viewList = new ArrayList<ImageView>();
        } else {
            viewList.clear();
        }
        if(bannerList == null) {
            bannerList = new ArrayList<>();
        } else{
            bannerList.clear();
        }

    }

    @Override
    public void initData() {
        showProgressDialog(ScenicSpotDetailActivity.this);
        getMchDetails();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ScenicSpotDetailActivity.this);
        linearLayoutManager.setOrientation(linearLayoutManager.HORIZONTAL);
        mRvPlayGuide.setLayoutManager(linearLayoutManager);
        playGuideAdapter = new PlayGuideAdapter(ScenicSpotDetailActivity.this);
        playGuideAdapter.setVisitGuideEntityList(visitGuideList);
        mRvPlayGuide.setAdapter(playGuideAdapter);

        GridLayoutManager linearLayoutManager1 = new GridLayoutManager(ScenicSpotDetailActivity.this, 3);
        linearLayoutManager1.setOrientation(linearLayoutManager1.VERTICAL);
        mRvUserCommentSearchTag.setLayoutManager(linearLayoutManager1);
        userCommentAdapter = new UserCommentAdapter(ScenicSpotDetailActivity.this);
        userCommentAdapter.setLabelList(labelEntityList);
        mRvUserCommentSearchTag.setAdapter(userCommentAdapter);

        List<HomePageResponse.SmallTagEntity> nearbyLabelList = new ArrayList();
        HomePageResponse.SmallTagEntity smallTagEntity = new HomePageResponse().new SmallTagEntity();
        smallTagEntity.setTitle("酒店");

        HomePageResponse.SmallTagEntity smallTagEntity1 = new HomePageResponse().new SmallTagEntity();
        smallTagEntity1.setTitle("组合");

        HomePageResponse.SmallTagEntity smallTagEntity2 = new HomePageResponse().new SmallTagEntity();
        smallTagEntity2.setTitle("景点");

        HomePageResponse.SmallTagEntity smallTagEntity3 = new HomePageResponse().new SmallTagEntity();
        smallTagEntity3.setTitle("美食");

        nearbyLabelList.add(smallTagEntity);
        nearbyLabelList.add(smallTagEntity1);
        nearbyLabelList.add(smallTagEntity2);
        nearbyLabelList.add(smallTagEntity3);

        mTabIndicator.initTab(nearbyLabelList, 15);
        mTabIndicator.setmTabSelector(0);

        mTabIndicator.setMyOnPageChangeListener(new NearbyTabIndicator.MyOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                MchDetailsResponse.NearbyEntity nearbyEntity = mchDetailsResponse.getNearby();
                List<NearbyTypeResponse> groupGoodsList = nearbyEntity.getGroup();
                List<NearbyTypeResponse> scenicSpotList = nearbyEntity.getScenic();
                List<NearbyTypeResponse> foodList = nearbyEntity.getFood();
                List<NearbyTypeResponse> hotelList = nearbyEntity.getHotel();
                if (position == 0) {
                    if (hotelList != null) {
                        nearbyScenicSpotAdapter.setNearbyScenicSpotsList(hotelList);
                        mRvNearScenicSpot.setAdapter(nearbyScenicSpotAdapter);
                    }
                } else if (position == 1) {
                    if (groupGoodsList != null) {
                        groupListAdapter.setGroupList(groupGoodsList);
                        mRvNearScenicSpot.setAdapter(groupListAdapter);
                    }
                } else if (position == 2) {
                    if (scenicSpotList != null) {
                        nearbyScenicSpotAdapter.setNearbyScenicSpotsList(scenicSpotList);
                        mRvNearScenicSpot.setAdapter(nearbyScenicSpotAdapter);
                    }
                } else if (position == 3) {
                    if (foodList != null) {
                        nearbyScenicSpotAdapter.setNearbyScenicSpotsList(foodList);
                        mRvNearScenicSpot.setAdapter(nearbyScenicSpotAdapter);
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //获取顶部图片高度后，设置滚动监听
        ViewTreeObserver vto = bannerView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                bannerView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                height = bannerView.getHeight();
                bannerView.getWidth();

                mScrollViewScenicSpotDetail.setScrolListener(ScenicSpotDetailActivity.this);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(ScenicSpotDetailActivity.this);
        layoutManager.setOrientation(layoutManager.VERTICAL);
        mRvNearScenicSpot.setLayoutManager(layoutManager);

        groupListAdapter = new GroupListAdapter(ScenicSpotDetailActivity.this);
        groupListAdapter.setGroupList(groupGoodsList);
        mRvNearScenicSpot.setAdapter(groupListAdapter);

        nearbyScenicSpotAdapter = new NearbyScenicSpotAdapter(ScenicSpotDetailActivity.this);
        nearbyScenicSpotAdapter.setNearbyScenicSpotsList(nearbyScenicSpotsList);
        mRvNearScenicSpot.setAdapter(nearbyScenicSpotAdapter);

        LinearLayoutManager userCommentLayoutManager = new LinearLayoutManager(ScenicSpotDetailActivity.this);
        userCommentLayoutManager.setOrientation(userCommentLayoutManager.VERTICAL);
        mRvUserComment.setLayoutManager(userCommentLayoutManager);
        scenicSpotDetailUserCommentAdapter = new ScenicSpotDetailUserCommentAdapter(ScenicSpotDetailActivity.this);
        scenicSpotDetailUserCommentAdapter.setScenicSpotsUserCommentList(commentList);
        mRvUserComment.setAdapter(scenicSpotDetailUserCommentAdapter);


    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }


    @Override
    public void getScenicSpotHomePageResult(BackResult<ScenicSpotHomePageResponse> res) {

    }

    @Override
    public void findScenicByCateResult(BackResult<ScenicSpotResponse> res) {

    }

    @Override
    public void getScenicBangDanRankingResult(BackResult<MchBangDanRankingResponse> res) {

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
                    MchDetailsResponse.CommentEntity commentEntity = mchDetailsResponse.getComment();
                    MchDetailsResponse.NearbyEntity nearbyEntity = mchDetailsResponse.getNearby();  //附近
                    List<MchGoodsBean> mchGoodsList = mchDetailsResponse.getMchGoods();     //商品展示列表
                    //latitude = mchDetailsEntity.getLatitude();
                    //longitude = mchDetailsEntity.getLongitude();
                    commentList = commentEntity.getComment();
                    mchName = mchDetailsEntity.getMchName();
                    //A级
                    int level = mchDetailsEntity.getLevel();
                    //评价
                    int commentNum = mchDetailsEntity.getCommentNum();
                    double mConsumePrice = mchDetailsEntity.getConsumePrice();
                    float mCommentScore = mchDetailsEntity.getCommentScore();
                    List<MchDetailsResponse.TagsEntity> tagsEntityList = mchDetailsEntity.getTags();
                    mTvScenicSpotName.setText(mchName + "(" + level + "A)");
                    mTvPerCapitaPrice.setText(String.valueOf(mConsumePrice));
                    mTvCommentScore.setText(String.valueOf(mCommentScore));
                    mStarBarView.setIntegerMark(false);

                    mStarBarView.setStarMark(mCommentScore);
                    mTvEvaluateNum.setText(commentNum + "评价");

                    mTvIntro.setText(mchDetailsEntity.getIntro());

                    MchDetailsResponse.ScoreEntity scoreEntity = commentEntity.getScore();
                    labelEntityList = commentEntity.getLabel();
                    int mCommentNum = scoreEntity.getCommentNum();
                    mTvUserCommentNum.setText("用户评论(" + mCommentNum + ")");

                    userCommentAdapter.setLabelList(labelEntityList);
                    userCommentAdapter.notifyDataSetChanged();

                    if (commentList != null) {
                        scenicSpotDetailUserCommentAdapter.setScenicSpotsUserCommentList(commentList);
                        scenicSpotDetailUserCommentAdapter.notifyDataSetChanged();
                    }
                    //banner
                    List<String> bannerList = mchDetailsEntity.getRecommendPhoto();

                    nearbyScenicSpotsList = nearbyEntity.getHotel();
                    if (nearbyScenicSpotsList != null) {
                        nearbyScenicSpotAdapter.setNearbyScenicSpotsList(nearbyScenicSpotsList);
                        nearbyScenicSpotAdapter.notifyDataSetChanged();
                    }
                    if (bannerList.size() > 0) {

                        for (int i = 0; i < bannerList.size(); i++) {
                            ImageView image = new ImageView(ScenicSpotDetailActivity.this);
                            image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                            //设置显示格式
                            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            viewList.add(image);
                        }
                    }

                    mTvBannerNum.setText("1/" + bannerList.size() + "张");
                    mTvBannerNum.getBackground().setAlpha(50);
                    //bannerView.startLoop(true);
                    bannerView.setViewList(ScenicSpotDetailActivity.this, viewList, bannerList, new ScenicSpotDetailBannerView.ScenicSpotDetailBannerViewListener() {
                        @Override
                        public void setScenicSpotDetailBannerViewListener(int curPos) {

                            mTvBannerNum.setText(curPos + "/" + bannerList.size() + "张");
                        }

                        @Override
                        public void bannerOnclickListener() {

                            Intent intent = new Intent();
                            intent.putExtra("mchId", mchId);
                            intent.setClass(ScenicSpotDetailActivity.this, ScenicSpotsAlbumActivity.class);
                            startActivity(intent);

                        }
                    });

                    if (tagsEntityList != null) {

                        mTagFlowlayoutScenicSpotLabel.setAdapter(new TagAdapter<MchDetailsResponse.TagsEntity>(tagsEntityList) {

                            @Override
                            public View getView(FlowLayout parent, int position, MchDetailsResponse.TagsEntity tagsEntity) {
                                LayoutInflater mInflater = LayoutInflater.from(mContext);
                                TextView tagName = (TextView) mInflater.inflate(R.layout.layout_flowlayout_tag_orange_mch_detail_frame,
                                        mTagFlowlayoutScenicSpotLabel, false);
                                tagName.setText(tagsEntity.getTitle());
                                return tagName;
                            }
                        });
                    }
                    address = mchDetailsEntity.getAddress();

                    mTvScenicSpotLocation.setText(address);
                    mTvOpeningHours.setText(mchDetailsEntity.getOpenTime());
                    mTvMchRanking.setText(mchDetailsEntity.getMchRanking());
                    mTvQuestionNum.setText(String.valueOf(mchQuestionEntity.getQuestionCount()) + "个问题>");
                    String questionContent = mchQuestionEntity.getQuestionContent();
                    if(!TextUtils.isEmpty(questionContent))
                    {
                        mTvQuestionContent.setText(questionContent);
                    }

                    mTvAnswerNum.setText(String.valueOf(mchQuestionEntity.getAnswerCount()) + "个答案");

                    List<MchDetailsResponse.VisitGuideEntity> visitGuideList = mchDetailsResponse.getVisitGuide();
                    if (visitGuideList != null) {
                        playGuideAdapter.setVisitGuideEntityList(visitGuideList);
                        playGuideAdapter.notifyDataSetChanged();
                    }

                    AdmissionTicketExpandableAdapter myExpandableAdapter = new AdmissionTicketExpandableAdapter(this, mchGoodsList);
                    mExpandableListTicket.setAdapter(myExpandableAdapter);

                    mchByNotesH5Url = mchDetailsResponse.getMchByNotes();

                    //优待政策
                    String discountInfo = mchDetailsEntity.getDiscountInfo();
                    if(!TextUtils.isEmpty(discountInfo))
                    {
                        mTvDiscountInfo.setText(discountInfo);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(ScenicSpotDetailActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getTourGuideListResult(BackResult<List<TourGuideBean>> res) {

    }

    @Override
    public void getMchAlbumListResult(BackResult<MchAlbumResponse> res) {

    }

    @Override
    public void getNetFriendAlbumListResult(BackResult<NetFriendAlbumResponse> res) {

    }

    @Override
    public void showMsg(String msg) {
        dismissProgressDialog();
        showToast(ScenicSpotDetailActivity.this, Constants.getResultMsg(msg));
    }

    @Override
    public void onScroll(int y) {
        if (y <= height && y >= 0) {
            float scale = (float) y / height;
            float alpha = (255 * scale);
//          Log.i("TAG","alpha--->"+alpha);

            //layout全部透明
//          layoutHead.setAlpha(scale);

            mRlytScenicSpostsDetail.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
            mToolbarSpace.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
            mImgBtnBack.setImageDrawable(getResources().getDrawable(R.mipmap.icon_left_arrow_black));
            mImgCollection.setImageResource(R.mipmap.icon_black_fine_food_collection);
            mImageMenu.setImageDrawable(getResources().getDrawable(R.mipmap.icon_black_menu_more));
            mImgScenicSpotForward.setImageDrawable(getResources().getDrawable(R.mipmap.icon_black_share));
            if (y <= 100) {
                mImgBtnBack.setImageDrawable(getResources().getDrawable(R.mipmap.icon_left_arrow_white));
                mImgCollection.setImageDrawable(getResources().getDrawable(R.mipmap.icon_white_fine_food_collection));
                mImageMenu.setImageDrawable(getResources().getDrawable(R.mipmap.icon_white_menu_more));
                mImgScenicSpotForward.setImageDrawable(getResources().getDrawable(R.mipmap.icon_white_share));
                mRlytScenicSpostsDetail.getBackground().setAlpha(255);
                mToolbarSpace.getBackground().setAlpha(255);
            }
        }
    }

    @OnClick({R.id.ibtn_back, R.id.rlyt_scenic_spots_ranking_list, R.id.img_menu, R.id.rlyt_scenic_spot_location, R.id.img_scenic_spot_forward, R.id.rlyt_view_more_tour_guide,
            R.id.tv_question_num, R.id.tv_look_all_scenic_spot_info})
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.ibtn_back:
                ScenicSpotDetailActivity.this.finish();
                break;
            case R.id.rlyt_scenic_spots_ranking_list:

                toActivity(ScenicSpotBangDanListActivity.class);

                break;
            case R.id.img_menu:
                showPopupWindow(mImageMenu);
                break;
            case R.id.rlyt_scenic_spot_location:
                Bundle bundle = new Bundle();

                bundle.putSerializable("mchDetailsEntity", mchDetailsEntity);
                intent.putExtras(bundle);
                intent.setClass(ScenicSpotDetailActivity.this, ScenicSpotsDetailLocationMapActivity.class);
                startActivity(intent);
                break;
            case R.id.rlyt_view_more_tour_guide:

                intent.putExtra("mchId", mchId);
                intent.setClass(ScenicSpotDetailActivity.this, PlayGuideActivity.class);
                toActivityWithParameters(PlayGuideActivity.class, intent);

                break;
            case R.id.img_scenic_spot_forward:

                ShareOprateDialog shareOprateDialog = new ShareOprateDialog(ScenicSpotDetailActivity.this, new ShareOprateDialog.OnSharePlatformItemClickListener() {
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
            case R.id.tv_question_num:

                toActivity(MoreQuestionsActivity.class);

                break;
            case R.id.tv_look_all_scenic_spot_info:

                if(!TextUtils.isEmpty(mchByNotesH5Url)) {

                    intent.putExtra("url", mchByNotesH5Url);
                    intent.putExtra("title", mchName);
                    intent.setClass(ScenicSpotDetailActivity.this, WebActivity.class);
                    startActivity(intent);

                }

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

    public void getMchDetails() {

        mPresenter.getMchDetails(mchId);
    }
}
