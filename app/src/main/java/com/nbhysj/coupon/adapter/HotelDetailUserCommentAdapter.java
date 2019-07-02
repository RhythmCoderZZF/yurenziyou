package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.ScenicSpotsUserCommentResponse;
import com.nbhysj.coupon.view.GlideImageView;
import com.nbhysj.coupon.view.StarBarView;

import java.util.List;

/**
 * @author hysj created at 2019/05/11.
 * description:酒店详情用户评论适配器
 */
public class HotelDetailUserCommentAdapter extends RecyclerView.Adapter<HotelDetailUserCommentAdapter.ViewHolder> {

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
    List<ScenicSpotsUserCommentResponse> scenicSpotsUserCommentList;
    private Context mContext;

    public HotelDetailUserCommentAdapter(Context mContext) {

        this.mContext = mContext;
        mTextStateList = new SparseArray<>();
    }

    public void setScenicSpotsUserCommentList(List<ScenicSpotsUserCommentResponse> scenicSpotsUserCommentList) {

        this.scenicSpotsUserCommentList = scenicSpotsUserCommentList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_hotel_detail_user_comment_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        try {
            int state = mTextStateList.get(scenicSpotsUserCommentList.get(position).getId(), STATE_UNKNOW);
            ScenicSpotsUserCommentResponse userCommentResponse = scenicSpotsUserCommentList.get(position);
            holder.mTvUserName.setText(userCommentResponse.getUsername());
            holder.mImgUserAvatar.loadCircle(userCommentResponse.getUserAvatarPhoto(), R.mipmap.icon_placeholder_image);
            // holder.mTvCommentPublishTime.setText(userCommentResponse.getCommentPublishTime());

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
                            holder.mTvExpandOrFold.setText("阅读更多");
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
                        holder.mTvExpandOrFold.setText("阅读更多");
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
                        holder.mTvExpandOrFold.setText("阅读更多");
                        mTextStateList.put(scenicSpotsUserCommentList.get(position).getId(), STATE_COLLAPSED);
                    }
                }
            });

          /*  LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
            layoutManager.setOrientation(layoutManager.HORIZONTAL);
            holder.mRvUserCommentPhoto.setLayoutManager(layoutManager);
            List<String> userCommentPhotoList = scenicSpotsUserCommentList.get(position).getUserCommentPhotoList();
            ScenicSpotDetailCommentPhotoAdapter userCommentPhotoAdapter = new ScenicSpotDetailCommentPhotoAdapter(mContext);
            userCommentPhotoAdapter.setUserCommentPhotoList(userCommentPhotoList);
            holder.mRvUserCommentPhoto.setAdapter(userCommentPhotoAdapter);*/
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
        GlideImageView mImgUserAvatar;
        //评论发布时间
        TextView mTvCommentPublishTime;
        //评论内容
        TextView mTvContent;
        //文本收缩
        TextView mTvExpandOrFold;
        //用户评论图片
        RecyclerView mRvUserCommentPhoto;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvUserName = itemView.findViewById(R.id.tv_username);
            mImgUserAvatar = itemView.findViewById(R.id.image_user_avatar);
            //  mTvCommentPublishTime = itemView.findViewById(R.id.tv_comment_publish_time);
            mTvContent = itemView.findViewById(R.id.tv_content);
            mTvExpandOrFold = itemView.findViewById(R.id.tv_expand_or_fold);
            mRvUserCommentPhoto = itemView.findViewById(R.id.rv_user_comment_photo);
        }
    }
}
