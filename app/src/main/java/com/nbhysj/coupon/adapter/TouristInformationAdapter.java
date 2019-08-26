package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.NearbyScenicSpotsResponse;
import com.nbhysj.coupon.model.response.OrderSubmitInitResponse;
import com.nbhysj.coupon.view.StarBarView;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;

import java.util.List;

/**
 * @author hysj created at 2019/5/05.
 * description:游客信息适配器
 */
public class TouristInformationAdapter extends RecyclerView.Adapter<TouristInformationAdapter.ViewHolder> {

    List<OrderSubmitInitResponse.TravellersEntity> touristInfoList;
    private Context mContext;
    private TouristInformationListener touristInformationListener;

    private int mTouristSelectPosition;
    public TouristInformationAdapter(Context mContext, TouristInformationListener touristInformationListener) {

        this.mContext = mContext;
        this.touristInformationListener = touristInformationListener;
    }

    public void setTouristInfoList(List<OrderSubmitInitResponse.TravellersEntity> touristInfoList) {

        this.touristInfoList = touristInfoList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_flowlayout_tag_tourist_info, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
            OrderSubmitInitResponse.TravellersEntity travellersEntity;
            if (itemPosition == touristInfoList.size()) {
                holder.mTvTouristName.setText("新增>");
                holder.mTvTouristName.setTextColor(mContext.getResources().getColor(R.color.txt_font_black2));
                holder.mTvTouristName.setBackgroundResource(R.drawable.bg_stroke_radius_two_light_gray_shape);
              //  holder.mTvTouristName.setPadding(20, 8, 20, 8);
            } else {
                travellersEntity = touristInfoList.get(itemPosition);
                String touristName = travellersEntity.getRealname();
                holder.mTvTouristName.setText(touristName);
                holder.mTvTouristName.setTextColor(mContext.getResources().getColor(R.color.white));
                holder.mTvTouristName.setBackgroundResource(R.drawable.bg_blue_green_gradient_radius_two);
               // holder.mTvTouristName.setPadding(20, 8, 20, 8);
            }

            holder.mTvTouristName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (itemPosition == touristInfoList.size()) {

                        touristInformationListener.setTouristInformationListener(itemPosition, true);

                    } else {
                        mTouristSelectPosition = itemPosition;
                        touristInformationListener.setTouristInformationListener(itemPosition, false);
                        notifyDataSetChanged();
                    }
                }
            });

            //游客选中
            if(mTouristSelectPosition == itemPosition){

                holder.mTvTouristName.setTextColor(mContext.getResources().getColor(R.color.white));
                holder.mTvTouristName.setBackgroundResource(R.drawable.bg_blue_green_gradient_radius_two);

            } else {

                holder.mTvTouristName.setTextColor(mContext.getResources().getColor(R.color.txt_font_black2));
                holder.mTvTouristName.setBackgroundResource(R.drawable.bg_stroke_radius_two_light_gray_shape);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return touristInfoList.size() + 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //游客名字
        TextView mTvTouristName;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvTouristName = itemView.findViewById(R.id.tv_tourist_name);
        }
    }

    public interface TouristInformationListener {

        void setTouristInformationListener(int position, boolean isAddTourists);
    }
}
