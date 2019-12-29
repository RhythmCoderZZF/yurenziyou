package com.nbhysj.coupon.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.FollowListAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.common.Enum.PostsTypeEnum;
import com.nbhysj.coupon.contract.HomePageContract;
import com.nbhysj.coupon.dialog.CollectEnterAlbumsDialog;
import com.nbhysj.coupon.model.HomePageModel;
import com.nbhysj.coupon.model.request.PostOprateRequest;
import com.nbhysj.coupon.model.request.PostsCollectionRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BannerUrlBO;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.FavoritesBean;
import com.nbhysj.coupon.model.response.FavoritesCollectionResponse;
import com.nbhysj.coupon.model.response.FavoritesListResponse;
import com.nbhysj.coupon.model.response.FollowUserStatusResponse;
import com.nbhysj.coupon.model.response.HomePageAllSearchResponse;
import com.nbhysj.coupon.model.response.HomePageResponse;
import com.nbhysj.coupon.model.response.HomePageSubTopicTagBean;
import com.nbhysj.coupon.model.response.HomePageTypeSearchResponse;
import com.nbhysj.coupon.model.response.PostInfoDetailResponse;
import com.nbhysj.coupon.model.response.PraiseOrCollectResponse;
import com.nbhysj.coupon.model.response.RecommendInterestUsersBean;
import com.nbhysj.coupon.pay.wechat.PayConstants;
import com.nbhysj.coupon.presenter.HomePagePresenter;
import com.nbhysj.coupon.ui.CommentsListActivity;
import com.nbhysj.coupon.ui.FindFriendsActivity;
import com.nbhysj.coupon.ui.NewAlbumActivity;
import com.nbhysj.coupon.ui.PhoneQuickLoginActivity;
import com.nbhysj.coupon.ui.PostRecommendDetailActivity;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.nbhysj.coupon.view.JudgeNestedScrollView;
import com.nbhysj.coupon.view.RecyclerScrollView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FollowFragment extends BaseFragment<HomePagePresenter, HomePageModel> implements HomePageContract.View {

    //暂无数据
    @BindView(R.id.rlyt_no_data)
    RelativeLayout mRlytNoData;

    @BindView(R.id.rv_follow)
    RecyclerView mRvFollow;
    @BindView(R.id.scroll_view)
    JudgeNestedScrollView setNeedScroll;
    //加载
    @BindView(R.id.llyt_progress_bar_loading)
    LinearLayout mLlytProgressBarLoading;
    @BindView(R.id.progressbar_load_more)
    ProgressBar mProgressBarLoadMore;
    @BindView(R.id.tv_load_more)
    TextView mTvLoadMore;
    private List<HomePageSubTopicTagBean> followDetailList;
    private List<BannerUrlBO> bannerUrlList;
    private FollowListAdapter followListAdapter;
    private int mPageNo = 1;
    private int mPageSize = 10;

    private int REQUEST_CODE_NEW_ALBUM = 0;

    //专辑收藏
    List<FavoritesBean> favoritesAlbumList;

    private boolean visibleToUser;

    private int hasNext;

    private boolean isInitView = false;
    private boolean isVisible = false;

    private int mPosition;

    private int imageWidth, imageHight;

    Bitmap bitmap = null;

    private IWXAPI api;

    //收藏弹框
    CollectEnterAlbumsDialog collectEnterAlbumsDialog;

    //总条数
    private int mTotalPageCount;

    private boolean isOnLoadMore = false;

    private int mPostId;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_follow;
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView(View v) {
        EventBus.getDefault().register(this);
        api = WXAPIFactory.createWXAPI(getActivity(), PayConstants.APP_ID, false);

        isInitView = true;
        isCanLoadData();
        if (followDetailList == null) {

            followDetailList = new ArrayList<>();
        } else {
            followDetailList.clear();
        }

        if (bannerUrlList == null) {

            bannerUrlList = new ArrayList<>();
        } else {
            bannerUrlList.clear();
        }

        if (favoritesAlbumList == null) {

            favoritesAlbumList = new ArrayList<>();

        } else {

            favoritesAlbumList.clear();
        }


      /*  HomePageSubTopicTagBean homePageSubTopicTagBean = recommendFriendsList.get(mPosition);
        int postId = homePageSubTopicTagBean.getId();
        Intent intent = new Intent();
        intent.putExtra("postId", postId);
        intent.setClass(getActivity(), PostRecommendDetailActivity.class);
        startActivity(intent);*/

        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        // 设置布局管理器
        mRvFollow.setLayoutManager(layoutManager);

        followListAdapter = new FollowListAdapter(getActivity(), new FollowListAdapter.FollowListener() {

            @Override
            public void setPostPraiseListener(int position, int postId) {

                mPosition = position;
                postOprate(postId);
            }

            @Override
            public void setUserOfInterestListener(RecommendInterestUsersBean userEntity) {
                int userId = userEntity.getId();
                userFollow(userId);
            }

            @Override
            public void setUserOfInterestLookMoreListener() {

                toActivity(FindFriendsActivity.class);
            }

            @Override
            public void setLookCommentListener(int mPostId) {

                Intent intent = new Intent();
                intent.setClass(getActivity(), CommentsListActivity.class);
                intent.putExtra("mPostId", mPostId);
                startActivity(intent);

            }

            @Override
            public void setCollectionPostToAlbumsListener(HomePageSubTopicTagBean followDetailBean) {
                int postId = followDetailBean.getId();
                // Toast.makeText(mContext, "关注" + userEntity.getNickname(), Toast.LENGTH_SHORT).show();
                mPostId = postId;
                getFavoritesList();
            }

            @Override
            public void setFollowItemOnClickListener(int mPostId) {
                Intent intent = new Intent();
                intent.putExtra("postId", mPostId);
                intent.setClass(getActivity(), PostRecommendDetailActivity.class);
                startActivity(intent);
            //    hiddenIME();
            }

            @Override
            public void setFollowShareListener(int mPostId) {

              /*  ShareDialog shareDialog = new ShareDialog();
                shareDialog.show(getActivity().getFragmentManager(),"");*/

               /* ShareOprateDialog shareOprateDialog = new ShareOprateDialog(getActivity(), new ShareOprateDialog.OnSharePlatformItemClickListener() {
                    @Override
                    public void onSharePlatformItemClick(String sharePlatform) {

                        //   showToast(getActivity(),sharePlatform);

                        WXMiniProgramObject miniProgramObj = new WXMiniProgramObject();
                        miniProgramObj.webpageUrl = "http:192.168.1.140:8083/"; // 兼容低版本的网页链接
                        miniProgramObj.miniprogramType = WXMiniProgramObject.MINIPROGRAM_TYPE_PREVIEW;// 正式版:0，测试版:1，体验版:2
                        miniProgramObj.userName = "gh_8f591c4ee659";     // 小程序原始id
                        miniProgramObj.path = "pages/travel/travel?id=" + mPostId; //小程序页面路径；对于小游戏，可以只传入 query 部分，来实现传参效果，如：传入 "?foo=bar"
                        WXMediaMessage msg = new WXMediaMessage(miniProgramObj);
                        msg.title = getResources().getString(R.string.app_name);                    // 小程序消息title
                        msg.description = "帖子分享";               // 小程序消息desc
                        msg.thumbData = getImage("/storage/emulated/0/UCDownloads/pictures/4a90f603738da977d581be2cbf51f8198618e30f.jpg");                      // 小程序消息封面图片，小于128k

                        SendMessageToWX.Req req = new SendMessageToWX.Req();
                        req.transaction = buildTransaction("miniProgram");
                        req.message = msg;
                        req.scene = SendMessageToWX.Req.WXSceneSession;  // 目前只支持会话
                        api.sendReq(req);

                        if (bitmap != null) {
                            bitmap.recycle();
                            bitmap = null;
                        }

                    }
                }).builder().setCancelable(true).setCanceledOnTouchOutside(true);
                shareOprateDialog.show();*/
            }
        }, true);
        followListAdapter.setFollowDetailList(followDetailList);
        mRvFollow.setAdapter(followListAdapter);
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

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

    private byte[] compressImage(Bitmap bitmapImage) {
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
        imageWidth = bitmapImage.getWidth();
        imageHight = bitmapImage.getHeight();
        return baos.toByteArray();
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
            Toast.makeText(getActivity(), "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(getActivity(), "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(getActivity(), "取消了", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public void initData() {

        setNeedScroll.setOnScrollChangeListener(new JudgeNestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > oldScrollY) {
                    Log.i("TAG", "Scroll DOWN");
                }
                if (scrollY < oldScrollY) {
                    Log.i("TAG", "Scroll UP");
                }

                if (scrollY == 0) {
                    Log.i("TAG", "TOP SCROLL");
                }

                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    Log.i("TAG", "BOTTOM SCROLL");

                    loadData();

                }
            }

        });
    }

    public void loadData() {
        mLlytProgressBarLoading.setVisibility(View.VISIBLE);
        if (hasNext == 1) {
            mProgressBarLoadMore.setVisibility(View.VISIBLE);
            mTvLoadMore.setText(getResources().getString(R.string.loading));
            showProgressDialog(getActivity());
            mPageNo++;
            getHomeAttention();
        } else {
            mProgressBarLoadMore.setVisibility(View.GONE);
            mTvLoadMore.setText(getResources().getString(R.string.str_loading_no_more));
        }
    }

    //帖子点赞操作
    public void postOprate(int mPostId) {

        if (validateInternet()) {

            showProgressDialog(getActivity());
            PostOprateRequest postOprateRequest = new PostOprateRequest();
            int postsType = PostsTypeEnum.POST_PRAISE.getKey();
            postOprateRequest.setPostsType(postsType);
            postOprateRequest.setPostsId(mPostId);
            mPresenter.postOprate(postOprateRequest);
        }
    }

    //关注
    public void userFollow(int userId) {
        showProgressDialog(getActivity());
        mPresenter.userFollow(userId);
    }

    @Override
    public void getHomePageIndexResult(BackResult<HomePageResponse> res) {

    }

    @Override
    public void queryByTopicResult(BackResult<HomePageResponse> res) {

    }

    @Override
    public void getPostInfoResult(BackResult<PostInfoDetailResponse> res) {

    }

    @Override
    public void userFollowResult(BackResult<FollowUserStatusResponse> res) {
        dismissProgressDialog();

        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    FollowUserStatusResponse followUserStatusResponse = res.getData();
                    int attentionStatus = followUserStatusResponse.getFollowStatus();
                    List<RecommendInterestUsersBean> recommendInterestUsersList = followDetailList.get(0).getRecommendUsers();
                    recommendInterestUsersList.get(0).setAttentionStatus(attentionStatus);

                    followListAdapter.setFollowDetailList(followDetailList);
                    followListAdapter.notifyDataSetChanged();
                    /*if(attentionStatus == 0){

                        mNewFansBean.setAttentionStatus(1);
                    } else if(attentionStatus == 1){
                        mNewFansBean.setAttentionStatus(0);
                    }

                    newFansListAdapter.setNewFansList(userFansFollowList);
                    newFansListAdapter.notifyDataSetChanged();*/

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(getActivity(), Constants.getResultMsg(res.getMsg()));
                break;
        }
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

                    HomePageSubTopicTagBean homePageSubTopicTagBean = followDetailList.get(mPosition);

                    homePageSubTopicTagBean.setZanStatus(zanStatus);
                    homePageSubTopicTagBean.setZanCount(zanNum);

                    followListAdapter.setFollowDetailList(followDetailList);
                    followListAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Constants.USER_NOT_LOGIN_CODE:
                onReLogin("");
                break;
            default:
                showToast(getActivity(), Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void postsCommentResult(BackResult res) {

    }

    @Override
    public void getHomeAttentionResult(BackResult<HomePageResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    HomePageResponse attentionResponse = res.getData();
                    HomePageResponse.ResultBean resultBean = attentionResponse.getResult();
                    List<HomePageSubTopicTagBean> followList = resultBean.getList();
                    HomePageResponse.PageBean pageBean = attentionResponse.getPage();
                    hasNext = pageBean.getHasNext();
                    if (followList.size() == 0) {

                        mRlytNoData.setVisibility(View.VISIBLE);

                    } else {
                        mRlytNoData.setVisibility(View.GONE);
                        followDetailList.addAll(followList);
                        followListAdapter.setFollowDetailList(followDetailList);
                        followListAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Constants.USER_NOT_LOGIN_CODE:
                break;
            default:
                showToast(getActivity(), Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(getActivity(), Constants.getResultMsg(msg));
    }

    public void getHomeAttention() {

        if (validateInternet()) {

            mPresenter.getHomeAttention(mPageNo, mPageSize);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {

            String token = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.TOKEN, "");
            if (TextUtils.isEmpty(token)) {
                toActivity(PhoneQuickLoginActivity.class);
            }

        }
    }

    private void isCanLoadData() {
        //所以条件是view初始化完成并且对用户可见
        if (isInitView && isVisible) {
            getHomeAttention();

            //防止重复加载数据
            isInitView = false;
            isVisible = false;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        visibleToUser = isVisibleToUser;
        if (isVisibleToUser) {
            isVisible = true;
            isCanLoadData();
        } else {
            isVisible = false;
        }


    }

    @Override
    public void getHomePageSearchAllResult(BackResult<HomePageAllSearchResponse> res) {

    }

    @Override
    public void getHomePageSearchByTypeResult(BackResult<HomePageTypeSearchResponse> res) {

    }

    @Override
    public void postCollectionResult(BackResult<FavoritesCollectionResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    FavoritesCollectionResponse favoritesCollectionResponse = res.getData();
                    int collectionStatus = favoritesCollectionResponse.getCollectionStatus();
                    // HomePageSubTopicTagBean homePageSubTopicTagBean = followDetailList.get(0);
                   /* if (collectionStatus == 0) {

                        mImageIsCollectionPosts.setImageResource(R.mipmap.icon_gray_collection_posts);
                    } else {

                        mImageIsCollectionPosts.setImageResource(R.mipmap.icon_yellow_collection_posts);
                        showToast(PostRecommendDetailActivity.this,"收藏成功");
                    }*/

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
                showToast(getActivity(), Constants.getResultMsg(res.getMsg()));
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
                                intent.setClass(getActivity(), NewAlbumActivity.class);
                                startActivityForResult(intent, REQUEST_CODE_NEW_ALBUM);

                            }

                            @Override
                            public void setNewAlbumOnRefreshListener() {

                                isOnLoadMore = false;
                                mPageNo = 1;
                                favoritesAlbumList.clear();
                                collectEnterAlbumsDialog.setAlbumCollectList(favoritesAlbumList);
                                showProgressDialog(getActivity());
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

                        collectEnterAlbumsDialog.show(getActivity().getFragmentManager(), "收藏专辑");

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
                showToast(getActivity(), Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void lazyInitView(View view) {

    }

    @Subscribe
    public void onEvent(String mineFragmentRefresh) {

        if (visibleToUser) {
            if (mineFragmentRefresh.equals("followFragmentRefresh")) {
                if (followDetailList != null) {
                    followDetailList.clear();
                }
                mPageNo = 1;
                if (mRlytNoData != null) {
                    mRlytNoData.setVisibility(View.GONE);
                }
                if (followListAdapter != null) {
                    followListAdapter.notifyDataSetChanged();
                }
                getHomeAttention();
            }
        }
    }

    //帖子收藏
    public void postCollection(int favoritesId) {

        showProgressDialog(getActivity());
        PostsCollectionRequest postsCollectionRequest = new PostsCollectionRequest();
        postsCollectionRequest.setDataId(mPostId);
        postsCollectionRequest.setFavoritesId(favoritesId);
        int userId = (int) SharedPreferencesUtils.getData(SharedPreferencesUtils.USER_ID, 0);
        postsCollectionRequest.setUserId(userId);
        mPresenter.postCollection(postsCollectionRequest);
    }

    //获取专辑列表
    public void getFavoritesList() {

        showProgressDialog(getActivity());
        mPresenter.getFavoritesList(mPageNo, mPageSize);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
