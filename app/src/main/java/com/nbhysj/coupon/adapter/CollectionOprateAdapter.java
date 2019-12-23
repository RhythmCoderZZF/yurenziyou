package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Enum.MchTypeEnum;
import com.nbhysj.coupon.common.Enum.MineCollectionTypeEnum;
import com.nbhysj.coupon.model.response.FavoritesBean;
import com.nbhysj.coupon.ui.FoodDetailActivity;
import com.nbhysj.coupon.ui.HomestayDetailActivity;
import com.nbhysj.coupon.ui.HotelDetailsActivity;
import com.nbhysj.coupon.ui.PostRecommendDetailActivity;
import com.nbhysj.coupon.ui.ScenicSpotDetailActivity;
import com.nbhysj.coupon.util.GlideUtil;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author hysj created at 2019/05/23.
 * description : 收藏详情适配器
 */
public class CollectionOprateAdapter extends RecyclerView.Adapter<CollectionOprateAdapter.ViewHolder> {

    /**
     * 文件类型列表
     */
    List<FavoritesBean> mRecommendFriendsList;
    private Context mContext;
    private List<FavoritesBean> mSelectAlbumEditList;
    private int oprate = 1;
    private String collectionType;

    private AlbumOprateListener albumOprateListener;

    public CollectionOprateAdapter(Context mContext, String collectionType, AlbumOprateListener albumOprateListener) {

        this.mContext = mContext;
        this.albumOprateListener = albumOprateListener;
        this.collectionType = collectionType;

        if (mSelectAlbumEditList == null) {

            mSelectAlbumEditList = new ArrayList<>();
        } else {
            mSelectAlbumEditList.clear();
        }
    }

    public void setAlbumOprateList(List<FavoritesBean> recommendFriendsPictureList) {

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

            FavoritesBean recommendFriends = mRecommendFriendsList.get(itemPosition);
            int id = recommendFriends.getId();
            String avatar = recommendFriends.getAvater();
            String name = recommendFriends.getNickname();
            String imageUrl = recommendFriends.getPhoto();
            int hits = recommendFriends.getHits();
            GlideUtil.loadImage(mContext, avatar, holder.mImageAvatar);

            holder.mTvDes.setText(recommendFriends.getContent());

            holder.mTvName.setText(name);
            holder.mTvLookNum.setText(String.valueOf(hits));

            GlideUtil.loadImage(mContext, imageUrl, holder.mImgRecommendFriends);

            if (oprate == 0) {
                holder.mImgIsSelectEditTag.setVisibility(View.VISIBLE);
                if (recommendFriends.getIsAlbumSelect() == 1) {

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
                        if (recommendFriends.getIsAlbumSelect() == 1) {
                            recommendFriends.setIsAlbumSelect(0);
                            mSelectAlbumEditList.remove(recommendFriends);
                            // holder.mImgIsSelectEditTag.setBackgroundResource(R.mipmap.icon_album_edit_item_unselect);
                        } else {
                            recommendFriends.setIsAlbumSelect(1);

                            mSelectAlbumEditList.add(recommendFriends);
                            //holder.mImgIsSelectEditTag.setBackgroundResource(R.mipmap.icon_album_edit_item_select);
                        }

                        albumOprateListener.setAlbumOprateListener(mSelectAlbumEditList.size());
                        notifyDataSetChanged();
                    } else {

                        String postsType = MineCollectionTypeEnum.POSTS.getKey();
                        String mchType = MineCollectionTypeEnum.MCH.getKey();
                        if (collectionType.equals(postsType)) {

                            Intent intent = new Intent();
                            intent.putExtra("postId", id);
                            intent.setClass(mContext, PostRecommendDetailActivity.class);
                            mContext.startActivity(intent);

                        } else if (collectionType.equals(mchType)) {
                            String type = recommendFriends.getMchType();
                            Intent intent = new Intent();
                            String mchFood = MchTypeEnum.MCH_FOOD.getValue();
                            String mchScenicSpot = MchTypeEnum.MCH_SCENIC.getValue();
                            String mchHotel = MchTypeEnum.MCH_HOTEL.getValue();
                            String mchRecreation = MchTypeEnum.MCH_RECREATION.getValue();
                            if (type.equals(mchFood)) {
                                intent.setClass(mContext, FoodDetailActivity.class);
                                intent.putExtra("mchId", id);
                                mContext.startActivity(intent);

                            } else if (type.equals(mchScenicSpot)) {
                                intent.setClass(mContext, ScenicSpotDetailActivity.class);
                                String mchTypeScenic = MchTypeEnum.MCH_SCENIC.getValue();

                                intent.putExtra("mchId", id);
                                intent.putExtra("mchType", mchTypeScenic);
                                mContext.startActivity(intent);
                            } else if (type.equals(mchRecreation)) {
                                String mchTypeRecreation = MchTypeEnum.MCH_RECREATION.getValue();
                                intent.setClass(mContext, ScenicSpotDetailActivity.class);
                                intent.putExtra("mchId", id);
                                intent.putExtra("mchType", mchTypeRecreation);
                                mContext.startActivity(intent);
                            } else if (type.equals(mchHotel)) {
                                String mchHotelType = MchTypeEnum.MCH_HOTEL1.getValue();
                                String mchHomestayType = MchTypeEnum.MCH_HOTEL1.getValue();
                                String mchType2 = recommendFriends.getMchType2();
                                if (mchType2 != null) {
                                    if (mchType2.equals(mchHotelType)) {

                                        intent.setClass(mContext, HotelDetailsActivity.class);
                                        intent.putExtra("mchId", id);
                                        mContext.startActivity(intent);

                                    } else if (mchType2.equals(mchHomestayType)) {
                                        intent.setClass(mContext, HomestayDetailActivity.class);
                                        intent.putExtra("mchId", id);
                                        mContext.startActivity(intent);
                                    }
                                }
                            }
                        }
                    }
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


    public List<FavoritesBean> getSelectAlbumEditList() {

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

    public interface AlbumOprateListener {

        void setAlbumOprateListener(int mSelectAlbumNum);
    }
}
