package com.nbhysj.coupon.ui;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.NoteDetailCommentListAdapter;
import com.nbhysj.coupon.adapter.NotePraiseUserListAdapter;
import com.nbhysj.coupon.model.response.NoteCommentBean;
import com.nbhysj.coupon.view.GlideImageView;
import com.nbhysj.coupon.widget.glide.CacheImageLoader;
import com.nbhysj.coupon.widget.glide.GlideApp;
import com.nbhysj.coupon.widget.glide.RadiusTransformation;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/03/05
 * description：附近详情
 */
public class NearbyCardDetailActivity extends BaseActivity {

    @BindView(R.id.toolbar_space)
    View mToolbarSpace;
    @BindView(R.id.banner_home)
    Banner mBannerHome;
    //店铺头像
    @BindView(R.id.image_store)
    GlideImageView mImageStore;
    //头像
    @BindView(R.id.image_interest_user_avatar)
    GlideImageView mImageUserAvatar;
    //点赞用户的头像
    @BindView(R.id.rv_praise_people_num)
    RecyclerView mRvPraisePeopleList;
    //用户评论列表
    @BindView(R.id.rv_user_comment)
    RecyclerView mRvUserCommentList;
    private ArrayList<String> bannerList;
    private List<NoteCommentBean> noteCommentList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_nearby_card_detail;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

     /*   ViewGroup.LayoutParams layoutParams = mToolbarSpace.getLayoutParams();//取控件当前的布局参数
        layoutParams.height = getStatusBarHeight();// 控件的高强制设成状态栏高度
        mToolbarSpace.setLayoutParams(layoutParams); //使设置好的布局参数应用到控件</pre>
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbarSpace.setVisibility(View.VISIBLE);
        } else {
            mToolbarSpace.setVisibility(View.GONE);
        }*/
        if (bannerList == null) {
            bannerList = new ArrayList<>();

        } else {

            bannerList.clear();
        }

        if (noteCommentList == null) {

            noteCommentList = new ArrayList<>();
        } else {
            noteCommentList.clear();
        }

        String imageUrl = getIntent().getStringExtra("imageUrl");
        bannerList.add(imageUrl);
        mBannerHome.setImages(bannerList);
        mBannerHome.isAutoPlay(true);//禁止轮播
        mBannerHome.setDelayTime(5000);
        mBannerHome.setIndicatorGravity(BannerConfig.CENTER);
        mBannerHome.setImageLoader(new CacheImageLoader());

        mBannerHome.start();

        List<String> mNotePraiseUserList = new ArrayList<>();
        mNotePraiseUserList.add("http://wx3.sinaimg.cn/large/006nLajtly1fkegnmnwuxj30dw0dw408.jpg");
        mNotePraiseUserList.add("http://img.bimg.126.net/photo/ZZ5EGyuUCp9hBPk6_s4Ehg==/5727171351132208489.jpg");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(NearbyCardDetailActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvPraisePeopleList.setLayoutManager(linearLayoutManager);
        NotePraiseUserListAdapter notePraiseUserListAdapter = new NotePraiseUserListAdapter(NearbyCardDetailActivity.this);
        notePraiseUserListAdapter.setNewFansList(mNotePraiseUserList);
        mRvPraisePeopleList.setAdapter(notePraiseUserListAdapter);

        LinearLayoutManager noteDetailCommentListLayoutManager = new LinearLayoutManager(NearbyCardDetailActivity.this);
        mRvUserCommentList.setLayoutManager(noteDetailCommentListLayoutManager);

        NoteCommentBean noteCommentBean = new NoteCommentBean();
        noteCommentBean.setCommentUsername("用户123");
        noteCommentBean.setCommentContent("东西很好吃啊");
        noteCommentBean.setCommentTime("2019-03-21");
        noteCommentBean.setCommentUserAvatar("http://img.bimg.126.net/photo/ZZ5EGyuUCp9hBPk6_s4Ehg==/5727171351132208489.jpg");

        NoteCommentBean noteCommentBean1 = new NoteCommentBean();
        noteCommentBean1.setCommentUsername("用户222");
        noteCommentBean1.setCommentContent("哈哈哈");
        noteCommentBean1.setCommentTime("2019-03-22");
        noteCommentBean1.setCommentUserAvatar("http://wx3.sinaimg.cn/large/006nLajtly1fkegnmnwuxj30dw0dw408.jpg");

        noteCommentList.add(noteCommentBean);
        noteCommentList.add(noteCommentBean1);

        NoteDetailCommentListAdapter noteDetailCommentListAdapter = new NoteDetailCommentListAdapter(NearbyCardDetailActivity.this);
        noteDetailCommentListAdapter.setNoteCommentList(noteCommentList);
        mRvUserCommentList.setAdapter(noteDetailCommentListAdapter);
    }

    @Override
    public void initData() {

        mImageUserAvatar.loadCircle("http://wx3.sinaimg.cn/large/006nLajtly1fkegnmnwuxj30dw0dw408.jpg", R.mipmap.icon_placeholder_image);

      /*  GlideApp.with(mContext)
                .load("")
                .placeholder(R.mipmap.icon_placeholder_image)
                .error(R.mipmap.icon_placeholder_image)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .override(Target.SIZE_ORIGINAL)
                .transform(new RadiusTransformation(mContext, 25))
                .into(mImageStore);*/

        mImageStore.load("http://img.bimg.126.net/photo/ZZ5EGyuUCp9hBPk6_s4Ehg==/5727171351132208489.jpg", R.mipmap.icon_placeholder_image, 15);
    }

    @Override
    public void initPresenter() {

    }

    @OnClick({R.id.img_note_detail_cancel})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_note_detail_cancel:
                finish();
                break;
            default:
                break;
        }
    }
}
