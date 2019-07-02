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
import com.nbhysj.coupon.model.response.TripHomePageResponse;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;

import java.util.List;

/**
 * @author hysj created at 2019/05/15.
 * description:行程助手推荐适配器
 */
public class TravelAssisantRecommendAdapter extends RecyclerView.Adapter<TravelAssisantRecommendAdapter.ViewHolder> {

    List<TripHomePageResponse.StrategyEntity> strategyList;
    private Context mContext;

    public TravelAssisantRecommendAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setTravelAssisantStrategyList(List<TripHomePageResponse.StrategyEntity> strategyList) {

        this.strategyList = strategyList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_travel_assistant_recommend_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            TripHomePageResponse.StrategyEntity strategyEntity = strategyList.get(itemPosition);
            String photoUrl = strategyEntity.getPhoto();
            String title = strategyEntity.getTitle();
            String intro = strategyEntity.getIntro();

            holder.mTvScenicSpotSpotName.setText(title);
            holder.mTvScenicSpotSpotDes.setText(intro);
            GlideUtil.loadImage(mContext, photoUrl, holder.mImgScenicSpots);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return strategyList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //景点照片
        ImageView mImgScenicSpots;

        TextView mTvScenicSpotSpotName;

        TextView mTvScenicSpotSpotDes;

        public ViewHolder(View itemView) {
            super(itemView);

            mImgScenicSpots = itemView.findViewById(R.id.image_scenic_spots);
            mTvScenicSpotSpotName = itemView.findViewById(R.id.tv_scenic_spots_name);
            mTvScenicSpotSpotDes = itemView.findViewById(R.id.tv_scenic_spots_des);
        }
    }


}
