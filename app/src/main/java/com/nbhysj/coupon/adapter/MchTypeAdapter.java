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

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Enum.MchTypeEnum;
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.model.response.TripDetailsResponse;
import com.nbhysj.coupon.util.GlideUtil;

import java.util.List;

/**
 * @author hysj created at 2019/09/11.
 * description: 商户类型适配器
 */
public class MchTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

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

    List<MchTypeBean> mchTypeList;
    private Context mContext;

    public MchTypeAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setMchTypeDetailList(List<MchTypeBean> mchTypeList) {

        this.mchTypeList = mchTypeList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        try {
            if (viewType == MCH_SCENIC) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_scenic_spots_item, parent, false);//解决宽度不能铺满
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

            MchTypeBean mchTypeBean = mchTypeList.get(itemPosition);
            String photoUrl = mchTypeBean.getPhoto();
            String mchName = mchTypeBean.getMchName();
            String intro = mchTypeBean.getIntro();
            String address = mchTypeBean.getAddress();
            String distance = mchTypeBean.getDistance();      //距离
            double commentScore = mchTypeBean.getCommentScore(); //评分
            if (holder instanceof ScenicSpotsViewHolder) {

                GlideUtil.loadImage(mContext, photoUrl, ((ScenicSpotsViewHolder) holder).mImgScenicSpots);

                ((ScenicSpotsViewHolder) holder).mRlytScenicSpotItem.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {

                      //  travelAssistantDetailItemListener.setItemDeleteListener(itemPosition, mchTypeList);

                        return false;
                    }
                });

                ((ScenicSpotsViewHolder) holder).mTvScenicSpotsName.setText(mchName);

                ((ScenicSpotsViewHolder) holder).mTvScenicSpotsDes.setText(intro);

                ((ScenicSpotsViewHolder)holder).mTvScenicSpotsScore.setText(String.valueOf(commentScore));

            } else if (holder instanceof FineFoodViewHolder) {

                ((FineFoodViewHolder) holder).mTvStoreName.setText(mchName);
                ((FineFoodViewHolder) holder).mTvLocationName.setText(address);
                GlideUtil.loadImage(mContext, photoUrl, ((FineFoodViewHolder) holder).mImgFineFood);
                ((FineFoodViewHolder) holder).mLlytFineFoodItem.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {

                      //  travelAssistantDetailItemListener.setItemDeleteListener(itemPosition, mchTypeList);

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
        return mchTypeList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemViewType(int position) {
        MchTypeBean mchTypeBean = getItem(position);
        String tripPlaceType = mchTypeBean.getMchType();
        if (tripPlaceType.equals(MchTypeEnum.MCH_SCENIC.getValue())) {
            return MCH_SCENIC;
        } else if (tripPlaceType.equals(MchTypeEnum.MCH_FOOD.getValue())) {
            return MCH_FOOD;
        }
        if (tripPlaceType.equals(MchTypeEnum.MCH_HOTEL.getValue())) {
            return MCH_HOTEL;
        } else if (tripPlaceType.equals(MchTypeEnum.MCH_RECREATION.getValue())) {
            return MCH_RECREATION;
        }
        return 0;
    }

    public MchTypeBean getItem(int position) {
        return position >= 0 && position < getItemCount() ? mchTypeList.get(position) : null;
    }


    //景点适配器
    public static class ScenicSpotsViewHolder extends RecyclerView.ViewHolder {

        //景点照片
        ImageView mImgScenicSpots;

        LinearLayout mRlytScenicSpotItem;

        //景点名称
        TextView mTvScenicSpotsName;

        //景点简介
        TextView mTvScenicSpotsDes;

        //景点评分
        TextView mTvScenicSpotsScore;

        public ScenicSpotsViewHolder(View itemView) {
            super(itemView);
            mImgScenicSpots = itemView.findViewById(R.id.image_scenic_spots);
            mRlytScenicSpotItem = itemView.findViewById(R.id.rlyt_scenic_spot_item);
            mTvScenicSpotsName = itemView.findViewById(R.id.tv_scenic_spots_name);
            mTvScenicSpotsDes = itemView.findViewById(R.id.tv_scenic_spots_des);
            mTvScenicSpotsScore = itemView.findViewById(R.id.tv_scenic_spots_score);
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

        void setItemDeleteListener(int position, TripDetailsResponse.TripDetailsEntity mchTypeList);

        void setItemRemarksEditListener(int position, TripDetailsResponse.TripDetailsEntity mchTypeList);
    }
}
