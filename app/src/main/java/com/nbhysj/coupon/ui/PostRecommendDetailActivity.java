package com.nbhysj.coupon.ui;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.FollowUsersOfInterestAdapter;
import com.nbhysj.coupon.adapter.HPFollowPostCommentAdapter;
import com.nbhysj.coupon.adapter.PraisePeopleAdapter;
import com.nbhysj.coupon.adapter.StrategyListAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.common.Enum.PostsTypeEnum;
import com.nbhysj.coupon.common.Enum.SharePlatformEnum;
import com.nbhysj.coupon.common.Enum.UploadFileTypeEnum;
import com.nbhysj.coupon.contract.HomePageContract;
import com.nbhysj.coupon.dialog.CollectEnterAlbumsDialog;
import com.nbhysj.coupon.dialog.OthersPostOprateDialog;
import com.nbhysj.coupon.dialog.ShareOprateDialog;
import com.nbhysj.coupon.framework.Net;
import com.nbhysj.coupon.model.HomePageModel;
import com.nbhysj.coupon.model.request.PostOprateRequest;
import com.nbhysj.coupon.model.request.PostsCollectionRequest;
import com.nbhysj.coupon.model.request.PostsCommentRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.FavoritesBean;
import com.nbhysj.coupon.model.response.FavoritesCollectionResponse;
import com.nbhysj.coupon.model.response.FavoritesListResponse;
import com.nbhysj.coupon.model.response.FollowUserStatusResponse;
import com.nbhysj.coupon.model.response.HomePageAllSearchResponse;
import com.nbhysj.coupon.model.response.HomePageResponse;
import com.nbhysj.coupon.model.response.HomePageTypeSearchResponse;
import com.nbhysj.coupon.model.response.MyPostShareBean;
import com.nbhysj.coupon.model.response.PostCommentBean;
import com.nbhysj.coupon.model.response.PostInfoDetailResponse;
import com.nbhysj.coupon.model.response.PraiseOrCollectResponse;
import com.nbhysj.coupon.model.response.RecommendInterestUsersBean;
import com.nbhysj.coupon.model.response.TopicsBean;
import com.nbhysj.coupon.model.response.ZanAvatersBean;
import com.nbhysj.coupon.pay.wechat.PayConstants;
import com.nbhysj.coupon.presenter.HomePagePresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.DateUtil;
import com.nbhysj.coupon.util.DensityUtils;
import com.nbhysj.coupon.util.DonwloadSaveImgUtil;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.ScreenUtil;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.nbhysj.coupon.util.blurbehind.BlurBehind;
import com.nbhysj.coupon.util.blurbehind.OnBlurCompleteListener;
import com.nbhysj.coupon.view.BannerSlideShowView;
import com.nbhysj.coupon.view.ExpandableTextView;
import com.nbhysj.coupon.widget.customjzvd.MyJzvdStd;
import com.nbhysj.coupon.widget.wavelineview.ExpandButtonLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
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
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.umeng.commonsdk.statistics.AnalyticsConstants.LOG_TAG;

/**
 * @auther：hysj created on 2019/03/02
 * description：帖子详情
 */
public class PostRecommendDetailActivity extends BaseActivity<HomePagePresenter, HomePageModel> implements HomePageContract.View {
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    //头像
    @BindView(R.id.image_user_avatar)
    CircleImageView mImgUserAvatar;

    //用户名
    @BindView(R.id.tv_user_name)
    TextView mTvUserName;

    @BindView(R.id.tv_time)
    TextView mTvTime;

    //轮播
    @BindView(R.id.banner_view_friend_detail_picture)
    BannerSlideShowView mBannerViewFriendDetailPicture;

    //帖子描述
    @BindView(R.id.tv_expand_post_description)
    ExpandableTextView mExpandPostDescription;

    //浏览数
    @BindView(R.id.tv_browse_num)
    TextView mTvBrowseNum;

    //赞
    @BindView(R.id.rlyt_praise)
    RelativeLayout mRlytPraise;

    //点赞的用户
    @BindView(R.id.rv_praise_people_num)
    RecyclerView mRvPraisePeopleNum;

    //点赞用户
    @BindView(R.id.rlyt_praise_people_num)
    RelativeLayout mRlytPraisePeopleNum;

    //点赞人数
    @BindView(R.id.tv_praise_people_num)
    TextView mTvPraisePeopleNum;

    @BindView(R.id.img_post_share)
    ImageView mImgPostShare;

    @BindView(R.id.flowlayout_tag)
    TagFlowLayout mTagFlowLayout;

    //评论数
    @BindView(R.id.tv_post_comment_num)
    TextView mTvPostCommentNum;

    //是否收藏帖子
    @BindView(R.id.img_is_collection_posts)
    ImageView mImageIsCollectionPosts;

    //用户评论
    @BindView(R.id.llyt_user_comment)
    LinearLayout mLlytUserComment;

    //评论数列表
    @BindView(R.id.rv_user_comment)
    RecyclerView mRvUserComment;

    //总共评论数查看
    @BindView(R.id.tv_total_comment_num)
    TextView mTvTotalCommentNum;

    //评论用户头像
    @BindView(R.id.image_comment_user_avatar)
    ImageView mImgCommentUserAvatar;

    //帖子发布时间
    @BindView(R.id.tv_post_time)
    TextView mTvPostTime;

    //可能感兴趣的用户
    @BindView(R.id.rlyt_interest_user)
    RelativeLayout mRlytInterestUser;

    //查看感兴趣的用户
    @BindView(R.id.tv_interest_user_look_more)
    TextView mTvInterestUserLookMore;

    //感兴趣的用户
    @BindView(R.id.rv_users_of_interest)
    RecyclerView mRvUserOfInterest;

    @BindView(R.id.img_post_video_gif)
    ImageView mImgPostVideGif;

    @BindView(R.id.rlyt_post_video)
    RelativeLayout mRlytPostVideo;

    @BindView(R.id.jzvd_post_video)
    MyJzvdStd mJzvdPostVideo;

    //帖子详情照片
    @BindView(R.id.rlyt_post_detail_picture)
    RelativeLayout mRlytPostDetailPicture;

    @BindView(R.id.layout_expanded_button)
    ExpandButtonLayout mBtnLayoutExpandSound;

    //是否点过赞 0:否 1:是
    @BindView(R.id.tv_post_praise)
    TextView mTvPostPraise;

    //帖子收藏数
    @BindView(R.id.tv_post_collection_num)
    TextView mTvPostCollectionNum;

    @BindView(R.id.edt_post_comment)
    EditText mEdtPostCommentContent;

    @BindView(R.id.llyt_follow_item)
    LinearLayout mLlytFollowItem;

    //(0:评论帖子 1:评论) 帖子的评论
    private int mPid = 0;

    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private String mLatitude = "";
    private String mLongitude = "";

    //postKey   near是附近,recomm是推荐
    private String mPostKey = "recomm";

    //帖子id
    private static int mPostId;

    Bitmap videoThumbnailBitmap;

    private MediaPlayer mMediaPlayer = null;

    //点赞用户
    private PraisePeopleAdapter praisePeopleAdapter;

    private HPFollowPostCommentAdapter hpFollowPostCommentAdapter;
    List<ZanAvatersBean> zanAvatersList;

    //帖子发布者
    private int authorId;
    private static IWXAPI api;

    static Bitmap bitmap = null;

    private int mPageNo = 1;
    private int mPageSize = 10;

    private boolean isOnLoadMore = false;
    //收藏弹框
    CollectEnterAlbumsDialog collectEnterAlbumsDialog;

    //专辑收藏
    List<FavoritesBean> favoritesAlbumList;

    private int REQUEST_CODE_NEW_ALBUM = 0;

    //总条数
    private int mTotalPageCount;

    //登录者头像
    private String userAvatarUrl;

    //帖子发布者头像
    String publisherAvatarUrl;

    ZanAvatersBean zanAvatersBean = new ZanAvatersBean();

    //昵称
    private String nickname;

    List<String> resourcesPictureList;

    private static String photoUrl;

    private OthersPostOprateDialog othersPostOprateDialog;

    //标题
    private String content;

    private int keyBoardState = 0;//0为键盘收缩，1为弹出

    private List<PostCommentBean> postsAllCommentsList;

    private Activity activity;

    private InputMethodManager imm;
    @Override
    public int getLayoutId() {

        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_post_recommend_detail;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setBar(PostRecommendDetailActivity.this, "", R.mipmap.icon_left_arrow_black);

        mPostId = getIntent().getIntExtra("postId", 0);

        api = WXAPIFactory.createWXAPI(this, PayConstants.APP_ID, false);

        nickname = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.USERNAME, "");
        userAvatarUrl = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.USER_AVATAR, "");
        userId = (int) SharedPreferencesUtils.getData(SharedPreferencesUtils.USER_ID, 0);
        if (zanAvatersList == null) {

            zanAvatersList = new ArrayList<>();

        } else {
            zanAvatersList.clear();
        }

        if (favoritesAlbumList == null) {

            favoritesAlbumList = new ArrayList<>();

        } else {

            favoritesAlbumList.clear();
        }

        if (resourcesPictureList == null) {

            resourcesPictureList = new ArrayList<>();
        } else {
            resourcesPictureList.clear();
        }

        if(postsAllCommentsList == null){

            postsAllCommentsList = new ArrayList<>();
        } else {
            postsAllCommentsList.clear();
        }

        //点赞用户列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(layoutManager.HORIZONTAL);
        mRvPraisePeopleNum.setLayoutManager(layoutManager);
        praisePeopleAdapter = new PraisePeopleAdapter(mContext);
        praisePeopleAdapter.setPraisePeopleList(zanAvatersList);
        mRvPraisePeopleNum.setAdapter(praisePeopleAdapter);

        mRlytPraisePeopleNum.getBackground().setAlpha(40);

        mBannerViewFriendDetailPicture.initUI(resourcesPictureList);
    }

    @Override
    public void initData() {
        initLocation();

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //  showProgressDialog(PostRecommendDetailActivity.this);
                       // isPostReFresh = true;
                        postsAllCommentsList.clear();
                        getPostInfo();

                    }
                }, 100);
            }
        });

       mEdtPostCommentContent.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    Log.i("---", "搜索操作执行");
                    postCommentRequest();
                }
                return false;
            }
        });
        activity = this;
        imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);

        mLlytFollowItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showToast(PostRecommendDetailActivity.this,"123");
            }
        });
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    public void getHomePageIndexResult(BackResult<HomePageResponse> res) {

    }

    @Override
    public void queryByTopicResult(BackResult<HomePageResponse> res) {

    }

    @Override
    public void getHomeAttentionResult(BackResult<HomePageResponse> res) {

    }

    @Override
    public void postOprateResult(BackResult<PraiseOrCollectResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    PraiseOrCollectResponse praiseOrCollectResponse = res.getData();
                    int zanStatus = praiseOrCollectResponse.getZanStatus();//是否点赞过 0:不赞 1:已点赞
                    int zanNum = praiseOrCollectResponse.getZanNum();  //点赞的数量

                    if (zanStatus == 0) {

                        mTvPostPraise.setCompoundDrawablesWithIntrinsicBounds(mContext.getResources().getDrawable(R.mipmap.icon_love_gray), null, null, null);
                        mTvPostPraise.setTextColor(mContext.getResources().getColor(R.color.text_grey));
                        mRlytPraise.setBackgroundResource(R.drawable.bg_rect_light_gray_shape_radius_five);

                        for (int i = 0; i < zanAvatersList.size(); i++) {

                            ZanAvatersBean zanAvatersBean = zanAvatersList.get(i);
                            String avatarUrl = zanAvatersBean.getAvater();
                            if (avatarUrl.equals(userAvatarUrl)) {

                                zanAvatersList.remove(zanAvatersBean);
                            }
                        }
                    } else {

                        zanAvatersBean.setNickname(nickname);
                        zanAvatersBean.setAvater(userAvatarUrl);
                        zanAvatersBean.setId(userId);
                        zanAvatersList.add(zanAvatersBean);

                        mTvPostPraise.setCompoundDrawablesWithIntrinsicBounds(mContext.getResources().getDrawable(R.mipmap.icon_note_heart_white), null, null, null);
                        mTvPostPraise.setTextColor(mContext.getResources().getColor(R.color.white));
                        mRlytPraise.setBackgroundResource(R.drawable.bg_praise_rect_dark_red_shape);
                    }

                    //点赞用户数量
                    mTvPraisePeopleNum.setText(String.valueOf(zanNum));

                    //用户点赞
                    if (zanAvatersList != null) {

                        praisePeopleAdapter.setPraisePeopleList(zanAvatersList);
                        praisePeopleAdapter.notifyDataSetChanged();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Constants.USER_NOT_LOGIN_CODE:
                onReLogin("");
                break;
            default:
                showToast(PostRecommendDetailActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getHomePageSearchAllResult(BackResult<HomePageAllSearchResponse> res) {

    }

    @Override
    public void getHomePageSearchByTypeResult(BackResult<HomePageTypeSearchResponse> res) {

    }

    @Override
    public void userFollowResult(BackResult<FollowUserStatusResponse> res) {

    }

    @Override
    public void getUnReadMessageListResult(BackResult<Integer> res) {

    }

    @Override
    public void getPostInfoResult(BackResult<PostInfoDetailResponse> res) {
        dismissProgressDialog();
        if (mSmartRefreshLayout != null) {
            mSmartRefreshLayout.finishRefresh();
        }
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    PostInfoDetailResponse postInfoDetailResponse = res.getData();
                    List<PostInfoDetailResponse.PostInfoEntity> postInfoList = postInfoDetailResponse.getResult();
                    if (postInfoList != null) {
                        PostInfoDetailResponse.PostInfoEntity postInfoEntity = postInfoList.get(0);
                        authorId = postInfoEntity.getUserId();
                        mPostId = postInfoEntity.getId();
                        String gifPhotoUrl = postInfoEntity.getPhoto();          //拍视频生成 gif
                        publisherAvatarUrl = postInfoEntity.getAvater();
                        resourcesPictureList = postInfoEntity.getResources(); //图片
                        String resourceUrl = postInfoEntity.getResourceUrl();  //mp4
                        content = postInfoEntity.getContent();
                        int photoHeight = postInfoEntity.getPhotoHeight();
                        int photoWidth = postInfoEntity.getPhotoWidth();
                        String postPublishTime = DateUtil.transferLongToDate(DateUtil.sDateYMDFormat, postInfoEntity.getCtime());
                        String userName = postInfoEntity.getNickname(); //用户名

                        int screenWidth = ScreenUtil.getScreenWidth(mContext);

                        int postsType = postInfoEntity.getPostsType();  //1图片，2语音，3视频

                        float ratio=(float) photoWidth/(float) photoHeight;
                        int height=(int)(screenWidth/ratio);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT, height);
                        mRlytPostDetailPicture.setLayoutParams(params);

                        //推荐用户
                        List<RecommendInterestUsersBean> recommendUsersList = postInfoEntity.getRecommendUsers();

                        //帖子发布者头像
                        if (publisherAvatarUrl != null) {
                            GlideUtil.loadImage(PostRecommendDetailActivity.this, publisherAvatarUrl, mImgUserAvatar);
                        }

                        if (userAvatarUrl != null) {
                            GlideUtil.loadImage(PostRecommendDetailActivity.this, userAvatarUrl, mImgCommentUserAvatar);
                        }

                        if (!TextUtils.isEmpty(userName)) {
                            mTvUserName.setText(userName);
                        }

                        if (!TextUtils.isEmpty(content)) {
                            mExpandPostDescription.setText(content);
                        }

                        int hitsNum = postInfoEntity.getHits(); //浏览数
                        mTvBrowseNum.setText("浏览" + String.valueOf(hitsNum));

                        //帖子时间
                        mTvPostTime.setText(postPublishTime);
                        mTvTime.setText(postPublishTime);

                        if (postsType == 1) { //图片

                            mRlytPostDetailPicture.setVisibility(View.VISIBLE);
                            mRlytPostVideo.setVisibility(View.GONE);
                            mBtnLayoutExpandSound.setVisibility(View.INVISIBLE);
                            mBannerViewFriendDetailPicture.setBannerList(resourcesPictureList);
                        } else if (postsType == 2) {  //音频+图片

                            mRlytPostDetailPicture.setVisibility(View.VISIBLE);
                            mRlytPostVideo.setVisibility(View.GONE);

                            if (!TextUtils.isEmpty(resourceUrl)) {
                                String audioValue = UploadFileTypeEnum.AUDIO.getValue();
                                if (resourceUrl.contains(audioValue))
                                {
                                    mBtnLayoutExpandSound.setVisibility(View.VISIBLE);
                                    mBtnLayoutExpandSound.setAudioFileUrl(resourceUrl);
                                }
                            }
                            mBannerViewFriendDetailPicture.setBannerList(resourcesPictureList);
                        } else if (postsType == 3) {  //视频

                            mRlytPostVideo.setVisibility(View.VISIBLE);
                            mRlytPostDetailPicture.setVisibility(View.GONE);

                            mJzvdPostVideo.setUp(resourceUrl, "");
                            mJzvdPostVideo.startButton.performClick();
                            mJzvdPostVideo.startVideo();
                            mBtnLayoutExpandSound.setVisibility(View.INVISIBLE);
                            Glide.with(this).load(gifPhotoUrl).into(mJzvdPostVideo.thumbImageView);

                            mJzvdPostVideo.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    System.out.print("111");
                                }
                            });
                        }

                        //用户点赞
                        zanAvatersList = postInfoEntity.getZanAvaters();
                        int zanCount = postInfoEntity.getZanCount();
                        if (zanAvatersList != null) {

                            praisePeopleAdapter.setPraisePeopleList(zanAvatersList);
                            praisePeopleAdapter.notifyDataSetChanged();
                        }

                        //点赞用户数量
                        mTvPraisePeopleNum.setText(String.valueOf(zanCount));

                        int zanStatus = postInfoEntity.getZanStatus(); //是否点赞过
                        if (zanStatus == 0) {

                            mTvPostPraise.setCompoundDrawablesWithIntrinsicBounds(mContext.getResources().getDrawable(R.mipmap.icon_love_gray), null, null, null);
                            mTvPostPraise.setTextColor(mContext.getResources().getColor(R.color.text_grey));
                            mRlytPraise.setBackgroundResource(R.drawable.bg_rect_light_gray_shape_radius_five);
                        } else {

                            mTvPostPraise.setCompoundDrawablesWithIntrinsicBounds(mContext.getResources().getDrawable(R.mipmap.icon_note_heart_white), null, null, null);
                            mTvPostPraise.setTextColor(mContext.getResources().getColor(R.color.white));
                            mRlytPraise.setBackgroundResource(R.drawable.bg_praise_rect_dark_red_shape);
                        }

                        int commentCount = postInfoEntity.getCommentCount();//评论数
                        int collectionCount = postInfoEntity.getCollectionCount();//收藏数
                        mTvPostCommentNum.setText(String.valueOf(commentCount));
                        mTvPostCollectionNum.setText(String.valueOf(collectionCount));

                        //主题标签
                        List<TopicsBean> topicsList = postInfoEntity.getTopics();
                        if (topicsList != null) {
                            mTagFlowLayout.setAdapter(new TagAdapter<TopicsBean>(topicsList) {

                                @Override
                                public View getView(FlowLayout parent, int position, TopicsBean topicsEntity) {
                                    LayoutInflater mInflater = LayoutInflater.from(mContext);
                                    TextView tagName = (TextView) mInflater.inflate(R.layout.layout_flowlayout_tv,
                                            mTagFlowLayout, false);
                                    tagName.getBackground().setAlpha(80);
                                    tagName.setText(topicsEntity.getTitle());
                                    return tagName;
                                }
                            });
                        }

                        //帖子评论
                        List<PostCommentBean> postsCommentsList = postInfoEntity.getPostsComments();

                        if (postsCommentsList != null) {
                            postsAllCommentsList.addAll(postsCommentsList);
                            mLlytUserComment.setVisibility(View.VISIBLE);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                            linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
                            mRvUserComment.setLayoutManager(linearLayoutManager);
                            hpFollowPostCommentAdapter = new HPFollowPostCommentAdapter(mContext);
                            hpFollowPostCommentAdapter.setLabelList(postsAllCommentsList);
                            mRvUserComment.setAdapter(hpFollowPostCommentAdapter);
                            mTvTotalCommentNum.setText("查看" + commentCount + "条评论");
                        } else {

                            mLlytUserComment.setVisibility(View.GONE);
                        }

                        if (recommendUsersList != null && recommendUsersList.size() > 0) {
                            mRlytInterestUser.setVisibility(View.VISIBLE);
                            //可能感兴趣列表
                            LinearLayoutManager userOfInterestLinearLayoutManager = new LinearLayoutManager(mContext);
                            userOfInterestLinearLayoutManager.setOrientation(userOfInterestLinearLayoutManager.HORIZONTAL);
                            mRvUserOfInterest.setLayoutManager(userOfInterestLinearLayoutManager);
                            FollowUsersOfInterestAdapter followUsersOfInterestAdapter = new FollowUsersOfInterestAdapter(mContext, new FollowUsersOfInterestAdapter.UsersOfInterestItemClickListener() {
                                @Override
                                public void setUsersOfInterestItemClickListener(RecommendInterestUsersBean userEntity) {


                                }
                            });
                            followUsersOfInterestAdapter.setFollowUsersOfInterestList(recommendUsersList);
                            mRvUserOfInterest.setAdapter(followUsersOfInterestAdapter);

                        } else {

                            mRlytInterestUser.setVisibility(View.GONE);
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Constants.USER_NOT_LOGIN_CODE:
                toActivity(PhoneQuickLoginActivity.class);
                break;
            default:
                showToast(PostRecommendDetailActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {
        if (mSmartRefreshLayout != null) {
            mSmartRefreshLayout.finishRefresh();
        }
        dismissProgressDialog();
        showToast(PostRecommendDetailActivity.this, Constants.getResultMsg(msg));
    }

    /**
     * 初始化定位
     */
    private void initLocation() {

        //初始化client
        locationClient = new AMapLocationClient(PostRecommendDetailActivity.this);
        locationOption = getDefaultOption();
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(locationListener);

        startLocation();
    }


    /**
     * 默认的定位参数
     *
     * @since 2.8.0
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(true);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        mOption.setGeoLanguage(AMapLocationClientOption.GeoLanguage.DEFAULT);//可选，设置逆地理信息的语言，默认值为默认语言（根据所在地区选择语言）
        return mOption;
    }

    /**
     * 开始定位
     */
    private void startLocation() {
        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }


    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            if (null != location) {
                mLongitude = String.valueOf(location.getLongitude());
                mLatitude = String.valueOf(location.getLatitude());

                showProgressDialog(PostRecommendDetailActivity.this);
                getPostInfo();
             /*   StringBuffer sb = new StringBuffer();
                //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
                if(location.getErrorCode() == 0){
                    sb.append("定位成功" + "\n");
                    sb.append("定位类型: " + location.getLocationType() + "\n");
                    sb.append("经    度    : " + location.getLongitude() + "\n");
                    sb.append("纬    度    : " + location.getLatitude() + "\n");
                    sb.append("精    度    : " + location.getAccuracy() + "米" + "\n");
                    sb.append("提供者    : " + location.getProvider() + "\n");

                    sb.append("速    度    : " + location.getSpeed() + "米/秒" + "\n");
                    sb.append("角    度    : " + location.getBearing() + "\n");
                    // 获取当前提供定位服务的卫星个数
                    sb.append("星    数    : " + location.getSatellites() + "\n");
                    sb.append("国    家    : " + location.getCountry() + "\n");
                    sb.append("省            : " + location.getProvince() + "\n");
                    sb.append("市            : " + location.getCity() + "\n");
                    sb.append("城市编码 : " + location.getCityCode() + "\n");
                    sb.append("区            : " + location.getDistrict() + "\n");
                    sb.append("区域 码   : " + location.getAdCode() + "\n");
                    sb.append("地    址    : " + location.getAddress() + "\n");
                    sb.append("兴趣点    : " + location.getPoiName() + "\n");
                    //定位完成的时间
                    sb.append("定位时间: " + MapUtils.formatUTC(location.getTime(), "yyyy-MM-dd HH:mm:ss") + "\n");
                } else {
                    //定位失败
                    sb.append("定位失败" + "\n");
                    sb.append("错误码:" + location.getErrorCode() + "\n");
                    sb.append("错误信息:" + location.getErrorInfo() + "\n");
                    sb.append("错误描述:" + location.getLocationDetail() + "\n");
                }
                sb.append("***定位质量报告***").append("\n");
                sb.append("* WIFI开关：").append(location.getLocationQualityReport().isWifiAble() ? "开启":"关闭").append("\n");
                sb.append("* GPS状态：").append(getGPSStatusString(location.getLocationQualityReport().getGPSStatus())).append("\n");
                sb.append("* GPS星数：").append(location.getLocationQualityReport().getGPSSatellites()).append("\n");
                sb.append("* 网络类型：" + location.getLocationQualityReport().getNetworkType()).append("\n");
                sb.append("* 网络耗时：" + location.getLocationQualityReport().getNetUseTime()).append("\n");
                sb.append("****************").append("\n");
                //定位之后的回调时间
                sb.append("回调时间: " + MapUtils.formatUTC(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss") + "\n");

                //解析定位结果，
                String result = sb.toString();*/
            } else {
            }
        }
    };

    public void getPostInfo() {

        if (validateInternet()) {
            mPresenter.getPostInfo(mPostId, mPostKey, mLatitude, mLongitude);
        }

    }

    @Override
    public void postsCommentResult(BackResult res) {

        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    hideSoftInputFromWindow();
                    mEdtPostCommentContent.setText("");
                    showToast(PostRecommendDetailActivity.this,"评论成功～");
                    String commentContent = mEdtPostCommentContent.getText().toString().trim();
                    PostCommentBean postCommentBean = new PostCommentBean();
                    postCommentBean.setContent(commentContent);
                    postsAllCommentsList.add(postCommentBean);
                    hpFollowPostCommentAdapter.setLabelList(postsAllCommentsList);
                    hpFollowPostCommentAdapter.notifyDataSetChanged();
                    if(postsAllCommentsList != null) {
                        mTvTotalCommentNum.setText("查看" + postsAllCommentsList.size() + "条评论");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Constants.USER_NOT_LOGIN_CODE:
                toActivity(PhoneQuickLoginActivity.class);
                break;
            default:
                showToast(PostRecommendDetailActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }


    @Override
    public void onBackPressed() {
        if (mJzvdPostVideo.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mJzvdPostVideo != null) {
            mJzvdPostVideo.releaseAllVideos();
        }

        if (mBtnLayoutExpandSound != null) {
            mBtnLayoutExpandSound.stopPlaying();
        }
        // mJzvdPostVideo.on
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoThumbnailBitmap != null) {

            videoThumbnailBitmap = null;
        }

        if (bitmap != null) {
            bitmap.recycle();
            bitmap = null;
        }
    }

    public void getMediaPlayer(String audioFileUrl) {
        mMediaPlayer = new MediaPlayer();

        try {
            mMediaPlayer.setDataSource(audioFileUrl);
            mMediaPlayer.prepare();

            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mMediaPlayer.start();
                }
            });

        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // stopPlaying();
            }
        });
    }

    @OnClick({R.id.img_post_share, R.id.tv_post_comment_num, R.id.rlyt_praise, R.id.tv_total_comment_num, R.id.llyt_post_collection, R.id.image_user_avatar,R.id.llyt_follow_item,R.id.refresh_layout})
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.img_post_share:

                if (othersPostOprateDialog == null) {
                    othersPostOprateDialog = new OthersPostOprateDialog(PostRecommendDetailActivity.this, new OthersPostOprateDialog.OnSharePlatformItemClickListener() {
                        @Override
                        public void onSharePlatformItemClick(SHARE_MEDIA sharePlatform) {
                            try {

                                String sharePlatformStr = sharePlatform.toString();
                                photoUrl = resourcesPictureList.get(0);
                                String wechatFriend = SharePlatformEnum.WECHAT_FRIEND.getValue();
                                if (sharePlatformStr.equals(wechatFriend)) {

                                    new Thread(saveFileRunnable).start();

                                } else {

                                    thirdShare(sharePlatform, photoUrl);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onPostReportItemClick() {

                            Intent intent = new Intent();
                            intent.setClass(PostRecommendDetailActivity.this, ReportActivity.class);
                            intent.putExtra("reportFlag", 0);   //举报帖子
                            intent.putExtra("postsId", mPostId);
                            startActivity(intent);
                        }

                        @Override
                        public void onGeneratePictureItemClick() {
                            photoUrl = resourcesPictureList.get(0);
                            BlurBehind.getInstance().execute(PostRecommendDetailActivity.this, new OnBlurCompleteListener() {
                                @Override
                                public void onBlurComplete() {

                                    Intent intent = new Intent(PostRecommendDetailActivity.this, GeneratePicturesActivity.class);
                                    intent.putExtra("imageUrl",photoUrl);
                                    intent.putExtra("postId",mPostId);
                                    intent.putExtra("content",content);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                                    startActivity(intent);
                                }
                            });
                        }
                    }).builder().setCancelable(true).setCanceledOnTouchOutside(true);
                    othersPostOprateDialog.show();
                } else {
                    othersPostOprateDialog.show();
                }

             /*   ShareOprateDialog shareOprateDialog = new ShareOprateDialog(PostRecommendDetailActivity.this, new ShareOprateDialog.OnSharePlatformItemClickListener() {
                    @Override
                    public void onSharePlatformItemClick(SHARE_MEDIA sharePlatform) {
                        try {

                            String sharePlatformStr = sharePlatform.toString();
                            photoUrl = resourcesPictureList.get(0);
                            String wechatFriend = SharePlatformEnum.WECHAT_FRIEND.getValue();
                            if (sharePlatformStr.equals(wechatFriend)) {

                                new Thread(saveFileRunnable).start();

                            } else {

                                thirdShare(sharePlatform, photoUrl);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).builder().setCancelable(true).setCanceledOnTouchOutside(true);
                shareOprateDialog.show();*/

                break;
            case R.id.tv_post_comment_num:

                intent.setClass(PostRecommendDetailActivity.this, CommentsListActivity.class);
                intent.putExtra("mPostId", mPostId);
                startActivity(intent);

                break;
           /* case R.id.edt_post_comment:
               *//* intent.setClass(PostRecommendDetailActivity.this, CommentsListActivity.class);
                intent.putExtra("mPostId", mPostId);
                startActivity(intent);*//*

                break;*/
            case R.id.rlyt_praise:
                postOprate();
                break;
            case R.id.tv_total_comment_num:
                intent.setClass(PostRecommendDetailActivity.this, CommentsListActivity.class);
                intent.putExtra("mPostId", mPostId);
                startActivity(intent);
                break;
            case R.id.llyt_post_collection:

                getFavoritesList();
                break;
            case R.id.image_user_avatar:
                intent.setClass(PostRecommendDetailActivity.this, UserPersonalHomePageActivity.class);
                intent.putExtra("publisherAvatarUrl", publisherAvatarUrl);
                intent.putExtra("authorId", authorId);
                startActivity(intent);
                break;
            case R.id.llyt_follow_item:

                hideSoftInputFromWindow();

                break;
            case R.id.refresh_layout:
                hideSoftInputFromWindow();
                break;
            default:
                break;
        }
    }

    public void hideSoftInputFromWindow(){

        keyBoardState = 0;
        imm.hideSoftInputFromWindow(mEdtPostCommentContent.getWindowToken(), 0);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               // main_emoji_layout.setVisibility(View.GONE);
            }
        }, 50);

        mPid = 0;
    }

    public void softInputFromWindowOprate(){

        if (keyBoardState == 0) {//弹出键盘
            showSoftInputFromWindow();

        } else {//关闭键盘
            hideSoftInputFromWindow();
        }
    }
    public void showSoftInputFromWindow(){

        keyBoardState = 1;
      /*  mEdtComment.setFocusable(true);
        mEdtComment.setFocusableInTouchMode(true);
        mEdtComment.requestFocus();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        //  showSoftInputFromWindow(CommentsListActivity.this, mEdtComment);
    *//*    new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imm.showSoftInput(mEdtComment, 0);
            }
        }, 50);
*//*

        imm.showSoftInput(mEdtComment, 0);*/
    }
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
                if (bmp != null) {
                    bmp.recycle();
                    bmp = null;
                }
                WXMiniProgramObject miniProgramObj = new WXMiniProgramObject();
                miniProgramObj.webpageUrl = "http:192.168.1.140:8083/"; // 兼容低版本的网页链接
                miniProgramObj.miniprogramType = WXMiniProgramObject.MINIPTOGRAM_TYPE_RELEASE;// 正式版:0，测试版:1，体验版:2
                miniProgramObj.userName = "gh_8f591c4ee659";     // 小程序原始id
                miniProgramObj.path = Net.POST_TRAVEL_MINIPTOGRAM_URL + mPostId; //小程序页面路径；对于小游戏，可以只传入 query 部分，来实现传参效果，如：传入 "?foo=bar"
                WXMediaMessage msg = new WXMediaMessage(miniProgramObj);
                msg.title = "鱼人自游";                    // 小程序消息title
                msg.description = "帖子分享";               // 小程序消息desc
                msg.thumbData = compressImage(thumbBmp);                      // 小程序消息封面图片，小于128k

                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = buildTransaction("miniProgram");
                req.message = msg;
                req.scene = SendMessageToWX.Req.WXSceneSession;  // 目前只支持会话
                api.sendReq(req);


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

    private void thirdShare(SHARE_MEDIA platform, String photoUrl) {
        String webUrl = Net.H5_YURENZIYOU_DOWNLOAD_GUIDE_PAGE_URL;
        UMImage image = new UMImage(PostRecommendDetailActivity.this, photoUrl);                    //资源文件
        UMWeb umWeb = new UMWeb(webUrl, PostRecommendDetailActivity.this.getResources().getString(R.string.app_name), "鱼人自游是宁波海洋世界旗下一站式旅游服务平台,产品及服务覆盖门票预订,景点评价及景点打折门票查询,酒店预订,美食推荐、还有更详细的旅游攻略.", image); //URL 标题 描述 封面图
        new ShareAction(PostRecommendDetailActivity.this)
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
            Toast.makeText(PostRecommendDetailActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(PostRecommendDetailActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };


    @Override
    public void postCollectionResult(BackResult<FavoritesCollectionResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    FavoritesCollectionResponse favoritesCollectionResponse = res.getData();
                    int collectionStatus = favoritesCollectionResponse.getCollectionStatus();
                    int collectionCount = favoritesCollectionResponse.getCollectionCount();

                    if (collectionStatus == 0) {

                        mImageIsCollectionPosts.setImageResource(R.mipmap.icon_gray_collection_posts);
                    } else {

                        mImageIsCollectionPosts.setImageResource(R.mipmap.icon_yellow_collection_posts);
                        showToast(PostRecommendDetailActivity.this, "收藏成功");
                    }

                    mTvPostCollectionNum.setText(String.valueOf(collectionCount));

                    if (collectEnterAlbumsDialog != null) {
                        collectEnterAlbumsDialog.dismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Constants.USER_NOT_LOGIN_CODE:
                onReLogin("");
                break;
            default:
                showToast(PostRecommendDetailActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getFavoritesListResult(BackResult<FavoritesListResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    favoritesAlbumList.clear();
                    if (collectEnterAlbumsDialog != null) {
                        if (isOnLoadMore) {
                            collectEnterAlbumsDialog.setSmartRefreshLayoutLoadMoreFinish();

                        } else {

                            collectEnterAlbumsDialog.setSmartRefreshLayoutRefreshFinish();
                        }
                    }
                    FavoritesListResponse favoritesListResponse = res.getData();

                    List<FavoritesBean> favoritesList = favoritesListResponse.getResult();

                    BasePaginationResult paginationResult = res.getData().getPage();
                    mTotalPageCount = paginationResult.getPageCount();
                    if (favoritesList == null) {
                        favoritesList = new ArrayList<>();
                    }


                    favoritesAlbumList.addAll(favoritesList);
                    if (collectEnterAlbumsDialog == null) {
                        collectEnterAlbumsDialog = new CollectEnterAlbumsDialog(favoritesAlbumList, new CollectEnterAlbumsDialog.ChooseAlbumsCollectionListener() {
                            @Override
                            public void setChooseAlbumsCollectionListener(FavoritesBean favoritesBean) {

                                int favoritesId = favoritesBean.getId();
                                postCollection(favoritesId);
                            }

                            @Override
                            public void setNewAlbumCollectionListener() {

                                Intent intent = new Intent();
                                intent.setClass(PostRecommendDetailActivity.this, NewAlbumActivity.class);
                                startActivityForResult(intent, REQUEST_CODE_NEW_ALBUM);

                            }

                            @Override
                            public void setNewAlbumOnRefreshListener() {

                                isOnLoadMore = false;
                                mPageNo = 1;
                                favoritesAlbumList.clear();
                                collectEnterAlbumsDialog.setAlbumCollectList(favoritesAlbumList);
                                showProgressDialog(PostRecommendDetailActivity.this);
                                getFavoritesList();
                            }

                            @Override
                            public void setNewAlbumOnLoadMoreListener(RefreshLayout refreshLayout) {

                                isOnLoadMore = true;
                                refreshLayout.getLayout().postDelayed(new Runnable() {

                                    @Override
                                    public void run() {
                                        isOnLoadMore = true;
                                        try {
                                            if (mTotalPageCount == favoritesAlbumList.size()) {
                                                refreshLayout.finishLoadMoreWithNoMoreData();
                                            } else {
                                                mPageNo++;
                                                getFavoritesList();
                                            }

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, 200);

                            }
                        });

                        collectEnterAlbumsDialog.show(getFragmentManager(), "收藏专辑");

                    } else {
                        collectEnterAlbumsDialog.setAlbumCollectList(favoritesAlbumList);
                        collectEnterAlbumsDialog.show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Constants.USER_NOT_LOGIN_CODE:
                onReLogin("");
                break;
            default:
                showToast(PostRecommendDetailActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    private static String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    //帖子点赞操作
    public void postOprate() {

        if (validateInternet()) {

            showProgressDialog(PostRecommendDetailActivity.this);
            PostOprateRequest postOprateRequest = new PostOprateRequest();
            int postsType = PostsTypeEnum.POST_PRAISE.getKey();
            postOprateRequest.setPostsType(postsType);
            postOprateRequest.setPostsId(mPostId);
            mPresenter.postOprate(postOprateRequest);
        }
    }

    private UMShareListener umShareListener = new UMShareListener() {
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
            Toast.makeText(PostRecommendDetailActivity.this, "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(PostRecommendDetailActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(PostRecommendDetailActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };


    public byte[] getImage(String srcPath) {

        try {
            BitmapFactory.Options newOpts = new BitmapFactory.Options();
            //开始读入图片，此时把options.inJustDecodeBounds 设回true了
            newOpts.inJustDecodeBounds = true;
            bitmap = BitmapFactory.decodeFile(srcPath, newOpts);//此时返回bm为空

            newOpts.inJustDecodeBounds = false;
            int w = newOpts.outWidth;
            int h = newOpts.outHeight;
            // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
            float hh = 800f;// 这里设置高度为800f
            float ww = 480f;// 这里设置宽度为480f
            // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
            int be = 1;// be=1表示不缩放
            if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
                be = (int) (newOpts.outWidth / ww);
            } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
                be = (int) (newOpts.outHeight / hh);
            }
            if (be <= 0)
                be = 1;
            newOpts.inSampleSize = be;// 设置缩放比例
            //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
            bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
            // String type = newOpts.outMimeType;
         /*   if (TextUtils.isEmpty(type)) {
                type = "未能识别的图片";
            } else {
                type = type.substring(6, type.length());
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }

        return compressImage(bitmap);//压缩好比例大小后再进行质量压缩
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
        // imageWidth = bitmapImage.getWidth();
        //imageHight = bitmapImage.getHeight();
        return baos.toByteArray();
    }

    //获取专辑列表
    public void getFavoritesList() {

        showProgressDialog(PostRecommendDetailActivity.this);
        mPresenter.getFavoritesList(mPageNo, mPageSize);
    }

    //帖子收藏
    public void postCollection(int favoritesId) {

        showProgressDialog(PostRecommendDetailActivity.this);
        PostsCollectionRequest postsCollectionRequest = new PostsCollectionRequest();
        postsCollectionRequest.setDataId(mPostId);
        postsCollectionRequest.setFavoritesId(favoritesId);
        int userId = getSharedPreferencesUserId();
        postsCollectionRequest.setUserId(userId);
        mPresenter.postCollection(postsCollectionRequest);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_NEW_ALBUM && resultCode == RESULT_OK) {
            getFavoritesList();
        }
    }

    //帖子评论
    public void postCommentRequest() {

        if (validateInternet()) {

            String postCommentContent = mEdtPostCommentContent.getText().toString().trim();

            if (TextUtils.isEmpty(postCommentContent)) {
                dismissProgressDialog();
                showToast(PostRecommendDetailActivity.this, "请填写评论");
                return;
            }
            PostsCommentRequest postsCommentRequest = new PostsCommentRequest();
            postsCommentRequest.setArticleId(mPostId);
            postsCommentRequest.setContent(postCommentContent);
            postsCommentRequest.setPid(mPid);
            mPresenter.postsCommentRequest(postsCommentRequest);

        }
    }
}
