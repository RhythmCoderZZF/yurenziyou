package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.MineCollectionAlbumResponse;
import com.nbhysj.coupon.util.GlideUtil;

import java.util.List;

/**
 * @author hysj created at 2019/05/25.
 * description : 我的页面收藏(标签)适配器
 */
public class MineCollectionTagAdapter extends RecyclerView.Adapter<MineCollectionTagAdapter.ViewHolder> {

    List<MineCollectionAlbumResponse> collectionAlbumResponseList;
    private Context mContext;


    public MineCollectionTagAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setCollectionAlbumList(List<MineCollectionAlbumResponse> collectionAlbumResponseList) {

        this.collectionAlbumResponseList = collectionAlbumResponseList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_mine_collection_tag_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            GlideUtil.loadCornersTransformImage(mContext, "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3093674761,1038215453&fm=26&gp=0.jpg", 5, holder.mImageScenicSpots);
         /*   if(itemPosition == collectionAlbumResponseList.size()){

                holder.mLlytNewAlbum.setVisibility(View.VISIBLE);
                holder.mLlytAlbum.setVisibility(View.GONE);
            } else {
                holder.mLlytNewAlbum.setVisibility(View.GONE);
                holder.mLlytAlbum.setVisibility(View.VISIBLE);
                MineCollectionAlbumResponse mineCollectionAlbumResponse = collectionAlbumResponseList.get(itemPosition);
                String imageUrl = mineCollectionAlbumResponse.getAlbumImage();
                GlideUtil.loadCornersTransformImage(mContext, imageUrl, 2, holder.mImageAlbumPhoto);
                holder.mTvAlbumName.setText(mineCollectionAlbumResponse.getAlbumName());
                holder.mTvAlbumDescription.setText(mineCollectionAlbumResponse.getAlbumDes());
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //景点图片
        public ImageView mImageScenicSpots;


        public ViewHolder(View itemView) {
            super(itemView);

            mImageScenicSpots = itemView.findViewById(R.id.image_scenic_spots);

        }
    }
}
