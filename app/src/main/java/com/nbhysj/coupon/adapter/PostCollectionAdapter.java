package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.TouristBean;
import com.nbhysj.coupon.util.GlideUtil;

import java.util.List;

/**
 * @author hysj created at 2019/08/01.
 * description:新增游客信息适配器
 */
public class PostCollectionAdapter extends RecyclerView.Adapter<PostCollectionAdapter.ViewHolder> {

    List<String> strings;
    private Context mContext;
    private TouristInformationListener touristInformationListener;

    public PostCollectionAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setPostCollectionList(List<String> strings) {

        this.strings = strings;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_collection_show_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            GlideUtil.loadImage(mContext,"", holder.mImgUserAvatarPhoto);
            holder.mTvCollectionTitle.setText("");
            holder.mTvCollectionTime.setText("");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //帖子点赞用户头像
        ImageView mImgUserAvatarPhoto,mImgPostPicture;
        //收藏主题
        TextView mTvCollectionTitle;
        //收藏时间
        TextView mTvCollectionTime;

        public ViewHolder(View itemView) {
            super(itemView);

            mImgUserAvatarPhoto = itemView.findViewById(R.id.img_user_avatar_photo);
            mImgPostPicture = itemView.findViewById(R.id.img_post_picture);
            mTvCollectionTitle = itemView.findViewById(R.id.tv_collection_title);
            mTvCollectionTime = itemView.findViewById(R.id.tv_collection_time);
        }
    }

    public interface TouristInformationListener {

        void setEditTouristInfoListener(int position);
    }
}
