package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.CommentReceiveResponse;
import com.nbhysj.coupon.model.response.CommentSubBean;
import com.nbhysj.coupon.model.response.CommentUserEntity;
import com.nbhysj.coupon.model.response.PostsCommentResponse;
import com.nbhysj.coupon.util.DateUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author hysj created on 2019/04/14.
 * description: 收到的评论列表子适配器
 */
public class CommentReceivedSubItemAdapter extends RecyclerView.Adapter<CommentReceivedSubItemAdapter.ViewHolder> {

    /**
     * 评论子级列表
     */
    private List<CommentSubBean> commentSubList;

    private Context mContext;


    public CommentReceivedSubItemAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setCommentReceivedSubItemList(List<CommentSubBean> commentSubList) {

        this.commentSubList = commentSubList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_comments_received_sub_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        try {

            CommentSubBean commentEntity = commentSubList.get(position);
            CommentUserEntity commentUserEntity = commentEntity.getUser();
            String nickName = commentUserEntity.getNickname();
            String content = commentEntity.getContent();
            holder.mTvCommentAuthorNickName.setText(nickName + ":");
            holder.mTvCommentContent.setText(content);


            if (commentSubList.size() - 1 == position) {
                holder.mLineView.setVisibility(View.GONE);
            } else {
                holder.mLineView.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return commentSubList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //评论者名称
        @BindView(R.id.tv_author_nickname)
        TextView mTvCommentAuthorNickName;
        //评论内容
        @BindView(R.id.tv_comment_content)
        TextView mTvCommentContent;
        @BindView(R.id.view_line)
        View mLineView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
