package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Enum.MchTypeEnum;
import com.nbhysj.coupon.model.response.CommentUserEntity;
import com.nbhysj.coupon.model.response.MchCommentEntity;
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.ui.HomestayDetailActivity;
import com.nbhysj.coupon.ui.HotelDetailsActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.RoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author hysj created at 2019/4/19.
 * description : 酒店名宿适配器
 */
public class HotelDetailUserCommentAdapter extends RecyclerView.Adapter<HotelDetailUserCommentAdapter.ViewHolder> {

    List<MchCommentEntity> hotelList;
    private Context mContext;

    public HotelDetailUserCommentAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setHotelList(List<MchCommentEntity> hotelList) {

        this.hotelList = hotelList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_hotel_detail_user_comment_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
            MchCommentEntity hotelComment = hotelList.get(itemPosition);
            String commentContent = hotelComment.getContent();
            CommentUserEntity commentUserEntity = hotelComment.getUser();
            String avaterUrl = commentUserEntity.getAvater();
            String nickname = commentUserEntity.getNickname();

            holder.mTvCommentContent.setText(commentContent);
            GlideUtil.loadImage(mContext,avaterUrl,holder.mImgUserAvatar);
            holder.mTvNickname.setText(nickname);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return hotelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //头像
        @BindView(R.id.image_user_avatar)
        CircleImageView mImgUserAvatar;
        //用户名
        @BindView(R.id.tv_nickname)
        TextView mTvNickname;
        //评论内容
        @BindView(R.id.tv_comment_content)
        TextView mTvCommentContent;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
