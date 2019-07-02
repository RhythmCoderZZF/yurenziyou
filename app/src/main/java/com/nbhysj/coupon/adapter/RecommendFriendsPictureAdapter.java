package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.HomePageResponse;
import com.nbhysj.coupon.model.response.HomePageSubTopicTagBean;
import com.nbhysj.coupon.model.response.RecommendFriendsBean;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.Tools;
import com.nbhysj.coupon.view.GlideImageView;
import com.nbhysj.coupon.widget.glide.GlideApp;
import com.nbhysj.coupon.widget.glide.GlideRoundCornersTransUtils;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;
import com.nbhysj.coupon.widget.shadowlib.RadiusView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author hysj created at 2019/03/12.
 * description : 推荐好友图片选择适配器
 */
public class RecommendFriendsPictureAdapter extends RecyclerView.Adapter<RecommendFriendsPictureAdapter.ViewHolder> {

    /**
     * 文件类型列表
     */
    List<HomePageSubTopicTagBean> mRecommendFriendsList;
    private Context mContext;
    RequestOptions scenicSpotOptions;

    public RecommendFriendsPictureAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setRecommendFriendsPictureList(List<HomePageSubTopicTagBean> recommendFriendsPictureList) {

        this.mRecommendFriendsList = recommendFriendsPictureList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_recommend_friends_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            HomePageSubTopicTagBean recommendFriends = mRecommendFriendsList.get(itemPosition);
            String avatar = recommendFriends.getAvater();
            String name = recommendFriends.getNickname();
            String imageUrl = recommendFriends.getPhoto();
            GlideUtil.loadImage(mContext, avatar, holder.mImageAvatar);
            holder.mTvDes.setText(recommendFriends.getContent());
            holder.mImgRecommendFriends.setAdjustViewBounds(true);
            int photoWidth = recommendFriends.getPhotoWidth();
            int photoHeight = recommendFriends.getPhotoHeight();
            ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
            layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            holder.mTvName.setText(name);
            int browseNum = recommendFriends.getHits();
            holder.mTvLookNum.setText(String.valueOf(browseNum));

            GlideUtil.loadImageWithProportion(mContext, imageUrl, photoWidth, photoHeight, holder.mImgRecommendFriends);


            if (recommendFriends.isLove()) {

                holder.mImgIsLove.setBackgroundResource(R.mipmap.icon_has_love_red);
            } else {
                holder.mImgIsLove.setBackgroundResource(R.mipmap.icon_red_love_heart);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mRecommendFriendsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //头像
        public CircleImageView mImageAvatar;

        //推荐好友名字
        public TextView mTvName;

        //浏览量
        public TextView mTvLookNum;

        public ImageView mImgRecommendFriends, mImgIsLove;

        TextView mTvDes;

        public ViewHolder(View itemView) {
            super(itemView);

            mImageAvatar = itemView.findViewById(R.id.image_avatar);
            mTvName = itemView.findViewById(R.id.tv_name);
            mTvLookNum = itemView.findViewById(R.id.tv_look_num);
            mImgRecommendFriends = itemView.findViewById(R.id.image_recommend_friends);
            mTvDes = itemView.findViewById(R.id.tv_description);
            mImgIsLove = itemView.findViewById(R.id.img_is_love);
        }
    }
}
