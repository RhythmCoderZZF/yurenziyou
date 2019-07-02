package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.TravelPreviewBean;

import java.util.List;

/**
 * @author hysj created at 2019/05/16.
 * description:行程预览适配器
 */
public class TravelPreviewSubAdapter extends RecyclerView.Adapter<TravelPreviewSubAdapter.ViewHolder> {

    List<TravelPreviewBean.TravelPreviewEntity> travelPreviewEntityList;
    private Context mContext;

    public TravelPreviewSubAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setTravelPreviewList(List<TravelPreviewBean.TravelPreviewEntity> travelPreviewEntityList) {

        this.travelPreviewEntityList = travelPreviewEntityList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_trip_preview_sub_destination_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            TravelPreviewBean.TravelPreviewEntity travelPreview = travelPreviewEntityList.get(itemPosition);
            String destination = travelPreview.getDestination();
            String time = travelPreview.getTime();
            holder.mTvDestinationName.setText(destination);
            holder.mTvTravelTime.setText(time);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return travelPreviewEntityList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //目的地名字
        TextView mTvDestinationName;
        //行程日期
        TextView mTvTravelTime;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvDestinationName = itemView.findViewById(R.id.tv_destination_name);
            mTvTravelTime = itemView.findViewById(R.id.tv_travel_time);
        }
    }
}
