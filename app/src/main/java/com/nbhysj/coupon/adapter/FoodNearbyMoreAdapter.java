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
import com.nbhysj.coupon.model.response.MchFoodDetailResponse;
import com.nbhysj.coupon.model.response.NearbyScenicSpotsResponse;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.Tools;
import com.nbhysj.coupon.view.RoundedImageView;
import com.nbhysj.coupon.view.StarBarView;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;

import java.util.List;

/**
 * @author hysj created at 2019/05/14.
 * description:商户附近适配器
 */
public class DeliciousFoodShopRecommendMoreAdapter extends RecyclerView.Adapter<DeliciousFoodShopRecommendMoreAdapter.ViewHolder> {

    List<MchFoodDetailResponse.NearbyFoodEntity> nearbyMchFoodList;
    private Context mContext;

    public DeliciousFoodShopRecommendMoreAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setNearbyScenicSpotsList(List<MchFoodDetailResponse.NearbyFoodEntity> nearbyMchFoodList) {

        this.nearbyMchFoodList = nearbyMchFoodList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_delicious_food_more_recommend_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            MchFoodDetailResponse.NearbyFoodEntity nearbyFoodEntity = nearbyMchFoodList.get(itemPosition);
            String photoUrl = nearbyFoodEntity.getPhoto();
            String title = nearbyFoodEntity.getTitle();
            String county = nearbyFoodEntity.getCounty();
            String distance = nearbyFoodEntity.getDistance();
            double price = nearbyFoodEntity.getPrice();
            float score = nearbyFoodEntity.getScore();

            GlideUtil.loadImage(mContext,photoUrl,holder.mImgDeliciousFood);
            //商户名
            holder.mTvMchName.setText(title);
            //评分
            holder.mStarBarView.setIntegerMark(false);
            holder.mStarBarView.setStarMark(score);

            holder.mTvAveragePricePerPerson.setText(Tools.getTwoDecimalPoint(price));

            holder.mTvMchAddress.setText(county + "  "+distance);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return nearbyMchFoodList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //美食照片
        RoundedImageView mImgDeliciousFood;
        //商户名字
        TextView mTvMchName;
        //人均价格
        TextView mTvAveragePricePerPerson;

        StarBarView mStarBarView;

        //商户位置
        TextView mTvMchAddress;
        public ViewHolder(View itemView) {
            super(itemView);

            mImgDeliciousFood = itemView.findViewById(R.id.image_delicious_food);
            mTvMchName = itemView.findViewById(R.id.tv_mch_name);
            mTvAveragePricePerPerson = itemView.findViewById(R.id.tv_average_price_per_person);
            mStarBarView = itemView.findViewById(R.id.starbar);
            mTvMchAddress = itemView.findViewById(R.id.tv_mch_address);
        }
    }
}
