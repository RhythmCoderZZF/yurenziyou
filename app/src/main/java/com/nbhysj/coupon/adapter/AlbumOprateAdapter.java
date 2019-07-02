package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
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
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.HomePageSubTopicTagBean;
import com.nbhysj.coupon.ui.AlbumDetailsActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.ImageLoaderUtil;
import com.nbhysj.coupon.view.GlideImageView;
import com.nbhysj.coupon.widget.glide.GlideApp;
import com.nbhysj.coupon.widget.glide.GlideRoundCornersTransUtils;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author hysj created at 2019/05/23.
 * description : 专辑适配器
 */
public class AlbumOprateAdapter extends RecyclerView.Adapter<AlbumOprateAdapter.ViewHolder> {

    /**
     * 文件类型列表
     */
    List<HomePageSubTopicTagBean> mRecommendFriendsList;
    private Context mContext;
    private List<HomePageSubTopicTagBean> mSelectAlbumEditList;
    private int oprate = 1;

    public AlbumOprateAdapter(Context mContext) {

        this.mContext = mContext;

        if (mSelectAlbumEditList == null) {

            mSelectAlbumEditList = new ArrayList<>();
        } else {
            mSelectAlbumEditList.clear();
        }
    }

    public void setAlbumOprateList(List<HomePageSubTopicTagBean> recommendFriendsPictureList) {

        this.mRecommendFriendsList = recommendFriendsPictureList;
    }

    //0:编辑  1:保存
    public void setAlbumOprate(int oprate) {

        this.oprate = oprate;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_album_item, parent, false);//解决宽度不能铺满
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
/*
           GlideApp.with(mContext)
                    .load(imageUrl)
            *//* .placeholder(R.mipmap.icon_placeholder_image)
                    .error(R.mipmap.icon_placeholder_image)*//*
                   .skipMemoryCache(false)
                   .thumbnail(0.5f) //加载缩略图
                    .dontAnimate()
                   .diskCacheStrategy(DiskCacheStrategy.ALL)
                   .override(photoWidth, photoHeight) //对图片进行压缩
                    .transform(new GlideRoundCornersTransUtils(mContext, 15, GlideRoundCornersTransUtils.CornerType.TOP))
                    .into(holder.mImgRecommendFriends);*/

            GlideUtil.loadImageWithProportion(mContext, imageUrl, photoWidth, photoHeight, holder.mImgRecommendFriends);

          /*  ImageLoaderUtil.getImageLoader(mContext)
                    .displayImage(imageUrl, holder.mImgRecommendFriends, ImageLoaderUtil.getPhotoImageOption());*/
           /*    scenicSpotOptions = new RequestOptions()
                 .transform(new GlideRoundTransform(mContext, 20));

            Glide.with(mContext)
                    .load("http://k.zol-img.com.cn/sjbbs/7692/a7691515_s.jpg")
                    .apply(scenicSpotOptions)
                    .into(holder.mImgScenicSpotPhoto);*/

            if (oprate == 0) {
                holder.mImgIsSelectEditTag.setVisibility(View.VISIBLE);
                if (recommendFriends.isLove()) {

                    holder.mImgIsSelectEditTag.setBackgroundResource(R.mipmap.icon_album_edit_item_select);
                } else {
                    holder.mImgIsSelectEditTag.setBackgroundResource(R.mipmap.icon_album_edit_item_unselect);
                }
            } else {

                if (oprate == 1) {

                    holder.mImgIsSelectEditTag.setBackgroundResource(R.mipmap.icon_album_edit_item_select);
                }

                holder.mImgIsSelectEditTag.setVisibility(View.GONE);
            }

            holder.mCardViewAlbum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (oprate == 0) {
                        if (recommendFriends.isLove()) {
                            recommendFriends.setLove(false);
                            mSelectAlbumEditList.remove(recommendFriends);
                            // holder.mImgIsSelectEditTag.setBackgroundResource(R.mipmap.icon_album_edit_item_unselect);
                        } else {
                            recommendFriends.setLove(true);

                            mSelectAlbumEditList.add(recommendFriends);
                            //holder.mImgIsSelectEditTag.setBackgroundResource(R.mipmap.icon_album_edit_item_select);
                        }
                    }

                    notifyDataSetChanged();
                }
            });
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


    public List<HomePageSubTopicTagBean> getSelectAlbumEditList() {

        return mSelectAlbumEditList;

    }

    public void setAlbumEditListClear() {

        if (mSelectAlbumEditList != null) {

            mSelectAlbumEditList.clear();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //头像
        public CircleImageView mImageAvatar;

        //推荐好友名字
        public TextView mTvName;

        //浏览量
        public TextView mTvLookNum;

        public ImageView mImgRecommendFriends, mImgIsSelectEditTag;

        CardView mCardViewAlbum;

        TextView mTvDes;

        public ViewHolder(View itemView) {
            super(itemView);

            mImageAvatar = itemView.findViewById(R.id.image_avatar);
            mTvName = itemView.findViewById(R.id.tv_name);
            mTvLookNum = itemView.findViewById(R.id.tv_look_num);
            mImgRecommendFriends = itemView.findViewById(R.id.image_recommend_friends);
            mTvDes = itemView.findViewById(R.id.tv_description);
            mImgIsSelectEditTag = itemView.findViewById(R.id.img_is_select_edit_tag);
            mCardViewAlbum = itemView.findViewById(R.id.card_view_album);

        }
    }
}
