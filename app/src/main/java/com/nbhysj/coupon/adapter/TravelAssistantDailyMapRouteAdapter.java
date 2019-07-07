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
import com.nbhysj.coupon.model.response.TripMapResponse;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;

import java.util.List;

/**
 * @author hysj created at 2019/05/17.
 * description: 行程助手每日路线规划适配器
 */
public class TravelAssistantDailyMapRouteAdapter extends RecyclerView.Adapter<TravelAssistantDailyMapRouteAdapter.ViewHolder> {

    List<TripMapResponse> mTripMapResponseList;
    private Context mContext;
    private int mPosition;
    public TravelAssistantDailyMapRouteAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setTravelAssistantDailyMapRouteList(List<TripMapResponse> tripMapResponseList) {

        this.mTripMapResponseList = mTripMapResponseList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_tavel_assistant_daily_route_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
            int position = itemPosition + 1;
            holder.mTvDayNumber.setText("第" + position + "天");

            holder.mTvDayNumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mPosition = itemPosition;
                    notifyDataSetChanged();
                }
            });

            if(itemPosition == mPosition){

                holder.mTvDayNumber.setTextColor(mContext.getResources().getColor(R.color.color_high_light_green));
            } else {
                holder.mTvDayNumber.setTextColor(mContext.getResources().getColor(R.color.color_text_black7));
            }

            if(itemPosition == 5-1){

                holder.mViewLine.setVisibility(View.GONE);

            } else {

                holder.mViewLine.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //天数索引
        TextView mTvDayNumber;
        View mViewLine;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvDayNumber = itemView.findViewById(R.id.tv_day_number);
            mViewLine = itemView.findViewById(R.id.view_line);
        }
    }


}
