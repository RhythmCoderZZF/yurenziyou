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
import com.nbhysj.coupon.common.Enum.MchTypeEnum;
import com.nbhysj.coupon.model.response.DeliciousFoodRecommendResponse;
import com.nbhysj.coupon.model.response.NearbyTypeResponse;
import com.nbhysj.coupon.ui.FoodDetailActivity;
import com.nbhysj.coupon.ui.HomestayDetailActivity;
import com.nbhysj.coupon.ui.HotelDetailsActivity;
import com.nbhysj.coupon.ui.ScenicSpotDetailActivity;
import com.nbhysj.coupon.ui.ScenicSpotsAlbumActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.Tools;
import com.nbhysj.coupon.view.RoundedImageView;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/4/7.
 * description : 酒店附近(美食/娱乐/景区)推荐适配器
 */
public class HotelNearbyAdapter extends RecyclerView.Adapter<HotelNearbyAdapter.ViewHolder> {

    List<NearbyTypeResponse> nearbyTypeList;
    private Context mContext;

    public HotelNearbyAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setHotelNearbyList(List<NearbyTypeResponse> nearbyTypeList) {

        this.nearbyTypeList = nearbyTypeList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_hotel_periphery_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
            NearbyTypeResponse nearbyTypeResponse = nearbyTypeList.get(itemPosition);
            String nearbyTypePhoto = nearbyTypeResponse.getPhoto();
            String title = nearbyTypeResponse.getTitle();
            double price = nearbyTypeResponse.getPrice();
            String mchType = nearbyTypeResponse.getType();
            int mchId = nearbyTypeResponse.getId();

            holder.mTvFoodMchName.setText(title);
            holder.mTvPerCapitaPrice.setText(Tools.getTwoDecimalPoint(price));

            GlideUtil.loadImage(mContext,nearbyTypePhoto,holder.mImgDeliciousFood);

            if (itemPosition == 0) {

                holder.mHeader.setVisibility(View.VISIBLE);
            } else {

                holder.mHeader.setVisibility(View.GONE);
            }

            if (itemPosition == nearbyTypeList.size() - 1) {

                holder.mFooter.setVisibility(View.VISIBLE);
            } else {
                holder.mFooter.setVisibility(View.GONE);
            }

            holder.mLlytHotelNearbyItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    String mchFood = MchTypeEnum.MCH_FOOD.getValue();
                    String mchScenicSpot = MchTypeEnum.MCH_SCENIC.getValue();
                    String mchHotel = MchTypeEnum.MCH_HOTEL.getValue();
                    String mchRecreation = MchTypeEnum.MCH_RECREATION.getValue();
                    if (mchType.equals(mchFood)) {
                        intent.setClass(mContext, FoodDetailActivity.class);
                        intent.putExtra("mchId", mchId);
                        mContext.startActivity(intent);

                    } else if (mchType.equals(mchScenicSpot)) {
                        intent.setClass(mContext, ScenicSpotDetailActivity.class);
                        intent.putExtra("mchId", mchId);
                        intent.putExtra("mchType", mchType);
                        mContext.startActivity(intent);
                    } else if (mchType.equals(mchRecreation)) {
                        intent.setClass(mContext, ScenicSpotDetailActivity.class);
                        intent.putExtra("mchId", mchId);
                        intent.putExtra("mchType", mchType);
                        mContext.startActivity(intent);
                    }else if (mchType.equals(mchHotel)) {
                        String mchHotelType = MchTypeEnum.MCH_HOTEL1.getValue();
                        String mchHomestayType = MchTypeEnum.MCH_HOTEL1.getValue();
                        String type2 = nearbyTypeResponse.getType2();
                        if (type2.equals(mchHotelType)) {

                            intent.setClass(mContext, HotelDetailsActivity.class);
                            intent.putExtra("mchId", mchId);
                            mContext.startActivity(intent);
                        } else if (type2.equals(mchHomestayType)) {

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
        return nearbyTypeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //美食人均价格
        @BindView(R.id.tv_per_capita_price)
        TextView mTvPerCapitaPrice;
        //美食位置
        @BindView(R.id.tv_food_mch_name)
        TextView mTvFoodMchName;
        //美食照片
        @BindView(R.id.image_delicious_food)
        RoundedImageView mImgDeliciousFood;
        @BindView(R.id.llyt_hotel_nearby_item)
        LinearLayout mLlytHotelNearbyItem;
        @BindView(R.id.view_header)
        View mHeader;
        @BindView(R.id.view_footer)
        View mFooter;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
