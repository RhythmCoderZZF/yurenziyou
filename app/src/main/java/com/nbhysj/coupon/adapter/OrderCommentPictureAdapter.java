package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.util.BitmapUtils;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.GlideImageView;
import com.nbhysj.coupon.view.RoundedImageView;

import java.util.ArrayList;
import java.util.List;


/**
 * created by hysj on 2019/10/07.
 * description: 订单评论图片适配器
 */
public class OrderCommentPictureAdapter extends RecyclerView.Adapter<OrderCommentPictureAdapter.ViewHolder> {

    private Context mContext;

    private OrderCommentPictureListener orderCommentPictureListener;

    private boolean isPhotoSelect;

    private List<String> filePictureList;

    public OrderCommentPictureAdapter(Context mContext,OrderCommentPictureListener orderCommentPictureListener) {

        this.mContext = mContext;
        this.orderCommentPictureListener = orderCommentPictureListener;
    }

    public void setOrderCommentPictureList(List<String> fileList) {

        this.filePictureList = fileList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View view = View.inflate(parent.getContext(), R.layout.list_survey_type_answer_item, null);

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_order_comment_picture_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        try {

            if (position == 0) {

                holder.mImgPhotopraph.setImageResource(R.mipmap.icon_upload_photo);
                holder.mImgPhotopraphDelete.setVisibility(View.GONE);
            } else {
                String photoUrl = filePictureList.get(position - 1);
                holder.mImgPhotopraphDelete.setVisibility(View.VISIBLE);
                GlideUtil.loadImage(mContext,photoUrl,holder.mImgPhotopraph);

            }
            holder.mImgPhotopraph.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (position == 0) {
                        orderCommentPictureListener.setPhotoSelectListener(position);
                    }
                }
            });

            holder.mImgPhotopraphDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    orderCommentPictureListener.setPhotoDeleteListener(position - 1);

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return filePictureList.size() + 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public RoundedImageView mImgPhotopraph;

        //照片删除
        ImageView mImgPhotopraphDelete;

        public ViewHolder(View itemView) {
            super(itemView);

            mImgPhotopraph = itemView.findViewById(R.id.img_comment_photo);
            mImgPhotopraphDelete = itemView.findViewById(R.id.img_comment_photo_delete);

        }
    }

    /**
     * 适配监听
     */
    public interface OrderCommentPictureListener {

        void setPhotoSelectListener(int position);

        void setPhotoDeleteListener(int position);

    }
}
