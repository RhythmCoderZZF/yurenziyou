package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.CommentUserEntity;
import com.nbhysj.coupon.model.response.MchCommentEntity;
import com.nbhysj.coupon.model.response.MchDetailsResponse;
import com.nbhysj.coupon.model.response.NearbyScenicSpotsResponse;
import com.nbhysj.coupon.model.response.ScenicSpotsUserCommentResponse;
import com.nbhysj.coupon.util.DateUtil;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.GlideImageView;
import com.nbhysj.coupon.view.MyRecycleView;
import com.nbhysj.coupon.view.StarBarView;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author hysj created at 2019/4/27.
 * description:景点详情用户评论适配器
 */
public class MchCommentAdapter extends RecyclerView.Adapter<MchCommentAdapter.ViewHolder> {

    private final int MAX_LINE_COUNT = 3;//最大显示行数

    private final int STATE_UNKNOW = -1;//未知状态

    private final int STATE_NOT_OVERFLOW = 1;//文本行数小于最大可显示行数

    private final int STATE_COLLAPSED = 2;//折叠状态

    private final int STATE_EXPANDED = 3;//展开状态

    /**
     * 注意：保存文本状态集合的key一定要是唯一的，如果用position。
     * 如果使用position作为key，则删除、增加条目的时候会出现显示错乱
     */
    private SparseArray<Integer> mTextStateList;//保存文本状态集合
    List<MchCommentEntity> scenicSpotsUserCommentList;
    private Context mContext;

    public MchCommentAdapter(Context mContext) {

        this.mContext = mContext;
        mTextStateList = new SparseArray<>();
    }

    public void setScenicSpotsUserCommentList(List<MchCommentEntity> scenicSpotsUserCommentList) {

        this.scenicSpotsUserCommentList = scenicSpotsUserCommentList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_scenic_spot_detail_user_comment_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        try {
            int state = mTextStateList.get(scenicSpotsUserCommentList.get(position).getId(), STATE_UNKNOW);
            MchCommentEntity userCommentResponse = scenicSpotsUserCommentList.get(position);
            CommentUserEntity userEntity = userCommentResponse.getUser();
            if(userEntity != null)
            {
                String nickName = userEntity.getNickname();
                if (!TextUtils.isEmpty(nickName))
                {
                    holder.mTvUserName.setText(nickName);
                }
                String avatarUrl = userEntity.getAvater();
                GlideUtil.loadImage(mContext, avatarUrl, holder.mImgUserAvatar);
            }

            long cTime = userCommentResponse.getCtime();
            holder.mTvCommentPublishTime.setText(DateUtil.transferLongToDate(DateUtil.sDateYMDFormat, cTime));
            holder.mStarBarScenicSpots.setIntegerMark(false);
            holder.mStarBarScenicSpots.setStarMark(userCommentResponse.getScore());

            //第一次初始化，未知状态
            if (state == STATE_UNKNOW) {
                holder.mTvContent.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        //这个回掉会调用多次，获取完行数后记得注销监听
                        holder.mTvContent.getViewTreeObserver().removeOnPreDrawListener(this);
                        //holder.content.getViewTreeObserver().addOnPreDrawListener(null);
                        //如果内容显示的行数大于最大显示行数
                        if (holder.mTvContent.getLineCount() > MAX_LINE_COUNT) {
                            holder.mTvContent.setMaxLines(MAX_LINE_COUNT);//设置最大显示行数
                            holder.mTvExpandOrFold.setVisibility(View.VISIBLE);//显示“全文”
                            holder.mTvExpandOrFold.setText("全文");
                            mTextStateList.put(scenicSpotsUserCommentList.get(position).getId(), STATE_COLLAPSED);//保存状态
                        } else {
                            holder.mTvExpandOrFold.setVisibility(View.GONE);
                            mTextStateList.put(scenicSpotsUserCommentList.get(position).getId(), STATE_NOT_OVERFLOW);
                        }
                        return true;
                    }
                });

                holder.mTvContent.setMaxLines(Integer.MAX_VALUE);//设置文本的最大行数，为整数的最大数值
                holder.mTvContent.setText(scenicSpotsUserCommentList.get(position).getContent());
            } else {
                //如果之前已经初始化过了，则使用保存的状态。
                switch (state) {
                    case STATE_NOT_OVERFLOW:
                        holder.mTvExpandOrFold.setVisibility(View.GONE);
                        break;
                    case STATE_COLLAPSED:
                        holder.mTvContent.setMaxLines(MAX_LINE_COUNT);
                        holder.mTvExpandOrFold.setVisibility(View.VISIBLE);
                        holder.mTvExpandOrFold.setText("全文");
                        break;
                    case STATE_EXPANDED:
                        holder.mTvContent.setMaxLines(Integer.MAX_VALUE);
                        holder.mTvExpandOrFold.setVisibility(View.VISIBLE);
                        holder.mTvExpandOrFold.setText("收起");
                        break;
                }
                holder.mTvContent.setText(scenicSpotsUserCommentList.get(position).getContent());
            }
            // holder.mTvPerCapitaPrice.setText(nearbyScenicSpots.getScenicSpotsTicketPrice());

            //全文和收起的点击事件
            holder.mTvExpandOrFold.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int state = mTextStateList.get(scenicSpotsUserCommentList.get(position).getId(), STATE_UNKNOW);
                    if (state == STATE_COLLAPSED) {
                        holder.mTvContent.setMaxLines(Integer.MAX_VALUE);
                        holder.mTvExpandOrFold.setText("收起");
                        mTextStateList.put(scenicSpotsUserCommentList.get(position).getId(), STATE_EXPANDED);
                    } else if (state == STATE_EXPANDED) {
                        holder.mTvContent.setMaxLines(MAX_LINE_COUNT);
                        holder.mTvExpandOrFold.setText("全文");
                        mTextStateList.put(scenicSpotsUserCommentList.get(position).getId(), STATE_COLLAPSED);
                    }
                }
            });

            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
            layoutManager.setOrientation(layoutManager.HORIZONTAL);
            holder.mRvUserCommentPhoto.setLayoutManager(layoutManager);
            List<String> userCommentPhotoList = scenicSpotsUserCommentList.get(position).getPhoto();
            if (userCommentPhotoList != null) {
                ScenicSpotDetailCommentPhotoAdapter userCommentPhotoAdapter = new ScenicSpotDetailCommentPhotoAdapter(mContext);
                userCommentPhotoAdapter.setUserCommentPhotoList(userCommentPhotoList);
                holder.mRvUserCommentPhoto.setAdapter(userCommentPhotoAdapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return scenicSpotsUserCommentList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //用户名字
        TextView mTvUserName;
        //用户头像
        CircleImageView mImgUserAvatar;
        //评论发布时间
        TextView mTvCommentPublishTime;
        //评分星级
        StarBarView mStarBarScenicSpots;
        //评论内容
        TextView mTvContent;
        //文本收缩
        TextView mTvExpandOrFold;
        //用户评论图片
        MyRecycleView mRvUserCommentPhoto;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvUserName = itemView.findViewById(R.id.tv_username);
            mImgUserAvatar = itemView.findViewById(R.id.image_user_avatar);
            mTvCommentPublishTime = itemView.findViewById(R.id.tv_comment_publish_time);
            mStarBarScenicSpots = itemView.findViewById(R.id.starbar_scenic_spots);
            mTvContent = itemView.findViewById(R.id.tv_content);
            mTvExpandOrFold = itemView.findViewById(R.id.tv_expand_or_fold);
            mRvUserCommentPhoto = itemView.findViewById(R.id.rv_user_comment_photo);
        }
    }
}
