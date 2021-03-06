package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.MessageResponse;
import com.nbhysj.coupon.model.response.UserFansFollowBean;
import com.nbhysj.coupon.ui.UserChatListActivity;
import com.nbhysj.coupon.util.DateUtil;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.GlideImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

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
    private List<MessageResponse.MessageEntity> messageList;

    public MessageListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setMessageList(List<MessageResponse.MessageEntity> messageList) {

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
            MessageResponse.MessageEntity userFansFollowBean = messageList.get(position);
            String fansName = userFansFollowBean.getNickname();
            int userId = userFansFollowBean.getId();
             long chatTime = userFansFollowBean.getCtime();
            String avatar = userFansFollowBean.getAvater();
            String messageContent = userFansFollowBean.getMessage();
            String time =  DateUtil.transferLongToDate(DateUtil.sDateYMDHHMMSSFormat,chatTime);
            holder.mTvUsername.setText(fansName);
            holder.mTvMessageTime.setText(time);
            GlideUtil.loadImage(mContext,avatar,holder.mImgUserAvatar);
            holder.mTvMessageContent.setText(messageContent);

            holder.mLlytChatMessageItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent mIntent = new Intent();
                    mIntent.setClass(mContext, UserChatListActivity.class);
                    mIntent.putExtra("uid",userId);
                    mIntent.putExtra("username",fansName);
                    mContext.startActivity(mIntent);
                }
            });
          //  holder.mTvMessageContent.setText(messageBean.getContent());
           /* String url = messageBean.getUrl();
            holder.mImgMessageType.loadCircle(url);
            if (messageBean.isRead() == false) {
                holder.mImageMessageDot.setVisibility(View.VISIBLE);
            } else if (messageBean.isRead() == true) {
                holder.mImageMessageDot.setVisibility(View.GONE);
            }*/

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
        //头像
        @BindView(R.id.img_user_avatar)
        CircleImageView mImgUserAvatar;
        @BindView(R.id.tv_username)
        TextView mTvUsername;
        @BindView(R.id.tv_message_time)
        TextView mTvMessageTime;
        @BindView(R.id.tv_message_content)
        TextView mTvMessageContent;
        @BindView(R.id.img_message_dot)
        ImageView mImageMessageDot;
        @BindView(R.id.llyt_chat_message_item)
        LinearLayout mLlytChatMessageItem;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
