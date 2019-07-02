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
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.StarBarView;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;

import java.util.List;

/**
 * @author hysj created at 2019/4/27.
 * description:附近景点查看适配器
 */
public class NearbyScenicSpotMoreAdapter extends RecyclerView.Adapter<NearbyScenicSpotMoreAdapter.ViewHolder> {


    List<NearbyScenicSpotsResponse> nearbyScenicSpotsList;
    private Context mContext;

    public NearbyScenicSpotMoreAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setNearbyScenicSpotsList(List<NearbyScenicSpotsResponse> nearbyScenicSpotsList) {

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
            NearbyScenicSpotsResponse nearbyScenicSpots = nearbyScenicSpotsList.get(itemPosition);
            String mScenicSpotsPhoto = nearbyScenicSpots.getScenicSpotsPhoto();
            holder.mTvPopularScenicSpotName.setText(nearbyScenicSpots.getScenicSpotsName());
            holder.mTvScore.setText(nearbyScenicSpots.getScenicSpotsScore() + "分");
            holder.mTvScenicSpotsDistance.setText("距景点" + nearbyScenicSpots.getScenicSpotsDistance() + "m");
            holder.mTvPerCapitaPrice.setText(nearbyScenicSpots.getScenicSpotsTicketPrice());

            GlideUtil.loadCornersTransformImage(mContext, mScenicSpotsPhoto, 5, holder.mImgScenicSpots);

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
        ImageView mImgScenicSpots;
        //景区评分
        TextView mTvScore;
        //距离景区距离
        TextView mTvScenicSpotsDistance;
        //人均价格
        TextView mTvPerCapitaPrice;
        //景点标签
        TextView mTvScenicSpotTag;
        //免费标签
        TextView mTvFreeTag;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvPopularScenicSpotName = itemView.findViewById(R.id.tv_scenic_spots_name);
            mTvScenicSpotTag = itemView.findViewById(R.id.tv_scenic_spot_score_tag);
            mImgScenicSpots = itemView.findViewById(R.id.image_scenic_spots);
            mTvScore = itemView.findViewById(R.id.tv_score);
            mTvPerCapitaPrice = itemView.findViewById(R.id.tv_per_capita_price);
            mTvFreeTag = itemView.findViewById(R.id.tv_free_tag);

        }
    }
}
