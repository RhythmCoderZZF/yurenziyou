package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.HomePageSubTopicTagBean;
import com.nbhysj.coupon.util.GlideUtil;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardHolder> {

    private List<HomePageSubTopicTagBean> mTopicTagList;
    private Context mContext;

    public CardAdapter(Context mContext) {
        this.mContext = mContext;

    }

    public void setNearbyCardList(List<HomePageSubTopicTagBean> topicTagList) {

        mTopicTagList = topicTagList;
    }

    @Override
    public CardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_nearby_card_item, parent, false);
        return new CardHolder(view);
    }

    @Override
    public void onBindViewHolder(final CardHolder holder, final int position) {

        final HomePageSubTopicTagBean homePageSubTopicTagBean = mTopicTagList.get(position);
        int collectionCount = homePageSubTopicTagBean.getCollectionCount();
        int commentCount = homePageSubTopicTagBean.getCommentCount();
        double distance = homePageSubTopicTagBean.getDistance();

        // Glide.with(holder.itemView).load(bean.getUrl()).apply(mRequestOptions).into(holder.img);

        /*GlideApp.with(mContext)
                .load(bean.getUrl())
                .placeholder(R.mipmap.icon_placeholder_image)
                .error(R.mipmap.icon_placeholder_image)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .override(Target.SIZE_ORIGINAL)
                .transform(new GlideRoundCornersTransUtils(mContext, 12, GlideRoundCornersTransUtils.CornerType.TOP))
                .into(holder.img);*/

        GlideUtil.loadImage(mContext, homePageSubTopicTagBean.getPhoto(), holder.mImgNearbyPost);

        GlideUtil.loadImage(mContext, homePageSubTopicTagBean.getAvater(), holder.mImgUserAvatar);

        //昵称
        holder.mTvNickname.setText(homePageSubTopicTagBean.getNickname());
        //内容
        holder.mTvContent.setText(homePageSubTopicTagBean.getContent());

        //评论数
        holder.mTvCommentNum.setText(String.valueOf(commentCount));

        //收藏数
        holder.mTvCollectionNum.setText(String.valueOf(collectionCount));

        //if(distance != 0) {

        holder.mTvLocationDistance.setText("距离" + String.valueOf(distance) + "km");

        // }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(holder.itemView.getContext(), "click " + bean.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.mImgNearbyPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                Intent intent = new Intent();
                intent.setClass(mContext, NearbyCardDetailActivity.class);
                intent.putExtra("imageUrl",bean.getUrl());
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeCustomAnimation(mContext,R.anim.activity_anim_loading,R.anim.actionsheet_dialog_out);
                ActivityCompat.startActivity(mContext, intent, compat.toBundle());*/
                // mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTopicTagList.size();
    }

    static class CardHolder extends RecyclerView.ViewHolder {
        ImageView mImgNearbyPost;
        CircleImageView mImgUserAvatar;
        TextView mTvNickname;
        //内容
        TextView mTvContent;
        //收藏数
        TextView mTvCollectionNum;
        //评论数
        TextView mTvCommentNum;
        //距离位置
        TextView mTvLocationDistance;

        public CardHolder(View itemView) {
            super(itemView);
            mImgNearbyPost = itemView.findViewById(R.id.img_nearby_post);
            mImgUserAvatar = itemView.findViewById(R.id.image_interest_user_avatar);
            mTvNickname = itemView.findViewById(R.id.tv_nickname);
            mTvContent = itemView.findViewById(R.id.tv_content);
            mTvCollectionNum = itemView.findViewById(R.id.tv_collection_num);
            mTvCommentNum = itemView.findViewById(R.id.tv_comment_num);
            mTvLocationDistance = itemView.findViewById(R.id.tv_location_distance);
        }
    }
}
