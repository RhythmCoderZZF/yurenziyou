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
import com.nbhysj.coupon.model.response.DeliciousFoodRecommendResponse;
import com.nbhysj.coupon.model.response.NearbyScenicSpotsResponse;
import com.nbhysj.coupon.model.response.NearbyTypeResponse;
import com.nbhysj.coupon.model.response.ScenicSpotBean;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.StarBarView;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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

        NearbyTypeResponse nearbyScenicSpots = nearbyScenicSpotsList.get(itemPosition);
        String photoUrl = nearbyScenicSpots.getPhoto();
        holder.mTvPopularScenicSpotName.setText(nearbyScenicSpots.getTitle());
        holder.mTvScore.setText(nearbyScenicSpots.getScore() + "分");
        holder.mStarBarScenicSpots.setIntegerMark(true);
        holder.mStarBarScenicSpots.setStarMark(nearbyScenicSpots.getLevel());
        holder.mTvScenicSpotsDistance.setText("距景点" + nearbyScenicSpots.getDistance());
        holder.mTvPerCapitaPrice.setText(String.valueOf(nearbyScenicSpots.getPrice()));

        if (photoUrl != null) {
            GlideUtil.loadCornersTransformImage(mContext, photoUrl, 5, holder.mImgScenicSpots);
        }
      /*  } catch (Exception e) {
            e.printStackTrace();
        }*/
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
        //评分星级
        StarBarView mStarBarScenicSpots;
        //距离景区距离
        TextView mTvScenicSpotsDistance;
        //人均价格
        TextView mTvPerCapitaPrice;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvPopularScenicSpotName = itemView.findViewById(R.id.tv_scenic_spots_name);
            mImgScenicSpots = itemView.findViewById(R.id.image_scenic_spots);
            mTvScore = itemView.findViewById(R.id.tv_score);
            mStarBarScenicSpots = itemView.findViewById(R.id.starbar_scenic_spots);
            mTvScenicSpotsDistance = itemView.findViewById(R.id.tv_scenic_spots_distance);
            mTvPerCapitaPrice = itemView.findViewById(R.id.tv_per_capita_price);
        }
    }
}
