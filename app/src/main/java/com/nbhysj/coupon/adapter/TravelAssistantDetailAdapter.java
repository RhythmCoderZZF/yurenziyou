package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
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
import com.nbhysj.coupon.common.Enum.TravelAssistantDetailTypeEnum;
import com.nbhysj.coupon.model.response.NearbyScenicSpotsResponse;
import com.nbhysj.coupon.model.response.TripDetailsResponse;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/05/17.
 * description: 行程助手详情页适配器
 */
public class TravelAssistantDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    TravelAssistantDetailItemListener travelAssistantDetailItemListener;
    // MCH("商户","MCH"):
    // MCH_SCENIC("景点","MCH_SCENIC"),
    // MCH_FOOD("美食","MCH_FOOD"),
    // MCH_HOTEL("酒店","MCH_HOTEL"),
    // MCH_RECREATION("休闲娱乐","MCH_RECREATION"),
    // TRAFFIC("交通","TRAFFIC"):
    //CAR("客运","CAR")
    //TRAIN("火车",TRAIN)
    //NOTE("备注",NOTE)

    private int MCH_SCENIC = 1;//景点
    private int MCH_FOOD = 2;//美食
    private int MCH_HOTEL = 3;//酒店
    private int MCH_RECREATION = 4;//互动
    private int TRAFFIC = 5;//交通   CAR("客运","CAR") TRAIN("火车",TRAIN)
    private int NOTE = 6;//备注

    List<TripDetailsResponse.TripDetailsEntity> detailsEntityList;
    private Context mContext;

    public TravelAssistantDetailAdapter(Context mContext, TravelAssistantDetailItemListener travelAssistantDetailItemListener) {

        this.mContext = mContext;
        this.travelAssistantDetailItemListener = travelAssistantDetailItemListener;
    }

    public void setTravelAssistantDetailList(List<TripDetailsResponse.TripDetailsEntity> detailsEntityList) {

        this.detailsEntityList = detailsEntityList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        try {
            if (viewType == MCH_SCENIC) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_travel_assistant_detail_scenic_spots_item, parent, false);//解决宽度不能铺满
                ScenicSpotsViewHolder hold = new ScenicSpotsViewHolder(view);
                return hold;
            }
            if (viewType == MCH_FOOD) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_travel_assistant_detail_delicious_food_item, parent, false);//解决宽度不能铺满
                FineFoodViewHolder hold = new FineFoodViewHolder(view);
                return hold;
            }

            if (viewType == MCH_HOTEL) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_travel_assistant_detail_hotel_item, parent, false);//解决宽度不能铺满
                HotelViewHolder hold = new HotelViewHolder(view);
                return hold;
            }

            if (viewType == TRAFFIC) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_travel_assistant_detail_traffic_item, parent, false);//解决宽度不能铺满
                TrafficViewHolder hold = new TrafficViewHolder(view);
                return hold;
            }

            if (viewType == NOTE) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_travel_assistant_detail_remarks_item, parent, false);//解决宽度不能铺满
                RemarksViewHolder hold = new RemarksViewHolder(view);
                return hold;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_travel_assistant_detail_scenic_spots_item, parent, false);//解决宽度不能铺满
        ScenicSpotsViewHolder hold = new ScenicSpotsViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int itemPosition) {

        try {

            TripDetailsResponse.TripDetailsEntity tripDetailsEntity = detailsEntityList.get(itemPosition);
            String photoUrl = tripDetailsEntity.getPhoto();
            String title = tripDetailsEntity.getTitle();
            String intro = tripDetailsEntity.getIntro();
            String address = tripDetailsEntity.getAddress();
            String playDurationTime = tripDetailsEntity.getPlayDuration();
            String distance = tripDetailsEntity.getDistance(); //距离
            String time = tripDetailsEntity.getTime();
            if (holder instanceof ScenicSpotsViewHolder) {

                int tripPlaceId = tripDetailsEntity.getTripPlaceId();
                GlideUtil.loadImage(mContext, photoUrl, ((ScenicSpotsViewHolder) holder).mImgScenicSpots);

                ((ScenicSpotsViewHolder) holder).mLlytScenicSpotItem.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {

                        travelAssistantDetailItemListener.setItemDeleteListener(itemPosition, tripDetailsEntity);

                        return false;
                    }
                });

                ((ScenicSpotsViewHolder) holder).mTvScenicSpotsName.setText(title);

                ((ScenicSpotsViewHolder) holder).mTvScenicSpotsIntro.setText(intro);

            } else if (holder instanceof FineFoodViewHolder) {

                ((FineFoodViewHolder) holder).mTvStoreName.setText(title);
                ((FineFoodViewHolder) holder).mTvLocationName.setText(address);
                GlideUtil.loadImage(mContext, photoUrl, ((FineFoodViewHolder) holder).mImgFineFood);
                ((FineFoodViewHolder) holder).mLlytFineFoodItem.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {

                        travelAssistantDetailItemListener.setItemDeleteListener(itemPosition, tripDetailsEntity);

                        return false;
                    }
                });
            } else if (holder instanceof HotelViewHolder) {

                ((HotelViewHolder) holder).mTvHotelName.setText(title);
                ((HotelViewHolder) holder).mTvHotelDes.setText(intro);
                ((HotelViewHolder) holder).mTvHotelSuggestedPlayTime.setText(playDurationTime);
                GlideUtil.loadImage(mContext, photoUrl, ((HotelViewHolder) holder).mImgHotel);
                ((HotelViewHolder) holder).mLlytHotelItem.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {

                        travelAssistantDetailItemListener.setItemDeleteListener(itemPosition, tripDetailsEntity);

                        return false;
                    }
                });
            } else if (holder instanceof RemarksViewHolder) {

                ((RemarksViewHolder) holder).mTvRemarksContent.setText(title);
                ((RemarksViewHolder) holder).mTvRemarksEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        travelAssistantDetailItemListener.setItemRemarksEditListener(itemPosition, tripDetailsEntity);
                    }
                });

                ((RemarksViewHolder) holder).mLlytRemarksItem.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {

                        travelAssistantDetailItemListener.setItemDeleteListener(itemPosition, tripDetailsEntity);

                        return false;
                    }
                });
            } else if (holder instanceof TrafficViewHolder) {

                ((TrafficViewHolder) holder).mTvLocationDistance.setText(distance);
                ((TrafficViewHolder) holder).mTvDuration.setText(playDurationTime);
                ((TrafficViewHolder) holder).mTvTravelPath.setText(address);
                ((TrafficViewHolder) holder).mTvTravelTime.setText("行程时间:" + time);

                ((TrafficViewHolder) holder).mLlytTrafficItem.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {

                        travelAssistantDetailItemListener.setItemDeleteListener(itemPosition, tripDetailsEntity);

                        return false;
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return detailsEntityList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemViewType(int position) {
        TripDetailsResponse.TripDetailsEntity detailsEntity = getItem(position);
        String tripPlaceType = detailsEntity.getTripPlaceType();
        if (tripPlaceType.equals(TravelAssistantDetailTypeEnum.MCH_SCENIC.getValue())) {
            return MCH_SCENIC;
        } else if (tripPlaceType.equals(TravelAssistantDetailTypeEnum.MCH_FOOD.getValue())) {
            return MCH_FOOD;
        }
        if (tripPlaceType.equals(TravelAssistantDetailTypeEnum.MCH_HOTEL.getValue())) {
            return MCH_HOTEL;
        } else if (tripPlaceType.equals(TravelAssistantDetailTypeEnum.MCH_RECREATION.getValue())) {
            return MCH_RECREATION;
        } else if (tripPlaceType.equals(TravelAssistantDetailTypeEnum.TRAFFIC.getValue())) {
            return TRAFFIC;
        } else if (tripPlaceType.equals(TravelAssistantDetailTypeEnum.NOTE.getValue())) {
            return NOTE;
        }
        return 0;
    }

    public TripDetailsResponse.TripDetailsEntity getItem(int position) {
        return position >= 0 && position < getItemCount() ? detailsEntityList.get(position) : null;
    }


    //景点适配器
    public static class ScenicSpotsViewHolder extends RecyclerView.ViewHolder {

        //景点照片
        ImageView mImgScenicSpots;

        LinearLayout mLlytScenicSpotItem;

        //景点名称
        TextView mTvScenicSpotsName;

        //景点简介
        TextView mTvScenicSpotsIntro;

        public ScenicSpotsViewHolder(View itemView) {
            super(itemView);
            mImgScenicSpots = itemView.findViewById(R.id.image_scenic_spots);
            mLlytScenicSpotItem = itemView.findViewById(R.id.llyt_scenic_spot_item);
            mTvScenicSpotsName = itemView.findViewById(R.id.tv_scenic_spots_name);
            mTvScenicSpotsIntro = itemView.findViewById(R.id.tv_scenic_spots_des);
        }
    }

    //美食适配器
    public static class FineFoodViewHolder extends RecyclerView.ViewHolder {

        //美食照片
        ImageView mImgFineFood;

        LinearLayout mLlytFineFoodItem;

        //商店名称
        TextView mTvStoreName;

        //美食位置
        TextView mTvLocationName;

        public FineFoodViewHolder(View itemView) {
            super(itemView);
            mImgFineFood = itemView.findViewById(R.id.image_fine_food);
            mLlytFineFoodItem = itemView.findViewById(R.id.llyt_fine_food_item);
            mTvStoreName = itemView.findViewById(R.id.tv_store_name);
            mTvLocationName = itemView.findViewById(R.id.tv_location_name);

        }
    }

    //酒店适配器
    public static class HotelViewHolder extends RecyclerView.ViewHolder {

        //酒店照片
        ImageView mImgHotel;

        //酒店布局item
        LinearLayout mLlytHotelItem;

        //酒店名称
        TextView mTvHotelName;

        //酒店描述
        TextView mTvHotelDes;

        //建议游玩时间
        TextView mTvHotelSuggestedPlayTime;

        public HotelViewHolder(View itemView) {
            super(itemView);
            mImgHotel = itemView.findViewById(R.id.image_hotel);
            mLlytHotelItem = itemView.findViewById(R.id.llyt_hotel_item);
            mTvHotelName = itemView.findViewById(R.id.tv_hotel_name);
            mTvHotelDes = itemView.findViewById(R.id.tv_hotel_des);
            mTvHotelSuggestedPlayTime = itemView.findViewById(R.id.tv_suggested_play_time);

        }
    }


    //备注适配器
    public static class RemarksViewHolder extends RecyclerView.ViewHolder {

        LinearLayout mLlytRemarksItem;

        //备注编辑
        TextView mTvRemarksEdit;

        //备注内容
        TextView mTvRemarksContent;

        public RemarksViewHolder(View itemView) {
            super(itemView);
            mTvRemarksEdit = itemView.findViewById(R.id.tv_edit_remark);
            mLlytRemarksItem = itemView.findViewById(R.id.llyt_remarks_item);
            mTvRemarksContent = itemView.findViewById(R.id.tv_remarks_content);
        }
    }

    //交通适配器
    public static class TrafficViewHolder extends RecyclerView.ViewHolder {

        LinearLayout mLlytTrafficItem;
        //公里数
        TextView mTvLocationDistance;

        //行程时间段
        TextView mTvTravelTime;

        //行程起始地点和结束地点
        TextView mTvTravelPath;

        //行程时长
        TextView mTvDuration;

        public TrafficViewHolder(View itemView) {
            super(itemView);
            mTvLocationDistance = itemView.findViewById(R.id.tv_location_distance);
            mLlytTrafficItem = itemView.findViewById(R.id.llyt_traffic_item);
            mTvTravelTime = itemView.findViewById(R.id.tv_travel_time);
            mTvTravelPath = itemView.findViewById(R.id.tv_travel_path);
            mTvDuration = itemView.findViewById(R.id.tv_duration);
        }
    }

    public interface TravelAssistantDetailItemListener {

        void setItemDeleteListener(int position, TripDetailsResponse.TripDetailsEntity tripDetailsEntity);

        void setItemRemarksEditListener(int position, TripDetailsResponse.TripDetailsEntity tripDetailsEntity);
    }
}
