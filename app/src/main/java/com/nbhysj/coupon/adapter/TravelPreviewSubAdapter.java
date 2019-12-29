package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.TravelPreviewBean;
import com.nbhysj.coupon.model.response.TripDetailsResponse;
import com.nbhysj.coupon.widget.RvSlideLayout;

import java.util.List;

/**
 * @author hysj created at 2019/05/16.
 * description:行程预览适配器
 */
public class TravelPreviewSubAdapter extends RecyclerView.Adapter<TravelPreviewSubAdapter.ViewHolder> {

    List<TripDetailsResponse.TripDetailsEntity> travelPreviewEntityList;
    private Context mContext;
    private RvSlideLayout mOpenMenu;
    private RvSlideLayout mScrollingMenu;
    private TripPlaceDeleteListener tripPlaceDeleteListener;
    public TravelPreviewSubAdapter(Context mContext,TripPlaceDeleteListener tripPlaceDeleteListener) {

        this.mContext = mContext;
        this.tripPlaceDeleteListener = tripPlaceDeleteListener;
    }

    public void setTravelPreviewList(List<TripDetailsResponse.TripDetailsEntity> travelPreviewEntityList) {

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

            TripDetailsResponse.TripDetailsEntity travelPreview = travelPreviewEntityList.get(itemPosition);
            int tripPlaceId = travelPreview.getTripPlaceId();
            String address = travelPreview.getAddress();
            String time = travelPreview.getTime();
            holder.mTvDestinationName.setText(address);
            holder.mTvTravelTime.setText(time);

            holder.mImgTripPlaceDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    tripPlaceDeleteListener.setTripPlaceDeleteCallBack(tripPlaceId,itemPosition);
                }
            });

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

        //行程点删除
        ImageView mImgTripPlaceDelete;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvDestinationName = itemView.findViewById(R.id.tv_trip_place);
            mTvTravelTime = itemView.findViewById(R.id.tv_travel_time);
            mImgTripPlaceDelete = itemView.findViewById(R.id.img_delete);

        }
    }

    public void setScrollingMenu(RvSlideLayout scrollingMenu) {
        mScrollingMenu = scrollingMenu;
    }

    public RvSlideLayout getScrollingMenu() {
        return mScrollingMenu;
    }

    public void closeOpenMenu() {
        if (mOpenMenu != null && mOpenMenu.isOpen()) {
            mOpenMenu.closeMenu();
            mOpenMenu = null;
        }
    }

    public void holdOpenMenu(RvSlideLayout rvSlideLayout) {
        mOpenMenu = rvSlideLayout;
    }

    public interface TripPlaceDeleteListener{

        void setTripPlaceDeleteCallBack(int tripPlaceId,int childPosition);
    }
}
