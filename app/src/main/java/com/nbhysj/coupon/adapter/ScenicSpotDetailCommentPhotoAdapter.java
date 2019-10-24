package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.NearbyScenicSpotsResponse;
import com.nbhysj.coupon.model.response.ScenicSpotsUserCommentResponse;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.RoundedImageView;
import com.nbhysj.coupon.view.StarBarView;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;

import java.util.List;

/**
 * @author hysj created at 2019/4/27.
 * description:景点附近适配器
 */
public class ScenicSpotDetailCommentPhotoAdapter extends RecyclerView.Adapter<ScenicSpotDetailCommentPhotoAdapter.ViewHolder> {

    List<String> userCommentPhotoList;
    private Context mContext;

    public ScenicSpotDetailCommentPhotoAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setUserCommentPhotoList(List<String> userCommentPhotoList) {

        this.userCommentPhotoList = userCommentPhotoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_scenic_spot_detail_user_comment_photo_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
            String photoUrl = userCommentPhotoList.get(itemPosition);

            GlideUtil.loadImage(mContext,photoUrl,holder.mImgUserCommentPhoto);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return userCommentPhotoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //用户评论图片
        RoundedImageView mImgUserCommentPhoto;

        public ViewHolder(View itemView) {
            super(itemView);

            mImgUserCommentPhoto = itemView.findViewById(R.id.image_user_comment_photo);
        }
    }
}
