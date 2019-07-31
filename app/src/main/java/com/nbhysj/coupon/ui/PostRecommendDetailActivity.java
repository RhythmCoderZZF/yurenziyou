package com.nbhysj.coupon.ui;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.nbhysj.coupon.view.BannerSlideShowView;
import com.nbhysj.coupon.view.ExpandableTextView;
import com.scwang.smartrefresh.header.material.CircleImageView;
import com.zhy.view.flowlayout.TagFlowLayout;

import butterknife.BindView;

public class PostRecommendDetailActivity extends BaseActivity {

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

    @BindView(R.id.rv_users_of_interest)
    RecyclerView mRvUserOfInterest;

    @Override
    public int getLayoutId() {
        return R.layout.activity_post_recommend_detail;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setBar(PostRecommendDetailActivity.this,getResources().getString(R.string.str_recommendation),R.mipmap.icon_left_arrow_black);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }
}
