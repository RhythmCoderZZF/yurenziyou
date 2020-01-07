package com.nbhysj.coupon.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.HomePageContract;
import com.nbhysj.coupon.dialog.CollectEnterAlbumsDialog;
import com.nbhysj.coupon.model.HomePageModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CollectionAlbumListResponse;
import com.nbhysj.coupon.model.response.FavoritesCollectionResponse;
import com.nbhysj.coupon.model.response.FavoritesListResponse;
import com.nbhysj.coupon.model.response.FollowUserStatusResponse;
import com.nbhysj.coupon.model.response.HomePageAllSearchResponse;
import com.nbhysj.coupon.model.response.HomePageResponse;
import com.nbhysj.coupon.model.response.HomePageSubTopicTagBean;
import com.nbhysj.coupon.model.response.HomePageTypeSearchResponse;
import com.nbhysj.coupon.model.response.PostInfoDetailResponse;
import com.nbhysj.coupon.presenter.HomePagePresenter;
import com.nbhysj.coupon.ui.HomePageSearchActivity;
import com.nbhysj.coupon.ui.MessageActivity;
import com.nbhysj.coupon.ui.PhoneQuickLoginActivity;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.nbhysj.coupon.widget.glide.CacheImageLoader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @auther：hysj created on 2019/03/04
 * description：首页
 */
public class HomeFragment extends BaseFragment<HomePagePresenter, HomePageModel> implements HomePageContract.View {

    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.banner_home)
    Banner mBannerHome;
    private ArrayList<String> bannerList;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.app_barlayout)
    AppBarLayout appBarlayout;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.ll_search)
    LinearLayout mLlytSearch;
    @BindView(R.id.rlyt_message_num)
    RelativeLayout mRlytMessageNum;
    @BindView(R.id.img_bar_search)
    ImageView mImgBarSearch;
    //未读消息数量
    @BindView(R.id.tv_msg_unread_num)
    TextView mTvUnreadMsgNum;
    private ArrayList<Fragment> fragments;
    private FollowFragment followFragment;
    private RecommendFragment recommendFragment;
    private NearbyFragment nearbyFragment;
    private String[] titles = new String[]{"关注", "推荐", "附近"};
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.tv_home_city)
    TextView mTvHomeCity;
    private int mCurrentItem = 1;
    //标识广播通知对应的页面
    private int mCurrentItemFlag = 1;
    private boolean initComplete = false;
    private boolean isOnTabSelect = true;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    public void getHomeAttentionResult(BackResult<HomePageResponse> res) {

    }

    @Override
    public void getHomePageIndexResult(BackResult<HomePageResponse> res) {

        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    //   mSmartRefreshLayout.finishRefresh();
                    bannerList.clear();
                    initComplete = true;
                    HomePageResponse.ResultBean resultBean = res.getData().getResult();
                    List<HomePageResponse.ResultBean.BannersBean> bannersList = resultBean.getBanners();
                    List<HomePageResponse.ResultBean.PostsTagsBean> postsTagsList = resultBean.getPostsTags();
                    //  List<HomePageSubTopicTagBean> list = resultBean.getList();

                    for (int i = 0; i < bannersList.size(); i++) {
                        HomePageResponse.ResultBean.BannersBean bannersBean = bannersList.get(i);
                        bannerList.add(bannersBean.getPhoto());
                    }

                    mBannerHome.setImages(bannerList);
                    mBannerHome.isAutoPlay(true);//禁止轮播
                    mBannerHome.setDelayTime(5000);
                    mBannerHome.setIndicatorGravity(BannerConfig.LEFT);
                    mBannerHome.setImageLoader(new CacheImageLoader());
                    mBannerHome.start();

                    HomePageResponse.ResultBean.PostsTagsBean recommendPostsTags = postsTagsList.get(2);
                    recommendFragment.newInstance(recommendPostsTags);

                    HomePageResponse.ResultBean.PostsTagsBean nearTagsBean = postsTagsList.get(1);
                    nearbyFragment.newInstance(nearTagsBean);
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
    public void queryByTopicResult(BackResult<HomePageResponse> res) {

    }

    @Override
    public void getPostInfoResult(BackResult<PostInfoDetailResponse> res) {

    }

    @Override
    public void postsCommentResult(BackResult res) {

    }

    @Override
    public void getHomePageSearchAllResult(BackResult<HomePageAllSearchResponse> res) {

    }

    @Override
    public void getHomePageSearchByTypeResult(BackResult<HomePageTypeSearchResponse> res) {

    }

    @Override
    public void postOprateResult(BackResult res) {

    }

    @Override
    public void userFollowResult(BackResult<FollowUserStatusResponse> res) {

    }

    @Override
    public void getUnReadMessageListResult(BackResult<Integer> res) {
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    int unreadMsgNum = res.getData();
                    if(unreadMsgNum > 0){
                        mTvUnreadMsgNum.setVisibility(View.VISIBLE);
                        mTvUnreadMsgNum.setText(String.valueOf(unreadMsgNum));
                    } else {
                        mTvUnreadMsgNum.setVisibility(View.GONE);

                    }

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
    public void showMsg(String msg) {
        if (mSmartRefreshLayout != null) {
            mSmartRefreshLayout.finishRefresh();
        }
        dismissProgressDialog();
        showToast(getActivity(), Constants.getResultMsg(msg));
    }

    @Override
    public void initView(View v) {
      /* ViewGroup.LayoutParams layoutParams = mToolbarSpace.getLayoutParams();//取控件当前的布局参数
        layoutParams.height = getStatusBarHeight();// 控件的高强制设成状态栏高度
        mToolbarSpace.setLayoutParams(layoutParams); //使设置好的布局参数应用到控件</pre>
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbarSpace.setVisibility(View.VISIBLE);
        } else {
            mToolbarSpace.setVisibility(View.GONE);
        }*/
        mLlytSearch.setAlpha(0.55f);

        if (bannerList == null) {
            bannerList = new ArrayList<>();

        } else {

            bannerList.clear();
        }

        fragments = new ArrayList<>();
        List<Fragment> fragments1 = getChildFragmentManager().getFragments();
        if (fragments1 != null && fragments1.size() > 1) {

            followFragment = (FollowFragment) fragments1.get(0);
            recommendFragment = (RecommendFragment) fragments1.get(1);
            nearbyFragment = (NearbyFragment) fragments1.get(2);

        } else {

            followFragment = new FollowFragment();
            recommendFragment = new RecommendFragment();
            nearbyFragment = new NearbyFragment();
        }

        fragments.add(followFragment);
        fragments.add(recommendFragment);
        fragments.add(nearbyFragment);

        tabLayout.setViewPager(viewpager, titles, getActivity(), fragments);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                isOnTabSelect = true;
                String token = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.TOKEN, "");
                if (TextUtils.isEmpty(token) && mCurrentItem == 0)
                {
                    toActivity(PhoneQuickLoginActivity.class);
                    viewpager.setCurrentItem(mCurrentItemFlag);
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                    mCurrentItem = position;
                    if(mCurrentItem != 0)
                    {
                        mCurrentItemFlag = position;
                    }

                    if(!isOnTabSelect)
                    {
                        String token = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.TOKEN, "");
                        if (TextUtils.isEmpty(token) && mCurrentItem == 0) {
                            toActivity(PhoneQuickLoginActivity.class);
                            viewpager.setCurrentItem(mCurrentItemFlag);
                        }
                    }
                isOnTabSelect = false;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mBannerHome.setImages(bannerList);
        mBannerHome.isAutoPlay(true);//禁止轮播
        mBannerHome.setDelayTime(5000);
        mBannerHome.setIndicatorGravity(BannerConfig.LEFT);
        mBannerHome.setImageLoader(new CacheImageLoader());
        mBannerHome.start();
        viewpager.setCurrentItem(mCurrentItem);

    }

    @Override
    public void initData() {
        getHomePageData();
       /* mReScrollViewMain.setScrolListener(new RecyclerScrollView.OnScrollListener() {
            @Override
            public void onScroll(int scrollY) {
                if (scrollY < 10) {
                    mLlytToolbar.setBackgroundColor(Color.argb(0, 221, 48, 10));
                } else {
                    mLlytToolbar.setBackgroundColor(Color.argb(50, 221, 48, 10));
                }
            }
        });*/

        appBarlayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    rlTitle.setBackgroundColor(Color.argb(0, 0, 0, 0));
                    mLlytSearch.setBackgroundResource(R.drawable.bg_home_search);

                    mImgBarSearch.setImageResource(R.mipmap.icon_home_search);

                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    rlTitle.setBackgroundColor(Color.argb(255, 255, 255, 255));
                    mLlytSearch.setBackgroundResource(R.drawable.btn_oprate_bg);
                    mImgBarSearch.setImageResource(R.mipmap.icon_bar_search);

                } else {
                    int alpha = (int) (255 - verticalOffset / (float) appBarLayout.getTotalScrollRange() * 255);
                    rlTitle.setBackgroundColor(Color.argb(alpha, 255, 255, 255));
                    mLlytSearch.setBackgroundResource(R.drawable.btn_oprate_bg);
                    mLlytSearch.getBackground().setAlpha(alpha);
                    mImgBarSearch.setImageResource(R.mipmap.icon_bar_search);

                }
            }
        });

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // MyRecommendFragment.isRefrsh = true;
                        HomeRecommendFragment.currentFragment = 0;
                        //getHomePageData();

                        if (!initComplete) {

                            getHomePageData();
                        } else {
                            if(mCurrentItem == 0){
                                EventBus.getDefault().post("followFragmentRefresh");
                            } else if(mCurrentItem == 1){
                                EventBus.getDefault().post("homeFragmentRefresh");
                            } else if(mCurrentItem == 2){
                                EventBus.getDefault().post("nearbyFragmentRefresh");
                            }
                          //  EventBus.getDefault().post("homeFragmentRefresh");
                          /*  Intent intent = new Intent();
                            //指定发送广播的频道
                            intent.setAction(mBroadcastAction);
                            //发送广播的数据
                            getActivity().sendBroadcast(intent);*/


                        }

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (mSmartRefreshLayout != null) {
                                    mSmartRefreshLayout.finishRefresh();
                                }
                            }
                        });
                    }
                }, 100);
            }
        });
    }

    @Override
    public void postCollectionResult(BackResult<FavoritesCollectionResponse> res) {

    }

    @Override
    public void getFavoritesListResult(BackResult<FavoritesListResponse> res) {

    }

    @OnClick({R.id.rlyt_message_num,R.id.ll_search})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlyt_message_num:
                Intent intent = new Intent();
                intent.setClass(getActivity(), MessageActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_search:
                toActivity(HomePageSearchActivity.class);
                break;
            default:
                break;
        }
    }

    /**
     * Android 6.0 以上设置状态栏颜色
     */
    protected void setStatusBar(@ColorInt int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // 设置状态栏底色颜色
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            /*    getWindow().setStatusBarColor(color);*/

            // 如果亮色，设置状态栏文字为黑色
            if (isLightColor(color)) {
                getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            }
        }
    }

    /**
     * 判断颜色是不是亮色
     *
     * @param color
     * @return
     * @from https://stackoverflow.com/questions/24260853/check-if-color-is-dark-or-light-in-android
     */
    private boolean isLightColor(@ColorInt int color) {
        return ColorUtils.calculateLuminance(color) >= 0.5;
    }

    public void getHomePageData() {

        if (validateInternet()) {

            mPresenter.getHomePageIndex();
        }
    }

    @Override
    public void lazyInitView(View view) {

    }

    @Override
    public void onResume() {
        super.onResume();
        getHomePageUnReadMsg();
    }

    //未读消息列表
    public void getHomePageUnReadMsg(){
        if(validateInternet())
        {
            mPresenter.getHomePageUnReadMsg();
        }
    }

}
