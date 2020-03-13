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
import com.nbhysj.coupon.model.response.FavoritesBean;
import com.nbhysj.coupon.model.response.MineCollectionAlbumResponse;
import com.nbhysj.coupon.ui.AlbumDetailsActivity;
import com.nbhysj.coupon.ui.EditAlbumActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.RoundedImageView;

import java.util.List;

/**
 * @author hysj created at 2019/05/24.
 * description : 我的页面收藏(专辑)适配器
 */
public class MineCollectionAlbumAdapter extends RecyclerView.Adapter<MineCollectionAlbumAdapter.ViewHolder> {

    List<FavoritesBean> collectionAlbumResponseList;
    private Context mContext;
    private NewCollectionAlbumListener newCollectionAlbumListener;
    FavoritesBean mineCollectionAlbumResponse;
    //专辑id
    private int favoritesId;
    public MineCollectionAlbumAdapter(Context mContext, NewCollectionAlbumListener newCollectionAlbumListener) {

        this.mContext = mContext;
        this.newCollectionAlbumListener = newCollectionAlbumListener;
    }

    public void setCollectionAlbumList(List<FavoritesBean> collectionAlbumResponseList) {

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

            if (itemPosition == collectionAlbumResponseList.size())
            {

                holder.mLlytNewAlbum.setVisibility(View.VISIBLE);
                holder.mLlytAlbum.setVisibility(View.GONE);

            } else {
                mineCollectionAlbumResponse = collectionAlbumResponseList.get(itemPosition);

                holder.mLlytNewAlbum.setVisibility(View.GONE);
                holder.mLlytAlbum.setVisibility(View.VISIBLE);

                String title = mineCollectionAlbumResponse.getTitle();
                int albumNum = mineCollectionAlbumResponse.getNum();

                String imageUrl = mineCollectionAlbumResponse.getPhoto();
                GlideUtil.loadImage(mContext, imageUrl,holder.mImageAlbumPhoto);
                holder.mTvAlbumName.setText(title);
                holder.mTvAlbumCollectionNum.setText(String.valueOf(albumNum));

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
                    FavoritesBean mineCollectionAlbumResponse = collectionAlbumResponseList.get(itemPosition);
                    newCollectionAlbumListener.setEditCollectionAlbumListener(itemPosition,mineCollectionAlbumResponse);
                }
            });

            holder.mLlytCollectionAlbum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(itemPosition == collectionAlbumResponseList.size()) {

                        newCollectionAlbumListener.setNewCollectionAlbumListener();

                    } else {
                        favoritesId = collectionAlbumResponseList.get(itemPosition).getId();
                        Intent intent = new Intent();
                        intent.setClass(mContext, AlbumDetailsActivity.class);
                        intent.putExtra("favoritesId",favoritesId);
                        mContext.startActivity(intent);
                    }
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
        public RoundedImageView mImageAlbumPhoto;

        //专辑名字
        public TextView mTvAlbumName;

        //专辑描述
        public TextView mTvAlbumCollectionNum;

        public TextView mTvAlbumEditTag;

        private LinearLayout mLlytNewAlbum, mLlytAlbum;

        private LinearLayout mLlytCollectionAlbum;

        public ViewHolder(View itemView) {
            super(itemView);

            mImageAlbumPhoto = itemView.findViewById(R.id.img_album_photo);
            mTvAlbumName = itemView.findViewById(R.id.tv_album_name);
            mTvAlbumCollectionNum = itemView.findViewById(R.id.tv_album_collect_num);
            mTvAlbumEditTag = itemView.findViewById(R.id.tv_album_edit_tag);

            mLlytNewAlbum = itemView.findViewById(R.id.llyt_new_album);
            mLlytAlbum = itemView.findViewById(R.id.llyt_album);
            mLlytCollectionAlbum = itemView.findViewById(R.id.llyt_collection_album);

        }
    }

    public interface NewCollectionAlbumListener {

        void setNewCollectionAlbumListener();

        void setEditCollectionAlbumListener(int position,FavoritesBean mineCollectionAlbumResponse);
    }
}
