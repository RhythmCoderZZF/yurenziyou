package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.MchAlbumResponse;
import com.nbhysj.coupon.model.response.NetFriendAlbumResponse;
import com.nbhysj.coupon.ui.ImagePagerActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author hysj created on 2019/05/08.
 * description: 网友相册适配器
 */
public class NetFriendAlbumAdapter extends RecyclerView.Adapter<NetFriendAlbumAdapter.ViewHolder> {

    /**
     * 图片列表数据
     */
    private List<NetFriendAlbumResponse.NetFriendAlbumEntity> photoUrlList;

    private List<String> photoList = new ArrayList<>();

    private Context mContext;

    public NetFriendAlbumAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setPhotoUrlList(List<NetFriendAlbumResponse.NetFriendAlbumEntity> photoUrlList) {
        photoList.clear();
        this.photoUrlList = photoUrlList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_merchant_album_sub_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        try {
            NetFriendAlbumResponse.NetFriendAlbumEntity friendAlbumEntity = photoUrlList.get(position);
            String photoUrl = friendAlbumEntity.getPhoto();
            if(!TextUtils.isEmpty(photoUrl))
            {
                photoList.add(photoUrl);
            }

            GlideUtil.loadImage(mContext, friendAlbumEntity.getPhoto(), holder.mImgMerchantAlbum);
            holder.mImgMerchantAlbum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ImagePagerActivity.ImageSize imageSize = new ImagePagerActivity.ImageSize(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    ImagePagerActivity.startImagePagerActivity(mContext, photoList, position, imageSize);

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return photoUrlList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //商户相册
        @BindView(R.id.img_merchant_album_photo)
        RoundedImageView mImgMerchantAlbum;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
