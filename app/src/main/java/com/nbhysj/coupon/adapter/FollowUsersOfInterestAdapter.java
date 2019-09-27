package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.library.banner.BannerLayout;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.FollowDetailBean;
import com.nbhysj.coupon.model.response.HomePageSubTopicTagBean;
import com.nbhysj.coupon.model.response.RecommendInterestUsersBean;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.GlideImageView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author hysj created at 2019/03/02.
 * description : 可能感兴趣用户列表
 */
public class FollowUsersOfInterestAdapter extends RecyclerView.Adapter<FollowUsersOfInterestAdapter.ViewHolder> {

    private Context context;
    private List<RecommendInterestUsersBean> userEntityList;
    private UsersOfInterestItemClickListener usersOfInterestItemClickListener;

    public FollowUsersOfInterestAdapter(Context context, UsersOfInterestItemClickListener usersOfInterestItemClickListener) {
        this.context = context;

        this.usersOfInterestItemClickListener = usersOfInterestItemClickListener;
    }


    public void setFollowUsersOfInterestList(List<RecommendInterestUsersBean> userEntityList) {
        this.userEntityList = userEntityList;
    }

    @Override
    public FollowUsersOfInterestAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_banner_user_like_item, parent, false));
    }

    @Override
    public void onBindViewHolder(FollowUsersOfInterestAdapter.ViewHolder holder, final int position) {
        if (userEntityList == null || userEntityList.isEmpty())
            return;
        // final int pos = position % userEntityList.size();
        RecommendInterestUsersBean userEntity = userEntityList.get(position);
        String avatarUrl = userEntity.getAvater();
        // String description = userEntity.get();
        String username = userEntity.getNickname();
        holder.mTvName.setText(username);
        // holder.mTvDescription.setText(description);
        GlideUtil.loadImage(context, avatarUrl, holder.mImageInterestUserAvatar);
        holder.mTvFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                usersOfInterestItemClickListener.setUsersOfInterestItemClickListener(userEntity);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (userEntityList != null) {
            return userEntityList.size();
        }
        return 0;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView mImageInterestUserAvatar;
        TextView mTvName, mTvDescription, mTvFollow;

        ViewHolder(View itemView) {
            super(itemView);
            mImageInterestUserAvatar = itemView.findViewById(R.id.image_interest_user_avatar);
            mTvName = itemView.findViewById(R.id.tv_name);
            mTvDescription = itemView.findViewById(R.id.tv_description);
            mTvFollow = itemView.findViewById(R.id.tv_follow);
        }
    }

    public interface UsersOfInterestItemClickListener {

        void setUsersOfInterestItemClickListener(RecommendInterestUsersBean userEntity);
    }
}
