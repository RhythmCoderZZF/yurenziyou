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
import com.nbhysj.coupon.model.response.GroupGoodsBean;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;

import java.util.List;

/**
 * @author hysj created at 2019/05/09.
 * description:景点附近酒店适配器
 */
public class GroupListAdapter extends RecyclerView.Adapter<GroupListAdapter.ViewHolder> {


    List<GroupGoodsBean> groupGoodsBeanList;
    private Context mContext;

    public GroupListAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setGroupList(List<GroupGoodsBean> groupGoodsBeanList) {

        this.groupGoodsBeanList = groupGoodsBeanList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_group_commodity_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
            GroupGoodsBean groupGoodsBean = groupGoodsBeanList.get(itemPosition);
            String photoUrl = groupGoodsBean.getPhoto();
            holder.mTvPopularScenicSpotName.setText(groupGoodsBean.getTitle());
            //  holder.mTvScore.setText(groupGoodsBean.getMarketPrice() + "分");
            // holder.mTvScenicSpotsDistance.setText("距景点" + nearbyScenicSpots.getScenicSpotsDistance() + "m");
            holder.mTvPerCapitaPrice.setText(String.valueOf(groupGoodsBean.getMarketPrice()));
            // holder.mTvHotelType.setText("品质客栈");
            // holder.mTvHotelType.getBackground().setAlpha(30);


            GlideUtil.loadCornersTransformImage(mContext, photoUrl, 5, holder.mImgScenicSpots);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return groupGoodsBeanList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //景区名字
        TextView mTvPopularScenicSpotName;
        //景点图片
        ImageView mImgScenicSpots;
        //景区评分
        TextView mTvScore;
        //酒店类型
        TextView mTvHotelType;
        //距离景区距离
        TextView mTvScenicSpotsDistance;
        //人均价格
        TextView mTvPerCapitaPrice;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvPopularScenicSpotName = itemView.findViewById(R.id.tv_scenic_spots_name);
            mImgScenicSpots = itemView.findViewById(R.id.image_scenic_spots);
            mTvScore = itemView.findViewById(R.id.tv_score);
            mTvHotelType = itemView.findViewById(R.id.tv_hotel_type);
            mTvScenicSpotsDistance = itemView.findViewById(R.id.tv_scenic_spots_distance);
            mTvPerCapitaPrice = itemView.findViewById(R.id.tv_per_capita_price);
        }
    }
}
