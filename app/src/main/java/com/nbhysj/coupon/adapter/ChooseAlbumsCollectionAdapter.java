package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.FavoritesBean;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.RoundedImageView;

import java.util.List;

public class ChooseAlbumsCollectionAdapter extends RecyclerView.Adapter<ChooseAlbumsCollectionAdapter.CardHolder> {

    private List<FavoritesBean> collectionAlbumList;
    private Context mContext;
    private ChooseAlbumsCollectionListener chooseAlbumsCollectionListener;

    public ChooseAlbumsCollectionAdapter(Context mContext, ChooseAlbumsCollectionListener chooseAlbumsCollectionListener) {
        this.mContext = mContext;

        this.chooseAlbumsCollectionListener = chooseAlbumsCollectionListener;
    }

    public void setChooseAlbumsCollectionList(List<FavoritesBean> collectionAlbumList){

        this.collectionAlbumList = collectionAlbumList;
    }

    @Override
    public CardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_choose_albums_collection_item, parent, false);
        return new CardHolder(view);
    }

    @Override
    public void onBindViewHolder(final CardHolder holder, final int position) {
        FavoritesBean collectionAlbum = collectionAlbumList.get(position);
        String imageUrl = collectionAlbum.getPhoto();
        String albumName = collectionAlbum.getTitle();

        GlideUtil.loadImage(mContext, imageUrl,  holder.mImgChooseAlbum);
        holder.mTvAlbumName.setText(albumName);

        holder.mLlytChooseAlbumItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chooseAlbumsCollectionListener.setChooseAlbumsCollectionListener(collectionAlbum);
/*
                Intent intent = new Intent();
                intent.setClass(mContext, NearbyCardDetailActivity.class);
                intent.putExtra("imageUrl",bean.getUrl());
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeCustomAnimation(mContext,R.anim.activity_anim_loading,R.anim.actionsheet_dialog_out);
                ActivityCompat.startActivity(mContext, intent, compat.toBundle());*/
                // mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return collectionAlbumList.size();
    }

    static class CardHolder extends RecyclerView.ViewHolder {
        RoundedImageView mImgChooseAlbum;
        TextView mTvAlbumName;
        LinearLayout mLlytChooseAlbumItem;

        public CardHolder(View itemView) {
            super(itemView);
            mImgChooseAlbum = itemView.findViewById(R.id.image_choose_album_item);
            mTvAlbumName = itemView.findViewById(R.id.tv_album_name);
            mLlytChooseAlbumItem = itemView.findViewById(R.id.llyt_choose_albums_item);

        }
    }

    public interface ChooseAlbumsCollectionListener {

        void setChooseAlbumsCollectionListener(FavoritesBean collectionAlbum);
    }
}
