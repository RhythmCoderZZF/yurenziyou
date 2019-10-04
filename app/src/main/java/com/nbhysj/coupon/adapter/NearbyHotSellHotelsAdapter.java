package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Enum.MchTypeEnum;
import com.nbhysj.coupon.model.response.HotelBean;
import com.nbhysj.coupon.model.response.MchDetailsResponse;
import com.nbhysj.coupon.model.response.NearbyScenicSpotsResponse;
import com.nbhysj.coupon.ui.HomestayDetailActivity;
import com.nbhysj.coupon.ui.HotelDetailsActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.RoundedImageView;
import com.nbhysj.coupon.view.StarBarView;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;

import java.util.List;

/**
 * @author hysj created at 2019/09/17.
 * description:附近热销酒店适配器
 */
public class NearbyHotSellHotelsAdapter extends RecyclerView.Adapter<NearbyHotSellHotelsAdapter.ViewHolder> {

    StringBuffer stringBuffer = new StringBuffer();
    List<HotelBean> hotelList;
    private Context mContext;

    public NearbyHotSellHotelsAdapter(Context mContext) {

        this.mContext = mContext;
        stringBuffer.setLength(0);
    }

    public void setHotSellHotelList(List<HotelBean> hotelList) {

        this.hotelList = hotelList;
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

            HotelBean hotelBean = hotelList.get(itemPosition);
            int mchId = hotelBean.getId();
            String hotelPhotoUrl = hotelBean.getPhoto();
            String distance = hotelBean.getDistance();
            String checkinTime = hotelBean.getCheckinTime();
            String leaveTime = hotelBean.getLeaveTime();
            String nearByMchType = hotelBean.getType();
            String mchType2 = hotelBean.getType2();
            float score = hotelBean.getScore();
            String title = hotelBean.getTitle();
            double price = hotelBean.getPrice();
            List<HotelBean.ServiceEntity> serviceList = hotelBean.getServiceJson();

            GlideUtil.loadImage(mContext, hotelPhotoUrl, holder.mImgHotelRoom);
            if (!TextUtils.isEmpty(distance)) {
                holder.mTvLocationDistance.setText(distance);
            }

            holder.mStarBarScenicSpots.setIntegerMark(false);
            holder.mStarBarScenicSpots.setStarMark(score);

                holder.mTvScore.setText(String.valueOf(score) + "分");
                if(!TextUtils.isEmpty(title)) {
                    holder.mTvHotelName.setText(title);
                }

            holder.mTvPerCapitaPrice.setText(String.valueOf(price));

            for(int i = 0;i < serviceList.size();i++)
            {
                String serviceTitle = serviceList.get(i).getTitle();
                if(!TextUtils.isEmpty(serviceTitle))
                {
                    stringBuffer.append(serviceTitle);
                }
            }

            holder.mHotelInfoDes.setText("入住时间" + checkinTime + "-" + leaveTime + stringBuffer.toString());

            holder.mLlytHotSellHotelItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String mchType = MchTypeEnum.MCH_HOTEL.getValue();
                    String mchHotelType = MchTypeEnum.MCH_HOTEL1.getValue();
                    String mchHomestayType = MchTypeEnum.MCH_HOTEL2.getValue();
                    if(nearByMchType != null && mchType2 != null) {
                        if (nearByMchType.equals(mchType))
                        {
                            if (mchType2.equals(mchHotelType))
                            {

                                Intent intent = new Intent();
                                intent.putExtra("mchId", mchId);
                                intent.setClass(mContext, HotelDetailsActivity.class);
                                mContext.startActivity(intent);
                            } else if(mchType2.equals(mchHomestayType)){
                                Intent intent = new Intent();
                                intent.putExtra("mchId", mchId);
                                intent.setClass(mContext, HomestayDetailActivity.class);
                                mContext.startActivity(intent);
                            }
                        }
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return hotelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //附近酒店名字
        TextView mTvHotelName;
        //酒店房间图片
        RoundedImageView mImgHotelRoom;
        //酒店类型
        TextView mTvHotelType;
        //评分星级
        StarBarView mStarBarScenicSpots;
        //距离距离
        TextView mTvLocationDistance;
        //人均价格
        TextView mTvPerCapitaPrice;
        //分数
        TextView mTvScore;
        //酒店信息描述
        TextView mHotelInfoDes;
        LinearLayout mLlytHotSellHotelItem;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvHotelName = itemView.findViewById(R.id.tv_hotel_name);
            mImgHotelRoom = itemView.findViewById(R.id.image_hotel_room);
            mTvHotelType = itemView.findViewById(R.id.tv_hotel_type);
            mStarBarScenicSpots = itemView.findViewById(R.id.starbar_scenic_spots);
            mTvLocationDistance = itemView.findViewById(R.id.tv_location_distance);
            mTvPerCapitaPrice = itemView.findViewById(R.id.tv_per_capita_price);
            mTvScore = itemView.findViewById(R.id.tv_score);
            mHotelInfoDes = itemView.findViewById(R.id.tv_hotel_info_des);
            mLlytHotSellHotelItem = itemView.findViewById(R.id.llyt_hot_sell_hotel_item);
        }
    }
}
