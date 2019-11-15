package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.NoteCommentBean;
import com.nbhysj.coupon.view.GlideImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * created by hysj on 2019/02/20.
 * description: 帖子详情评论列表适配器
 */

public class NoteDetailCommentListAdapter extends RecyclerView.Adapter<NoteDetailCommentListAdapter.ViewHolder> {

    /**
     * 空数据时，显示空布局类型
     */
    private final int EMPTY_VIEW = 1;

    /**
     * 控制空布局的显隐
     */
    private int mEmptyType = 0;

    private Context mContext;
    private List<NoteCommentBean> noteCommentList;

    public NoteDetailCommentListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setNoteCommentList(List<NoteCommentBean> noteCommentList) {

        this.noteCommentList = noteCommentList;
    }

    @Override
    public NoteDetailCommentListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.list_note_detail_comment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteDetailCommentListAdapter.ViewHolder holder, int position) {

        try {
            NoteCommentBean noteCommentBean = noteCommentList.get(position);
            holder.mImgNoteCommentUserAvatar.loadCircle(noteCommentBean.getCommentUserAvatar(), R.mipmap.icon_placeholder_image);

            holder.mTvCommentContent.setText(noteCommentBean.getCommentContent());
            holder.mTvCommentTime.setText(noteCommentBean.getCommentTime());
            holder.mTvCommentUser.setText(noteCommentBean.getCommentUsername());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        //注意，空布局---> mEmptyType = 1 显示1个布局
        //            ---> mEmptyType = 0 所有布局都不显示的
        //   return dataList != null ? dataList.size() + mEmptyType : mEmptyType;
        return noteCommentList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mEmptyType == EMPTY_VIEW) {
            //空布局的类型
            return EMPTY_VIEW;
        }
        return super.getItemViewType(position);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //帖子详情评论用户头像
        @BindView(R.id.image_note_comment_user_avatar)
        GlideImageView mImgNoteCommentUserAvatar;
        //评论内容
        @BindView(R.id.tv_comment_content)
        TextView mTvCommentContent;
        //评论用户
        @BindView(R.id.tv_comment_user)
        TextView mTvCommentUser;
        //评论时间
        @BindView(R.id.tv_comment_time)
        TextView mTvCommentTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
