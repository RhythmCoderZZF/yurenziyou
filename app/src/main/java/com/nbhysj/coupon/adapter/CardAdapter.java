package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.HomePageSubTopicTagBean;
import com.nbhysj.coupon.ui.PostRecommendDetailActivity;
import com.nbhysj.coupon.util.GlideUtil;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardHolder> {

    private List<HomePageSubTopicTagBean> mTopicTagList;
    private Context mContext;
    private PostFollowListener postFollowListener;

    public CardAdapter(Context mContext,PostFollowListener postFollowListener) {
        this.mContext = mContext;
        this.postFollowListener = postFollowListener;

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

        HomePageSubTopicTagBean homePageSubTopicTagBean = mTopicTagList.get(position);
        int id = homePageSubTopicTagBean.getId();
        int userId = homePageSubTopicTagBean.getUserId();
        int collectionCount = homePageSubTopicTagBean.getCollectionCount();
        int commentCount = homePageSubTopicTagBean.getCommentCount();
        double distance = homePageSubTopicTagBean.getDistance();

        int isAttention = homePageSubTopicTagBean.getIsAttention();

        if (isAttention == 0) {

            holder.mTvFollow.setBackgroundResource(R.drawable.bg_blue_green_gradient_radius_thirteen);
            holder.mTvFollow.setTextColor(mContext.getResources().getColor(R.color.white));
            holder.mTvFollow.setText(mContext.getResources().getString(R.string.str_attention));

        } else if(isAttention == 1){
            holder.mTvFollow.setBackgroundResource(R.drawable.bg_gray_radius_thirteen_shape);
            holder.mTvFollow.setTextColor(mContext.getResources().getColor(R.color.white));
            holder.mTvFollow.setText(mContext.getResources().getString(R.string.str_already_concerned));

        }

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

        holder.mTvLocationDistance.setText("距离" + String.valueOf(distance) + "km");

        holder.mImgNearbyPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.putExtra("postId",id);
                intent.setClass(mContext, PostRecommendDetailActivity.class);
                mContext.startActivity(intent);
            }
        });

        holder.mTvFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                postFollowListener.setPostFollowListener(homePageSubTopicTagBean,userId);
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
        //关注
        TextView mTvFollow;

        public CardHolder(View itemView) {
            super(itemView);
            mImgNearbyPost = itemView.findViewById(R.id.img_nearby_post);
            mImgUserAvatar = itemView.findViewById(R.id.image_interest_user_avatar);
            mTvNickname = itemView.findViewById(R.id.tv_nickname);
            mTvContent = itemView.findViewById(R.id.tv_content);
            mTvCollectionNum = itemView.findViewById(R.id.tv_collection_num);
            mTvCommentNum = itemView.findViewById(R.id.tv_comment_num);
            mTvLocationDistance = itemView.findViewById(R.id.tv_location_distance);
            mTvFollow = itemView.findViewById(R.id.tv_follow);
        }
    }

    public interface PostFollowListener{

        void setPostFollowListener(HomePageSubTopicTagBean homePageSubTopicTagBean,int userId);
    }
}
