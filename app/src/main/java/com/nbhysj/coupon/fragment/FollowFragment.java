package com.nbhysj.coupon.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.FollowListAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.HomePageContract;
import com.nbhysj.coupon.dialog.ShareOprateDialog;
import com.nbhysj.coupon.model.HomePageModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BannerUrlBO;
import com.nbhysj.coupon.model.response.FavoritesCollectionResponse;
import com.nbhysj.coupon.model.response.FavoritesListResponse;
import com.nbhysj.coupon.model.response.FollowUserStatusResponse;
import com.nbhysj.coupon.model.response.HomePageAllSearchResponse;
import com.nbhysj.coupon.model.response.HomePageResponse;
import com.nbhysj.coupon.model.response.HomePageSubTopicTagBean;
import com.nbhysj.coupon.model.response.HomePageTypeSearchResponse;
import com.nbhysj.coupon.model.response.PostInfoDetailResponse;
import com.nbhysj.coupon.model.response.RecommendInterestUsersBean;
import com.nbhysj.coupon.presenter.HomePagePresenter;
import com.nbhysj.coupon.ui.CommentsListActivity;
import com.nbhysj.coupon.ui.FindFriendsActivity;
import com.nbhysj.coupon.ui.PhoneQuickLoginActivity;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

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
    NestedScrollView setNeedScroll;
    private List<HomePageSubTopicTagBean> followDetailList;
    private List<BannerUrlBO> bannerUrlList;
    private FollowListAdapter followListAdapter;
    private int mPage = 1;
    private int mPageSize = 10;
    MyBroadcastReceiver receiver;

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
        receiver = new MyBroadcastReceiver();  //(这里可以写系统的广播接收者重写onReceiver方法就可以)
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.BROCAST_ACTION_FOLLOW);
        //注册receiver
        getActivity().registerReceiver(receiver, filter);
        getHomeAttention();
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
/*
        List<FollowDetailBean.UserEntity> userEntityList = new ArrayList<>();
        List<String> tagVal = new ArrayList<>();
        tagVal.add("#必吃美食");
        FollowDetailBean followDetailBean = new FollowDetailBean();

        FollowDetailBean.UserEntity userEntity = followDetailBean.new UserEntity();
        userEntity.setAvatar("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1378109966,2141209797&fm=26&gp=0.jpg");
        userEntity.setDes("旅游达人");
        userEntity.setUsername("小明");

        FollowDetailBean.UserEntity userEntity1 = followDetailBean.new UserEntity();
        userEntity1.setAvatar("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1550729403&di=f0527daf7298a984971e13a621f2eb9f&src=http://img.duoziwang.com/2016/11/24/033907101039.jpg");
        userEntity1.setDes("美食达人");
        userEntity1.setUsername("小张");
        userEntityList.add(userEntity);
        userEntityList.add(userEntity1);

        BannerUrlBO bannerUrlBO = new BannerUrlBO();
        bannerUrlBO.setUrl("http://img.bimg.126.net/photo/ZZ5EGyuUCp9hBPk6_s4Ehg==/5727171351132208489.jpg");

        BannerUrlBO bannerUrlBO1 = new BannerUrlBO();
        bannerUrlBO1.setUrl("https://t1.hddhhn.com/uploads/tu/201611/228/st87.png");

        BannerUrlBO bannerUrlBO2 = new BannerUrlBO();
        bannerUrlBO2.setUrl("http://gss0.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/lvpics/w=1000/sign=a669f57d3a12b31bc76cc929b628377a/503d269759ee3d6d801feef140166d224f4ade2b.jpg");

        bannerUrlList.add(bannerUrlBO);
        bannerUrlList.add(bannerUrlBO1);
        bannerUrlList.add(bannerUrlBO2);
        followDetailBean.setBannerList(bannerUrlList);
        followDetailBean.setName("测试");
        followDetailBean.setTime("2019-02-21");
        followDetailBean.setUrl("https://t1.hddhhn.com/uploads/tu/201611/168/st30.png");
        followDetailBean.setTagFlowLayout(tagVal);
        followDetailBean.setBannerList(bannerUrlList);
        followDetailBean.setInterestUserEntity(userEntityList);

        List<String> tagVal1 = new ArrayList<>();
        tagVal1.add("#必吃美食");
        tagVal1.add("#必玩景点");

        List<String> interestUser = new ArrayList<>();


        List<BannerUrlBO> bannerUrlList1 = new ArrayList<>();
        FollowDetailBean followDetailBean1 = new FollowDetailBean();

        BannerUrlBO bannerUrlBO3 = new BannerUrlBO();
        bannerUrlBO.setUrl("http://img.bimg.126.net/photo/ZZ5EGyuUCp9hBPk6_s4Ehg==/5727171351132208489.jpg");

        BannerUrlBO bannerUrlBO4 = new BannerUrlBO();
        bannerUrlBO1.setUrl("https://t1.hddhhn.com/uploads/tu/201611/228/st87.png");

        BannerUrlBO bannerUrlBO5 = new BannerUrlBO();
        bannerUrlBO2.setUrl("http://gss0.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/lvpics/w=1000/sign=a669f57d3a12b31bc76cc929b628377a/503d269759ee3d6d801feef140166d224f4ade2b.jpg");

        bannerUrlList1.add(bannerUrlBO3);
        bannerUrlList1.add(bannerUrlBO4);
        bannerUrlList1.add(bannerUrlBO5);
        followDetailBean1.setBannerList(bannerUrlList1);
        followDetailBean1.setName("测试");
        followDetailBean1.setTime("2019-02-21");
        followDetailBean1.setUrl("https://t1.hddhhn.com/uploads/tu/201611/168/st30.png");
        followDetailBean1.setTagFlowLayout(tagVal1);
        followDetailBean1.setBannerList(bannerUrlList);
        followDetailList.add(followDetailBean);
        followDetailList.add(followDetailBean1);*/

        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        // 设置布局管理器
        mRvFollow.setLayoutManager(layoutManager);

        followListAdapter = new FollowListAdapter(getActivity(), new FollowListAdapter.FollowListener() {

            @Override
            public void setUserOfInterestListener(RecommendInterestUsersBean userEntity) {
                Toast.makeText(mContext, "关注" + userEntity.getNickname(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void setUserOfInterestLookMoreListener() {

                toActivity(FindFriendsActivity.class);

            }

            @Override
            public void setLookCommentListener() {

                toActivity(CommentsListActivity.class);

            }

            @Override
            public void setCollectionPostToAlbumsListener(HomePageSubTopicTagBean followDetailBean) {


              /*  List<CollectionAlbumListResponse> collectionAlbumList = new ArrayList<>();

                CollectionAlbumListResponse collectionAlbumListResponse = new CollectionAlbumListResponse();
                collectionAlbumListResponse.setAlbumImage("http://pic35.nipic.com/20131115/13972544_160943053001_2.jpg");
                collectionAlbumListResponse.setAlbumName("专辑1");

                CollectionAlbumListResponse collectionAlbumListResponse1 = new CollectionAlbumListResponse();
                collectionAlbumListResponse1.setAlbumImage("http://pic35.nipic.com/20131115/13972544_160943053001_2.jpg");
                collectionAlbumListResponse1.setAlbumName("专辑2");

                CollectionAlbumListResponse collectionAlbumListResponse2 = new CollectionAlbumListResponse();
                collectionAlbumListResponse2.setAlbumImage("http://pic35.nipic.com/20131115/13972544_160943053001_2.jpg");
                collectionAlbumListResponse2.setAlbumName("专辑3");

                CollectionAlbumListResponse collectionAlbumListResponse3 = new CollectionAlbumListResponse();
                collectionAlbumListResponse3.setAlbumImage("http://pic35.nipic.com/20131115/13972544_160943053001_2.jpg");
                collectionAlbumListResponse3.setAlbumName("专辑4");

                CollectionAlbumListResponse collectionAlbumListResponse4 = new CollectionAlbumListResponse();
                collectionAlbumListResponse4.setAlbumImage("http://pic35.nipic.com/20131115/13972544_160943053001_2.jpg");
                collectionAlbumListResponse4.setAlbumName("专辑5");

                CollectionAlbumListResponse collectionAlbumListResponse5 = new CollectionAlbumListResponse();
                collectionAlbumListResponse5.setAlbumImage("http://pic35.nipic.com/20131115/13972544_160943053001_2.jpg");
                collectionAlbumListResponse5.setAlbumName("专辑6");

                collectionAlbumList.add(collectionAlbumListResponse);
                collectionAlbumList.add(collectionAlbumListResponse1);
                collectionAlbumList.add(collectionAlbumListResponse2);
                collectionAlbumList.add(collectionAlbumListResponse3);
                collectionAlbumList.add(collectionAlbumListResponse4);
                collectionAlbumList.add(collectionAlbumListResponse5);

                CollectEnterAlbumsDialog collectEnterAlbumsDialog = new CollectEnterAlbumsDialog(collectionAlbumList, new CollectEnterAlbumsDialog.ChooseAlbumsCollectionListener() {
                    @Override
                    public void setChooseAlbumsCollectionListener(FavoritesBean collectionAlbum) {

                    }

                    @Override
                    public void setNewAlbumCollectionListener() {

                    }
                });
                collectEnterAlbumsDialog.show(getActivity().getFragmentManager(), "收藏专辑");*/
            }

            @Override
            public void setFollowItemOnClickListener() {

                hiddenIME();

            }

            @Override
            public void setFollowShareListener() {

              /*  ShareDialog shareDialog = new ShareDialog();
                shareDialog.show(getActivity().getFragmentManager(),"");*/

                ShareOprateDialog shareOprateDialog = new ShareOprateDialog(getActivity(), new ShareOprateDialog.OnSharePlatformItemClickListener() {
                    @Override
                    public void onSharePlatformItemClick(String sharePlatform) {

                        //   showToast(getActivity(),sharePlatform);

                        new ShareAction(getActivity())
                                .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                                .withText("hello")//分享内容
                                .setCallback(umShareListener)//回调监听器
                                .share();

                    }
                }).builder().setCancelable(true).setCanceledOnTouchOutside(true);
                shareOprateDialog.show();
            }
        });
        followListAdapter.setFollowDetailList(followDetailList);
        mRvFollow.setAdapter(followListAdapter);
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

        setNeedScroll.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
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

                    mPage++;
                    showProgressDialog(getActivity());
                    getHomeAttention();

                }
            }

        });
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

    }

    @Override
    public void postOprateResult(BackResult res) {

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
                    if (followList == null) {

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

                    toActivity(PhoneQuickLoginActivity.class);

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

            mPresenter.getHomeAttention(mPage, mPageSize);
        }
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (Constants.BROCAST_ACTION_FOLLOW.equals(action)) {
                getHomeAttention();
            }
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

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {

            String token = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.TOKEN, "");
            if (TextUtils.isEmpty(token)) {
                toActivity(PhoneQuickLoginActivity.class);
            }
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

    }

    @Override
    public void getFavoritesListResult(BackResult<FavoritesListResponse> res) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(receiver != null) {
            getActivity().unregisterReceiver(receiver);
        }
    }

    @Override
    public void lazyInitView(View view) {

    }
}
