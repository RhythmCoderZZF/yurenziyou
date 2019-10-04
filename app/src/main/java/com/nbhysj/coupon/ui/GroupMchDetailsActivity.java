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
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.GroupMchListMoreAdapter;
import com.nbhysj.coupon.adapter.GroupMchPackageMealItemAdapter;
import com.nbhysj.coupon.adapter.GroupMchTicketAdapter;
import com.nbhysj.coupon.adapter.ScenicSpotDetailUserCommentAdapter;
import com.nbhysj.coupon.adapter.UserCommentAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.common.Enum.MchTypeEnum;
import com.nbhysj.coupon.contract.GroupMchContract;
import com.nbhysj.coupon.dialog.PurchaseInstructionsDialog;
import com.nbhysj.coupon.dialog.ShareOprateDialog;
import com.nbhysj.coupon.model.GroupMchModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.GroupMchDetailsResponse;
import com.nbhysj.coupon.model.response.GroupMchResponse;
import com.nbhysj.coupon.model.response.LabelEntity;
import com.nbhysj.coupon.model.response.MchCommentEntity;
import com.nbhysj.coupon.model.response.MchDetailsResponse;
import com.nbhysj.coupon.model.response.MchGoodsBean;
import com.nbhysj.coupon.presenter.GroupMchPresenter;
import com.nbhysj.coupon.systembar.StatusBarCompat;
import com.nbhysj.coupon.systembar.StatusBarUtil;
import com.nbhysj.coupon.util.PopupWindowUtil;
import com.nbhysj.coupon.view.ExpandableTextView;
import com.nbhysj.coupon.view.RecyclerScrollView;
import com.nbhysj.coupon.view.ScenicSpotDetailBannerView;
import com.nbhysj.coupon.view.StarBarView;
import com.nbhysj.coupon.widget.NestedExpandaleListView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/09/03
 * description：组合详情
 */
public class GroupMchDetailsActivity extends BaseActivity<GroupMchPresenter, GroupMchModel> implements GroupMchContract.View, RecyclerScrollView.OnScrollListener {
    @BindView(R.id.toolbar_space)
    View mToolbarSpace;
    @BindView(R.id.banner)
    ScenicSpotDetailBannerView bannerView;
    //组合商品名
    @BindView(R.id.tv_mch_group_name)
    TextView mTvMchGroupName;
    //组合商品价格
    @BindView(R.id.tv_group_mch_price)
    TextView mTvGroupMchPrice;
    //组合商品评分
    @BindView(R.id.tv_group_mch_score)
    TextView mTvGroupMchScore;
    //星级评分
    @BindView(R.id.starbar)
    StarBarView mStarBarView;
    //组合商品简介
    @BindView(R.id.tv_group_mch_intro)
    ExpandableTextView mTvExpandableMchIntro;
    //组合商品标签
    @BindView(R.id.flowlayout_group_mch_label)
    TagFlowLayout mTagFlowlayoutGroupMchLabel;
    //评价数
    @BindView(R.id.tv_evaluate_num)
    TextView mTvEvaluateNum;
    //用户评论数量
    @BindView(R.id.tv_user_comment_num)
    TextView mTvUserCommentNum;
    @BindView(R.id.rv_user_comment_search_tag)
    RecyclerView mRvUserCommentSearchTag;
    @BindView(R.id.tv_banner_num)
    TextView mTvBannerNum;
    //问题数量
    @BindView(R.id.tv_question_num)
    TextView mTvQuestionNum;
    //问题内容
    @BindView(R.id.tv_question_content)
    TextView mTvQuestionContent;
    //回答数量
    @BindView(R.id.tv_answer_num)
    TextView mTvAnswerNum;
    @BindView(R.id.expandable_list_admission_ticket)
    NestedExpandaleListView mExpandableListGroupMchTicket;
    @BindView(R.id.scrollview_scenic_spot_detail)
    RecyclerScrollView mScrollViewScenicSpotDetail;
    @BindView(R.id.ibtn_back)
    ImageButton mImgBtnBack;
    @BindView(R.id.rlyt_scenic_spots_detail_header)
    RelativeLayout mRlytScenicSpostsDetail;
    //收藏
    @BindView(R.id.img_collection)
    ImageView mImgCollection;
    @BindView(R.id.img_menu)
    ImageView mImageMenu;
    //详情页转发
    @BindView(R.id.img_scenic_spot_forward)
    ImageView mImgScenicSpotForward;
    //预定时间
    @BindView(R.id.tv_booking_info)
    TextView mTvBookingInfo;
    //组合详情
    @BindView(R.id.rv_group_mch_package_meal_detail)
    RecyclerView mRvGroupMchPackageMealDetail;
    //用户评论
    @BindView(R.id.llyt_user_comment)
    LinearLayout mLlytUserComment;
    //更多组合列表
    @BindView(R.id.rv_group_mch_more)
    RecyclerView mRvGroupMchListMore;
    @BindView(R.id.rv_user_comment)
    RecyclerView mRvUserComment;
    //用户评论标签
    List<LabelEntity> labelEntityList;
    private GroupMchDetailsResponse mchDetailsResponse;

    //组合商户详情
    private GroupMchDetailsResponse.MchDetailsEntity mchDetailsEntity;

    //评论列表
    List<MchCommentEntity> commentList;

    //商户名
    private String mchName;
    private UserCommentAdapter userCommentAdapter;

    private ScenicSpotDetailUserCommentAdapter scenicSpotDetailUserCommentAdapter;

    private List<ImageView> viewList;
    private List<String> bannerList;
    //偏移高度
    private int height;
    private PopupWindow mPopupWindow;
    //组合包id
    private int packageId;

    //组合套餐详情
    private List<GroupMchDetailsResponse.ContentEntity> groupMchPackageMealList;

    //组合套餐详情适配器
    GroupMchPackageMealItemAdapter groupMchPackageMealItemAdapter;

    //更多组合
    private List<GroupMchDetailsResponse.NearbyFoodEntity> groupMchMoreList;

    private GroupMchListMoreAdapter groupMchListMoreAdapter;

    //购买须知弹框
    private PurchaseInstructionsDialog mPurchaseInstructionsDialog;

    @Override
    public int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusBarCompat.translucentStatusBar(this, false);
        //修改状态栏字体颜色
        StatusBarUtil.setImmersiveStatusBar(this, true);
        return R.layout.activity_group_mch_detail;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        packageId = getIntent().getIntExtra("packageId", 0);
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
        if (commentList == null) {

            commentList = new ArrayList<>();
        } else {

            commentList.clear();
        }

        if (labelEntityList == null) {

            labelEntityList = new ArrayList<>();

        } else {

            labelEntityList.clear();
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

        if(groupMchPackageMealList == null){

            groupMchPackageMealList = new ArrayList<>();
        } else {
            groupMchPackageMealList.clear();
        }

        if(groupMchMoreList == null){

            groupMchMoreList = new ArrayList<>();
        } else {
            groupMchMoreList.clear();
        }

        GridLayoutManager linearLayoutManager1 = new GridLayoutManager(GroupMchDetailsActivity.this, 3);
        linearLayoutManager1.setOrientation(linearLayoutManager1.VERTICAL);
        mRvUserCommentSearchTag.setLayoutManager(linearLayoutManager1);
        userCommentAdapter = new UserCommentAdapter(GroupMchDetailsActivity.this);
        userCommentAdapter.setLabelList(labelEntityList);
        mRvUserCommentSearchTag.setAdapter(userCommentAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(GroupMchDetailsActivity.this);
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        mRvGroupMchPackageMealDetail.setLayoutManager(linearLayoutManager);
        groupMchPackageMealItemAdapter = new GroupMchPackageMealItemAdapter(GroupMchDetailsActivity.this);
        groupMchPackageMealItemAdapter.setGroupMchPackageMealList(groupMchPackageMealList);
        mRvGroupMchPackageMealDetail.setAdapter(groupMchPackageMealItemAdapter);

        LinearLayoutManager userCommentLayoutManager = new LinearLayoutManager(GroupMchDetailsActivity.this);
        userCommentLayoutManager.setOrientation(userCommentLayoutManager.VERTICAL);
        mRvUserComment.setLayoutManager(userCommentLayoutManager);
        scenicSpotDetailUserCommentAdapter = new ScenicSpotDetailUserCommentAdapter(GroupMchDetailsActivity.this);
        scenicSpotDetailUserCommentAdapter.setScenicSpotsUserCommentList(commentList);
        mRvUserComment.setAdapter(scenicSpotDetailUserCommentAdapter);

        LinearLayoutManager groupMchListMoreLinearLayoutManager = new LinearLayoutManager(GroupMchDetailsActivity.this);
        groupMchListMoreLinearLayoutManager.setOrientation(groupMchListMoreLinearLayoutManager.VERTICAL);
        mRvGroupMchListMore.setLayoutManager(groupMchListMoreLinearLayoutManager);
        groupMchListMoreAdapter = new GroupMchListMoreAdapter(GroupMchDetailsActivity.this);
        groupMchListMoreAdapter.setGroupMchListMore(groupMchMoreList);
        mRvGroupMchListMore.setAdapter(groupMchListMoreAdapter);

    }

    @Override
    public void initData() {
        //获取顶部图片高度后，设置滚动监听
        ViewTreeObserver vto = bannerView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                bannerView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                height = bannerView.getHeight();
                bannerView.getWidth();

                mScrollViewScenicSpotDetail.setScrolListener(GroupMchDetailsActivity.this);
            }
        });

        showProgressDialog(GroupMchDetailsActivity.this);
        getGroupMchDetail();
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    public void getGroupMchHomePageResult(BackResult<GroupMchResponse> res) {

    }

    @Override
    public void getGroupMchDetailResult(BackResult<GroupMchDetailsResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    mchDetailsResponse = res.getData();

                    mchDetailsEntity = mchDetailsResponse.getMchDetails();
                    GroupMchDetailsResponse.MchQuestionEntity mchQuestionEntity = mchDetailsResponse.getMchQuestion(); //问题
                    GroupMchDetailsResponse.CommentEntity commentEntity = mchDetailsResponse.getComment();
                    groupMchMoreList = mchDetailsResponse.getNearbyFood();  //更多组合
                    List<MchGoodsBean> mchGoodsList = mchDetailsResponse.getMchGoods();     //商品展示列表
                    groupMchPackageMealList = mchDetailsEntity.getContent();

                    commentList = commentEntity.getComment();
                    mchName = mchDetailsEntity.getMchName();
                    //评价
                    int commentNum = mchDetailsEntity.getCommentNum();
                    double mConsumePrice = mchDetailsEntity.getConsumePrice();
                    float mCommentScore = mchDetailsEntity.getCommentScore();
                    List<GroupMchDetailsResponse.TagsEntity> tagsEntityList = mchDetailsEntity.getTags();

                    //banner
                    List<String> bannerList = mchDetailsEntity.getRecommendPhoto();

                    if (bannerList.size() > 0) {

                        for (int i = 0; i < bannerList.size(); i++) {
                            ImageView image = new ImageView(GroupMchDetailsActivity.this);
                            image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                            //设置显示格式
                            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            viewList.add(image);
                        }
                    }

                    mTvBannerNum.setText("1/" + bannerList.size() + "张");
                    mTvBannerNum.getBackground().setAlpha(50);
                    //bannerView.startLoop(true);
                    bannerView.setViewList(GroupMchDetailsActivity.this, viewList, bannerList, new ScenicSpotDetailBannerView.ScenicSpotDetailBannerViewListener() {
                        @Override
                        public void setScenicSpotDetailBannerViewListener(int curPos) {

                            mTvBannerNum.setText(curPos + "/" + bannerList.size() + "张");
                        }

                        @Override
                        public void bannerOnclickListener() {

                        /*    Intent intent = new Intent();
                            intent.putExtra("packageId", mchId);
                            intent.setClass(GroupMchDetailsActivity.this, ScenicSpotsAlbumActivity.class);
                            startActivity(intent);*/

                        }
                    });

                    mTvMchGroupName.setText(mchName);
                    mTvGroupMchPrice.setText(String.valueOf(mConsumePrice));
                    mTvGroupMchScore.setText(String.valueOf(mCommentScore));
                    mStarBarView.setIntegerMark(false);

                    mStarBarView.setStarMark(mCommentScore);
                    mTvEvaluateNum.setText(commentNum + "评价");

                    mTvExpandableMchIntro.setText(mchDetailsEntity.getIntro());

                    GroupMchDetailsResponse.ScoreEntity scoreEntity = commentEntity.getScore();
                    labelEntityList = commentEntity.getLabel();
                    int mCommentNum = scoreEntity.getCommentNum();
                    mTvUserCommentNum.setText("用户评论(" + mCommentNum + ")");

                    userCommentAdapter.setLabelList(labelEntityList);
                    userCommentAdapter.notifyDataSetChanged();

                    if (commentList != null)
                    {
                        mLlytUserComment.setVisibility(View.VISIBLE);
                        scenicSpotDetailUserCommentAdapter.setScenicSpotsUserCommentList(commentList);
                        scenicSpotDetailUserCommentAdapter.notifyDataSetChanged();
                    } else {

                        mLlytUserComment.setVisibility(View.GONE);
                    }


                    if (tagsEntityList != null) {

                        mTagFlowlayoutGroupMchLabel.setAdapter(new TagAdapter<GroupMchDetailsResponse.TagsEntity>(tagsEntityList) {

                            @Override
                            public View getView(FlowLayout parent, int position, GroupMchDetailsResponse.TagsEntity tagsEntity) {
                                LayoutInflater mInflater = LayoutInflater.from(mContext);
                                TextView tagName = (TextView) mInflater.inflate(R.layout.layout_flowlayout_tag_orange_mch_detail_frame,
                                        mTagFlowlayoutGroupMchLabel, false);
                                tagName.setText(tagsEntity.getTitle());
                                return tagName;
                            }
                        });
                    }

                    //预定时间
                    String bookingInfo = mchDetailsEntity.getBookingInfo();
                    mTvBookingInfo.setText("预定时间：" + bookingInfo);

                    GroupMchTicketAdapter myExpandableAdapter = new GroupMchTicketAdapter(this, mchGoodsList, new GroupMchTicketAdapter.GroupMchTicketListener() {
                        @Override
                        public void setGroupMchTicketCallback(int groupPosition, int childPosition,String goodsBuyNotes) {

                            if (mPurchaseInstructionsDialog == null)
                            {
                                MchGoodsBean mchGoodsBean = mchGoodsList.get(childPosition);
                                String mchName = mchDetailsEntity.getMchName();

                                String mchType = MchTypeEnum.MCH_GROUP.getValue();
                                mPurchaseInstructionsDialog = new PurchaseInstructionsDialog(mchGoodsBean,mchType,mchName);
                            }
                            mPurchaseInstructionsDialog.show(getFragmentManager(), "组合商品购票须知");
                        }
                    });
                    mExpandableListGroupMchTicket.setAdapter(myExpandableAdapter);

                    String mQuestionContent = mchQuestionEntity.getQuestionContent();  //问题内容
                    int questionCount = mchQuestionEntity.getQuestionCount();
                    mTvQuestionNum.setText(String.valueOf(questionCount) + "个问题>");

                    if(!TextUtils.isEmpty(mQuestionContent))
                    {
                        mTvQuestionContent.setText(mQuestionContent);
                    }

                    mTvAnswerNum.setText(String.valueOf(mchQuestionEntity.getAnswerCount()) + "个答案");

                    //套餐详情
                    if(groupMchPackageMealList != null)
                    {
                        groupMchPackageMealItemAdapter.setGroupMchPackageMealList(groupMchPackageMealList);
                        groupMchPackageMealItemAdapter.notifyDataSetChanged();
                    }

                    //更多组合
                    if(groupMchMoreList != null)
                    {
                        groupMchListMoreAdapter.setGroupMchListMore(groupMchMoreList);
                        groupMchListMoreAdapter.notifyDataSetChanged();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(GroupMchDetailsActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
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

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(GroupMchDetailsActivity.this, Constants.getResultMsg(msg));
    }

    @OnClick({R.id.ibtn_back, R.id.img_menu, R.id.rlyt_scenic_spot_location, R.id.img_scenic_spot_forward, R.id.tv_question_num})
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.ibtn_back:
                GroupMchDetailsActivity.this.finish();
                break;

            case R.id.img_menu:
                showPopupWindow(mImageMenu);
                break;
            case R.id.rlyt_scenic_spot_location:
                Bundle bundle = new Bundle();

                bundle.putSerializable("mchDetailsEntity", mchDetailsEntity);
                intent.putExtras(bundle);
                intent.setClass(GroupMchDetailsActivity.this, ScenicSpotsDetailLocationMapActivity.class);
                startActivity(intent);
                break;
            case R.id.img_scenic_spot_forward:

                ShareOprateDialog shareOprateDialog = new ShareOprateDialog(GroupMchDetailsActivity.this, new ShareOprateDialog.OnSharePlatformItemClickListener() {
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

    //获取组合列表
    public void getGroupMchDetail()
    {
        if (validateInternet()) {

            mPresenter.getGroupMchDetail(packageId);
        }
    }
}
