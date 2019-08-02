package com.nbhysj.coupon.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.UserInfoContract;
import com.nbhysj.coupon.model.UserInfoModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.ThirdPartyLoginStatusResponse;
import com.nbhysj.coupon.model.response.UserInfoResponse;
import com.nbhysj.coupon.presenter.UserInfoPresenter;
import com.nbhysj.coupon.ui.AccountAndPersonalDataActivity;
import com.nbhysj.coupon.ui.BezierViewDemoActivity;
import com.nbhysj.coupon.ui.LoginActivity;
import com.nbhysj.coupon.ui.MyBusinessCardActivity;
import com.nbhysj.coupon.ui.MyOrderActivity;
import com.nbhysj.coupon.ui.PersonalSettingsActivity;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.nbhysj.coupon.util.blurbehind.BlurBehind;
import com.nbhysj.coupon.util.blurbehind.OnBlurCompleteListener;
import com.nbhysj.coupon.view.GlideImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
/**
 * created by hysj on 2018/07/31.
 * description: 我的
 */
public class MineFragment extends BaseFragment<UserInfoPresenter, UserInfoModel> implements UserInfoContract.View {
    @BindView(R.id.image_avatar)
    GlideImageView mImgAvatar;
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.app_barlayout)
    AppBarLayout appBarlayout;
    @BindView(R.id.rlyt_toolbar)
    RelativeLayout mRlytToolbar;
    @BindView(R.id.llyt_order)
    LinearLayout mLlytOrder;
    @BindView(R.id.llyt_data_statistics)
    LinearLayout mLlytDataStatistics;
    @BindView(R.id.rlyt_user_info)
    RelativeLayout mRlytUserInfo;
    @BindView(R.id.rlyt_my_business_card)
    RelativeLayout mRlytMyBusinessCard;
    //昵称
    @BindView(R.id.tv_nickname)
    TextView mTvNickname;
    //简介
    @BindView(R.id.tv_user_profile)
    TextView mTvUserProfile;
    @BindView(R.id.toolbar_collapsing)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    private int userId;
    private boolean isFristCreate = true;
    private boolean isLoginFristCreate = true;

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

        String avatar = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.USER_AVATAR, "");
        if (!TextUtils.isEmpty(avatar)) {
            mImgAvatar.loadCircle(avatar);
        } else {

            mImgAvatar.loadCircle("https://img5.duitang.com/uploads/item/201410/05/20141005190442_nuceP.thumb.700_0.jpeg");
        }
      /* ViewGroup.LayoutParams layoutParams = mToolbarSpace.getLayoutParams();//取控件当前的布局参数
        layoutParams.height = getStatusBarHeight();// 控件的高强制设成状态栏高度
        mToolbarSpace.setLayoutParams(layoutParams); //使设置好的布局参数应用到控件</pre>
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbarSpace.setVisibility(View.VISIBLE);        } else {
            mToolbarSpace.setVisibility(View.GONE);
        }*/

        viewpager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            private String[] mTitles = new String[]{"分享", "收藏", "赞过"};

            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return new ShareFragment();
                } else if (position == 1) {
                    return new CollectionFragment();
                } else if (position == 2) {
                    return new RecommendFragment();
                }

                return null;
            }

            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }

        });

        //  fragments.add(nearbyFragment);

        tabLayout.setViewPager(viewpager);
        // getChildFragmentManager().beginTransaction().add(R.id.fl_mine, followFragment).commit();
    }

    @Override
    public void initData() {
        /*appBarlayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    mRlytToolbar.setBackgroundColor(Color.argb(0, 0, 0, 0));
                    mLlytOrder.setVisibility(View.VISIBLE);
                    mLlytDataStatistics.setVisibility(View.VISIBLE);
                  *//*  ivLeftHotBtn.setBackground(getResources().getDrawable(R.drawable.shap_left_btn_bg));
                    ivSearchBtn.setVisibility(View.VISIBLE);
                    ivSearchBtn.setBackground(getResources().getDrawable(R.drawable.shap_left_btn_bg));
                    llSearch.setVisibility(View.VISIBLE);*//*
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    mRlytToolbar.setBackgroundColor(Color.argb(255, 29, 235, 150));
                    mLlytOrder.setVisibility(View.GONE);
                    mLlytDataStatistics.setVisibility(View.GONE);
                   *//* ivLeftHotBtn.setBackground(null);
                    llSearch.setVisibility(View.VISIBLE);
                    ivSearchBtn.setBackground(null);*//*
                } else {
                    int alpha = (int) (255 - verticalOffset / (float) appBarLayout.getTotalScrollRange() * 255);
                    mRlytToolbar.setBackgroundColor(Color.argb(alpha, 29, 235, 150));
                    mLlytOrder.setVisibility(View.VISIBLE);
                    mLlytDataStatistics.setVisibility(View.VISIBLE);
                  *//*  llSearch.setVisibility(View.VISIBLE);
                    ivSearchBtn.setVisibility(View.VISIBLE);*//*
                }
            }
        });*/

        /**
         * 监听滑动状态
         */
      /*  appBarlayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener()
        {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset)
            {
                if (verticalOffset == 0)
                {
                    showToast(getActivity(),"展开状态");
                }
                else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange())
                {
                    showToast(getActivity(),"折叠状态");
                }
                else
                {
                    showToast(getActivity(),"中间状态");
                }
            }
        });*/
    }

    @OnClick({R.id.img_personal_setting, R.id.rlyt_avatar, R.id.llyt_user_info, R.id.llyt_all_order, R.id.rlyt_my_business_card})
    public void onClick(View view) {
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
                toActivity(MyOrderActivity.class);
                break;
            case R.id.rlyt_my_business_card:

                BlurBehind.getInstance().execute(getActivity(), new OnBlurCompleteListener() {
                    @Override
                    public void onBlurComplete() {

                        Intent intent = new Intent(getActivity(), MyBusinessCardActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                        startActivity(intent);
                    }
                });
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
                    if (TextUtils.isEmpty(profile)) {
                        mTvUserProfile.setVisibility(View.GONE);

                    } else {

                        mTvUserProfile.setVisibility(View.VISIBLE);
                        mTvUserProfile.setText(profile);
                    }
                    if (!TextUtils.isEmpty(avatar)) {
                        mImgAvatar.loadCircle(avatar);
                    } else {

                        mImgAvatar.loadCircle("https://img5.duitang.com/uploads/item/201410/05/20141005190442_nuceP.thumb.700_0.jpeg");
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
    public void updateInformationResult(BackResult res) {

    }

    @Override
    public void userLogoutResult(BackResult res) {

    }

    @Override
    public void getThirdPartyLoginStatusResult(BackResult<ThirdPartyLoginStatusResponse> res) {

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
                userId = (int) SharedPreferencesUtils.getData(SharedPreferencesUtils.USER_ID, 0);
                if (validateInternet()) {
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
                } else {

                    mImgAvatar.loadCircle("https://img5.duitang.com/uploads/item/201410/05/20141005190442_nuceP.thumb.700_0.jpeg");
                }
                isLoginFristCreate = false;
            }
        } else {
            if (isFristCreate) {
                toActivity(LoginActivity.class);
                isFristCreate = false;
            }
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {

            String token = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.TOKEN, "");
            if (TextUtils.isEmpty(token)) {

                toActivity(LoginActivity.class);
            }
        }
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
