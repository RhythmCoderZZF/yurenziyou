package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.model.response.UserChatBean;
import com.nbhysj.coupon.ui.HotelDetailsActivity;
import com.nbhysj.coupon.util.GlideUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author hysj created at 2019/4/7.
 * description:酒店页面栏目适配器
 */
public class ChatMessageListAdapter extends RecyclerView.Adapter<ChatMessageListAdapter.ViewHolder> {

    List<UserChatBean> userChatList;
    private Context mContext;

    public ChatMessageListAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setChatMessageList(List<UserChatBean> userChatList) {

        this.userChatList = userChatList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_message_chat_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
            UserChatBean userChatBean = userChatList.get(itemPosition);
            String message = userChatBean.getMessage();
            int ownStatus = userChatBean.getOwnStatus();
            String avatarUrl = userChatBean.getAvater();
            if (ownStatus == 0) {

                holder.mLlytOthersChatMessage.setVisibility(View.VISIBLE);
                holder.mRlytMineChatMessage.setVisibility(View.GONE);
                holder.mTvOthersChatMessageContent.setText(message);
                GlideUtil.loadImage(mContext,avatarUrl,holder.mImgOthersAvatar);

            } else {

                holder.mLlytOthersChatMessage.setVisibility(View.GONE);
                holder.mRlytMineChatMessage.setVisibility(View.VISIBLE);
                holder.mTvOwnChatMessageContent.setText(message);
                GlideUtil.loadImage(mContext,avatarUrl,holder.mImgOwnAvatar);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return userChatList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //他人聊天消息内容
        @BindView(R.id.tv_others_chat_message_content)
        TextView mTvOthersChatMessageContent;
        //美食照片
        @BindView(R.id.image_others_avatar)
        CircleImageView mImgOthersAvatar;
        //他人聊天消息
        @BindView(R.id.llyt_others_chat_message)
        LinearLayout mLlytOthersChatMessage;
        //自己聊天消息
        @BindView(R.id.rlyt_mine_chat_message)
        RelativeLayout mRlytMineChatMessage;
        //美食照片
        @BindView(R.id.image_own_avatar)
        CircleImageView mImgOwnAvatar;
        @BindView(R.id.tv_own_chat_message_content)
        TextView mTvOwnChatMessageContent;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
