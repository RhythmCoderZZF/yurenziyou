package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.MchGoodsBean;
import com.nbhysj.coupon.model.response.NearbyScenicSpotsResponse;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.RoundedImageView;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;

import java.util.List;

/**
 * @author hysj created at 2019/05/10.
 * description:酒店详情房间筛选适配器
 */
public class HotelDetailRoomAdapter extends RecyclerView.Adapter<HotelDetailRoomAdapter.ViewHolder> {


    List<MchGoodsBean> mchHotelGoodsList;
    private Context mContext;
    private HotelRoomItemListener hotelRoomItemListener;

    public HotelDetailRoomAdapter(Context mContext, HotelRoomItemListener hotelRoomItemListener) {

        this.mContext = mContext;
        this.hotelRoomItemListener = hotelRoomItemListener;
    }

    public void setMchHotelGoodsList(List<MchGoodsBean> mchHotelGoodsList) {

        this.mchHotelGoodsList = mchHotelGoodsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_hotel_room_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
            StringBuffer stringBuffer = new StringBuffer();
            MchGoodsBean mchGoodsBean = mchHotelGoodsList.get(itemPosition);
            String photoUrl = mchGoodsBean.getPhoto();
            String title = mchGoodsBean.getTitle();
            double marketPrice = mchGoodsBean.getMarketPrice();
            int breakfastStatus = mchGoodsBean.getBreakfastStatus();
            int windowStatus = mchGoodsBean.getWindowStatus();
            String bedInfo = mchGoodsBean.getBedInfo();

            holder.mTvHotelRoomTilte.setText(title);
            holder.mTvHotelRoomPrice.setText(String.valueOf(marketPrice));
            if(breakfastStatus == 0){

                stringBuffer.append("不含早餐 ");

            } else if(breakfastStatus == 1){

                stringBuffer.append("含早餐 ");
            }

            if(windowStatus == 0){

                stringBuffer.append("有窗");

            } else if(windowStatus == 1){

                stringBuffer.append("无窗");
            }

            holder.mTvHotelRoomDes.setText(stringBuffer.toString());
            GlideUtil.loadImage(mContext,photoUrl,holder.mImgHotelRoom);

            holder.mLlytHotelRoomItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    hotelRoomItemListener.setHotelRoomItemListener(itemPosition);

                }
            });
            stringBuffer.setLength(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mchHotelGoodsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //酒店房间类型
        TextView mTvHotelRoomTilte;
        //酒店房间图片
        RoundedImageView mImgHotelRoom;
        //酒店房间描述
        TextView mTvHotelRoomDes;
        //酒店可取消时间
        TextView mTvHotelBookCancelTimeLimit;
        //人均价格
        TextView mTvHotelRoomPrice;
        LinearLayout mLlytHotelRoomItem;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvHotelRoomTilte = itemView.findViewById(R.id.tv_hotel_room_title);
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
