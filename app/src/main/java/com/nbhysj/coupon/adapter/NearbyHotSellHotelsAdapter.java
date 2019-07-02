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
import com.nbhysj.coupon.view.StarBarView;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;

import java.util.List;

/**
 * @author hysj created at 2019/05/13.
 * description:附近热销酒店适配器
 */
public class NearbyHotSellHotelsAdapter extends RecyclerView.Adapter<NearbyHotSellHotelsAdapter.ViewHolder> {


    List<NearbyScenicSpotsResponse> nearbyScenicSpotsList;
    private Context mContext;

    public NearbyHotSellHotelsAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setNearbyScenicSpotsList(List<NearbyScenicSpotsResponse> nearbyScenicSpotsList) {

        this.nearbyScenicSpotsList = nearbyScenicSpotsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_hot_selling_hotel_nearby_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
          /*  NearbyScenicSpotsResponse nearbyScenicSpots = nearbyScenicSpotsList.get(itemPosition);
            holder.mTvHotelName.setText(nearbyScenicSpots.getScenicSpotsName());
            holder.mTvHotelType.setText(nearbyScenicSpots.getScenicSpotsScore() + "分");
            holder.mStarBarScenicSpots.setIntegerMark(true);
            holder.mStarBarScenicSpots.setStarMark(nearbyScenicSpots.getStarLevel());
            holder.mTvScenicSpotsDistance.setText("距景点" + nearbyScenicSpots.getScenicSpotsDistance() + "m");
            holder.mTvPerCapitaPrice.setText(nearbyScenicSpots.getScenicSpotsTicketPrice());*/

            RequestOptions myOptions = new RequestOptions()
                    .transform(new GlideRoundTransform(mContext, 5));

            Glide.with(mContext)
                    .load("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1555639803&di=038b9646f3b207fcf7ed84a41c72a85b&src=http://b-ssl.duitang.com/uploads/item/20182/21/2018221142159_MZ33z.jpeg")
                    .apply(myOptions)
                    .into(holder.mImgHotelRoom);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //附近酒店名字
        TextView mTvHotelName;
        //酒店房间图片
        ImageView mImgHotelRoom;
        //酒店类型
        TextView mTvHotelType;
        //评分星级
        StarBarView mStarBarScenicSpots;
        //距离景区距离
        TextView mTvScenicSpotsDistance;
        //人均价格
        TextView mTvPerCapitaPrice;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvHotelName = itemView.findViewById(R.id.tv_hotel_name);
            mImgHotelRoom = itemView.findViewById(R.id.image_hotel_room);
            mTvHotelType = itemView.findViewById(R.id.tv_hotel_type);
            mStarBarScenicSpots = itemView.findViewById(R.id.starbar_scenic_spots);
            mTvScenicSpotsDistance = itemView.findViewById(R.id.tv_scenic_spots_distance);
            mTvPerCapitaPrice = itemView.findViewById(R.id.tv_per_capita_price);
        }
    }
}
