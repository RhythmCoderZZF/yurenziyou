package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Enum.MchTypeEnum;
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.model.response.UserFansFollowBean;
import com.nbhysj.coupon.ui.ScenicSpotDetailActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.Tools;
import com.nbhysj.coupon.view.RoundedImageView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author hysj created at 2019/11/16.
 * description :
 */
public class MineFollowFansHeaderListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<UserFansFollowBean> userFansFollowList;
    private Context mContext;
    private View mHeaderView;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    LayoutInflater mInflater;
    private FollowListener followListener;

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
//        notifyItemChanged(1,1);
    }

    public MineFollowFansHeaderListAdapter(Context mContext,FollowListener followListener) {

        this.mContext = mContext;
        this.followListener = followListener;
    }

    public void setMineFollowFansHeaderList(List<UserFansFollowBean> userFansFollowList) {

        this.userFansFollowList = userFansFollowList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) return new ViewHolder(mHeaderView);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_mine_follow_footer_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        try {
            if (getItemViewType(position) == TYPE_HEADER) return;
            final int pos = getRealPosition(holder);
            if (holder instanceof ViewHolder) {
                ViewHolder holder1 = (ViewHolder) holder;
                UserFansFollowBean userFansFollow = userFansFollowList.get(position - 1);
                int fansId = userFansFollow.getFansId();
                String avatarUrl = userFansFollow.getAvater();
                String fansName = userFansFollow.getFansName();
                int attentionStatus = userFansFollow.getFanStatus();
                int postsNum = userFansFollow.getPostsNum();
                int fansNum = userFansFollow.getFansNum();

                GlideUtil.loadImage(mContext, avatarUrl, holder1.mImgUserAvatar);

                holder1.mTvUserName.setText(fansName);
                holder1.mTvPostNum.setText(String.valueOf(postsNum));
                holder1.mTvFansNum.setText(String.valueOf(fansNum));
                /*if (attentionStatus == 0) {
                    holder1.mImgPostFollowStatus.setImageResource(R.mipmap.icon_mine_follow_header_plus);
                    holder1.mLlytFollow.setBackgroundResource(R.drawable.bg_blue_green_gradient_radius_thirteen);

                } else if (attentionStatus == 1) {
                    holder1.mImgPostFollowStatus.setImageResource(R.mipmap.icon_already_followed_check_mark);
                    holder1.mLlytFollow.setBackgroundResource(R.drawable.bg_gray_radius_thirteen_shape);
                    holder1.mTvCancelFollow.setText("取消关注");
                }*/

                holder1.mLlytFollow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        followListener.setFollowListener(fansId,position - 1);

                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mHeaderView != null ? userFansFollowList.size() + 1:userFansFollowList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null) return TYPE_NORMAL;
        if (position == 0) return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        //用户头像
        CircleImageView mImgUserAvatar;
        //用户名
        TextView mTvUserName;
        //帖子数
        TextView mTvPostNum;
        //粉丝数量
        TextView mTvFansNum;
        //帖子关注状态
        ImageView mImgPostFollowStatus;

        TextView mTvCancelFollow;

        LinearLayout mLlytFollow;

        public ViewHolder(View itemView) {
            super(itemView);

            mImgUserAvatar = itemView.findViewById(R.id.image_user_avatar);
            mTvUserName = itemView.findViewById(R.id.tv_username);
            mTvPostNum = itemView.findViewById(R.id.tv_posts_num);
            mTvFansNum = itemView.findViewById(R.id.tv_fans_num);
            mImgPostFollowStatus = itemView.findViewById(R.id.img_post_follow_status);
            mTvCancelFollow = itemView.findViewById(R.id.tv_cancel_follow);
            mLlytFollow = itemView.findViewById(R.id.llyt_follow);
        }
    }

    public interface FollowListener{

        void setFollowListener(int userId,int mPosition);
    }
}
