package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.CommentReceiveResponse;
import com.nbhysj.coupon.model.response.MchAlbumResponse;
import com.nbhysj.coupon.model.response.MerchantAlbumResponse;
import com.nbhysj.coupon.view.GlideImageView;
import com.nbhysj.coupon.view.MyRecycleView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author hysj created on 2019/04/14.
 * description: 商家相册适配器
 */
public class MerchentAlbumItemAdapter extends RecyclerView.Adapter<MerchentAlbumItemAdapter.ViewHolder> {

    /**
     * 图片列表数据
     */
    private List<MchAlbumResponse.CateWithPhotosEntity> merchantAlbumList;

    private Context mContext;


    public MerchentAlbumItemAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setMerchantAlbumList(List<MchAlbumResponse.CateWithPhotosEntity> merchantAlbumList) {

        this.merchantAlbumList = merchantAlbumList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_merchant_album_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        try {
            MchAlbumResponse.CateWithPhotosEntity merchantAlbum = merchantAlbumList.get(position);
            holder.mTvMerchantAlbumName.setText(merchantAlbum.getTitle());
            List<MchAlbumResponse.PhotosVOSEntity> merchantPhotoList = merchantAlbum.getPhotosVOS();
            GridLayoutManager gridLayoutManager =
                    new GridLayoutManager(mContext, 2
                    );
            holder.mRvMerchantAlbum.setLayoutManager(gridLayoutManager);
            // linearLayoutManager.setAutoMeasureEnabled(true);
            MerchentAlbumSubItemAdapter merchentAlbumSubItemAdapter = new MerchentAlbumSubItemAdapter(mContext);
            merchentAlbumSubItemAdapter.setPhotoUrlList(merchantPhotoList);
            holder.mRvMerchantAlbum.setAdapter(merchentAlbumSubItemAdapter);
            holder.mRvMerchantAlbum.addItemDecoration(new RecyclerItemDecoration(12, 2));
          /*  List<CommentReceiveResponse.CommentEntity> commentEntityList =  commentReceive.getCommentList();
            CommentReceivedSubItemAdapter commentReceivedSubItemAdapter = new CommentReceivedSubItemAdapter(mContext);
            commentReceivedSubItemAdapter.setCommentReceivedSubItemList(commentEntityList);
            holder.mRvCommentContent.setAdapter(commentReceivedSubItemAdapter);
            String avatarUrl = commentReceive.getAvatar();
            String commentPictrueUrl = commentReceive.getCommentPictrue();
            holder.mImgUserAvatar.loadCircle(avatarUrl,R.mipmap.icon_placeholder_image);
            Glide.with(mContext).load(commentPictrueUrl).into(holder.mImgCommentPictrue);*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return merchantAlbumList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //商户相册
        @BindView(R.id.rv_merchant_album)
        MyRecycleView mRvMerchantAlbum;
        //相册名
        @BindView(R.id.tv_merchant_album_name)
        TextView mTvMerchantAlbumName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class RecyclerItemDecoration extends RecyclerView.ItemDecoration {
        private int itemSpace;
        private int itemNum;

        /**
         * @param itemSpace item间隔
         * @param itemNum   每行item的个数
         */
        public RecyclerItemDecoration(int itemSpace, int itemNum) {
            this.itemSpace = itemSpace;
            this.itemNum = itemNum;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            // outRect.bottom = itemSpace;
                /*if (parent.getChildLayoutPosition(view)%itemNum == 0){  //parent.getChildLayoutPosition(view) 获取view的下标
                    outRect.left = 0;
                } else {
                    outRect.left = itemSpace;
                }*/
            outRect.left = itemSpace;
            outRect.right = itemSpace;
            // outRect.bottom = itemSpace;
            //outRect.right = itemSpace;
        }
    }
}
