package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.view.GlideImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/4/7.
 * description : 酒店适配器
 */
public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder> {

    List<String> mQuestionContentList;
    private Context mContext;

    public HotelAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setHotelContentList(List<String> questionContentList) {

        this.mQuestionContentList = questionContentList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_hotel_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            holder.mImgHotel.load("http://img4.imgtn.bdimg.com/it/u=2146046871,2611785107&fm=26&gp=0.jpg", R.mipmap.icon_placeholder_image, 30);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_hotel_photo)
        GlideImageView mImgHotel;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
