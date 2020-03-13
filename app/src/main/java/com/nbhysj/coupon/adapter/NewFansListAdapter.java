package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.NewFansBean;
import com.nbhysj.coupon.model.response.SupportedUserBean;
import com.nbhysj.coupon.model.response.UserFansFollowBean;
import com.nbhysj.coupon.ui.UserPersonalHomePageActivity;
import com.nbhysj.coupon.util.DateUtil;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.GlideImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * created by hysj on 2019/03/21
 * description: 新增关注列表适配器
 */

public class NewFansListAdapter extends RecyclerView.Adapter<NewFansListAdapter.ViewHolder> {

    /**
     * 空数据时，显示空布局类型
     */
    private final int EMPTY_VIEW = 1;

    /**
     * 控制空布局的显隐
     */
    private int mEmptyType = 0;

    private Context mContext;
    private List<UserFansFollowBean> newFansList;
    private NewFansFollowListener newFansFollowListener;

    public NewFansListAdapter(Context mContext,NewFansFollowListener newFansFollowListener) {
        this.mContext = mContext;
        this.newFansFollowListener = newFansFollowListener;
    }

    public void setNewFansList(List<UserFansFollowBean> newFansList) {

        this.newFansList = newFansList;
    }

    @Override
    public NewFansListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.list_new_fans_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewFansListAdapter.ViewHolder holder, int position) {

        try {
            UserFansFollowBean newFansBean = newFansList.get(position);
            String fansName = newFansBean.getFansName();
            long followTime = newFansBean.getCtime();   //关注时间
            int userId = newFansBean.getFansId();
            String followTimeStr = DateUtil.transferLongToDate(DateUtil.sDateYMDFormat,followTime);
            holder.mTvUsername.setText(fansName);
            holder.mTvFollowTime.setText(followTimeStr);
            String avatarUrl = newFansBean.getAvater();
            GlideUtil.loadImage(mContext,avatarUrl,holder.mImgUserAvatar);
            if (newFansBean.getAttentionStatus() == 0) {
                holder.mTvFollow.setBackgroundResource(R.drawable.bg_new_fans_stroke_radius_red_shape);
                holder.mTvFollow.setTextColor(mContext.getResources().getColor(R.color.color_red2));
                holder.mTvFollow.setText(mContext.getResources().getString(R.string.str_return_powder));

            } else if (newFansBean.getAttentionStatus() == 1) {
                holder.mTvFollow.setBackgroundResource(R.drawable.bg_gray_radius_thirteen_shape);
                holder.mTvFollow.setTextColor(mContext.getResources().getColor(R.color.white));
                holder.mTvFollow.setText(mContext.getResources().getString(R.string.str_already_concerned));
            }

            holder.mTvFollow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    newFansFollowListener.setNewFansFollowListener(newFansBean);

                }
            });

            holder.mImgUserAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent();
                    intent.setClass(mContext, UserPersonalHomePageActivity.class);
                    intent.putExtra("publisherAvatarUrl", avatarUrl);
                    intent.putExtra("authorId", userId);
                    mContext.startActivity(intent);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        //注意，空布局---> mEmptyType = 1 显示1个布局
        //            ---> mEmptyType = 0 所有布局都不显示的
        //   return dataList != null ? dataList.size() + mEmptyType : mEmptyType;
        return newFansList.size();
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
        @BindView(R.id.image_user_avatar)
        CircleImageView mImgUserAvatar;
        @BindView(R.id.tv_username)
        TextView mTvUsername;
        @BindView(R.id.tv_follow_time)
        TextView mTvFollowTime;
        @BindView(R.id.tv_follow)
        TextView mTvFollow;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public interface NewFansFollowListener {

        public void setNewFansFollowListener(UserFansFollowBean newFansBean);

    }
}
