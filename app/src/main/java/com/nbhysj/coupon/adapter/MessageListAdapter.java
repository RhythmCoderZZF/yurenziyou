package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.MessageBean;
import com.nbhysj.coupon.view.GlideImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * created by hysj on 2019/02/20.
 * description: 消息适配器
 */

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.ViewHolder> {

    /**
     * 空数据时，显示空布局类型
     */
    private final int EMPTY_VIEW = 1;

    /**
     * 控制空布局的显隐
     */
    private int mEmptyType = 0;

    private Context mContext;
    private List<MessageBean> messageList;

    public MessageListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setMessageList(List<MessageBean> messageList) {

        this.messageList = messageList;
    }

    @Override
    public MessageListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.list_message_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MessageListAdapter.ViewHolder holder, int position) {

        try {
            MessageBean messageBean = messageList.get(position);
            holder.mTvTitle.setText(messageBean.getTitle());
            holder.mTvMessageTime.setText(messageBean.getTime());
            holder.mTvMessageContent.setText(messageBean.getContent());
            String url = messageBean.getUrl();
            holder.mImgMessageType.loadCircle(url);
            if (messageBean.isRead() == false) {
                holder.mImageMessageDot.setVisibility(View.VISIBLE);
            } else if (messageBean.isRead() == true) {
                holder.mImageMessageDot.setVisibility(View.GONE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        //注意，空布局---> mEmptyType = 1 显示1个布局
        //            ---> mEmptyType = 0 所有布局都不显示的
        //   return dataList != null ? dataList.size() + mEmptyType : mEmptyType;
        return messageList.size();
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
        @BindView(R.id.image_message_type)
        GlideImageView mImgMessageType;
        @BindView(R.id.tv_title)
        TextView mTvTitle;
        @BindView(R.id.tv_message_time)
        TextView mTvMessageTime;
        @BindView(R.id.tv_message_content)
        TextView mTvMessageContent;
        @BindView(R.id.img_message_dot)
        ImageView mImageMessageDot;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
