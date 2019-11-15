package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Enum.MchTypeEnum;
import com.nbhysj.coupon.model.response.NearbyTypeResponse;
import com.nbhysj.coupon.ui.FoodDetailActivity;
import com.nbhysj.coupon.ui.GroupMchDetailsActivity;
import com.nbhysj.coupon.ui.HomestayDetailActivity;
import com.nbhysj.coupon.ui.HotelDetailsActivity;
import com.nbhysj.coupon.ui.ScenicSpotDetailActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.Tools;
import com.nbhysj.coupon.view.RoundedImageView;
import com.nbhysj.coupon.view.StarBarView;

import java.util.List;

/**
 * @author hysj created at 2019/4/27.
 * description:景点附近适配器
 */
public class NearbyScenicSpotAdapter extends RecyclerView.Adapter<NearbyScenicSpotAdapter.ViewHolder> {


    List<NearbyTypeResponse> nearbyScenicSpotsList;
    private Context mContext;

    public NearbyScenicSpotAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setNearbyScenicSpotsList(List<NearbyTypeResponse> nearbyScenicSpotsList) {

        this.nearbyScenicSpotsList = nearbyScenicSpotsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_near_scenic_spot_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
            NearbyTypeResponse nearbyScenicSpots = nearbyScenicSpotsList.get(itemPosition);
            String photoUrl = nearbyScenicSpots.getPhoto();
            float score = nearbyScenicSpots.getScore();
            double price = nearbyScenicSpots.getPrice();

            int mchId = nearbyScenicSpots.getId();
            String title = nearbyScenicSpots.getTitle();
            String mchType = nearbyScenicSpots.getType();
            holder.mTvPopularScenicSpotName.setText(title);
            holder.mTvScore.setText(score + "分");
            holder.mStarBarScenicSpots.setIntegerMark(true);
            holder.mStarBarScenicSpots.setStarMark(score);
            holder.mTvScenicSpotsDistance.setText("距景点" + nearbyScenicSpots.getDistance());
            holder.mTvPerCapitaPrice.setText(Tools.getTwoDecimalPoint(price));


            if (photoUrl != null) {
                GlideUtil.loadImage(mContext, photoUrl, holder.mImgScenicSpots);
            }

            holder.mRlytScenicSpotNearbyItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();

                    String mchScenicSpot = MchTypeEnum.MCH_SCENIC.getValue();
                    String mchFood = MchTypeEnum.MCH_FOOD.getValue();
                    String mchGroup = MchTypeEnum.MCH_GROUP.getValue();
                    String mchHotel = MchTypeEnum.MCH_HOTEL.getValue();
                    String mchRecreation = MchTypeEnum.MCH_RECREATION.getValue();
                    if (mchType.equals(mchScenicSpot)) {

                        intent.setClass(mContext, ScenicSpotDetailActivity.class);
                        intent.putExtra("mchId", mchId);
                        intent.putExtra("mchType", mchType);
                        mContext.startActivity(intent);

                    } else if (mchType.equals(mchRecreation)) {
                        intent.setClass(mContext, ScenicSpotDetailActivity.class);
                        intent.putExtra("mchId", mchId);
                        intent.putExtra("mchType", mchType);
                        mContext.startActivity(intent);
                    }else if (mchType.equals(mchFood)) {

                        intent.setClass(mContext, FoodDetailActivity.class);
                        intent.putExtra("mchId", mchId);
                        mContext.startActivity(intent);

                    } else if (mchType.equals(mchGroup)) {

                        intent.setClass(mContext, GroupMchDetailsActivity.class);
                        intent.putExtra("packageId", mchId);
                        mContext.startActivity(intent);

                    } else if (mchType.equals(mchHotel)) {
                        String mchHotel1 = MchTypeEnum.MCH_HOTEL1.getValue();
                        String mchHomestay = MchTypeEnum.MCH_HOTEL2.getValue();
                        String type2 = nearbyScenicSpots.getType2();

                        if (mchHotel1.equals(type2)) {

                            intent.setClass(mContext, HotelDetailsActivity.class);
                            intent.putExtra("mchId", mchId);
                            mContext.startActivity(intent);

                        } else if (mchHomestay.equals(type2)) {

                            intent.setClass(mContext, HomestayDetailActivity.class);
                            intent.putExtra("mchId", mchId);
                            mContext.startActivity(intent);
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
        return nearbyScenicSpotsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //景区名字
        TextView mTvPopularScenicSpotName;
        //景点图片
        RoundedImageView mImgScenicSpots;
        //景区评分
        TextView mTvScore;
        //评分星级
        StarBarView mStarBarScenicSpots;
        //距离景区距离
        TextView mTvScenicSpotsDistance;
        //人均价格
        TextView mTvPerCapitaPrice;

        RelativeLayout mRlytScenicSpotNearbyItem;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvPopularScenicSpotName = itemView.findViewById(R.id.tv_scenic_spots_name);
            mImgScenicSpots = itemView.findViewById(R.id.image_scenic_spots);
            mTvScore = itemView.findViewById(R.id.tv_score);
            mStarBarScenicSpots = itemView.findViewById(R.id.starbar_scenic_spots);
            mTvScenicSpotsDistance = itemView.findViewById(R.id.tv_scenic_spots_distance);
            mTvPerCapitaPrice = itemView.findViewById(R.id.tv_per_capita_price);
            mRlytScenicSpotNearbyItem = itemView.findViewById(R.id.rlyt_scenic_spot_nearby_item);
        }
    }
}
