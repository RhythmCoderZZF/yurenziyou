package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.BroadcastBean;
import com.nbhysj.coupon.model.response.UserFansFollowBean;
import com.nbhysj.coupon.ui.WebActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.RoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * @author hysj created on 2019/11/14.
 * description: 关注和粉丝尾部（我的模块)适配器
 */
public class MineFollowFooterAdapter extends RecyclerView.Adapter<MineFollowFooterAdapter.ViewHolder> {

    private List<UserFansFollowBean> userFansFollowList;
    private Context mContext;
    public MineFollowFooterAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setMineFollowFooterList(List<UserFansFollowBean> userFansFollowList) {

        this.userFansFollowList = userFansFollowList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_mine_follow_footer_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        try {
            UserFansFollowBean userFansFollow = userFansFollowList.get(position);
            String avatarUrl = userFansFollow.getAvater();
            String fansName = userFansFollow.getFansName();
            int attentionStatus = userFansFollow.getAttentionStatus();
            int postsNum = userFansFollow.getPostsNum();
            int fansNum = userFansFollow.getFansNum();

            GlideUtil.loadImage(mContext, avatarUrl, holder.mImgUserAvatar);

            holder.mTvUserName.setText(fansName);
            holder.mTvPostsNum.setText(String.valueOf(postsNum));
            holder.mTvFansNum.setText(String.valueOf(fansNum));
            if (attentionStatus == 0) {
                holder.mImgPostFollowStatus.setImageResource(R.mipmap.icon_mine_follow_header_plus);
                holder.mLlytFollow.setBackgroundResource(R.drawable.bg_blue_green_gradient_radius_thirteen);

            } else if (attentionStatus == 1) {
                holder.mImgPostFollowStatus.setImageResource(R.mipmap.icon_already_followed_check_mark);
                holder.mLlytFollow.setBackgroundResource(R.drawable.bg_gray_radius_thirteen_shape);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return userFansFollowList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //用户头像
        @BindView(R.id.image_user_avatar)
        CircleImageView mImgUserAvatar;

        //用户名
        @BindView(R.id.tv_username)
        TextView mTvUserName;

        //帖子数量
        @BindView(R.id.tv_posts_num)
        TextView mTvPostsNum;

        //粉丝数量
        @BindView(R.id.tv_fans_num)
        TextView mTvFansNum;

        //帖子关注状态
        @BindView(R.id.img_post_follow_status)
        ImageView mImgPostFollowStatus;

        @BindView(R.id.llyt_follow)
        LinearLayout mLlytFollow;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
