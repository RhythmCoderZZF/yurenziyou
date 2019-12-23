package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.os.IBinder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.library.banner.BannerLayout;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Enum.UploadFileTypeEnum;
import com.nbhysj.coupon.model.response.BannerUrlBO;
import com.nbhysj.coupon.model.response.FollowDetailBean;
import com.nbhysj.coupon.model.response.HomePageSubTopicTagBean;
import com.nbhysj.coupon.model.response.PostCommentBean;
import com.nbhysj.coupon.model.response.RecommendInterestUsersBean;
import com.nbhysj.coupon.model.response.TopicsBean;
import com.nbhysj.coupon.model.response.ZanAvatersBean;
import com.nbhysj.coupon.util.DateUtil;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.nbhysj.coupon.view.BannerSlideShowView;
import com.nbhysj.coupon.view.ExpandableTextView;
import com.nbhysj.coupon.widget.customjzvd.MyJzvdStd;
import com.nbhysj.coupon.widget.wavelineview.ExpandButtonLayout;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * created by hysj on 2019/02/20.
 * description: 关注列表适配器
 */

public class FollowListAdapter extends RecyclerView.Adapter<FollowListAdapter.ViewHolder> {

    /**
     * 空数据时，显示空布局类型
     */
    private final int EMPTY_VIEW = 1;

    /**
     * 控制空布局的显隐
     */
    private int mEmptyType = 0;

    private Context mContext;
    private List<HomePageSubTopicTagBean> followDetailList;
    LayoutInflater mInflater;
    private HPFollowPostCommentAdapter hpFollowPostCommentAdapter;
    private FollowListener followListener;

    private boolean isFollowRefresh = true;

    public FollowListAdapter(Context mContext, FollowListener followListener,boolean isFollowRefresh) {
        this.mContext = mContext;
        this.followListener = followListener;
        this.isFollowRefresh = isFollowRefresh;

    }

    public void setFollowDetailList(List<HomePageSubTopicTagBean> followDetailList) {

        this.followDetailList = followDetailList;

    }

    @Override
    public FollowListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_follow_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FollowListAdapter.ViewHolder holder, int position) {

        try {

            HomePageSubTopicTagBean followDetailBean = followDetailList.get(position);
            int postId = followDetailBean.getId();

            int commentCount = followDetailBean.getCommentCount();//评论数
            int collectionCount = followDetailBean.getCollectionCount();//收藏数
            int zanCount = followDetailBean.getZanCount();//点赞数
            String photoUrl = followDetailBean.getPhoto();          //拍视频生成 gif
            holder.mTvUserName.setText(followDetailBean.getNickname());
            String postPublishDateStr = DateUtil.transferLongToDate(DateUtil.sDateYMDHHMMSSFormat, followDetailBean.getCtime());
            Date date = DateUtil.getDateStrToDate(postPublishDateStr, DateUtil.sDateYMDHHMMSSFormat);
            String postPublishDate = DateUtil.dateFormat(date);
            holder.mTvTime.setText(postPublishDate);
            String avaterUrl = followDetailBean.getAvater();
            List<PostCommentBean> postsCommentsList = followDetailBean.getPostsComments();

                GlideUtil.loadImage(mContext, avaterUrl, holder.mImgRecommendUserAvatar);

            List<String> bannerUrlList = followDetailBean.getResources();
            String resourceUrl = followDetailBean.getResourceUrl();  //mp4
            int postsType = followDetailBean.getPostsType();  //1图片，2语音，3视频

            if (bannerUrlList != null)
            {
                holder.mSlideViewFriendDetailPictrue.initUI(bannerUrlList);

                isFollowRefresh = false;
            }

            if (postsType == 1) { //图片

                holder.mRlytPostDetailPicture.setVisibility(View.VISIBLE);
                holder.mRlytPostVideo.setVisibility(View.GONE);
                holder.mBtnLayoutExpandSound.setVisibility(View.INVISIBLE);
                holder.mSlideViewFriendDetailPictrue.setBannerList(bannerUrlList);

            } else if (postsType == 2) {  //音频+图片

                holder.mRlytPostDetailPicture.setVisibility(View.VISIBLE);
                holder.mRlytPostVideo.setVisibility(View.GONE);

                if (!TextUtils.isEmpty(resourceUrl)) {
                    String audioValue = UploadFileTypeEnum.AUDIO.getValue();
                    if (resourceUrl.contains(audioValue)) {
                        holder.mBtnLayoutExpandSound.setVisibility(View.VISIBLE);
                        holder.mBtnLayoutExpandSound.setAudioFileUrl(resourceUrl);
                    }
                }
                holder.mSlideViewFriendDetailPictrue.setBannerList(bannerUrlList);

            } else if (postsType == 3) {  //视频

                holder.mRlytPostVideo.setVisibility(View.VISIBLE);
                holder.mRlytPostDetailPicture.setVisibility(View.GONE);
                holder.mJzvdPostVideo.setUp(resourceUrl, "");
                holder.mBtnLayoutExpandSound.setVisibility(View.INVISIBLE);
                GlideUtil.loadImage(mContext, photoUrl, holder.mJzvdPostVideo.thumbImageView);
            }

            List<TopicsBean> topicsList = followDetailBean.getTopics();
            holder.mTagFlowLayout.setAdapter(new TagAdapter<TopicsBean>(topicsList) {

                @Override
                public View getView(FlowLayout parent, int position, TopicsBean topicsEntity) {
                    mInflater = LayoutInflater.from(mContext);
                    TextView tagName = (TextView) mInflater.inflate(R.layout.layout_flowlayout_tv,
                            holder.mTagFlowLayout, false);
                    tagName.getBackground().setAlpha(80);
                    tagName.setText(topicsEntity.getTitle());
                    return tagName;
                }
            });
            holder.mRlytPraisePeopleNum.getBackground().setAlpha(75);
            holder.mTvExpandPostDescription.setText(followDetailBean.getContent());

            //浏览量
            int browseNum = followDetailBean.getHits();
            holder.mTvBrowseNum.setText("浏览" + String.valueOf(browseNum));

            List<ZanAvatersBean> zanAvatersList = followDetailBean.getZanAvaters();
            if (zanAvatersList != null) {

                LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
                layoutManager.setOrientation(layoutManager.HORIZONTAL);
                holder.mRvPraisePeopleNum.setLayoutManager(layoutManager);
                PraisePeopleAdapter praisePeopleAdapter = new PraisePeopleAdapter(mContext);
                praisePeopleAdapter.setPraisePeopleList(zanAvatersList);
                holder.mRvPraisePeopleNum.setAdapter(praisePeopleAdapter);
            }

            //点赞用户数量
            holder.mTvPraisePeopleNum.setText(String.valueOf(zanCount));

            int zanStatus = followDetailBean.getZanStatus(); //是否点赞过
            if (zanStatus == 0) {

                holder.mTvPostPraise.setCompoundDrawablesWithIntrinsicBounds(mContext.getResources().getDrawable(R.mipmap.icon_love_gray), null, null, null);
                holder.mTvPostPraise.setTextColor(mContext.getResources().getColor(R.color.text_grey));
                holder.mRlytPraise.setBackgroundResource(R.drawable.bg_rect_light_gray_shape_radius_five);
            } else {

                holder.mTvPostPraise.setCompoundDrawablesWithIntrinsicBounds(mContext.getResources().getDrawable(R.mipmap.icon_note_heart_white), null, null, null);
                holder.mTvPostPraise.setTextColor(mContext.getResources().getColor(R.color.white));
                holder.mRlytPraise.setBackgroundResource(R.drawable.bg_praise_rect_dark_red_shape);
            }

            holder.mTvPostCommentNum.setText(String.valueOf(commentCount));
            holder.mTvPostCollectionNum.setText(String.valueOf(collectionCount));

            if (postsCommentsList != null) {
                holder.mLlytUserComment.setVisibility(View.VISIBLE);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
                holder.mRvUserComment.setLayoutManager(linearLayoutManager);
                hpFollowPostCommentAdapter = new HPFollowPostCommentAdapter(mContext);
                hpFollowPostCommentAdapter.setLabelList(postsCommentsList);
                holder.mRvUserComment.setAdapter(hpFollowPostCommentAdapter);
                holder.mTvTotalCommentNum.setText("查看" + postsCommentsList.size() + "条评论");
            } else {

                holder.mLlytUserComment.setVisibility(View.GONE);
            }

            //用户头像
            String commentatorAvater = (String)SharedPreferencesUtils.getData(SharedPreferencesUtils.USER_AVATAR,"");

            if(!TextUtils.isEmpty(commentatorAvater))
            {
                GlideUtil.loadImage(mContext, commentatorAvater, holder.mCircleImageViewCommentUserAvatar);
            }
            //帖子时间
            String postPublishTime = DateUtil.transferLongToDate(DateUtil.sDateYMDFormat, followDetailBean.getCtime());
            holder.mTvPostTime.setText(postPublishTime);

            List<RecommendInterestUsersBean> recommendUsersList = followDetailBean.getRecommendUsers();

            if (recommendUsersList != null && recommendUsersList.size() > 0) {
                holder.mLlytInterestUser.setVisibility(View.VISIBLE);
                //可能感兴趣列表
                LinearLayoutManager userOfInterestLinearLayoutManager = new LinearLayoutManager(mContext);
                userOfInterestLinearLayoutManager.setOrientation(userOfInterestLinearLayoutManager.HORIZONTAL);
                holder.mRvUserOfInterest.setLayoutManager(userOfInterestLinearLayoutManager);
                FollowUsersOfInterestAdapter followUsersOfInterestAdapter = new FollowUsersOfInterestAdapter(mContext, new FollowUsersOfInterestAdapter.UsersOfInterestItemClickListener() {
                    @Override
                    public void setUsersOfInterestItemClickListener(RecommendInterestUsersBean userEntity) {

                        followListener.setUserOfInterestListener(userEntity);

                    }
                });
                followUsersOfInterestAdapter.setFollowUsersOfInterestList(recommendUsersList);
                holder.mRvUserOfInterest.setAdapter(followUsersOfInterestAdapter);

            } else {

                holder.mLlytInterestUser.setVisibility(View.GONE);
            }

            holder.mTvInterestUserLookMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    followListener.setUserOfInterestLookMoreListener();
                }
            });

            holder.mTvPostCommentNum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    followListener.setLookCommentListener(postId);

                }
            });

            holder.mLlytPostCollection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    followListener.setCollectionPostToAlbumsListener(followDetailBean);

                }
            });

            holder.mLlytFollowItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    followListener.setFollowItemOnClickListener(postId);
                }
            });

            holder.mImgShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    followListener.setFollowShareListener(postId);
                }
            });


            holder.mRlytPraise.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    followListener.setPostPraiseListener(position, postId);
                }
            });

            holder.mTvAddComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    followListener.setLookCommentListener(postId);
                }
            });

            holder.mLlytUserComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    followListener.setLookCommentListener(postId);
                }
            });
           /* if (interestUserList == null || interestUserList.size() == 0) {

                holder.mRlytInterestUser.setVisibility(View.GONE);

            } else {

                holder.mRlytInterestUser.setVisibility(View.VISIBLE);
                webBannerAdapter = new WebBannerAdapter(mContext, new BannerLayout.OnBannerItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                    *//*if (galleryList != null) {
                        AdvertisementBean.ItemsBeanX.ItemsBean itemsBean = galleryList.get(position);
                        String link = (String) itemsBean.getLink();
                        skipPage(link);
                    }*//*
                    }
                });
                webBannerAdapter.setWebBannerList(interestUserList);
                holder.mBannerUserOfInterest.setAdapter(webBannerAdapter);
            }*/


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        //注意，空布局---> mEmptyType = 1 显示1个布局
        //            ---> mEmptyType = 0 所有布局都不显示的
        //   return dataList != null ? dataList.size() + mEmptyType : mEmptyType;
        return followDetailList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mEmptyType == EMPTY_VIEW) {
            //空布局的类型
            return EMPTY_VIEW;
        }
        return super.getItemViewType(position);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_recommend_user_avatar)
        CircleImageView mImgRecommendUserAvatar;
        @BindView(R.id.tv_user_name)
        TextView mTvUserName;
        @BindView(R.id.tv_time)
        TextView mTvTime;
        @BindView(R.id.view_friend_detail_picture)
        BannerSlideShowView mSlideViewFriendDetailPictrue;
        @BindView(R.id.flowlayout_tag)
        TagFlowLayout mTagFlowLayout;
        //帖子详情照片
        @BindView(R.id.rlyt_post_detail_picture)
        RelativeLayout mRlytPostDetailPicture;
        @BindView(R.id.llyt_interest_user)
        LinearLayout mLlytInterestUser;
        //点赞人数
        @BindView(R.id.rlyt_praise_people_num)
        RelativeLayout mRlytPraisePeopleNum;
        //点赞用户
        @BindView(R.id.rv_praise_people_num)
        RecyclerView mRvPraisePeopleNum;
        //点赞用户数量
        @BindView(R.id.tv_praise_people_num)
        TextView mTvPraisePeopleNum;
        //评论列表
        @BindView(R.id.rv_user_comment)
        RecyclerView mRvUserComment;
        //帖子内容
        @BindView(R.id.tv_expand_post_description)
        ExpandableTextView mTvExpandPostDescription;
        //总共评论数
        @BindView(R.id.tv_total_comment_num)
        TextView mTvTotalCommentNum;
        //评论用户头像
        @BindView(R.id.image_comment_user_avatar)
        CircleImageView mCircleImageViewCommentUserAvatar;
        //感兴趣用户
        @BindView(R.id.rv_users_of_interest)
        RecyclerView mRvUserOfInterest;
        //查看更多兴趣用户
        @BindView(R.id.tv_interest_user_look_more)
        TextView mTvInterestUserLookMore;
        //评论数
        @BindView(R.id.tv_post_comment_num)
        TextView mTvPostCommentNum;
        //帖子收藏数
        @BindView(R.id.tv_post_collection_num)
        TextView mTvPostCollectionNum;
        //用户评论
        @BindView(R.id.llyt_user_comment)
        LinearLayout mLlytUserComment;
        //是否点过赞 0:否 1:是
        @BindView(R.id.tv_post_praise)
        TextView mTvPostPraise;
        //帖子收藏
        @BindView(R.id.llyt_post_collection)
        LinearLayout mLlytPostCollection;
        //帖子时间
        @BindView(R.id.tv_post_time)
        TextView mTvPostTime;
        //浏览量
        @BindView(R.id.tv_browse_num)
        TextView mTvBrowseNum;
        @BindView(R.id.rlyt_praise)
        RelativeLayout mRlytPraise;
        @BindView(R.id.llyt_follow_item)
        LinearLayout mLlytFollowItem;
        @BindView(R.id.img_share)
        ImageView mImgShare;
        @BindView(R.id.jzvd_post_video)
        MyJzvdStd mJzvdPostVideo;
        @BindView(R.id.img_post_video_gif)
        ImageView mImgPostVideGif;
        @BindView(R.id.layout_expanded_button)
        ExpandButtonLayout mBtnLayoutExpandSound;
        //添加评论
        @BindView(R.id.tv_add_comment)
        TextView mTvAddComment;
        @BindView(R.id.rlyt_post_video)
        RelativeLayout mRlytPostVideo;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface FollowListener {

        //帖子点赞
        void setPostPraiseListener(int position, int postId);

        //可能感兴趣的用户 点击关注
        void setUserOfInterestListener(RecommendInterestUsersBean userEntity);

        //查看更多感兴趣用户
        void setUserOfInterestLookMoreListener();

        //点击查看评论列表
        void setLookCommentListener(int mPostId);

        //点击收藏帖子到专辑
        void setCollectionPostToAlbumsListener(HomePageSubTopicTagBean followDetailBean);

        //item点击查看帖子详情
        void setFollowItemOnClickListener(int postId);

        //分享
        void setFollowShareListener(int mPostId);
    }
}
