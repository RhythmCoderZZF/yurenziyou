package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.NearbyScenicSpotsResponse;
import com.nbhysj.coupon.util.GlideUtil;

import java.util.List;

/**
 * @author hysj created at 2019/06/11.
 * description:附近美食查看适配器
 */
public class NearbyDeliciousFoodMoreAdapter extends RecyclerView.Adapter<NearbyDeliciousFoodMoreAdapter.ViewHolder> {

    List<NearbyScenicSpotsResponse> nearbyScenicSpotsList;
    private Context mContext;

    public NearbyDeliciousFoodMoreAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setNearbyScenicSpotsList(List<NearbyScenicSpotsResponse> nearbyScenicSpotsList) {

        this.nearbyScenicSpotsList = nearbyScenicSpotsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_near_delicious_food_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
            NearbyScenicSpotsResponse nearbyScenicSpots = nearbyScenicSpotsList.get(itemPosition);
            String mScenicSpotsPhoto = nearbyScenicSpots.getScenicSpotsPhoto();
            holder.mTvDeliciousFoodName.setText(nearbyScenicSpots.getScenicSpotsName());
            holder.mTvScore.setText(nearbyScenicSpots.getScenicSpotsScore() + "分");
            holder.mTvScenicSpotsDistance.setText("距景点" + nearbyScenicSpots.getScenicSpotsDistance() + "m");
            holder.mTvPerCapitaPrice.setText(nearbyScenicSpots.getScenicSpotsTicketPrice());

            GlideUtil.loadCornersTransformImage(mContext, mScenicSpotsPhoto, 5, holder.mImgDdeliciousFood);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return nearbyScenicSpotsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //美食
        TextView mTvDeliciousFoodName;
        //美食图片
        ImageView mImgDdeliciousFood;
        //景区评分
        TextView mTvScore;
        //距离景区距离
        TextView mTvScenicSpotsDistance;
        //人均价格
        TextView mTvPerCapitaPrice;
        //美食标签
        TextView mTagFlowlayoutDeliciousFoodLabel;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvDeliciousFoodName = itemView.findViewById(R.id.tv_delicious_food_name);
            mImgDdeliciousFood = itemView.findViewById(R.id.image_delicious_food);
            mTvScore = itemView.findViewById(R.id.tv_score);
            mTvPerCapitaPrice = itemView.findViewById(R.id.tv_per_capita_price);
            mTagFlowlayoutDeliciousFoodLabel = itemView.findViewById(R.id.flowlayout_delicious_food_label);
        }
    }
}
