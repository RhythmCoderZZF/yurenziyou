package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.MineCollectionAlbumResponse;
import com.nbhysj.coupon.util.GlideUtil;

import java.util.List;

/**
 * @author hysj created at 2019/05/24.
 * description : 我的页面收藏(专辑)适配器
 */
public class MineCollectionAlbumAdapter extends RecyclerView.Adapter<MineCollectionAlbumAdapter.ViewHolder> {

    List<MineCollectionAlbumResponse> collectionAlbumResponseList;
    private Context mContext;
    private NewCollectionAlbumListener newCollectionAlbumListener;


    public MineCollectionAlbumAdapter(Context mContext, NewCollectionAlbumListener newCollectionAlbumListener) {

        this.mContext = mContext;
        this.newCollectionAlbumListener = newCollectionAlbumListener;
    }

    public void setCollectionAlbumList(List<MineCollectionAlbumResponse> collectionAlbumResponseList) {

        this.collectionAlbumResponseList = collectionAlbumResponseList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gridview_mine_collect_album_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
            MineCollectionAlbumResponse mineCollectionAlbumResponse = collectionAlbumResponseList.get(itemPosition);
            ;
            if (itemPosition == collectionAlbumResponseList.size()) {

                holder.mLlytNewAlbum.setVisibility(View.VISIBLE);
                holder.mLlytAlbum.setVisibility(View.GONE);
            } else {
                holder.mLlytNewAlbum.setVisibility(View.GONE);
                holder.mLlytAlbum.setVisibility(View.VISIBLE);

                String imageUrl = mineCollectionAlbumResponse.getAlbumImage();
                GlideUtil.loadCornersTransformImage(mContext, imageUrl, 2, holder.mImageAlbumPhoto);
                holder.mTvAlbumName.setText(mineCollectionAlbumResponse.getAlbumName());
                holder.mTvAlbumDescription.setText(mineCollectionAlbumResponse.getAlbumDes());
            }

            holder.mLlytNewAlbum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    newCollectionAlbumListener.setNewCollectionAlbumListener();
                }
            });
            holder.mTvAlbumEditTag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    newCollectionAlbumListener.setEditCollectionAlbumListener(mineCollectionAlbumResponse);
                }
            });

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
        return collectionAlbumResponseList.size() + 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //专辑图片
        public ImageView mImageAlbumPhoto;

        //专辑名字
        public TextView mTvAlbumName;

        //专辑描述
        public TextView mTvAlbumDescription;

        public TextView mTvAlbumEditTag;

        private LinearLayout mLlytNewAlbum, mLlytAlbum;

        public ViewHolder(View itemView) {
            super(itemView);

            mImageAlbumPhoto = itemView.findViewById(R.id.img_album_photo);
            mTvAlbumName = itemView.findViewById(R.id.tv_album_name);
            mTvAlbumDescription = itemView.findViewById(R.id.tv_album_description);
            mTvAlbumEditTag = itemView.findViewById(R.id.tv_album_edit_tag);

            mLlytNewAlbum = itemView.findViewById(R.id.llyt_new_album);
            mLlytAlbum = itemView.findViewById(R.id.llyt_album);

        }
    }

    public interface NewCollectionAlbumListener {

        void setNewCollectionAlbumListener();

        void setEditCollectionAlbumListener(MineCollectionAlbumResponse mineCollectionAlbumResponse);
    }
}
