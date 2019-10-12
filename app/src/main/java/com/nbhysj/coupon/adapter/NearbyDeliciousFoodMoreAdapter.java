package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Enum.MchTypeEnum;
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.model.response.NearbyScenicSpotsResponse;
import com.nbhysj.coupon.ui.GroupMchDetailsActivity;
import com.nbhysj.coupon.ui.HomestayDetailActivity;
import com.nbhysj.coupon.ui.HotelDetailsActivity;
import com.nbhysj.coupon.ui.ScenicSpotDetailActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.RoundedImageView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

/**
 * @author hysj created at 2019/06/11.
 * description:附近美食查看适配器
 */
public class NearbyDeliciousFoodMoreAdapter extends RecyclerView.Adapter<NearbyDeliciousFoodMoreAdapter.ViewHolder> {

    List<MchTypeBean> nearbyFoodList;
    private Context mContext;

    public NearbyDeliciousFoodMoreAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setNearbyScenicSpotsList(List<MchTypeBean> nearbyFoodList) {

        this.nearbyFoodList = nearbyFoodList;
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
            MchTypeBean mchTypeBean = nearbyFoodList.get(itemPosition);
            int mchId = mchTypeBean.getId();
            String mchName = mchTypeBean.getMchName();
            String mFoodPhotoUrl = mchTypeBean.getPhoto();
            int consumePrice = mchTypeBean.getConsumePrice();
            String distance = mchTypeBean.getDistance();
            float commentScore = mchTypeBean.getCommentScore();
            String mchType = mchTypeBean.getMchType();
            List<MchTypeBean.TagsEntity> tagsEntityList = mchTypeBean.getTags();
            holder.mTvDeliciousFoodName.setText(mchName);
            holder.mTvScore.setText(commentScore + "分");
            holder.mTvScenicSpotsDistance.setText("距景点" + distance);
            holder.mTvPerCapitaPrice.setText("人均¥" + String.valueOf(consumePrice));

            GlideUtil.loadImage(mContext, mFoodPhotoUrl, holder.mImgDdeliciousFood);

            if (tagsEntityList != null) {
                if (tagsEntityList.size() > 0) {

                    holder.mTagFlowlayoutDeliciousFoodLabel.setAdapter(new TagAdapter<MchTypeBean.TagsEntity>(tagsEntityList) {

                        @Override
                        public View getView(FlowLayout parent, int position, MchTypeBean.TagsEntity tagsEntity) {
                            TextView tagName = null;
                            if(position < 2) {

                                LayoutInflater mInflater = LayoutInflater.from(mContext);
                                 tagName = (TextView) mInflater.inflate(R.layout.layout_flowlayout_tag_interaction,
                                        holder.mTagFlowlayoutDeliciousFoodLabel, false);
                                tagName.setText(tagsEntity.getTitle());
                            }
                            return tagName;

                        }
                    });
                }
            }


            holder.mRlytNearbyFoodItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();

                    String mchScenicSpot = MchTypeEnum.MCH_SCENIC.getValue();
                    String mchFood = MchTypeEnum.MCH_FOOD.getValue();
                    String mchGroup = MchTypeEnum.MCH_GROUP.getValue();
                    String mchHotel = MchTypeEnum.MCH_HOTEL.getValue();
                    String mchRecreation = MchTypeEnum.MCH_RECREATION.getValue();
                   if (mchType.equals(mchFood)) {

                        intent.setClass(mContext, ScenicSpotDetailActivity.class);
                        intent.putExtra("mchId", mchId);
                        mContext.startActivity(intent);

                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return nearbyFoodList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //美食
        TextView mTvDeliciousFoodName;
        //美食图片
        RoundedImageView mImgDdeliciousFood;
        //景区评分
        TextView mTvScore;
        //距离景区距离
        TextView mTvScenicSpotsDistance;
        //人均价格
        TextView mTvPerCapitaPrice;
        //美食标签
        TagFlowLayout mTagFlowlayoutDeliciousFoodLabel;

        RelativeLayout mRlytNearbyFoodItem;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvDeliciousFoodName = itemView.findViewById(R.id.tv_delicious_food_name);
            mImgDdeliciousFood = itemView.findViewById(R.id.image_delicious_food);
            mTvScore = itemView.findViewById(R.id.tv_score);
            mTvPerCapitaPrice = itemView.findViewById(R.id.tv_per_capita_price);
            mTagFlowlayoutDeliciousFoodLabel = itemView.findViewById(R.id.flowlayout_delicious_food_label);
            mTvScenicSpotsDistance = itemView.findViewById(R.id.tv_scenic_spots_distance);
            mRlytNearbyFoodItem = itemView.findViewById(R.id.rlyt_nearby_food_item);

        }
    }
}
