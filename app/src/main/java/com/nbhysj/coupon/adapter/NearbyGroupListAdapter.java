package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.nbhysj.coupon.model.response.GroupGoodsBean;
import com.nbhysj.coupon.model.response.NearbyTypeResponse;
import com.nbhysj.coupon.ui.GroupMchDetailsActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.RoundedImageView;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;

import java.util.List;

/**
 * @author hysj created at 2019/05/09.
 * description:景点附近酒店适配器
 */
public class NearbyGroupListAdapter extends RecyclerView.Adapter<NearbyGroupListAdapter.ViewHolder> {


    List<NearbyTypeResponse> groupGoodsBeanList;
    private Context mContext;

    public NearbyGroupListAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setGroupList(List<NearbyTypeResponse> groupGoodsBeanList) {

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
            NearbyTypeResponse groupGoodsBean = groupGoodsBeanList.get(itemPosition);
            int packageId = groupGoodsBean.getId();
            String photoUrl = groupGoodsBean.getPhoto();
            float score = groupGoodsBean.getScore();
            List<NearbyTypeResponse.TagsEntity> tagsEntityList = groupGoodsBean.getTags();
            holder.mTvGroupMchGoodsName.setText(groupGoodsBean.getTitle());
            holder.mTvPerCapitaPrice.setText(String.valueOf(groupGoodsBean.getPrice()));

            GlideUtil.loadImage(mContext, photoUrl, holder.mImgScenicSpots);

            holder.mTvScore.setText(String.valueOf(score) + "分");

            if (tagsEntityList != null && tagsEntityList.size() > 0) {

                holder.mTvGroupGoodsType.setVisibility(View.VISIBLE);
                String tagTitle = tagsEntityList.get(0).getTitle();

                holder.mTvGroupGoodsType.setText(tagTitle);
            } else {
                holder.mTvGroupGoodsType.setVisibility(View.GONE);
            }

            holder.mLlytGroupMchGoodsItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent();
                    intent.setClass(mContext, GroupMchDetailsActivity.class);
                    intent.putExtra("packageId", packageId);
                    mContext.startActivity(intent);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return groupGoodsBeanList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //组合商品名
        TextView mTvGroupMchGoodsName;
        //组合商品图片
        RoundedImageView mImgScenicSpots;
        //景区评分
        TextView mTvScore;
        //酒店类型
        TextView mTvHotelType;
        //距离景区距离
        TextView mTvScenicSpotsDistance;
        //人均价格
        TextView mTvPerCapitaPrice;

        //组合商品类型
        TextView mTvGroupGoodsType;
        LinearLayout mLlytGroupMchGoodsItem;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvGroupMchGoodsName = itemView.findViewById(R.id.tv_group_mch_goods_name);
            mImgScenicSpots = itemView.findViewById(R.id.image_group_mch_goods);
            mTvScore = itemView.findViewById(R.id.tv_score);
            mTvHotelType = itemView.findViewById(R.id.tv_hotel_type);
            mTvScenicSpotsDistance = itemView.findViewById(R.id.tv_scenic_spots_distance);
            mTvPerCapitaPrice = itemView.findViewById(R.id.tv_price);
            mTvGroupGoodsType = itemView.findViewById(R.id.tv_group_goods_type);
            mLlytGroupMchGoodsItem = itemView.findViewById(R.id.llyt_group_mch_goods_item);

        }
    }
}
