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

    List<List<TripMapResponse>> mTripMapResponseList;
    private Context mContext;
    private TravelAssistantDailyMapRouteListener tripDailyMapRouteListener;

    private int mPosition;
    public TravelAssistantDailyMapRouteAdapter(Context mContext,TravelAssistantDailyMapRouteListener tripDailyMapRouteListener) {

        this.mContext = mContext;
        this.tripDailyMapRouteListener = tripDailyMapRouteListener;
    }

    public void setTravelAssistantDailyMapRouteList(List<List<TripMapResponse>> tripMapResponseList) {

        this.mTripMapResponseList = tripMapResponseList;
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

            if(itemPosition == 0){

                holder.mTvDayNumber.setText("全部");

            } else {

                holder.mTvDayNumber.setText("第" + itemPosition + "天");

                holder.mTvDayNumber.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPosition = itemPosition;
                        List<TripMapResponse> tripMapResponseList = mTripMapResponseList.get(mPosition - 1);
                        tripDailyMapRouteListener.setTravelAssistantDailyMapRouteListener(tripMapResponseList);
                    }
                });
            }

            if (itemPosition == mPosition) {

                holder.mTvDayNumber.setTextColor(mContext.getResources().getColor(R.color.color_high_light_green));
            } else {
                holder.mTvDayNumber.setTextColor(mContext.getResources().getColor(R.color.color_text_black7));
            }

            if(itemPosition == mTripMapResponseList.size()){

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
        return mTripMapResponseList.size() + 1;
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

    public interface TravelAssistantDailyMapRouteListener{

        void setTravelAssistantDailyMapRouteListener(List<TripMapResponse> tripMapResponseList);
    }
}
