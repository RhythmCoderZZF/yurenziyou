package com.nbhysj.coupon.ui;


import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.AlbumOprateAdapter;
import com.nbhysj.coupon.model.response.HomePageSubTopicTagBean;
import com.nbhysj.coupon.util.GlideUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class AlbumDetailsActivity extends BaseActivity {
    @BindView(R.id.toolbar_space)
    View mToolbarSpace;
    @BindView(R.id.rv_album)
    RecyclerView mRvAlbumDetail;
    @BindView(R.id.image_avatar)
    CircleImageView mImgAvatar;
    //保存
    @BindView(R.id.tv_save)
    TextView mTvSave;
    //专辑编辑
    @BindView(R.id.img_album_edit)
    ImageView mImgAlbumEdit;
    //专辑分享
    @BindView(R.id.img_album_share)
    ImageView mImgAlbumShare;
    //编辑专辑标签
    @BindView(R.id.tv_album_edit_tag)
    TextView mTvAlbumEditTag;
    @BindView(R.id.edt_album_name)
    EditText mEdtAlbumName;
    @BindView(R.id.tv_album_num)
    TextView mTvAlbumNum;
    @BindView(R.id.tv_album_title)
    TextView mTvAlbumTitle;
    private AlbumOprateAdapter albumOprateAdapter;
    private List<HomePageSubTopicTagBean> recommendFriendsList;
    private String albumName;

    @Override
    public int getLayoutId() {
        return R.layout.activity_album_details;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ViewGroup.LayoutParams layoutParams = mToolbarSpace.getLayoutParams();//取控件当前的布局参数
        layoutParams.height = getStatusBarHeight();// 控件的高强制设成状态栏高度
        mToolbarSpace.setLayoutParams(layoutParams); //使设置好的布局参数应用到控件</pre>
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbarSpace.setVisibility(View.VISIBLE);
        } else {
            mToolbarSpace.setVisibility(View.GONE);
        }

        if (recommendFriendsList == null) {

            recommendFriendsList = new ArrayList<>();
        } else {
            recommendFriendsList.clear();
        }

        GlideUtil.loadImage(AlbumDetailsActivity.this, "http://pic15.nipic.com/20110628/1369025_192645024000_2.jpg", mImgAvatar);

        HomePageSubTopicTagBean homePageSubTopicTagBean = new HomePageSubTopicTagBean();
        homePageSubTopicTagBean.setAvater("http://pic15.nipic.com/20110628/1369025_192645024000_2.jpg");
        homePageSubTopicTagBean.setPhoto("http://pic37.nipic.com/20140113/8800276_184927469000_2.png");
        homePageSubTopicTagBean.setContent("您男男女女难男男女女");
        homePageSubTopicTagBean.setNickname("宁波");
        homePageSubTopicTagBean.setPhotoWidth(960);
        homePageSubTopicTagBean.setPhotoHeight(1080);


        HomePageSubTopicTagBean homePageSubTopicTagBean2 = new HomePageSubTopicTagBean();
        homePageSubTopicTagBean2.setAvater("http://pic15.nipic.com/20110628/1369025_192645024000_2.jpg");
        homePageSubTopicTagBean2.setPhoto("http://pic15.nipic.com/20110628/1369025_192645024000_2.jpg");
        homePageSubTopicTagBean2.setContent("您男男女女难男男女女");
        homePageSubTopicTagBean2.setNickname("宁波");
        homePageSubTopicTagBean2.setPhotoWidth(960);
        homePageSubTopicTagBean2.setPhotoHeight(1080);


        HomePageSubTopicTagBean homePageSubTopicTagBean3 = new HomePageSubTopicTagBean();
        homePageSubTopicTagBean3.setAvater("http://pic15.nipic.com/20110628/1369025_192645024000_2.jpg");
        homePageSubTopicTagBean3.setPhoto("http://pic15.nipic.com/20110628/1369025_192645024000_2.jpg");
        homePageSubTopicTagBean3.setContent("您男男女女难男男女女");
        homePageSubTopicTagBean3.setNickname("宁波");
        homePageSubTopicTagBean3.setPhotoWidth(960);
        homePageSubTopicTagBean3.setPhotoHeight(1080);


        HomePageSubTopicTagBean homePageSubTopicTagBean4 = new HomePageSubTopicTagBean();
        homePageSubTopicTagBean4.setAvater("http://pic15.nipic.com/20110628/1369025_192645024000_2.jpg");
        homePageSubTopicTagBean4.setPhoto("http://pic15.nipic.com/20110628/1369025_192645024000_2.jpg");
        homePageSubTopicTagBean4.setContent("您男男女女难男男女女");
        homePageSubTopicTagBean4.setNickname("宁波");
        homePageSubTopicTagBean4.setPhotoWidth(960);
        homePageSubTopicTagBean4.setPhotoHeight(1080);


        HomePageSubTopicTagBean homePageSubTopicTagBean5 = new HomePageSubTopicTagBean();
        homePageSubTopicTagBean5.setAvater("http://pic15.nipic.com/20110628/1369025_192645024000_2.jpg");
        homePageSubTopicTagBean5.setPhoto("http://pic37.nipic.com/20140113/8800276_184927469000_2.png");
        homePageSubTopicTagBean5.setContent("您男男女女难男男女女");
        homePageSubTopicTagBean5.setNickname("宁波");
        homePageSubTopicTagBean5.setPhotoWidth(960);
        homePageSubTopicTagBean5.setPhotoHeight(1080);

        HomePageSubTopicTagBean homePageSubTopicTagBean6 = new HomePageSubTopicTagBean();
        homePageSubTopicTagBean6.setAvater("http://pic15.nipic.com/20110628/1369025_192645024000_2.jpg");
        homePageSubTopicTagBean6.setPhoto("http://pic15.nipic.com/20110628/1369025_192645024000_2.jpg");
        homePageSubTopicTagBean6.setContent("您男男女女难男男女女");
        homePageSubTopicTagBean6.setNickname("宁波");
        homePageSubTopicTagBean6.setPhotoWidth(960);
        homePageSubTopicTagBean6.setPhotoHeight(1080);

        recommendFriendsList.add(homePageSubTopicTagBean);
        recommendFriendsList.add(homePageSubTopicTagBean2);
        recommendFriendsList.add(homePageSubTopicTagBean3);
        recommendFriendsList.add(homePageSubTopicTagBean4);
        recommendFriendsList.add(homePageSubTopicTagBean5);
        recommendFriendsList.add(homePageSubTopicTagBean6);

        int albumNum = recommendFriendsList.size();
        mTvAlbumNum.setText(String.valueOf(albumNum));

        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(2,
                        StaggeredGridLayoutManager.VERTICAL);
        mRvAlbumDetail.setLayoutManager(staggeredGridLayoutManager);
        mRvAlbumDetail.setHasFixedSize(true);
        mRvAlbumDetail.setItemViewCacheSize(10);
        mRvAlbumDetail.setDrawingCacheEnabled(true);
        mRvAlbumDetail.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        mRvAlbumDetail.addItemDecoration(new RecyclerItemDecoration(6, 2));
        albumOprateAdapter = new AlbumOprateAdapter(AlbumDetailsActivity.this);
        albumOprateAdapter.setHasStableIds(true);
        albumOprateAdapter.setAlbumOprateList(recommendFriendsList);
        mRvAlbumDetail.setAdapter(albumOprateAdapter);


    }

    @Override
    public void initData() {

        mEdtAlbumName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                albumName = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void initPresenter() {

    }


    public class RecyclerItemDecoration extends RecyclerView.ItemDecoration {
        private int itemSpace;
        private int itemNum;

        /**
         * @param itemSpace item间隔
         * @param itemNum   每行item的个数
         */
        public RecyclerItemDecoration(int itemSpace, int itemNum) {
            this.itemSpace = itemSpace;
            this.itemNum = itemNum;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.bottom = itemSpace;
                /*if (parent.getChildLayoutPosition(view)%itemNum == 0){  //parent.getChildLayoutPosition(view) 获取view的下标
                    outRect.left = 0;
                } else {
                    outRect.left = itemSpace;
                }*/
            outRect.left = itemSpace;
            outRect.right = itemSpace;
            //outRect.right = itemSpace;
        }
    }

    @OnClick({R.id.img_album_edit, R.id.tv_save})
    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.img_album_edit:

                mImgAlbumEdit.setVisibility(View.GONE);
                mImgAlbumShare.setVisibility(View.GONE);
                mTvSave.setVisibility(View.VISIBLE);
                mTvAlbumEditTag.setVisibility(View.VISIBLE);
                albumOprateAdapter.setAlbumOprate(0);
                albumOprateAdapter.notifyDataSetChanged();

                break;
            case R.id.tv_save:

                if (!TextUtils.isEmpty(albumName)) {

                    mTvSave.setVisibility(View.GONE);
                    mImgAlbumEdit.setVisibility(View.VISIBLE);
                    mImgAlbumShare.setVisibility(View.VISIBLE);
                    mTvAlbumEditTag.setVisibility(View.GONE);
                    albumOprateAdapter.setAlbumOprate(1);
                    List<HomePageSubTopicTagBean> selectAlbumEditList = albumOprateAdapter.getSelectAlbumEditList();
                    //showToast(AlbumDetailsActivity.this, selectAlbumEditList.toString());
                    for (int i = 0; i < recommendFriendsList.size(); i++) {
                        HomePageSubTopicTagBean albumBean = recommendFriendsList.get(i);
                        boolean isAlbumSelect = albumBean.isLove();
                        if (isAlbumSelect) {
                            albumName = mEdtAlbumName.getText().toString().trim();
                            albumBean.setContent(albumName);
                        }
                        albumBean.setLove(false);
                    }
                    albumOprateAdapter.setAlbumOprateList(recommendFriendsList);
                    albumOprateAdapter.notifyDataSetChanged();
                    albumOprateAdapter.setAlbumEditListClear();
                    hiddenIME();
                } else {

                    showToast(AlbumDetailsActivity.this, "为你的专辑起一个好听的名字吧~");
                }
                break;
            default:
                break;

        }

    }
}
