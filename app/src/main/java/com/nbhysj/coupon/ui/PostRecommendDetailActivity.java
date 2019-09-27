package com.nbhysj.coupon.ui;


import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.FollowUsersOfInterestAdapter;
import com.nbhysj.coupon.adapter.HPFollowPostCommentAdapter;
import com.nbhysj.coupon.adapter.PraisePeopleAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.HomePageContract;
import com.nbhysj.coupon.model.HomePageModel;
import com.nbhysj.coupon.model.request.PostsCommentRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.HomePageResponse;
import com.nbhysj.coupon.model.response.HomePageSubTopicTagBean;
import com.nbhysj.coupon.model.response.PostCommentBean;
import com.nbhysj.coupon.model.response.PostInfoDetailResponse;
import com.nbhysj.coupon.model.response.RecommendInterestUsersBean;
import com.nbhysj.coupon.model.response.TopicsBean;
import com.nbhysj.coupon.model.response.ZanAvatersBean;
import com.nbhysj.coupon.presenter.HomePagePresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.BitmapUtils;
import com.nbhysj.coupon.util.DateUtil;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.nbhysj.coupon.view.BannerSlideShowView;
import com.nbhysj.coupon.view.ExpandableTextView;
import com.nbhysj.coupon.widget.customjzvd.MyJzvdStd;
import com.nbhysj.coupon.widget.wavelineview.ExpandButtonLayout;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.umeng.commonsdk.statistics.AnalyticsConstants.LOG_TAG;

/**
 * @auther：hysj created on 2019/03/02
 * description：帖子详情
 */
public class PostRecommendDetailActivity extends BaseActivity<HomePagePresenter, HomePageModel> implements HomePageContract.View {

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

    //添加评论
    @BindView(R.id.edt_post_comment)
    EditText mEdtPostCommentContent;

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

    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private String mLatitude = "";
    private String mLongitude = "";

    //postKey   near是附近,recomm是推荐
    private String mPostKey = "recomm";

    //帖子id
    private int mPostId;

    Bitmap videoThumbnailBitmap;

    private MediaPlayer mMediaPlayer = null;

    //点赞用户
    private PraisePeopleAdapter praisePeopleAdapter;

    private HPFollowPostCommentAdapter hpFollowPostCommentAdapter;
    List<ZanAvatersBean> zanAvatersList;

    @Override
    public int getLayoutId() {

        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_post_recommend_detail;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setBar(PostRecommendDetailActivity.this, getResources().getString(R.string.str_recommendation_detail), R.mipmap.icon_left_arrow_black);

        mPostId = getIntent().getIntExtra("postId", 0);

        if (zanAvatersList == null) {

            zanAvatersList = new ArrayList<>();

        } else {
            zanAvatersList.clear();
        }
        //点赞用户列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(layoutManager.HORIZONTAL);
        mRvPraisePeopleNum.setLayoutManager(layoutManager);
        praisePeopleAdapter = new PraisePeopleAdapter(mContext);
        praisePeopleAdapter.setPraisePeopleList(zanAvatersList);
        mRvPraisePeopleNum.setAdapter(praisePeopleAdapter);

        mRlytPraisePeopleNum.getBackground().setAlpha(40);
    }

    @Override
    public void initData() {
        initLocation();

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
    public void postOprateResult(BackResult res) {

    }

    @Override
    public void getPostInfoResult(BackResult<PostInfoDetailResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    PostInfoDetailResponse postInfoDetailResponse = res.getData();
                    List<PostInfoDetailResponse.PostInfoEntity> postInfoList = postInfoDetailResponse.getResult();
                    if (postInfoList != null) {
                        PostInfoDetailResponse.PostInfoEntity postInfoEntity = postInfoList.get(0);
                        mPostId = postInfoEntity.getId();
                        String photoUrl = postInfoEntity.getPhoto();          //拍视频生成 gif
                        String publisherAvatarUrl = postInfoEntity.getAvater();
                        List<String> resources = postInfoEntity.getResources(); //图片
                        String resourceUrl = postInfoEntity.getResourceUrl();  //mp4
                        String content = postInfoEntity.getContent();
                        String postPublishTime = DateUtil.transferLongToDate(DateUtil.sDateYMDFormat, postInfoEntity.getCtime());
                        String userName = postInfoEntity.getNickname(); //用户名
                        int postsType = postInfoEntity.getPostsType();  //1图片，2语音，3视频
                        //推荐用户
                        List<RecommendInterestUsersBean> recommendUsersList = postInfoEntity.getRecommendUsers();

                        //头像
                        if (publisherAvatarUrl != null) {
                            GlideUtil.loadImage(PostRecommendDetailActivity.this, publisherAvatarUrl, mImgUserAvatar);
                        }

                        String userAvatarUrl = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.USER_AVATAR, "");

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
                            mJzvdPostVideo.setVisibility(View.GONE);
                            mBtnLayoutExpandSound.setVisibility(View.GONE);
                            mBannerViewFriendDetailPicture.initUI(resources);
                        } else if (postsType == 2) {  //音频+图片

                            mRlytPostDetailPicture.setVisibility(View.VISIBLE);
                            mJzvdPostVideo.setVisibility(View.GONE);
                            mBtnLayoutExpandSound.setVisibility(View.VISIBLE);
                            mBannerViewFriendDetailPicture.initUI(resources);

                        } else if (postsType == 3) {  //视频

                            mJzvdPostVideo.setVisibility(View.VISIBLE);
                            mRlytPostDetailPicture.setVisibility(View.GONE);
                            mJzvdPostVideo.setUp(resourceUrl, "");
                            Glide.with(this).load(photoUrl).into(mJzvdPostVideo.thumbImageView);
                        }

                        //用户点赞
                        List<ZanAvatersBean> zanAvatersList = postInfoEntity.getZanAvaters();
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
                            mLlytUserComment.setVisibility(View.VISIBLE);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                            linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
                            mRvUserComment.setLayoutManager(linearLayoutManager);
                            hpFollowPostCommentAdapter = new HPFollowPostCommentAdapter(mContext);
                            hpFollowPostCommentAdapter.setLabelList(postsCommentsList);
                            mRvUserComment.setAdapter(hpFollowPostCommentAdapter);
                            mTvTotalCommentNum.setText("查看" + postsCommentsList.size() + "条评论");
                        } else {

                            mLlytUserComment.setVisibility(View.GONE);
                        }

                        if (recommendUsersList != null) {

                            mRlytInterestUser.setVisibility(View.VISIBLE);
                        }

                        if (recommendUsersList != null) {
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
            default:
                showToast(PostRecommendDetailActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {

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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoThumbnailBitmap != null) {

            videoThumbnailBitmap = null;
        }

        stopPlaying();
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
                stopPlaying();
            }
        });
    }


    private void stopPlaying() {

        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.reset();
            mMediaPlayer.release();
            mMediaPlayer = null;

            //allow the screen to turn off again once audio is finished playing
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }

    //帖子评论
    public void postCommentRequest() {

        if (validateInternet()) {

            String postCommentContent = mEdtPostCommentContent.getText().toString().trim();

            if (!TextUtils.isEmpty(postCommentContent)) {
                dismissProgressDialog();
                showToast(PostRecommendDetailActivity.this, "请填写帖子评论");
                return;
            }
            PostsCommentRequest postsCommentRequest = new PostsCommentRequest();
            postsCommentRequest.setArticleId(mPostId);
            postsCommentRequest.setContent(postCommentContent);
            postsCommentRequest.setPid(0);
            mPresenter.postsCommentRequest(postsCommentRequest);

        }
    }

}
