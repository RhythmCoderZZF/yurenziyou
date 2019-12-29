package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.NearbyScenicSpotsResponse;
import com.nbhysj.coupon.model.response.TravelPreviewBean;
import com.nbhysj.coupon.model.response.TripDetailsResponse;
import com.nbhysj.coupon.util.DateUtil;
import com.nbhysj.coupon.util.RadiusGradientSpanUtil;
import com.nbhysj.coupon.widget.RvSlideLayout;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;

import java.util.List;

/**
 * @author hysj created at 2019/05/16.
 * description:行程预览适配器
 */
public class TravelPreviewAdapter extends RecyclerView.Adapter<TravelPreviewAdapter.ViewHolder> {

    List<TripDetailsResponse.DetailsEntity> travelPreviewList;
    private Context mContext;

    private TripPlaceDeleteListener tripPlaceDeleteListener;
    public TravelPreviewAdapter(Context mContext,TripPlaceDeleteListener tripPlaceDeleteListener) {

        this.mContext = mContext;
        this.tripPlaceDeleteListener = tripPlaceDeleteListener;
    }

    public void setTravelPreviewList(List<TripDetailsResponse.DetailsEntity> travelPreviewList) {

        this.travelPreviewList = travelPreviewList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_trip_preview_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int groupPosition) {

        try {
            if (groupPosition == 0) {

                holder.mViewTop.setVisibility(View.GONE);
            } else {

                holder.mViewTop.setVisibility(View.VISIBLE);
            }

            TripDetailsResponse.DetailsEntity travelPreviewBean = travelPreviewList.get(groupPosition);
            String date = travelPreviewBean.getTripDate();

            String tripDate = null;
            String week = null;
            if(!TextUtils.isEmpty(date))
            {
                tripDate = date.replace("-", ".");
                 week = DateUtil.dateToWeek(date);

                holder.mTvTravelDate.setText(tripDate + " "+ week);
            } else {

                holder.mTvTravelDate.setText("");
            }
            int day = groupPosition + 1;
            holder.mTvTravelDays.setText(RadiusGradientSpanUtil.getRadiusGradientSpan("DAY." + day, 0xFF1DEB96, 0xFF0DDDF6));
            List<TripDetailsResponse.TripDetailsEntity> travelPreviewList = travelPreviewBean.getTripDetails();
            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
            layoutManager.setOrientation(layoutManager.VERTICAL);
            holder.mRvDestinationClassify.setLayoutManager(layoutManager);
            TravelPreviewSubAdapter travelPreviewSubAdapter = new TravelPreviewSubAdapter(mContext, new TravelPreviewSubAdapter.TripPlaceDeleteListener() {
                @Override
                public void setTripPlaceDeleteCallBack(int tripPlaceId,int childPosition) {

                    tripPlaceDeleteListener.setTripPlaceDeleteCallBack(tripPlaceId,groupPosition,childPosition);
                }
            });
            travelPreviewSubAdapter.setTravelPreviewList(travelPreviewList);
            holder.mRvDestinationClassify.setAdapter(travelPreviewSubAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return travelPreviewList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //行程天数
        TextView mTvTravelDays;
        //行程日期
        TextView mTvTravelDate;
        //目的地分类
        RecyclerView mRvDestinationClassify;
        View mViewTop;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvTravelDays = itemView.findViewById(R.id.tv_travel_days);
            mTvTravelDate = itemView.findViewById(R.id.tv_travel_date);
            mRvDestinationClassify = itemView.findViewById(R.id.rv_destination_classify);
            mViewTop = itemView.findViewById(R.id.view_gradient_top);
        }
    }

    public interface TripPlaceDeleteListener{

        void setTripPlaceDeleteCallBack(int tripPlaceId,int groupPosition,int childPosition);
    }

}
