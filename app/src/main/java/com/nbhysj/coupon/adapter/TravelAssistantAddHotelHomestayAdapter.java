package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.TripScenicSpotAddCountryBean;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.RoundedImageView;
import com.nbhysj.coupon.view.StarBarView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author hysj created at 2019/05/17.
 * description: 添加酒店民宿适配器
 */
public class TravelAssistantAddHotelHomestayAdapter extends RecyclerView.Adapter<TravelAssistantAddHotelHomestayAdapter.ViewHolder> {

    List<TripScenicSpotAddCountryBean.TravelAssistantAddScenicSpotEntity> scenicSpotList;
    private Context mContext;
    private HotelHomestayAddListener hotelHomestayAddListener;

    public TravelAssistantAddHotelHomestayAdapter(Context mContext, HotelHomestayAddListener hotelHomestayAddListener) {

        this.mContext = mContext;
        this.hotelHomestayAddListener = hotelHomestayAddListener;
    }

    public void setScenicSpotList(List<TripScenicSpotAddCountryBean.TravelAssistantAddScenicSpotEntity> scenicSpotList) {

        this.scenicSpotList = scenicSpotList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_add_hotel_homestay_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            TripScenicSpotAddCountryBean.TravelAssistantAddScenicSpotEntity travelAssistantAddScenicSpotEntity = scenicSpotList.get(itemPosition);

            String photoUrl = travelAssistantAddScenicSpotEntity.getPhoto();
            int level = travelAssistantAddScenicSpotEntity.getLevel();
            String fineFoodTag = travelAssistantAddScenicSpotEntity.getTags();
            int mSelectedStatus = travelAssistantAddScenicSpotEntity.getSelectedStatus();
            String mchName = travelAssistantAddScenicSpotEntity.getMchName();
            String intro = travelAssistantAddScenicSpotEntity.getIntro();
            int commentScore = travelAssistantAddScenicSpotEntity.getScore();

            GlideUtil.loadImage(mContext, photoUrl, holder.mImgHotelReputation);
            holder.mTvHotelHomestayName.setText(mchName);
            holder.mTvHotelReputationScore.setText(String.valueOf(commentScore));
            if (intro != null) {

                holder.mTvHotelHomestayDes.setText(intro);
            } else {

                holder.mTvHotelHomestayDes.setText("");
            }
            if (level == 0) {
                holder.mTvHotelStar.setVisibility(View.GONE);
            } else {
                holder.mTvHotelStar.setText(level + "星级");
                holder.mTvHotelStar.setVisibility(View.VISIBLE);
            }

            holder.mLlytAddHotelHomestay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    hotelHomestayAddListener.setHotelHomestayAddListener(travelAssistantAddScenicSpotEntity);
                }
            });

            if (mSelectedStatus == 0) {

                holder.mImgAddMyTravel.setBackgroundResource(R.mipmap.icon_travel_assistant_selected_tag);
                holder.mImgAddMyTravel.setEnabled(false);
            } else {
                holder.mImgAddMyTravel.setBackgroundResource(R.mipmap.icon_travel_assistant_add_tag);
                holder.mImgAddMyTravel.setEnabled(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return scenicSpotList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //酒店民宿照片
        RoundedImageView mImgHotelReputation;

        LinearLayout mLlytAddHotelHomestay;

        ImageView mImgAddMyTravel;

        //酒店民宿名称
        TextView mTvHotelHomestayName;

        //酒店民宿评分
        TextView mTvHotelReputationScore;

        //评分
        TextView mTvHotelStar;

        //酒店民宿描述
        TextView mTvHotelHomestayDes;

        public ViewHolder(View itemView) {
            super(itemView);

            mImgHotelReputation = itemView.findViewById(R.id.image_hotel_reputation);
            mLlytAddHotelHomestay = itemView.findViewById(R.id.llyt_add_hotel_homestay);
            mImgAddMyTravel = itemView.findViewById(R.id.img_add_my_travel);
            mTvHotelHomestayName = itemView.findViewById(R.id.tv_hotel_homestay_name);
            mTvHotelReputationScore = itemView.findViewById(R.id.tv_hotel_reputation_score);
            mTvHotelStar = itemView.findViewById(R.id.tv_hotel_star);
            mTvHotelHomestayDes = itemView.findViewById(R.id.tv_hotel_description);
        }
    }

    public interface HotelHomestayAddListener {

        void setHotelHomestayAddListener(TripScenicSpotAddCountryBean.TravelAssistantAddScenicSpotEntity travelAssistantAddScenicSpotEntity);

    }

}
