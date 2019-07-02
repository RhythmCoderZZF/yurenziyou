package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.MchAlbumResponse;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author hysj created on 2019/05/08.
 * description: 商家相册适配器
 */
public class MerchentAlbumSubItemAdapter extends RecyclerView.Adapter<MerchentAlbumSubItemAdapter.ViewHolder> {

    /**
     * 图片列表数据
     */
    private List<MchAlbumResponse.PhotosVOSEntity> photoUrlList;

    private Context mContext;


    public MerchentAlbumSubItemAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setPhotoUrlList(List<MchAlbumResponse.PhotosVOSEntity> photoUrlList) {

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
            MchAlbumResponse.PhotosVOSEntity photoUrl = photoUrlList.get(position);
            GlideUtil.loadCornersTransformImage(mContext, photoUrl.getPhoto(), 13, holder.mImgMerchantAlbum);
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
        ImageView mImgMerchantAlbum;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
