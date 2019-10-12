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
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.model.response.NearbyScenicSpotsResponse;
import com.nbhysj.coupon.ui.FoodDetailActivity;
import com.nbhysj.coupon.ui.GroupMchDetailsActivity;
import com.nbhysj.coupon.ui.HomestayDetailActivity;
import com.nbhysj.coupon.ui.HotelDetailsActivity;
import com.nbhysj.coupon.ui.ScenicSpotDetailActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.RoundedImageView;
import com.nbhysj.coupon.view.StarBarView;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

/**
 * @author hysj created at 2019/4/27.
 * description:附近景点(附近酒店和景点共用)查看适配器
 */
public class NearbyScenicSpotMoreAdapter extends RecyclerView.Adapter<NearbyScenicSpotMoreAdapter.ViewHolder> {


    List<MchTypeBean> nearbyScenicSpotsList;
    private Context mContext;

    public NearbyScenicSpotMoreAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setNearbyScenicSpotsList(List<MchTypeBean> nearbyScenicSpotsList) {

        this.nearbyScenicSpotsList = nearbyScenicSpotsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_near_scenic_spot_more_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
            MchTypeBean nearbyScenicSpots = nearbyScenicSpotsList.get(itemPosition);
            int mchId = nearbyScenicSpots.getId();
            String mScenicSpotsPhoto = nearbyScenicSpots.getPhoto();
            holder.mTvPopularScenicSpotName.setText(nearbyScenicSpots.getMchName());
            String mchType = nearbyScenicSpots.getMchType();
            holder.mTvScore.setText(nearbyScenicSpots.getCommentScore() + "分");

            String distance = nearbyScenicSpots.getDistance();

            if(!TextUtils.isEmpty(distance))
            {
                holder.mTvScenicSpotsDistance.setText("距景点" + distance);
            }

            holder.mTvPerCapitaPrice.setText(String.valueOf(nearbyScenicSpots.getConsumePrice()));

            GlideUtil.loadImage(mContext, mScenicSpotsPhoto, holder.mImgScenicSpots);

            List<MchTypeBean.TagsEntity> tagsEntityList = nearbyScenicSpots.getTags();

            if(tagsEntityList != null && tagsEntityList.size() > 0)
            {
                holder.mTvScenicSpotNearByTag.setVisibility(View.VISIBLE);
                String title = tagsEntityList.get(0).getTitle();
                holder.mTvScenicSpotNearByTag.setText(title);
            } else {
                holder.mTvScenicSpotNearByTag.setVisibility(View.GONE);
            }

            holder.mLlytScenicSpotNearbyHotelItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent();
                    String mchFood = MchTypeEnum.MCH_FOOD.getValue();
                    String mchScenicSpot = MchTypeEnum.MCH_SCENIC.getValue();
                    String mchHotel = MchTypeEnum.MCH_HOTEL.getValue();
                    String mchRecreation = MchTypeEnum.MCH_RECREATION.getValue();
                    String mchGroup = MchTypeEnum.MCH_GROUP2.getValue();
                    if (mchType != null) {
                        if (mchType.equals(mchFood)) {
                            intent.setClass(mContext, FoodDetailActivity.class);
                            intent.putExtra("mchId", mchId);
                            mContext.startActivity(intent);

                        } else if (mchType.equals(mchScenicSpot)) {
                            intent.setClass(mContext, ScenicSpotDetailActivity.class);
                            intent.putExtra("mchId", mchId);
                            mContext.startActivity(intent);
                        } else if (mchType.equals(mchRecreation)) {
                            intent.setClass(mContext, ScenicSpotDetailActivity.class);
                            intent.putExtra("mchId", mchId);
                            mContext.startActivity(intent);
                        } else if (mchType.equals(mchHotel)) {

                            String mchHotel1 = MchTypeEnum.MCH_HOTEL1.getValue();//酒店
                            String mchHotel2 = MchTypeEnum.MCH_HOTEL2.getValue(); //民宿
                            String mchType2 = nearbyScenicSpots.getMchType2();
                            if (mchType2 != null) {
                                if (mchType2.equals(mchHotel1)) {

                                    intent.setClass(mContext, HotelDetailsActivity.class);
                                    intent.putExtra("mchId", mchId);
                                    mContext.startActivity(intent);
                                } else if (mchType2.equals(mchHotel2)) {

                                    intent.setClass(mContext, HomestayDetailActivity.class);
                                    intent.putExtra("mchId", mchId);
                                    mContext.startActivity(intent);
                                }
                            }

                        } else if (mchType.equals(mchGroup)) {

                            intent.setClass(mContext, GroupMchDetailsActivity.class);
                            intent.putExtra("packageId", mchId);
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
        //距离景区距离
        TextView mTvScenicSpotsDistance;
        //人均价格
        TextView mTvPerCapitaPrice;
        //景点标签
        TextView mTvScenicSpotNearByTag;

        LinearLayout mLlytScenicSpotNearbyHotelItem;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvPopularScenicSpotName = itemView.findViewById(R.id.tv_scenic_spots_name);
            mImgScenicSpots = itemView.findViewById(R.id.image_scenic_spots);
            mTvScore = itemView.findViewById(R.id.tv_score);
            mTvPerCapitaPrice = itemView.findViewById(R.id.tv_per_capita_price);
            mTvScenicSpotNearByTag = itemView.findViewById(R.id.tv_scenic_spot_tag);
            mTvScenicSpotsDistance = itemView.findViewById(R.id.tv_scenic_spots_distance);
            mLlytScenicSpotNearbyHotelItem = itemView.findViewById(R.id.llyt_scenic_spot_nearby_hotel_item);

        }
    }
}
