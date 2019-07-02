package com.nbhysj.coupon.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.FindFriendsPictureAdapter;
import com.nbhysj.coupon.model.response.ImageData;
import com.nbhysj.coupon.model.response.FindFriendsPictureBean;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.nbhysj.coupon.view.GlideImageView;
import com.nbhysj.coupon.view.MyRecycleView;
import com.nbhysj.coupon.view.RecyclerScrollView;
import com.nbhysj.coupon.widget.glide.BlurTransformation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FindFriendsActivity extends BaseActivity {
    @BindView(R.id.toolbar_space)
    View mToolbarSpace;
    @BindView(R.id.rv_recommend_find_friends)
    MyRecycleView mRvRecommendFindFriends;
    @BindView(R.id.scrollView_find_friends)
    RecyclerScrollView mReScrollViewFindFriends;
    @BindView(R.id.iv_back)
    ImageButton mIbtnBack;
    //标题
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    private FindFriendsPictureAdapter mFindFriendsPictureAdapter;
    public static final String cat = "https://raw.githubusercontent.com/sfsheng0322/GlideImageView/master/resources/cat.jpg";
    public static final String cat_thumbnail = "https://raw.githubusercontent.com/sfsheng0322/GlideImageView/master/resources/cat_thumbnail.jpg";
    private List<FindFriendsPictureBean> recommendFriendsPictureList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_find_friends;
    }

    @Override
    public void initView(Bundle savedInstanceState) {


        mTvTitle.setText(getResources().getString(R.string.str_find_friends));
        ViewGroup.LayoutParams layoutParams = mToolbarSpace.getLayoutParams();//取控件当前的布局参数
        layoutParams.height = getStatusBarHeight();// 控件的高强制设成状态栏高度
        mToolbarSpace.setLayoutParams(layoutParams); //使设置好的布局参数应用到控件</pre>
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbarSpace.setVisibility(View.VISIBLE);
        } else {
            mToolbarSpace.setVisibility(View.GONE);
        }
        if (recommendFriendsPictureList == null) {

            recommendFriendsPictureList = new ArrayList<>();
        } else {
            recommendFriendsPictureList.clear();
        }
    }

    @Override
    public void initData() {

     /*   glideImageView.setOnClickListener(v -> {
            Intent intent = new Intent(FindFriendsActivity.this, ImagePreviewLoadingActivity.class);
            intent.putExtra(ImagePreviewLoadingActivity.KEY_IMAGE_URL, cat);
            intent.putExtra(ImagePreviewLoadingActivity.KEY_IMAGE_URL_THUMBNAIL, cat_thumbnail);
            ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(FindFriendsActivity.this, glideImageView, getString(R.string.app_name));
            ActivityCompat.startActivity(FindFriendsActivity.this, intent, compat.toBundle());
        });*/
        FindFriendsPictureBean friendsPictureBean = new FindFriendsPictureBean();
        List<ImageData> imageDataList = new ArrayList<>();
        ImageData imageData = new ImageData();
        imageData.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1529402445474&di=b5da3b2f6a466e618e1e32d4dd2bda4d&imgtype=0&src=http%3A%2F%2F2b.zol-img.com.cn%2Fproduct%2F133_500x2000%2F801%2Fce21ke76FRh4A.jpg");
        ImageData imageData1 = new ImageData();
        imageData1.setUrl("https://raw.githubusercontent.com/sfsheng0322/GlideImageView/master/resources/girl_thumbnail.jpg");

        imageDataList.add(imageData);
        imageDataList.add(imageData1);
        friendsPictureBean.setName("小明");
        friendsPictureBean.setAvatar("https://raw.githubusercontent.com/sfsheng0322/GlideImageView/master/resources/cat.jpg");
        friendsPictureBean.setImages(imageDataList);

        FindFriendsPictureBean friendsPictureBean1 = new FindFriendsPictureBean();
        List<ImageData> imageDataList1 = new ArrayList<>();
        ImageData imageData4 = new ImageData();
        imageData4.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1529402445474&di=b5da3b2f6a466e618e1e32d4dd2bda4d&imgtype=0&src=http%3A%2F%2F2b.zol-img.com.cn%2Fproduct%2F133_500x2000%2F801%2Fce21ke76FRh4A.jpg");
        ImageData imageData5 = new ImageData();
        imageData5.setUrl("https://raw.githubusercontent.com/sfsheng0322/GlideImageView/master/resources/girl_thumbnail.jpg");
        ImageData imageData6 = new ImageData();
        imageData6.setUrl("http://img3.imgtn.bdimg.com/it/u=2584422743,1020351689&fm=214&gp=0.jpg");
        ImageData imageData7 = new ImageData();
        imageData7.setUrl("http://img2.imgtn.bdimg.com/it/u=825595977,2574576731&fm=26&gp=0.jpg");
        ImageData imageData8 = new ImageData();
        imageData8.setUrl("http://img2.imgtn.bdimg.com/it/u=825595977,2574576731&fm=26&gp=0.jpg");
        ImageData imageData9 = new ImageData();
        imageData9.setUrl("http://img3.imgtn.bdimg.com/it/u=2584422743,1020351689&fm=214&gp=0.jpg");

        imageDataList1.add(imageData4);
        imageDataList1.add(imageData5);
        imageDataList1.add(imageData6);
        imageDataList1.add(imageData7);
        imageDataList1.add(imageData8);
        imageDataList1.add(imageData9);
        friendsPictureBean1.setName("小张");
        friendsPictureBean1.setAvatar("https://raw.githubusercontent.com/sfsheng0322/GlideImageView/master/resources/cat.jpg");
        friendsPictureBean1.setImages(imageDataList1);

        FindFriendsPictureBean friendsPictureBean2 = new FindFriendsPictureBean();
        List<ImageData> imageDataList2 = new ArrayList<>();
        ImageData imageData18 = new ImageData();
        imageData18.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1529402445474&di=b5da3b2f6a466e618e1e32d4dd2bda4d&imgtype=0&src=http%3A%2F%2F2b.zol-img.com.cn%2Fproduct%2F133_500x2000%2F801%2Fce21ke76FRh4A.jpg");
        ImageData imageData19 = new ImageData();
        imageData19.setUrl("https://raw.githubusercontent.com/sfsheng0322/GlideImageView/master/resources/girl_thumbnail.jpg");
        ImageData imageData10 = new ImageData();
        imageData10.setUrl("http://img3.imgtn.bdimg.com/it/u=2584422743,1020351689&fm=214&gp=0.jpg");
        ImageData imageData11 = new ImageData();
        imageData11.setUrl("http://img2.imgtn.bdimg.com/it/u=825595977,2574576731&fm=26&gp=0.jpg");

        imageDataList2.add(imageData18);
        imageDataList2.add(imageData19);
        imageDataList2.add(imageData10);
        imageDataList2.add(imageData11);
        friendsPictureBean2.setName("小陈");
        friendsPictureBean2.setAvatar("https://raw.githubusercontent.com/sfsheng0322/GlideImageView/master/resources/cat.jpg");
        friendsPictureBean2.setImages(imageDataList2);
        recommendFriendsPictureList.add(friendsPictureBean);
        recommendFriendsPictureList.add(friendsPictureBean1);
        recommendFriendsPictureList.add(friendsPictureBean2);


        // 创建一个线性布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRvRecommendFindFriends.setNestedScrollingEnabled(false);
        mRvRecommendFindFriends.setLayoutManager(linearLayoutManager);

        mFindFriendsPictureAdapter = new FindFriendsPictureAdapter(FindFriendsActivity.this);
        mFindFriendsPictureAdapter.setRecommendFriendsPictureList(recommendFriendsPictureList);
        mRvRecommendFindFriends.setAdapter(mFindFriendsPictureAdapter);

        mIbtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FindFriendsActivity.this.finish();
            }
        });
       /* mReScrollViewFindFriends.setScrolListener(new RecyclerScrollView.OnScrollListener() {
            @Override
            public void onScroll(int scrollY) {
                if (scrollY == 0) {
                    mRlytAddressBookFriends.setBackgroundColor(Color.argb(0, 0, 0, 0));
                } else {
                        int alpha = (int) (255 - scrollY / (float) mReScrollViewFindFriends.getScrollBarSize() * 255);
                        mRlytAddressBookFriends.setBackgroundColor(Color.argb(alpha, 255, 255, 255));
                  *//*  llSearch.setVisibility(View.VISIBLE);
                    ivSearchBtn.setVisibility(View.VISIBLE);*//*
                }
            }
        });*/

      /*  appBarlayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    mRlytAddressBookFriends.setBackgroundColor(Color.argb(0, 0, 0, 0));
                  *//*  ivLeftHotBtn.setBackground(getResources().getDrawable(R.drawable.shap_left_btn_bg));
                    ivSearchBtn.setVisibility(View.VISIBLE);
                    ivSearchBtn.setBackground(getResources().getDrawable(R.drawable.shap_left_btn_bg));
                    llSearch.setVisibility(View.VISIBLE);*//*
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    mRlytAddressBookFriends.setBackgroundColor(Color.argb(255, 255, 255, 255));
                   *//* ivLeftHotBtn.setBackground(null);
                    llSearch.setVisibility(View.VISIBLE);
                    ivSearchBtn.setBackground(null);*//*
                } else {
                    int alpha = (int) (255 - verticalOffset / (float) appBarLayout.getTotalScrollRange() * 255);
                    mRlytAddressBookFriends.setBackgroundColor(Color.argb(alpha, 255, 255, 255));
                  *//*  llSearch.setVisibility(View.VISIBLE);
                    ivSearchBtn.setVisibility(View.VISIBLE);*//*
                }
            }
        });*/

    }

    @Override
    public void initPresenter() {

    }
}
