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
import com.nbhysj.coupon.model.response.CommentReceiveResponse;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.RoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author hysj created on 2019/04/14.
 * description:  我的页面收藏(全部)图片配器
 */
public class MineCollectionAllSubItemAdapter extends RecyclerView.Adapter<MineCollectionAllSubItemAdapter.ViewHolder> {

    /**
     * 我的收藏图片
     */
    private List<String>  mineCollectionPhotoUrlList;

    private Context mContext;
    private MineCollectionAllItemListener collectionAllItemListener;

    public MineCollectionAllSubItemAdapter(Context mContext,MineCollectionAllItemListener collectionAllItemListener) {

        this.mContext = mContext;
        this.collectionAllItemListener = collectionAllItemListener;
    }

    public void setMineCollectionPhotoUrlList(List<String> mineCollectionPhotoUrlList) {

        this.mineCollectionPhotoUrlList = mineCollectionPhotoUrlList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_mine_collection_all_sub_picture_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        try {

           String photoUrl = mineCollectionPhotoUrlList.get(position);
           GlideUtil.loadImage(mContext, photoUrl, holder.mImageCollectionAll);

            if (position == mineCollectionPhotoUrlList.size()) {

                holder.mViewMineCollectionAllSubPictureFoot.setVisibility(View.VISIBLE);
            } else {
                holder.mViewMineCollectionAllSubPictureFoot.setVisibility(View.GONE);
            }

            if (position == 0) {
                holder.mViewMineCollectionAllSubPictureHeader.setVisibility(View.VISIBLE);
            } else {
                holder.mViewMineCollectionAllSubPictureHeader.setVisibility(View.GONE);
            }

            holder.mLlytCollectionPictureItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    collectionAllItemListener.setMineCollectionAllItemListener(position);

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mineCollectionPhotoUrlList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //收藏有图片
        @BindView(R.id.image_collection_all)
        RoundedImageView mImageCollectionAll;
        @BindView(R.id.view_mine_collection_all_sub_picture_foot)
        View mViewMineCollectionAllSubPictureFoot;
        @BindView(R.id.view_mine_collection_all_sub_picture_header)
        View mViewMineCollectionAllSubPictureHeader;
        @BindView(R.id.llyt_collection_picture_item)
        LinearLayout mLlytCollectionPictureItem;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    interface MineCollectionAllItemListener{

        void setMineCollectionAllItemListener(int position);
    }
}
