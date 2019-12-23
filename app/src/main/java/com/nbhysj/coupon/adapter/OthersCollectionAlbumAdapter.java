package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.FavoritesBean;
import com.nbhysj.coupon.ui.AlbumDetailsActivity;
import com.nbhysj.coupon.ui.OthersAlbumDetailsActivity;
import com.nbhysj.coupon.ui.OthersCollectionDetailsActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.RoundedImageView;

import java.util.List;

/**
 * @author hysj created at 2019/05/24.
 * description : 他人页面收藏(专辑)适配器
 */
public class OthersCollectionAlbumAdapter extends RecyclerView.Adapter<OthersCollectionAlbumAdapter.ViewHolder> {

    List<FavoritesBean> collectionAlbumResponseList;
    private Context mContext;
    private NewCollectionAlbumListener newCollectionAlbumListener;
    FavoritesBean mineCollectionAlbumResponse;
    //专辑id
    private int favoritesId;

    public OthersCollectionAlbumAdapter(Context mContext, NewCollectionAlbumListener newCollectionAlbumListener) {

        this.mContext = mContext;
        this.newCollectionAlbumListener = newCollectionAlbumListener;
    }

    public void setCollectionAlbumList(List<FavoritesBean> collectionAlbumResponseList) {

        this.collectionAlbumResponseList = collectionAlbumResponseList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gridview_others_collect_album_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
            mineCollectionAlbumResponse = collectionAlbumResponseList.get(itemPosition);

            String title = mineCollectionAlbumResponse.getTitle();
            int albumNum = mineCollectionAlbumResponse.getNum();

            String imageUrl = mineCollectionAlbumResponse.getPhoto();
            GlideUtil.loadImage(mContext, imageUrl, holder.mImageAlbumPhoto);
            holder.mTvAlbumName.setText(title);
            holder.mTvAlbumCollectionNum.setText(String.valueOf(albumNum));

            holder.mTvAlbumEditTag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    newCollectionAlbumListener.setEditCollectionAlbumListener(mineCollectionAlbumResponse);
                }
            });

            holder.mLlytAlbum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int favoritesId = mineCollectionAlbumResponse.getId();
                    newCollectionAlbumListener.setLookCollectionAlbumListener(favoritesId);
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
        return collectionAlbumResponseList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //专辑图片
        public RoundedImageView mImageAlbumPhoto;

        //专辑名字
        public TextView mTvAlbumName;

        //专辑描述
        public TextView mTvAlbumCollectionNum;

        public TextView mTvAlbumEditTag;

        private LinearLayout mLlytAlbum;

        public ViewHolder(View itemView) {
            super(itemView);

            mImageAlbumPhoto = itemView.findViewById(R.id.img_album_photo);
            mTvAlbumName = itemView.findViewById(R.id.tv_album_name);
            mTvAlbumCollectionNum = itemView.findViewById(R.id.tv_album_collect_num);
            mTvAlbumEditTag = itemView.findViewById(R.id.tv_album_edit_tag);
            mLlytAlbum = itemView.findViewById(R.id.llyt_album);

        }
    }

    public interface NewCollectionAlbumListener {

        void setLookCollectionAlbumListener(int favoritesId);

        void setEditCollectionAlbumListener(FavoritesBean mineCollectionAlbumResponse);
    }
}
