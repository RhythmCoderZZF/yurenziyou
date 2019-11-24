package com.nbhysj.coupon.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.ComFragmentAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.UserInfoContract;
import com.nbhysj.coupon.model.UserInfoModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.MyCardResponse;
import com.nbhysj.coupon.model.response.ThirdPartyLoginStatusResponse;
import com.nbhysj.coupon.model.response.UserInfoResponse;
import com.nbhysj.coupon.presenter.UserInfoPresenter;
import com.nbhysj.coupon.ui.AlbumDetailsActivity;
import com.nbhysj.coupon.ui.CollectionActivity;
import com.nbhysj.coupon.ui.CouponListActivity;
import com.nbhysj.coupon.ui.FollowAndFansActivity;
import com.nbhysj.coupon.ui.MyBusinessCardActivity;
import com.nbhysj.coupon.ui.MyOrderActivity;
import com.nbhysj.coupon.ui.PersonalSettingsActivity;
import com.nbhysj.coupon.ui.PhoneQuickLoginActivity;
import com.nbhysj.coupon.ui.ZanActivity;
import com.nbhysj.coupon.util.ScreenUtil;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.nbhysj.coupon.util.blurbehind.BlurBehind;
import com.nbhysj.coupon.util.blurbehind.OnBlurCompleteListener;
import com.nbhysj.coupon.view.ColorFlipPagerTitleView;
import com.nbhysj.coupon.view.GlideImageView;
import com.nbhysj.coupon.view.JudgeNestedScrollView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
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

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * created by hysj on 2018/07/31.
 * description: 我的
 */
public class MineFragment extends BaseFragment<UserInfoPresenter, UserInfoModel> implements UserInfoContract.View {
    @BindView(R.id.toolbar_space)
    View mToolbarSpace;
    @BindView(R.id.image_avatar)
    GlideImageView mImgAvatar;
    @BindView(R.id.rlyt_toolbar)
    RelativeLayout mRlytToolbar;
    @BindView(R.id.llyt_order)
    LinearLayout mLlytOrder;
    @BindView(R.id.llyt_data_statistics)
    LinearLayout mLlytDataStatistics;
    @BindView(R.id.rlyt_user_info)
    RelativeLayout mRlytUserInfo;
    //昵称
    @BindView(R.id.tv_nickname)
    TextView mTvNickname;
    //简介
    @BindView(R.id.tv_user_profile)
    TextView mTvUserProfile;
    @BindView(R.id.llyt_header)
    LinearLayout mLlytHeaderToolbar;
    //指示器
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.magic_indicator_title)
    MagicIndicator magicIndicatorTitle;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.scrollView)
    JudgeNestedScrollView scrollView;
    @BindView(R.id.collapse)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
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

    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    private int userId;
    private boolean isFristCreate = true;
    private boolean isLoginFristCreate = true;

    int toolBarPositionY = 0;
    private int mScrollY = 0;
    private String[] mTitles = new String[]{"分享", "收藏", "赞过"};
    private List<String> mDataList = Arrays.asList(mTitles);


    @Override
    public int getLayoutId() {

        return R.layout.fragment_mine;
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    public void lazyInitView(View view) {

    }

    @Override
    public void initView(View v) {
        //沉浸式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);
            getActivity().getWindow().getDecorView()
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
        String avatar = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.USER_AVATAR, "");
        if (!TextUtils.isEmpty(avatar)) {
            mImgAvatar.loadCircle(avatar);
        }
        String token = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.TOKEN, "");
        if (!TextUtils.isEmpty(token)) {

                if (validateInternet()) {
                    userId = (int) SharedPreferencesUtils.getData(SharedPreferencesUtils.USER_ID, 0);
                    getUserInfo();
                }

            }
        }

    @Override
    public void initData() {

        mLlytHeaderToolbar.post(new Runnable() {
            @Override
            public void run() {
                dealWithViewPager();
            }
        });
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            int lastScrollY = 0;
            int h = DensityUtil.dp2px(170);
            int color = ContextCompat.getColor(getActivity(), R.color.color_blue3) & 0x00ffffff;

            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.e(">>>>>>>>>>>>>>", scrollY + "");
                int[] location = new int[2];
                magicIndicator.getLocationOnScreen(location);
                int yPosition = location[1];
                if (yPosition < toolBarPositionY) {
                    magicIndicatorTitle.setVisibility(View.VISIBLE);
                    scrollView.setNeedScroll(false);
                } else {
                    magicIndicatorTitle.setVisibility(View.GONE);
                    scrollView.setNeedScroll(true);

                }

                if (lastScrollY < h) {
                    scrollY = Math.min(h, scrollY);
                    mScrollY = scrollY > h ? h : scrollY;
                    //    buttonBarLayout.setAlpha(1f * mScrollY / h);
                    mLlytHeaderToolbar.setBackgroundColor(((255 * mScrollY / h) << 24) | color);
                    // mLlytHeaderBg.setTranslationY(mOffset - mScrollY);
                }
                if (scrollY == 0) {
                    // mLlytHeaderToolbar.setBackgroundColor(0);
                    //ivBack.setImageResource(R.drawable.back_white);
                    // ivMenu.setImageResource(R.drawable.icon_menu_white);
                } else {
                    // ivBack.setImageResource(R.drawable.back_black);
                    // ivMenu.setImageResource(R.drawable.icon_menu_black);
                }

                lastScrollY = scrollY;
            }
        });
        //mLlytHeaderToolbar.setBackgroundColor(0);

        viewPager.setAdapter(new ComFragmentAdapter(getChildFragmentManager(), getFragments()));
        viewPager.setOffscreenPageLimit(3);
        initMagicIndicator();
        initMagicIndicatorTitle();

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        EventBus.getDefault().post("mineFragmentRefresh");
                        if (mSmartRefreshLayout != null) {
                            mSmartRefreshLayout.finishRefresh();
                        }

                    }
                }, 100);
            }
        });

    }

    private List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new ShareFragment());
        fragments.add(new CollectionFragment());
        fragments.add(new MinePostZanListFragment());
        return fragments;
    }

    private void dealWithViewPager() {
        toolBarPositionY = mLlytHeaderToolbar.getHeight();
        ViewGroup.LayoutParams params = viewPager.getLayoutParams();
        params.height = ScreenUtil.getScreenHeightPx(getActivity().getApplicationContext()) - toolBarPositionY - magicIndicator.getHeight() + 1;
        viewPager.setLayoutParams(params);
    }

    @OnClick({R.id.img_personal_setting, R.id.rlyt_avatar, R.id.llyt_user_info, R.id.llyt_all_order, R.id.llyt_pending_payment, R.id.llyt_pending_travel, R.id.llyt_pending_comment, R.id.llyt_order_refund,
            R.id.img_qr_my_card, R.id.rlyt_my_coupon, R.id.llyt_fans_num, R.id.llyt_zan,R.id.llyt_collection,R.id.llyt_follow_num})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.img_personal_setting:

                toActivity(PersonalSettingsActivity.class);
                break;
            case R.id.rlyt_avatar:
                toActivity(PersonalSettingsActivity.class);
                break;
            case R.id.llyt_user_info:
                toActivity(PersonalSettingsActivity.class);
                break;
            case R.id.llyt_all_order:

                intent.putExtra("orderStatus", 0);  //自定义0:全部订单 1:待支付 2:待出行 3:待评价 4:售后
                intent.setClass(getActivity(), MyOrderActivity.class);
                getActivity().startActivity(intent);

                break;
            case R.id.llyt_pending_payment:

                intent.putExtra("orderStatus", 1);  //自定义0:全部订单 1:待支付 2:待出行 3:待评价 4:售后
                intent.setClass(getActivity(), MyOrderActivity.class);
                getActivity().startActivity(intent);
                break;

            case R.id.llyt_pending_travel:

                intent.putExtra("orderStatus", 2);  //自定义0:全部订单 1:待支付 2:待出行 3:待评价 4:售后
                intent.setClass(getActivity(), MyOrderActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.llyt_pending_comment:

                intent.putExtra("orderStatus", 3);  //自定义0:全部订单 1:待支付 2:待出行 3:待评价 4:售后
                intent.setClass(getActivity(), MyOrderActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.llyt_order_refund:

                intent.putExtra("orderStatus", 4);  //自定义0:全部订单 1:待支付 2:待出行 3:待评价 4:售后
                intent.setClass(getActivity(), MyOrderActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.img_qr_my_card:
                BlurBehind.getInstance().execute(getActivity(), new OnBlurCompleteListener() {
                    @Override
                    public void onBlurComplete() {

                        Intent intent = new Intent(getActivity(), MyBusinessCardActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                        startActivity(intent);
                    }
                });
                break;
            case R.id.rlyt_my_coupon:

                toActivity(CouponListActivity.class);

                break;
            case R.id.llyt_fans_num:
                intent.putExtra("currentItem",0);
                intent.setClass(getActivity(),FollowAndFansActivity.class);
                startActivity(intent);

                break;
            case R.id.llyt_zan:
                toActivity(ZanActivity.class);
                break;
            case R.id.llyt_collection:
                toActivity(CollectionActivity.class);
                break;
            case R.id.llyt_follow_num:
                intent.putExtra("currentItem",1);
                intent.setClass(getActivity(),FollowAndFansActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }

    @Override
    public void getUserInfoResult(BackResult<UserInfoResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    UserInfoResponse userInfoResponse = res.getData();
                    String nickname = userInfoResponse.getNickname();
                    String avatar = userInfoResponse.getAvater();      //用户头像
                    String profile = userInfoResponse.getProfile();   //简介
                    int sex = userInfoResponse.getSex();              //性别
                    String birthday = userInfoResponse.getBirthday(); //生日
                    String fansNum = String.valueOf(userInfoResponse.getFansNum()); //粉丝数
                    String followNum = String.valueOf(userInfoResponse.getFollowNum()); //关注数
                    String collectionNum = String.valueOf(userInfoResponse.getCollectionNum()); //收藏数
                    String zanNum = String.valueOf(userInfoResponse.getZanNum()); //点赞数
                    SharedPreferencesUtils.saveUserInfoData(avatar, sex, birthday, profile, fansNum, followNum, collectionNum, zanNum);
                    mTvNickname.setText(nickname);
                    mTvFansNum.setText(fansNum);
                    mTvFollowNum.setText(followNum);
                    mTvCollectionNum.setText(collectionNum);
                    mTvZanNum.setText(zanNum);
                    if (TextUtils.isEmpty(profile)) {
                        mTvUserProfile.setVisibility(View.GONE);

                    } else {

                        mTvUserProfile.setVisibility(View.VISIBLE);
                        mTvUserProfile.setText(profile);
                    }
                    if (!TextUtils.isEmpty(avatar)) {
                        mImgAvatar.loadCircle(avatar);
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
    public void updateInformationResult(BackResult res) {

    }

    @Override
    public void userLogoutResult(BackResult res) {

    }

    @Override
    public void getThirdPartyLoginStatusResult(BackResult<ThirdPartyLoginStatusResponse> res) {

    }

    @Override
    public void getMyCardResult(BackResult<MyCardResponse> res) {

    }

    @Override
    public void showMsg(String msg) {
        dismissProgressDialog();
        showToast(getActivity(), Constants.getResultMsg(msg));
    }

    public void getUserInfo() {

        if (validateInternet()) {

            mPresenter.getUserInfo(userId);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        String token = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.TOKEN, "");
        if (!TextUtils.isEmpty(token)) {

            if (!isLoginFristCreate) {

                if (validateInternet()) {
                    userId = (int) SharedPreferencesUtils.getData(SharedPreferencesUtils.USER_ID, 0);
                    getUserInfo();
                }

            } else {
                String userName = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.NICKNAME, "");

                String profile = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.USER_PROFILE, "");
                mTvNickname.setText(userName);
                if (TextUtils.isEmpty(profile)) {
                    mTvUserProfile.setVisibility(View.GONE);

                } else {

                    mTvUserProfile.setVisibility(View.VISIBLE);
                    mTvUserProfile.setText(profile);
                }
                String avatar = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.USER_AVATAR, "");
                if (!TextUtils.isEmpty(avatar)) {
                    mImgAvatar.loadCircle(avatar);
                }
                isLoginFristCreate = false;
            }
        } else {
            if (isFristCreate) {
                toActivity(PhoneQuickLoginActivity.class);
                isFristCreate = false;
                isLoginFristCreate = false;
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

    private void initMagicIndicator() {
        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
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
                simplePagerTitleView.setNormalColor(ContextCompat.getColor(getActivity(), R.color.color_text_gray24));
                simplePagerTitleView.setSelectedColor(ContextCompat.getColor(getActivity(), R.color.black));
                simplePagerTitleView.setTextSize(16);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // scrollView.scrollTo(0, 2835);
                        viewPager.setCurrentItem(index, false);
                        //  simplePagerTitleView.setTypeface(Typeface.DEFAULT_BOLD);
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
                indicator.setColors(ContextCompat.getColor(getActivity(), R.color.color_high_light_green));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);
    }

    private void initMagicIndicatorTitle() {
        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
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
                simplePagerTitleView.setNormalColor(ContextCompat.getColor(getActivity(), R.color.color_text_gray24));
                simplePagerTitleView.setSelectedColor(ContextCompat.getColor(getActivity(), R.color.black));
                simplePagerTitleView.setTextSize(16);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //int[] location = new int[2];
                        //magicIndicator.getLocationOnScreen(location);
                        //  scrollView.scrollTo(0, 2835);
                        int hight = mCollapsingToolbarLayout.getHeight();
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
                indicator.setColors(ContextCompat.getColor(getActivity(), R.color.color_high_light_green));
                return indicator;
            }
        });
        magicIndicatorTitle.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicatorTitle, viewPager);
    }

    @Override
    public boolean getUserVisibleHint() {
        System.out.print("112");
        return super.getUserVisibleHint();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        System.out.print("112");
    }
}
