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
import com.nbhysj.coupon.model.response.MchGoodsBean;
import com.nbhysj.coupon.model.response.NearbyScenicSpotsResponse;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.Tools;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;

import java.util.List;

/**
 * @author hysj created at 2019/05/30.
 * description:民宿详情房间筛选适配器
 */
public class HomestayReservationAdapter extends RecyclerView.Adapter<HomestayReservationAdapter.ViewHolder> {

    List<MchGoodsBean> mchHomestayGoodsList;
    private Context mContext;
    private HotelRoomItemListener hotelRoomItemListener;

    public HomestayReservationAdapter(Context mContext, HotelRoomItemListener hotelRoomItemListener) {

        this.mContext = mContext;
        this.hotelRoomItemListener = hotelRoomItemListener;
    }

    public void setHomestayReservationList(List<MchGoodsBean> mchHomestayGoodsList) {

        this.mchHomestayGoodsList = mchHomestayGoodsList;
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
            StringBuffer stringBuffer = new StringBuffer();
            MchGoodsBean mchGoodsBean = mchHomestayGoodsList.get(itemPosition);
            String photoUrl = mchGoodsBean.getPhoto();
            String title = mchGoodsBean.getTitle();
            double defaultPrice = mchGoodsBean.getDefaultPrice();
            int breakfastStatus = mchGoodsBean.getBreakfastStatus();
            int windowStatus = mchGoodsBean.getWindowStatus();
            String bedInfo = mchGoodsBean.getBedInfo();

            holder.mTvHomestayTitle.setText(title);
            holder.mTvHomestayRoomPrice.setText("¥ " + Tools.getTwoDecimalPoint(defaultPrice));
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

            holder.mRlytHomestayRoomItem.setOnClickListener(new View.OnClickListener() {
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
        return mchHomestayGoodsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //民宿标题
        TextView mTvHomestayTitle;
        //酒店房间图片
        ImageView mImgHotelRoom;
        //酒店房间描述
        TextView mTvHotelRoomDes;
        //酒店可取消时间
        TextView mTvHotelBookCancelTimeLimit;
        //民宿价格
        TextView mTvHomestayRoomPrice;
        RelativeLayout mRlytHomestayRoomItem;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvHomestayTitle = itemView.findViewById(R.id.tv_homestay_title);
            mImgHotelRoom = itemView.findViewById(R.id.image_hotel_room);
            mTvHotelRoomDes = itemView.findViewById(R.id.tv_hotel_room_des);
            mTvHotelBookCancelTimeLimit = itemView.findViewById(R.id.tv_hotel_book_cancel_time_limits);
            mTvHomestayRoomPrice = itemView.findViewById(R.id.tv_homestay_room_price);
            mRlytHomestayRoomItem = itemView.findViewById(R.id.rlyt_homestay_room_item);
        }
    }

    public interface HotelRoomItemListener {

        void setHotelRoomItemListener(int position);
    }
}
