package com.nbhysj.coupon.ui;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.ComFragmentAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.OthersHomePageContract;
import com.nbhysj.coupon.dialog.ShareOprateDialog;
import com.nbhysj.coupon.fragment.LocalFoodFragment;
import com.nbhysj.coupon.fragment.ScenicSpotFragment;
import com.nbhysj.coupon.model.OthersHomePageModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.MchCollectionResponse;
import com.nbhysj.coupon.model.response.UserPersonalHomePageResponse;
import com.nbhysj.coupon.presenter.OthersHomePagePresenter;
import com.nbhysj.coupon.systembar.StatusBarCompat;
import com.nbhysj.coupon.systembar.StatusBarUtil;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.ScreenUtil;
import com.nbhysj.coupon.view.ColorFlipPagerTitleView;
import com.nbhysj.coupon.view.JudgeNestedScrollView;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @auther：hysj created on 2019/07/31
 * description：个人主页
 */
public class UserPersonalHomePageActivity extends BaseActivity<OthersHomePagePresenter, OthersHomePageModel> implements OthersHomePageContract.View, JudgeNestedScrollView.OnScrollChangeListener {


    @BindView(R.id.toolbar_space)
    View mToolbarSpace;
    @BindView(R.id.llyt_header)
    LinearLayout mLlytHeaderToolbar;
    @BindView(R.id.scroll_view_personal_home_page)
    JudgeNestedScrollView mScrollViewPersonalHomePage;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.magic_indicator_title)
    MagicIndicator magicIndicatorTitle;
    @BindView(R.id.collapse)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.img_bg_avatar_blur)
    ImageView mImgBgAvatarBlur;
    @BindView(R.id.img_post_publisher_avatar)
    CircleImageView mImgPostPublisherAvatar;
    @BindView(R.id.img_menu)
    ImageView mImageMenu;
    @BindView(R.id.ibtn_back)
    ImageButton mIBtnBack;
    //粉丝数量
    @BindView(R.id.tv_fans_num)
    TextView mTvFansNum;
    //关注数量
    @BindView(R.id.tv_follow_num)
    TextView mTvFollowNum;
    //收藏数量
    @BindView(R.id.tv_collection_num)
    TextView mTvCollectionNum;
    //赞数量
    @BindView(R.id.tv_zan_num)
    TextView mTvZanNum;
    @BindView(R.id.tv_follow)
    TextView mTvFollow;
    @BindView(R.id.tv_nickname)
    TextView mTvNickName;
    //用户简介
    @BindView(R.id.tv_user_profile)
    TextView mTvUserProfile;

    @BindView(R.id.llyt_chat_with_others)
    LinearLayout mLlytChatWithOthers;
    int toolBarPositionY = 0;
    private int mScrollY = 0;
    private String[] mTitles = new String[]{"游记", "收藏", "赞过"};
    private List<String> mDataList = Arrays.asList(mTitles);
    private String publisherAvatarUrl;

    //帖子发布者id
    private int authorId;

    @Override
    public int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusBarCompat.translucentStatusBar(this, false);
        //修改状态栏字体颜色
        StatusBarUtil.setImmersiveStatusBar(this, true);
        return R.layout.activity_user_personal_home_page;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
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

        publisherAvatarUrl = getIntent().getStringExtra("publisherAvatarUrl");
        authorId = getIntent().getIntExtra("authorId",0);
        GlideUtil.loadImage(UserPersonalHomePageActivity.this, publisherAvatarUrl, mImgPostPublisherAvatar);

        GlideUtil.loadBlurImageUrl(UserPersonalHomePageActivity.this, publisherAvatarUrl, mImgBgAvatarBlur);

        //获取顶部图片高度后，设置滚动监听
        ViewTreeObserver vto = mImgBgAvatarBlur.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mImgBgAvatarBlur.getViewTreeObserver().removeGlobalOnLayoutListener(this);
               // height = mImgBgAvatarBlur.getHeight();
                mScrollViewPersonalHomePage.setOnScrollChangeListener(UserPersonalHomePageActivity.this);
            }
        });

        mLlytChatWithOthers.getBackground().setAlpha(30);
    }

    @Override
    public void initData() {

        getOthersHomePageInfo();

        mLlytHeaderToolbar.post(new Runnable() {
            @Override
            public void run() {
                dealWithViewPager();
            }
        });
        mScrollViewPersonalHomePage.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            int lastScrollY = 0;
            int h = DensityUtil.dp2px(170);
            int color = ContextCompat.getColor(getApplicationContext(), R.color.white) & 0x00ffffff;

            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.e(">>>>>>>>>>>>>>", scrollY + "");
                int[] location = new int[2];
                magicIndicator.getLocationOnScreen(location);
                int yPosition = location[1];
                if (yPosition < toolBarPositionY) {
                    magicIndicatorTitle.setVisibility(View.VISIBLE);
                    mScrollViewPersonalHomePage.setNeedScroll(false);
                } else {
                    magicIndicatorTitle.setVisibility(View.GONE);
                    mScrollViewPersonalHomePage.setNeedScroll(true);

                }

                if (lastScrollY < h) {
                    scrollY = Math.min(h, scrollY);
                    mScrollY = scrollY > h ? h : scrollY;
                    //    buttonBarLayout.setAlpha(1f * mScrollY / h);
                    mLlytHeaderToolbar.setBackgroundColor(((255 * mScrollY / h) << 24) | color);
                    // mLlytHeaderBg.setTranslationY(mOffset - mScrollY);
                }
                if (scrollY == 0) {

                    mIBtnBack.setImageDrawable(getResources().getDrawable(R.mipmap.icon_left_arrow_black));

                    mImageMenu.setImageDrawable(getResources().getDrawable(R.mipmap.icon_black_menu_more));
                    mLlytHeaderToolbar.setBackgroundColor(Color.argb(((255 * mScrollY / h) << 24) | color, 0, 0, 0));
                    mToolbarSpace.setBackgroundColor(Color.argb(((255 * mScrollY / h) << 24) | color, 0, 0, 0));
                } else {
                    mIBtnBack.setImageDrawable(getResources().getDrawable(R.mipmap.icon_left_arrow_black));
                    mImageMenu.setImageDrawable(getResources().getDrawable(R.mipmap.icon_black_menu_more));
                    mLlytHeaderToolbar.setBackgroundColor(Color.argb(255, 255, 255, 255));
                    mToolbarSpace.setBackgroundColor(Color.argb(255, 255, 255, 255));
                }

                lastScrollY = scrollY;
            }
        });
        //buttonBarLayout.setAlpha(0);
        mLlytHeaderToolbar.setBackgroundColor(0);

        viewPager.setAdapter(new ComFragmentAdapter(getSupportFragmentManager(), getFragments()));
        viewPager.setOffscreenPageLimit(10);
        initMagicIndicator();
        initMagicIndicatorTitle();

    }

    private void dealWithViewPager() {
        toolBarPositionY = mLlytHeaderToolbar.getHeight();
        ViewGroup.LayoutParams params = viewPager.getLayoutParams();
        params.height = ScreenUtil.getScreenHeightPx(getApplicationContext()) - toolBarPositionY - magicIndicator.getHeight() + 1;
        viewPager.setLayoutParams(params);
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    private List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new LocalFoodFragment());
        fragments.add(new ScenicSpotFragment());
        fragments.add(new ScenicSpotFragment());
        return fragments;
    }

    private void initMagicIndicator() {
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setScrollPivotX(0.65f);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorFlipPagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setNormalColor(ContextCompat.getColor(UserPersonalHomePageActivity.this, R.color.color_text_gray24));
                simplePagerTitleView.setSelectedColor(ContextCompat.getColor(UserPersonalHomePageActivity.this, R.color.black));
                simplePagerTitleView.setTextSize(16);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mScrollViewPersonalHomePage.scrollTo(0, 2835);
                        viewPager.setCurrentItem(index, false);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 4));
                indicator.setLineWidth(UIUtil.dip2px(context, 18));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(ContextCompat.getColor(UserPersonalHomePageActivity.this, R.color.color_high_light_green));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);
    }

    private void initMagicIndicatorTitle() {
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setScrollPivotX(0.65f);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorFlipPagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setNormalColor(ContextCompat.getColor(UserPersonalHomePageActivity.this, R.color.color_text_gray24));
                simplePagerTitleView.setSelectedColor(ContextCompat.getColor(UserPersonalHomePageActivity.this, R.color.black));
                simplePagerTitleView.setTextSize(16);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //int[] location = new int[2];
                        //magicIndicator.getLocationOnScreen(location);
                        mScrollViewPersonalHomePage.scrollTo(0, 2835);
                        int hight = mCollapsingToolbarLayout.getHeight();
                        System.out.print(hight + "");
                        viewPager.setCurrentItem(index, false);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 4));
                indicator.setLineWidth(UIUtil.dip2px(context, 18));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(ContextCompat.getColor(UserPersonalHomePageActivity.this, R.color.color_high_light_green));
                return indicator;
            }
        });
        magicIndicatorTitle.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicatorTitle, viewPager);
    }

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        int lastScrollY = 0;
        int h = DensityUtil.dp2px(170);
        int color = ContextCompat.getColor(getApplicationContext(), R.color.white) & 0x00ffffff;
        if (lastScrollY < h) {
            scrollY = Math.min(h, scrollY);
            mScrollY = scrollY > h ? h : scrollY;
            //    buttonBarLayout.setAlpha(1f * mScrollY / h);
            mLlytHeaderToolbar.setBackgroundColor(((255 * mScrollY / h) << 24) | color);
            // mLlytHeaderBg.setTranslationY(mOffset - mScrollY);
        }
        if (scrollY == 0) {

            mIBtnBack.setImageDrawable(getResources().getDrawable(R.mipmap.icon_left_arrow_white));
            mImageMenu.setImageDrawable(getResources().getDrawable(R.mipmap.icon_white_menu_more));
            mLlytHeaderToolbar.setBackgroundColor(Color.argb(0, 0, 0, 0));
            mToolbarSpace.setBackgroundColor(Color.argb(0, 0, 0, 0));
        } else {
            mIBtnBack.setImageDrawable(getResources().getDrawable(R.mipmap.icon_left_arrow_black));
            mImageMenu.setImageDrawable(getResources().getDrawable(R.mipmap.icon_black_menu_more));
            mLlytHeaderToolbar.setBackgroundColor(Color.argb(255, 255, 255, 255));
            mToolbarSpace.setBackgroundColor(Color.argb(255, 255, 255, 255));
        }

        lastScrollY = scrollY;
    }

    @OnClick({R.id.ibtn_back})
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.ibtn_back:

                UserPersonalHomePageActivity.this.finish();

                break;
            default:
                break;
        }
    }

    @Override
    public void getOthersHomePageInfoResult(BackResult<UserPersonalHomePageResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    UserPersonalHomePageResponse userPersonalHomePageResponse = res.getData();
                    String nickName = userPersonalHomePageResponse.getNickname();
                    int followNum = userPersonalHomePageResponse.getFollowNum();
                    int followStatus = userPersonalHomePageResponse.getFollowStatus();
                    int collectionNum = userPersonalHomePageResponse.getCollectionNum();
                    String profile = userPersonalHomePageResponse.getProfile();
                    int fansNum = userPersonalHomePageResponse.getFansNum();
                    int zanNum = userPersonalHomePageResponse.getZanNum();

                    mTvNickName.setText(nickName);

                    if(!TextUtils.isEmpty(profile))
                    {
                        mTvUserProfile.setText(profile);
                    }
                    mTvFansNum.setText(String.valueOf(zanNum));
                    mTvCollectionNum.setText(String.valueOf(collectionNum));
                    mTvFollowNum.setText(String.valueOf(followNum));
                    mTvFansNum.setText(String.valueOf(fansNum));

                    if (followStatus == 0) {
                        mTvFollow.setBackgroundResource(R.drawable.bg_blue_green_gradient_radius_five);

                    } else if (followStatus == 1) {

                        mTvFollow.setBackgroundResource(R.drawable.bg_stroke_radius_five_black_shape_white_edge);

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(UserPersonalHomePageActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(UserPersonalHomePageActivity.this, Constants.getResultMsg(msg));
    }

    public void getOthersHomePageInfo(){

        if(validateInternet()){

            showProgressDialog(UserPersonalHomePageActivity.this);
            mPresenter.getOthersHomePageInfo(authorId);
        }
    }
}
