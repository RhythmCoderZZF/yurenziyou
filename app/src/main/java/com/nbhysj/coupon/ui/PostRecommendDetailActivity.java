package com.nbhysj.coupon.ui;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.HomePageContract;
import com.nbhysj.coupon.model.HomePageModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.HomePageResponse;
import com.nbhysj.coupon.model.response.PostInfoDetailResponse;
import com.nbhysj.coupon.presenter.HomePagePresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.BitmapUtils;
import com.nbhysj.coupon.util.DateUtil;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.nbhysj.coupon.view.BannerSlideShowView;
import com.nbhysj.coupon.view.ExpandableTextView;
import com.nbhysj.coupon.widget.customjzvd.MyJzvdStd;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

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
    @BindView(R.id.edt_add_comment)
    EditText mEdtAddComment;

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

    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private String mLatitude = "";
    private String mLongitude = "";

    //postKey   near是附近,recomm是推荐
    private String mPostKey = "recomm";

    //帖子id
    private int mPostId;

    Bitmap videoThumbnailBitmap;
    @Override
    public int getLayoutId() {

        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_post_recommend_detail;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setBar(PostRecommendDetailActivity.this,getResources().getString(R.string.str_recommendation_detail),R.mipmap.icon_left_arrow_black);

        mPostId = getIntent().getIntExtra("postId",0);
    }

    @Override
    public void initData() {
        initLocation();

    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this,mModel);
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
                    if(postInfoList != null)
                    {
                        PostInfoDetailResponse.PostInfoEntity postInfoEntity = postInfoList.get(0);
                        String photoUrl = postInfoEntity.getPhoto();          //拍视频生成 gif
                        String avatarUrl = postInfoEntity.getAvater();
                        List<String> resources = postInfoEntity.getResources(); //图片
                        String resourceUrl = postInfoEntity.getResourceUrl();  //mp4
                        String postPublishTime = DateUtil.transferLongToDateStr(DateUtil.sDateYMDFormat, postInfoEntity.getCtime());

                        //头像
                        if(avatarUrl != null)
                        {
                            GlideUtil.loadImage(PostRecommendDetailActivity.this,avatarUrl,mImgUserAvatar);
                        }

                        //帖子时间
                        mTvPostTime.setText(postPublishTime);

                        if(resources != null)
                        {
                            mBannerViewFriendDetailPicture.setVisibility(View.VISIBLE);
                            mJzvdPostVideo.setVisibility(View.GONE);
                            mBannerViewFriendDetailPicture.initUI(resources);
                        } else {

                           //videoThumbnailBitmap = BitmapUtils.getNetVideoBitmap(resourceUrl);
                            //mImgPostVideGif.setImageBitmap(videoThumbnailBitmap);
                            mJzvdPostVideo.setVisibility(View.VISIBLE);
                            mBannerViewFriendDetailPicture.setVisibility(View.GONE);
                            mJzvdPostVideo.setUp(resourceUrl
                                    , "");
                            Glide.with(this).load(photoUrl).into(mJzvdPostVideo.thumbImageView);

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

    public void getPostInfo(){

        mPresenter.getPostInfo(mPostId,mPostKey,mLatitude,mLongitude);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(videoThumbnailBitmap != null){

            videoThumbnailBitmap = null;
        }
    }
}
