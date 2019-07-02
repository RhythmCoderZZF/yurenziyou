package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.library.banner.BannerLayout;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.FollowDetailBean;
import com.nbhysj.coupon.view.GlideImageView;

import java.util.List;

/**
 * @author hysj created at 2019/03/02.
 * description : 分享发布图片录音适配器
 */
public class PublishPictureSoundRecordAdapter extends RecyclerView.Adapter<PublishPictureSoundRecordAdapter.MzViewHolder> {

    private Context context;
    private List<String> publishPictureUrlList;
    private BannerLayout.OnBannerItemClickListener onBannerItemClickListener;

    public PublishPictureSoundRecordAdapter(Context context) {
        this.context = context;

    }

    public void setPublishPictureUrlList(List<String> publishPictureUrlList) {
        this.publishPictureUrlList = publishPictureUrlList;
    }

    @Override
    public PublishPictureSoundRecordAdapter.MzViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MzViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_banner_publish_picture_item, parent, false));
    }

    @Override
    public void onBindViewHolder(PublishPictureSoundRecordAdapter.MzViewHolder holder, final int position) {
       /* if (userEntityList == null || userEntityList.isEmpty())
            return;
        // final int pos = position % userEntityList.size();
        FollowDetailBean.UserEntity userEntity = userEntityList.get(position);
        String avatarUrl = userEntity.getAvatar();
        String description = userEntity.getDes();
        String username = userEntity.getUsername();
        holder.mTvName.setText(username);
        holder.mTvDescription.setText(description);
        holder.mImageInterestUserAvatar.loadCircle(avatarUrl);
        holder.mTvFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "关注" + username, Toast.LENGTH_SHORT).show();

            }
        });*/

        String pictureUrl = publishPictureUrlList.get(position);
        holder.mImagePublishPicture.load(pictureUrl);
    }

    @Override
    public int getItemCount() {
        if (publishPictureUrlList != null) {
            return publishPictureUrlList.size();
        }
        return 0;
    }


    class MzViewHolder extends RecyclerView.ViewHolder {
        GlideImageView mImagePublishPicture;

        MzViewHolder(View itemView) {
            super(itemView);
            mImagePublishPicture = itemView.findViewById(R.id.img_publish_picture);
        }
    }
}
