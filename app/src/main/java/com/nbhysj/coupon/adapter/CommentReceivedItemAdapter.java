package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.CommentReceiveResponse;
import com.nbhysj.coupon.view.GlideImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author hysj created on 2019/04/14.
 * description: 收到的评论适配器
 */
public class CommentReceivedItemAdapter extends RecyclerView.Adapter<CommentReceivedItemAdapter.ViewHolder> {

    /**
     * 图片列表数据
     */
    private List<CommentReceiveResponse> commentReceiveList;

    private Context mContext;


    public CommentReceivedItemAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setCommentReceivedList(List<CommentReceiveResponse> commentReceiveList) {

        this.commentReceiveList = commentReceiveList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_comments_received_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        try {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            holder.mRvCommentContent.setLayoutManager(linearLayoutManager);
            CommentReceiveResponse commentReceive = commentReceiveList.get(position);
            List<CommentReceiveResponse.CommentEntity> commentEntityList = commentReceive.getCommentList();
            CommentReceivedSubItemAdapter commentReceivedSubItemAdapter = new CommentReceivedSubItemAdapter(mContext);
            commentReceivedSubItemAdapter.setCommentReceivedSubItemList(commentEntityList);
            holder.mRvCommentContent.setAdapter(commentReceivedSubItemAdapter);
            String avatarUrl = commentReceive.getAvatar();
            String commentPictrueUrl = commentReceive.getCommentPictrue();
            holder.mImgUserAvatar.loadCircle(avatarUrl, R.mipmap.icon_placeholder_image);
            Glide.with(mContext).load(commentPictrueUrl).into(holder.mImgCommentPictrue);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return commentReceiveList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //评论内容
        @BindView(R.id.rv_comment_content)
        RecyclerView mRvCommentContent;
        //头像
        @BindView(R.id.image_user_avatar)
        GlideImageView mImgUserAvatar;
        //评论图片
        @BindView(R.id.img_comment_pictrue)
        ImageView mImgCommentPictrue;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
