package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.NearbyScenicSpotsResponse;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;

import java.util.List;

/**
 * @author hysj created at 2019/05/30.
 * description:民宿详情房间筛选适配器
 */
public class HomestayReservationAdapter extends RecyclerView.Adapter<HomestayReservationAdapter.ViewHolder> {

    List<NearbyScenicSpotsResponse> nearbyScenicSpotsList;
    private Context mContext;
    private HotelRoomItemListener hotelRoomItemListener;

    public HomestayReservationAdapter(Context mContext, HotelRoomItemListener hotelRoomItemListener) {

        this.mContext = mContext;
        this.hotelRoomItemListener = hotelRoomItemListener;
    }

    public void setNearbyScenicSpotsList(List<NearbyScenicSpotsResponse> nearbyScenicSpotsList) {

        this.nearbyScenicSpotsList = nearbyScenicSpotsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_homestay_reservation_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
            RequestOptions myOptions = new RequestOptions()
                    .transform(new GlideRoundTransform(mContext, 5));

            Glide.with(mContext)
                    .load("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1557554460&di=587cccfcf79487fa86575a004a4785fd&src=http://seopic.699pic.com/photo/50014/4961.jpg_wh1200.jpg")
                    .apply(myOptions)
                    .into(holder.mImgHotelRoom);

            holder.mLlytHotelRoomItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    hotelRoomItemListener.setHotelRoomItemListener(itemPosition);

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //酒店房间类型
        TextView mTvHotelRoomType;
        //酒店房间图片
        ImageView mImgHotelRoom;
        //酒店房间描述
        TextView mTvHotelRoomDes;
        //酒店可取消时间
        TextView mTvHotelBookCancelTimeLimit;
        //人均价格
        TextView mTvHotelRoomPrice;
        RelativeLayout mLlytHotelRoomItem;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvHotelRoomType = itemView.findViewById(R.id.tv_hotel_room_type);
            mImgHotelRoom = itemView.findViewById(R.id.image_hotel_room);
            mTvHotelRoomDes = itemView.findViewById(R.id.tv_hotel_room_des);
            mTvHotelBookCancelTimeLimit = itemView.findViewById(R.id.tv_hotel_book_cancel_time_limits);
            mTvHotelRoomPrice = itemView.findViewById(R.id.tv_hotel_room_price);
            mLlytHotelRoomItem = itemView.findViewById(R.id.llyt_hotel_room_item);
        }
    }

    public interface HotelRoomItemListener {

        void setHotelRoomItemListener(int position);
    }
}
